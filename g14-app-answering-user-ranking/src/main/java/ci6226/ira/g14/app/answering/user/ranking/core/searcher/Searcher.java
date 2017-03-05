package ci6226.ira.g14.app.answering.user.ranking.core.searcher;


import ci6226.ira.g14.app.answering.user.ranking.core.indexer.Indexer;
import ci6226.ira.g14.app.answering.user.ranking.model.UserRank;
import ci6226.ira.g14.common.core.searcher.BaseSearcher;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Fields;
import org.apache.lucene.index.MultiFields;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * User ranking searcher.
 *
 * @author Zhou Shengsheng
 *
 */
@Component
@Lazy
@ConfigurationProperties(prefix = "lucene")
public class Searcher extends BaseSearcher<UserRank> {

    private static final Logger logger = LoggerFactory.getLogger(Searcher.class);

    // max user count allowed in the ranking list
    @Getter @Setter private int maxUserCount;

    // ranking file
    @Getter @Setter private String rankingFile;

    // cached ranking list
    private List<UserRank> rankingList;

    @SuppressWarnings("unchecked")
    @Override
    public void preProcess() {
        try {
            File file = new File(rankingFile);
            if (file.exists()) {
                // load ranking list from file
                logger.info("loading ranking list from file");
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                rankingList = (List<UserRank>)ois.readObject();
                ois.close();
            } else {
                // get ranking list and save to local file
                logger.info("saving ranking list from file");
                getAnsweringUserRank(maxUserCount);
                file.getParentFile().mkdirs();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(rankingList);
                oos.close();
            }
        } catch (IOException e) {
            logger.error("IOException: {}", e);
        } catch (ClassNotFoundException e) {
            logger.error("ClassNotFoundException: {}", e);
        }
    }

    @Override
    public void preDestroy() {
    }

    /**
     * Get term frequency.
     * @param field
     * @param term
     * @return
     * @throws IOException
     */
    public long getTermFreq(String field, String term) throws IOException {
        return indexReader.totalTermFreq(new Term(field, term));
    }

    /**
     * Compute language rank.
     * @return
     */
    public List<UserRank> getAnsweringUserRank(int userCount) throws IOException {
        int sortStep = 10;
        if (rankingList == null) {
            rankingList = new ArrayList<>(maxUserCount+sortStep);

            // get all terms of a filed
            Fields fields = MultiFields.getFields(indexReader);
            TermsEnum termsEnum = fields.terms(Indexer.INDEX_FILED_USER_ID).iterator();
            BytesRef bytesRef;
            int count = 0;

            // sort comparator
            Comparator<? super UserRank> comparator = (r1, r2) -> {
                long r1Count = r1.getAnwseredCount();
                long r2Count = r2.getAnwseredCount();
                return (r1Count < r2Count) ? 1 : ((r1Count == r2Count) ? 0 : -1);
            };

            while ((bytesRef = termsEnum.next()) != null) {
                String userID = bytesRef.utf8ToString();
                UserRank userRank = new UserRank();
                userRank.setUserID(userID);
                userRank.setAnwseredCount(getTermFreq(Indexer.INDEX_FILED_USER_ID, userID));
                rankingList.add(userRank);
                count++;
                // sort
                if (count == maxUserCount+sortStep) {
                    count -= sortStep;
                    rankingList.sort(comparator);
                    for (int i = 0; i < sortStep; i++) {
                        rankingList.remove(maxUserCount);
                    }
                }
            }

            // sort
            rankingList.sort(comparator);

            // search for username
            rankingList.forEach(userRank -> {
                try {
                    List<UserRank> searchResults = search(userRank.getUserID(), 1000, Indexer.INDEX_FILED_USER_ID);
                    for(UserRank r: searchResults) {
                        String username = r.getUsername();
                        if (!StringUtils.isEmpty(username)) {
                            userRank.setUsername(username);
                            break;
                        }
                    }
                } catch (Exception e) {
                    logger.error("Exception: {}", e);
                }
            });
        }

        if (userCount > maxUserCount) {
            return null;
        }

        return rankingList.subList(0, userCount);
    }

    @Override
    public UserRank getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        UserRank userRank = new UserRank();
        userRank.setUserID(document.get(Indexer.INDEX_FILED_USER_ID));
        userRank.setUsername(document.get(Indexer.INDEX_FILED_USER_NAME));
        return userRank;
    }
}

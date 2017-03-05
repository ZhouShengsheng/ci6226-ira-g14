package ci6226.ira.g14.app.answering.user.ranking.core.searcher;


import ci6226.ira.g14.app.answering.user.ranking.core.indexer.Indexer;
import ci6226.ira.g14.app.answering.user.ranking.model.UserRank;
import ci6226.ira.g14.common.core.searcher.BaseSearcher;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * Post searcher.
 * 
 * @author Zhou Shengsheng
 *
 */
@Component
@Lazy
@ConfigurationProperties(prefix = "lucene")
public class Searcher extends BaseSearcher<UserRank> {

    private static final Logger logger = LoggerFactory.getLogger(Searcher.class);

    @Override
    public void preProcess() {
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
        List<UserRank> userRanks = new ArrayList<>(userCount+sortStep);
        // get all terms of a filed
        Fields fileds = MultiFields.getFields(indexReader);
        TermsEnum termsEnum = fileds.terms(Indexer.INDEX_FILED_USER_ID).iterator();
        BytesRef bytesRef;
        int count = 0;

        // sort comparator
        Comparator<? super UserRank> comparator = (r1, r2) -> r1.getAnwseredCount() >= r2.getAnwseredCount() ? -1 : 1;

        while ((bytesRef = termsEnum.next()) != null) {
            String userID = bytesRef.utf8ToString();
            UserRank userRank = new UserRank();
            userRank.setUserID(userID);
            userRank.setAnwseredCount(getTermFreq(Indexer.INDEX_FILED_USER_ID, userID));
            userRanks.add(userRank);
            count++;
            // sort
            if (count == userCount+sortStep) {
                count -= sortStep;
                userRanks.sort(comparator);
                for (int i = 0; i < sortStep; i++) {
                    userRanks.remove(userCount);
                }
            }
        }

        // sort
        userRanks.sort(comparator);

        // search for username
        userRanks.forEach(userRank -> {
            try {
                List<UserRank> searchResults = search(userRank.getUserID(), 10000, Indexer.INDEX_FILED_USER_ID);
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
        return userRanks;
    }

    @Override
    public UserRank getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        UserRank userRank = new UserRank();
        userRank.setUserID(document.get(Indexer.INDEX_FILED_USER_ID));
        userRank.setUsername(document.get(Indexer.INDEX_FILED_USER_NAME));
        return userRank;
    }
}

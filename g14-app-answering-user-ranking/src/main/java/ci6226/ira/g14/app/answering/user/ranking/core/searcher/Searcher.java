package ci6226.ira.g14.app.answering.user.ranking.core.searcher;


import ci6226.ira.g14.app.answering.user.ranking.core.indexer.Indexer;
import ci6226.ira.g14.app.answering.user.ranking.model.UserRank;
import ci6226.ira.g14.common.core.searcher.AbstractSearcher;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.*;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.util.BytesRef;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

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
@ConfigurationProperties(prefix = "indexer")
public class Searcher extends AbstractSearcher<UserRank> {

    private static final Logger logger = LoggerFactory.getLogger(Searcher.class);

    @Getter @Setter private int userCount;

    private List<UserRank> userRanks;

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
    public List<UserRank> getAnsweringUserRank() throws IOException {
        if (userRanks == null) {
            synchronized (this) {
                if (userRanks == null) {
                    int sortStep = 10;
                    userRanks = new ArrayList<>(userCount+sortStep);
                    // get all terms of a filed
                    Fields fileds = MultiFields.getFields(indexReader);
                    TermsEnum termsEnum = fileds.terms(Indexer.INDEX_FILED_USER_ID).iterator();
                    BytesRef bytesRef;
                    int count = 0;
                    while ((bytesRef = termsEnum.next()) != null) {
                        String userID = bytesRef.utf8ToString();
                        UserRank userRank = new UserRank();
                        userRank.setUserID(userID);
                        userRank.setAnwseredCount(getTermFreq(Indexer.INDEX_FILED_USER_ID, userID));
                        count++;
                        // sort
                        if (count == userCount+sortStep) {
                            userRanks.sort((r1, r2) -> r1.getAnwseredCount() > r2.getAnwseredCount() ? 1 : 0);
                            for (int i = 0; i < sortStep; i++) {
                                userRanks.remove(userCount);
                            }
                        }
                    }
                    // search for username
                    userRanks.forEach(userRank -> {
                        try {
                            List<UserRank> searchResults = search(userRank.getUserID(), 1, Indexer.INDEX_FILED_USER_ID);
                            userRank.setUsername(searchResults.get(0).getUsername());
                        } catch (Exception e) {
                            logger.error("Exception: {}", e);
                        }
                    });
                }
            }
        }
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

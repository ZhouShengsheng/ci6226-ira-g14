package ci6226.ira.g14.app.language.trend.core.searcher;


import ci6226.ira.g14.app.language.trend.model.LanguageRank;
import ci6226.ira.g14.common.core.searcher.AbstractSearcher;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Post searcher.
 * 
 * @author Zhou Shengsheng
 *
 */
@Component
@Lazy
@ConfigurationProperties(prefix = "indexer")
@Getter
@Setter
public class Searcher extends AbstractSearcher<LanguageRank> {

    private String rankLanguages;
    private Map<String, Object> rankResult;

    @Override
    public void preProcess() {
    }

    @Override
    public void preDestroy() {
    }

    /**
     * Get frequency of term.
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
    public Map<String, Object> getLanguageRank() {
        synchronized (this) {
            if (rankResult == null) {
                rankResult = new HashMap<>();
            }
        }
        return rankResult;
    }

    @Override
    public LanguageRank getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        return null;
    }
}

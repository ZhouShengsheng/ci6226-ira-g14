package ci6226.ira.g14.app.language.trend.core.searcher;


import ci6226.ira.g14.app.language.trend.core.indexer.Indexer;
import ci6226.ira.g14.app.language.trend.model.LanguageRank;
import ci6226.ira.g14.common.core.searcher.AbstractSearcher;
import ci6226.ira.g14.common.core.searcher.SearcherConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

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

    private String indexPath;
    private String rankLanguages;
    private int startYear;
    private int endYear;
    private int years;

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
    public long getTermFreq(IndexReader indexReader, String field, String term) throws IOException {
        return indexReader.totalTermFreq(new Term(field, term));
    }

    /**
     * Compute language rank.
     * @return
     */
    public Map<String, Object> getLanguageRank() throws IOException {
        if (rankResult == null) {
            synchronized (this) {
                if (rankResult == null) {
                    rankResult = new HashMap<>();
                    List<String> languageList = Arrays.stream(rankLanguages.replace(" ", "").toLowerCase().split(",")).collect(Collectors.toList());
                    // computer language rank of each year
                    for(int year = startYear; year <= endYear; year++) {
                        IndexReader indexReader = SearcherConfig.newIndexReader(String.format("%s/%d", indexPath, year));
                        List<LanguageRank> ranks = new ArrayList<>();
                        for (String language: languageList) {
                            long popularity = getTermFreq(indexReader, Indexer.INDEX_FILED_TITLE, language)
                                    + getTermFreq(indexReader, Indexer.INDEX_FILED_BODY, language);
                            LanguageRank rank = new LanguageRank();
                            rank.setName(language);
                            rank.setPopularity(popularity);
                            ranks.add(rank);
                        }
                        indexReader.close();
                        rankResult.put(String.valueOf(year), ranks);
                    }
                }
            }
        }
        return rankResult;
    }

    @Override
    public LanguageRank getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        return null;
    }
}

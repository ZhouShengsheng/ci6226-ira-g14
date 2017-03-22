package ci6226.ira.g14.app.language.trend.core.searcher;


import ci6226.ira.g14.app.language.trend.core.indexer.LanguageIndexer;
import ci6226.ira.g14.app.language.trend.model.LanguageRank;
import ci6226.ira.g14.common.core.searcher.BaseSearcher;
import ci6226.ira.g14.common.core.searcher.SearcherConfig;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.ScoreDoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.*;
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
@ConfigurationProperties(prefix = "lucene")
@Getter
@Setter
public class LanguageSearcher extends BaseSearcher<LanguageRank> {

    private static final Logger logger = LoggerFactory.getLogger(LanguageSearcher.class);

    private String indexPath;
    private String rankLanguages;   // all ranked languages
    private int startYear;          // minimal start year
    private int endYear;            // max end year

    // language trend file
    private String trendFile;

    // cached language trend list
    private Map<Integer, List<LanguageRank>> trendResult;

    @SuppressWarnings("unchecked")
    @Override
    public void preProcess() {
        try {
            File file = new File(trendFile);
            if (file.exists()) {
                // load ranking list from file
                logger.info("loading language trend result from file");
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                trendResult = (Map<Integer, List<LanguageRank>>)ois.readObject();
                ois.close();
            } else {
                // get language trend result and save it to local file
                logger.info("saving language trend result to file");
                getLanguageTrend(rankLanguages, startYear, endYear);
                file.getParentFile().mkdirs();
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
                oos.writeObject(trendResult);
                oos.close();
            }
        } catch (Exception e) {
            logger.error("Exception: {}", e);
        }
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
    private long getTermFreq(IndexReader indexReader, String field, String term) throws IOException {
        return indexReader.totalTermFreq(new Term(field, term));
    }

    /**
     * Get language trend.
     * @param rankLanguages
     * @param startYear
     * @param endYear
     * @return
     * @throws IOException
     */
    public Map<Integer, List<LanguageRank>> getLanguageTrend(String rankLanguages, int startYear, int endYear) throws Exception {
        // get all language rankings
        if (this.trendResult == null) {
            this.trendResult = new HashMap<>();
            List<String> languageList = Arrays.stream(this.rankLanguages.replace(" ", "").toLowerCase().split(",")).collect(Collectors.toList());
            // computer language rank of each year
            for(int year = this.startYear; year <= this.endYear; year++) {
                IndexReader indexReader = SearcherConfig.newIndexReader(String.format("%s/%d", indexPath, year));
                List<LanguageRank> ranks = new ArrayList<>();
                for (String language: languageList) {
                    long popularity = getTermFreq(indexReader, LanguageIndexer.INDEX_FILED_TITLE, language)
                            + getTermFreq(indexReader, LanguageIndexer.INDEX_FILED_BODY, language);
                    LanguageRank rank = new LanguageRank();
                    rank.setName(language);
                    rank.setPopularity(popularity);
                    ranks.add(rank);
                }
                indexReader.close();
                this.trendResult.put(year, ranks);
            }
        }

        // check params
        if (startYear < this.startYear || endYear > this.endYear) {
            throw new Exception("parameter checking failed");
        }

        // get queried language rankings
        Map<Integer, List<LanguageRank>> trendResult = new HashMap<>();
        for(int year = startYear; year <= endYear; year++) {
            List<LanguageRank> ranksOfYear = this.trendResult.get(year);
            List<LanguageRank> queriedRanks = ranksOfYear.stream().filter(rank -> rankLanguages.contains(rank.getName())).collect(Collectors.toList());
            trendResult.put(year, queriedRanks);
        }

        return trendResult;
    }

    @Override
    public LanguageRank getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        return null;
    }
}

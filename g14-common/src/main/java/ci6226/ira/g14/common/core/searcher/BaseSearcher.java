package ci6226.ira.g14.common.core.searcher;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Abstract post searcher.
 * 
 * @author Zhou Shengsheng
 *
 */

@ConfigurationProperties(prefix = "lucene")
public abstract class BaseSearcher<T> {

    private static final Logger logger = LoggerFactory.getLogger(BaseSearcher.class);
	
	// search fields
	public static final String SEARCH_FIELD_ALL = "all";
	public static final String SEARCH_FIELD_TITLE = "title";
	public static final String SEARCH_FIELD_BODY = "body";

	private static final String specialCharacters = "+,-,&,|,!,(,),{,},[,],^,\",~,*,?,:";
    private Set<String> specialCharacterSet;
	
	@Autowired
	protected IndexReader indexReader;

	@Autowired
	private IndexSearcher indexSearcher;

	@PostConstruct
    private void init() {
        specialCharacterSet = Arrays.stream(specialCharacters.replace(" ", "").split(",")).collect(Collectors.toSet());
	    preProcess();
    }

	@PreDestroy
	private void destroy() throws IOException {
        preDestroy();
		indexReader.close();
	}

	/**
	 * Search for scored documents.
	 * 
	 * @param keywords
	 * @param topNum
	 * @param fields
	 * @return
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public List<T> search(String keywords, int topNum, String... fields) throws IOException, ParseException {
		StandardAnalyzer analyzer = new StandardAnalyzer();
		MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
		Query query = parser.parse(escapeSpecialCharacters(keywords));
		TopScoreDocCollector collector = TopScoreDocCollector.create(topNum);
		indexSearcher.search(query, collector);
        ScoreDoc[] docs = collector.topDocs().scoreDocs;
        List<T> results = new ArrayList<>(topNum);
        for (ScoreDoc doc: docs) {
            Document document = indexReader.document(doc.doc);
            T result = getResultFromDocument(document, doc);
            if (result != null) {
                results.add(result);
            }
        }
        return results;
	}

    /**
     * Any other processing before indexing.
     */
    public abstract void preProcess();

    /**
     * Any other processing before destroying.
     */
    public abstract void preDestroy();

    /**
     * Convert lucene document to result.
     *
     * @param document
     * @return
     */
    public abstract T getResultFromDocument(Document document, ScoreDoc doc);

    /**
     * Add \ to every special character.
     * @param keywords
     * @return
     */
    public String escapeSpecialCharacters(String keywords) {
        for (String character: specialCharacterSet) {
            keywords = keywords.replace(character, "\\" + character);
        }
        logger.info("keywords: {}", keywords);
        return keywords;
    }

}

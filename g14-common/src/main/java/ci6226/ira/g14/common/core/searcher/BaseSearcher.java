package ci6226.ira.g14.common.core.searcher;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract post searcher.
 * 
 * @author Zhou Shengsheng
 *
 */

public abstract class BaseSearcher<T> {
	
	// search fields
	public static final String SEARCH_FIELD_ALL = "all";
	public static final String SEARCH_FIELD_TITLE = "title";
	public static final String SEARCH_FIELD_BODY = "body";
	
	@Autowired
	protected IndexReader indexReader;

	@Autowired
	private IndexSearcher indexSearcher;

	@PostConstruct
    private void init() {
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
		Query query = parser.parse(keywords);
		TopScoreDocCollector collector = TopScoreDocCollector.create(topNum);
		indexSearcher.search(query, collector);
        ScoreDoc[] docs = collector.topDocs().scoreDocs;
        List<T> results = new ArrayList<T>(topNum);
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

}

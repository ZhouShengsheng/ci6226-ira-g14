package ci6226.ira.g14.search.core.searcher;

import ci6226.ira.g14.common.core.searcher.BaseSearcher;
import ci6226.ira.g14.search.core.indexer.Indexer;
import ci6226.ira.g14.search.model.Result;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Post searcher.
 * 
 * @author Zhou Shengsheng
 *
 */
@Component
@Lazy
public class Searcher extends BaseSearcher<Result> {

    @Override
    public void preProcess() {
    }

    @Override
    public void preDestroy() {
    }

    @Override
    public Result getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        Result result = new Result();
        result.setTitle(document.get(Indexer.INDEX_FILED_TITLE));
        result.setBody(document.get(Indexer.INDEX_FILED_BODY));
        result.setScore(scoreDoc.score);
        return result;
    }
}

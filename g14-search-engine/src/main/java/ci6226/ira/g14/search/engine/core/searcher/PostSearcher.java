package ci6226.ira.g14.search.engine.core.searcher;

import ci6226.ira.g14.common.core.searcher.BaseSearcher;
import ci6226.ira.g14.search.engine.core.indexer.PostIndexer;
import ci6226.ira.g14.search.engine.model.Result;

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
public class PostSearcher extends BaseSearcher<Result> {

    @Override
    public void preProcess() {
    }

    @Override
    public void preDestroy() {
    }

    @Override
    public Result getResultFromDocument(Document document, ScoreDoc scoreDoc) {
        Result result = new Result();
        result.setTitle(document.get(PostIndexer.INDEX_FILED_TITLE));
        result.setBody(document.get(PostIndexer.INDEX_FILED_BODY));
        result.setDocId(scoreDoc.doc);
        result.setScore(scoreDoc.score);
        return result;
    }

}

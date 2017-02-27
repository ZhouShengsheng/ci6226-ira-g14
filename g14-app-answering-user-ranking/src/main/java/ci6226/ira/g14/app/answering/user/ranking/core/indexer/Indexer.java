package ci6226.ira.g14.app.answering.user.ranking.core.indexer;

import ci6226.ira.g14.common.core.indexer.BaseIndexer;
import ci6226.ira.g14.common.core.indexer.IndexerCondition;
import ci6226.ira.g14.common.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Post indexer.
 *
 * @author Zhou Shengsheng
 */
@Component
@Conditional(IndexerCondition.class)
@ConfigurationProperties(prefix = "indexer")
@Getter
@Setter
public class Indexer extends BaseIndexer {

    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

    // index fileds
    public static final String INDEX_FILED_USER_ID = "userId";
    public static final String INDEX_FILED_USER_NAME = "userName";

    @Override
    public void preProcess() {
    }

    @Override
    public void preDestroy() {
    }

    @Override
    public boolean willIndexPost(Post post) {
        // index answers only
        if (StringUtils.isEmpty(post.getPostTypeId())) {
            return false;
        }
        return Integer.valueOf(post.getPostTypeId()) == Post.POST_TYPE_ANSWER;
    }

    @Override
    public Document getDocumentFromPost(Post post) {
        Document document = new Document();
        if (!StringUtils.isEmpty(post.getOwnerUserId())) {
            document.add(new TextField(Indexer.INDEX_FILED_USER_ID, post.getOwnerUserId(), Field.Store.YES));
        }
        if (!StringUtils.isEmpty(post.getOwnerDisplayName())) {
            document.add(new TextField(Indexer.INDEX_FILED_USER_NAME, post.getOwnerDisplayName(), Field.Store.YES));
        }
        return document;
    }

}

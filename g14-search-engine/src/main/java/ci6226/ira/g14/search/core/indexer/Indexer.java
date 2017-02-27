package ci6226.ira.g14.search.core.indexer;

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

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Post indexer.
 *
 * @author Zhou Shengsheng
 */
@Component
@Conditional(IndexerCondition.class)
@ConfigurationProperties(prefix = "indexer")
public class Indexer extends BaseIndexer {

    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

    // index fileds
    public static final String INDEX_FILED_TITLE = "title";
    public static final String INDEX_FILED_BODY = "body";

    // post tags to be indexed
    @Getter @Setter private String indexTags;
    private List<String> indexTagListWithBorder;
    private List<String> indexTagListRegexPaterns;

    @Override
    public void preProcess() {
        // convert indexTags string into a list of tags
        List<String> indexTagList = Arrays.stream(indexTags.replace(" ", "").toLowerCase().split(",")).collect(Collectors.toList());
        indexTagListWithBorder = indexTagList.stream().map(tag -> String.format("<%s>", tag)).collect(Collectors.toList()); // eg: <java>
        indexTagListRegexPaterns = indexTagList.stream().map(tag -> String.format(".*\\b%s\\b.*", tag)).collect(Collectors.toList()); // eg: .*\bjava\b.*
        logger.info("Will index documents with tags: {}", indexTags);
    }

    @Override
    public void preDestroy() {
    }

    @Override
    public boolean willIndexPost(Post post) {
        // get tags, title and body of the post
        String tags = post.getTags();
        if (tags == null) {
            tags = "";
        }
        String title = post.getTitle();
        if (title == null) {
            title = "";
        }
        String body = post.getBody();
        if (body == null) {
            body = "";
        }

        boolean willIndex = false;

        // check if tags, title or body contain tags in indexTagList
        int size = indexTagListWithBorder.size();
        for (int i = 0; i < size; i++) {
            String tagWithBorder = indexTagListWithBorder.get(i);
            String tagRegexPattern = indexTagListRegexPaterns.get(i);
            if (tags.contains(tagWithBorder) || title.matches(tagRegexPattern) || body.matches(tagRegexPattern)) {
                willIndex = true;
                break;
            }
        }

        return willIndex;
    }

    @Override
    public Document getDocumentFromPost(Post post) {
        Document document = new Document();
        if (!StringUtils.isEmpty(post.getTitle())) {
            document.add(new TextField(Indexer.INDEX_FILED_TITLE, post.getTitle(), Field.Store.YES));
        }
        if (!StringUtils.isEmpty(post.getBody())) {
            document.add(new TextField(Indexer.INDEX_FILED_BODY, post.getBody(), Field.Store.YES));
        }
        return document;
    }

}

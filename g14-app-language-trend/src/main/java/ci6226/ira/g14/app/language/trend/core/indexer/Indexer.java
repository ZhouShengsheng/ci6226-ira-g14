package ci6226.ira.g14.app.language.trend.core.indexer;

import ci6226.ira.g14.common.core.indexer.BaseIndexer;
import ci6226.ira.g14.common.core.indexer.IndexerCondition;
import ci6226.ira.g14.common.core.indexer.IndexerConfig;
import ci6226.ira.g14.common.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
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
@ConfigurationProperties(prefix = "lucene")
@Getter
@Setter
public class Indexer extends BaseIndexer {

    private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

    // index fileds
    public static final String INDEX_FILED_TITLE = "title";
    public static final String INDEX_FILED_BODY = "body";

    private int startYear;
    private int endYear;
    private int years;
    private String indexPath;

    // separated index writers for each year
    private List<IndexWriter> indexWriters;

    // tags
    @Getter @Setter private String rankLanguages;
    private List<String> rankLanguageListWithBorder;

    @Override
    public void preProcess() {
        // convert rankLanguages string into a list of tags
        List<String> indexTagList = Arrays.stream(rankLanguages.replace(" ", "").toLowerCase().split(",")).collect(Collectors.toList());
        rankLanguageListWithBorder = indexTagList.stream().map(tag -> String.format("<%s>", tag)).collect(Collectors.toList()); // eg: <java>

        // created index writers
        try {
            years = endYear - startYear + 1;
            if (years <= 0) {
                logger.error("endYear must >= startYear");
                System.exit(-1);
            }
            indexWriters = new ArrayList<>(years);
            for (int i = 0; i < years; i++) {
                int year = startYear + i;
                IndexWriter indexWriter = IndexerConfig.newIndexWriter(String.format("%s/%d", indexPath, year)); // path/2008 path/2009 ...
                indexWriters.add(indexWriter);
            }
        } catch (IOException e) {
            logger.error("IOException: {}", e);
            System.exit(-1);
        }
    }

    @Override
    public void preDestroy() {
        // close all index writers
        indexWriters.forEach(indexWriter -> {
            try {
                indexWriter.commit();
                indexWriter.close();
            } catch (IOException e) {
                logger.error("IOException: {}", e);
            }
        });
        indexWriters.clear();
    }

    @Override
    protected IndexWriter getIndexWriter(Post post) {
        String creationDate = post.getCreationDate();
        if (StringUtils.isEmpty(creationDate)) {
            return null;
        }
        String year = post.getCreationDate().substring(0, 4);
        for (int i = 0; i < years; i++) {
            if (startYear +i == Integer.valueOf(year)) {
                return indexWriters.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean willIndexPost(Post post) {
        String tags = post.getTags();
        if (tags == null) {
            return false;
        }

        for (String tagWithBorder: rankLanguageListWithBorder) {
            if (tags.contains(tagWithBorder)) {
                return true;
            }
        }

        return false;
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

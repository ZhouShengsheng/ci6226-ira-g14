package ci6226.ira.g14.app.language.trend.core.indexer;

import ci6226.ira.g14.common.core.indexer.AbstractIndexer;
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
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * Post indexer.
 *
 * @author Zhou Shengsheng
 */
@Component
@ConfigurationProperties(prefix = "indexer")
@Getter
@Setter
public class Indexer extends AbstractIndexer {

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

    @Override
    public void preProcess() {
        // created index writers
        try {
            years = endYear - startYear;
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
        // index every post
        return true;
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

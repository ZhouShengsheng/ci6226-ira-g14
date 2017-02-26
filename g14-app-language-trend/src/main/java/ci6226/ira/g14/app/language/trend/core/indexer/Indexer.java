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

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
    private String indexPath;

    // post tags to be indexed
    private String indexTags;
    private List<String> indexTagList;

    @Override
    public void preProcess() {
        // convert indexTags string into a list of tags
        indexTagList = Arrays.stream(indexTags.toLowerCase().split(",")).map(tag -> String.format("<%s>", tag)).collect(Collectors.toList());
        logger.info("Will index documents with tags: {}", indexTags);
    }

    @Override
    public void preDestroy() {
    }

    /**
     * Read documents index each year.
     *
     * @throws IOException
     */
    protected void process() throws IOException {
        int years = endYear - startYear;
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Future<Boolean>> futures = new ArrayList<>(years);
        for (int i = 0; i < years; i++) {
            int year = startYear + i;
            Future<Boolean> future = executorService.submit(() -> {
                logger.info("Starting indexing documents in year {}", year);
                BufferedReader dataReader = IndexerConfig.newDataReader();
                IndexWriter indexWriter = IndexerConfig.newIndexWriter(String.format("%s/%d", indexPath, year)); // path/2008 path/2009 ...
                index(dataReader, indexWriter);
                dataReader.close();
                indexWriter.close();
                logger.info("Finished indexing documents in year {}", year);
                logger.info("Total documents in year {} indexed: {}", year, indexWriter.maxDoc());
                return true;
            });
            futures.add(future);
        }
        // wait until all of the threads are done
        futures.forEach(future -> {
            try {
                future.get();
            } catch (Exception e) {
                logger.error("Exception: {}", e);
            }
        });

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

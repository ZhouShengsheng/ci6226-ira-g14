package ci6226.ira.g14.common.core.indexer;

import ci6226.ira.g14.common.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Abstract post indexer.
 *
 * @author Zhou Shengsheng
 */
@ConfigurationProperties(prefix = "indexer")
public abstract class AbstractIndexer {

    private static final Logger logger = LoggerFactory.getLogger(AbstractIndexer.class);

    // whether to perform indexing or not
    @Getter @Setter private boolean willIndex;

    // whether the indexing process is done
    protected AtomicBoolean indexed;

    @Autowired
    protected IndexWriter indexWriter;

    @Autowired
    protected BufferedReader dataReader;

    @Autowired
    protected Unmarshaller postUnmarshaller;

    /**
     * Initialization method, if the willIndex property is set to true, a thread will be instantiated to index the documents.
     */
    @PostConstruct
    private void init() {
        indexed = new AtomicBoolean(false);
        if (willIndex) {
            // index in a separated thread
            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.submit(() -> {
                try {
                    preProcess();
                    process();
                    destroy();
                    indexed.set(true);
                    executorService.shutdown();
                } catch (IOException e) {
                    logger.error("IOException: {}", e);
                }
            });
        } else {
            indexed.set(true);
        }
    }

    /**
     * Destruction method.
     * @throws IOException
     */
    private void destroy() throws IOException {
        preDestroy();
        dataReader.close();
        indexWriter.close();
    }

    /**
     * Get if the the indexing has finished.
     * @return
     */
    public boolean getIndexed() {
        return indexed.get();
    }

    /**
     * Read documents and index them.
     *
     * @throws IOException
     */
    protected void process() throws IOException {
        logger.info("Starting indexing");
        index(dataReader, indexWriter);
        logger.info("Finished indexing");
        logger.info("Total documents indexed: {}", indexWriter.maxDoc());
    }

    protected void index(BufferedReader dataReader, IndexWriter indexWriter) throws IOException {
        String line;
        while ((line = dataReader.readLine()) != null) {
            try {
                // convert xml string to object
                Post post = (Post) postUnmarshaller.unmarshal(new StringReader(line));
                if (willIndexPost(post)) {
                    // index the post
                    try {
                        logger.info("Indexing document with id: {}", post.getId());
                        indexWriter.addDocument(getDocumentFromPost(post));
                    } catch (IOException e) {
                        logger.error("IOException: {}", e);
                    }
                }
            } catch (JAXBException e) {
                logger.error("JAXBException: {}", e);
            }
        }
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
     * Check if the post will be indexed.
     *
     * @param post
     * @return
     */
    public abstract boolean willIndexPost(Post post);

    /**
     * Convert post to lucene document.
     *
     * @param post
     */
    public abstract Document getDocumentFromPost(Post post);

}

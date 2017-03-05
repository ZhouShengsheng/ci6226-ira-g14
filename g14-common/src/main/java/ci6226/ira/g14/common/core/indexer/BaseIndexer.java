package ci6226.ira.g14.common.core.indexer;

import ci6226.ira.g14.common.model.Post;
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
@ConfigurationProperties(prefix = "lucene")
public abstract class BaseIndexer {

    private static final Logger logger = LoggerFactory.getLogger(BaseIndexer.class);

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
        logger.info("initializing indexer");
        indexed = new AtomicBoolean(false);
        // index in a separated thread
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            try {
                preProcess();
                process();
            } catch (IOException e) {
                logger.error("IOException: {}", e);
            } finally {
                try {
                    destroy();
                } catch (IOException e) {
                    logger.error("IOException: {}", e);
                }
                indexed.set(true);
                executorService.shutdown();
            }
        });
    }

    /**
     * Destruction method.
     * @throws IOException
     */
    private void destroy() throws IOException {
        preDestroy();
        dataReader.close();
        indexWriter.commit();
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
     * @throws IOException
     */
    protected void process() throws IOException {
        logger.info("Starting indexing");
        String line;
        long docNum = 0;
        while ((line = dataReader.readLine()) != null) {
            try {
                // convert xml string to object
                Post post = (Post) postUnmarshaller.unmarshal(new StringReader(line));
                if (willIndexPost(post)) {
                    // index the post
                    try {
                        IndexWriter indexWriter = getIndexWriter(post);
                        if (indexWriter != null) {
                            logger.info("Indexing document with id: {}", post.getId());
                            indexWriter.addDocument(getDocumentFromPost(post));
                            docNum++;
                        }
                    } catch (IOException e) {
                        logger.error("IOException: {}", e);
                    }
                }
            } catch (JAXBException e) {
                logger.error("JAXBException: {}", e);
            }
        }

        logger.info("Finished indexing");
        logger.info("Total documents indexed: {}", docNum);
    }

    /**
     * Get index writer for specific post and parameters.
     * @param post
     * @return
     */
    protected IndexWriter getIndexWriter(Post post) {
        return indexWriter;
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

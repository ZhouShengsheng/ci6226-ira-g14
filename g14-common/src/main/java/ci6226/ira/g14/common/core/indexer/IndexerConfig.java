package ci6226.ira.g14.common.core.indexer;

import ci6226.ira.g14.common.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Indexer configuration.
 * 
 * @author Zhou Shengsheng
 *
 */
@Configuration
@ConfigurationProperties(prefix = "indexer")
@Getter
@Setter
public class IndexerConfig {
	
	private String indexPath;
	private String dataFile;
	private String stopWords;

    private static String dataFileStatic;
    private static String stopWordsStatic;

    @PostConstruct
    private void init() {
        dataFileStatic = dataFile;
        stopWordsStatic = stopWords;
    }

    public static IndexWriter newIndexWriter(String indexPath) throws IOException {
		File path = new File(indexPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		Directory dir = FSDirectory.open(Paths.get(indexPath));
		CharArraySet set = new CharArraySet(Arrays.stream(stopWordsStatic.replace(" ", "").split(",")).collect(Collectors.toSet()), true);
		Analyzer analyzer = new EnglishAnalyzer(set);
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		cfg.setOpenMode(OpenMode.CREATE);
		return new IndexWriter(dir, cfg);
	}

	public static BufferedReader newDataReader() throws FileNotFoundException {
        FileReader reader = new FileReader(dataFileStatic);
        BufferedReader br = new BufferedReader(reader);
        return br;
    }
	
	/**
	 * Index writer.
	 * 
	 * @return
	 * @throws IOException
	 */
	@Bean
	public IndexWriter indexWriter() throws IOException {
		return IndexerConfig.newIndexWriter(indexPath);
	}

	/**
	 * XML parser for Post.
	 * 
	 * @return
	 * @throws JAXBException
	 */
	@Bean
	public Unmarshaller postUnmarshaller() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Post.class);  
        return jaxbContext.createUnmarshaller();
	}
	
	@Bean
	@Scope(BeanDefinition.SCOPE_PROTOTYPE)
	public BufferedReader dataReader() throws FileNotFoundException {
		return IndexerConfig.newDataReader();
	}
	
}

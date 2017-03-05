package ci6226.ira.g14.common.core.indexer;

import ci6226.ira.g14.common.model.Post;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.*;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.core.*;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.validation.constraints.AssertFalse;
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
@ConfigurationProperties(prefix = "lucene")
@Getter
@Setter
@Conditional(IndexerCondition.class)
public class IndexerConfig {

	private String indexPath;
	private String dataFile;

    public static IndexWriter newIndexWriter(String indexPath) throws IOException {
		File path = new File(indexPath);
		if (!path.exists()) {
			path.mkdirs();
		}
		Directory dir = FSDirectory.open(Paths.get(indexPath));
        Analyzer analyzer = new WhitespaceAnalyzer();
		IndexWriterConfig cfg = new IndexWriterConfig(analyzer);
		cfg.setOpenMode(OpenMode.CREATE);
		return new IndexWriter(dir, cfg);
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
        FileReader reader = new FileReader(dataFile);
        BufferedReader br = new BufferedReader(reader);
        return br;
	}
	
}

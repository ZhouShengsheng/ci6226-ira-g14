package ci6226.ira.g14.common.core.searcher;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.NIOFSDirectory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * Searcher configuration.
 * 
 * @author Zhou Shengsheng
 *
 */
@Configuration
@ConfigurationProperties(prefix = "indexer")
@Getter
@Setter
public class SearcherConfig {

	private String indexPath;
	
	@Bean
	@Lazy
	IndexReader indexReader() throws IOException {
        return DirectoryReader.open(NIOFSDirectory.open(Paths.get(indexPath)));
	}

	@Bean
	@Lazy
	IndexSearcher indexSearcher() throws IOException {
		return new IndexSearcher(indexReader());
	}

}

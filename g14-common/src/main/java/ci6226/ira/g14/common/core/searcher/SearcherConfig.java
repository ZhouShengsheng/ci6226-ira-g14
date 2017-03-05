package ci6226.ira.g14.common.core.searcher;

import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.IOContext;
import org.apache.lucene.store.MMapDirectory;
import org.apache.lucene.store.RAMDirectory;
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
@ConfigurationProperties(prefix = "lucene")
@Getter
@Setter
public class SearcherConfig {

	private String indexPath;

	public static IndexReader newIndexReader(String indexPath) throws IOException {
//        return DirectoryReader.open(new RAMDirectory(FSDirectory.open(Paths.get(indexPath)), IOContext.DEFAULT));
        return DirectoryReader.open(MMapDirectory.open(Paths.get(indexPath)));
    }

	@Bean
	@Lazy
	IndexReader indexReader() throws IOException {
        return SearcherConfig.newIndexReader(indexPath);
	}

	@Bean
	@Lazy
	IndexSearcher indexSearcher() throws IOException {
		return new IndexSearcher(indexReader());
	}

}

package ci6226.ira.g14.search.engine;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class of the search engine.
 * 
 * @author Zhou Shengsheng
 */
@ServletComponentScan
@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "ci6226.ira.g14")
public class Main {
	
    public static void main(String args[]) {
        SpringApplication.run(Main.class);
    }
    
}

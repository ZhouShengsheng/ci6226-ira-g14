package ci6226.ira.g14.fe;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Main class of the language trend application.
 * 
 * @author Zhou Shengsheng
 */
@ServletComponentScan
@Configuration
@SpringBootApplication
@ComponentScan
public class Main {
	
    public static void main(String args[]) {
        SpringApplication.run(Main.class);
    }
    
}

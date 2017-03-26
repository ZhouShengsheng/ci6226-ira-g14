package ci6226.ira.g14.fe.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Spring application context configuration.
 * 
 * @author Jinjin
 *
 */
//@Configuration
public class SpringConfig {
	
	@Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
    }
	
}

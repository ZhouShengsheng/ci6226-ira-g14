package ci6226.ira.g14.app.language.trend.controller;


import ci6226.ira.g14.app.language.trend.core.searcher.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * API RESTful controller.
 */
@RestController
@RequestMapping(value = "api")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private Searcher searcher;

	@GetMapping(value = "/language_trend")
	public Map<String, Object> getLanguageTrend() throws IOException {
		return searcher.getLanguageRank();
	}
	
}

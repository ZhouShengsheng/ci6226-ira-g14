package ci6226.ira.g14.app.language.trend.controller;


import ci6226.ira.g14.app.language.trend.core.searcher.Searcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

/**
 * API RESTful controller.
 */
@RestController
@RequestMapping(value = "api")
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private Searcher searcher;

	@GetMapping(value = "/language_trend")
	public Map<String, Object> getLanguageTrend(String rankLanguages, int startYear, int endYear) throws IOException {
		logger.info("getLanguageTrend");
		return searcher.getLanguageRank(rankLanguages, startYear, endYear);
	}
	
}

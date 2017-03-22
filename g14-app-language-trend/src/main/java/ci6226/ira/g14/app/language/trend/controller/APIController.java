package ci6226.ira.g14.app.language.trend.controller;


import ci6226.ira.g14.app.language.trend.core.searcher.LanguageSearcher;
import ci6226.ira.g14.app.language.trend.model.LanguageRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private LanguageSearcher languageSearcher;

	@GetMapping(value = "/language_trend")
	public Map<Integer, List<LanguageRank>> getLanguageTrend(String rankLanguages, int startYear, int endYear) throws Exception {
		logger.info("getLanguageTrend");
		return languageSearcher.getLanguageTrend(rankLanguages, startYear, endYear);
	}
	
}

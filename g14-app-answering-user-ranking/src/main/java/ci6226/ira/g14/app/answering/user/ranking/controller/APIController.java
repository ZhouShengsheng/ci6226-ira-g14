package ci6226.ira.g14.app.answering.user.ranking.controller;


import ci6226.ira.g14.app.answering.user.ranking.core.searcher.Searcher;
import ci6226.ira.g14.app.answering.user.ranking.model.UserRank;
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

/**
 * API RESTful controller.
 *
 * @author Zhou Shengsheng
 */
@RestController
@RequestMapping(value = "api")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private Searcher searcher;

	@GetMapping(value = "/user_ranking")
	public List<UserRank> getUserRanking() throws IOException {
	    logger.info("getUserRanking");
		return searcher.getAnsweringUserRank();
	}
	
}

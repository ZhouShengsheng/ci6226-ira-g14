package ci6226.ira.g14.app.answering.user.ranking.controller;


import ci6226.ira.g14.app.answering.user.ranking.core.searcher.UserSearcher;
import ci6226.ira.g14.app.answering.user.ranking.model.UserRank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * API RESTful controller.
 *
 * @author Zhou Shengsheng
 */
@RestController
@RequestMapping(value = "api")
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private UserSearcher userSearcher;

	@GetMapping(value = "/user_ranking")
	public List<UserRank> getUserRanking(@RequestParam int userCount) throws Exception {
	    logger.info("getUserRanking");
	    if (userCount > userSearcher.getMaxUserCount()) {
	        throw new Exception(String.format("%d is larger than max allowed user count %d", userCount, userSearcher.getMaxUserCount()));
        }
		return userSearcher.getAnsweringUserRank(userCount);
	}
	
}

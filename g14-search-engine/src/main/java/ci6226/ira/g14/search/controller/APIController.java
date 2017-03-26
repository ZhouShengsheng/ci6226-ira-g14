package ci6226.ira.g14.search.controller;

import ci6226.ira.g14.search.core.searcher.PostSearcher;
import ci6226.ira.g14.search.model.Result;
import org.apache.lucene.queryparser.classic.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * API RESTful controller.
 */
@RestController
@RequestMapping(value = "api")
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class APIController {
	
	private static final Logger logger = LoggerFactory.getLogger(APIController.class);
	
	@Autowired
	private PostSearcher postSearcher;

	@GetMapping(value = "/search")
	public List<Result> search(@RequestParam String field, @RequestParam String keywords, @RequestParam int count) throws IOException, ParseException {
		logger.info("search: {}, {}, {}", field, keywords, count);
		
		// title
		if (PostSearcher.SEARCH_FIELD_TITLE.equals(field)) {
			return postSearcher.search(keywords, count, PostSearcher.SEARCH_FIELD_TITLE);
		}
		
		// body
		if(PostSearcher.SEARCH_FIELD_BODY.equals(field)) {
			return postSearcher.search(keywords, count, PostSearcher.SEARCH_FIELD_BODY);
		}

		// all
		return postSearcher.search(keywords, count, PostSearcher.SEARCH_FIELD_TITLE, PostSearcher.SEARCH_FIELD_BODY);
	}

}

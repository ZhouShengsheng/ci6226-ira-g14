package ci6226.ira.g14.common.filter;

import ci6226.ira.g14.common.core.indexer.BaseIndexer;
import ci6226.ira.g14.common.core.indexer.IndexerCondition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter to check if the search engine is indexing.
 *
 * @author Zhou Shengsheng
 */
@Component
@Conditional(IndexerCondition.class)
public class ProcessingFilter implements Filter {

	private final static Logger logger = LoggerFactory.getLogger(ProcessingFilter.class);

	@Autowired
	BaseIndexer indexer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init processing filter");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!indexer.getIndexed()) {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println("{\"message\":\"Currently indexing.\"}");
			pw.close();
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}

}

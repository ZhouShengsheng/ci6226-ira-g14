package ci6226.ira.g14.common.filter;

import ci6226.ira.g14.common.core.indexer.AbstractIndexer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Filter to check if the search engine is indexing.
 * Web application may extend this class and annotated with @WebFilter(urlPatterns = "/*") to gain filtering capacity.
 *
 * @author Zhou Shengsheng
 */
public class BaseProcessingFilter implements Filter {

	private final static Logger logger = LoggerFactory.getLogger(BaseProcessingFilter.class);

	@Autowired
    AbstractIndexer indexer;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		if (!indexer.getIndexed()) {
			HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.setHeader("Content-Type", "application/json;charset=UTF-8");
			PrintWriter pw = response.getWriter();
			pw.println("{\"message\":\"Search engine is currently indexing.\"}");
			pw.close();
		} else {
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void destroy() {
	}

}

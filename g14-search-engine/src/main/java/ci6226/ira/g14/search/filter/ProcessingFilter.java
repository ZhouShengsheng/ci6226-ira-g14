package ci6226.ira.g14.search.filter;

import ci6226.ira.g14.common.filter.BaseProcessingFilter;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
public class ProcessingFilter extends BaseProcessingFilter {
}

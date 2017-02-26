package ci6226.ira.g14.app.answering.user.ranking.filter;

import ci6226.ira.g14.common.core.indexer.IndexerCondition;
import ci6226.ira.g14.common.filter.BaseProcessingFilter;
import org.springframework.context.annotation.Conditional;

import javax.servlet.annotation.WebFilter;

@WebFilter(urlPatterns = "/*")
@Conditional(IndexerCondition.class)
public class ProcessingFilter extends BaseProcessingFilter {
}

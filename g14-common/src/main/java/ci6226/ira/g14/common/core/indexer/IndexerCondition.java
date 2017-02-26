package ci6226.ira.g14.common.core.indexer;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.StringUtils;

/**
 * Indexer condition. Whether to index or not.
 *
 * @author Zhou Shengsheng
 */
public class IndexerCondition implements Condition {

    public static final String willIndexKey = "indexer.willIndex";

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String willIndexProperty = context.getEnvironment().getProperty(willIndexKey);
        boolean willIndex;
        if (StringUtils.isEmpty(willIndexProperty)) {
            willIndex = false;
        } else {
            willIndex = Boolean.valueOf(willIndexProperty);
        }
        return willIndex;
    }

}

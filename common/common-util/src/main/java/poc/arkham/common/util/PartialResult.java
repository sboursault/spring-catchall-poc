package poc.arkham.common.util;

import org.apache.commons.lang.math.IntRange;

import java.util.List;
/**
 * {@link PartialResult} serves the same purpose as {@link org.springframework.data.domain.Page}.
 * The difference is that {@link PartialResult} is based on a range and not on a page number.
 */
public class PartialResult<T> {
    private final List<T> content;
    private final IntRange range;
    private final int totalNumberOfResults;

    public PartialResult(List<T> content, IntRange range, int totalNumberOfResults) {
        if (content == null) {
            throw new IllegalArgumentException("content must not be null");
        }
        if (range == null) {
            throw new IllegalArgumentException("range must not be null");
        }
        this.content = content;
        this.range = range;
        this.totalNumberOfResults = totalNumberOfResults;
    }

    public List<T> getContent() {
        return content;
    }

    public int getSize() {
        return content.size();
    }

    public int getTotalNumberOfResults() {
        return totalNumberOfResults;
    }

    public int getOffset() {
        return range.getMinimumInteger();
    }

    public int getLimit() {
        return range.getMinimumInteger() + getSize() -1;
    }
}

package poc.arkham.common.util;

import org.apache.commons.lang.math.IntRange;

import java.util.List;
import java.util.Optional;

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

    public Optional<IntRange> getFirstRange() {
        if (getOffset() == 0 || getTotalNumberOfResults() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(new IntRange(0, getRangeSize() - 1));
        }
    }

    public Optional<IntRange> getLastRange() {
        if (getTotalNumberOfResults() == 0)
            return Optional.empty();
        int rangeSize = getRangeSize();
        int lastIndex = getTotalNumberOfResults() - 1;
        int lastOffsetWithoutShift = lastIndex - rangeSize + getTotalNumberOfResults() % rangeSize;
        int lastOffset = lastOffsetWithoutShift + getOffset() % rangeSize;
        final IntRange lastRange = new IntRange(lastOffset, lastOffset + getRangeSize() - 1);
        if (lastRange.getMinimumInteger() <= getOffset()) {
            return Optional.empty();
        } else {
            return Optional.of(lastRange);
        }
    }

    public Optional<IntRange> getPreviousRange() {
        if (getFirstRange().isPresent()) {
            return Optional.of(new IntRange(getOffset() - getRangeSize(), getOffset() - 1));
        } else {
            return Optional.empty();
        }
    }

    public Optional<IntRange> getNextRange() {
        if (getLastRange().isPresent()) {
            int nextRange = getOffset() + getRangeSize();
            return Optional.of(new IntRange(nextRange, nextRange + getRangeSize() - 1));
        } else {
            return Optional.empty();
        }
    }

    private int getRangeSize() {
        return range.getMaximumInteger() - getOffset() + 1;
    }


}

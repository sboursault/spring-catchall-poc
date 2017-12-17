package poc.arkham.common.web.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.IntRange;

import static poc.arkham.common.web.util.RestPreconditions.validateWithRegex;

/**
 * {@link RangeQueryParameter} serves the same purpose as {@link org.springframework.data.domain.Pageable}.
 * The difference is that {@link RangeQueryParameter} is based on a range and not on a page number.
 */
public class RangeQueryParameter {

    int offset; // lower range
    int limit; // upper range

    public RangeQueryParameter(String rangeParam) {
        String newRange = StringUtils.defaultIfBlank(rangeParam, "0-19");
        validateWithRegex("range", newRange, "^\\d+-\\d+$");
        // validate offset < limit
        String[] parts = newRange.split("-");
        this.offset = Integer.valueOf(parts[0]);
        this.limit = Integer.valueOf(parts[1]);
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public IntRange getRange() {
        return new IntRange(offset, limit);
    }
}

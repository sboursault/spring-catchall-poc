package poc.arkham.common.util;


import org.apache.commons.lang.math.IntRange;
import org.junit.Test;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

public class PartialResultUnitTest {

    @Test
    public void test() {

        PartialResult<Integer> partialResult = new PartialResult<>(
                asList(7, 9),
                new IntRange(7, 9),
                14);
        assertThat( partialResult.getFirstRange().get()    ).isEqualTo( new IntRange(  0,  2 ));
        assertThat( partialResult.getPreviousRange().get() ).isEqualTo( new IntRange(  4,  6 ));
        assertThat( partialResult.getNextRange().get()     ).isEqualTo( new IntRange( 10, 12 ));
        assertThat( partialResult.getLastRange().get()     ).isEqualTo( new IntRange( 13, 15 ));

    }

    @Test
    public void testAtBeginning() {

        PartialResult<Integer> partialResult = new PartialResult<>(
                asList(0, 2),
                new IntRange(0, 2),
                10);
        assertThat( partialResult.getFirstRange().isPresent()     ).isFalse();
        assertThat( partialResult.getPreviousRange().isPresent()   ).isFalse();
        assertThat( partialResult.getNextRange().get() ).isEqualTo( new IntRange( 3, 5 ));
        assertThat( partialResult.getLastRange().get() ).isEqualTo( new IntRange( 7, 9 ));

    }

    @Test
    public void testAtEnd() {

        PartialResult<Integer> partialResult = new PartialResult<>(
                asList(7, 8),
                new IntRange(7, 8),
                8);
        assertThat( partialResult.getFirstRange().get()      ).isEqualTo(new IntRange(0, 1));
        assertThat( partialResult.getPreviousRange().get()   ).isEqualTo(new IntRange(5, 6));
        assertThat( partialResult.getNextRange().isPresent() ).isFalse();
        assertThat( partialResult.getLastRange().isPresent() ).isFalse();
    }

    @Test
    public void testWithoutResult() {

        PartialResult<Integer> partialResult = new PartialResult<>(
                emptyList(),
                new IntRange(7, 9),
                0);
        assertThat( partialResult.getFirstRange().isPresent()    ).isFalse();
        assertThat( partialResult.getPreviousRange().isPresent() ).isFalse();
        assertThat( partialResult.getNextRange().isPresent()     ).isFalse();
        assertThat( partialResult.getLastRange().isPresent()     ).isFalse();
    }

    @Test
    public void rangeBiggerThanNumberOfResults() {

        PartialResult<Integer> partialResult = new PartialResult<>(
                asList(0, 1, 2),
                new IntRange(0, 24),
                3);
        assertThat( partialResult.getFirstRange().isPresent()    ).isFalse();
        assertThat( partialResult.getPreviousRange().isPresent() ).isFalse();
        assertThat( partialResult.getNextRange().isPresent()     ).isFalse();
        assertThat( partialResult.getLastRange().isPresent()     ).isFalse();
    }
}

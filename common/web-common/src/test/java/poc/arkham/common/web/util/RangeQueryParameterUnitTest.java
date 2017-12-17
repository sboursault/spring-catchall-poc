package poc.arkham.common.web.util;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RangeQueryParameterUnitTest {

    @Test
    public void validateWithRegex_ok() {
        assertThat(new RangeQueryParameter("5-8").getOffset()).isEqualTo(5);
        assertThat(new RangeQueryParameter("5-8").getLimit()).isEqualTo(8);
        assertThat(new RangeQueryParameter("5-8").getSize()).isEqualTo(4);
    }

}

package poc.arkham.common.web.util;

import org.junit.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;

public class RestPreconditionsUnitTest {

    @Test
    public void validateWithRegex_ok() {
        // when
        RestPreconditions.validateWithRegex("range", "0-19", "^\\d+-\\d+$");
        // then no exception is thrown
    }

    @Test(expected = HttpMessageNotReadableException.class)
    public void validateWithRegex_fail() {
        // when
        RestPreconditions.validateWithRegex("range", "0-yop", "^\\d+-\\d+$");
        // then a HttpMessageNotReadableException is thrown
    }
}

package poc.arkham.inmate.domain.impl.util;

import org.springframework.util.StringUtils;
import poc.arkham.inmate.domain.model.Errors;

public class ValidationUtils {

    public static boolean checkNotEmpty(String value, String field, Errors errors) {
        if (StringUtils.isEmpty(value)) {
            errors.add(field + " must not be null or empty");
            return false;
        }
        return true;
    }
}

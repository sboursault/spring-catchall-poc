package poc.arkham.common.util;

public class ValidationUtils {

    public static boolean checkNotEmpty(String value, String field, Errors errors) {
        if (value == null || value.isEmpty()) {
            errors.add(field + " must not be null or empty");
            return false;
        }
        return true;
    }
}

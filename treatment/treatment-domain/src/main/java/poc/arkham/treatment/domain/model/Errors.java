package poc.arkham.treatment.domain.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Errors {

    private final List<Error> internalErrors = new ArrayList<>();

    public void add(String... messages) {
        add(Arrays.stream(messages).map(it -> new Error(it)).toArray(Error[]::new));
    }

    public void add(Error... errors) {
        Arrays.stream(errors).forEach(internalErrors::add);
    }

    public boolean hasErrors() {
        return !internalErrors.isEmpty();
    }

    public List<Error> getAll() {
        return internalErrors;
    }

    public static class Error {

        private final String message;

        public Error(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    // factory method
    public static Errors errors(String... messages) {
        Errors output = new Errors();
        for (String it : messages) {
            output.add(new Error(it));
        }
        return output;
    }

    // factory method
    public static Errors noError() {
        return new Errors();
    }

}

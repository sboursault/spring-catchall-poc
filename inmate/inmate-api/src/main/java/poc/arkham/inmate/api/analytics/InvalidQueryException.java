package poc.arkham.inmate.api.analytics;

public class InvalidQueryException extends IllegalArgumentException {

    public InvalidQueryException(String s) {
        super(s);
    }
}

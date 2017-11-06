package poc.arkham.common.webanalytics;

public class InvalidQueryException extends IllegalArgumentException {

    public InvalidQueryException(String s) {
        super(s);
    }
}

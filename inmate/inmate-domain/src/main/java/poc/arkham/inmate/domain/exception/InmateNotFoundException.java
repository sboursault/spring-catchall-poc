package poc.arkham.inmate.domain.exception;

public class InmateNotFoundException extends Exception {
    public InmateNotFoundException(String id) {
        super("no inmate found with id " + id);
    }
}

package poc.arkham.logistic.domain.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String id) {
        super("no entity found with id " + id);
    }
}

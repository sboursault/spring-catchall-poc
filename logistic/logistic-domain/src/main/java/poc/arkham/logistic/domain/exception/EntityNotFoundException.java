package poc.arkham.logistic.domain.exception;

public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String id) {
        this(id, "entity");
    }

    public EntityNotFoundException(String id, String type) {
        super("no " + type + " found with id " + id);
    }
}

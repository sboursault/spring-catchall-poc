package poc.arkham.common.web.exception;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String id, String type) {
        super("no " + type + " found with id " + id);
    }
}

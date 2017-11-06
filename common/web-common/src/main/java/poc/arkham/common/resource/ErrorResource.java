package poc.arkham.common.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Error message to be returned on failure.
 */
public class ErrorResource {

    private final List<String> messages = new ArrayList<>();

    public ErrorResource(String ... messages) {
        this(Arrays.asList(messages));
    }

    public ErrorResource(List<String> messages) {
        this.messages.addAll(messages);
    }

    public List<String> getMessages() {
        return messages;
    }

}

package poc.arkham.logistic.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Room {

    @Id
    private String id;

    private String name; // should be unique

    private boolean open;

    public Room(String name) {
        this.name = name;
    }

}

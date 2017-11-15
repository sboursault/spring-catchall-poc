package poc.arkham.logistic.domain.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Document
public class Room {

    @Id
    private String id;

    private String name; // should be unique, should not be the id if you want to change room names in the future

    private final List<Maintenance> maintenances = new ArrayList<>();

    private final List<Occupation> occupations = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Maintenance> getMaintenances() {
        return maintenances;
    }

    public List<Occupation> getOccupations() {
        return occupations;
    }

    public static class RoomBuilder
    {
        private Room room;

        private RoomBuilder()
        {
            room = new Room();
        }

        public RoomBuilder name(String name)
        {
            room.name = name;
            return this;
        }

        public RoomBuilder maintenances(Maintenance... maintenances)
        {
            room.maintenances.addAll(Arrays.asList(maintenances));
            return this;
        }

        public RoomBuilder occupations(Occupation... occupations)
        {
            room.occupations.addAll(Arrays.asList(occupations));
            return this;
        }

        public Room build()
        {
            return room;
        }
    }

    public static RoomBuilder newRoom()
    {
        return new RoomBuilder();
    }
}

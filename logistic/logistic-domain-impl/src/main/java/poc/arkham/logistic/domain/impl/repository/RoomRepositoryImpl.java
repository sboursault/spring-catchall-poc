package poc.arkham.logistic.domain.impl.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import poc.arkham.logistic.domain.model.Room;

import java.time.LocalDate;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepositoryCustom {

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public List<Room> findFreeRooms(LocalDate date) {
        Query query = new BasicQuery("{ occupations: { $in: [null, []] } }")
                .with(new PageRequest(0, 5));
        return mongoOperations.find(query, Room.class);
    }
}

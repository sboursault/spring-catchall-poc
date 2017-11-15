package poc.arkham.logistic.domain.impl.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import poc.arkham.logistic.domain.model.Room;

public interface RoomRepository extends MongoRepository<Room, String>, RoomRepositoryCustom {
}
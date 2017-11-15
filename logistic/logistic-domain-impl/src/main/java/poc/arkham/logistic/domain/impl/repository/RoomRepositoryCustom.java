package poc.arkham.logistic.domain.impl.repository;

import poc.arkham.logistic.domain.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepositoryCustom {

    List<Room> findFreeRooms(LocalDate date);
}

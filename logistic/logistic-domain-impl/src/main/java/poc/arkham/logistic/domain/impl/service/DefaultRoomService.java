package poc.arkham.logistic.domain.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import poc.arkham.logistic.domain.exception.EntityNotFoundException;
import poc.arkham.logistic.domain.exception.InvalidStateException;
import poc.arkham.logistic.domain.impl.repository.RoomRepository;
import poc.arkham.logistic.domain.model.Room;
import poc.arkham.logistic.domain.service.RoomService;

import java.time.LocalDate;
import java.util.List;

@Component
class DefaultRoomService implements RoomService {


    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomValidator roomValidator;

    public Room findById(String id) throws EntityNotFoundException {
        Room entity = roomRepository.findOne(id);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        return entity;
    }

    @Override
    public Room create(Room room) throws InvalidStateException {

        Assert.notNull(room, "[Assertion failed] - inmate is required; it must not be null");

        roomValidator.validate(room);

        return roomRepository.save(room);
    }

    @Override
    public List<Room> findFreeRooms(LocalDate date) {
        return roomRepository.findFreeRooms(date);
    }
}

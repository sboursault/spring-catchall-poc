package poc.arkham.logistic.domain.impl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import poc.arkham.logistic.domain.exception.EntityNotFoundException;
import poc.arkham.logistic.domain.exception.InvalidStateException;
import poc.arkham.logistic.domain.impl.repository.RoomRepository;
import poc.arkham.logistic.domain.model.Room;
import poc.arkham.logistic.domain.service.RoomService;
import poc.arkham.treatment.domain.exception.InmateNotFoundException;
import poc.arkham.treatment.domain.exception.InvalidStateException;
import poc.arkham.treatment.domain.model.Inmate;

import java.util.List;

import static poc.arkham.treatment.domain.model.InmateTransition.REGISTRATION;

@Component
class DefaultRoomService implements RoomService {


    @Autowired
    private RoomRepository roomRepository;

    public Room findById(String id) throws EntityNotFoundException {
        Room entity = roomRepository.findOne(id);
        if (entity == null) {
            throw new EntityNotFoundException(id);
        }
        return entity;
    }

    public Room create(Room room) throws InvalidStateException {

        Assert.notNull(room, "[Assertion failed] - inmate is required; it must not be null");

        // TODO VALIDATION

        return inmateRepository.save(inmate);
    }

    @Override
    public Room create(Room inmate) throws InvalidStateException {
        return null;
    }
}

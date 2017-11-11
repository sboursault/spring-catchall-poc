package poc.arkham.logistic.domain.service;

import poc.arkham.logistic.domain.exception.EntityNotFoundException;
import poc.arkham.logistic.domain.exception.InvalidStateException;
import poc.arkham.logistic.domain.model.Room;

public interface RoomService {

    Room findById(String id) throws EntityNotFoundException;

    Room create(Room inmate) throws InvalidStateException;
}

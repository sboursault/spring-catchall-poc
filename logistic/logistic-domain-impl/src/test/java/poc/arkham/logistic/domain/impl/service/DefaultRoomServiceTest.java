package poc.arkham.logistic.domain.impl.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import poc.arkham.logistic.domain.exception.InvalidStateException;
import poc.arkham.logistic.domain.impl.config.LogisticDomainApplication;
import poc.arkham.logistic.domain.impl.repository.RoomRepository;
import poc.arkham.logistic.domain.model.Occupation;
import poc.arkham.logistic.domain.model.Room;
import poc.arkham.logistic.domain.service.RoomService;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.assertj.core.api.Assertions.assertThat;
import static poc.arkham.logistic.domain.model.Room.newRoom;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= { LogisticDomainApplication.class })
public class DefaultRoomServiceTest {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private RoomService roomService;

    @Before
    public void setup() {
        roomRepository.deleteAll();;
    }

    @Test
    public void createRoom() throws Exception {

        Room newRoom = newRoom()
                .name("AAA")
                .occupations(new Occupation("penguin_1234", null, null))
                .build();

        roomService.create(newRoom);

        assertThat(roomRepository.findAll()).hasSize(1);
    }

    @Test(expected = InvalidStateException.class)
    public void createRoomInvalidRoom() throws Exception {

        Room newRoom = newRoom()
                .name("")
                .occupations(new Occupation("penguin_1234", null, null))
                .build();

        roomService.create(newRoom);

        // then an InvalidStateException is thrown
    }

    @Test
    public void findFreeRooms() throws Exception {

        roomRepository.save(newRoom()
                .name("AAA")
                .occupations(new Occupation("penguin_1234", null, null))
                .build());
        roomRepository.save(newRoom()
                .name("BBB")
                .occupations(/* none */)
                .build());

        List<Room> result = roomService.findFreeRooms(LocalDate.now());

        List<String> roomNames = result.stream().map(Room::getName).collect(toList());
        assertThat(roomNames).containsExactly("BBB");
    }

}

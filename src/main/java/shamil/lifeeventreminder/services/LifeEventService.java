package shamil.lifeeventreminder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shamil.lifeeventreminder.models.dao.LifeEvent;
import shamil.lifeeventreminder.models.dto.LifeEventDto;
import shamil.lifeeventreminder.repositories.ILifeEventRepository;
import shamil.lifeeventreminder.repositories.IUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LifeEventService {

    @Autowired
    private ILifeEventRepository lifeEventRepository;
    @Autowired
    private IUserRepository userRepository;

    public List<LifeEventDto> getAllEvents(Long userId) {
        List<LifeEventDto> lifeEventDtoList = new ArrayList<>();
        List<LifeEvent> events;
        if (userId != null) {
            events = lifeEventRepository.findByUserId_Id(userId);
            events.forEach(lifeEvent -> lifeEventDtoList.add(dataToWeb(lifeEvent)));
        } else {
            events = lifeEventRepository.findAll();
            events.forEach(lifeEvent -> lifeEventDtoList.add(dataToWeb(lifeEvent)));
        }
        return lifeEventDtoList;
    }

    public LifeEventDto addLifeEvent(LifeEventDto lifeEventDto) {
        return dataToWeb(lifeEventRepository.save(dataToDB(lifeEventDto)));
    }

    private LifeEvent dataToDB(LifeEventDto lifeEventDto) {
        LifeEvent lifeEvent = new LifeEvent();
        lifeEvent.setId(lifeEventDto.getId());
        lifeEvent.setEventName(lifeEventDto.getEventName());
        lifeEvent.setEventDate(lifeEventDto.getEventDate());
        lifeEvent.setUserId(userRepository.findById(lifeEventDto.getId()).orElse(null));
        return lifeEvent;
    }

    public LifeEventDto dataToWeb(LifeEvent lifeEvent) {
        LifeEventDto lifeEventDto = new LifeEventDto();
        lifeEventDto.setEventName(lifeEvent.getEventName());
        lifeEventDto.setEventDate(lifeEvent.getEventDate());
        lifeEventDto.setUserId(lifeEvent.getUserId().getId());
        return lifeEventDto;
    }
}

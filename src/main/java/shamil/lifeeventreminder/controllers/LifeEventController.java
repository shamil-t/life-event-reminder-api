package shamil.lifeeventreminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shamil.lifeeventreminder.models.dto.LifeEventDto;
import shamil.lifeeventreminder.services.LifeEventService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/lifeEvent/")
public class LifeEventController {

    @Autowired
    private LifeEventService lifeEventService;

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllEvents(@RequestParam("uid") Long userId) {
        Map<String, Object> map = new HashMap<>();
        List<LifeEventDto> events = lifeEventService.getAllEvents(userId);
        map.put("status", "success");
        map.put("data", events);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addNewEvent(@RequestBody LifeEventDto lifeEventDto) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", lifeEventService.addLifeEvent(lifeEventDto));
        map.put("status", "success");
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }


}

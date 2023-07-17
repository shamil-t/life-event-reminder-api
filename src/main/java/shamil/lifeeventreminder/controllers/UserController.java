package shamil.lifeeventreminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shamil.lifeeventreminder.models.dto.LoginDto;
import shamil.lifeeventreminder.models.dto.UserDto;
import shamil.lifeeventreminder.services.UserService;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee/")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.getAllUsers());
        map.put("status", "success");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable long id) {
        Map<String, Object> map = new HashMap<>();
        Optional<UserDto> user = userService.getUserById(id);
        if (user.isPresent()) {
            map.put("data", user);
            map.put("status", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", "failed");
            map.put("data", MessageFormat.format("User with {0} doesn't exist", id));
            return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserDto user) {
        Map<String, Object> map = new HashMap<>();
        UserDto userDto = userService.addUser(user);
        if (userDto != null) {
            map.put("data", userDto);
            map.put("status", "success");
        } else {
            map.put("status", "failed");
            map.put("data", "User with email '" + user.getEmail() + "' doesn't exists");
        }
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping(value = "login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody LoginDto login) {
        return new ResponseEntity<>(userService.userLogin(login), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> editUser(@PathVariable long id, @RequestBody UserDto user) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.editUser(user, id));
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable long id) {
        Map<String, Object> map = new HashMap<>();
        if (userService.deleteUser(id)) {
            map.put("status", "success");
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            map.put("status", "failed");
            return new ResponseEntity<>(map, HttpStatus.NO_CONTENT);
        }
    }


}

package shamil.lifeeventreminder.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shamil.lifeeventreminder.models.Dto.Login;
import shamil.lifeeventreminder.models.User;
import shamil.lifeeventreminder.services.UserService;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user/")
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
        User user = userService.getUserById(id);
        if (user != null) {
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
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("data", userService.addUser(user));
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> userLogin(@RequestBody Login login) {
        return new ResponseEntity<>(userService.userLogin(login), HttpStatus.CREATED);
    }

    @PutMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> editUser(@PathVariable long id, @RequestBody User user) {
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

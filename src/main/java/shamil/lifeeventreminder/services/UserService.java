package shamil.lifeeventreminder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shamil.lifeeventreminder.models.dao.User;
import shamil.lifeeventreminder.models.dto.LoginDto;
import shamil.lifeeventreminder.models.dto.UserDto;
import shamil.lifeeventreminder.repositories.IUserRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<UserDto> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            users.add(dataToWeb(user));
        });
        return users;
    }

    public UserDto addUser(UserDto user) {
        UserDto userDto = new UserDto();
        try {
            userDto = dataToWeb(userRepository.save((dataToDB(user))));
            return userDto;
        } catch (Exception ex) {
            System.out.println("Add User Exception :: " + ex.getMessage());
            return null;
        }
    }

    public Optional<UserDto> getUserById(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null)
            return Optional.of(dataToWeb(user));
        else return Optional.empty();
    }

    public Map<String, Object> userLogin(LoginDto login) {
        Map<String, Object> map = new HashMap<>();
        User user = userRepository.findByEmail(login.getEmail()).orElse(null);
        if (user != null) {
            if (Objects.equals(user.getPassword(), login.getPassword())) {
                map.put("status", "success");
                map.put("data", dataToWeb(user));
            } else {
                map.put("status", "failure");
                map.put("data", "incorrect password");
            }
        } else {
            map.put("status", "failure");
            map.put("data", "user with email " + login.getEmail() + " doesn't exist");
        }
        return map;
    }

    public UserDto editUser(UserDto user, long id) {
        User u = userRepository.findById(id).orElse(null);
        if (u != null) {
            u.setDesignation(user.getDesignation());
            u.setEmail(user.getEmail());
            u.setFName(user.getFName());
            u.setLName(user.getLName());
            u.setMangerId(user.getMangerId());
            u.setProfileImg(user.getProfileImg());
            u.setPhone(user.getPhone());
            return dataToWeb(userRepository.save(u));
        } else {
            return null;
        }
    }

    public List<UserDto> getUsersByManager(long id) {
        List<UserDto> users = new ArrayList<>();
        List<User> allUsers = userRepository.findAll().stream().filter(user -> user.getMangerId() == id).toList();
        allUsers.forEach(u -> {
            users.add(dataToWeb(u));
        });
        return users;
    }

    public boolean deleteUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else
            return false;
    }


    private User dataToDB(UserDto _user) {
        User user = new User();
        user.setFName(_user.getFName());
        user.setLName(_user.getLName());
        user.setEmail(_user.getEmail());
        user.setDesignation(_user.getDesignation());
        user.setMangerId(_user.getMangerId());
        user.setPhone(_user.getPhone());
        System.out.println("_user = " + _user.getPhone());
        return user;
    }

    private UserDto dataToWeb(User _user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(_user.getEmail());
        userDto.setDesignation(_user.getDesignation());
        userDto.setFName(_user.getFName());
        userDto.setLName(_user.getLName());
        userDto.setPhone(_user.getPhone());
        userDto.setProfileImg(_user.getProfileImg());
        UserDto user = getUserById(_user.getMangerId()).orElse(null);
        userDto.setManager(user);
        return userDto;
    }
}

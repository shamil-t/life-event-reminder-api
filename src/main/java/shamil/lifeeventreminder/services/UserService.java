package shamil.lifeeventreminder.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shamil.lifeeventreminder.models.Dto.Login;
import shamil.lifeeventreminder.models.User;
import shamil.lifeeventreminder.repositories.IUserRepository;

import java.util.*;

@Service
public class UserService {
    @Autowired
    private IUserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Map<String, Object> userLogin(Login login) {
        Map<String, Object> map = new HashMap<>();
        User user = userRepository.findByEmail(login.getEmail()).orElse(null);
        if (user != null) {
            if (Objects.equals(user.getPassword(), login.getPassword())) {
                map.put("status", "success");
                map.put("data", user);
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

    public User editUser(User user, long id) {
        User u = userRepository.findById(id).orElse(null);
        if (u != null) {
            u.setDesignation(user.getDesignation());
            u.setEmail(user.getEmail());
            u.setFName(user.getFName());
            u.setLName(user.getLName());
            u.setMangerId(user.getMangerId());
            u.setProfileImg(user.getProfileImg());
            u.setPassword(user.getPassword());
            u.setPhone(user.getPhone());
            return userRepository.save(u);
        } else {
            return null;
        }
    }

    public List<User> getUsersByManager(long id) {
        return userRepository.findAll().stream().filter(user -> user.getMangerId() == id).toList();
    }

    public boolean deleteUser(long id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            userRepository.delete(user);
            return true;
        } else
            return false;
    }
}

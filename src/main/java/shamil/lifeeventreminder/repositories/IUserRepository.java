package shamil.lifeeventreminder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shamil.lifeeventreminder.models.dao.User;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByEmail(String email);
}

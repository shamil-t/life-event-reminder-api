package shamil.lifeeventreminder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shamil.lifeeventreminder.models.LifeEvent;

public interface ILifeEventRepository extends JpaRepository<LifeEvent, Long> {
}

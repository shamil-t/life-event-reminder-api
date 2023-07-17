package shamil.lifeeventreminder.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import shamil.lifeeventreminder.models.dao.LifeEvent;

import java.util.List;

public interface ILifeEventRepository extends JpaRepository<LifeEvent, Long> {
    List<LifeEvent> findByUserId_Id(long id);

}

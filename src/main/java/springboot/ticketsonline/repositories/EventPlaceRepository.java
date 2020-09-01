package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ticketsonline.entities.EventPlace;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long>
{
}

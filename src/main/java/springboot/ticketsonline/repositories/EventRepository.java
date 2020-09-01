package springboot.ticketsonline.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>
{
}

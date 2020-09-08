package springboot.ticketsonline.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.Event;

import java.util.Date;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>
{
  Optional<Event> findByNameAndDate( String name, Date date);
}

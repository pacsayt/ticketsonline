package springboot.ticketsonline.repositories;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.ticketsonline.entities.Event;

import java.sql.Timestamp;
// import java.util.Date; pt++ : the database creates Timestamp (that inherits from Date)
import java.util.List;
import java.util.Optional;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>
{
  List<Event> findByName( String name);

  List<Event> findByDate( Timestamp date);

  Optional<Event> findByNameAndDate( String name, Timestamp date);
}

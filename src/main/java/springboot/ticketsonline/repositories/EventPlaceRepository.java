package springboot.ticketsonline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.ticketsonline.entities.EventPlace;

import java.util.List;

@Repository
public interface EventPlaceRepository extends JpaRepository<EventPlace, Long>
{
  List<EventPlace> findByNameContainingIgnoreCase(String name);

  List<EventPlace> findFirst2ByNameContainingIgnoreCase(String name);

  List<EventPlace> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

  // Sort sort = new Sort(Sort.Direction.ASC, "name");
  // List<Book> findByNameContains(String name, Sort sort);
}

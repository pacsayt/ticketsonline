package springboot.ticketsonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.repositories.EventPlaceRepository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;


/**
 * LazyInitializationException: could not initialize proxy [springboot.ticketsonline.entities.EventPlace#11] - no Session
 * When use getOne and findOne methods Spring Data JPA
 *
 * Optional<T> findById(ID id) (name in the new API) relies on EntityManager.find() that performs an entity eager loading.
 *
 * T getOne(ID id) relies on EntityManager.getReference() that performs an entity lazy loading.
 * So to ensure the effective loading of the entity, invoking a method on it is required.
 *
 * https://stackoverflow.com/questions/24482117/when-use-getone-and-findone-methods-spring-data-jpa
 */
@Service
public class EventPlaceService
{
  @Autowired
  private EventPlaceRepository eventPlaceRepository;

  public Long count()
  {
    return eventPlaceRepository.count();
  }

  public EventPlace save( EventPlace eventPlaceToSave)
  {
    return eventPlaceRepository.save( eventPlaceToSave);
  }

//    eventPlaceRepository.findOne(Example<EventPlace> eventPlaceExample)

  public Optional<EventPlace> findById( Long iD)
  {
    Optional<EventPlace> eventPlaceOptional = eventPlaceRepository.findById( iD);

    return eventPlaceOptional;
  }

  public EventPlace getOne( Long iD)
  {
    return eventPlaceRepository.getOne( iD);
  }

  public List<EventPlace> findAll()
  {
    return eventPlaceRepository.findAll();
  }

  public void delete(EventPlace  eventPlaceToBeDeleted)
  {
    eventPlaceRepository.delete( eventPlaceToBeDeleted);
  }
}
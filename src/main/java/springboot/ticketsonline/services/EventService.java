package springboot.ticketsonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
  @Autowired
  private EventRepository eventRepository;

  public Long count()
  {
    return eventRepository.count();
  }

  public Event save( Event EventToSave)
  {
    return eventRepository.save( EventToSave);
  }

//    EventRepository.findOne(Example<Event> EventExample)

  public Optional<Event> findById( Long iD)
  {
    Optional<Event> EventOptional = eventRepository.findById( iD);

    return EventOptional;
  }

  public List<Event> findAll()
  {
    return eventRepository.findAll();
  }

  public void delete( Event  EventToBeDeleted)
  {
    eventRepository.delete( EventToBeDeleted);
  }

  public Event getOne( Long iD)
  {
    return eventRepository.getOne( iD);
  }
}
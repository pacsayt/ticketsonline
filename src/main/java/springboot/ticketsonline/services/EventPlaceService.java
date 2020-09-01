package springboot.ticketsonline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.repositories.EventPlaceRepository;

import java.util.List;

@Service
public class EventPlaceService
{
  @Autowired
  private EventPlaceRepository eventPlaceRepository;

  public Long count()
  {
    return eventPlaceRepository.count();
  }

  public EventPlace save(EventPlace eventPlaceToSave)
  {
    return eventPlaceRepository.save( eventPlaceToSave);
  }

  public EventPlace getOne(Long iD)
  {
    return eventPlaceRepository.getOne( iD);
  }

  public List<EventPlace> findAll()
  {
    return eventPlaceRepository.findAll();
  }
}
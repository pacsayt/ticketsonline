package springboot.ticketsonline.controllers;

import org.springframework.web.bind.annotation.*;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.services.EventPlaceService;

import java.util.List;

@RestController( "/eventplace")
public class EventPlaceController
{
  private EventPlaceService eventPlaceService;

  @GetMapping()
  public List<EventPlace> getAll()
  {
    return eventPlaceService.findAll();
  }

  @GetMapping( "/{eventPlaceId}")
  public EventPlace getById( @RequestParam Long eventPlaceId)
  {
    EventPlace eventPlace = eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace());

    return eventPlace;
  }

  @PutMapping()
  public void put(@RequestBody EventPlace eventPlace)
  {
    eventPlaceService.save( eventPlace);
  }
}
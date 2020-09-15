package springboot.ticketsonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.entities.EventPlaces;
import springboot.ticketsonline.services.EventPlaceService;

import java.util.List;

@RestController                     // pt++ : you cannot specify root path here (it seems)
@RequestMapping( path="eventplace") // pt++ : root path must be specified separately
public class EventPlaceController
{
  @Autowired
  private EventPlaceService eventPlaceService;

  @GetMapping()
  public EventPlaces getAll()
  {
    return new EventPlaces( eventPlaceService.findAll());
  }

  @GetMapping( path="/{eventPlaceId}", produces = "application/json")
  public EventPlace getById( @PathVariable Long eventPlaceId)
  {
    EventPlace eventPlace = eventPlaceService.findById( eventPlaceId).orElseGet( () -> new EventPlace( 6L, "6", 6));

    return eventPlace;
  }

  @PostMapping() // pt++ : POST - INSERT
  public void post(@RequestBody EventPlace eventPlace)
  {
    eventPlaceService.save( eventPlace);
  }

  @PutMapping() // pt++ : PUT - update
  public void put(@RequestBody EventPlace eventPlace)
  {
    eventPlaceService.save( eventPlace);
  }
}
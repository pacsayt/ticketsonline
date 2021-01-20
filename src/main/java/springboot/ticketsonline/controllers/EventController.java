package springboot.ticketsonline.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.Events;
import springboot.ticketsonline.services.EventService;

import java.util.Optional;

/**
 * http://localhost:8080/event/
 */

@RestController
@RequestMapping( path="event")
public class EventController
{
  private static final Logger LOG = LoggerFactory.getLogger( EventController.class);

  @Autowired
  private EventService eventService;

  @GetMapping( produces = "application/json")
  public Events getAll()
  {
    LOG.info( "EventController::getAll() +++++++++++++++++++++++++++++++");
    return new Events( eventService.findAll());
  }

  @GetMapping( path="/{eventId}", produces = "application/json")
  public Optional<Event> getById(@PathVariable Long eventId)
  {
    LOG.info( "EventController::getById(" + eventId + ") +++++++++++++++++++++++++++++++");

    return eventService.findById( eventId);
  }

  // <-> public ResponseEntity<Object> EventPlaceController::post( @RequestBody EventPlace eventPlace)
  @PostMapping // pt++ : POST - INSERT
  public ResponseEntity<Optional<Event>> post( @RequestBody Event event)
  {
    LOG.info( "EventController::post( " + event + ") +++++++++++++++++++++++++++++++");

    Event savedEvent = eventService.save( event);

    return ResponseEntity.ok( Optional.of( savedEvent));
  }

  @PutMapping // pt++ : PUT - update
  public ResponseEntity<Optional<Event>> put( @RequestBody Event event)
  {
    LOG.info( "EventController::put( " + event + ") +++++++++++++++++++++++++++++++");

    Event savedEvent = eventService.save( event);

    return ResponseEntity.ok( Optional.of(savedEvent));
  }
}

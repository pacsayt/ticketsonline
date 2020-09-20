package springboot.ticketsonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.entities.EventPlaces;
import springboot.ticketsonline.services.EventPlaceService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
  public ResponseEntity<Object> post( @RequestBody EventPlace eventPlace)
  {
    //Generate resource id
    EventPlace eventPlaceSaved = eventPlaceService.save( eventPlace);

    // Create resource location : no idea what it is good for
//    URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                                              .path("/{id}")
//                                              .buildAndExpand( eventPlaceSaved.getId())
//                                              .toUri();

    Optional<EventPlace> optionalEventPlace = Optional.of( eventPlaceSaved);

    //Send location in response
    return ResponseEntity/*.created( location)*/.ok( optionalEventPlace); // pt++ : this works
  }

  @PutMapping() // pt++ : PUT - update
  public ResponseEntity<Optional<EventPlace>> put(@RequestBody EventPlace eventPlace)
  {
    EventPlace eventPlaceAfterSave = eventPlaceService.save( eventPlace);

    return ResponseEntity.ok( Optional.of( eventPlaceAfterSave));
  }

}
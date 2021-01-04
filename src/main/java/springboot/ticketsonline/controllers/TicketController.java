package springboot.ticketsonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.entities.Tickets;
import springboot.ticketsonline.services.TicketService;

import java.util.Optional;

/**
 * http://localhost:8080/ticket/
 * http://localhost:8080/ticket/11
 */

@RestController
@RequestMapping( path="ticket")
public class TicketController
{
  @Autowired
  private TicketService ticketService;

  @GetMapping
  public Tickets getAll()
  {
    return new Tickets( ticketService.findAll());
  }

  @GetMapping( path="{ticketId}", produces="application/json")
  public Ticket getById(@PathVariable Long ticketId)
  {
    Ticket ticketFound = ticketService.findById( ticketId).orElseGet( () -> new Ticket());

    return ticketFound;
  }

  @PostMapping
  public ResponseEntity<Object> post( @RequestBody Ticket ticket)
  {
    Ticket ticketSaved = ticketService.save( ticket);

    return ResponseEntity.ok( Optional.of( ticketSaved)); // pt++ : could be Ticket as well
  }

  @PutMapping
  public ResponseEntity<Optional<Ticket>> put( @RequestBody Ticket ticket)
  {
    Ticket ticketUpdated = ticketService.save( ticket);

    return ResponseEntity.ok( Optional.of( ticketUpdated));
  }
}
package springboot.ticketsonline.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import springboot.ticketsonline.entities.BookedTicket;
import springboot.ticketsonline.entities.BookedTickets;
import springboot.ticketsonline.services.BookedTicketService;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * http://localhost:8080/bookedticket/
 * http://localhost:8080/bookedticket/22
 * http://localhost:8080/bookedticket/33
 *
 * ConfigurableBeanFactory <-> WebApplicationContext
 *
 * SCOPE-thing :
 * In connection with @Bean :
 * @RequestScope == @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
 * @SessionScope
 * I have seen examples with scoping only in connection with @Bean
 * https://www.baeldung.com/spring-bean-scopes
 */

// @Scope( value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
@RestController
@RequestMapping( path="bookedticket")
public class BookedTicketController
{
//  SCOPE-thing :
//  @Resource( name = "customScopedBean")        <-> @Bean IS used together with @RequestScope / @SessionScope
//  private ACustomScopedBean customScopedBean;

  @Autowired
  private BookedTicketService bookedTicketService;

//  public BookedTicketController()
//  {
//    System.out.println( "BookedTicketController::BookedTicketController() : @Scope( value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) +++++++++++++++++++++++++++++++++++++");
//  }

  @GetMapping( produces = "application/json")
  public BookedTickets getAll()
  {
    return new BookedTickets( bookedTicketService.findAll());
  }

  @GetMapping( path="{bookedTicketId}", produces = "application/json")
  public BookedTicket getById(@PathVariable Long bookedTicketId)
  {
    BookedTicket bookedTicketFound = bookedTicketService.findById( bookedTicketId).orElseGet( BookedTicket::new);

    return bookedTicketFound;
  }

  @PostMapping()
  public ResponseEntity<Optional<BookedTicket>> post( @RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of( bookedTicketAfterSave));
  }

  @PutMapping()
  public ResponseEntity<Optional<BookedTicket>> put( @RequestBody BookedTicket bookedTicket)
  {
    BookedTicket bookedTicketAfterSave = bookedTicketService.save( bookedTicket);

    return ResponseEntity.ok( Optional.of( bookedTicketAfterSave));
  }
}
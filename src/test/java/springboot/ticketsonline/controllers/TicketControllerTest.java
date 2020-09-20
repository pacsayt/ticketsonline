package springboot.ticketsonline.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.services.TicketService;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class TicketControllerTest
{
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TicketService ticketService;

  @Test
  public void testFindOneById()
  {
    Ticket ticket = new Ticket();
    when( ticketService.findById( 11L));
    mockMvc.perform( get())
  }

}

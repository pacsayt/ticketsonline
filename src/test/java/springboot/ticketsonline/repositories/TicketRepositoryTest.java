package springboot.ticketsonline.repositories;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.EventPlace;
import springboot.ticketsonline.entities.Ticket;
import springboot.ticketsonline.entities.TicketWithIds;
import springboot.ticketsonline.services.TestBase;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
// @DataJpaTest // pt++ : TicketEntity is not a managed object (or so) when using this
@ExtendWith( SpringExtension.class)
@DisplayName( "TicketRepository test cases")
public class TicketRepositoryTest extends TestBase
{
  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private TicketRepository ticketRepository;

  @Test
  public void findByTicketPriceTest() throws ParseException
  {
    EventPlace eventPlace = new EventPlace(11L, "Name_11", 11);
    Event event = new Event(11L, "EventName_11", stringToDate("2020-09-03 11:32:41.00"), eventPlace);

    List<Ticket> ticketsFound = ticketRepository.findByEvent(event);

    assertEquals(4, ticketsFound.size());

    Ticket ticketEntityFound = ticketsFound.get(0);

    assertEquals(10L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByTicketPrice()
  {
    List<Ticket> ticketEntiesFound = ticketRepository.findByTicketPrice(113);

    assertEquals(1, ticketEntiesFound.size());

    Ticket ticketEntityFound = ticketEntiesFound.get(0);

    assertEquals(13L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByTicketPriceLessThan()
  {
    List<Ticket> ticketEntiesFound = ticketRepository.findByTicketPriceLessThan( 115);

    assertEquals(4, ticketEntiesFound.size());

    Ticket ticketEntityFound = ticketEntiesFound.get(0);

    assertEquals(10L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByEvent() throws ParseException
  {
    Event event = new Event(11L, "EventName_11", stringToDate("2020-09-03 11:32:41.00"), null);

    List<Ticket> ticketEntiesFound = ticketRepository.findByEvent(event);

    assertEquals(4, ticketEntiesFound.size());

    Optional<Ticket> ticket = ticketEntiesFound.stream().findFirst( t -> t.getiD() == 11L );
    Ticket ticketEntityFound = ticketEntiesFound.get(0);

    assertEquals(11L, ticketEntityFound.getiD());
  }

  @Test
  public void testFindByEventAndTicketPrice() throws ParseException
  {
    EventPlace eventPlace = new EventPlace(11L, "Name_11", 11);
    Event event = new Event(11L, "EventName_11", stringToDate("2020-09-03 11:32:41.00"), eventPlace);

    List<Ticket> ticketEntiesFound = ticketRepository.findByEventAndTicketPrice( event, 112);

    assertEquals(1, ticketEntiesFound.size());

    Ticket ticketEntityFound = ticketEntiesFound.get(0);

    assertEquals(12L, ticketEntityFound.getiD());
  }

  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
  // pt++ : THESE MAINLY FOR THE TicketRepositoryTest, THEY WON'T BE USED PROBABLY ELSEWHERE ---------------------------------------------------------------------------
  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------

  @Test
  public void testFetchById()
  {
    Ticket ticket11 = ticketRepository.fetchById(11L);

    assertEquals(1, ticket11.getSeatNo());
  }

  @Test
  public void testFetchTicketsOrderedBySeatNo()
  {
    Sort sortBySeatNo = Sort.by(Sort.Direction.ASC, "seatNo");

    List<Ticket> ticketsFetched = ticketRepository.fetchTicketsByMinTicketPrice(100, sortBySeatNo);

    assertEquals(5, ticketsFetched.size());

    assertEquals(11L, ticketsFetched.get(0).getiD());
  }

  @Test
  public void testFetchTicketsByMinTicketPriceAndSeatNo()
  {
    List<Ticket> ticketsFetched = ticketRepository.fetchTicketsByMinTicketPriceAndSeatNo(100, 2);

    assertEquals(3, ticketsFetched.size());

    Set<Long> possibleTicketIds = Set.of(12L, 13L, 33L);

    assertTrue(possibleTicketIds.contains(ticketsFetched.get(0).getiD()));
  }

  @Test
  public void testFetchTicketsByEventNameOrderedByName()
  {
    List<Ticket> ticketsFetched = ticketRepository.fetchTicketsByEventName("EventName_11");

    assertEquals(4, ticketsFetched.size());

    assertEquals(10L, ticketsFetched.get(0).getiD());
  }

  @Test
  public void testFetchTicketsByEventNameOrderedByNameParam()
  {
    List<Ticket> ticketsFetched = ticketRepository.fetchTicketsByEventNameParam("EventName_11");

    assertEquals(4, ticketsFetched.size());

    assertEquals(10L, ticketsFetched.get(0).getiD());
  }

  @Test
  public void testSelectingMultipleValues1()
  {
//    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("persistenceUnitName"); pt++ : specified usually in persistence.xml
//    EntityManager entityManager = entityManagerFactory.createEntityManager();

    TypedQuery<Object[]> query = entityManager.createQuery( "SELECT t.seatNo, t.ticketPrice FROM Ticket AS t", Object[].class);

    List<Object[]> results = query.getResultList();

    assertEquals( 6, results.size());

    assertEquals( 2, results.get( 0)[0]);
    assertEquals( 100, results.get( 0)[1]);
  }

  @Test
  public void testColumnResult()
  {
    List<Long> employeeIds = entityManager.createNamedQuery("EventIdWithId").getResultList();
    assertEquals(1, employeeIds.size());
  }

  @Test
  public void testConstructorResult()
  {
    List<TicketWithIds> ticketWithIds = entityManager.createNamedQuery("SeatNoTicketPriceEventIdWithId", TicketWithIds.class).getResultList();
    assertEquals(1, ticketWithIds.size());
  }


/*
  @Test
  public void testSelectingMultipleValues()
  {
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory( "persistenceUnitName");
    EntityManager entityManager = entityManagerFactory.createEntityManager();

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Object[]> criteriaQuery = criteriaBuilder.createQuery( Object[].class);
    Root<Ticket> ticketRoot = criteriaQuery.from( Ticket.class);

    // Select multiple scalar values
    criteriaQuery.multiselect( ticketRoot.get( Ticket_.seatNo).alias( "seatNo"), ticketRoot.get( Ticket_.ticketPrice).alias( "ticketPrice"));
    ^ itt tartottam https://www.objectdb.com/java/jpa/query/jpql/select

  ez nem mukodott a Ticket_ miatt : https://thorben-janssen.com/jpql/

    List<Tuple> authorNames = entityManager.createQuery(criteriaQuery).getResultList();

    for(Tuple authorName :authorNames)
    {
      log.info(authorName.get( "seatNo") + " " + authorName.get( "ticketPrice"));
    }
  }
*/
}

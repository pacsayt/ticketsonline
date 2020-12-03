package springboot.ticketsonline.repositories;

import org.hibernate.Criteria;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.ticketsonline.entities.Event;
import springboot.ticketsonline.entities.Ticket;

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.NamedNativeQuery;
import javax.persistence.SqlResultSetMapping;
import java.util.List;
import java.util.Optional;

/**
 * pt++ :
 * Derived Queries with Spring Data JPA – The Ultimate Guide
 * https://thorben-janssen.com/ultimate-guide-derived-queries-with-spring-data-jpa/
 *
 * Hibernate Logging Guide – Use the right config for development and production
 * https://thorben-janssen.com/hibernate-logging-guide/
 *
 * https://thorben-janssen.com/jpql/
 * https://thorben-janssen.com/spring-data-jpa-query-annotation/
 * https://thorben-janssen.com/jpa-native-queries/
 * https://thorben-janssen.com/how-to-join-unrelated-entities/ *
 *
 * JpaRepository< , > -> CrudRepository< , >
 */

// https://www.baeldung.com/jpa-sql-resultset-mapping
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>
{
  List<Ticket> findByTicketPrice( Integer ticketPrice);

  List<Ticket> findByTicketPriceLessThan( Integer ticketPrice);

  List<Ticket> findByTicketPriceBetween( Integer minPrice, Integer maxPrice);

  List<Ticket> findByEventAndTicketPrice( Event event, Integer ticketPrice); // pt++ : referenced object in query param

  List<Ticket> findByEvent( Event event);

  List<Ticket> findByEventName( String name); // pt++ : referenced object in query param

  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
  // pt++ : THESE MAINLY FOR THE TicketRepositoryTest, THEY WON'T BE USED PROBABLY ELSEWHERE ---------------------------------------------------------------------------
  //
  // https://www.objectdb.com/java/jpa/query/jpql/select :
  // Because construction of managed entity objects has some overhead, queries that return non entity objects, as the two queries above, are usually more efficient.
  // Such queries are useful mainly for displaying information efficiently
  // (pt++ : ==not associated with an EntityManager and changes to them when a transaction is active are not propagated to the database).
  // They are less productive with operations that update or delete entity objects, in which managed entity objects are needed.
  // -------------------------------------------------------------------------------------------------------------------------------------------------------------------
  @Query( value="SELECT t FROM Ticket t WHERE t.id=?1", nativeQuery = false) // pt++ : you can omit SELECT ... : "FROM ticket t WHERE t.id=?1"
  Ticket fetchById( Long id);

  // pt++ : in case of READ ONLY access, this is more effective by avoiding overhead associated with managed objects
//  @Query( value="SELECT new Ticket( t.id, t.seatNo, null, t.ticketPrice) FROM Ticket t WHERE t.id=?1", nativeQuery = false) // pt++ : you can omit SELECT ... : "FROM ticket t WHERE t.id=?1"
//  Ticket fetchByIdNotManaged( Long id);

  @Query( "SELECT t FROM Ticket t WHERE t.ticketPrice > ?1")
  List<Ticket> fetchTicketsByMinTicketPrice( Integer minTicketPrice, Sort sort);

  @Query( "SELECT t FROM Ticket t WHERE t.ticketPrice > ?1 AND t.seatNo > ?2")
  List<Ticket> fetchTicketsByMinTicketPriceAndSeatNo( Integer minTicketPrice, Integer seatNo);

  @Query( "SELECT t FROM Ticket t JOIN t.event e WHERE e.name = ?1")
  List<Ticket> fetchTicketsByEventName( String eventName);

  @Query( "SELECT t FROM Ticket t JOIN t.event e WHERE e.name = :eventName")
  List<Ticket> fetchTicketsByEventNameParam(@Param( "eventName") String eventName);

  // pt++ : examples :
  // SELECT a FROM Author a WHERE (SELECT count(b) FROM Book b WHERE a MEMBER OF b.authors ) > 1
  // SELECT a FROM Author a WHERE (SELECT count(b) FROM Book b WHERE a MEMBER OF b.authors ) > 1
  // SELECT new org.thoughts.on.java.model.AuthorValue(a.id, a.firstName, a.lastName) FROM Author a

/*
Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
List<Author> authors = authorRepository.findByFirstName("Thorben", sort);
@Query("FROM Author WHERE firstName = ?1")
List<Author> findByFirstName(String firstName, Sort sort);

    @Query("FROM Author WHERE firstName = ?1")
    List<Author> findByFirstName(String firstName, Pageable pageable);

  Pageable pageable = PageRequest.of(0, 10);
  List<Author> authors = authorRepository.findByFirstName("Thorben", pageable);

  @Query(value = "SELECT * FROM author WHERE first_name = :firstName", nativeQuery = true)
  List<Author> findAuthorsByFirstName(@Param("firstName") String firstName);

  @Query("FROM Author WHERE firstName = ?1")
  List<Author> findByFirstName(String firstName);

  @Query("SELECT a FROM Author a WHERE firstName = :firstName AND lastName = :lastName")
  List<Author> findByFirstNameAndLastName(@Param("lastName") String firstName, @Param("firstName") String lastName);

  @Query("UPDATE Author SET firstName = :prefix || firstName")
  @Modifying <-> UPDATE
  void addPrefixToFirstName(@Param("prefix") String prefix);

  map to maneged entity : select all its fields
  Query q = em.createNativeQuery("SELECT a.id, a.version, a.firstname, a.lastname FROM Author a", Author.class);
  List<Author> authors = q.getResultList();

  for (Author a : authors) {
    System.out.println("Author "
            + a.getFirstName()
            + " "
            + a.getLastName());
  }

  @SqlResultSetMapping( name = "AuthorValueMapping",
          classes = @ConstructorResult(
                  targetClass = AuthorValue.class,
                  columns = {
                          @ColumnResult(name = "id", type = Long.class),
                          @ColumnResult(name = "firstname"),
                          @ColumnResult(name = "lastname"),
                          @ColumnResult(name = "numBooks", type = Long.class)}))


  Query q = em.createNativeQuery("SELECT a.id, a.firstname, a.lastname, count(b.id) as numBooks FROM Author a JOIN BookAuthor ba on a.id = ba.authorid JOIN Book b ON b.id = ba.bookid GROUP BY a.id", "AuthorValueMapping");
  List<AuthorValue> authors = q.getResultList();

for (AuthorValue a : authors) {
  System.out.println("Author "
          + a.getFirstName()
          + " "
          + a.getLastName()
          + " wrote "
          + a.getNumBooks()
          + " books.");
}
*/
}

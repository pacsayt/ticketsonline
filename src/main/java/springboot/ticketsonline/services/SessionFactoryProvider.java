package springboot.ticketsonline.services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.hibernate.id.Configurable;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * https://www.baeldung.com/spring-bean-scopes
 * singleton
 * prototype
 * request
 * session
 * application
 * websocket
 *
 * # spring.datasource.url=jdbc:h2:file:/data/demo pt++ : persist in file instead of volatile memory
 * # pt++ : http://www.h2database.com/html/features.html#connection_modes
 *
 * spring.h2.console.enabled=true  # pt++ this is Spring-Hibernate config in application.properties
 *                                 #      there is hibernate.properties however
 */

// https://ducmanhphan.github.io/2020-04-02-How-to-configure-Hibernate-correctly-in-our-project/

@Configuration
public class SessionFactoryProvider
{
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//    localSessionFactoryBean.setDataSource(restDataSource());
    localSessionFactoryBean.setPackagesToScan( new String[] { "springboot.ticketsonline.entities" });
    return localSessionFactoryBean;
  }

  @Bean
  public SessionFactory configureSessionFactory() throws IOException
  {
    org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
    InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("hibernate.properties");
    Properties hibernateProperties = new Properties();
    hibernateProperties.load(inputStream);
    configuration.setProperties(hibernateProperties);

    // configuration.addAnnotatedClass(Foo.class);

    ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    sessionFactory.  setPackagesToScan( new String[] { "springboot.ticketsonline.entities" });
    return sessionFactory;
  }



//  @Bean
  // @Scope("singleton") // pt++ : this is the default
  public SessionFactory createSessionFactory()
  {
    StandardServiceRegistry standardServiceRegistry = null;
    SessionFactory sessionFactory = null;

    try
    {
      org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//      configuration.addClass( Event.class); // pt++ : -> *.hbm.xml


      // Create registry
//    standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();                                   // pt++ : this uses hibernate.cfg.xml
      standardServiceRegistry = new StandardServiceRegistryBuilder().applySettings( configuration.getProperties()).build(); // pt++ : this uses hibernate.properties

      // Create MetadataSources
      MetadataSources metadataSources = new MetadataSources( standardServiceRegistry);

//      metadataSources.addAnnotatedClass( EventPlace.class); pt++ : will search then corresponding XML files ...
//      metadataSources.addAnnotatedClass( Event.class);
//      metadataSources.addAnnotatedClass( Ticket.class);
//      metadataSources.addAnnotatedClass( BookedTickets.class);

      // Create Metadata
      Metadata metadata = metadataSources.getMetadataBuilder().build();

      // Create SessionFactory
      sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (standardServiceRegistry != null)
      {
        StandardServiceRegistryBuilder.destroy( standardServiceRegistry);
      }
    }

    return sessionFactory;
  }
/*
  @Bean
  @Scope("singleton") // pt++ : this is the default
  public SessionFactory createSessionFactoryWontWork()
  {
    SessionFactory sessionFactory = null;

    org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
//    configuration.configure("/hibernate.cfg.xml");
//    StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
//    sessionFactory = configuration.buildSessionFactory( standardServiceRegistryBuilder.build());

    configuration.addFile("/hibernate.cfg.xml");
    sessionFactory = configuration.buildSessionFactory();

    return sessionFactory;
  }
 */
}

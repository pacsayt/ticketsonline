package springboot.ticketsonline.services;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.FileNotFoundException;
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
  @Bean
  @Scope("singleton") // pt++ : this is the default
  public SessionFactory createSessionFactory()
  {
    StandardServiceRegistry registry = null;
    SessionFactory sessionFactory = null;

    try
    {
      // Create registry
      registry = new StandardServiceRegistryBuilder().configure().build();

      // Create MetadataSources
      MetadataSources sources = new MetadataSources(registry);

      // Create Metadata
      Metadata metadata = sources.getMetadataBuilder().build();

      // Create SessionFactory
      sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
    catch (Exception e)
    {
      e.printStackTrace();
      if (registry != null)
      {
        StandardServiceRegistryBuilder.destroy( registry);
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

package fr.iagl.gamification.listener.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import akka.actor.ActorSystem;

/**
 * Configuration du système AKKA
 * 
 * @author ALEX
 *
 */
@Configuration
@ComponentScan
public class AkkaConfiguration {
  /**
   * Context d'application
   */
  @Autowired
  private ApplicationContext applicationContext;

  /**
   * Méthode de création du système
   * 
   * @return l'acteur système
   */
  @Bean
  public ActorSystem actorSystem() {
    ActorSystem system = ActorSystem.create("akka-system");
    SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);
    return system;
  }
}
 
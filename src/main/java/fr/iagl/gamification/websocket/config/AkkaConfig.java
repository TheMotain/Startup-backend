package fr.iagl.gamification.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;
import fr.iagl.gamification.listener.akka.SpringExtension;

@Configuration
@Lazy
@ComponentScan(basePackages = { "fr.iagl.gamification.services", "fr.iagl.gamification.services.impl", 
    "fr.iagl.gamification.listener.akka"})
public class AkkaConfig {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    /**
     * Actor system singleton for this application.
     */
    @Bean
    public ActorSystem actorSystem() {

        ActorSystem system = ActorSystem
            .create("AkkaTaskProcessing", akkaConfiguration());

        springExtension.initialize(applicationContext);
        return system;
    }

    /**
     * Read configuration from application.conf file
     */
    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
    }
}

package fr.iagl.gamification.listener.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import akka.actor.ActorSystem;

@Configuration
@Lazy
@ComponentScan(basePackages = { "fr.iagl.gamification.services",
    "fr.iagl.gamification.listener", "fr.iagl.gamification.listener.akka" })
public class AkkaConfig {
	@Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private SpringExtension springExtension;

    @Bean
    public ActorSystem actorSystem() {
    	System.out.println("\n\n here");
        ActorSystem actorSystem = ActorSystem.create("actors", akkaConfiguration());
        springExtension.initialize(applicationContext);
        return actorSystem;
    }

    @Bean
    public Config akkaConfiguration() {
        return ConfigFactory.load();
}
}

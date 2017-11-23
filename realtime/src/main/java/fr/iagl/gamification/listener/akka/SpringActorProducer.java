package fr.iagl.gamification.listener.akka;

import org.springframework.context.ApplicationContext;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;

/**
 * Créateur d'acteur AKKA
 * @author ALEX
 *
 */
public class SpringActorProducer implements IndirectActorProducer {

	/**
	 * Context de l'application
	 */
	private ApplicationContext applicationContext;

	/**
	 * Nom de l'acteur
	 */
	private String beanActorName;

	/**
	 * Constructeur
	 * @param applicationContext Context de l'application
	 * @param beanActorName Nom de l'acteur à créer
	 */
	public SpringActorProducer(ApplicationContext applicationContext, String beanActorName) {
		this.applicationContext = applicationContext;
		this.beanActorName = beanActorName;
	}

	/*
	 * (non-Javadoc)
	 * @see akka.actor.IndirectActorProducer#produce()
	 */
	@Override
	public Actor produce() {
		return (Actor) applicationContext.getBean(beanActorName);
	}

	/*
	 * (non-Javadoc)
	 * @see akka.actor.IndirectActorProducer#actorClass()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Class<? extends Actor> actorClass() {
		return (Class<? extends Actor>) applicationContext.getType(beanActorName);
	}
}

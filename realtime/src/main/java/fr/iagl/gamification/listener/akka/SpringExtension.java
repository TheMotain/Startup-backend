package fr.iagl.gamification.listener.akka;

import org.springframework.context.ApplicationContext;

import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;

/**
 * Singleton
 * Extension Spring pour fonctionnement AKKA
 * 
 * @author ALEX
 *
 */
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

	/**
	 * Instance du singleton
	 */
	public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();

	/**
	 * Constructeur privé pour ne pas pouvoir réinstancier le singleton
	 */
	private SpringExtension(){
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see akka.actor.ExtensionId#createExtension(akka.actor.ExtendedActorSystem)
	 */
	@Override
	public SpringExt createExtension(ExtendedActorSystem system) {
		return new SpringExt();
	}

	/**
	 * Implémentation de l'extension
	 * @author ALEX
	 *
	 */
	public static class SpringExt implements Extension {

		/**
		 * Context de l'application
		 */
		private volatile ApplicationContext applicationContext;

		/**
		 * Initialisation
		 * @param applicationContext Context à initialiser
		 */
		public void initialize(ApplicationContext applicationContext) {
			this.applicationContext = applicationContext;
		}

		/**
		 * Méthode de génération d'un acteur
		 * @param actorBeanName nom de l'acteur
		 * @return actor généré
		 */
		public Props props(String actorBeanName) {
			return Props.create(SpringActorProducer.class, applicationContext, actorBeanName);
		}
	}
}

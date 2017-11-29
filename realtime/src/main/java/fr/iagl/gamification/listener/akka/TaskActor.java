package fr.iagl.gamification.listener.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedAbstractActor;
import fr.iagl.gamification.model.TaskModel;
import fr.iagl.gamification.services.TaskService;

/**
 * Définition d'un acteur
 * 
 * @author ALEX
 *
 */

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TaskActor extends UntypedAbstractActor {

	/**
	 * Service pour le traitement d'une task
	 */
	@Autowired
	private TaskService service;

	/*
	 * (non-Javadoc)
	 * 
	 * @see akka.actor.UntypedAbstractActor#onReceive(java.lang.Object)
	 */
	@Override
	public void onReceive(Object message) throws Throwable {
		if (message instanceof TaskModel) {
			service.treatTask(((TaskModel) message));
		} else {
			unhandled(message);
		}
	}
}

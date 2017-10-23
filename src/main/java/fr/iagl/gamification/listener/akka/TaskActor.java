package fr.iagl.gamification.listener.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import fr.iagl.gamification.services.impl.AkkaTaskServiceImpl;

/**
 * Acteur akka
 * 
 * @author Hélène Meyer
 *
 */
@Component("taskActor")
@Scope("prototype")
public class TaskActor extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging
        .getLogger(getContext().system(), "TaskProcessor");

    static public Props props() {
        return Props.create(TaskActor.class, () -> new TaskActor());
    }
    
    /**
	 * service traitant les données akka
	 */
	@Autowired
	private AkkaTaskServiceImpl service;
    
    @Override
    public void onReceive(Object message) throws Exception {
    	Task mess = (Task) message;
        service.treatTask((Task) message);
        log.debug("Treat task {}", message.toString());
    }
}

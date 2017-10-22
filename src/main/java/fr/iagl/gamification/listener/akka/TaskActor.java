package fr.iagl.gamification.listener.akka;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.Props;
import akka.actor.UntypedAbstractActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;

/**
 * Acteur akka
 * 
 * @author Hélène Meyer
 *
 */
@Component
@Scope("prototype")
public class TaskActor extends UntypedAbstractActor {

    private final LoggingAdapter log = Logging
        .getLogger(getContext().system(), "TaskProcessor");

    static public Props props() {
        return Props.create(TaskActor.class, () -> new TaskActor());
    }
    
    @Override
    public void onReceive(Object message) throws Exception {
    	Task mess = (Task) message;
        mess.getService().treatTask((Task) message);
        log.debug("Treat task {}", message.toString());
    }
}

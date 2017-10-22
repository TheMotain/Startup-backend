package fr.iagl.gamification.listener.akka;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.Terminated;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;

/**
 * Acteur qui gère tous les autres acteurs et leur attribue des tâches
 * 
 * @author Hélène Meyer
 *
 */
@Component
@Scope("prototype")
public class Supervisor extends AbstractActor {

    private final LoggingAdapter log = Logging
        .getLogger(getContext().system(), "Supervisor");

    private Router router;

    static public Props props() {
        return Props.create(Supervisor.class, () -> new Supervisor());
    }
    
    @Override
    public void preStart() throws Exception {

        log.info("Starting up");

        List<Routee> routees = new ArrayList<Routee>();
        for (int i = 0; i < 100; i++) {
            ActorRef actor = getContext().actorOf(TaskActor.props());
            getContext().watch(actor);
            routees.add(new ActorRefRoutee(actor));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees);
        super.preStart();
    }

    @Override
    public void postStop() throws Exception {
        log.info("Shutting down");
        super.postStop();
    }

	@Override
	public Receive createReceive() {
        return receiveBuilder()
                .match(Task.class, message -> {
                    router.route(message, getSender());
                })
                .match(Terminated.class, message -> {
                	// Readd task actors if one failed
                    router = router.removeRoutee(((Terminated) message).actor());
                    ActorRef actor = getContext().actorOf(TaskActor.props());
                    getContext().watch(actor);
                	router = router.addRoutee(new ActorRefRoutee(actor));
                })
                .build();
    }

}

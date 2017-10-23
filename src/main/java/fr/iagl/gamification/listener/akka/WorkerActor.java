package fr.iagl.gamification.listener.akka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import akka.actor.UntypedActor;
import fr.iagl.gamification.services.BusinessService;

@Component("workerActor")
@Scope("prototype")
public class WorkerActor extends UntypedActor {

    @Autowired
    private BusinessService businessService;

    private int count = 0;

    @Override
    public void onReceive(Object message) throws Exception {
        if (message instanceof Request) {
            businessService.perform(this + " " + (++count));
        } else if (message instanceof Response) {
            getSender().tell(count, getSelf());
        } else {
            unhandled(message);
        }
    }

    public static class Request {
    }

    public static class Response {
    }
}

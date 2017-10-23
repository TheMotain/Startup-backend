package fr.iagl.gamification.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akka.pattern.Patterns;
import akka.util.Timeout;
import fr.iagl.gamification.listener.akka.SpringExtension;
import fr.iagl.gamification.listener.akka.Task;
import fr.iagl.gamification.listener.akka.WorkerActor;
import fr.iagl.gamification.services.impl.AkkaTaskServiceImpl;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

/**
 * Ecoute tous les changements de la base de données et fais les traitements
 * 
 * @author Hélène Meyer
 *
 */
@Component
class Listener implements Runnable{

	/**
	 * Connexion avec la base de donnée
	 */
	private Connection conn;
	/**
	 * Connexion avec la base de donnée postgres
	 */
	private org.postgresql.PGConnection pgconn;
	
	/**
	 * service traitant les données akka
	 */
	@Autowired
	private AkkaTaskServiceImpl service;
	
	/**
	 * broadcast un message dans un cannal 
	 */
	@Autowired
    public SimpMessageSendingOperations messagingTemplate;
	
	/**
	 * acteur akka qui gère l'ensemble de ses acteurs
	 */
	private ActorRef supervisor;
	
	@Autowired
	private ActorSystem actorSystem;
	
	@Autowired
	private SpringExtension springExtension;

	/**
	 * Initialisation d'un système d'acteurs et connexion avec la base de donnée
	 * @throws Exception 
	 */
	Listener() throws Exception {
		//ActorSystem system = ActorSystem.create("system");
        final LoggingAdapter log = Logging.getLogger(actorSystem, "system");
        log.info("Starting up");
        this.supervisor = actorSystem.actorOf(springExtension.props("workerActor"), "worker-actor");

        this.supervisor.tell(new WorkerActor.Request(), null);
        
        //this.supervisor = system.actorOf(Supervisor.props());
        FiniteDuration duration = FiniteDuration.create(1, TimeUnit.SECONDS);
        Future<Object> awaitable = Patterns.ask(this.supervisor, new WorkerActor.Response(), Timeout.durationToTimeout(duration));

        log.info("\n\nResponse: " + Await.result(awaitable, duration));
		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection("jdbc:postgresql://172.28.2.225:5432/startup","startup","startup");
		this.conn = conn;
		this.pgconn = (org.postgresql.PGConnection)conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN table_update");
		stmt.close();
	}

	@Async
	public void run() {
		while (true) {
			try {
				treatNotification();

				// wait a while before checking again for new
				// notifications
				Thread.sleep(500);
			} catch (SQLException sqle) {
				sqle.printStackTrace();
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			} catch (JSONException json) {
				json.printStackTrace();
			}
		}
	}

	/**
	 * Reception de la notification et envoie du message aux acteurs akka
	 * 
	 * @throws SQLException
	 * @throws JSONException 
	 */
	public void treatNotification() throws SQLException, JSONException {
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT 1");
		rs.close();
		stmt.close();

		org.postgresql.PGNotification notifications[] = pgconn.getNotifications();
		if (notifications != null) {
			for (int i=0; i<notifications.length; i++) {
				System.out.println("Got notification: " + notifications[i].getParameter());
				Task task = new Task( new JSONObject(notifications[i].getParameter()), service, messagingTemplate);
		        supervisor.tell(task, null);
				
			}
		}
		
		stmt.close();
	}
	
	public void setPgConn(org.postgresql.PGConnection pgconn) {
		this.pgconn = pgconn;
	}
	
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void setSupervisor(Object supervisor) {
		this.supervisor = (ActorRef) supervisor;
	}

}

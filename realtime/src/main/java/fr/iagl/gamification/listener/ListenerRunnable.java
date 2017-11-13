package fr.iagl.gamification.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import fr.iagl.gamification.listener.akka.SpringExtension;
import fr.iagl.gamification.model.Task;

/**
 * Ecoute tous les changements de la base de données et fais les traitements
 * 
 * @author Hélène Meyer
 *
 */
@Component
class ListenerRunnable{

	/**
	 * Connexion avec la base de donnée
	 */
	private Connection conn;
	/**
	 * Connexion avec la base de donnée postgres
	 */
	private org.postgresql.PGConnection pgconn;
	
	@Autowired
	private ActorSystem system;

	private ActorRef actor;
	
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	
	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	

	/**
	 * Initialisation d'un système d'acteurs et connexion avec la base de donnée
	 * 
	 * @throws Exception
	 */
	@PostConstruct
	public void init() throws Exception {
		 actor = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("taskActor"), "task");



		
		Class.forName("org.postgresql.Driver");
		Connection conn = DriverManager.getConnection(datasourceUrl, datasourceUsername,
				datasourcePassword);
		this.conn = conn;
		this.pgconn = (org.postgresql.PGConnection) conn;
		Statement stmt = conn.createStatement();
		stmt.execute("LISTEN table_update");
		stmt.close();
	}

	@Scheduled(cron = "${cron.scheduler.listener.runnable}")
	public void run() throws Exception {
		try {
			treatNotification();

		} catch (JSONException json) {
			json.printStackTrace();
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
			for (int i = 0; i < notifications.length; i++) {
				System.out.println("Got notification: " + notifications[i].getParameter());
				Task task = new Task(new JSONObject(notifications[i].getParameter()));
				actor.tell(task, null);
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
}

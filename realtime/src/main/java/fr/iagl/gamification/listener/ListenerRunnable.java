package fr.iagl.gamification.listener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
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
	 * Logger
	 */
	private static final Logger LOG = Logger.getLogger(ListenerRunnable.class);
	
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
	 * @throws SQLException 
	 */
	@PostConstruct
	public void init() throws SQLException {
		 actor = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props("taskActor"), "task");

		 Statement stmt = null;
		 
		 try {
			Class.forName("org.postgresql.Driver");
			this.conn = DriverManager.getConnection(datasourceUrl, datasourceUsername,
					datasourcePassword);

			this.pgconn = (org.postgresql.PGConnection) conn;
			
			stmt = conn.createStatement();
			stmt.execute("LISTEN table_update");
		} catch (SQLException e) {
			LOG.warn("Erreur d'accès à la base de donnée");
		} catch (ClassNotFoundException e) {
			LOG.warn("Driver Postgres non trouvé");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		
		
	}

	@Scheduled(cron = "${cron.scheduler.listener.runnable}")
	public void run() {
		try {
			treatNotification();

		} catch (JSONException json) {
			LOG.warn("jsonException");
		} catch (SQLException e) {
			LOG.warn("Erreur de connexion base de donnée");
		}
	}

	/**
	 * Reception de la notification et envoie du message aux acteurs akka
	 * 
	 * @throws SQLException
	 */
	public void treatNotification() throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT 1");
			rs.close();
			stmt.close();

			org.postgresql.PGNotification[] notifications = pgconn.getNotifications();
			if (notifications != null) {
				for (int i = 0; i < notifications.length; i++) {
					System.out.println("Got notification: " + notifications[i].getParameter());
					Task task = new Task(new JSONObject(notifications[i].getParameter()));
					actor.tell(task, null);
				}
			}

		} catch (Exception e) {
			LOG.warn("Erreur dans le traitement de la notification");
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void setPgConn(org.postgresql.PGConnection pgconn) {
		this.pgconn = pgconn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
}

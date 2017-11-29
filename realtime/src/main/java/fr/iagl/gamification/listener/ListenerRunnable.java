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
import fr.iagl.gamification.model.TaskModel;

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
	 * Driver JDBC à utiliser
	 */
	public static final String DRIVER = "org.postgresql.Driver";
	
	/**
	 * Query pour passer en écoute de notifications
	 */
	public static final String LISTEN_UPDATE_QUERY = "LISTEN table_update";
	
	/**
	 * Query pour récupérer les notifications reçus
	 */
	private static final String GET_FIRST_NOTIFICATION_QUERY = "SELECT 1";
	
	/**
	 * Nom root de l'acteur pour le system
	 */
	public static final String SYSTEM_ROOT_ACTOR_NAME = "taskActor";
	
	/**
	 * Nom de l'acteur root
	 */
	public static final String ROOT_ACTOR_NAME = "task";
	
	/**
	 * Connexion avec la base de donnée
	 */
	private Connection conn;
	/**
	 * Connexion avec la base de donnée postgres
	 */
	private org.postgresql.PGConnection pgconn;
	
	/**
	 * Systèmes des acteurs AKKA
	 */
	@Autowired
	private ActorSystem system;

	/**
	 * Acteur AKKA principal
	 */
	private ActorRef actor;
	
	/**
	 * Source postgresql
	 */
	@Value("${spring.datasource.url}")
	private String datasourceUrl;
	
	/**
	 * user postgresql
	 */
	@Value("${spring.datasource.username}")
	private String datasourceUsername;
	
	/**
	 * password postgresql
	 */
	@Value("${spring.datasource.password}")
	private String datasourcePassword;
	

	/**
	 * Initialisation d'un système d'acteurs et connexion avec la base de donnée
	 * @throws SQLException 
	 */
	@PostConstruct
	public void init() throws SQLException {
		 actor = system.actorOf(SpringExtension.SPRING_EXTENSION_PROVIDER.get(system).props(SYSTEM_ROOT_ACTOR_NAME), ROOT_ACTOR_NAME);

		 Statement stmt = null;
		 try {
			Class.forName(DRIVER);
			this.conn = DriverManager.getConnection(datasourceUrl, datasourceUsername,
					datasourcePassword);
			this.pgconn = (org.postgresql.PGConnection) conn;

			stmt = conn.createStatement();
			stmt.execute(LISTEN_UPDATE_QUERY);
			
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

	/**
	 * Traitement batch schédulé par un expression CRON configurée dans le application.properties
	 */
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
			ResultSet rs = stmt.executeQuery(GET_FIRST_NOTIFICATION_QUERY);
			rs.close();

			org.postgresql.PGNotification[] notifications = pgconn.getNotifications();
			if (notifications != null) {
				for (int i = 0; i < notifications.length; i++) {
					LOG.info("Got notification: " + notifications[i].getParameter());
					TaskModel task = new TaskModel(new JSONObject(notifications[i].getParameter()));
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

	/**
	 * Récupère la connexion postgresql
	 * @param pgconn la connexion postgresql
	 */
	public void setPgConn(org.postgresql.PGConnection pgconn) {
		this.pgconn = pgconn;
	}

	/**
	 * Récupère la connexion jdbc
	 * @param conn la connexion jdbc
	 */
	public void setConn(Connection conn) {
		this.conn = conn;
	}
}
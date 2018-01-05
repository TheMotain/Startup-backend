package fr.iagl.gamification.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "responsable")
public class ResponsableEntity implements Serializable{

	/**
	 * Generated Serial Version UID
	 */
	private static final long serialVersionUID = -5899913687499762471L;

	/**
	 * Classe
	 */
	
	 @Id
	 @Column( name = "classroom_fk", nullable = false )
	 private Long idClassroom;
	 
	 
	 /** 
	  * Utilisateur
	  */
	 @Id
	 @Column( name = "user_fk", nullable = false )
	 private Long idUser;
	 
	 
	 @ManyToOne( cascade = CascadeType.PERSIST )
	 @JoinColumn( name = "classroom_fk", insertable = false, updatable = false )
	 private ClassEntity classeL;
	 
	 @ManyToOne( cascade = CascadeType.PERSIST )
	 @JoinColumn( name = "user_fk", insertable = false, updatable = false )
	 //private UserEntity classeM;

}

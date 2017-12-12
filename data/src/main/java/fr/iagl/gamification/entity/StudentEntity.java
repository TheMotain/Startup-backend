package fr.iagl.gamification.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Entité décrivant un élève en base de données
 * @author ALEX
 *
 */
@Entity
@Table(name = "pupil")
public class StudentEntity implements Serializable {

	/**
	 * Generated Serial version UID
	 */
	private static final long serialVersionUID = 8652841504295936751L;

	/**
	 * Identifiant unique de l'élève
	 */
	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	/**
	 * Token unique de l'utilateur
	 */
	@Column(name = "token", nullable = false)
	private String studentToken;
	
	/**
	 * Prénom
	 */
	@Column(name = "firstname", nullable = false, columnDefinition = "text")
	private String firstName;
	
	/**
	 * Nom
	 */
	@Column(name = "lastname", nullable = false, columnDefinition = "text")
	private String lastName;
	
	/**
	 * date de naissance
	 */
	@Column(name = "borndate", nullable = false, columnDefinition = "date")
	private Date born;
	
	/**
	 * classe de l'élève
	 */
	@ManyToOne
	@JoinColumn(name = "classroom", nullable = true)
	private ClassEntity classroom;
	
	/**
	 * point de l'élève
	 */
    @PrimaryKeyJoinColumn
	@OneToOne(cascade = CascadeType.ALL)
    private PointEntity points;
    
    /**
     * Avatar de l'élève
     */
    @PrimaryKeyJoinColumn
    @OneToOne(cascade = CascadeType.ALL)
    private AvatarEntity avatar;
    
    @OneToMany(mappedBy = "student")
	private List<ResultQcmEntity> resultsQcm;

	/**
	 * Getter de l'attribut {@link StudentEntity#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#id}
	 * @param id l'attribut {@link StudentEntity#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#firstName}
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#firstName}
	 * @param firstName l'attribut {@link StudentEntity#firstName} à setter
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#lastName}
	 * @return lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#lastName}
	 * @param lastName l'attribut {@link StudentEntity#lastName} à setter
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#classroom}
	 * @return classroom
	 */
	public ClassEntity getClassroom() {
		return classroom;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#classroom}
	 * @param classroom l'attribut {@link StudentEntity#classroom} à setter
	 */
	public void setClassroom(ClassEntity classroom) {
		this.classroom = classroom;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#born}
	 * @return born
	 */
	public Date getBorn() {
		return born;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#born}
	 * @param born l'attribut {@link StudentEntity#born} à setter
	 */
	public void setBorn(Date born) {
		this.born = born;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#points}
	 * @return points
	 */
	public PointEntity getPoints() {
		return points;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#points}
	 * @param points l'attribut {@link StudentEntity#points} à setter
	 */
	public void setPoints(PointEntity points) {
		this.points = points;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#avatar}
	 * @return avatar
	 */
	public AvatarEntity getAvatar() {
		return avatar;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#avatar}
	 * @param avatar l'attribut {@link StudentEntity#avatar} à setter
	 */
	public void setAvatar(AvatarEntity avatar) {
		this.avatar = avatar;
	}

	/**
	 * Getter de l'attribut {@link StudentEntity#studentToken}
	 * @return studentToken
	 */
	public String getStudentToken() {
		return studentToken;
	}

	/**
	 * Setter de l'attribut {@link StudentEntity#studentToken}
	 * @param studentToken l'attribut {@link StudentEntity#studentToken} à setter
	 */
	public void setStudentToken(String studentToken) {
		this.studentToken = studentToken;
	}
	
	
}

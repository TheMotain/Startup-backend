package fr.iagl.gamification.object;

/**
 * Objet de l'IHM représentant le point
 * 
 * @author Hélène MEYER
 *
 */
public class PointObject {

	/**
	 * identifiant des points
	 */
	private long id;
	
	/**
	 * bonus de l'élève
	 */
	private long bonus;
	
	/**
	 * malus de l'élève
	 */
	private long malus;
	
	/**
	 * identifiant de l'élève
	 */
	private long idStudent;

	/**
	 * Getter de l'attribut {@link PointObject#id}
	 * @return id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link PointObject#id}
	 * @param id l'attribut {@link PointObject#id} à setter
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link PointObject#bonus}
	 * @return bonus
	 */
	public long getBonus() {
		return bonus;
	}

	/**
	 * Setter de l'attribut {@link PointObject#bonus}
	 * @param bonus l'attribut {@link PointObject#bonus} à setter
	 */
	public void setBonus(long bonus) {
		this.bonus = bonus;
	}

	/**
	 * Getter de l'attribut {@link PointObject#malus}
	 * @return malus
	 */
	public long getMalus() {
		return malus;
	}

	/**
	 * Setter de l'attribut {@link PointObject#malus}
	 * @param malus l'attribut {@link PointObject#malus} à setter
	 */
	public void setMalus(long malus) {
		this.malus = malus;
	}

	/**
	 * Getter de l'attribut {@link PointObject#idStudent}
	 * @return idStudent
	 */
	public long getIdStudent() {
		return idStudent;
	}

	/**
	 * Setter de l'attribut {@link PointObject#idStudent}
	 * @param idStudent l'attribut {@link PointObject#idStudent} à setter
	 */
	public void setIdStudent(long idStudent) {
		this.idStudent = idStudent;
	}
	
	
}

package fr.iagl.gamification.model;

public class ResultQcmModel {

	private Long id;
	
	private StudentModel student;
	
	private AnswerModel answer;

	/**
	 * Getter de l'attribut {@link ResultQcmModel#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmModel#id}
	 * @param id l'attribut {@link ResultQcmModel#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link ResultQcmModel#student}
	 * @return student
	 */
	public StudentModel getStudent() {
		return student;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmModel#student}
	 * @param student l'attribut {@link ResultQcmModel#student} à setter
	 */
	public void setStudent(StudentModel student) {
		this.student = student;
	}

	/**
	 * Getter de l'attribut {@link ResultQcmModel#answer}
	 * @return answer
	 */
	public AnswerModel getAnswer() {
		return answer;
	}

	/**
	 * Setter de l'attribut {@link ResultQcmModel#answer}
	 * @param answer l'attribut {@link ResultQcmModel#answer} à setter
	 */
	public void setAnswer(AnswerModel answer) {
		this.answer = answer;
	}
	
	
}

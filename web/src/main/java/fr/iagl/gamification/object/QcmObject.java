package fr.iagl.gamification.object;

import java.util.Date;
import java.util.List;

public class QcmObject {

	private Long id;
	
	private String title;
	
	private String instruction;
	
	private long idClass;
	
	private Date dateIns;
	
	private List<QuestionObject> questions;
	
	/**
	 * Getter de l'attribut {@link QcmObject#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#id}
	 * @param id l'attribut {@link QcmObject#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmObject#title}
	 * @return title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#title}
	 * @param title l'attribut {@link QcmObject#title} à setter
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Getter de l'attribut {@link QcmObject#instruction}
	 * @return instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#instruction}
	 * @param instruction l'attribut {@link QcmObject#instruction} à setter
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getter de l'attribut {@link QcmObject#idClass}
	 * @return idClass
	 */
	public long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#idClass}
	 * @param idClass l'attribut {@link QcmObject#idClass} à setter
	 */
	public void setIdClass(long idClass) {
		this.idClass = idClass;
	}

	/**
	 * Getter de l'attribut {@link QcmObject#questions}
	 * @return questions
	 */
	public List<QuestionObject> getQuestions() {
		return questions;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#questions}
	 * @param questions l'attribut {@link QcmObject#questions} à setter
	 */
	public void setQuestions(List<QuestionObject> questions) {
		this.questions = questions;
	}

	/**
	 * Getter de l'attribut {@link QcmObject#dateIns}
	 * @return dateIns
	 */
	public Date getDateIns() {
		return dateIns;
	}

	/**
	 * Setter de l'attribut {@link QcmObject#dateIns}
	 * @param dateIns l'attribut {@link QcmObject#dateIns} à setter
	 */
	public void setDateIns(Date dateIns) {
		this.dateIns = dateIns;
	}



	public class QuestionObject {
		
		private Long id;
		
		private String query;
		
		private int nbPoints;
		
		private List<AnswerObject> answers;

		/**
		 * Getter de l'attribut {@link QcmObject.QuestionObject#id}
		 * @return id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.QuestionObject#id}
		 * @param id l'attribut {@link QcmObject.QuestionObject#id} à setter
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Getter de l'attribut {@link QcmObject.QuestionObject#query}
		 * @return query
		 */
		public String getQuery() {
			return query;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.QuestionObject#query}
		 * @param query l'attribut {@link QcmObject.QuestionObject#query} à setter
		 */
		public void setQuery(String query) {
			this.query = query;
		}

		/**
		 * Getter de l'attribut {@link QcmObject.QuestionObject#nbPoints}
		 * @return nbPoints
		 */
		public int getNbPoints() {
			return nbPoints;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.QuestionObject#nbPoints}
		 * @param nbPoints l'attribut {@link QcmObject.QuestionObject#nbPoints} à setter
		 */
		public void setNbPoints(int nbPoints) {
			this.nbPoints = nbPoints;
		}

		/**
		 * Getter de l'attribut {@link QcmObject.QuestionObject#answers}
		 * @return answers
		 */
		public List<AnswerObject> getAnswers() {
			return answers;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.QuestionObject#answers}
		 * @param answers l'attribut {@link QcmObject.QuestionObject#answers} à setter
		 */
		public void setAnswers(List<AnswerObject> answers) {
			this.answers = answers;
		}
	}
	
	public class AnswerObject {
		
		private Long id;
		
		private String choice;
		
		private boolean good;

		/**
		 * Getter de l'attribut {@link QcmObject.AnswerObject#id}
		 * @return id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.AnswerObject#id}
		 * @param id l'attribut {@link QcmObject.AnswerObject#id} à setter
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Getter de l'attribut {@link QcmObject.AnswerObject#choice}
		 * @return choice
		 */
		public String getChoice() {
			return choice;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.AnswerObject#choice}
		 * @param choice l'attribut {@link QcmObject.AnswerObject#choice} à setter
		 */
		public void setChoice(String choice) {
			this.choice = choice;
		}

		/**
		 * Getter de l'attribut {@link QcmObject.AnswerObject#good}
		 * @return good
		 */
		public boolean isGood() {
			return good;
		}

		/**
		 * Setter de l'attribut {@link QcmObject.AnswerObject#good}
		 * @param good l'attribut {@link QcmObject.AnswerObject#good} à setter
		 */
		public void setGood(boolean good) {
			this.good = good;
		}
		
		
	}
}

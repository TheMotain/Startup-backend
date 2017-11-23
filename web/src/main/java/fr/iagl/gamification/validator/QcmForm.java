package fr.iagl.gamification.validator;

import java.util.List;

/**
 * Qcm à envoyer/modifier
 *
 * @author Hélène MEYER
 *
 */
public class QcmForm implements AbstractForm{

	/**
	 * id de la classe
	 */
	private long idClass;
	
	/**
	 * id du QCM
	 */
	private Long id;
	
	/**
	 * titre du QCM
	 */
	private String titre;
	
	/**
	 * instruction du QCM
	 */
	private String instruction;
	
	/**
	 * Ensemble des questions du QCM
	 */
	private List<QuestionForm> questions;
	
	/**
	 * Getter de l'attribut {@link QcmForm#idClass}
	 * @return idClass
	 */
	public long getIdClass() {
		return idClass;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#idClass}
	 * @param idClass l'attribut {@link QcmForm#idClass} à setter
	 */
	public void setIdClass(long idClass) {
		this.idClass = idClass;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#id}
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#id}
	 * @param id l'attribut {@link QcmForm#id} à setter
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#titre}
	 * @return titre
	 */
	public String getTitre() {
		return titre;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#titre}
	 * @param titre l'attribut {@link QcmForm#titre} à setter
	 */
	public void setTitre(String titre) {
		this.titre = titre;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#instruction}
	 * @return instruction
	 */
	public String getInstruction() {
		return instruction;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#instruction}
	 * @param instruction l'attribut {@link QcmForm#instruction} à setter
	 */
	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	/**
	 * Getter de l'attribut {@link QcmForm#questions}
	 * @return questions
	 */
	public List<QuestionForm> getQuestions() {
		return questions;
	}

	/**
	 * Setter de l'attribut {@link QcmForm#questions}
	 * @param questions l'attribut {@link QcmForm#questions} à setter
	 */
	public void setQuestions(List<QuestionForm> questions) {
		this.questions = questions;
	}

	/**
	 * Vérification des questions
	 *
	 * @author Hélène MEYER
	 *
	 */
	public class QuestionForm implements AbstractForm {
		
		/**
		 * identifiant de la question
		 */
		private Long id;
		
		/**
		 * liste des choix
		 */
		private List<ChoiceForm> choices;

		/**
		 * Getter de l'attribut {@link QcmForm.QuestionForm#id}
		 * @return id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Setter de l'attribut {@link QcmForm.QuestionForm#id}
		 * @param id l'attribut {@link QcmForm.QuestionForm#id} à setter
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Getter de l'attribut {@link QcmForm.QuestionForm#choices}
		 * @return choices
		 */
		public List<ChoiceForm> getChoices() {
			return choices;
		}

		/**
		 * Setter de l'attribut {@link QcmForm.QuestionForm#choices}
		 * @param choices l'attribut {@link QcmForm.QuestionForm#choices} à setter
		 */
		public void setChoices(List<ChoiceForm> choices) {
			this.choices = choices;
		}
		
	}
	
	/**
	 * Vérification des choix
	 *
	 * @author Hélène MEYER
	 *
	 */
	public class ChoiceForm implements AbstractForm {
		
		/**
		 * identifiant du choix
		 */
		private Long id;
		
		/**
		 * intitulé du choix
		 */
		private String choice;
		
		/**
		 * true si c'est la bonne réponse
		 */
		private boolean good;

		/**
		 * Getter de l'attribut {@link QcmForm.ChoiceForm#id}
		 * @return id
		 */
		public Long getId() {
			return id;
		}

		/**
		 * Setter de l'attribut {@link QcmForm.ChoiceForm#id}
		 * @param id l'attribut {@link QcmForm.ChoiceForm#id} à setter
		 */
		public void setId(Long id) {
			this.id = id;
		}

		/**
		 * Getter de l'attribut {@link QcmForm.ChoiceForm#choice}
		 * @return choice
		 */
		public String getChoice() {
			return choice;
		}

		/**
		 * Setter de l'attribut {@link QcmForm.ChoiceForm#choice}
		 * @param choice l'attribut {@link QcmForm.ChoiceForm#choice} à setter
		 */
		public void setChoice(String choice) {
			this.choice = choice;
		}

		/**
		 * Getter de l'attribut {@link QcmForm.ChoiceForm#good}
		 * @return good
		 */
		public boolean isGood() {
			return good;
		}

		/**
		 * Setter de l'attribut {@link QcmForm.ChoiceForm#good}
		 * @param good l'attribut {@link QcmForm.ChoiceForm#good} à setter
		 */
		public void setGood(boolean good) {
			this.good = good;
		}
		
		
	}
}

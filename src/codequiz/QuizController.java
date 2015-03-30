package codequiz;

import java.util.LinkedList;

public class QuizController {

	private LinkedList<Question> list = new LinkedList<Question>();
	private Question question, question2;

	public QuizController() {
		question = new Question(
				"Vilka av följande påståenden är korrekta?",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				"Ett subsystem kan inte bestå av andra subsystem",
				"Vattenfallsmodellen är en agil modell.",
				"Man måste använda ett informationssystem i lösningen som byggs medvattenfallsmodellen",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				1);
		question2 = new Question(
				"Vilka av följande påståenden om objekt är korrekta?",
				"Alla objekt representerar abstraktioner av något konkret.",
				"Alla objekt har ett tillstånd.",
				"Alla objekt måste ha någon operation som utför en matematisk beräkning",
				"Inkapsling betyder att ett attribut inte kan ändra värde efter det att objektet skapats.",
				"Alla objekt har ett tillstånd.", 2);
		setQuestion();// Emma was here, just a little
	}

	public void setQuestion() {
		list.add(question);
		list.add(question2);
	}

	public Question getQuestion() {
		return list.pop();
	}

}
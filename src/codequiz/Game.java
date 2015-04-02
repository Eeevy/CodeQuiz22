package codequiz;

import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Game {
	
	private LinkedList<Question> questionlist = new LinkedList<Question>();
	private LinkedList<QuizScenario> scenariolist = new LinkedList<QuizScenario>();
	private Question question, question2;
	private QuizScenario scenario;
	private ImageIcon storypic, correctpic,incorrectpic; 
	
	public Game(){
		
		setQuestion();
		scenario = new QuizScenario(storypic,correctpic,incorrectpic);
		setScenario();
		}
	
	public void CreateQuestions(){
		question = new Question(
				"Vilket av följande påståenden är korrekta?",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				"Ett subsystem kan inte bestå av andra subsystem",
				"Vattenfallsmodellen är en agil modell.",
				"Man måste använda ett informationssystem i lösningen som byggs medvattenfallsmodellen",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				1);
		question2 = new Question(
				"Vilket av följande påståenden om objekt är korrekta?",
				"Alla objekt representerar abstraktioner av något konkret.",
				"Alla objekt har ett tillstånd.",
				"Alla objekt måste ha någon operation som utför en matematisk beräkning",
				"Inkapsling betyder att ett attribut inte kan ändra värde efter det att objektet skapats.",
				"Alla objekt har ett tillstånd.", 2);
		
	}
	
	public void setQuestion() {
		questionlist.add(question);
		questionlist.add(question2);
	}

	public Question getQuestion() {
		return questionlist.pop();
	}
	public void setScenario(){
		scenariolist.add(scenario);
	}
	public QuizScenario getScenario(){
		return scenariolist.pop();
	}
	
	public LinkedList<Question> getQuestions(){
		return questionlist;
	}
	
	public LinkedList<QuizScenario> getScenarios(){
		return scenariolist;
	}
	
}

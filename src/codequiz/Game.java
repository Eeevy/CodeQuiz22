package codequiz;

import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.ImageIcon;

public class Game implements Serializable {
	
	private LinkedList<Question> questionlist = new LinkedList<Question>();
	private LinkedList<QuizScenario> scenariolist = new LinkedList<QuizScenario>();
	private Question question, question2, question3;
	private QuizScenario  scenario;
	
	
	
	public Game(){
		System.out.println("Game: Konstruktor");
		createQuestions();
		
//		setQuestion();
//		scenario = new QuizScenario(storypic,correctpic,incorrectpic);
//		setScenario();
		}
	
		

	public void createQuestions(){
		System.out.println("Game: createQuestions()");
		question = new Question(
				"Vilket av följande påståenden är korrekta?FUNKA NU!",
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
		question3 = new Question(
				"Vilket av följande påståenden är korrekta?FUNKA NU!",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				"Ett subsystem kan inte bestå av andra subsystem",
				"Vattenfallsmodellen är en agil modell.",
				"Man måste använda ett informationssystem i lösningen som byggs medvattenfallsmodellen",
				"Ett subsystem är en del av ett system som kan fungera som ett eget system.",
				1);
		
				setQuestion();
	}
	public void CreateScenario(){
	scenario = new QuizScenario(new ImageIcon("/CodeQuiz22/src/media/diary.jpg"),
			new ImageIcon("/CodeQuiz22/src/media/Correct.jpg"), 
			new ImageIcon("/CodeQuiz22/src/media/Incorrect.jpg"));
	setScenario();
	}
	
	public void setQuestion() {
		questionlist.add(question);
		questionlist.add(question2);
		questionlist.add(question3);
	}

	public Question getQuestion(int index) {
		return questionlist.get(index);//funkar som peek
		
	}
	public void setScenario(){
		scenariolist.add(scenario);
	}
	public QuizScenario getScenario(int index){
		return scenariolist.get(index);
	}
	
	public int getSize(){
		return questionlist.size();
	}
	

}

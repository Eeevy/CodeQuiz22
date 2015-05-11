package CodeQuizServer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.swing.ImageIcon;

import codequiz.Question;

/**
 * Klassen består av ett objekt som innehåller samtliga frågor och scenarion för
 * ett spel som sedan skickas från server till klient vid spelets start.
 * Frågorna skall i en senare version hämtas från databasen och ett 30 tal slumpas innan
 * de skickas till klient. (I den ordningen om dessa inte kan slumpas från databasen)
 */
public class Game implements Serializable {

	private LinkedList<Question> questionlist = new LinkedList<Question>();
	private LinkedList<QuizScenario> scenariolist = new LinkedList<QuizScenario>();
	private QuizScenario scenario, scenario1, scenario2, scenario3, scenario4,
			scenario5, scenario6, scenario7, scenario8;
	private Database dbKlass = new Database();

	/**
	 * Konstruerar frågor och scenarion
	 */
	public Game() {
		System.out.println("Game: Konstruktor");
		//createQuestions();
		dbKlass.getQuestionDB();
		createScenario();
		setQuestion();
	}

	/**
	 * Metoden skapar scenarion, bestående av tre bilder vardera; bild vid
	 * fråga, bild vid rätt svar, bild vid fel svar
	 */
	public void createScenario() {
		System.out.println("Game: createScenario()");
		scenario = new QuizScenario(new ImageIcon("src/media/Story1.jpg"),
				new ImageIcon("src/media/Correct1.jpg"), new ImageIcon(
						"src/media/Incorrect1.jpg"));
		scenario1 = new QuizScenario(
				new ImageIcon("src/media/dementorer.jpeg"), new ImageIcon(
						"src/media/Dementorerb.jpeg"), new ImageIcon(
						"src/media/dementorerv.jpeg"));
		scenario2 = new QuizScenario(new ImageIcon(
				"src/media/Scenario2intro.jpg"), new ImageIcon(
				"src/media/Scenario2correct.jpg"), new ImageIcon(
				"src/media/Scenario2inCorrect.jpg"));
		scenario3 = new QuizScenario(new ImageIcon(
				"src/media/beatrixQuestion.png"), new ImageIcon(
				"src/media/beatrixCorrect.png"), new ImageIcon(
				"src/media/beatrixIncorrect.png"));
		scenario4 = new QuizScenario(new ImageIcon(
				"src/media/MonsterBookOfMonstersQ.png"), new ImageIcon(
				"src/media/MonsterBookOfMonstersC.png"), new ImageIcon(
				"src/media/MonsterBookOfMonstersW.png"));
		scenario5 = new QuizScenario(new ImageIcon(
				"src/media/ScenarioDumbledoreIntro.jpg"), new ImageIcon(
				"src/media/ScenarioDumbledoreCorrect.jpg"), new ImageIcon(
				"src/media/ScenarioDumbledoreIncorrect.jpg"));
		scenario6 = new QuizScenario(new ImageIcon(
				"src/media/ScenarioOakOwl.jpg"), new ImageIcon(
				"src/media/ScenarioOwlCorrect.jpg"), new ImageIcon(
				"src/media/ScenarioOwlIncorrect.jpg"));
		scenario7 = new QuizScenario(new ImageIcon("src/media/Forest.jpeg"), 
				new ImageIcon("src/media/Forestcorrect.jpeg"),
				new ImageIcon("src/media/Forestincorrect.jpeg"));
		scenario8 = new QuizScenario(new ImageIcon("src/media/Maze.jpeg"), 
				new ImageIcon("src/media/Mazecorrect.jpeg"),
				new ImageIcon("src/media/Mazeincorrect.jpeg"));
		
		setScenario();
	}
	
	/**
	 * Metoden lägger till Question objekten till LinkedList (questionList)
	 * FRÅN DATABASEN
	 */
	public void setQuestion() {
		System.out.println("Game: setQuestions");
		LinkedList<Question> list = new LinkedList<Question>(dbKlass.returnQuestions());
		System.out.println(list.size()+ "storlek");
		for(int i = 0; i < list.size();i++){
		questionlist.add((Question) list.get(i));
		}
		
	}

	/**
	 * 
	 * @param index
	 *            position vars fråga skall hämtas
	 * @return det Question objekt vars position efterfrågas
	 */
	public Question getQuestion(int index) {
		return questionlist.get(index);
	}

	/**
	 * Metoden lägger in scenarion i LinkedList (sceanriolist)
	 */
	public void setScenario() {
		scenariolist.add(scenario);
		scenariolist.add(scenario1);
		scenariolist.add(scenario2);
		scenariolist.add(scenario3);
		scenariolist.add(scenario4);
		scenariolist.add(scenario5);
		scenariolist.add(scenario6);
		scenariolist.add(scenario7);
		scenariolist.add(scenario8);
	}

	/**
	 * 
	 * @param index
	 *            position vars scenario skall hämtas
	 * @return det QuizScenario objekt vars position efterfrågas
	 */
	public QuizScenario getScenario(int index) {
		return scenariolist.get(index);
	}

	/**
	 * 
	 * @return antal element i scenariolist
	 */
	public int getScenarioListSize() {
		return scenariolist.size();
	}
}
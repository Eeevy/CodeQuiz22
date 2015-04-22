package CodeQuizServer;

import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.ImageIcon;

import codequiz.Question;

/**
 * Klassen består av ett objekt som innehåller samtliga frågor och scenarion för
 * ett spel som sedan skickas från server till klient vid spelets start.
 * 
 * Frågorna skall i en senare version hämtas från databasen och ett 30 tal slumpas innan
 * de skickas till klient. (I den ordningen om dessa inte kan slumpas från databasen)
 */
public class Game implements Serializable {

	private LinkedList<Question> questionlist = new LinkedList<Question>();
	private LinkedList<QuizScenario> scenariolist = new LinkedList<QuizScenario>();
	private Question question1, question2, question3, question4, question5,
			question6, question7, question8, question9;
	private QuizScenario scenario, scenario1, scenario2, scenario3, scenario4,
			scenario5;

	/**
	 * Konstruerar frågor och scenarion
	 */
	public Game() {
		System.out.println("Game: Konstruktor");
		createQuestions();
		createScenario();
	}

	/**
	 * Metoden skapar Question objekt som sedan läggs in i linkedList
	 * (questionList)
	 */
	public void createQuestions() {
		System.out.println("Game: createQuestions()");
		question1 = new Question(
				"Vilket av följande påståenden är korrekt?",
				"<html>Ett subsystem är en del av ett system <br> som kan fungera som ett eget system.</html>",
				"Ett subsystem kan inte bestå av andra subsystem",
				"Vattenfallsmodellen är en agil modell.",
				"Man måste använda ett informationssystem i lösningen som byggs medvattenfallsmodellen",
				"<html>Ett subsystem är en del av ett system <br> som kan fungera som ett eget system.</html>",
				1);
		question2 = new Question(
				"Vilket av följande påståenden om objekt är korrekt?",
				"Alla objekt representerar abstraktioner av något konkret.",
				"Alla objekt har ett tillstånd.",
				"Alla objekt måste ha någon operation som utför en matematisk beräkning",
				"Inkapsling betyder att ett attribut inte kan ändra värde efter det att objektet skapats.",
				"Alla objekt har ett tillstånd.", 2);
		question3 = new Question("Vilket påstående om Java är felaktigt?",
				"Java är ett objektorienterat programspråk.",
				"Java kallades ursprungligen för ”D” och senare ”Oak”.",
				"Java utvecklades av företaget Sun Microsystems.",
				"Java konstruerades under 80-talets senare hälft.",
				"Java konstruerades under 80-talets senare hälft.", 4);
		question4 = new Question("Vilket påstående är korrekt?",
				"Ett Font-objekt beskriver färgen på text.",
				"Man lyssnar efter knapp-klick med en ButtonListener.",
				"JPanel är ett interface som kan implementeras.",
				"En JFrame är ett fönster.", "En JFrame är ett fönster.", 4);
		question5 = new Question(
				"Vilket påstående är korrekt om modeller?",
				"En modell kan inte användas för simuleringar.",
				"En modell måste kunna sammanfattas i ett enda diagram.",
				"En användbar modell måste erbjuda rätt detaljdjup.",
				"En modell är inte enklare/snabbare att bygga än det verkliga systemet.",
				"En användbar modell måste erbjuda rätt detaljdjup.", 3);
		question6 = new Question(
				"Vilket påstående nedan är korrekt?",
				"Man kan inte använda pseudokod för att testa om en algoritm fungerar som man tänkt.",
				"I pseudokod är det irrelevant att vara konsekvent med hur man skriver.",
				"Algoritmer går att återanvända mellan olika program.",
				"En algoritm är unik för just det programspråk som man implementerar algoritmen i.",
				"Algoritmer går att återanvända mellan olika program.", 3);
		question7 = new Question(
				"Vilket påstående är korrekt för Java?",
				"Programmet javac.exe kan översätta källkodsfiler till bytekodsfiler.",
				"Kompilatorn kan exekvera class-filer.",
				"Java Virtual Machine används för att editera källkodsfiler.",
				"Programmet java.exe används för kontroll av indentering i källkoden.",
				"Programmet javac.exe kan översätta källkodsfiler till bytekodsfiler.",
				1);
		question8 = new Question(
				"Vilket av följande påståenden är korrekt?",
				"Klassen Random kan användas då man behöver slumptal i ett program.",
				"Ett objekt av typen String måste innehålla minst ett tecken.",
				"Med metoden Double.parseDouble kan man avrunda ett flyttal till två decimaler.",
				"Med metoden Math.max kan man erhålla det minsta tänkbara heltalet.",
				"Klassen Random kan användas då man behöver slumptal i ett program.",
				1);
		question9 = new Question(
				"Vilket påstående är korrekt?",
				"Om klasserna A och B är associerade med en aggregation där A är helheten så kan ett objekt av klassen B som mest vara associerat till ett objekt av klassen A.",
				"En klass kan inte associera till sig själv.",
				"Det kan endast finnas en associationstyp mellan två klasser.",
				"Om navigeringsriktning är utsatt så antar man att klasserna bara kan skicka meddelanden i associationens riktning.",
				"Om navigeringsriktning är utsatt så antar man att klasserna bara kan skicka meddelanden i associationens riktning.",
				4);

		setQuestion();
	}

	/**
	 * Metoden skapar scenarion, bestående av tre bilder vardera; bild vid
	 * fråga, bild vid rätt svar, bild vid fel svar
	 */
	public void createScenario() {
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

		setScenario();
	}

	/**
	 * Metoden lägger till Question objekten till LinkedList (questionList)
	 */
	public void setQuestion() {
		questionlist.add(question1);
		questionlist.add(question2);
		questionlist.add(question3);
		questionlist.add(question4);
		questionlist.add(question5);
		questionlist.add(question6);
		questionlist.add(question7);
		questionlist.add(question8);
		questionlist.add(question9);
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
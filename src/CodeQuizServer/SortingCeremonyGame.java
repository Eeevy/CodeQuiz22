package CodeQuizServer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Random;

import codequiz.SortingQuestion;

/**
 * Hanterar frågor till sorteringsceremonin.
 * 
 * @author Evelyn
 *
 */
public class SortingCeremonyGame implements Serializable {
	private Database database;
	private LinkedList<SortingQuestion> sortingQuestionList = new LinkedList<SortingQuestion>();
//	private SortingQuestion sortingQuestion1;

	public SortingCeremonyGame() {
		System.out.println("SortingCeremonyGame: Konstruktor");
		database = new Database();
		database.getSortingQuestionDB();
		setSortingQuestion();
		// createSortingQuestions();
	}

	// public void createSortingQuestions() {
	// System.out.println("SortingCeremonyGame: createSortingCeremonyQuestions()");
	// sortingQuestion1 = new SortingQuestion(
	// 1,
	// "Vilken färg föredrar du?",
	// "Blå",
	// "Röd",
	// "Grön",
	// "Gul",
	// "Blå",
	// "Röd",
	// "Grön",
	// "Gul"
	// );
	// setSortingQuestion();
	// }

	public void setSortingQuestion() {
		LinkedList<SortingQuestion> sortingList = new LinkedList<SortingQuestion>(
				database.returnSortingQuestions());
		System.out.println(sortingList.size() + "storlek");
		for (int i = 0; i < sortingList.size(); i++) {
			sortingQuestionList.add(sortingList.get(i));
		}
	}

	public SortingQuestion getSortingQuestion(int index) {
		Random rand = new Random();
		int random = rand.nextInt(sortingQuestionList.size());
		return sortingQuestionList.get(random);
	}
}
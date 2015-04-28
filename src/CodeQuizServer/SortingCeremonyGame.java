package CodeQuizServer;

import java.io.Serializable;
import java.util.LinkedList;

import codequiz.SortingQuestion;

/**
 * Hanterar frågor till sorteringsceremonin.
 * 
 * @author Evelyn
 *
 */
public class SortingCeremonyGame implements Serializable {
	
	private LinkedList<SortingQuestion> sortingQuestionList = new LinkedList<SortingQuestion>();
	private SortingQuestion sortingQuestion1;
	
	public SortingCeremonyGame() {
		System.out.println("SortingCeremonyGame: Konstruktor");
		createSortingQuestions();
	}
	
	public void createSortingQuestions() {
		System.out.println("SortingCeremonyGame: createSortingCeremonyQuestions()");
		sortingQuestion1 = new SortingQuestion(
		1, 
		"Vilken färg föredrar du?",
		"Blå",
		"Röd",
		"Grön",
		"Gul",
		"Blå",
		"Röd",
		"Grön",
		"Gul"
		);
		setSortingQuestion();
	}
	
	public void setSortingQuestion() {
		sortingQuestionList.add(sortingQuestion1);
	}
	
	public SortingQuestion getSortingQuestion(int index) {
		return sortingQuestionList.get(index);
	}
}
package codequiz;

import java.io.Serializable;

public class SortingQuestion implements Serializable {
	private int ID;
	private String question;
	private String answer1;
	private String answer2;
	private String answer3;
	private String answer4;
	private String answerRavenclaw;
	private String answerGryffindor;
	private String answerSlytherin;
	private String answerHufflepuff;

	public SortingQuestion(int inID, String inQuestion, String inAnswer1,
			String inAnswer2, String inAnswer3, String inAnswer4,
			String inAnswerRavenclaw, String inAnswerGryffindor,
			String inAnswerSlytherin, String inAnswerHufflepuff) {
		this.ID = inID;
		this.question = inQuestion;
		this.answer1 = inAnswer1;
		this.answer2 = inAnswer2;
		this.answer3 = inAnswer3;
		this.answer4 = inAnswer4;
		this.answerRavenclaw = inAnswerRavenclaw;
		this.answerGryffindor = inAnswerGryffindor;
		this.answerSlytherin = inAnswerSlytherin;
		this.answerHufflepuff = inAnswerHufflepuff;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer1() {
		return answer1;
	}

	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}

	public String getAnswer2() {
		return answer2;
	}

	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}

	public String getAnswer3() {
		return answer3;
	}

	public void setAnswer3(String answer3) {
		this.answer3 = answer3;
	}

	public String getAnswer4() {
		return answer4;
	}

	public void setAnswer4(String answer4) {
		this.answer4 = answer4;
	}

	public void setRavenclawAnswer(String answerRavenclaw) {
		this.answerRavenclaw = answerRavenclaw;
	}
	
	public String getRavenclawAnswer() {
		return answerRavenclaw;
	}
	
	public void setGryffindorAnswer(String answerGryffindor) {
		this.answerGryffindor = answerGryffindor;
	}

	public String getGryffindorAnswer() {
		return answerGryffindor;
	}

	public void setSlytherinAnswer(String answerSlytherin) {
		this.answerSlytherin = answerSlytherin;
	}
	
	public String getSlytherinAnswer() {
		return answerSlytherin;
	}
	
	public void setHufflepuffAnswer(String answerHufflepuff) {
		this.answerHufflepuff = answerHufflepuff;
	}

	public String getHufflepuffAnswer() {
		return answerHufflepuff;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
}
package codequiz;

import java.io.Serializable;

import javax.swing.*;
/**
 * Klassen lagrar en bossfråga. 
 * @author gustavbodestad
 *
 */
public class BossQuestion implements Serializable {
	private String codeEx;
	private String answer1, answer2, answer3, answer4, correctAnswer;
	
	public BossQuestion(String pic, String a1, String a2, String a3, String a4, String cAnswer){
		codeEx = pic;
		answer1 = a1;
		answer2 = a2;
		answer3 = a3;
		answer4 = a4;
		correctAnswer = cAnswer;

	}

	public String getCodeEx() {
		return codeEx;
	}

	public void setCodeEx(String picUrl) {
		this.codeEx = picUrl;
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

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

}

package codequiz;

import java.io.Serializable;
 /**
  * Klassen består av en fråga med fyra svarsalternativ
  *
  */
public class Question implements Serializable {
    
    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctanswer;
    private int ID;
      
    /**
     * Konstruerar en fråga
     * @param inQuestion - fråga
     * @param inAnswer1 - svarsalternativ 1
     * @param inAnswer2 - svarsalternativ 2
     * @param inAnswer3 - svarsalternativ 3
     * @param inAnswer4 - svarsalternativ 
     * @param correctAnswer - rätt svar
     * @param inID - frågans ID (unikt)
     */
    public Question(int id,String inQuestion, String inAnswer1, String inAnswer2, String inAnswer3, String inAnswer4, 
            String correctAnswer, int level) {
          
        this.question = inQuestion;
        this.answer1 = inAnswer1;
        this.answer2 = inAnswer2;
        this.answer3 = inAnswer3;
        this.answer4 = inAnswer4;
        this.correctanswer = correctAnswer;
        this.ID = id;
    }
  /**
   * 
   * @return - fråga
   */
    public String getQuestion() {
        return question;
    }
  /**
   * Metoden initierar Question (fråga)
   * @param question - fråga
   */
    public void setQuestion(String question) {
        this.question = question;
    }
  /**
   * 
   * @return - svarsalternativ 1
   */
    public String getAnswer1() {
        return answer1;
    }
  /**
   * 
   * @param -initierar svarsalternativ 1
   */
    public void setAnswer1(String answer1) {
        this.answer1 = answer1;
    }
  /**
   * 
   * @return - svarsalternativ 2
   */
    public String getAnswer2() {
        return answer2;
    }
  /**
   * 
   * @param answer2 - initierar svarsalternativ 2
   */
    public void setAnswer2(String answer2) {
        this.answer2 = answer2;
    }
  /**
   * 
   * @return - svarsalternativ 3
   */
    public String getAnswer3() {
        return answer3;
    }
  /**
   * 
   * @param answer3 - initierar svarsalternativ 3
   */
    public void setAnswer3(String answer3) {
        this.answer3 = answer3;
    }
  /**
   * 
   * @return - svarsalternativ 4
   */
    public String getAnswer4() {
        return answer4;
    }
  /**
   * 
   * @param answer4 - initierar svarsalternativ 4
   */
    public void setAnswer4(String answer4) {
        this.answer4 = answer4;
    }
  /**
   * 
   * @return - rätt svar
   */
    public String getCorrectanswer() {
        return correctanswer;
    }
  /**
   * 
   * @param correctanswer - initierar rätt svar
   */
    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }
  /**
   * 
   * @return - frågans ID
   */
    public int getID() {
        return ID;
    }
  /**
   * 
   * @param iD - initierar frågans ID
   */
    public void setID(int iD) {
        ID = iD;
    }
}
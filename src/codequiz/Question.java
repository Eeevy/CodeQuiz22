package codequiz;
  
public class Question {

    private String question;
    private String answer1;
    private String answer2;
    private String answer3;
    private String answer4;
    private String correctanswer;
    private int ID;
      
    public Question(String inQuestion, String inAnswer1, String inAnswer2, String inAnswer3, String inAnswer4, 
            String inCorrectAnswer, int inID) {
          
        this.question = inQuestion;
        this.answer1 = inAnswer1;
        this.answer2 = inAnswer2;
        this.answer3 = inAnswer3;
        this.answer4 = inAnswer4;
        this.correctanswer = inCorrectAnswer;//Emma was here
        this.ID = inID;
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
  
    public String getCorrectanswer() {
        return correctanswer;
    }
  
    public void setCorrectanswer(String correctanswer) {
        this.correctanswer = correctanswer;
    }
  
    public int getID() {
        return ID;
    }
  
    public void setID(int iD) {
        ID = iD;
    }
}
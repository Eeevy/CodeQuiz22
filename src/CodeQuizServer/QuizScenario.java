package CodeQuizServer;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class QuizScenario implements Serializable {
	
	private ImageIcon storyPic;
	private ImageIcon correctPic;
	private ImageIcon incorrectPic;
	
	
	public QuizScenario(ImageIcon storyPic, ImageIcon correctPic, ImageIcon incorrectPic) {
		this.storyPic = storyPic;
		this.correctPic = correctPic;
		this.incorrectPic = incorrectPic;
	}
	
	public ImageIcon getStoryPic() {
		return storyPic;
	}
	
	public ImageIcon getCorrectPic() {
		return correctPic;
	}
	
	public ImageIcon getIncorrectPic() {
		return incorrectPic;
	}
}
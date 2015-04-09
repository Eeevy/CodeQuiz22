package codequiz;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class QuizScenario implements Serializable {
	
	private ImageIcon storyPic;
	private ImageIcon correctPic;
	private ImageIcon incorrectPic;
	
	public QuizScenario(ImageIcon storyPic, ImageIcon correctPic, ImageIcon incorrectPic) {
		
	}
	
	public ImageIcon getStoryPic(int index) {
		return storyPic;
	}
	
	public ImageIcon getCorrectPic(int index) {
		return correctPic;
	}
	
	public ImageIcon getIncorrectPic(int index) {
		return incorrectPic;
	}
}
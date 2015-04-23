package CodeQuizServer;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.ImageIcon;

/**
 * Klassen består av ett scenario - objekt med tre bilder;
 * bild vid fråga, bild vid rätt svar och bild vid fel svar
 *
 */
public class QuizScenario implements Serializable {
	
	private ImageIcon storyPic;
	private ImageIcon correctPic;
	private ImageIcon incorrectPic;
	
	/**
	 * Konstruerar ett scenario - objekt med tre bilder
	 * @param storyPic - bild med fråga
	 * @param correctPic - bild vid rätt svar
	 * @param incorrectPic - bild vid fel svar
	 */
	public QuizScenario(ImageIcon storyPic, ImageIcon correctPic, ImageIcon incorrectPic) {
		this.storyPic = storyPic;
		this.correctPic = correctPic;
		this.incorrectPic = incorrectPic;
	}
	/**
	 * 
	 * @return - bild med fråga
	 */
	public ImageIcon getStoryPic() {
		return storyPic;
	}
	/**
	 * 
	 * @return - bild vid rätt svar
	 */
	public ImageIcon getCorrectPic() {
		return correctPic;
	}
	/**
	 * 
	 * @return - bild vid fel svar
	 */
	public ImageIcon getIncorrectPic() {
		return incorrectPic;
	}
}
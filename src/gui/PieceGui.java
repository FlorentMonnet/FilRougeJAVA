package gui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import nutsAndBolts.PieceSquareColor;


/**
 * @author francoise.perrin
 * 
 * Cette classe permet de donner une image aux pi�ces
 *
 */

public class PieceGui extends ImageView implements CheckersPieceGui {
	
	@Override
	public void promote(Image image) {
		
		super.setImage(image);
		
	}

	public PieceGui(Image image) {
		super();
		super.setImage(image);
	}
	
	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {

		// ToDo Atelier 2, utile pour Atelier 4
		
		return false; // � changer 
	}
	
}
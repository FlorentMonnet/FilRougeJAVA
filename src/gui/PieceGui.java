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
	
	private PieceSquareColor color;
	@Override
	public void promote(Image image) {
		
		super.setImage(image);
		
	}

	public PieceGui(Image image, PieceSquareColor color) {
		super();
		super.setImage(image);
		this.color = color;
	}
	
	public PieceSquareColor getColor() {
		return color;
	}

	public void setColor(PieceSquareColor color) {
		this.color = color;
	}

	@Override
	public boolean hasSameColorAsGamer(PieceSquareColor gamerColor) {

		return this.color == gamerColor;
	}
	
}
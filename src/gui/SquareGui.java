package gui;

import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoiseperrin
 * 
 * Classe d'affichage des carr�s du damier
 * leur couleur est initialis� par les couleurs par d�faut du jeu
 *
 */
class SquareGui extends BorderPane implements CheckersSquareGui {

	/**
	 *Retourne l'indice du carr� sur la grille (N� de 0 � 99)
	 */
	@Override
	public int getSquareCoord() {
		int index = -1;
		Pane parent = (Pane) this.getParent();
		index = parent.getChildren().indexOf(this);
		return index;
	}
	
	public SquareGui()
	{
		super();
	}
	
	public SquareGui(Border border,Background background) {
		super();
		super.setBorder(border);
		super.setBackground(background);
	}
	
}

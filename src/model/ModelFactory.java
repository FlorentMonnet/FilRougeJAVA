package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

public class ModelFactory {

	public static ArrayList<AbstractPieceModel> createPieceModelCollection() {
		
		ArrayList<AbstractPieceModel> pieces = new ArrayList<AbstractPieceModel>();

		// Cr�ation des pion blancs et ajout dans la collection de pi�ces
		for ( Coord coord : ModelConfig.WHITE_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.WHITE));
		}

		// Cr�ation des pions noirs et ajout dans la collection de pi�ces
		for ( Coord coord : ModelConfig.BLACK_PIECE_COORDS){
			pieces.add(new PawnModel(coord, PieceSquareColor.BLACK));
		}
		return pieces;
	}
}

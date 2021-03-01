package model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ListIterator;

import Comparators.*;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 * 
 * Cete classe fabrique et stocke toutes les PieceModel du Model dans une collection 
 * elle est donc responsable de rechercher et mettre � jour les PieceModel (leur position)
 * En r�alit�, elle d�l�gue � une fabrique le soin de fabriquer les bonnes PieceModel 
 * avec les bonnes coordonn�es
 * 
 * En revanche, elle n'est pas responsable des algorithme m�tiers li�s au d�placement des pi�ces
 * (responsabilit� de la classe Model)
 */
public class ModelImplementor{

	// la collection de pi�ces en jeu - m�lange noires et blanches
	private ArrayList<AbstractPieceModel> pieces;	

	public ModelImplementor() {
		super();

		pieces = ModelFactory.createPieceModelCollection();
	}

	public PieceSquareColor getPieceColor(Coord coord) {
		PieceSquareColor color = null;
		PieceModel pieceTofind = findPiece(coord);
		if(pieceTofind != null)
		{
			color = findPiece(coord).getPieceColor();
		}
		return color;
	}

	public boolean isPiecehere(Coord coord) {
		boolean isPiecehere = false;
		if(findPiece(coord)!=null)
		{
			isPiecehere = true;
		}
		return isPiecehere;
	}

	public boolean isMovePieceOk(Coord initCoord, Coord targetCoord, boolean isPieceToTake) {
	    boolean isMovePieceOk = false;
        PieceModel pieceModel = findPiece(initCoord);
        if(pieceModel != null) {
        	isMovePieceOk = pieceModel.isMoveOk(targetCoord, isPieceToTake);
        }
        return isMovePieceOk;
	}


	public boolean movePiece(Coord initCoord, Coord targetCoord) {

		boolean isMovePieceDone = false;
		PieceModel pieceTofind = findPiece(initCoord);
		if(pieceTofind != null)
		{
			pieceTofind.move(targetCoord);
			isMovePieceDone = true;
		}
		
		return isMovePieceDone;
	}

	public void removePiece(Coord pieceToTakeCoord) {
		PieceModel pieceTofind = findPiece(pieceToTakeCoord);
		if(pieceTofind != null)
		{
			this.pieces.remove(pieceTofind);
		}
	}
	
	private void createQueen(Coord toPromoteCoord, PieceSquareColor color) {
		AbstractPieceModel pieceModel = new QueenModel(toPromoteCoord,color);
		this.pieces.add(pieceModel);
	}
	
	public List<Coord> getCoordsOnItinerary(Coord initCoord, Coord targetCoord) {
		List<Coord> coordsOnItinerary = null;
		PieceModel pieceTofind = findPiece(initCoord);
		if(pieceTofind != null)
		{
			coordsOnItinerary = pieceTofind.getCoordsOnItinerary(targetCoord);
		}
		return coordsOnItinerary;
	}

	public boolean isPromotable(Coord toPromoteCoord)
	{
		boolean isPromotable = false;
		PieceModel pieceTofind = findPiece(toPromoteCoord);
		
		if(pieceTofind != null && pieceTofind instanceof Promotable)
		{
			Promotable pawnPromotable = (Promotable) pieceTofind;
			isPromotable = pawnPromotable.isPromotable();
		}
		return isPromotable;
	}
	
	public void promote(Coord toPromoteCoord)
	{
		PieceModel pieceTofind = findPiece(toPromoteCoord);

		this.removePiece(toPromoteCoord);
		this.createQueen(toPromoteCoord,pieceTofind.getPieceColor());
	}
	/**
	 * @param coord
	 * @return la pi�ce qui se trouve aux coordonn�es indiqu�es
	 */
	 public PieceModel findPiece(Coord coord) {
		 
		PieceModel findPiece = null;
		
		for(PieceModel piece : this.pieces)
		{
			if(piece.hasThisCoord(coord))
			{
				findPiece =  piece;
			}
		}
		
		return findPiece;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * 
	 * La m�thode toStrong() retourne une repr�sentation 
	 * de la liste de pi�ces sous forme d'un tableau 2D
	 * 
	 */
	public String toString() {


		String st = "";
		String[][] damier = new String[ModelConfig.LENGTH][ModelConfig.LENGTH];
		Collections.sort(this.pieces,new ComparatorLinesAndColumns());
		ListIterator<AbstractPieceModel> listIterator = this.pieces.listIterator();
		int count = 1;
		while(listIterator.hasNext())
		{
			System.out.print(listIterator.next()+" ");
			if(count%5 == 0)
			{
				System.out.print("\n");
			}
			count++;
		}
		return "\nDamier du model \n" + st;	
	}


}

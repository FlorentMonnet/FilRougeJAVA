package model;


import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

public class PawnModel implements PieceModel{


	private Coord coord;
	private PieceSquareColor pieceColor;

	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord=new Coord(coord.getColonne(),coord.getLigne());
		this.pieceColor=pieceColor;
	}

	@Override
	public char getColonne() {
		return coord.getColonne();
	}

	@Override
	public int getLigne() {
		return coord.getLigne();
	}

	@Override
	public boolean hasThisCoord(Coord coord) {
		boolean hasThisCoord = false;
		if(this.coord==coord)
		{
			hasThisCoord = true;
		}
		return hasThisCoord;
	}

	@Override
	public PieceSquareColor getPieceColor() {
		PieceSquareColor color = null;
		color=pieceColor;
		return color;	
	}

	@Override
	public void move(Coord coord) {

		this.coord=new Coord(coord.getColonne(),coord.getLigne());

	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean isOk = false;
		if(isPieceToCapture)
		{
			if(targetCoord.getColonne()+2==this.getColonne() && (targetCoord.getLigne()+2==this.getLigne() || targetCoord.getLigne()-2 == this.getLigne()))
			{ 
				isOk = true;
			}
			
			if(targetCoord.getColonne()-2==this.getColonne() && (targetCoord.getLigne()+2==this.getLigne() || targetCoord.getLigne()-2 == this.getLigne()))
			{ 
				isOk = true;
			}
		}
		else
		{
			if(this.pieceColor==PieceSquareColor.WHITE)
			{
				if(targetCoord.getLigne()==this.getLigne()+1 && (targetCoord.getColonne()==this.getColonne()+1 || targetCoord.getColonne()==this.getColonne()-1) )
				{
					isOk = true;
				}
			}
			else
			{
				if(targetCoord.getLigne()==this.getLigne()-1 && (targetCoord.getColonne()==this.getColonne()+1 || targetCoord.getColonne()==this.getColonne()-1) )
				{
					isOk = true;
				}
			}
			
		}
		return isOk;
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>(); 

		// TODO Atelier 2

		return coordsOnItinery;
	}

	@Override
	public String toString() {
		String result;
		if(this.pieceColor==PieceSquareColor.BLACK)
		{
			result="B";
		}
		else
		{
			result="W";
		}
		return result+"["+this.getLigne()+","+this.getColonne()+"]";
	}

	
}


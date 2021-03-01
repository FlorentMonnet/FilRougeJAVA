package Comparators;

import java.util.Comparator;

import model.*;
public class ComparatorPieceModel implements Comparator<AbstractPieceModel>{

	@Override
	public int compare(AbstractPieceModel abstractPieceCompared, AbstractPieceModel abstractPieceToCompare) {
		Coord coord = new Coord(abstractPieceCompared.getColonne(),abstractPieceCompared.getLigne());
		Coord coordToCompare = new Coord(abstractPieceToCompare.getColonne(),abstractPieceToCompare.getLigne());
		return coord.compareTo(coordToCompare);
	}
}

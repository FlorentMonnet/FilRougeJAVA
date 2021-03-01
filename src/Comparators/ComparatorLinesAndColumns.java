package Comparators;

import java.util.Comparator;
import model.AbstractPieceModel;

public class ComparatorLinesAndColumns implements Comparator<AbstractPieceModel>{

	@Override
	public int compare(AbstractPieceModel abstractPieceCompared, AbstractPieceModel abstractPieceToCompare) {
		int diffColonne = abstractPieceCompared.getColonne() - abstractPieceToCompare.getColonne();
		int diffLigne = abstractPieceCompared.getLigne() - abstractPieceToCompare.getLigne();
		return diffColonne == 0 ? diffLigne : diffColonne;
	}

}

package model;

import java.util.LinkedList;
import java.util.List;

import com.sun.prism.paint.Color;

import nutsAndBolts.PieceSquareColor;

public class PawnModel extends AbstractPieceModel implements Promotable {


	public PawnModel(Coord coord, PieceSquareColor pieceColor) {
		super();
		this.coord = new Coord(coord.getColonne(), coord.getLigne());
		this.pieceColor = pieceColor;
	}

	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = false;

		if (!isPieceToCapture) {
			if (this.pieceColor == PieceSquareColor.BLACK) {
				if (this.getLigne() - 1 == targetCoord.getLigne() && (this.getColonne() + 1 == targetCoord.getColonne()
						|| this.getColonne() - 1 == targetCoord.getColonne())) {
					ret = true;
				} else {
					ret = false;
				}
			} else {
				if (this.getLigne() + 1 == targetCoord.getLigne() && (this.getColonne() + 1 == targetCoord.getColonne()
						|| this.getColonne() - 1 == targetCoord.getColonne())) {
					ret = true;
				} else {
					ret = false;
				}
			}
		} else {
			if ((this.getLigne() - 2 == targetCoord.getLigne() || this.getLigne() + 2 == targetCoord.getLigne())
					&& (this.getColonne() + 2 == targetCoord.getColonne()
							|| this.getColonne() - 2 == targetCoord.getColonne())) {
				ret = true;
			} else {
				ret = false;
			}
		}

		return ret;
	}

	@Override
	public String toString() {
		String result;
		if (this.pieceColor == PieceSquareColor.BLACK) {
			result = "B";
		} else {
			result = "W";
		}
		return result + "[" + this.getLigne() + "," + this.getColonne() + "]";
	}

	@Override
	public boolean isPromotable() {
		boolean isPromotable = false;
		if (this.getPieceColor().equals(PieceSquareColor.BLACK) && this.getLigne() == 1) {
			isPromotable = true;
		}

		if (this.getPieceColor().equals(PieceSquareColor.WHITE) && this.getLigne() == Coord.MAX) {
			isPromotable = true;
		}
		return isPromotable;
	}

	@Override
	public void promote() {
		throw new UnsupportedOperationException();
	}

}

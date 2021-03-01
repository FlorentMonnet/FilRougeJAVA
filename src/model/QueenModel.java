package model;


import nutsAndBolts.PieceSquareColor;
/**
 * @author francoiseperrin
 *
 *le mode de d�placement et de prise de la reine est diff�rent de celui du pion
 */
public class QueenModel extends AbstractPieceModel{

	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
			super();
			this.coord = new Coord(coord.getColonne(),coord.getLigne());
			this.pieceColor = pieceColor;
	}
	
	@Override
    public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
        boolean ret = false;
    
        if((Math.abs(this.getLigne() - targetCoord.getLigne()) - Math.abs(this.getColonne() - targetCoord.getColonne())) == 0 ) {
            ret = true;
        }else { 
            ret = false;
        }

        return ret;
    }


	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return " ["+pieceColor.toString().charAt(0) + coord + "]";
	}

}


package model;


import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;
/**
 * @author francoiseperrin
 *
 *le mode de d�placement et de prise de la reine est diff�rent de celui du pion
 */
public class QueenModel implements PieceModel {

	public QueenModel(Coord coord, PieceSquareColor pieceColor) {
			super();
			this.coord=new Coord(coord.getColonne(),coord.getLigne());
			this.pieceColor=pieceColor;
	}

	private Coord coord;
	private PieceSquareColor pieceColor;


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
		if(this.getColonne() == coord.getColonne() && this.getLigne() == coord.getLigne())
		{
			hasThisCoord = true;
		}
		return hasThisCoord;
	}

	@Override
	public void move(Coord coord) {

		this.coord=new Coord(coord.getColonne(),coord.getLigne());

	}

	@Override
	public PieceSquareColor getPieceColor() {
		return pieceColor;	
	}

	@Override
	public List<Coord> getCoordsOnItinerary(Coord targetCoord) {

		List<Coord> coordsOnItinery = new LinkedList<Coord>(); 

        // TODO Atelier 2
        // Colonne et ligne de départ
        int initColonne = this.getColonne();
        int initLigne = this.getLigne();
        
        // Colonne et ligne d'arrivé
        int targetColonne = targetCoord.getColonne();
        int targetLigne = targetCoord.getLigne();
        
        // Soustraction des lignes et colonnes
        int diffColonne = targetColonne - initColonne;
        int diffLigne = targetLigne - initLigne;
        
        // On vérifie si c'est un déplacement vers le haut ou vers le bas
        int signeColonne = (int) Math.signum(diffColonne);
        int signeLigne = (int) Math.signum(diffLigne);
        
        
        // Vérifier si c'est en diagonal donc les lignes doivent être égales aux colonnes
        if(Math.abs(diffColonne) == Math.abs(diffLigne)) {
                
                // Parcours de la différences des colonnes
                for (int i = 1; i < Math.abs(diffColonne); i++) {
                        
                        // On crée et ajoute chaque case parcourus (avec le signe pour savoir si c'est vers le bas ou vers le haut)
                        Coord coord = new Coord((char) (initColonne + i * signeColonne), (char)(initLigne + i * signeLigne));
                        coordsOnItinery.add(coord);
                }
        }
        
        
        return coordsOnItinery;
	}


	
	@Override
	public boolean isMoveOk(Coord targetCoord, boolean isPieceToCapture) {
		boolean ret = true;
		
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


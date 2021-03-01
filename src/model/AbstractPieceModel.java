package model;

import java.util.LinkedList;
import java.util.List;

import nutsAndBolts.PieceSquareColor;

public abstract class AbstractPieceModel implements PieceModel{
	
	protected Coord coord;
	protected PieceSquareColor pieceColor;
	
	public PieceSquareColor getPieceColor() {
		return pieceColor;	
	}
	
	public char getColonne() {
		return coord.getColonne();
	}

	public int getLigne() {
		return coord.getLigne();
	}
	
	public void move(Coord coord) {

		this.coord=new Coord(coord.getColonne(),coord.getLigne());

	}
	
	public boolean hasThisCoord(Coord coord) {
		boolean hasThisCoord = false;
		if(this.getColonne() == coord.getColonne() && this.getLigne() == coord.getLigne())
		{
			hasThisCoord = true;
		}
		return hasThisCoord;
	}
	
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

		// Vérifier si c'est en diagonal donc les lignes doivent être égales aux
		// colonnes
		if (Math.abs(diffColonne) == Math.abs(diffLigne)) {

			// Parcours de la différences des colonnes
			for (int i = 1; i < Math.abs(diffColonne); i++) {

				// On crée et ajoute chaque case parcourus (avec le signe pour savoir si c'est
				// vers le bas ou vers le haut)
				Coord coord = new Coord((char) (initColonne + i * signeColonne), (char) (initLigne + i * signeLigne));
				coordsOnItinery.add(coord);
			}
		}

		return coordsOnItinery;
	}
}

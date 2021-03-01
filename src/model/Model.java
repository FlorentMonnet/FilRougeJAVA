package model;

import java.util.List;

import controller.OutputModelData;
import nutsAndBolts.PieceSquareColor;

/**
 * @author francoise.perrin
 *
 *         Cette classe g�re les aspects m�tiers du jeu de dame ind�pendamment
 *         de toute vue
 * 
 *         Elle d�l�gue � son objet ModelImplementor le stockage des PieceModel
 *         dans une collection
 * 
 *         Les pi�ces sont capables de se d�placer d'une case en diagonale si la
 *         case de destination est vide
 * 
 *         Ne sont pas g�r�s les prises, les rafles, les dames,
 * 
 *         N'est pas g�r� le fait que lorsqu'une prise est possible une autre
 *         pi�ce ne doit pas �tre jou�e
 * 
 */
public class Model implements BoardGame<Coord> {

	private PieceSquareColor currentGamerColor; // couleur du joueur courant

	private ModelImplementor implementor; // Cet objet sait communiquer avec les PieceModel

	public Model() {
		super();
		this.implementor = new ModelImplementor();
		this.currentGamerColor = ModelConfig.BEGIN_COLOR;

		System.out.println(this);
	}

	@Override
	public String toString() {
		return implementor.toString();
	}

	/**
	 * Actions potentielles sur le model : move, capture, promotion pion, rafles
	 */
	@Override
	public OutputModelData<Coord> moveCapturePromote(Coord toMovePieceCoord, Coord targetSquareCoord) {

		OutputModelData<Coord> outputModelData = null;

		boolean isMoveDone = false;
		Coord toCapturePieceCoord = null;
		Coord toPromotePieceCoord = null;
		PieceSquareColor toPromotePieceColor = null;

		// Si la pi�ce est d�pla�able (couleur du joueur courant et case arriv�e
		// disponible)
		if (this.isPieceMoveable(toMovePieceCoord, targetSquareCoord)) {
			// S'il n'existe pas plusieurs pi�ces sur le chemin
			if (this.isThereMaxOnePieceOnItinerary(toMovePieceCoord, targetSquareCoord)) {

				// Recherche coord de l'�ventuelle pi�ce � prendre
				toCapturePieceCoord = this.getToCapturePieceCoord(toMovePieceCoord, targetSquareCoord);
				
				// si le d�placement est l�gal (en diagonale selon algo pion ou dame)
				boolean isPieceToCapture = toCapturePieceCoord != null;
				if (this.isMovePiecePossible(toMovePieceCoord, targetSquareCoord, isPieceToCapture)) {
					// d�placement effectif de la pi�ce
					this.movePiece(toMovePieceCoord, targetSquareCoord);
					isMoveDone = true;
					if(isPieceToCapture)
					{
						// suppression effective de la pi�ce prise
						this.remove(toCapturePieceCoord);
					}
					System.out.println(targetSquareCoord);
					if(this.isPromotable(targetSquareCoord))
					{
						this.promotePiece(targetSquareCoord);
						toPromotePieceCoord = targetSquareCoord;
						toPromotePieceColor = this.currentGamerColor;
					}
					// S'il n'y a pas eu de prise
					// ou si une rafle n'est pas possible alors changement de joueur
					if (true) { // TODO : Test � changer atelier 4
						this.switchGamer();
					}

				}
			}
		}
		System.out.println(this);
		
		// Constitution objet de donn�es avec toutes les infos n�cessaires � la view
		outputModelData = new OutputModelData<Coord>(isMoveDone, toCapturePieceCoord, toPromotePieceCoord,
				toPromotePieceColor);

		return outputModelData;

	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true si la PieceModel � d�placer est de la couleur du joueur courant
	 *         et que les coordonn�es d'arriv�es soient dans les limites du tableau
	 *         et qu'il n'y ait pas de pi�ce sur la case d'arriv�e
	 */
	private boolean isPieceMoveable(Coord toMovePieceCoord, Coord targetSquareCoord) { // TODO : remettre en "private" apr�s
																				// test unitaires
		boolean bool = false;

		// TODO : � compl�ter atelier 4 pour g�rer les rafles
		bool = this.implementor.isPiecehere(toMovePieceCoord)
				&& this.implementor.getPieceColor(toMovePieceCoord) == this.currentGamerColor
				&& Coord.coordonnees_valides(targetSquareCoord) && !this.implementor.isPiecehere(targetSquareCoord);

		return bool;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return true s'il n'existe qu'1 seule pi�ce � prendre d'une autre couleur sur
	 *         la trajectoire ou pas de pi�ce � prendre
	 */
	private boolean isThereMaxOnePieceOnItinerary(Coord toMovePieceCoord, Coord targetSquareCoord) {
		boolean isThereMaxOnePieceOnItinerary = false;
		int numberOfPieces = 0;
		List<Coord> coordsOnItinerary = this.implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord);
		Coord potentialToCapturePieceCoord = null;
		for (Coord coord : coordsOnItinerary) {
			if (this.implementor.isPiecehere(coord)) {
					numberOfPieces++;
					potentialToCapturePieceCoord = coord;
			}
		}
		
		if (numberOfPieces == 0 || (numberOfPieces == 1 && this.currentGamerColor != this.implementor.getPieceColor(potentialToCapturePieceCoord))){
			isThereMaxOnePieceOnItinerary = true;
		}
		return isThereMaxOnePieceOnItinerary;
	}

	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord
	 * @return les coord de la pi�ce � prendre, null sinon
	 */
	private Coord getToCapturePieceCoord(Coord toMovePieceCoord, Coord targetSquareCoord) {
		Coord toCapturePieceCoord = null;
		List<Coord> coordsOnItinerary = this.implementor.getCoordsOnItinerary(toMovePieceCoord, targetSquareCoord);
		for (Coord coord : coordsOnItinerary) {
			System.out.println(coord);
			if (this.implementor.isPiecehere(coord)) {
				if (this.implementor.getPieceColor(coord) != this.implementor.getPieceColor(toMovePieceCoord)) {
					toCapturePieceCoord = coord;
				}
			}
		}
		System.out.println(toCapturePieceCoord);
		return toCapturePieceCoord;
	}

	/**
	 * @param initCoord
	 * @param targetCoord
	 * @param isPieceToCapture
	 * @return true si le d�placement est l�gal (s'effectue en diagonale, avec ou
	 *         sans prise) La PieceModel qui se trouve aux coordonn�es pass�es en
	 *         param�tre est capable de r�pondre �cette question (par l'interm�diare
	 *         du ModelImplementor)
	 */
	boolean isMovePiecePossible(Coord toMovePieceCoord, Coord targetSquareCoord, boolean isPieceToCapture) { 

		return this.implementor.isMovePieceOk(toMovePieceCoord, targetSquareCoord, isPieceToCapture);
	}

	private boolean isPromotable(Coord toPromoteCoord)
	{
		return this.implementor.isPromotable(toPromoteCoord);
	}
	/**
	 * @param toMovePieceCoord
	 * @param targetSquareCoord D�placement effectif de la PieceModel
	 */
	private void movePiece(Coord toMovePieceCoord, Coord targetSquareCoord) { // TODO : remettre en "private" apr�s test
																		// unitaires
		this.implementor.movePiece(toMovePieceCoord, targetSquareCoord);
	}

	/**
	 * @param toCapturePieceCoord Suppression effective de la pi�ce captur�e
	 */
	private void remove(Coord toCapturePieceCoord) {
		this.implementor.removePiece(toCapturePieceCoord);
	}
	
	private void promotePiece(Coord toPromoteCoord) {
		this.implementor.promote(toPromoteCoord);
	}
	
	private void switchGamer() { // TODO : remettre en "private" apr�s test unitaires
		this.currentGamerColor = (PieceSquareColor.WHITE).equals(this.currentGamerColor) ? PieceSquareColor.BLACK
				: PieceSquareColor.WHITE;

	}

}
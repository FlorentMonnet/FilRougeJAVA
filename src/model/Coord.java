package model;



/**
 * @author francoiseperrin
 *
 * Coordonn�es des PieceModel
 */
public class Coord implements Comparable<Coord>{
	
	private char colonne; 	// ['a'..'j']
	private int ligne;		// [10..1]
	static final int MAX = ModelConfig.LENGTH;	// 10

	public Coord(char colonne, int ligne) {
		super();
		this.colonne = colonne;
		this.ligne = ligne;
	}

	public char getColonne() {
		return colonne;
	}

	public int getLigne() {
		return ligne;
	}


	@Override
	public String toString() {
		return "["+ligne + "," + colonne + "]";
	}


	/**
	 * @param coord
	 * @return true si 'a' <= col < 'a'+MAX et 1 < lig <= MAX
	 */
	public static boolean coordonnees_valides(Coord coord){

		boolean ret = false;
		
		if(coord.ligne<=MAX && coord.ligne>=1 && coord.colonne<'a'+MAX && coord.colonne>='a')
		{
			return true;
		}
		return ret;
	}

	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 * 
	 * La m�thode compareTo() indique comment comparer un objet � l'objet courant
	 * selon l'ordre dit naturel
	 * Dans cet application, nous d�cidons que l'ordre naturel est celui 
	 * correspondant au N� de la case d'un tableau 2D repr�sent� par la Coord
	 * ainsi le N� 1 correspond � la Coord ['a', 10], le N� 100 correspond � la Coord ['j', 1]  
	 */
	@Override
	public int compareTo(Coord o) {
		
		return this.hashCode()-o.hashCode() ;
	}


	@Override
	public int hashCode() {
		int result = colonne-'a'+1 + (10-ligne)*10;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coord other = (Coord) obj;
		if (colonne != other.colonne)
			return false;
		if (ligne != other.ligne)
			return false;
		return true;
	}


}

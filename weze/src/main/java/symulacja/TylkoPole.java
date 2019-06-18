package symulacja;

/**
 * Klasa przechowuj�ca wsp�rz�dne pustego pola mapy symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class TylkoPole implements WspI {
	private int x;
	private int y;

	/**
	 * Stwarza nowe puste pole.
	 * 
	 * @param x - wsp�rz�dna x pustego pola
	 * @param y - wsp�rz�dna y pustego pola
	 */
	public TylkoPole(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Zwraca wsp�rz�dne x i y pustego pola.
	 * 
	 * @return tablica przechowuj�ca wsp�rz�dne x i y pustego pola
	 */
	@Override
	public int[] getWazneWsp() {
		int[] a = { x, y };
		return a;
	}
}

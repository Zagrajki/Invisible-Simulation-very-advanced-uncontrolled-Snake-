package symulacja;

/**
 * Klasa przechowuj¹ca wspó³rzêdne pustego pola mapy symulacji.
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
	 * @param x - wspó³rzêdna x pustego pola
	 * @param y - wspó³rzêdna y pustego pola
	 */
	public TylkoPole(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Zwraca wspó³rzêdne x i y pustego pola.
	 * 
	 * @return tablica przechowuj¹ca wspó³rzêdne x i y pustego pola
	 */
	@Override
	public int[] getWazneWsp() {
		int[] a = { x, y };
		return a;
	}
}

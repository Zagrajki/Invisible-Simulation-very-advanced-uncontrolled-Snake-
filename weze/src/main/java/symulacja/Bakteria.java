package symulacja;

/**
 * Klasa reprezentuj¹ca pojedyncz¹ bakteriê.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Bakteria implements BakteriaI {
	private int level;
	private int mojTimer = 0;

	/**
	 * Stwarza now¹ bakteriê.
	 * 
	 * @param i       - okreœla wielkoœæ stworzonej bakterii
	 * @param srednie - okreœla licznik ju¿ prze¿ytych tur dla nowej œredniej
	 *                bakterii
	 * @param duze    - okreœla licznik ju¿ prze¿ytych tur dla nowej du¿ej bakterii
	 */
	public Bakteria(int i, int srednie, int duze) {
		level = i;
		if (level == 2) {
			mojTimer = srednie;
		}
		if (level == 3) {
			mojTimer = duze;
		}
	}

	/**
	 * Zwraca liczbowo wyra¿on¹ wielkoœæ bakterii.
	 * 
	 * @return liczbowo wyra¿ona wielkoœæ bakterii
	 */
	@Override
	public int getLevel() {
		return level;
	}

	/**
	 * Zwiêksza wielkoœæ bakterii.
	 */
	@Override
	public void zwiekszLevel() {
		level++;
	}

	/**
	 * Zwraca liczbê tur prze¿ytych tur bakterii.
	 * 
	 * @return liczbowo wyra¿ona wielkoœæ bakterii
	 */
	@Override
	public int getMojTimer() {
		return mojTimer;
	}

	/**
	 * Zwiêksza liczbê prze¿ytych tur bakterii.
	 */
	@Override
	public void zwiekszMojTimer() {
		mojTimer++;
	}
}
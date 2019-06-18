package symulacja;

/**
 * Klasa reprezentuj�ca pojedyncz� bakteri�.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Bakteria implements BakteriaI {
	private int level;
	private int mojTimer = 0;

	/**
	 * Stwarza now� bakteri�.
	 * 
	 * @param i       - okre�la wielko�� stworzonej bakterii
	 * @param srednie - okre�la licznik ju� prze�ytych tur dla nowej �redniej
	 *                bakterii
	 * @param duze    - okre�la licznik ju� prze�ytych tur dla nowej du�ej bakterii
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
	 * Zwraca liczbowo wyra�on� wielko�� bakterii.
	 * 
	 * @return liczbowo wyra�ona wielko�� bakterii
	 */
	@Override
	public int getLevel() {
		return level;
	}

	/**
	 * Zwi�ksza wielko�� bakterii.
	 */
	@Override
	public void zwiekszLevel() {
		level++;
	}

	/**
	 * Zwraca liczb� tur prze�ytych tur bakterii.
	 * 
	 * @return liczbowo wyra�ona wielko�� bakterii
	 */
	@Override
	public int getMojTimer() {
		return mojTimer;
	}

	/**
	 * Zwi�ksza liczb� prze�ytych tur bakterii.
	 */
	@Override
	public void zwiekszMojTimer() {
		mojTimer++;
	}
}
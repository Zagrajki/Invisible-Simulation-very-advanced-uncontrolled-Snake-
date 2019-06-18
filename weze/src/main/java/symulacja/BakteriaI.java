package symulacja;

/**
 * Interfejs przechowuj¹cy deklaracje metod u¿ywanych przez bakterie.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface BakteriaI {
	/**
	 * Zwraca liczbowo wyra¿on¹ wielkoœæ bakterii.
	 */
	public int getLevel();

	/**
	 * Zwraca liczbê tur prze¿ytych tur bakterii.
	 */
	public int getMojTimer();

	/**
	 * Zwiêksza liczbê prze¿ytych tur bakterii.
	 */
	public void zwiekszMojTimer();

	/**
	 * Zwiêksza wielkoœæ bakterii.
	 */
	public void zwiekszLevel();
}

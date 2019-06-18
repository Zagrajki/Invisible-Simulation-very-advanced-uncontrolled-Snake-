package symulacja;

/**
 * Interfejs przechowuj�cy deklaracje metod u�ywanych przez bakterie.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface BakteriaI {
	/**
	 * Zwraca liczbowo wyra�on� wielko�� bakterii.
	 */
	public int getLevel();

	/**
	 * Zwraca liczb� tur prze�ytych tur bakterii.
	 */
	public int getMojTimer();

	/**
	 * Zwi�ksza liczb� prze�ytych tur bakterii.
	 */
	public void zwiekszMojTimer();

	/**
	 * Zwi�ksza wielko�� bakterii.
	 */
	public void zwiekszLevel();
}

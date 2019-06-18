package symulacja;

import java.util.Map;

/**
 * Interfejs przechowuj�cy deklaracje metod u�ywanych przez Obiekty bior�ce
 * udzia� w symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface ExistingI extends WspI {
	/**
	 * Zwraca "nazw�" Obiektu.
	 * 
	 * @return "nazwa" Obiektu
	 */
	String getNazwa();

	/**
	 * Zwraca paczk� informacji o Obiekcie.
	 * 
	 * @return paczka informacji o Obiekcie
	 */
	Map<informacje, Object> getDane();

	/**
	 * Zwraca zmienn� logiczn� okre�laj�c�, czy Obiekt �yje.
	 * 
	 * @return true - gdy Obiekt �yje; false - gdy Obiekt nie �yje
	 */
	boolean getPrzezycie();

	/**
	 * Zmienn� logiczn� okre�laj�c�, czy Obiekt �yje, ustawia na fa�sz.
	 */
	void niePrzezyj();

	/**
	 * Usuwa Obiekt z listy Obiekt�w bior�cych udzia� w symulacji i z obecnie
	 * zajmowanego przez ten Obiekt pola.
	 */
	void zniszcz();

	/**
	 * Przeprowadza tur� Obiektu.
	 */
	void tura();

	/**
	 * Okre�la reakcje Obiektu na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbi�r otrzymanych informacji, na podstawie kt�rych
	 *                        b�dzie przeprowadzona reakcja
	 */
	void reakcja(Map<informacje, Object> przyslanaPaczka);
}

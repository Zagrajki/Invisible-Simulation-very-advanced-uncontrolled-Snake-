package symulacja;

import java.util.Map;

/**
 * Interfejs przechowuj¹cy deklaracje metod u¿ywanych przez Obiekty bior¹ce
 * udzia³ w symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface ExistingI extends WspI {
	/**
	 * Zwraca "nazwê" Obiektu.
	 * 
	 * @return "nazwa" Obiektu
	 */
	String getNazwa();

	/**
	 * Zwraca paczkê informacji o Obiekcie.
	 * 
	 * @return paczka informacji o Obiekcie
	 */
	Map<informacje, Object> getDane();

	/**
	 * Zwraca zmienn¹ logiczn¹ okreœlaj¹c¹, czy Obiekt ¿yje.
	 * 
	 * @return true - gdy Obiekt ¿yje; false - gdy Obiekt nie ¿yje
	 */
	boolean getPrzezycie();

	/**
	 * Zmienn¹ logiczn¹ okreœlaj¹c¹, czy Obiekt ¿yje, ustawia na fa³sz.
	 */
	void niePrzezyj();

	/**
	 * Usuwa Obiekt z listy Obiektów bior¹cych udzia³ w symulacji i z obecnie
	 * zajmowanego przez ten Obiekt pola.
	 */
	void zniszcz();

	/**
	 * Przeprowadza turê Obiektu.
	 */
	void tura();

	/**
	 * Okreœla reakcje Obiektu na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbiór otrzymanych informacji, na podstawie których
	 *                        bêdzie przeprowadzona reakcja
	 */
	void reakcja(Map<informacje, Object> przyslanaPaczka);
}

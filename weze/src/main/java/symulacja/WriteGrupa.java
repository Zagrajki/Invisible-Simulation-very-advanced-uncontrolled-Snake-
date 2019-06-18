package symulacja;

/**
 * Interfejs daj�cy dost�p klasie zapisuj�cej wyniki do ilo�ci bakterii o
 * poszczeg�lnych levelach w grupie.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface WriteGrupa {
	/**
	 * Zwraca ilo�ci bakterii o kolejnych rozmiarach.
	 * 
	 * @return tablica przechowuj�ca ilo�ci bakterii o kolejnych rozmiarach
	 */
	public int[] getSklad();
}

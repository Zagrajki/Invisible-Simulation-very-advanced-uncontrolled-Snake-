package symulacja;

/**
 * Interfejs daj¹cy dostêp klasie zapisuj¹cej wyniki do iloœci bakterii o
 * poszczególnych levelach w grupie.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface WriteGrupa {
	/**
	 * Zwraca iloœci bakterii o kolejnych rozmiarach.
	 * 
	 * @return tablica przechowuj¹ca iloœci bakterii o kolejnych rozmiarach
	 */
	public int[] getSklad();
}

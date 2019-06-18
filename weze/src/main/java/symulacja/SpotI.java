package symulacja;

import java.util.List;
import java.util.Map;

/**
 * Interfejs daj¹cy dostêp do danych klasy reprezentuj¹cej spoty mapy.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface SpotI {
	/**
	 * Ustawia nosiciela na polu, aby jakiœ Obiekt móg³ go przyj¹æ i mieæ dostêp do
	 * jego wspó³rzêdnych.
	 * 
	 * @param dane - nosiciel zajmuj¹cy pole
	 */
	public void setNosiciel(WspI dane);

	/**
	 * Zwraca nosiciela zajmuj¹cego pole.
	 * 
	 * @return dane - nosiciel zajmuj¹cy pole
	 */
	public WspI getNosiciel();

	/**
	 * Usuwa nosiciela zajmuj¹cego pole.
	 */
	public void usunNosiciel();

	/**
	 * Dodaje obiekt do listy obiektów znajduj¹cych siê na polu mapy symulacji.
	 * 
	 * @param dane - obiekt, który wchodzi na pole mapy symulacji.
	 */
	public void setZajmujacy(ExistingI dane);

	/**
	 * Zwraca listê obiektów zajmuj¹cych pole mapy symulacji.
	 * 
	 * @return lista obiektów zajmuj¹cych pole mapy symulacji
	 */
	public List<ExistingI> getZajmujacy();

	/**
	 * Zwraca ostatnio dodany obiekt z listy obiektów zajmuj¹cych pole mapy
	 * symulacji.
	 * 
	 * @return ostatnio dodany obiekt z listy obiektów zajmuj¹cych pole mapy
	 *         symulacji
	 */
	public ExistingI najnowszyZajmujacy();

	/**
	 * Zwraca zbiór informacji o obiektach znajduj¹cych siê na polu.
	 * 
	 * @return zbiór informacji o obiektach znajduj¹cych siê na polu
	 */
	public Map<informacje, Object> getPaczki();

}

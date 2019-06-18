package symulacja;

import java.util.List;
import java.util.Map;

/**
 * Interfejs daj�cy dost�p do danych klasy reprezentuj�cej spoty mapy.
 * 
 * @author Mati
 * @version 1.0.0
 */
public interface SpotI {
	/**
	 * Ustawia nosiciela na polu, aby jaki� Obiekt m�g� go przyj�� i mie� dost�p do
	 * jego wsp�rz�dnych.
	 * 
	 * @param dane - nosiciel zajmuj�cy pole
	 */
	public void setNosiciel(WspI dane);

	/**
	 * Zwraca nosiciela zajmuj�cego pole.
	 * 
	 * @return dane - nosiciel zajmuj�cy pole
	 */
	public WspI getNosiciel();

	/**
	 * Usuwa nosiciela zajmuj�cego pole.
	 */
	public void usunNosiciel();

	/**
	 * Dodaje obiekt do listy obiekt�w znajduj�cych si� na polu mapy symulacji.
	 * 
	 * @param dane - obiekt, kt�ry wchodzi na pole mapy symulacji.
	 */
	public void setZajmujacy(ExistingI dane);

	/**
	 * Zwraca list� obiekt�w zajmuj�cych pole mapy symulacji.
	 * 
	 * @return lista obiekt�w zajmuj�cych pole mapy symulacji
	 */
	public List<ExistingI> getZajmujacy();

	/**
	 * Zwraca ostatnio dodany obiekt z listy obiekt�w zajmuj�cych pole mapy
	 * symulacji.
	 * 
	 * @return ostatnio dodany obiekt z listy obiekt�w zajmuj�cych pole mapy
	 *         symulacji
	 */
	public ExistingI najnowszyZajmujacy();

	/**
	 * Zwraca zbi�r informacji o obiektach znajduj�cych si� na polu.
	 * 
	 * @return zbi�r informacji o obiektach znajduj�cych si� na polu
	 */
	public Map<informacje, Object> getPaczki();

}

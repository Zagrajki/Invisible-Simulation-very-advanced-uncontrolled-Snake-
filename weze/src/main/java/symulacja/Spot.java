package symulacja;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Klasa reprezentuj¹ca pole mapy, na którym mog¹ byæ Obiekty w symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Spot implements SpotI{
	private List<ExistingI> zajmujacy;
	private Map<informacje, Object> paczki;
	private WspI nosiciel;

	/**
	 * Stwarza nowe pole mapy, na którym mog¹ byæ Obiekty w symulacji.
	 */
	public Spot() {
		zajmujacy = new LinkedList<>();
		paczki = new HashMap<>();
	}

	/**
	 * Ustawia nosiciela na polu, aby jakiœ Obiekt móg³ go przyj¹æ i mieæ dostêp do
	 * jego wspó³rzêdnych.
	 * 
	 * @param dane - nosiciel zajmuj¹cy pole
	 */
	@Override
	public void setNosiciel(WspI dane) {
		nosiciel = dane;
	}

	/**
	 * Zwraca nosiciela zajmuj¹cego pole.
	 * 
	 * @return dane - nosiciel zajmuj¹cy pole
	 */
	@Override
	public WspI getNosiciel() {
		return nosiciel;
	}

	/**
	 * Usuwa nosiciela zajmuj¹cego pole.
	 */
	@Override
	public void usunNosiciel() {
		nosiciel = null;
	}

	/**
	 * Dodaje obiekt do listy obiektów znajduj¹cych siê na polu mapy symulacji.
	 * 
	 * @param dane - obiekt, który wchodzi na pole mapy symulacji.
	 */
	@Override
	public void setZajmujacy(ExistingI dane) {
		zajmujacy.add(dane);
	}

	/**
	 * Zwraca listê obiektów zajmuj¹cych pole mapy symulacji.
	 * 
	 * @return lista obiektów zajmuj¹cych pole mapy symulacji
	 */
	@Override
	public List<ExistingI> getZajmujacy() {
		return zajmujacy;
	}

	/**
	 * Zwraca ostatnio dodany obiekt z listy obiektów zajmuj¹cych pole mapy
	 * symulacji.
	 * 
	 * @return ostatnio dodany obiekt z listy obiektów zajmuj¹cych pole mapy
	 *         symulacji
	 */
	@Override
	public ExistingI najnowszyZajmujacy() {
		return zajmujacy.get(zajmujacy.size() - 1);
	}

	/**
	 * Zwraca zbiór informacji o obiektach znajduj¹cych siê na polu.
	 * 
	 * @return zbiór informacji o obiektach znajduj¹cych siê na polu
	 */
	@Override
	public Map<informacje, Object> getPaczki() {
		return paczki;
	}

}

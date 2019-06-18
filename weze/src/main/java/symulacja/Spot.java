package symulacja;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Klasa reprezentuj�ca pole mapy, na kt�rym mog� by� Obiekty w symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Spot implements SpotI{
	private List<ExistingI> zajmujacy;
	private Map<informacje, Object> paczki;
	private WspI nosiciel;

	/**
	 * Stwarza nowe pole mapy, na kt�rym mog� by� Obiekty w symulacji.
	 */
	public Spot() {
		zajmujacy = new LinkedList<>();
		paczki = new HashMap<>();
	}

	/**
	 * Ustawia nosiciela na polu, aby jaki� Obiekt m�g� go przyj�� i mie� dost�p do
	 * jego wsp�rz�dnych.
	 * 
	 * @param dane - nosiciel zajmuj�cy pole
	 */
	@Override
	public void setNosiciel(WspI dane) {
		nosiciel = dane;
	}

	/**
	 * Zwraca nosiciela zajmuj�cego pole.
	 * 
	 * @return dane - nosiciel zajmuj�cy pole
	 */
	@Override
	public WspI getNosiciel() {
		return nosiciel;
	}

	/**
	 * Usuwa nosiciela zajmuj�cego pole.
	 */
	@Override
	public void usunNosiciel() {
		nosiciel = null;
	}

	/**
	 * Dodaje obiekt do listy obiekt�w znajduj�cych si� na polu mapy symulacji.
	 * 
	 * @param dane - obiekt, kt�ry wchodzi na pole mapy symulacji.
	 */
	@Override
	public void setZajmujacy(ExistingI dane) {
		zajmujacy.add(dane);
	}

	/**
	 * Zwraca list� obiekt�w zajmuj�cych pole mapy symulacji.
	 * 
	 * @return lista obiekt�w zajmuj�cych pole mapy symulacji
	 */
	@Override
	public List<ExistingI> getZajmujacy() {
		return zajmujacy;
	}

	/**
	 * Zwraca ostatnio dodany obiekt z listy obiekt�w zajmuj�cych pole mapy
	 * symulacji.
	 * 
	 * @return ostatnio dodany obiekt z listy obiekt�w zajmuj�cych pole mapy
	 *         symulacji
	 */
	@Override
	public ExistingI najnowszyZajmujacy() {
		return zajmujacy.get(zajmujacy.size() - 1);
	}

	/**
	 * Zwraca zbi�r informacji o obiektach znajduj�cych si� na polu.
	 * 
	 * @return zbi�r informacji o obiektach znajduj�cych si� na polu
	 */
	@Override
	public Map<informacje, Object> getPaczki() {
		return paczki;
	}

}

package symulacja;

import java.util.Map;

/**
 * Klasa reprezentuj¹ca antidotum.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Antidotum extends Obiekt {
	private static int antidotumCzas;

	public static void setAntidotumCzas(int dane) {
		antidotumCzas = dane;
	}

	private int timer;

	/**
	 * Stwarza nowe antidotum na mapie symulacji.
	 */
	public Antidotum() {
		timer = antidotumCzas;
		ustawNazwe();
		losowanieMiejsca();
		Main.getPole(x, y).getZajmujacy().add(this);
		Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
	}

	/**
	 * Przeprowadza turê antidotum.
	 */
	@Override
	public void tura() {
		if (timer == 0 || przezycie == false) {
			if (timer == 0)
				Main.getPole(x, y).getPaczki().remove(informacje.MEDYCZNE);
			zniszcz();
			if (Main.getPole(x, y).getZajmujacy().isEmpty()) {
				Main.getPolePuste().add(Main.getTablicaPolePuste(x, y));
			}
		}
		timer--;
	}

	/**
	 * Okreœla reakcje antidotum na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbiór otrzymanych informacji, na podstawie których
	 *                        bêdzie przeprowadzona reakcja
	 */
	@Override
	public void reakcja(Map<informacje, Object> przyslanaPaczka) {
		if (przezycie) {
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.JEDZACY, false) == true
					|| (boolean) przyslanaPaczka.getOrDefault(informacje.ZARAZAJACY, false) == true) {
				przezycie = false;
			}
		}
	}

	/**
	 * Zbiera informacje o antidotum, które maj¹ byæ przechowywane na polu mapy,
	 * gdzie siê znajduje antidotum.
	 */
	@Override
	public Map<informacje, Object> zbierzPaczka() {
		mojaPaczka.put(informacje.MEDYCZNE, true);
		return mojaPaczka;
	}
}

package symulacja;

import java.util.Map;

/**
 * Klasa reprezentuj¹ca jedzenie.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Pakiet_jedzenia extends Obiekt {
	private static int prawdopodobienstwoSpecjalnosci;

	/**
	 * Ustawia prawdopodobieñstwo wyst¹pienia w jedzeniu cechy specjalnej.
	 * 
	 * @param dane - prawdopodobieñstwo wyst¹pienia w jedzeniu cechy specjalnej
	 */
	public static void setPrawdopodobienstwoSpecjalnosci(int dane) {
		prawdopodobienstwoSpecjalnosci = dane;
	}

	private boolean przyspieszenie = false;
	private boolean zwolnienie = false;
	private boolean ochrona = false;
	private boolean specjalnosc = false;
	private int podstawowe;

	/**
	 * Stwarza nowe jedzenie na mapie symulacji.
	 */
	public Pakiet_jedzenia() {
		if (random.nextInt(prawdopodobienstwoSpecjalnosci) == 0) {
			przyspieszenie = true;
			specjalnosc = true;
		}
		if (random.nextInt(prawdopodobienstwoSpecjalnosci) == 0) {
			zwolnienie = true;
			specjalnosc = true;
		}
		if (przyspieszenie == true && zwolnienie == true) {
			przyspieszenie = false;
			zwolnienie = false;
			specjalnosc = false;
		}
		if (random.nextInt(prawdopodobienstwoSpecjalnosci) == 0) {
			ochrona = true;
			specjalnosc = true;
		}
		podstawowe = (byte) (random.nextInt(3) + 1);
		ustawNazwe();
		losowanieMiejsca();
		Main.getPole(x, y).getZajmujacy().add(this);
		Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
	}

	/**
	 * Przeprowadza turê jedzenia.
	 */
	@Override
	public void tura() {
		if (przezycie == false) {
			zniszcz();
			if (Main.getPolePuste().size() > 0) {
				Main.getPionki().add(new Pakiet_jedzenia());
			}
		}
	}

	/**
	 * Okreœla reakcje jedzenia na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbiór otrzymanych informacji, na podstawie których
	 *                        bêdzie przeprowadzona reakcja
	 */
	@Override
	public void reakcja(Map<informacje, Object> przyslanaPaczka) {
		if (przezycie) {
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.JEDZACY, false) == true) {
				przezycie = false;
			}
		}
	}

	/**
	 * Zbiera informacje o jedzeniu, które maj¹ byæ przechowywane na polu mapy,
	 * gdzie siê znajduje antidotum.
	 */
	@Override
	public Map<informacje, Object> zbierzPaczka() {
		mojaPaczka.put(informacje.ZJADLIWY, true);
		mojaPaczka.put(informacje.PODSTAWOWE, podstawowe);
		mojaPaczka.put(informacje.PRZYSPIESZENIE, przyspieszenie);
		mojaPaczka.put(informacje.ZWOLNIENIE, zwolnienie);
		mojaPaczka.put(informacje.OCHRONA, ochrona);
		mojaPaczka.put(informacje.SPECJALNOSC, specjalnosc);
		return mojaPaczka;
	}
}
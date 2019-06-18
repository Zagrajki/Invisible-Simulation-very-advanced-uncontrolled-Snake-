package symulacja;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Klasa, której zadaniem jest odczytanie danych z pliku konfiguracyjnego
 * symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class ReadData {
	/**
	 * Wyjmuje liczbê bêd¹c¹ na ostatnim miejscu w linijce tekstu.
	 * 
	 * @param linijka - tekst, który zawiera liczbê
	 * @return liczba bêd¹ca na koñcu linijki tekstu
	 * @throws NumberFormatException wyrzucany, kiedy na koñcu tekstu nie znajduje
	 *                               siê liczba
	 */
	public static int WyjmijLiczbe(final String linijka) throws NumberFormatException {
		String numer = linijka.substring(linijka.lastIndexOf(' ') + 1);
		return Integer.parseInt(numer);
	}

	/**
	 * Ustawia wartoœci pól w plikach klasowych symulacji na podstawie danych z
	 * pliku tekstowego.
	 * 
	 * @param in - plik, z którego odczytywane s¹ dane
	 * @throws IOException wyrzucany, kiedy nie ma dostêpu do pliku, z którego maj¹
	 *                     byæ odczytane dane
	 */
	public static void czytaj(BufferedReader in) throws IOException {
		String napis;
		for (int i = 0; i < 18; i++) {
			napis = in.readLine();
			if (!napis.contains(wiersz[i])) {
				throw new RuntimeException("Zmieniono napisy w pliku konfiguracyjnym!");
			}
			dane[i] = WyjmijLiczbe(napis);
			if (dane[i] < 1) {
				throw new RuntimeException(wiersz[i] + " < 1 - Tak nie mo¿e byæ!");
			}
			if (dane[i] > 1000) {
				throw new RuntimeException(wiersz[i] + " > 1000 - Tak nie mo¿e byæ!");
			}
		}

		napis = in.readLine();
		if (!napis.contains(wiersz[18])) {
			throw new RuntimeException("Zmieniono napisy w pliku konfiguracyjnym!");
		}
		dane[18] = WyjmijLiczbe(napis);
		if (dane[18] < 0) {
			throw new RuntimeException(wiersz[18] + " < 0 - Tak nie mo¿e byæ!");
		}
		if (dane[18] > 99) {
			throw new RuntimeException(wiersz[18] + " > 99 - Tak nie mo¿e byæ!");
		}

		for (int i = 19; i < 22; i++) {
			napis = in.readLine();
			if (!napis.contains(wiersz[i])) {
				throw new RuntimeException("Zmieniono napisy w pliku konfiguracyjnym!");
			}
			dane[i] = WyjmijLiczbe(napis);
			if (dane[i] < dane[i - 11]) {
				throw new RuntimeException(wiersz[19] + " < " + dane[i - 11] + " - Tak nie mo¿e byæ!");
			}
			if (dane[i] > 1000) {
				throw new RuntimeException(wiersz[19] + " > 1000 - Tak nie mo¿e byæ!");
			}
		}

		napis = in.readLine();
		if (!napis.contains(wiersz[22])) {
			throw new RuntimeException("Zmieniono napisy w pliku konfiguracyjnym!");
		}
		dane[22] = WyjmijLiczbe(napis);
		if (dane[22] < 0) {
			throw new RuntimeException(wiersz[22] + " < 0 - Tak nie mo¿e byæ!");
		}
		if (dane[22] > dane[1] * dane[2] / 4) {
			throw new RuntimeException(wiersz[22] + " Wiêksze od æwierci sumy iloœci pól planszy - Tak nie mo¿e byæ!");
		}

		napis = in.readLine();
		if (!napis.contains(wiersz[23])) {
			throw new RuntimeException("Zmieniono napisy w pliku konfiguracyjnym!");
		}
		dane[23] = WyjmijLiczbe(napis);
		if (dane[23] < 0) {
			throw new RuntimeException(wiersz[23] + " < 0 - Tak nie mo¿e byæ!");
		}
		if (dane[23] > dane[1] * dane[2] / 2) {
			throw new RuntimeException(wiersz[23] + " Wiêksze od po³owy sumy iloœci pól planszy - Tak nie mo¿e byæ!");
		}
		Main.setTury(dane[0]);
		Main.setDlugoscPlanszy(dane[1]);
		Main.setSzerokoscPlanszy(dane[2]);
		Waz.setCzasSpacjalnosci(dane[3]);
		Pakiet_jedzenia.setPrawdopodobienstwoSpecjalnosci(dane[4]);
		Waz.setCzasOgluszenia(dane[5]);
		Grupa.setCzasSM(dane[6]);
		Grupa.setCzasML(dane[6] + dane[7]);
		Grupa.setMin_S(dane[8]);
		Grupa.setMin_M(dane[9]);
		Grupa.setMin_L(dane[10]);
		zyje.setMinimumJedzeniowychWygranych(dane[11]);
		Waz.setMinimalnyJedzeniowyTlum(dane[12]);
		zyje.setMinimumWygranych(dane[13]);
		Waz.setMinimalnyTlum(dane[14]);
		zyje.setCzasRozmnazania(dane[15]);
		Main.setCzasOdrodzeniaAntidotum(dane[16]);
		Antidotum.setAntidotumCzas(dane[17]);
		Grupa.setPochlanianieWrogiejGrupy(dane[18]);
		Grupa.setMax_S(dane[19]);
		Grupa.setMax_M(dane[20]);
		Grupa.setMax_L(dane[21]);
		Main.setLiczbaWezy(dane[22]);
		Main.setLiczbaGrup(dane[23]);
	}

	private static int[] dane = new int[24];
	private static final String[] wiersz = { "ILOSC TUR SYMULACJI:", "Dlugosc planszy:", "Szerokosc planszy:",
			"Czas dzialania jedzenia specjalnego:", "Rzadkosc wystapienia jedzenia specjalnego:", "Czas ogluszenia:",
			"Czas ewolucji malej bakterii na srednia:", "Czas ewolucji sredniej bakterii na duza:",
			"Minimalna liczba poczatkowa malych bakterii w grupie bakterii:",
			"Minimalna liczba poczatkowa srednich bakterii w grupie bakterii:",
			"Minimalna liczba poczatkowa duzych bakterii w grupie bakterii:",
			"Grupa bakterii na pakiecie jedzenia nie ginie po zjedzeniu przez weza po tylu wygranych:",
			"Waz ginie po rozmnozeniu sie w nim tak licznej grupy bakterii z pakietu jedzenia:",
			"Grupa bakterii nie ginie po zjedzeniu przez weza po tylu wygranych:",
			"Waz ginie po rozmnozeniu sie w nim tak licznej grupy bakterii:", "Czas rozmnazania sie grupy bakterii:",
			"Czas miedzy pojawieniem sie antidotum:", "Czas istnienia antidotum:",
			"Tyle procent bakterii musi zostac w grupie, zeby zostala wchlonieta w sklad drugiej grupy:",
			"Maksymalna liczba poczatkowa malych bakterii w grupie bakterii:",
			"Maksymalna liczba poczatkowa srednich bakterii w grupie bakterii:",
			"Maksymalna liczba poczatkowa duzych bakterii w grupie bakterii:", "Ilosc wezy:", "Ilosc grup bakterii:" };
}
package symulacja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa odpowiadaj¹ca za przeprowadzenie symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Main {
	private static List<ExistingI> pionki;

	/**
	 * Zwraca listê wszystkich Obiektów bior¹cyh udzia³ w symulacji.
	 * 
	 * @return lista wszystkich Obiektów bior¹cyh udzia³ w symulacji
	 */
	public static List<ExistingI> getPionki() {
		return pionki;
	}

	private static int czasOdrodzeniaAntidotum;

	/**
	 * Ustawia iloœæ tur potrzebn¹ na stworzenie nowego antidotum.
	 * 
	 * @param dane - iloœæ tur potrzebna na stworzenie nowego antidotum.
	 */
	public static void setCzasOdrodzeniaAntidotum(int dane) {
		czasOdrodzeniaAntidotum = dane;
	}

	private static int timerAntidotum;

	private static SpotI[][] pole;

	/**
	 * Zwraca pole mapy symulacji o zadanych wspó³rzêdnych.
	 * 
	 * @param x - wspó³rzêdna x wybranego miejsca mapy symulacji
	 * @param y - wspó³rzêdna y wybranego miejsca mapy symulacji
	 * @return konkretne pole mapy symulacji
	 */
	public static SpotI getPole(int x, int y) {
		return pole[x][y];
	}

	private static List<WspI> polePuste;

	/**
	 * Zwraca puste pole z listy przechowuj¹cej puste pola mapy.
	 * 
	 * @param x - liczba okreœlaj¹ca, które puste pole z listy nale¿y zwróciæ
	 * @return puste pole
	 */
	public static WspI getPolePuste(int x) {
		return polePuste.get(x);
	}

	/**
	 * Zwraca listê przechowuj¹c¹ puste pola mapy.
	 * 
	 * @return lista pustych pól
	 */
	public static List<WspI> getPolePuste() {
		return polePuste;
	}

	/**
	 * Usuwa puste pole z listy przechowuj¹cej puste pola mapy.
	 * 
	 * @param numerPola - liczba okreœlaj¹ca, które puste pole z listy nale¿y usun¹æ
	 */
	public static void usunPolePuste(int numerPola) {
		polePuste.remove(numerPola);
	}

	/**
	 * Usuwa puste pole o konkretnych wspó³rzêdnych z listy przechowuj¹cej puste
	 * pola mapy.
	 * 
	 * @param x - wspó³rzêdna x wybranego pustego pola
	 * @param y - wspó³rzêdna y wybranego pustego pola
	 */
	public static void usunKonkretnePolePuste(int x, int y) {
		polePuste.remove(tablicaPolePuste[x][y]);
	}

	private static WspI[][] tablicaPolePuste;

	/**
	 * Zwraca puste pole o konkretnych wspó³rzêdnych z tablicy przechowuj¹cej puste
	 * pola.
	 * 
	 * @param x - wspó³rzêdna x wybranego pustego pola
	 * @param y - wspó³rzêdna y wybranego pustego pola
	 * @return puste pole o konkretnych wspó³rzêdnych
	 */
	public static WspI getTablicaPolePuste(int x, int y) {
		return tablicaPolePuste[x][y];
	}

	private static int liczbaWezy;

	/**
	 * Ustawia liczbê wê¿y do stworzenia.
	 * 
	 * @param dane - liczba wê¿y do stworzenia
	 */
	public static void setLiczbaWezy(int dane) {
		liczbaWezy = dane;
	}

	private static int liczbaGrup;

	/**
	 * Ustawia liczbê grup bakterii do stworzenia.
	 * 
	 * @param dane - liczba grup bakterii do stworzenia
	 */
	public static void setLiczbaGrup(int dane) {
		liczbaGrup = dane;
	}

	private static int dlugoscPlanszy;

	/**
	 * Ustawia d³ugoœæ mapy symulacji.
	 * 
	 * @param dane - d³ugoœæ mapy symulacji
	 */
	public static void setDlugoscPlanszy(int dane) {
		dlugoscPlanszy = dane;
	}

	/**
	 * Zwraca d³ugoœæ mapy symulacji.
	 * 
	 * @return d³ugoœæ mapy symulacji
	 */
	public static int getDlugoscPlanszy() {
		return dlugoscPlanszy;
	}

	private static int szerokoscPlanszy;

	/**
	 * Ustawia szerokoœæ mapy symulacji.
	 * 
	 * @param dane - szerokoœæ mapy symulacji
	 */
	public static void setSzerokoscPlanszy(int dane) {
		szerokoscPlanszy = dane;
	}

	/**
	 * Zwraca szerokoœæ mapy symulacji.
	 * 
	 * @return szerokoœæ mapy symulacji
	 */
	public static int getSzerokoscPlanszy() {
		return szerokoscPlanszy;
	}

	private static int tury;

	/**
	 * Ustawia iloœæ tur symulacji.
	 * 
	 * @param dane - iloœæ tur symulacji
	 */
	public static void setTury(int dane) {
		tury = dane;
	}

	/**
	 * Przeprowadza symulacjê.
	 * 
	 * @param args - parametry, które mo¿na przekazaæ w liœcie poleceñ przy
	 *             uruchomieniu (niewykorzystywane)
	 */
	public static void main(String[] args) {
		try (BufferedReader in = new BufferedReader(
				new FileReader("C:\\Users\\Mati\\eclipse-workspace\\weze\\src\\main\\resources\\Config.txt"))) {
			ReadData.czytaj(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		polePuste = new LinkedList<>();
		pionki = new LinkedList<>();
		pole = new Spot[dlugoscPlanszy][szerokoscPlanszy];
		tablicaPolePuste = new TylkoPole[dlugoscPlanszy][szerokoscPlanszy];
		for (int i = 0; i < dlugoscPlanszy; i++) {
			for (int j = 0; j < szerokoscPlanszy; j++) {
				pole[i][j] = new Spot();
				tablicaPolePuste[i][j] = new TylkoPole(i, j);
				polePuste.add(tablicaPolePuste[i][j]);
			}
		}

		for (int i = 0; i < liczbaWezy; i++) {
			pionki.add(new Waz());
			pionki.add(new Pakiet_jedzenia());
		}
		for (int i = 0; i < liczbaGrup; i++) {
			pionki.add(new Grupa());
		}
		timerAntidotum = 1;
		for (int i = 0; i < tury; i++) {
			if (timerAntidotum % czasOdrodzeniaAntidotum == 0 && polePuste.size() > 0) {
				pionki.add(new Antidotum());
			}
			timerAntidotum++;
			for (int j = pionki.size() - 1; j >= 0; j--) {
				pionki.get(j).tura();
			}
		}
		for (int j = pionki.size() - 1; j >= 0; j--) {
			if (pionki.get(j).getPrzezycie() == false)
				pionki.get(j).zniszcz();
		}
		try (FileWriter in = new FileWriter(
				"C:\\Users\\Mati\\eclipse-workspace\\weze\\src\\main\\java\\symulacja\\Wyniki.csv")) {
			WriteData.zapisz(in, pionki);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package symulacja;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Klasa odpowiadaj�ca za przeprowadzenie symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Main {
	private static List<ExistingI> pionki;

	/**
	 * Zwraca list� wszystkich Obiekt�w bior�cyh udzia� w symulacji.
	 * 
	 * @return lista wszystkich Obiekt�w bior�cyh udzia� w symulacji
	 */
	public static List<ExistingI> getPionki() {
		return pionki;
	}

	private static int czasOdrodzeniaAntidotum;

	/**
	 * Ustawia ilo�� tur potrzebn� na stworzenie nowego antidotum.
	 * 
	 * @param dane - ilo�� tur potrzebna na stworzenie nowego antidotum.
	 */
	public static void setCzasOdrodzeniaAntidotum(int dane) {
		czasOdrodzeniaAntidotum = dane;
	}

	private static int timerAntidotum;

	private static SpotI[][] pole;

	/**
	 * Zwraca pole mapy symulacji o zadanych wsp�rz�dnych.
	 * 
	 * @param x - wsp�rz�dna x wybranego miejsca mapy symulacji
	 * @param y - wsp�rz�dna y wybranego miejsca mapy symulacji
	 * @return konkretne pole mapy symulacji
	 */
	public static SpotI getPole(int x, int y) {
		return pole[x][y];
	}

	private static List<WspI> polePuste;

	/**
	 * Zwraca puste pole z listy przechowuj�cej puste pola mapy.
	 * 
	 * @param x - liczba okre�laj�ca, kt�re puste pole z listy nale�y zwr�ci�
	 * @return puste pole
	 */
	public static WspI getPolePuste(int x) {
		return polePuste.get(x);
	}

	/**
	 * Zwraca list� przechowuj�c� puste pola mapy.
	 * 
	 * @return lista pustych p�l
	 */
	public static List<WspI> getPolePuste() {
		return polePuste;
	}

	/**
	 * Usuwa puste pole z listy przechowuj�cej puste pola mapy.
	 * 
	 * @param numerPola - liczba okre�laj�ca, kt�re puste pole z listy nale�y usun��
	 */
	public static void usunPolePuste(int numerPola) {
		polePuste.remove(numerPola);
	}

	/**
	 * Usuwa puste pole o konkretnych wsp�rz�dnych z listy przechowuj�cej puste
	 * pola mapy.
	 * 
	 * @param x - wsp�rz�dna x wybranego pustego pola
	 * @param y - wsp�rz�dna y wybranego pustego pola
	 */
	public static void usunKonkretnePolePuste(int x, int y) {
		polePuste.remove(tablicaPolePuste[x][y]);
	}

	private static WspI[][] tablicaPolePuste;

	/**
	 * Zwraca puste pole o konkretnych wsp�rz�dnych z tablicy przechowuj�cej puste
	 * pola.
	 * 
	 * @param x - wsp�rz�dna x wybranego pustego pola
	 * @param y - wsp�rz�dna y wybranego pustego pola
	 * @return puste pole o konkretnych wsp�rz�dnych
	 */
	public static WspI getTablicaPolePuste(int x, int y) {
		return tablicaPolePuste[x][y];
	}

	private static int liczbaWezy;

	/**
	 * Ustawia liczb� w�y do stworzenia.
	 * 
	 * @param dane - liczba w�y do stworzenia
	 */
	public static void setLiczbaWezy(int dane) {
		liczbaWezy = dane;
	}

	private static int liczbaGrup;

	/**
	 * Ustawia liczb� grup bakterii do stworzenia.
	 * 
	 * @param dane - liczba grup bakterii do stworzenia
	 */
	public static void setLiczbaGrup(int dane) {
		liczbaGrup = dane;
	}

	private static int dlugoscPlanszy;

	/**
	 * Ustawia d�ugo�� mapy symulacji.
	 * 
	 * @param dane - d�ugo�� mapy symulacji
	 */
	public static void setDlugoscPlanszy(int dane) {
		dlugoscPlanszy = dane;
	}

	/**
	 * Zwraca d�ugo�� mapy symulacji.
	 * 
	 * @return d�ugo�� mapy symulacji
	 */
	public static int getDlugoscPlanszy() {
		return dlugoscPlanszy;
	}

	private static int szerokoscPlanszy;

	/**
	 * Ustawia szeroko�� mapy symulacji.
	 * 
	 * @param dane - szeroko�� mapy symulacji
	 */
	public static void setSzerokoscPlanszy(int dane) {
		szerokoscPlanszy = dane;
	}

	/**
	 * Zwraca szeroko�� mapy symulacji.
	 * 
	 * @return szeroko�� mapy symulacji
	 */
	public static int getSzerokoscPlanszy() {
		return szerokoscPlanszy;
	}

	private static int tury;

	/**
	 * Ustawia ilo�� tur symulacji.
	 * 
	 * @param dane - ilo�� tur symulacji
	 */
	public static void setTury(int dane) {
		tury = dane;
	}

	/**
	 * Przeprowadza symulacj�.
	 * 
	 * @param args - parametry, kt�re mo�na przekaza� w li�cie polece� przy
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

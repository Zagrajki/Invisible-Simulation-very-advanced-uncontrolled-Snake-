package symulacja;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa reprezentuj¹ca cechy wspólne dla ka¿dego obiektu, który siê porusza.
 * 
 * @author Mati
 * @version 1.0.0
 */
public abstract class zyje extends Obiekt {
	protected static int minimumJedzeniowychWygranych;

	/**
	 * Ustawia minimaln¹ liczbê wygranych walk zara¿aj¹cej jednostki znajduj¹cej siê
	 * na jedzeniu potrzebn¹, aby mog³a zaraziæ i prze¿yæ.
	 * 
	 * @param dane - minimalna liczba wygranych walk zara¿aj¹cej jednostki
	 *             znajduj¹cej siê na jedzeniu potrzebna, aby mog³a zaraziæ i
	 *             prze¿yæ
	 */
	public static void setMinimumJedzeniowychWygranych(int dane) {
		minimumJedzeniowychWygranych = dane;
	}

	protected static int minimumWygranych;

	/**
	 * Ustawia minimaln¹ liczbê wygranych walk zara¿aj¹cej jednostki potrzebn¹, aby
	 * mog³a zaraziæ i prze¿yæ.
	 * 
	 * @param dane - minimalna liczba wygranych walk zara¿aj¹cej jednostki
	 *             potrzebna, aby mog³a zaraziæ i prze¿yæ
	 */
	public static void setMinimumWygranych(int dane) {
		minimumWygranych = dane;
	}

	protected static int czasRozmnazania;

	/**
	 * Ustawia iloœæ tur potrzebn¹ do rozmno¿enia jednostki zara¿aj¹cej bêd¹c¹ te¿
	 * d³ugoœci¹ trwania zara¿enia.
	 * 
	 * @param dane - iloœæ tur potrzebna do rozmno¿enia jednostki zara¿aj¹cej bêd¹c¹
	 *             te¿ d³ugoœci¹ trwania zara¿enia
	 */
	public static void setCzasRozmnazania(int dane) {
		czasRozmnazania = dane;
	}

	protected int timerRozmnazania;

	/**
	 * Usuwa informacje charakterystyczne dla danej klasy z pola mapy symulacji,
	 * które jest zajmowane przez obiekt tej klasy.
	 */
	public abstract void usunMojaPaczkeZPola();

	/**
	 * Ustawia nowe wspó³rzêdne obiektu, losowe i zarazem s¹siednie do starych.
	 */
	public void rusz() {
		List<int[]> pulaMozliwosci = new LinkedList<>();
		int[] temp;
		int wylosowane;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i >= 0 && i < Main.getDlugoscPlanszy() && j >= 0 && j < Main.getSzerokoscPlanszy()
						&& !Main.getPole(i, j).getZajmujacy().contains(this)) {
					temp = new int[2];
					temp[0] = i;
					temp[1] = j;

					pulaMozliwosci.add(temp);
				}
			}
		}
		if (pulaMozliwosci.size() == 0) {
			zniszcz();
		} else {
			wylosowane = random.nextInt(pulaMozliwosci.size());
			x = pulaMozliwosci.get(wylosowane)[0];
			y = pulaMozliwosci.get(wylosowane)[1];
			Main.usunKonkretnePolePuste(x, y);
		}
	}

	/**
	 * Przeprowadza interakcjê obiektu z obiektami znajduj¹cymi siê na tym samym
	 * polu mapy symulacji, czyli wywo³uje w nich reakcjê na informacje od tego
	 * obiektu oraz wywo³uje w tym obiekcie reakcjê na informacje ju¿ zawarte na
	 * polu mapy symulacji, do których nastêpnie dodaje w³asne informacje.
	 */
	public void interakcja() {
		mojaPaczka = zbierzPaczka();
		Main.getPole(x, y).setZajmujacy(this);
		for (int i = Main.getPole(x, y).getZajmujacy().indexOf(this) - 1; i >= 0; i--) {
			Main.getPole(x, y).getZajmujacy().get(i).reakcja(mojaPaczka);
		}
		if (przezycie) {
			reakcja(Main.getPole(x, y).getPaczki());
		}
		if (przezycie) {
			Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
		}
	}
}

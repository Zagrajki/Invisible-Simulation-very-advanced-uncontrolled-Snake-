package symulacja;

import java.util.LinkedList;
import java.util.List;

/**
 * Klasa reprezentuj�ca cechy wsp�lne dla ka�dego obiektu, kt�ry si� porusza.
 * 
 * @author Mati
 * @version 1.0.0
 */
public abstract class zyje extends Obiekt {
	protected static int minimumJedzeniowychWygranych;

	/**
	 * Ustawia minimaln� liczb� wygranych walk zara�aj�cej jednostki znajduj�cej si�
	 * na jedzeniu potrzebn�, aby mog�a zarazi� i prze�y�.
	 * 
	 * @param dane - minimalna liczba wygranych walk zara�aj�cej jednostki
	 *             znajduj�cej si� na jedzeniu potrzebna, aby mog�a zarazi� i
	 *             prze�y�
	 */
	public static void setMinimumJedzeniowychWygranych(int dane) {
		minimumJedzeniowychWygranych = dane;
	}

	protected static int minimumWygranych;

	/**
	 * Ustawia minimaln� liczb� wygranych walk zara�aj�cej jednostki potrzebn�, aby
	 * mog�a zarazi� i prze�y�.
	 * 
	 * @param dane - minimalna liczba wygranych walk zara�aj�cej jednostki
	 *             potrzebna, aby mog�a zarazi� i prze�y�
	 */
	public static void setMinimumWygranych(int dane) {
		minimumWygranych = dane;
	}

	protected static int czasRozmnazania;

	/**
	 * Ustawia ilo�� tur potrzebn� do rozmno�enia jednostki zara�aj�cej b�d�c� te�
	 * d�ugo�ci� trwania zara�enia.
	 * 
	 * @param dane - ilo�� tur potrzebna do rozmno�enia jednostki zara�aj�cej b�d�c�
	 *             te� d�ugo�ci� trwania zara�enia
	 */
	public static void setCzasRozmnazania(int dane) {
		czasRozmnazania = dane;
	}

	protected int timerRozmnazania;

	/**
	 * Usuwa informacje charakterystyczne dla danej klasy z pola mapy symulacji,
	 * kt�re jest zajmowane przez obiekt tej klasy.
	 */
	public abstract void usunMojaPaczkeZPola();

	/**
	 * Ustawia nowe wsp�rz�dne obiektu, losowe i zarazem s�siednie do starych.
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
	 * Przeprowadza interakcj� obiektu z obiektami znajduj�cymi si� na tym samym
	 * polu mapy symulacji, czyli wywo�uje w nich reakcj� na informacje od tego
	 * obiektu oraz wywo�uje w tym obiekcie reakcj� na informacje ju� zawarte na
	 * polu mapy symulacji, do kt�rych nast�pnie dodaje w�asne informacje.
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

package symulacja;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Klasa reprezentuj¹ca wê¿a.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Waz extends zyje implements WriteWaz {
	private WspI cel;
	private static int minimalnyJedzeniowyTlum;

	/**
	 * Ustawia minimaln¹ liczbê bakterii w grupie bakterii znajduj¹cej siê na
	 * jedzeniu, aby mog³y one zaraziæ wê¿a.
	 * 
	 * @param dane - minimalna liczbê bakterii w grupie bakterii znajduj¹cej siê na
	 *             jedzeniu, aby mog³y one zaraziæ wê¿a
	 */
	public static void setMinimalnyJedzeniowyTlum(int dane) {
		minimalnyJedzeniowyTlum = dane;
	}

	private static int minimalnyTlum;

	/**
	 * Ustawia minimaln¹ liczbê bakterii w grupie bakterii, aby mog³y one zaraziæ
	 * wê¿a.
	 * 
	 * @param dane - minimalna liczbê bakterii w grupie bakterii, aby mog³y one
	 *             zaraziæ wê¿a
	 */
	public static void setMinimalnyTlum(int dane) {
		minimalnyTlum = dane;
	}

	private static int czasOgluszenia;

	/**
	 * Ustawia iloœæ tur, jak¹ trwa og³uszenie wê¿a.
	 * 
	 * @param dane - iloœæ tur, jak¹ trwa og³uszenie wê¿a
	 */
	public static void setCzasOgluszenia(int dane) {
		czasOgluszenia = dane;
	}

	private static int czasSpacjalnosci;

	/**
	 * Ustawia iloœæ tur, jak¹ trwa specjalna moc z jedzenia.
	 * 
	 * @param dane - iloœæ tur, jak¹ trwa specjalna moc z jedzenia
	 */
	public static void setCzasSpacjalnosci(int dane) {
		czasSpacjalnosci = dane;
	}

	private boolean przyspieszenie = false;
	private boolean zwolnienie = false;
	private boolean ochrona = false;
	private int timerSpecjalnosci;
	private int timerOgluszenia;
	private List<int[]> ogon;
	private int dlugosc;

	/**
	 * Zwraca d³ugoœæ wê¿a.
	 * 
	 * @return d³ugoœæ wê¿a
	 */
	@Override
	public int getDlugosc() {
		return dlugosc;
	}

	private int[] glowa;
	private boolean zarazony;

	/**
	 * Stwarza nowego wê¿a na mapie symulacji.
	 */
	public Waz() {
		ogon = new LinkedList<>();
		przezycie = true;
		dlugosc = 1;
		cel = null;
		timerOgluszenia = 0;
		ustawNazwe();
		losowanieMiejsca();
		glowa = new int[2];
		glowa[0] = x;
		glowa[1] = y;
		ogon.add(glowa);
		Main.getPole(x, y).getZajmujacy().add(this);
		Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
	}

	/**
	 * Zwraca tablicê zawieraj¹c¹ wspó³rzêdne koñca wê¿a.
	 * 
	 * @return tablica zawieraj¹c¹ wspó³rzêdne koñca wê¿a
	 */
	@Override
	public int[] getWazneWsp() {
		return ogon.get(ogon.size() - 1);
	}

	/**
	 * Usuwa wê¿a z listy Obiektów bior¹cych udzia³ w symulacji i ze wszystkich
	 * zajmowanych przez niego pól razem z informacjami o nim z tych pól.
	 */
	@Override
	public void zniszcz() {
		przezycie = false;
		Main.getPionki().remove(this);
		Main.getPole(ogon.get(0)[0], ogon.get(0)[1]).getPaczki().remove(informacje.GLOWA);
		for (int i = 0; i < ogon.size(); i++) {
			Main.getPole(ogon.get(i)[0], ogon.get(i)[1]).usunNosiciel();
			Main.getPole(ogon.get(i)[0], ogon.get(i)[1]).getZajmujacy().remove(this);
			Main.getPole(ogon.get(i)[0], ogon.get(i)[1]).getPaczki().remove(informacje.JEDZACY);
			Main.getPolePuste().add(Main.getTablicaPolePuste(ogon.get(i)[0], ogon.get(i)[1]));
		}
	}

	/**
	 * Szuka najbli¿czego obiektu bêd¹cego antidotum.
	 * 
	 * @return najbli¿szy obiekt bêd¹cy antidotum.
	 */
	public WspI szukajLeku() {
		int odleglosc = 0;
		WspI fakeCel = null;
		for (int i = 0; i < Main.getPionki().size(); i++)
			if (Main.getPionki().get(i).getDane().containsKey(informacje.MEDYCZNE)) {
				if (fakeCel == null) {
					fakeCel = Main.getPionki().get(i);
					odleglosc = (int) Math.sqrt((fakeCel.getWazneWsp()[0] - x) * (fakeCel.getWazneWsp()[0] - x)
							+ (fakeCel.getWazneWsp()[1] - y) * (fakeCel.getWazneWsp()[1] - y));
				} else {
					if (odleglosc > (int) Math.sqrt((Main.getPionki().get(i).getWazneWsp()[0] - x)
							* (Main.getPionki().get(i).getWazneWsp()[0] - x)
							+ (Main.getPionki().get(i).getWazneWsp()[1] - y)
									* (Main.getPionki().get(i).getWazneWsp()[1] - y))) {
						fakeCel = Main.getPionki().get(i);
						odleglosc = (int) Math.sqrt((fakeCel.getWazneWsp()[0] - x) * (fakeCel.getWazneWsp()[0] - x)
								+ (fakeCel.getWazneWsp()[1] - y) * (fakeCel.getWazneWsp()[1] - y));
					}
				}
			}
		return fakeCel;
	}

	/**
	 * Zmienia wspó³rzêdne g³owy wê¿a na s¹siednie, bli¿sze celu.
	 * 
	 * @param cel - cel, do którego zmierza w¹¿
	 */
	public void ruszDoCelu(WspI cel) {
		if (x < cel.getWazneWsp()[0])
			x++;
		else if (x > cel.getWazneWsp()[0])
			x--;
		if (y < cel.getWazneWsp()[1])
			y++;
		else if (y > cel.getWazneWsp()[1])
			y--;
	}

	/**
	 * Przeprowadza pocz¹tek tury wê¿a.
	 */
	@Override
	public void tura() {
		if (zarazony) {
			timerRozmnazania--;
			if (timerRozmnazania == 0)
				zniszcz();
		}
		if (przezycie) {
			if (timerOgluszenia == 0) {
				if (zwolnienie) {
					if (timerSpecjalnosci % 2 == 0)
						turaWeza();
				} else {
					turaWeza();
					if (przyspieszenie)
						turaWeza();
				}
			} else {
				timerOgluszenia--;
			}
			if (timerSpecjalnosci != 0) {
				timerSpecjalnosci--;
				if (timerSpecjalnosci == 0) {
					zwolnienie = false;
					przyspieszenie = false;
					ochrona = false;
				}
			}
		}
	}

	/**
	 * Przeprowadza w³aœciwe rozwiniêcie tury wê¿a.
	 */
	public void turaWeza() {
		if (zarazony && (cel == null || (boolean) Main.getPole(cel.getWazneWsp()[0], cel.getWazneWsp()[1]).getPaczki()
				.getOrDefault(informacje.MEDYCZNE, false) == false)) {
			cel = szukajLeku();
		}
		if (cel == null) {
			rusz();
		} else {
			ruszDoCelu(cel);
		}
		if (przezycie) {
		if ((boolean) Main.getPole(x, y).getPaczki().getOrDefault(informacje.JEDZACY, false) == true) {
			timerOgluszenia = czasOgluszenia;
			x = ogon.get(0)[0];
			y = ogon.get(0)[1];

		}
		if (timerOgluszenia == 0) {
			glowa = new int[2];
			glowa[0] = x;
			glowa[1] = y;
			ogon.add(0, glowa);
			Main.getPole(ogon.get(1)[0], ogon.get(1)[1]).getPaczki().remove(informacje.GLOWA);
			if (ogon.size() > dlugosc) {
				Main.getPole(ogon.get(dlugosc)[0], ogon.get(dlugosc)[1]).usunNosiciel();
				Main.getPole(ogon.get(dlugosc)[0], ogon.get(dlugosc)[1]).getZajmujacy().remove(this);
				Main.getPole(ogon.get(dlugosc)[0], ogon.get(dlugosc)[1]).getPaczki().remove(informacje.JEDZACY);
				if (Main.getPole(ogon.get(dlugosc)[0], ogon.get(dlugosc)[1]).getZajmujacy().isEmpty()) {
					Main.getPolePuste().add(Main.getTablicaPolePuste(ogon.get(dlugosc)[0], ogon.get(dlugosc)[1]));
				}

				ogon.remove(dlugosc);
			}
			Main.getPole(x, y).setNosiciel(this);
			interakcja();
		}
		}
	}

	/**
	 * Okreœla reakcje wê¿a na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbiór otrzymanych informacji, na podstawie których
	 *                        bêdzie przeprowadzona reakcja
	 */
	@Override
	public void reakcja(Map<informacje, Object> przyslanaPaczka) {

		if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZJADLIWY, false) == true) {
			if (dlugosc + (int) przyslanaPaczka.get(informacje.PODSTAWOWE) < Main.getDlugoscPlanszy()
					&& dlugosc + (int) przyslanaPaczka.get(informacje.PODSTAWOWE) < Main.getSzerokoscPlanszy()) {
				dlugosc += (int) przyslanaPaczka.get(informacje.PODSTAWOWE);
			}
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.SPECJALNOSC, false) == true) {
				timerSpecjalnosci = czasSpacjalnosci;
			}
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.PRZYSPIESZENIE, false)) {
				przyspieszenie = true;
			}
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZWOLNIENIE, false)) {
				zwolnienie = true;
			}
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.OCHRONA, false)) {
				ochrona = true;
			}
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZARAZAJACY, false) == true
					&& (int) przyslanaPaczka.get(informacje.WYGRANEBAKTERII) >= minimumJedzeniowychWygranych && !ochrona
					&& !zarazony
					&& (int) przyslanaPaczka.getOrDefault(informacje.LICZBABAKTERII, 0) >= minimalnyJedzeniowyTlum) {
				zarazony = true;
				timerRozmnazania = czasRozmnazania;
			}
			Main.getPole(x, y).getPaczki().remove(informacje.ZJADLIWY);
			Main.getPole(x, y).getPaczki().remove(informacje.PODSTAWOWE);
			Main.getPole(x, y).getPaczki().remove(informacje.SPECJALNOSC);
			Main.getPole(x, y).getPaczki().remove(informacje.PRZYSPIESZENIE);
			Main.getPole(x, y).getPaczki().remove(informacje.ZWOLNIENIE);
			Main.getPole(x, y).getPaczki().remove(informacje.OCHRONA);
		}
		if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZARAZAJACY, false) == true
				&& (int) przyslanaPaczka.get(informacje.WYGRANEBAKTERII) >= minimumWygranych
				&& (int) przyslanaPaczka.get(informacje.ZARAZAJACYX) == x
				&& (int) przyslanaPaczka.get(informacje.ZARAZAJACYY) == y && !ochrona && !zarazony
				&& (int) przyslanaPaczka.getOrDefault(informacje.LICZBABAKTERII, 0) >= minimalnyTlum) {
			zarazony = true;
			timerRozmnazania = czasRozmnazania;
		}
		if ((boolean) przyslanaPaczka.getOrDefault(informacje.MEDYCZNE, false) == true) {
			zarazony = false;
			cel = null;
			timerRozmnazania = 0;
			Main.getPole(x, y).getPaczki().remove(informacje.MEDYCZNE);
		}
	}

	/**
	 * Zbiera informacje o wê¿u.
	 */
	@Override
	public Map<informacje, Object> zbierzPaczka() {
		mojaPaczka.put(informacje.JEDZACY, true);
		mojaPaczka.put(informacje.GLOWA, true);
		return mojaPaczka;
	}

	/**
	 * Usuwa z pola mapy symulacji informacjê o byciu tam g³owy wê¿a.
	 */
	@Override
	public void usunMojaPaczkeZPola() {
		Main.getPole(x, y).getPaczki().remove(informacje.GLOWA);
	}
}
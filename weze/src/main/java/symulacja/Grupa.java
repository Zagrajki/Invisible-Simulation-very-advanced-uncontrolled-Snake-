package symulacja;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Klasa reprezentuj�ca grup� bakterii.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class Grupa extends zyje implements WriteGrupa {
	private static int czasSM;

	/**
	 * Ustawia ilo�� tur potrzebn� na uro�ni�cie bakterii na �redni�.
	 * 
	 * @param dane - ilo�� tur potrzebna na uro�ni�cie bakterii a �redni�
	 */
	public static void setCzasSM(int dane) {
		czasSM = dane;
	}

	private static int czasML;

	/**
	 * Ustawia ilo�� tur potrzebn� na uro�ni�cie bakterii na du��.
	 * 
	 * @param dane - ilo�� tur potrzebna na uro�ni�cie bakterii na du��
	 */
	public static void setCzasML(int dane) {
		czasML = dane;
	}

	private static int pochlanianieWrogiejGrupy;

	/**
	 * Ustawia procent liczby cz�onk�w grupy bakterii, poni�ej kt�rego zostaje ona
	 * wch�oni�ta do grupy, z kt�r� walczy.
	 * 
	 * @param dane - procent liczby cz�onk�w grupy bakterii, poni�ej kt�rego zostaje
	 *             ona wch�oni�ta do grupy, z kt�r� walczy
	 */
	public static void setPochlanianieWrogiejGrupy(int dane) {
		pochlanianieWrogiejGrupy = dane;
	}

	private static int Min_S;

	/**
	 * Ustawia minimaln� ilo�� ma�ych bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - minimalna ilo�� ma�ych bakterii, jaka mo�e zosta� stworzona w
	 *             nowej grupie
	 */
	public static void setMin_S(int dane) {
		Min_S = dane;
	}

	private static int Min_M;

	/**
	 * Ustawia minimaln� ilo�� �rednich bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - minimalna ilo�� �rednich bakterii, jaka mo�e zosta� stworzona w
	 *             nowej grupie
	 */
	public static void setMin_M(int dane) {
		Min_M = dane;
	}

	private static int Min_L;

	/**
	 * Ustawia minimaln� ilo�� du�ych bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - minimalna ilo�� du�ych bakterii, jaka mo�e zosta� stworzona w
	 *             nowej grupie
	 */
	public static void setMin_L(int dane) {
		Min_L = dane;
	}

	private static int Max_S;

	/**
	 * Ustawia maksymaln� ilo�� ma�ych bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - maksymalna ilo�� ma�ych bakterii, jaka mo�e zosta� stworzona w
	 *             nowej grupie
	 */
	public static void setMax_S(int dane) {
		Max_S = dane;
	}

	private static int Max_M;

	/**
	 * Ustawia maksymaln� ilo�� �renich bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - maksymalna ilo�� �rednich bakterii, jaka mo�e zosta� stworzona
	 *             w nowej grupie
	 */
	public static void setMax_M(int dane) {
		Max_M = dane;
	}

	private static int Max_L;

	/**
	 * Ustawia maksymaln� ilo�� du�ych bakterii, jaka mo�e zosta� stworzona w nowej
	 * grupie.
	 * 
	 * @param dane - maksymalna ilo�� du�ych bakterii, jaka mo�e zosta� stworzona w
	 *             nowej grupie
	 */
	public static void setMax_L(int dane) {
		Max_L = dane;
	}

	private List<BakteriaI> czlonkowie;
	private WspI zywiciel;
	private int liczbaBakterii;
	private int wygraneBakterii;
	private boolean mojeJedzonko;
	private int staryX;
	private int staryY;

	/**
	 * Zwraca ilo�ci bakterii o kolejnych rozmiarach.
	 * 
	 * @return tablica przechowuj�ca ilo�ci bakterii o kolejnych rozmiarach
	 */
	@Override
	public int[] getSklad() {
		int[] skladzik = { 0, 0, 0 };
		for (int i = 0; i < czlonkowie.size(); i++) {
			if (czlonkowie.get(i).getLevel() == 1)
				skladzik[0]++;
			if (czlonkowie.get(i).getLevel() == 2)
				skladzik[1]++;
			if (czlonkowie.get(i).getLevel() == 3)
				skladzik[2]++;
		}
		return skladzik;
	}

	/**
	 * Zwi�ksza ilo�� przezytych tur ka�dej bakterii w grupie.
	 */
	public void zwiekszTimerWzrostu() {
		for (BakteriaI piece : czlonkowie) {
			piece.zwiekszMojTimer();
			if (piece.getMojTimer() == czasSM || piece.getMojTimer() == czasML) {
				piece.zwiekszLevel();
			}
		}
	}

	/**
	 * Stwarza now� grup� bakterii na mapie symulacji.
	 */
	public Grupa() {
		czlonkowie = new LinkedList<>();
		for (int i = 0; i < getRandomInt(Min_S, Max_S); i++) {
			czlonkowie.add(new Bakteria(1, czasSM, czasML));
		}
		for (int i = 0; i < getRandomInt(Min_M, Max_M); i++) {
			czlonkowie.add(new Bakteria(2, czasSM, czasML));
		}
		for (int i = 0; i < getRandomInt(Min_L, Max_L); i++) {
			czlonkowie.add(new Bakteria(3, czasSM, czasML));
		}
		timerRozmnazania = 0;
		liczbaBakterii = czlonkowie.size();
		wygraneBakterii = 0;
		przezycie = true;
		zywiciel = null;
		mojeJedzonko = false;
		ustawNazwe();
		losowanieMiejsca();
		Main.getPole(x, y).getZajmujacy().add(this);
		Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
	}

	/**
	 * Przeprowadza tur� grupy bakterii.
	 */
	@Override
	public void tura() {
		if (!przezycie) {
			zniszcz();
		} else {
			if (timerRozmnazania != 0) {
				timerRozmnazania--;
				if (timerRozmnazania == 0) {
					mojeJedzonko = false;
					czlonkowie.addAll(czlonkowie);
					if (zywiciel != null) {
						x = zywiciel.getWazneWsp()[0];
						y = zywiciel.getWazneWsp()[1];
						zywiciel = null;
					}
				}
			}
			if (timerRozmnazania == 0) {
				zwiekszTimerWzrostu();
				staryX = x;
				staryY = y;
				usunMojaPaczkeZPola();
				rusz();
				Main.getPole(staryX, staryY).getZajmujacy().remove(this);
				if (Main.getPole(staryX, staryY).getZajmujacy().isEmpty()) {
					Main.getPolePuste().add(Main.getTablicaPolePuste(staryX, staryY));
				}
				interakcja();
			} else {
				Main.getPole(x, y).getPaczki().putAll(zbierzPaczka());
			}
		}
	}

	/**
	 * Okre�la reakcje grupy bakterii na otrzymane informacje.
	 * 
	 * @param przyslanaPaczka - zbi�r otrzymanych informacji, na podstawie kt�rych
	 *                        b�dzie przeprowadzona reakcja
	 */
	@Override
	public void reakcja(Map<informacje, Object> przyslanaPaczka) {
		if (zywiciel == null && przezycie) {
			if ((boolean) przyslanaPaczka.getOrDefault(informacje.MEDYCZNE, false) == true) {
				przezycie = false;
				Main.getPole(x, y).getPaczki().remove(informacje.MEDYCZNE);
			} else {
				if ((boolean) przyslanaPaczka.getOrDefault(informacje.DOPRZEJECIA, false) == true) {
					czlonkowie.clear();
					czlonkowie.addAll((LinkedList<BakteriaI>) przyslanaPaczka.get(informacje.WOJOWNICY));
					liczbaBakterii = (int) przyslanaPaczka.get(informacje.LICZBABAKTERII);
					Main.getPole(x, y).getPaczki().remove(informacje.DOPRZEJECIA);
					wygraneBakterii++;
				} else if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZARAZAJACY, false) == true) {
					przezycie = walka((LinkedList<BakteriaI>) przyslanaPaczka.get(informacje.WOJOWNICY));
				}
				if (przezycie) {
					if ((boolean) przyslanaPaczka.getOrDefault(informacje.ZJADLIWY, false) == true) {
						mojeJedzonko = true;
						timerRozmnazania = czasRozmnazania;
					}
					if ((boolean) przyslanaPaczka.getOrDefault(informacje.JEDZACY, false) == true) {
						if ((boolean) przyslanaPaczka.getOrDefault(informacje.GLOWA, false) == true) {
							if (mojeJedzonko && wygraneBakterii < minimumJedzeniowychWygranych) {
								przezycie = false;
							}
							if (!mojeJedzonko && wygraneBakterii < minimumWygranych) {
								przezycie = false;
							}
						}
						if (przezycie) {
							zywiciel = Main.getPole(x, y).getNosiciel();
							timerRozmnazania = czasRozmnazania;
						}
					}
				}
			}
		}
	}

	/**
	 * Zbiera informacje o grupie bakterii.
	 */
	@Override
	public Map<informacje, Object> zbierzPaczka() {
		mojaPaczka.put(informacje.ZARAZAJACY, true);
		mojaPaczka.put(informacje.LICZBABAKTERII, liczbaBakterii);
		mojaPaczka.put(informacje.WYGRANEBAKTERII, wygraneBakterii);
		mojaPaczka.put(informacje.WOJOWNICY, czlonkowie);
		mojaPaczka.put(informacje.ZARAZAJACYX, x);
		mojaPaczka.put(informacje.ZARAZAJACYY, y);
		return mojaPaczka;
	}

	/**
	 * Usuwa informacje o grupie bakterii z pola mapy.
	 */
	@Override
	public void usunMojaPaczkeZPola() {
		Main.getPole(x, y).getPaczki().remove(informacje.ZARAZAJACY);
		Main.getPole(x, y).getPaczki().remove(informacje.LICZBABAKTERII);
		Main.getPole(x, y).getPaczki().remove(informacje.WYGRANEBAKTERII);
		Main.getPole(x, y).getPaczki().remove(informacje.WOJOWNICY);
		Main.getPole(x, y).getPaczki().remove(informacje.ZARAZAJACYX);
		Main.getPole(x, y).getPaczki().remove(informacje.ZARAZAJACYY);
	}

	/**
	 * Przeprowadza walk� mi�dzy jedn� list� przechowuj�c� bakterie a drug�.
	 * 
	 * @param wrog - lista przecowujaca wrogie jednostki, z ktorymi zostanie
	 *             stoczona walka
	 * @return true - gdy wygra�a ta grupa bakteii; false - gdy przegra�a ta grupa
	 *         bakterii
	 */
	private boolean walka(List<BakteriaI> wrog) {
		int a = czlonkowie.size() * pochlanianieWrogiejGrupy / 100;
		int b = wrog.size() * pochlanianieWrogiejGrupy / 100;
		int nasi = czlonkowie.size();
		int wrogowie = wrog.size();
		int losowaNaszych;
		int losowaWroga;
		do {
			losowaNaszych = czlonkowie.get(0).getLevel() * random.nextInt(100);
			losowaWroga = wrog.get(0).getLevel() * random.nextInt(100);
			if (losowaNaszych > losowaWroga) {
				wrogowie--;
				wrog.remove(0);
			} else {
				if (losowaNaszych < losowaWroga) {
					nasi--;
					czlonkowie.remove(0);
				} else {
					continue;
				}
			}
		} while (nasi > a && wrogowie > b);
		czlonkowie.addAll(wrog);
		liczbaBakterii = czlonkowie.size();
		Main.getPole(x, y).getPaczki().put(informacje.LICZBABAKTERII, liczbaBakterii);
		Main.getPole(x, y).getPaczki().put(informacje.WOJOWNICY, czlonkowie);
		if (nasi <= a) {
			Main.getPole(x, y).getPaczki().put(informacje.DOPRZEJECIA, true);
			return false;
		} else {
			wygraneBakterii++;
			Main.getPole(x, y).najnowszyZajmujacy().niePrzezyj();
			return true;
		}
	}
}
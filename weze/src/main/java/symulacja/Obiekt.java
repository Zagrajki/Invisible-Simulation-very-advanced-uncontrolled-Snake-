package symulacja;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Klasa reprezentuj¹ca cechy wspólne dla ka¿dego Obiektu bior¹cego udzia³ w
 * symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public abstract class Obiekt implements ExistingI {
	protected int x;
	protected int y;

	/**
	 * Zwraca wspó³rzêdne x i y Obiektu.
	 * 
	 * @return tablica przechowuj¹ca wspó³rzêdne x i y Obiektu
	 */
	@Override
	public int[] getWazneWsp() {
		int[] a = { x, y };
		return a;
	}

	protected static Random random = new Random();
	private int losowanka;
	protected String nazwa;

	/**
	 * Zwraca "nazwê" Obiektu.
	 * 
	 * @return "nazwa" Obiektu
	 */
	@Override
	public String getNazwa() {
		return nazwa;
	}

	/**
	 * Ustawia nazwê klasy obiektu jako jego "nazwê".
	 */
	public void ustawNazwe() {
		nazwa = this.getClass().getSimpleName();
	}

	protected Map<informacje, Object> mojaPaczka = new HashMap<>();

	/**
	 * Zwraca paczkê informacji o Obiekcie.
	 * 
	 * @return paczka informacji o Obiekcie
	 */
	@Override
	public Map<informacje, Object> getDane() {
		return mojaPaczka;
	}

	protected boolean przezycie = true;

	/**
	 * Zwraca zmienn¹ logiczn¹ okreœlaj¹c¹, czy Obiekt ¿yje.
	 * 
	 * @return true - gdy Obiekt ¿yje; false - gdy Obiekt nie ¿yje
	 */
	@Override
	public boolean getPrzezycie() {
		return przezycie;
	}

	/**
	 * Zmienn¹ logiczn¹ okreœlaj¹c¹, czy Obiekt ¿yje, ustawia na fa³sz.
	 */
	@Override
	public void niePrzezyj() {
		przezycie = false;
	}

	/**
	 * Usuwa Obiekt z listy Obiektów bior¹cych udzia³ w symulacji i z obecnie
	 * zajmowanego przez ten Obiekt pola.
	 */
	@Override
	public void zniszcz() {
		Main.getPionki().remove(this);
		Main.getPole(x, y).getZajmujacy().remove(this);
	}

	/**
	 * Losuje liczbê ca³kowit¹ z podanego przedzia³u.
	 * 
	 * @param min - najmniejsza liczba mo¿liwa do wylosowania
	 * @param max - najwiêksza liczba mo¿liwa do wylosowania
	 * @return zwraca liczbê ca³kowit¹ wylosowan¹ z podanego przedzia³u
	 */
	public static int getRandomInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * Usuwa losowe z pustych pól planszy. Wspó³rzêdne tego pola zapisuje jako
	 * wspó³rzêdne danego Obiektu.
	 */
	public void losowanieMiejsca() {
		losowanka = random.nextInt(Main.getPolePuste().size());
		x = Main.getPolePuste(losowanka).getWazneWsp()[0];
		y = Main.getPolePuste(losowanka).getWazneWsp()[1];
		Main.usunPolePuste(losowanka);
	}

	/**
	 * Zbiera informacje o Obiekcie.
	 * 
	 * @return mapa przechowuj¹ca informacje o Obiekcie
	 */
	public abstract Map<informacje, Object> zbierzPaczka();
}

package symulacja;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Klasa reprezentuj�ca cechy wsp�lne dla ka�dego Obiektu bior�cego udzia� w
 * symulacji.
 * 
 * @author Mati
 * @version 1.0.0
 */
public abstract class Obiekt implements ExistingI {
	protected int x;
	protected int y;

	/**
	 * Zwraca wsp�rz�dne x i y Obiektu.
	 * 
	 * @return tablica przechowuj�ca wsp�rz�dne x i y Obiektu
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
	 * Zwraca "nazw�" Obiektu.
	 * 
	 * @return "nazwa" Obiektu
	 */
	@Override
	public String getNazwa() {
		return nazwa;
	}

	/**
	 * Ustawia nazw� klasy obiektu jako jego "nazw�".
	 */
	public void ustawNazwe() {
		nazwa = this.getClass().getSimpleName();
	}

	protected Map<informacje, Object> mojaPaczka = new HashMap<>();

	/**
	 * Zwraca paczk� informacji o Obiekcie.
	 * 
	 * @return paczka informacji o Obiekcie
	 */
	@Override
	public Map<informacje, Object> getDane() {
		return mojaPaczka;
	}

	protected boolean przezycie = true;

	/**
	 * Zwraca zmienn� logiczn� okre�laj�c�, czy Obiekt �yje.
	 * 
	 * @return true - gdy Obiekt �yje; false - gdy Obiekt nie �yje
	 */
	@Override
	public boolean getPrzezycie() {
		return przezycie;
	}

	/**
	 * Zmienn� logiczn� okre�laj�c�, czy Obiekt �yje, ustawia na fa�sz.
	 */
	@Override
	public void niePrzezyj() {
		przezycie = false;
	}

	/**
	 * Usuwa Obiekt z listy Obiekt�w bior�cych udzia� w symulacji i z obecnie
	 * zajmowanego przez ten Obiekt pola.
	 */
	@Override
	public void zniszcz() {
		Main.getPionki().remove(this);
		Main.getPole(x, y).getZajmujacy().remove(this);
	}

	/**
	 * Losuje liczb� ca�kowit� z podanego przedzia�u.
	 * 
	 * @param min - najmniejsza liczba mo�liwa do wylosowania
	 * @param max - najwi�ksza liczba mo�liwa do wylosowania
	 * @return zwraca liczb� ca�kowit� wylosowan� z podanego przedzia�u
	 */
	public static int getRandomInt(int min, int max) {
		return random.nextInt(max - min + 1) + min;
	}

	/**
	 * Usuwa losowe z pustych p�l planszy. Wsp�rz�dne tego pola zapisuje jako
	 * wsp�rz�dne danego Obiektu.
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
	 * @return mapa przechowuj�ca informacje o Obiekcie
	 */
	public abstract Map<informacje, Object> zbierzPaczka();
}

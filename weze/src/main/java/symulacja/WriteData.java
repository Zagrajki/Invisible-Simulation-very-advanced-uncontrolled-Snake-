package symulacja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa odpowiadaj�ca za zapis wynik�w symulacji do pliku zewn�trznego.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class WriteData {
	/**
	 * Zapisuje wyniki symulacji do pliku zewn�trznego.
	 * 
	 * @param in     - plik, do kt�rego maj� by� zapisane wyniki symulacji.
	 * @param pionki - obiekty, kt�re pozosta�y w symulacji po jej zako�czeniu
	 * @throws IOException wyrzucany, kiedy nie mo�na uzyska� dost�pu do pliku, w
	 *                     kt�rym maj� by� zapisane wyniki
	 */
	public static void zapisz(FileWriter in, List<ExistingI> pionki) throws IOException {
		String napis;
		int iloscWezy = 0;
		int iloscGrup = 0;
		int[] skladBakterii;
		for (ExistingI iteracja : pionki) {
			napis = iteracja.getNazwa();
			if (napis.equals("Waz")) {
				iloscWezy++;
				System.out.println("Waz numer " + iloscWezy + " ma dlugosc " + ((WriteWaz) iteracja).getDlugosc());
				in.append("numer Weza");
				in.append(",");
				in.append(String.valueOf(iloscWezy));
				in.append("\n");
				in.append("dlugosc Weza");
				in.append(",");
				in.append(String.valueOf(((Waz) iteracja).getDlugosc()));
				in.append("\n");
				in.append("\n");
			}
			if (napis.equals("Grupa")) {
				iloscGrup++;
				skladBakterii = ((WriteGrupa) iteracja).getSklad();
				System.out.println("Grupa numer " + iloscGrup + " ma tyle ma�ych bakterii: " + skladBakterii[0]
						+ ", tyle �rednich: " + skladBakterii[1] + " i tyle du�ych: " + skladBakterii[2]);
				in.append("numer Grupy");
				in.append(",");
				in.append(String.valueOf(iloscGrup));
				in.append("\n");
				in.append("ilo�� ma�ych bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[0]));
				in.append("\n");
				in.append("ilo�� �rednich bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[1]));
				in.append("\n");
				in.append("ilo�� du�ych bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[2]));
				in.append("\n");
				in.append("\n");
			}
		}
	}
}

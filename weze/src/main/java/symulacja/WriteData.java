package symulacja;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Klasa odpowiadaj¹ca za zapis wyników symulacji do pliku zewnêtrznego.
 * 
 * @author Mati
 * @version 1.0.0
 */
public class WriteData {
	/**
	 * Zapisuje wyniki symulacji do pliku zewnêtrznego.
	 * 
	 * @param in     - plik, do którego maj¹ byæ zapisane wyniki symulacji.
	 * @param pionki - obiekty, które pozosta³y w symulacji po jej zakoñczeniu
	 * @throws IOException wyrzucany, kiedy nie mo¿na uzyskaæ dostêpu do pliku, w
	 *                     którym maj¹ byæ zapisane wyniki
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
				System.out.println("Grupa numer " + iloscGrup + " ma tyle ma³ych bakterii: " + skladBakterii[0]
						+ ", tyle œrednich: " + skladBakterii[1] + " i tyle du¿ych: " + skladBakterii[2]);
				in.append("numer Grupy");
				in.append(",");
				in.append(String.valueOf(iloscGrup));
				in.append("\n");
				in.append("iloœæ ma³ych bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[0]));
				in.append("\n");
				in.append("iloœæ œrednich bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[1]));
				in.append("\n");
				in.append("iloœæ du¿ych bakterii");
				in.append(",");
				in.append(String.valueOf(skladBakterii[2]));
				in.append("\n");
				in.append("\n");
			}
		}
	}
}

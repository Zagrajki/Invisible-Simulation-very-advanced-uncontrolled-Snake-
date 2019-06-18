
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
/**
 * Test metod klasy "ReadData".
 * @author Mati
 * @version 1.0.0
 */
class ReadDataTest {
	/**
	 * Testuje wyrzucanie wyj¹tku przez metodê "czytaj" w klasie "ReadData".
	 */
	@Test
	void czytajTest() {
	    Assertions.assertThrows(IOException.class, () -> {
	        symulacja.ReadData.czytaj(new BufferedReader(new FileReader("C:\\Users\\Mati\\eclipse-workspace\\weze\\src\\main\\java\\symulacja\\zlyplik.txt")));
	      });
	}
	/**
	 * Testuje wyrzucanie wyj¹tku przez metodê "wyjmijLiczbe" w klasie "ReadData".
	 */
	@Test
	void wyjmijLiczbeTest() {
	    Assertions.assertThrows(NumberFormatException.class, () -> {
	        symulacja.ReadData.WyjmijLiczbe("safddsafdsaf 7 4r5");
	      });
	}
}

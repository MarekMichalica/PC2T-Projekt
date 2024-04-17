package projekt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Kniznica {
	private Map<String, Kniha> zoznamKnih;
	
	public Kniznica() {
		this.zoznamKnih = new HashMap<>();
	}
	
	public void pridajKnihu(Scanner scanner) {
		System.out.println("Zadajte nazov knihy:");
		String nazov = scanner.nextLine();
		System.out.println("Zadajte autora knihy:");
		String autor = scanner.nextLine();
		System.out.println("Zadajte rok vydania knihy:");
		int rokVydania = scanner.nextInt();
		
		scanner.nextLine(); // cistenie bufferu
		
		System.out.println("Vyberte typ knihy: 1 pre roman, 2 pre ucebnicu");
		int typKnihy = scanner.nextInt();
		
		scanner.nextLine(); // cistenie bufferu
		
		Zaner zaner = null;
		int hodnotenie = 0;
		
		if(typKnihy == 1) {
			System.out.println("Zadajte zaner romanu (DETEKTIVNY, FANTASTICKY, VEDECKO_FANTASTICKY, HISTORICKY, PSYCHOLOGICKY)");
			zaner = Zaner.valueOf(scanner.nextLine().toUpperCase());
		} else if (typKnihy == 2) {
			System.out.println("Zadajte rocnik, pre ktory je ucebnica vhodna: ");
			hodnotenie = scanner.nextInt();
			
			scanner.nextLine(); // cistenien bufferu
		} else {
			System.out.println("Zadali ste nespravnu volbu");
		}
		
		Kniha kniha = new Kniha(nazov, autor, rokVydania, true, zaner, hodnotenie);
		zoznamKnih.put(nazov, kniha);
		
		System.out.println("Kniha bola uspesne pridana.");
	}	
}

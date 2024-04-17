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
	
	public void upravKnihu(Scanner scanner) {
		System.out.println("Zadajte nazov knihy, ktoru chcete upravit: ");
		String nazov = scanner.nextLine();
		
		if(!zoznamKnih.containsKey(nazov)) {
			System.out.println("Kniha nebola najdena v zozname");
			return;
		}
		
		Kniha kniha = zoznamKnih.get(nazov);
		System.out.println("Vyberte, co chcete upravit: ");
		System.out.println("1. Autor, 2. Rok vydania, 3. Stav dostupnosti");
		int volba = scanner.nextInt();
		
		switch(volba) {
			case 1:
				System.out.println("Zadajte meno noveho autora: ");
				String Autor = scanner.nextLine();
				kniha.setAutor(Autor);
				break;
			case 2:
				System.out.println("Zadajte novy rok vydania: ");
				int rokVydania = scanner.nextInt();
				kniha.setRokVydania(rokVydania);
				break;
			case 3:
				System.out.println("Zadajte novy stav dostupnosti: ");
				boolean dostupna = scanner.nextBoolean();
				kniha.setDostupna(dostupna);
				break;
			default:
				System.out.println("Zadali ste nespravnu operaciu");
		}
		
		System.out.println("Kniha bola uspesne aktualizovana");
	}
	
	public void vymazKnihu(Scanner scanner) {
		System.out.println("Zadajte nazov knihy, ktoru chcete vymazat");
		String nazov = scanner.nextLine();
		
		if(!zoznamKnih.containsKey(nazov)) {
			System.out.println("Kniha nebola najdena v zozname");
			return;
		}
		
		zoznamKnih.remove(nazov);
		System.out.println("Kniha bola uspesne zmazana");
	}
	
	public void oznacKnihu(Scanner scanner) {
		System.out.println("Zadajte nazov knihy, ktoru chcete oznacit");
		String nazov = scanner.nextLine();
		
		if(!zoznamKnih.containsKey(nazov)) {
			System.out.println("Kniha nebola najdena v zozname");
			return;
		}
		
		Kniha kniha = zoznamKnih.get(nazov);
		System.out.println("Zadajte novy stav dostupnosti: true pre dostupna, false pre nedostupna");
		boolean dostupna = scanner.nextBoolean();
		
		kniha.setDostupna(dostupna);
		System.out.println("Stav knihy bol uspesne zmeneny.");		
	}
	
	public void zobrazZoznamKnih(Scanner scanner) {
		if(zoznamKnih.isEmpty()){
			System.out.println("Kniznica je prazdna.");
			return;
		}
		
		for(Kniha kniha : zoznamKnih.values()) {
			 System.out.println("Nazov:" + kniha.getNazov() + " - " + "Autor: " + kniha.getAutor() + " - " + "Rok Vydania: " + kniha.getRokVydania() + " - " + "Dostupna: " + kniha.getDostupna() + " - " + "Zaner: " + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));
		}
	}
}

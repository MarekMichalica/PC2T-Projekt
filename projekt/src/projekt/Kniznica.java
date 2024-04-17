package projekt;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.*;
import java.io.*;

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
		int vyberTypuKnihy = scanner.nextInt();
		
		scanner.nextLine(); // cistenie bufferu
		
		Zaner zaner = null;
		int hodnotenie = 0;
		
		if(vyberTypuKnihy == 1) {
			System.out.println("Zadajte zaner romanu (DETEKTIVNY, FANTASTICKY, VEDECKO_FANTASTICKY, HISTORICKY, PSYCHOLOGICKY)");
			zaner = Zaner.valueOf(scanner.nextLine().toUpperCase());
		} else if (vyberTypuKnihy == 2) {
			System.out.println("Zadajte rocnik, pre ktory je ucebnica vhodna: ");
			hodnotenie = scanner.nextInt();
			
			scanner.nextLine(); // cistenie bufferu
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
	public void najdiKnihu(Scanner scanner) {
		System.out.println("Zadajte nazov knihy, ktoru chcete najst");
		String nazov = scanner.nextLine();
		
		if(!zoznamKnih.containsKey(nazov)) {
			System.out.println("Kniha nebola najdena v zozname");
			return;
		}
		
		Kniha kniha = zoznamKnih.get(nazov);
		System.out.println("Nazov:" + kniha.getNazov() + " - " + "Autor: " + kniha.getAutor() + " - " + "Rok Vydania: " + kniha.getRokVydania() + " - " + "Dostupna: " + kniha.getDostupna() + " - " + "Zaner: " + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));
	}
	public void zobrazKnihyPodlaAutora(Scanner scanner) {
		System.out.println("Zadajte meno autora:");
        String autor = scanner.nextLine();
        
        List<Kniha> zoznamKnihOdVybranehoAutora = new ArrayList<>();
        for (Kniha kniha : zoznamKnih.values()) {
            if (kniha.getAutor() == autor) {
            	zoznamKnihOdVybranehoAutora.add(kniha);
            }
        }
        
        if(zoznamKnihOdVybranehoAutora.isEmpty()) {
        	System.out.println("Od zadaneho autora sme nenasli ziadne knihy");
        }
        
        zoznamKnihOdVybranehoAutora.sort(Comparator.comparing(Kniha::getRokVydania));
	}
	public void zobrazKnihyPodlaZanru(Scanner scanner) {
		System.out.println("Zadajte zaner (DETEKTIVNY, FANTASTICKY, VEDECKO_FANTASTICKY, HISTORICKY, PSYCHOLOGICKY)");
		Zaner zaner = Zaner.valueOf(scanner.nextLine().toUpperCase());
		
		List<Kniha> zoznamKnihPodlaVybranehoZanru = new ArrayList<>();
        for (Kniha kniha : zoznamKnih.values()) {
            if (kniha.getZaner() == zaner) {
            	zoznamKnihPodlaVybranehoZanru.add(kniha);
            }
        }
        
        if(zoznamKnihPodlaVybranehoZanru.isEmpty()) {
        	System.out.println("Pod zadanym zanrom sme nenasli ziadne knihy");
        }
        
        zoznamKnihPodlaVybranehoZanru.sort(Comparator.comparing(Kniha::getNazov));
        
        for(Kniha kniha : zoznamKnihPodlaVybranehoZanru) {
        	System.out.println("Nazov:" + kniha.getNazov() + " - " + "Autor: " + kniha.getAutor() + " - " + "Rok Vydania: " + kniha.getRokVydania() + " - " + "Dostupna: " + kniha.getDostupna() + " - " + "Zaner: " + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));
        }
	}
	public void zobrazPozicaneKnihy(Scanner scanner) {
		for(Kniha kniha : zoznamKnih.values()) {
			if(!kniha.getDostupna()) {
				System.out.println("Nazov:" + kniha.getNazov() + " - " + "Autor: " + kniha.getAutor() + " - " + "Rok Vydania: " + kniha.getRokVydania() + " - " + "Dostupna: " + kniha.getDostupna() + " - " + "Zaner: " + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));
			}
		}
	}
	
	public void zapisInformaciiOKniheDoSuboru(Scanner scanner) {
		try {
	        System.out.println("Zadajte nazov knihy, ktory chcete zapisat do suboru");
	        String nazov = scanner.nextLine();
	        
	        if (!zoznamKnih.containsKey(nazov)) {
	            System.out.println("Kniha nebola najdena v zozname");
	            return;
	        }
	        
	        String menoSuboru = "knihy/" + nazov + ".txt";
	        PrintWriter writer = new PrintWriter(menoSuboru); // true pre pridanie do existujúceho súboru
	        
	        for (Kniha kniha : zoznamKnih.values()) {
	            if (kniha.getNazov().equals(nazov)) {
	                writer.println(kniha.getNazov() + "," + kniha.getAutor() + "," + kniha.getRokVydania() + "," + kniha.getDostupna() + "," + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));
	            }
	        }
	        
	        writer.close();
	        
	        System.out.println("Kniha bola uspesne zapisana do suboru");
	    } catch (IOException e) {
	        System.out.println("Chyba pri ukladani do suboru: " + e.getMessage());
	    }
	}
	
	public void citanieInformaciiOKniheZoSuboru(Scanner scanner) {
		try {
	        System.out.println("Zadajte nazov knihy, o ktorej chcete zistit informacie zo suboru");
	        String nazovKnihy = scanner.nextLine();
	        
	        String menoSuboru = "knihy/" + nazovKnihy + ".txt";
	        File subor  = new File(menoSuboru); 
	        Scanner citanieSuboru = new Scanner(subor);
	        
	        while(citanieSuboru.hasNextLine()) {
	        	String riadok = citanieSuboru.nextLine();
	        	String[] castiRiadku = riadok.split(",");
	        	
	        	String nazov = castiRiadku[0];
	        	String autor = castiRiadku[1];
	        	int rokVydania = Integer.parseInt(castiRiadku[2]);
	        	boolean dostupna = Boolean.parseBoolean(castiRiadku[3]);
	        	
	        	int hodnotenie = 0;
	        	Zaner zaner = null;
	        	
	        	if(!castiRiadku[4].isEmpty()) {
	        		zaner = Zaner.valueOf(castiRiadku[4]);
	        	} if (castiRiadku.length > 5 && !castiRiadku[5].isEmpty()) {
	        		hodnotenie = Integer.parseInt(castiRiadku[5]);
                }
	        	
	        	Kniha kniha = new Kniha(nazov, autor, rokVydania, dostupna, zaner, hodnotenie);
	        	System.out.println("Nazov:" + kniha.getNazov() + " - " + "Autor: " + kniha.getAutor() + " - " + "Rok Vydania: " + kniha.getRokVydania() + " - " + "Dostupna: " + kniha.getDostupna() + " - " + "Zaner: " + (kniha.getZaner() != null ? kniha.getZaner() : kniha.getHodnotenie()));	
	        }
	        
	        citanieSuboru.close();
	       
	    } catch (FileNotFoundException e) {
	        System.out.println("Subor sa nepodarilo najst: " + e.getMessage());
	    }
	}
}

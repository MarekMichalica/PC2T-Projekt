package projekt;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Kniznica kniznica = new Kniznica();
		
		while (true) {
            System.out.println("Vyberte akciu:");
            System.out.println("1. Pridanie novej knihy");
            System.out.println("2. Uprava knihy");
            System.out.println("3. Mazanie knihy");
            System.out.println("4. Oznacenie knihy ako vypozicana/vratiaca");
            System.out.println("5. Vypis knih");
            System.out.println("6. Vyhladanie knihy");
            System.out.println("7. Vypis vsetkych knih daneho autora");
            System.out.println("8. Vypis vsetkych knih podla zanru");
            System.out.println("9. Vypis vsetkych vypozicanych knih s informaciou o type");
            System.out.println("10. Ulozenie informacie o vybranej knihe do suboru");
            System.out.println("11. Nacitanie informacie o danej knihe zo suboru");
            System.out.println("12. Ulozenie vsetkych informacii do databazy a ukoncenie programu");
            System.out.print("Vasa volba: ");
            int volba = scanner.nextInt();
            scanner.nextLine();

            switch (volba) {
                case 1:
                	kniznica.pridajKnihu(scanner);
                    break;
                case 2:
                	kniznica.upravKnihu(scanner);
                    break;
                case 3:
                	kniznica.vymazKnihu(scanner);
                    break;
                case 4:
                	kniznica.oznacKnihu(scanner);
                    break;
                case 5:
                	kniznica.zobrazZoznamKnih(scanner);
                    break;
                case 6:
                	kniznica.najdiKnihu(scanner);
                    break;
                case 7:
                	kniznica.zobrazKnihyPodlaAutora(scanner);
                    break;
                case 8:
                	kniznica.zobrazKnihyPodlaZanru(scanner);
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                default:
                    System.out.println("Neplatna volba, zadajte cislo od 1 do 12.");
            }
        }
		
	}
}

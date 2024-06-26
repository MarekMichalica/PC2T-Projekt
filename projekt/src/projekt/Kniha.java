package projekt;

public class Kniha {
	private String nazov;
	private String autor;
	private int rokVydania;
	private boolean dostupna;
	private Zaner zaner;
	private int hodnotenie;
	
	public Kniha(String nazov, String autor, int rokVydania, boolean dostupna, Zaner zaner, int hodnotenie) {
		this.nazov = nazov;
		this.autor = autor;
		this.rokVydania = rokVydania;
		this.dostupna = dostupna;
		this.zaner = zaner;
		this.hodnotenie = hodnotenie;
	}
	
	public String getNazov() {
		return nazov;
	}
	
	public void setNazov(String nazov) {
		this.nazov = nazov;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public int getRokVydania() {
		return rokVydania;
	}
	
	public void setRokVydania(int rokVydania) {
		this.rokVydania = rokVydania;
	}
	
	public boolean getDostupna() {
		return dostupna;
	}
	
	public void setDostupna(boolean dostupna) {
		this.dostupna = dostupna;
	}
	
	public Zaner getZaner() {
		return zaner;
	}
	
	public void setZaner(Zaner zaner) {
		this.zaner = zaner;
	}
	
	public int getHodnotenie() {
		return hodnotenie;
	}
	
	public void setHodnotnie(int hodnotenie) {
		this.hodnotenie = hodnotenie;
	}
}


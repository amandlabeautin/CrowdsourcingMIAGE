package DataBean;

public class Attribut {

	private int id;
	private Pair p;
	private int pairId;
	private String nomAttribut;
	private String elem1;
	private String elem2;
	private double val;
	private int nbrVote;
	private int tauxVote;
	
	public Attribut() {
	}
	
	public Attribut(Pair p, String nomAttribut, String elem1, String elem2, double val, int nbrVote, int tauxVote){
		this.p = p;
		this.nomAttribut = nomAttribut;
		this.elem1 = elem1;
		this.elem2 = elem2;
		this.val = val;
		this.nbrVote = nbrVote;
		this.tauxVote = tauxVote;
	}

	
	public Attribut(int pairId, String nomAttribut, String elem1, String elem2, double val, int nbrVote, int tauxVote){
		this.pairId = pairId;
		this.nomAttribut = nomAttribut;
		this.elem1 = elem1;
		this.elem2 = elem2;
		this.val = val;
		this.nbrVote = nbrVote;
		this.tauxVote = tauxVote;
	}
	
	public Attribut(Pair p, String elem1, String elem2, double val, int nbrVote, int tauxVote){
		this.p = p;
		this.elem1 = elem1;
		this.elem2 = elem2;
		this.val = val;
		this.nbrVote = nbrVote;
		this.tauxVote = tauxVote;
	}
	
	public int getTauxVote() {
		return tauxVote;
	}
	
	public void setTauxVote(int tauxVote) {
		this.tauxVote = tauxVote;
	}
	
	public String getNomAttribut() {
		return nomAttribut;
	}
	
	public void setNomAttribut(String nomAttribut) {
		this.nomAttribut = nomAttribut;
	}
	
	public void setP(Pair p) {
		this.p = p;
	}
	
	public int getNbrVote() {
		return nbrVote;
	}
	
	public void setNbrVote(int nbrVote) {
		this.nbrVote = nbrVote;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public Pair getP() {
		return p;
	}
	
	public String getElem1() {
		return elem1;
	}
	
	public String getElem2() {
		return elem2;
	}
	
	public double getVal() {
		return val;
	}
	
	public void setVal(double val) {
		this.val = val;
	}
	
	public void setElem1(String elem1) {
		this.elem1 = elem1;
	}
	
	public void setElem2(String elem2) {
		this.elem2 = elem2;
	}
	@Override
	public String toString() {
		return "### " + elem1 + " # " + elem2 + " # " + val + " ###";
	}
}

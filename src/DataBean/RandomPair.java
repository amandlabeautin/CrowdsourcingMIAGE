package DataBean;

public class RandomPair {

	private int idPair;
	private Attribut Attribut1;
	private Attribut Attribut2;
	private Attribut Attribut3;
	private Attribut Attribut4;
	private Attribut Attribut5;
	private double val;
	
	public RandomPair() {
		
	}

	public RandomPair(int idPair, Attribut Attribut1, Attribut Attribut2, Attribut Attribut3, Attribut Attribut4, Attribut Attribut5) {
		this.idPair = idPair;
		this.Attribut1 = Attribut1;
		this.Attribut2 = Attribut2;
		this.Attribut3 = Attribut3;
		this.Attribut4 = Attribut4;
		this.Attribut5 = Attribut5;
	}
	
	public Attribut getAttribut1() {
		return Attribut1;
	}
	
	public double getVal() {
		return val;
	}
	public void setVal(double val) {
		this.val = val;
	}
	public Attribut getAttribut2() {
		return Attribut2;
	}
	
	public Attribut getAttribut3() {
		return Attribut3;
	}
	
	public Attribut getAttribut4() {
		return Attribut4;
	}
	
	public Attribut getAttribut5() {
		return Attribut5;
	}
	
	public int getIdPair() {
		return idPair;
	}
	
	public void setAttribut1(Attribut Attribut1) {
		this.Attribut1 = Attribut1;
	}
	
	public void setAttribut2(Attribut Attribut2) {
		this.Attribut2 = Attribut2;
	}
	
	public void setAttribut3(Attribut Attribut3) {
		this.Attribut3 = Attribut3;
	}
	
	public void setAttribut4(Attribut Attribut4) {
		this.Attribut4 = Attribut4;
	}
	
	public void setAttribut5(Attribut Attribut5) {
		this.Attribut5 = Attribut5;
	}
	
	public void setIdPair(int idPair) {
		this.idPair = idPair;
	}
	
}

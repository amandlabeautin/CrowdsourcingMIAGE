package DataBean;

public class LHS_RHS {

	private int id;
	private int IdPair;
	private String LHS;
	private String RHS;
	
	public LHS_RHS() {
	}
	
	public LHS_RHS(int IdPair, String LHS, String RHS){
		this.IdPair = IdPair;
		this.LHS = LHS;
		this.RHS = RHS;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getIdPair() {
		return IdPair;
	}
	
	public String getLHS() {
		return LHS;
	}
	
	public String getRHS() {
		return RHS;
	}
	
	public void setIdPair(int idPair) {
		IdPair = idPair;
	}
	
	public void setLHS(String lHS) {
		LHS = lHS;
	}
	
	public void setRHS(String rHS) {
		RHS = rHS;
	}
}

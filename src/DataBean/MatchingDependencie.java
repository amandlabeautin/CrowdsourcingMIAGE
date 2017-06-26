package DataBean;

public class MatchingDependencie {

	private int id;
	private String Entry1;
	private String Entry2;
	
	public MatchingDependencie() {
	}
	
	public MatchingDependencie(String Entry1, String Entry2){
		this.Entry1 = Entry1;
		this.Entry2 = Entry2;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEntry1() {
		return Entry1;
	}
	
	public String getEntry2() {
		return Entry2;
	}
	
	public void setEntry1(String entry1) {
		Entry1 = entry1;
	}
	
	public void setEntry2(String entry2) {
		Entry2 = entry2;
	}
}

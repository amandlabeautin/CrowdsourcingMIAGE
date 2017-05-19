package DataBean;

public class User {
	
	private int id;
	private String login;
	private String password;
	private boolean isAdministrator;;
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param id
	 * @param login
	 * @param password
	 * @param isAdministrator
	 */
	public User(int id, String login, String password, boolean isAdministrator) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.isAdministrator = isAdministrator;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the isAdministrator
	 */
	public boolean isAdministrator() {
		return isAdministrator;
	}
	/**
	 * @param isAdministrator the isAdministrator to set
	 */
	public void setAdministrator(boolean isAdministrator) {
		this.isAdministrator = isAdministrator;
	}
	
}

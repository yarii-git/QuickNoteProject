package model;

/**
 * 
 * @author Yarií Soto - Nasera Boulehoual
 * @version 1.0, 24 May 2023
 */
public class User {
	/**
	 * Variable to store the user name.
	 */
	private String name;
	
	/**
	 * Variable to store the username.
	 */
	private String username;
	
	/**
	 * Variable to store the user password.
	 */
	private String pass;
	
	/**
	 * Variable to store the user email.
	 */
	private String email;
	
	/**
	 * User constructor.
	 * @param name - an user name.
	 * @param username - an username to login.
	 * @param pass - an user password.
	 * @param email - an user email.
	 */
	public User(String name, String username, String pass, String email) {
		super();
		this.name = name;
		this.username = username;
		this.pass = pass;
		this.email = email;
	}
	
	/**
	 * User name getter.
	 * @return - the user name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * User name setter.
	 * @param name - the user name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * username getter.
	 * @return - the username.
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * username setter.
	 * @param username - the username.
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Password getter.
	 * @return - the user password.
	 */
	public String getPass() {
		return pass;
	}
	
	/**
	 * Password setter.
	 * @param pass - the user password.
	 */
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	/**
	 * Email getter.
	 * @return - the user email.
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Email setter.
	 * @param email - the user email.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}

import java.util.ArrayList;

public class Teacher {
	
	//Teacher Properties
	private String lastName;
	private String firstName;
	private String schoolName;
	private String userName;
	private char[] password;
	private String securityAnswer1;
	private String securityQuestion1;
	private String securityAnswer2;
	private String securityQuestion2;
	private ArrayList<Class> classes = new ArrayList<>();
	
	/**
	 * Class constructor: generates instance of Teacher class. 
	 * @param lN		last name
	 * @param fN		first name
	 * @param sN		school name
	 * @param uN		username
	 * @param password	password
	 * @param SA1	security answer 1
	 * @param SQ1	security question 1
	 * @param SA2	security answer 2
	 * @param SQ2	security question 2
	 */
	public Teacher(String lN, String fN, String sN, String uN, char[] password, 
			String SA1, String SQ1, String SA2, String SQ2) {
		
		lastName = lN;
		firstName = fN;
		schoolName = sN;
		userName = uN;
		this.password = password;
		securityAnswer1 = SA1;
		securityQuestion1 = SQ1;
		securityAnswer2 = SA2;
		securityQuestion2 = SQ2;
	}
	
	/**
	 * The getLastName method returns the last name of Teacher
	 * @return	lastName
	 */
	public String getLastName() {
		return lastName;
	}
	
	/**
	 * The setLastName method sets the last name of Teacher
	 * @param lastName
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	/**
	 * The getFirstName method returns the first name of Teacher
	 * @return firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/**
	 * The setFirstName method set the first name of Teacher
	 * @param firstName
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	/**
	 * The getSchoolName method returns the school name of Teacher
	 * @return	schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}
	
	/**
	 * The setSchoolName method sets the school name of Teacher
	 * @param schoolName
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	/**
	 * The getUsername method returns username of Teacher
	 * @return	userName
	 */
	public String getUsername() {
		return userName;
	}
	
	/**
	 * The setUsername method sets the username of Teacher
	 * @param userName
	 */
	public void setUsername(String userName) {
		this.userName = userName;
	}
	
	/**
	 * The getSecurityAnswer1 method returns security answer 1 of Teacher
	 * @return	securityAnswer1
	 */
	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	
	/**
	 * The setSecurityAnswer1 method sets the security answer 1 of Teacher
	 * @param sA1
	 */
	public void setSecuirtyAnswer1(String sA1) {
		securityAnswer1 = sA1;
	}
	
	/**
	 * The getSecurityQuestion1 method returns security question 1 of Teacher
	 * @return	securityQuestion1
	 */
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}
	
	/**
	 * The setSecurityAnswer1 method sets the security question 1 of Teacher 
	 * @param sQ1
	 */
	public void setSecurityQuestion1(String sQ1) {
		securityQuestion1 = sQ1;
	}
	
	/**
	 * The getSecurityAnswer2 method returns security answer 2 of Teacher
	 * @return	securityAnswer2
	 */
	public String getSecurityAnswer2() {
		return securityAnswer2;
	}
	
	/**
	 * The setSecurityAnswer2 method sets the security answer 2 of Teacher
	 * @param sA2
	 */
	public void setSecurityAnswer2(String sA2) {
		securityAnswer2 = sA2;
	}
	
	/**
	 * The getSecurityQuestion2 method returns security question 2 of Teacher
	 * @return	securityQuestion2
	 */
	public String getSecurityQuestion2() {
		return securityQuestion2;
	}
	
	/**
	 * The setSecurityQuestion2 method sets the security question 2 of Teacher
	 * @param sQ2
	 */
	public void setSecurityQuestion2(String sQ2) {
		securityQuestion2 = sQ2;
	}
	
	/**
	 * The addClass method adds a Class to ArrayList<Class> classes
	 * @param c
	 */
	public void addClass(Class c) {
		classes.add(c);
		System.out.println(classes.size());
	}
	
	/**
	 * The removeClass method removes a Class from ArrayList<Class> classes
	 * @param c
	 */
	public void removeClass(Class c) {
		classes.remove(c);
	}
	
	/**
	 * The getClasses method returns the ArrayList<Class> classes for Teacher
	 * @return	classes
	 */
	public ArrayList<Class> getClasses() {
		return classes;
	}
	
	/**
	 * The setPassword method sets the password for Teacher
	 * @param password
	 */
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	/**
	 * The getPassword method returns the password for Teacher
	 * @return	password
	 */
	public char[] getPassword() {
		return password;
	}
		

}

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

public class Student {
	
	private String lastName, firstName, schoolName, username, grade;
	private String securityAnswer1, securityQuestion1, securityAnswer2, securityQuestion2;
	private char[] password;
	private ArrayList<Class> classes;
	private ArrayList<ImageIcon> characters;
	private ArrayList<Character> characters2;
	private Image character;
	private int score, time;
	private String[] imageNames = {"character2", "character3", "character4"};	 
	private Character currentCharacter;
	
	public Student(String lN, String fN, String sN, char[] password, String sA1, String sQ1, String sA2, String sQ2, String grade) {
		
		lastName = lN;
		firstName = fN;
		schoolName = sN;
		this.password = password;
		securityAnswer1 = sA1;
		securityQuestion1 = sQ1;
		securityAnswer2 = sA2;
		securityQuestion2 = sQ2;
		this.grade = grade;
		score = 73647593;
		classes = new ArrayList<>();
		characters = new ArrayList<>();
		characters2 = new ArrayList<>();
		Class personal = new Class("Personal");
		classes.add(personal);
		generateCharacters();
		generateUsername();
		
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	
	public char[] getPassword() {
		return password;
	}
	
	public void setPassword(char[] password) {
		this.password = password;
	}
	
	public String getSecurityAnswer1() {
		return securityAnswer1;
	}
	
	public void setSecuirtyAnswer1(String sA1) {
		securityAnswer1 = sA1;
	}
	
	public String getSecurityQuestion1() {
		return securityQuestion1;
	}
	
	public void setSecurityQuestion1(String sQ1) {
		securityQuestion1 = sQ1;
	}
	
	public String getSecurityAnswer2() {
		return securityAnswer2;
	}
	
	public void setSecurityAnswer2(String sA2) {
		securityAnswer2 = sA2;
	}
	
	public String getSecurityquestion2() {
		return securityQuestion2;
	}
	
	public void setSecurityQuestion2(String sQ2) {
		securityQuestion2 = sQ2;
	}
	
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	public String getGrade() {
		return grade;
	}
	
	public void generateUsername() {
		String first = firstName.substring(0, 1);
		String last = lastName.substring(0, 1);
		Random rand = new Random();
		int randomNumber = rand.nextInt(9999);
		username = first + last + Integer.toString(randomNumber);
	}
	
	public String getUsername() {
		return username;
	}
	
	public void addClass(Class newClass) {
		classes.add(newClass);
	}
	
	public ArrayList<Class> getClasses() {
		return classes;
	}
	
	public void removeClass(Class removeClass) {
		classes.remove(removeClass);
	}
	
	public void generateCharacters() {
		
		ImageIcon character1 = new ImageIcon("Resource/character2.png");
		ImageIcon character2 = new ImageIcon("Resource/character3.png");
		ImageIcon character3 = new ImageIcon("Resource/character4.png");
		//characters.add(character1);
		//characters.add(character2);
		//characters.add(character3);
		character = character1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		currentCharacter = new Character("character2");
		characters2.add(currentCharacter);
		Character c2 = new Character("character3");
		characters2.add(c2);
		Character c3 = new Character("character4");
		characters2.add(c3);
	}
	
	
	public void setCharacter(ImageIcon image) {
		character = image.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
	}
	
	public Image getCharacter() {
		return character;
	}
	
	public String[] getImageNames() {
		return imageNames;
	}
	
	public void addCharacter(Character c) {
		characters2.add(c);
	}
	
	public ArrayList<Character> getCharacters() {
		return characters2;
	}
	
	public Character getCharacterIndex(int index) {
		return characters2.get(index);
	}

	public Character getCurrentCharacter() {
		return currentCharacter;
	}
	
	public void setCharacter(Character c) {
		currentCharacter = c;
	}
}

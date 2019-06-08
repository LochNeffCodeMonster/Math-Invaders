import javax.swing.ImageIcon;

public class Character {

	private String name;
	private ImageIcon characterIcon;
	
	/**
	 * Class constructor: generates an instance of Character
	 * @param name
	 */
	public Character(String name) {
		this.name = name;
	}
	
	/**
	 * The getCharacterName method returns name of Character
	 * @return	name
	 */
	public String getCharacterName() {
		return name;
	}
}

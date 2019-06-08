import java.util.ArrayList;

public class FlashCard {

	private String subject;
	private ArrayList<String[]> flashCards;
	private int totalCards;
	
	public FlashCard(String name, ArrayList<String[]> flashCards) {
		this.subject = name;
		this.flashCards = flashCards;	
		totalCards = flashCards.size();
	}
	
	public FlashCard(String name) {
		this.subject = name;
		flashCards = new ArrayList<>();
	}
	
	public ArrayList<String[]> getFLashCards() {
		return flashCards;
	}
	
	public void addCard(String[] card) {
		flashCards.add(card);
		totalCards++;
	}
	
	public void setName(String name) {
		subject = name;
	}
	
	public String getName() {
		return subject;
	}

	public String getTotalCards() {
		return Integer.toString(totalCards);
	}
	
}

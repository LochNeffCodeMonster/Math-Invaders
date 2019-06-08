import java.awt.Image;

import javax.swing.ImageIcon;

public class Operator {
	private int operator_x;
	private int operator_y;
	private static int numberOfOperators;
	private String operatorType;
	private Image operatorImage;
	private Boolean isActive = false;
	
	public Operator() {
		numberOfOperators++;
		if (numberOfOperators == 1) {
			ImageIcon i = new ImageIcon("Resource/plus.jpg");
			operatorImage = i.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
			operatorType = "+";
			operator_x = 480;
			operator_y = 215;
		}
		if (numberOfOperators == 2) {
			ImageIcon i = new ImageIcon("Resource/minus.jpg");
			operatorImage = i.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
			operatorType = "-";
			operator_x = 445;
			operator_y = 250;
		}
		if (numberOfOperators == 3) {
			ImageIcon i = new ImageIcon("Resource/multiply.jpg");
			operatorImage = i.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
			operatorType = "x";
			operator_x = 480;
			operator_y = 285;
		}
		if (numberOfOperators == 4) {
			ImageIcon i = new ImageIcon("Resource/divide.jpg");
			operatorImage = i.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
			operatorType = "÷";
			operator_x = 515;
			operator_y = 250;
		}
		
	}
	public void cycle() {
		if (operator_x == 480 && operator_y == 215) {
			isActive = false;
			operator_x = 445;
			operator_y = 250;
		}
		else if (operator_x == 445 && operator_y == 250) {
			isActive = true;
			operator_x = 480;
			operator_y = 285;
		}
		else if (operator_x == 480 && operator_y == 285) {
			isActive = false;
			operator_x = 515;
			operator_y = 250;
		}
		else if (operator_x == 515 && operator_y == 250) {
			isActive = false;
			operator_x = 480;
			operator_y = 215;
		}
	}
	public void setActive() {
		isActive = true;
	}
	public Boolean isActive() {
		return isActive;
	}
	public Image getOperatorImage() {
		return operatorImage;
	}
	public String getOperatorType() {
		return operatorType;
	}
	public int getOperatorX() {
		return operator_x;
	}
	public int getOperatorY() {
		return operator_y;
	}
}

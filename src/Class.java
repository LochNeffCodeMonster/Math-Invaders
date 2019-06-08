import java.awt.Color;
import java.awt.Component;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class Class  {
		
		private String className;
		private JTable classTable, flashTable;
		private String classCode;
		private SecretKey secretKey;
		
		private DefaultTableModel classModel;
		private DefaultTableModel flashModel = new DefaultTableModel();
		
		private ArrayList<Student> students = new ArrayList<>();
		private ArrayList<FlashCard> flashCards = new ArrayList<>();
		
		
		private final String[] columnNames = {"Last",
                "First",
                "Grade",
                "Score",
                "Time"};
		
		private final String[] columnNames2 = {"Subject",
				"Total"};
		
		private final Object[][] empty = {{}};
		
		private final String letters = "abcdefghijklmnopqrstuvwxyz123456789";
		private final char[] alphaNumeric = (letters + letters.toUpperCase() + "123456789").toCharArray();
		
		
		
		
		//--------------------------------------------------------------------------------------------------
		
		/**
		 * Class constructor: generates instance of Class
		 * @param name	name of class
		 * @param table		
		 */
		public Class(String name, JTable table, JTable table2) {
			className = name;
			classTable = table;
			flashTable = table2;
			generateClassCode();
		}
		
		/**
		 * Class constructor: generates instance of Class
		 * @param name	name of class
		 */
		public Class(String name) {
			
			className = name;
			
			classModel = new DefaultTableModel(20, 5);
			classModel.setColumnIdentifiers(columnNames);
			classTable = new JTable(classModel);
			
			flashModel = new DefaultTableModel();
			flashModel.setColumnIdentifiers(columnNames2);
			flashTable = new JTable(flashModel);
		
			generateClassCode();
			/*
			//Add column names to JTable
			for (int i = 0; i < columnNames.length; i++) {
				classModel.addColumn(columnNames[i]);
			}
			
			for (int j = 0; j < columnNames2.length; j++) {
				flashModel.addColumn(columnNames2[j]);
			}
			*/
		}
	
		/**
		 * The getName method returns the name of Class
		 * @return	className
		 */
		public String getName() {
			return className;
		}
		
		/**
		 * The setName method sets the name of Class
		 * @param className
		 */
		public void setName(String className) {
			this.className = className;
		}
	
		/**
		 * The updateClassTable method updates the Class JTable, when a student signs up for the Class
		 * @param s
		 */
		public void updateClassTable(Student s) {
			
			String[] temp = new String[5];
			temp[0] = s.getLastName();
			temp[1] = s.getFirstName();
			temp[2] = s.getSchoolName();
			temp[3] = Integer.toString(s.getScore());
			temp[4] = Integer.toString(s.getTime());
			
			classModel.addRow(temp);
		}
		
		/**
		 * The getClassTable method returns the classTable JTable
		 * @return classTable
		 */
		public JTable getClassTable() {
			return classTable;
		}
		
		/**
		 * The updateFlashTable method updates the flashModel with new study material (FlashCard)
		 * @param fC
		 */
		public void updateFlashTable(FlashCard fC) {
			
			String[] temp = new String[2];
			temp[0] = fC.getName();
			temp[1] = fC.getTotalCards();
			
			flashModel.addRow(temp);
		}
		
		/**
		 * The getFlashTable method returns the JTable containing study material assigned to Class
		 * @return	flashTable
		 */
		public JTable getFlashTable() {
			return flashTable;
		}
		
		/**
		 * The getFlashCards method returns an ArrayList<FlashCard> flashCards
		 * @return	flashCards
		 */
		public ArrayList<FlashCard> getFlashCards() {
			return flashCards;
		}
		
		public void removeFlashCard(FlashCard fC, int row) {
			flashCards.remove(fC);
			flashModel.removeRow(row);
		}
		
		/**
		 * The generateClassCode method generates a secret key specific to instance of the Class
		 */
		public void generateClassCode() {
			
			StringBuilder result = new StringBuilder();
			for (int i = 0; i < 7; i++) {
				result.append(alphaNumeric[new Random().nextInt(alphaNumeric.length)]);
			}
			classCode = result.toString();
		}
		
		/**
		 * The getClassCode method returns the classCode of the Class
		 * @return	classCode
		 */
		public String getClassCode() {
			return classCode;
		}
		
		/**
		 * The addStudent method adds a student to classTable JTable
		 * @param student
		 */
		public void addStudent(Student student) {
			students.add(student);
			updateClassTable(student);
		}
		
		/**
		 * The addFlashCard method adds a FlashCard to ArrayList<FlashCard> flashCards and updates JTable
		 * @param fC
		 */
		public void addFlashCard(FlashCard fC) {
			flashCards.add(fC);
			updateFlashTable(fC);
		}
		
		/**
		 * The getColumnNames method returns the column names of the JTable
		 * @return
		 */
		public String[] getColumnNames() {
			return columnNames;
		}
		
		@Override
		public String toString() {
			return className;
		}
	
	}

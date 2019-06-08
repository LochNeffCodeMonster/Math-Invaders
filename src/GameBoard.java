import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferStrategy;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.UIDefaults;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import javafx.scene.control.*;
public class GameBoard extends JFrame implements ActionListener, MouseListener, FocusListener {

	public final static int boardWidth = 1000, boardHeight = 700;
	
	private String gameName = "Education Game";
	
	//Border & Font Properties
	private Border raisedbevel = BorderFactory.createRaisedSoftBevelBorder();
	private Border loweredBevel = BorderFactory.createEtchedBorder();
	private Border compound = BorderFactory.createCompoundBorder(raisedbevel, loweredBevel);
	private Font masterFont = new Font("Candara", Font.BOLD, 14);
	
	//LoginScreen Properties
	public final static int loginWidth = 600, loginHeight = 350;
	private JFrame loginWindow;
	private JTextField usernameTF;
	private JPasswordField passwordPF;
	private boolean login = false;
	private ImageIcon passwordIcon = new ImageIcon("Resource/password.png");
	private Image passwordImage = passwordIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
	
	//NewUserScreen Properties
	public final static int newUserWidth = 400, newUserHeight = 125;
	private JFrame newUserWindow;
	
	//NewStudentWindow Properties
	public final static int newStudentWidth = 500, newStudentHeight = 450;
	private JFrame newStudentWindow;
	private JLabel sFirstNameL, sLastNameL, sSchoolNameL, sGradeL, sPasswordL1, sPasswordL2, sSecurityQ1L, sSecurityQ2L;
	private JTextField sFirstNameTF, sLastNameTF, sSchoolNameTF, sSecurityQ1TF, sSecurityQ2TF;
	private JPasswordField sPasswordPF1, sPasswordPF2;
	@SuppressWarnings("rawtypes")
	private JComboBox sSecurityList1, sSecurityList2, sGradeList;
	private boolean studentAccount = false;
	private boolean newStudent = false;
	
	//NewTeacherWindow Properties
	public final static int newTeacherWidth = 500, newTeacherHeight = 450;
	private JFrame newTeacherWindow;
	private JLabel tFirstNameL, tLastNameL, tUsernameL, tSchoolNameL, tPasswordL1, tPasswordL2, tSecurityQ1L, tSecurityQ2L;
	private JTextField tFirstNameTF, tLastNameTF, tUsernameTF, tSchoolNameTF, tSecurityQ1TF, tSecurityQ2TF;
	private JPasswordField tPasswordPF1, tPasswordPF2;
	@SuppressWarnings("rawtypes")
	private JComboBox tSecurityList1, tSecurityList2;
	private boolean teacherAccount = false; 
	private boolean newTeacher = false;
	
	//TeacherPortalWindow Properties
	public final static int teacherPortalWidth = 1000, teacherPortalHeight = 600;
	private JFrame tPortalWindow;
	private JPanel teacherPortal;
	private JTabbedPane tTabbedPane;
	private JComponent tProfileComponent, tClassComponent;
	private Teacher teacher;
	
	//StudentPortalWindow Properties
	public final static int studentPortalWidth = 1000, studentPortalHeight = 600;
	private JFrame sPortalWindow;
	private JTabbedPane sTabbedPane;
	private JPanel studentPortal;
	private JComponent sProfileComponent;
	private Student student;
	private JLabel characterImage;
	
	//MakeTeacherProfilePanel Properties
	private FlashCard newFlashCard;
	
	//MakeTeacherClassPanel Properties
	private JPanel tClassPanel;
	private JTabbedPane tClassTabs;
	private ArrayList<Class> teacherClasses;
	
	//UpdateTeacherProfileWindow Properties
	public final static int updateTeacherPWidth = 350, updateTeacherPHeight = 250;
	private JFrame updateTeacherPWindow;
	private boolean tUpdateProfile = false;
	
	//UpdateTeacherSecurityWindow Properties
	public final static int updateTeacherSWidth = 425, updateTeacherSHeight = 400;
	private JFrame updateTeacherSWindow;
	private JLabel tOldPasswordL, tNewPasswordL1, tNewPasswordL2;
	private JPasswordField tOldPasswordPF;
	private boolean tUpdateSecurity = false;
	
	//UpdateStudentProfileWindow Properties
	public final static int updateStudentPWidth = 350, updateStudentPHeight = 250;
	private JFrame updateStudentPWindow;	
	private boolean sUpdateProfile = false;
	
	//UpdateStudentSecurityWindow Properties
	public final static int updateStudentSWidth = 425, updateStudentSHeight = 400;
	private JFrame updateStudentSWindow;
	private JLabel sOldPasswordL, sNewPasswordL1, sNewPasswordL2;
	private JPasswordField sOldPasswordPF;
	private boolean sUpdateSecurity = false;

	//tAddClassWindow Properties
	public final static int addClassTeacherWidth = 300, addClassTeacherHeight = 200;
	private JFrame tAddClassWindow;
	private JLabel classNameL;
	private JTextField classNameTF;

	//tDeleteClassWindow Properties
	public final static int deleteClassTeacherWidth = 350, deleteClassTeacherHeight = 250;
	private JFrame tDeleteClassWindow;
	private DefaultListModel<String> deleteClassModel;
    private JList<String> deleteClassList;
    private Class deleteSelectedClass;
    
    //sAddClassWindow Properties
    public final static int addClassStudentWidth = 300, addClassStudentHeight = 200;
  	private JFrame sAddClassWindow;
  	private JTextField classKeyTF;
  	
  	//sDeleteClassWindow Properties
  	private JFrame sDeleteClassWindow;
  	private ArrayList<Class> studentClasses;
  	
    //CreateStudyNameWindow Properties
  	public final static int createStudyWidth = 300, createStudyHeight = 200;
  	private JFrame createStudyNameWindow;
  	
    //CreateStudyMaterialWindow Properties
  	public final static int createStudyMaterialWidth = 600, createStudyMaterialHeight = 400;
    private JFrame createStudyMaterialWindow;
	private JTextField subjectNameTF;
    private JTextArea leftTA, rightTA;
   
	//tAssignStudyWindow Properties
    public final static int assignStudyMaterialWidth = 350, assignStudyMaterialHeight = 250;
    private JFrame tAssignStudyWindow;
    private DefaultListModel<String> tAddStudyModel;
    
    //sAssignStudyWindow Properties
    public final static int assignStudyWidth = 350, assignStudyHeight = 250;
    private JFrame sAssignStudyWindow;
    private DefaultListModel<String> sAddStudyModel;
    
    //tDeleteStudyWindow Properties
    public final static int deleteStudyMaterialWidth = 500, deleteStudyMaterialHeight = 300;
    private JFrame tDeleteStudyWindow;
    private ArrayList<FlashCard> tFlashCards;
    private Class tSelectedClass;
    DefaultListModel<String> tStudyModel, tClassModel; 
    
    //sDeleteStudyWindow Properties
    public final static int deleteStudyWidth = 500, deleteStudyHeight = 300;
    private JFrame sDeleteStudyWindow;
    private Class sSelectedClass;
    private ArrayList<FlashCard> sFlashCards;
    private DefaultListModel<String> sStudyModel, sClassModel;
    private boolean isClassSelected = false;
    private int previousIndex;
    private Class previousClass;
    
	//changeCharacter Properties
    private JFrame changeCharacterWindow;
	@SuppressWarnings("rawtypes")
	private JList characterList;
	private JLabel picture;
	private DefaultListModel<String> characterModel;
	private ArrayList<Character> studentCharacters;
	private boolean changeC = false;
	
	//GameWindow Properties
	private JFrame gameWindow;

	//Temporary Variables
    JTable[] classTables;
    private JTable table1, table2;
    
	String[] columnNames = {"Last",
            "First",
            "Grade",
            "Score",
            "Time"};
	
    
    Object[][] data1 = {
    	    {"Neff", "Jon", "Senior", "84573", "1:23"},
    	    {"Hawkins", "Daniel", "Senior", "93747", "5:23"},
    	    {"McMillan", "Alec", "Senior", "4875456", "10:50"},
    	    {"Brunk", "David", "Senior", "7348", "0:45"},
    	    {"Caldwell", "Riley", "Senior", "793647", "2:30"},
    	    {"Deets", "Britton", "Senior", "72472", "3:38"},
    	    {"Devaul", "Cameron", "Senior", "4937284", "5:26"},
    	    {"Gemuenden", "Andrew", "Senior", "437268", "3:46"},
    	    {"Goynes", "Corey", "Senior", "879423", "4:49"},
    	    {"Harris", "Matthew", "Senior", "64329", "2:57"},
    	    {"Jones", "Lindsey", "Senior", "2384892", "6:23"},
    	    {"Ou", "Jiajian", "Senior", "4293749", "5:10"},
    	    {"Perryman", "Dakota", "Senior", "908927", "6:44"},
    	    {"Poole", "Joshua", "Senior", "246239", "1:15"},
    	    {"Robinson", "Chase", "Senior", "23423", "0:45"},
    	    {"Stephens", "Jonathan", "Senior", "0", "13:24"},
    	    {"Thompson", "Mark", "Senior", "23424", "3:12"},
    	    {"Tibbetts", "Travis", "Senior", "56654654", "10:12"}
    	};
    
    
    Object[][] data2 = {
    		 {"Neff", "Josh",
	    	 "Junior", "1000", "1:23"},
	    	 {"Hawk", "Dan",
	    	 "Junior", "10000", "5:23"},
	    	 {"Miller", "Alex",
	    	 "Junior", "1000000", "10.50"},
	};
	
    //Unimplemented methods. 
	JPanel mainPanel = new JPanel();
	JPanel healthPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel powerPanel = new JPanel();
	JPanel livesPanel = new JPanel();
	JPanel timePanel = new JPanel();
	JPanel weaponPanel = new JPanel();
	JPanel menuPanel = new JPanel();
	damageHandler dH = new damageHandler();
	Container con;
	public int hp = 10;
	ArrayList<JTextField> healthBar = new ArrayList();

	/**
	 * The GameBoard constructor generates the loginScreen GUI, allowing the user to login or create a new account. 
	 */
	
	public GameBoard() {
		
		// Initiate LoginScreen
		loginScreen();
	}
	
	/**
	 * The main method is the entry point to the program.
	 * @param args
	 */
	
	public static void main(String[] args) {
		new GameBoard();
	}
	
	/**
	 * The loginScreen method generates the GUI, allowing exisiting users to enter their username and password,
	 * 		and forward new users to create an account. 
	 * 		
	 */
	public void loginScreen() {
		
		//Initialize windows
		Dimension loginSize = new Dimension(loginWidth, loginHeight);
		loginWindow = new JFrame("Login");
		loginWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginWindow.setSize(loginSize);
		loginWindow.setBackground(new Color(51, 153, 255));
		loginWindow.setLayout(new BorderLayout());
		loginWindow.setResizable(false);
		loginWindow.setLocationRelativeTo(null);
		
		//Set Background
		JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		background.setBounds(0, 0, loginWidth, loginHeight);
		loginWindow.add(background);
		
		//LoginIcon JLabel
		JLabel loginIcon = new JLabel(new ImageIcon("Resource/EOS-icon.png"));
		loginIcon.setBounds(255, 30, 90, 90);
		background.add(loginIcon);
		
		//Username JLabel
		JLabel usernameL = new JLabel("Username");
		usernameL.setForeground(Color.LIGHT_GRAY);
		usernameL.setBackground(Color.WHITE);
		usernameL.setFont(masterFont);
		usernameL.setOpaque(true);
		usernameL.setEnabled(false);
		usernameL.setBounds(215, 145, 200, 35);
		background.add(usernameL);
		
		//UserIcon JLabel
		ImageIcon userIcon = new ImageIcon("Resource/username.png");
		Image userImage = userIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		JLabel userIconL = new JLabel(new ImageIcon(userImage));
		userIconL.setBounds(180, 145, 35, 35);
		userIconL.setOpaque(true);
		background.add(userIconL);
		
		//Username JTextField
		usernameTF = new JTextField();
		usernameTF.setBounds(215, 145, 200, 35);
		usernameTF.setOpaque(false);
		usernameTF.addMouseListener(this);
		background.add(usernameTF);
		
		//Password JLabel
		JLabel passwordL = new JLabel("Password");
		passwordL.setForeground(Color.LIGHT_GRAY);
		passwordL.setBackground(Color.WHITE);
		passwordL.setFont(masterFont);
		passwordL.setOpaque(true);
		passwordL.setEnabled(false);
		passwordL.setBounds(215, 210, 200, 35);
		background.add(passwordL);
		
		//PasswordIcon JLabel
		ImageIcon passwordIcon = new ImageIcon("Resource/password.png");
		Image passwordImage = passwordIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
		JLabel passwordIconL = new JLabel(new ImageIcon(passwordImage));
		passwordIconL.setBounds(180, 210, 35, 35);
		passwordIconL.setOpaque(true);
		background.add(passwordIconL);

		//Password JPasswordField
		passwordPF = new JPasswordField();
		passwordPF.setBounds(215, 210, 200, 35);
		passwordPF.setForeground(Color.BLACK);
		passwordPF.setOpaque(false);
		passwordPF.addMouseListener(this);
		background.add(passwordPF);
		
		//Login JButton
		JButton loginB = new JButton("LOGIN");
		loginB.setBounds(200, 280, 95, 45);
		loginB.setFont(masterFont);
		loginB.setOpaque(true);
		loginB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	login = true;
	        	if (usernameTF.getText().equals("jmn7080")) {
	        		
	        		//Retrieve password input by user
				char[] input = passwordPF.getPassword();
				char[] correctPassword = { 's', 'a', 'v', 'e', 'm', 'e'};
					
				if (isPasswordCorrect(input, correctPassword)) {
					loginWindow.dispose();
					createTempData();
					teacherPortalScreen();
					
				} else {
					JOptionPane.showMessageDialog(loginWindow,"Incorrect login or password",
							"Error",JOptionPane.ERROR_MESSAGE);
					passwordPF.setText("");
			    	}
	        } else {
				JOptionPane.showMessageDialog(loginWindow,"Invalid username",
						"Error",JOptionPane.ERROR_MESSAGE);
				usernameTF.setText("");
				passwordPF.setText("");
	        }
	        }
	    	});
		background.add(loginB);
		
		//Sign-up JButton
		JButton signupB = new JButton("SIGN UP");
		signupB.setBounds(305, 280, 95, 45);
		signupB.setFont(masterFont);
		signupB.setOpaque(true);
		signupB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	loginWindow.dispose();
	    		newUserScreen();
	        }
	    	});
		background.add(signupB);
		
		//Forgot Username JButton
		JButton forgotUsernameB = new JButton("Forgot your username?");
		forgotUsernameB.setBounds(200, 180, 200, 35);
		forgotUsernameB.setFont(new Font("Candara", Font.PLAIN, 12));
		forgotUsernameB.setOpaque(false);
		forgotUsernameB.setContentAreaFilled(false);
		forgotUsernameB.setBorderPainted(false);
		forgotUsernameB.setForeground(Color.WHITE);
		forgotUsernameB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	loginWindow.dispose();
	    		//forgotUsernameScreen();
	        }
	    	});
		background.add(forgotUsernameB);
		
		//Forgot Password JButton
		JButton forgotPasswordB = new JButton("Forgot your password?");
		forgotPasswordB.setBounds(200, 240, 200, 35);
		forgotPasswordB.setFont(new Font("Candara", Font.PLAIN, 12));
		forgotPasswordB.setOpaque(false);
		forgotPasswordB.setContentAreaFilled(false);
		forgotPasswordB.setBorderPainted(false);
		forgotPasswordB.setForeground(Color.WHITE);
		forgotPasswordB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	loginWindow.dispose();
	    		//forgotPasswordScreen();
	        }
	    	});
		background.add(forgotPasswordB);
		
		//Display the window
		loginWindow.pack();
		loginWindow.setVisible(true);
	}
	
	/**
	 * The newUserScreen method generates the GUI, allowing new users to identify themselves as student or teacher. 
	 * 		An existing option returns users to loginScreen, otherwise they are forwarded to create an account. 
	 */
	private void newUserScreen() {
		
		//Initialize windows
		Dimension newUserSize = new Dimension(newUserWidth, newUserHeight);
		newUserWindow = new JFrame("New User");
		newUserWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newUserWindow.setSize(newUserSize);
		newUserWindow.setLayout(null);
		newUserWindow.setResizable(false);
		newUserWindow.setLocationRelativeTo(null);
		newUserWindow.setLayout(new BorderLayout());
		
		//Set Background
		JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		background.setBounds(0, 0, newUserWidth, newUserHeight);
		newUserWindow.add(background);
		
		//Teacher JButton
		JButton teacherB = new JButton("Teacher");
		teacherB.setBounds(25, 25, 100, 50);
		teacherB.setFont(masterFont);
		teacherB.setBorder(compound);
		teacherB.setOpaque(true);
		teacherB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		newUserWindow.dispose();
	        		teacherAccount = true;
	        		newTeacherScreen();
	        }
	    	});
		background.add(teacherB);
				
		//Student JButton
		JButton studentB = new JButton("Student");
		studentB.setBounds(150, 25, 100, 50);
		studentB.setFont(masterFont);
		studentB.setBorder(compound);
		studentB.setOpaque(true);
		studentB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		newUserWindow.dispose();
				studentAccount = true;
				newStudentScreen();
	        }
	    	});
		background.add(studentB);
		
		//Existing JButton
		JButton existingB = new JButton("Existing");
		existingB.setBounds(275, 25, 100, 50);
		existingB.setFont(masterFont);
		existingB.setBorder(compound);
		existingB.setOpaque(true);
		existingB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		newUserWindow.dispose();
				loginScreen();
	        }
	    	});
		background.add(existingB);
		
		//Display the window
		newUserWindow.setVisible(true);
	}
	
	/**
	 * The newStudentScreen method generates the GUI, used to collect the required information 
	 * 		to create a Student account. 
	 */
	private void newStudentScreen() {
		
		//Initialize Window
		Dimension newStudentSize = new Dimension(newStudentWidth, newStudentHeight);
		newStudentWindow = new JFrame("Student Account");
		newStudentWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newStudentWindow.setSize(newStudentSize);
		newStudentWindow.setLayout(null);
		newStudentWindow.setResizable(false);
		newStudentWindow.setLocationRelativeTo(null);
		newStudentWindow.setLayout(new BorderLayout());
				
		//Set Background
		JLabel background = new JLabel(new ImageIcon("Resource/newBackground.jpg"));
		background.setBounds(0, 0, newStudentWidth, newStudentHeight);
		newStudentWindow.add(background);
		
		//Student: FirstName JLabel
		sFirstNameL = new JLabel("First");
		sFirstNameL.setForeground(Color.WHITE);
		sFirstNameL.setFont(masterFont);
		sFirstNameL.setBounds(25, 25, 200, 30);
		background.add(sFirstNameL);
		
		//Student: LastName JLabel
		sLastNameL = new JLabel("Last");
		sLastNameL.setForeground(Color.WHITE);
		sLastNameL.setFont(masterFont);
		sLastNameL.setBounds(275, 25, 200, 30);
		background.add(sLastNameL);
		
		//Student: SchoolName JLabel
		sSchoolNameL = new JLabel("School");
		sSchoolNameL.setForeground(Color.WHITE);
		sSchoolNameL.setFont(masterFont);
		sSchoolNameL.setBounds(25, 85, 200, 30);
		background.add(sSchoolNameL);
		
		//Student: Grade JLabel
		sGradeL = new JLabel("Grade");
		sGradeL.setForeground(Color.WHITE);
		sGradeL.setFont(masterFont);
		sGradeL.setBounds(275, 85, 200, 30);
		background.add(sGradeL);
		
		//Student: Password JLabel
		sPasswordL1 = new JLabel("Password");
		sPasswordL1.setForeground(Color.WHITE);
		sPasswordL1.setFont(masterFont);
		sPasswordL1.setBounds(25, 145, 200, 30);
		background.add(sPasswordL1);
		
		//Student: Re-enter Password JLabel
		sPasswordL2 = new JLabel("Re-enter Password");
		sPasswordL2.setForeground(Color.WHITE);
		sPasswordL2.setFont(masterFont);
		sPasswordL2.setBounds(275, 145, 200, 30);
		background.add(sPasswordL2);
		
		//Student: Security Question 1 JLabel
		sSecurityQ1L = new JLabel("Security Question 1");
		sSecurityQ1L.setForeground(Color.WHITE);
		sSecurityQ1L.setFont(masterFont);
		sSecurityQ1L.setBounds(25, 205, 200, 30);
		background.add(sSecurityQ1L);
		
		//Student: Security Question 2 JLabel
		sSecurityQ2L = new JLabel("Security Question 2");
		sSecurityQ2L.setForeground(Color.WHITE);
		sSecurityQ2L.setFont(masterFont);
		sSecurityQ2L.setBounds(25, 265, 200, 30);
		background.add(sSecurityQ2L);
		
		//-----------------------------------------------------------
		
		//Student: FirstName JTextField
		sFirstNameTF = new JTextField();
		sFirstNameTF.setBounds(25, 55, 200, 30);
		sFirstNameTF.setBorder(compound);
		background.add(sFirstNameTF);
		
		//Student: LastName JTextField
		sLastNameTF = new JTextField();
		sLastNameTF.setBounds(275, 55, 200, 30);
		sLastNameTF.setBorder(compound);
		background.add(sLastNameTF);
		
		//Student: SchoolName JTextField
		sSchoolNameTF = new JTextField();
		sSchoolNameTF.setBounds(25, 115, 200, 30);
		sSchoolNameTF.setBorder(compound);
		background.add(sSchoolNameTF);
		
		//Student: Security Question 1 JTextField
		sSecurityQ1TF = new JTextField();
		sSecurityQ1TF.setBounds(325, 235, 150, 30);
		sSecurityQ1TF.setBorder(compound);
		background.add(sSecurityQ1TF);
				
		//Student: Security Question 2 JTextField
		sSecurityQ2TF = new JTextField();
		sSecurityQ2TF.setBounds(325, 295, 150, 30);
		sSecurityQ2TF.setBorder(compound);
		background.add(sSecurityQ2TF);
		
		//------------------------------------------------
		
		//Student: "Create Account" JButton
		JButton sCreateAccountB = new JButton("Create Account");
		sCreateAccountB.setBounds(175, 350, 150, 50);
		sCreateAccountB.setBorder(compound);
		sCreateAccountB.setOpaque(true);
		sCreateAccountB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		newStudent = true;
	        		verifyStudent();
	        }
	    	});
		background.add(sCreateAccountB);
		
		//-------------------------------------------------
		
		//Student: Password JPasswordField
		sPasswordPF1 = new JPasswordField();
		sPasswordPF1.setBounds(25, 175, 200, 30);
		sPasswordPF1.addActionListener(this);
		sPasswordPF1.setBorder(compound);
		background.add(sPasswordPF1);
			
		//Student: Re-enter Password JPasswordField
		sPasswordPF2 = new JPasswordField();
		sPasswordPF2.setBounds(275, 175, 200, 30);
		sPasswordPF2.addActionListener(this);
		sPasswordPF2.setBorder(compound);
		background.add(sPasswordPF2);
		
		//--------------------------------------------------
		
		//Student: Grade Level JComboBox
		String[] sGradeLevels = {
				"Kindergarten", "1st", "2nd", "3rd", "4th",
				"5th", "6th", "7th", "8th", "9th",
				"10th", "11th", "12th"
		};
		sGradeList = new JComboBox(sGradeLevels);
		sGradeList.setEditable(false);
		sGradeList.addActionListener(this);
		sGradeList.setBounds(275, 115, 200, 30);
		sGradeList.setBorder(compound);
		background.add(sGradeList);
		
		//Student: Security Question 1 JComboBox
		String[] sSecurityQuestions1 = {
		         "What is your favorite movie?",
		         "What is your favorite food?",
		         "What is the name of your first pet?",
		         "What is your middle name?",
		         "What is your favorite animal?",
		         "What is your mother's name?",
		         "What is your father's name?",
		         "What is your favorite color?",
		         "What is your favorite holiday?"
		};
		sSecurityList1 = new JComboBox(sSecurityQuestions1);
		sSecurityList1.setEditable(false);
		sSecurityList1.addActionListener(this);
		sSecurityList1.setBounds(25, 235, 275, 30);
		sSecurityList1.setBorder(compound);
		background.add(sSecurityList1);
		
		//Student: Security Question 2 JComboBox
		String[] sSecurityQuestions2 = {
		         "What is your favorite movie?",
		         "What is your favorite food?",
		         "What is the name of your first pet?",
		         "What is your middle name?",
		         "What is your favorite animal?",
		         "What is your mother's name?",
		         "What is your father's name?",
		         "What is your favorite color?",
		         "What is your favorite holiday?"
		};
		sSecurityList2 = new JComboBox(sSecurityQuestions2);
		sSecurityList2.setEditable(false);
		sSecurityList2.addActionListener(this);
		sSecurityList2.setBounds(25, 295, 275, 30);
		sSecurityList2.setBorder(compound);
		background.add(sSecurityList2);
		
		//Display the window
		newStudentWindow.pack();
		newStudentWindow.setVisible(true);
	}
	
	/**
	 * The newTeacherScreen method generates the GUI, used to collect the required information 
	 * 		to create a Teacher account. 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked", "rawtypes", "unchecked" })
	private void newTeacherScreen() {
				
		//Initialize Window
		Dimension newTeacherSize = new Dimension(newTeacherWidth, newTeacherHeight);
		newTeacherWindow = new JFrame("Teacher Account");
		newTeacherWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		newTeacherWindow.setSize(newTeacherSize);
		newTeacherWindow.setResizable(false);
		newTeacherWindow.setLocationRelativeTo(null);
		newTeacherWindow.setLayout(new BorderLayout());
						
		//Set Background
		JLabel background = new JLabel(new ImageIcon("Resource/newBackground.jpg"));
		background.setBounds(0, 0, newTeacherWidth, newTeacherHeight);
		newTeacherWindow.add(background);
				
		//Teacher: FirstName JLabel
		tFirstNameL = new JLabel("First");
		tFirstNameL.setForeground(Color.WHITE);
		tFirstNameL.setFont(masterFont);
		tFirstNameL.setBounds(25, 25, 200, 30);
		background.add(tFirstNameL);
				
		//Teacher: LastName JLabel
		tLastNameL = new JLabel("Last");
		tLastNameL.setForeground(Color.WHITE);
		tLastNameL.setFont(masterFont);
		tLastNameL.setBounds(275, 25, 200, 30);
		background.add(tLastNameL);
				
		//Teacher: SchoolName JLabel
		tSchoolNameL = new JLabel("School");
		tSchoolNameL.setForeground(Color.WHITE);
		tSchoolNameL.setFont(masterFont);
		tSchoolNameL.setBounds(25, 85, 200, 30);
		background.add(tSchoolNameL);
		
		//Teacher: Username JLabel
		tUsernameL = new JLabel("Username");
		tUsernameL.setForeground(Color.WHITE);
		tUsernameL.setFont(masterFont);
		tUsernameL.setBounds(275, 85, 200, 30);
		background.add(tUsernameL);
				
		//Teacher: Password JLabel
		tPasswordL1 = new JLabel("Password");
		tPasswordL1.setForeground(Color.WHITE);
		tPasswordL1.setFont(masterFont);
		tPasswordL1.setBounds(25, 145, 200, 30);
		background.add(tPasswordL1);
				
		//Teacher: Re-enter Password JLabel
		tPasswordL2 = new JLabel("Re-enter Password");
		tPasswordL2.setForeground(Color.WHITE);
		tPasswordL2.setFont(masterFont);
		tPasswordL2.setBounds(275, 145, 200, 30);
		background.add(tPasswordL2);
				
		//Teacher: Security Question 1 JLabel
		tSecurityQ1L = new JLabel("Security Question 1");
		tSecurityQ1L.setForeground(Color.WHITE);
		tSecurityQ1L.setFont(masterFont);
		tSecurityQ1L.setBounds(25, 205, 200, 30);
		background.add(tSecurityQ1L);
				
		//Teacher: Security Question 2 JLabel
		tSecurityQ2L = new JLabel("Security Question 2");
		tSecurityQ2L.setForeground(Color.WHITE);
		tSecurityQ2L.setFont(masterFont);
		tSecurityQ2L.setBounds(25, 265, 200, 30);
		background.add(tSecurityQ2L);
				
		//-----------------------------------------------------------
				
		//Teacher: FirstName JTextField
		tFirstNameTF = new JTextField();
		tFirstNameTF.setBounds(25, 55, 200, 30);
		tFirstNameTF.setBorder(compound);
		background.add(tFirstNameTF);
				
		//Teacher: LastName JTextField
		tLastNameTF = new JTextField();
		tLastNameTF.setBounds(275, 55, 200, 30);
		tLastNameTF.setBorder(compound);
		background.add(tLastNameTF);
		
		//Teacher: Username JTextField
		tUsernameTF = new JTextField();
		tUsernameTF.setBounds(275, 115, 200, 30);
		tUsernameTF.setBorder(compound);
		background.add(tUsernameTF);
				
		//Teacher: SchoolName JTextField
		tSchoolNameTF = new JTextField();
		tSchoolNameTF.setBounds(25, 115, 200, 30);
		tSchoolNameTF.setBorder(compound);
		background.add(tSchoolNameTF);
		
		//Teacher: Security Question 1 JTextField
		tSecurityQ1TF = new JTextField();
		tSecurityQ1TF.setBounds(325, 235, 150, 30);
		tSecurityQ1TF.setBorder(compound);
		background.add(tSecurityQ1TF);
					
		//Teacher: Security Question 2 JTextField
		tSecurityQ2TF = new JTextField();
		tSecurityQ2TF.setBounds(325, 295, 150, 30);
		tSecurityQ2TF.setBorder(compound);
		background.add(tSecurityQ2TF);
				
		//----------------------------------------------------------------
		
		//Teacher: "Create Account" JButton
		JButton tCreateAccountB = new JButton("Create Account");
		tCreateAccountB.setBounds(175, 350, 150, 50);
		tCreateAccountB.setBorder(compound);
		tCreateAccountB.setOpaque(true);
		tCreateAccountB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		newTeacher = true;
	        		verifyTeacher();
	        }
	    	});
		background.add(tCreateAccountB);
		
		//----------------------------------------------------------------
				
		//Teacher: Password JPasswordField
		tPasswordPF1 = new JPasswordField();
		tPasswordPF1.setBounds(25, 175, 200, 30);
		tPasswordPF1.addActionListener(this);
		tPasswordPF1.setBorder(compound);
		background.add(tPasswordPF1);
					
		//Teacher: Re-enter Password JPasswordField
		tPasswordPF2 = new JPasswordField();
		tPasswordPF2.setBounds(275, 175, 200, 30);
		tPasswordPF2.addActionListener(this);
		tPasswordPF2.setBorder(compound);
		background.add(tPasswordPF2);
				
		//----------------------------------------------------------------
				
		//Teacher: Security Question 1 JComboBox
		String[] tSecurityQuestions1 = {
			        "What high school did you attend?",
			        "In what city were you born?",
			        "What is your favorite movie?",
			        "What is your middle name?",
			        "What is your mother's maiden name?",
			        "What street did you grow up on?",
			        "What was the make of your first car?",
			        "What is your favorite color?",
			        "What is your father's middle name?"
		};
		tSecurityList1 = new JComboBox(tSecurityQuestions1);
		tSecurityList1.setEditable(false);
		tSecurityList1.addActionListener(this);
		tSecurityList1.setBounds(25, 235, 275, 30);
		tSecurityList1.setBorder(compound);
		tSecurityList1.setBackground(Color.WHITE);
		background.add(tSecurityList1);
		
		//Teacher: Security Question 2 JComboBox
		String[] tSecurityQuestions2 = {
		         "What is the name of your first grade teacher?",
		         "What was your high school mascot?",
		         "What is your favorite web browser?",
		         "What is your favorite website?",
		         "What is your favorite animal?",
		         "What is your favorite social media website?",
		         "What is the name of your favorite pet?",
		         "What is the name of your first boyfriend or girlfriend?",
		         "What is your favorite musician?"
		};	
		tSecurityList2 = new JComboBox(tSecurityQuestions2);
		tSecurityList2.setEditable(false);
		tSecurityList2.addActionListener(this);
		tSecurityList2.setBounds(25, 295, 275, 30);
		tSecurityList2.setBorder(compound);
		tSecurityList2.setBackground(Color.WHITE);
		background.add(tSecurityList2);
		
		//Display the window
		newTeacherWindow.pack();
		newTeacherWindow.setVisible(true);
	}
	
	/**
	 * The verifyStudent method verifies all information entered by the new user (Student) is valid, if so, 
	 * 		the user is forwarded to their portal. If any field is left blank or invalid, 
	 * 		the user is informed which fields need addressed. 
	 */
	private void verifyStudent() {
		
		char[] newPassword1 = sPasswordPF1.getPassword();
		char[] newPassword2 = sPasswordPF2.getPassword();
		
		if (newStudent || sUpdateProfile) {
			
			//Student: FirstName
			if (sFirstNameTF.getText().isEmpty()) { 
				JOptionPane.showMessageDialog(this,"Blank Field: First","Error",JOptionPane.ERROR_MESSAGE);
				sFirstNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!sFirstNameTF.getText().isEmpty() && sFirstNameTF.getBackground() == Color.LIGHT_GRAY) { 
				sFirstNameTF.setBackground(Color.WHITE);
			}
			
			//Student: LastName
			if (sLastNameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Last","Error",JOptionPane.ERROR_MESSAGE);
				sLastNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!sLastNameTF.getText().isEmpty() && sLastNameTF.getBackground() == Color.LIGHT_GRAY) { 
				sLastNameTF.setBackground(Color.WHITE);
			}
			
			//Student: SchoolName
			if (sSchoolNameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: School","Error",JOptionPane.ERROR_MESSAGE);
				sSchoolNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!sSchoolNameTF.getText().isEmpty() && sSchoolNameTF.getBackground() == Color.LIGHT_GRAY) { 
				sSchoolNameTF.setBackground(Color.WHITE);
			}
		}
		
		if (newStudent || sUpdateSecurity) {
			
			//Student: Password
			if (sPasswordPF1.getPassword().length == 0) {
				if (newStudent) {
					JOptionPane.showMessageDialog(this,"Blank Field: Password","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF1.setBackground(Color.LIGHT_GRAY);
					return;
				}
				//Student: New Password
				else if (sUpdateSecurity) {
					JOptionPane.showMessageDialog(this,"Blank Field: New Password","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF1.setBackground(Color.LIGHT_GRAY);
					return;
				}
			}
			else if (sPasswordPF1.getPassword().length != 0 && sPasswordPF1.getBackground() == Color.LIGHT_GRAY) { 
				sPasswordPF1.setBackground(Color.WHITE);
			}
			
			//Student: Re-enter Password
			if (sPasswordPF2.getPassword().length == 0) {
				if (newStudent) {
					JOptionPane.showMessageDialog(this,"Blank Field: Re-enter Password","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF2.setBackground(Color.LIGHT_GRAY);
					return;
				}
				//Student: Re-enter New Password
				else if (sUpdateSecurity){
					JOptionPane.showMessageDialog(this,"Blank Field: Re-enter New Password","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF2.setBackground(Color.LIGHT_GRAY);
					return;
				}
			}
			else if (sPasswordPF2.getPassword().length != 0 && sPasswordPF2.getBackground() == Color.LIGHT_GRAY) { 
				sPasswordPF2.setBackground(Color.WHITE);
			}
			
			//Student: Security Question 1
			if (sSecurityQ1TF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Security Question 1","Error",JOptionPane.ERROR_MESSAGE);
				sSecurityQ1TF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!sSecurityQ1TF.getText().isEmpty() && sSecurityQ1TF.getBackground() == Color.LIGHT_GRAY) { 
				sSecurityQ1TF.setBackground(Color.WHITE);
			}
			
			//Student: Security Question 2
			if (sSecurityQ2TF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Security Question 2","Error",JOptionPane.ERROR_MESSAGE);
				sSecurityQ2TF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!sSecurityQ2TF.getText().isEmpty() && sSecurityQ2TF.getBackground() == Color.LIGHT_GRAY) { 
				sSecurityQ2TF.setBackground(Color.WHITE);
			}
			
			//Student: Password Equivalency 
			else if (isPasswordCorrect(newPassword1, newPassword2) != true) {
				if (newStudent) {
					JOptionPane.showMessageDialog(this,"Passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF1.setText("");
					sPasswordPF2.setText("");
					return;
				}
				//Student: New Password Equivalency 
				else if (sUpdateSecurity) {
					JOptionPane.showMessageDialog(this,"New passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					sPasswordPF1.setText("");
					sPasswordPF2.setText("");
					return;
				}
			}
			
			//Student: Old Password Equivalency 
			if (sUpdateSecurity) {
				
				char[] oldPassword1 = student.getPassword();
				char[] oldPassword2 = sOldPasswordPF.getPassword();
				
				if (isPasswordCorrect(oldPassword1, oldPassword2) != true) {
					JOptionPane.showMessageDialog(this,"Old passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					sOldPasswordPF.setText("");
					return;
				}
				else if (sOldPasswordPF.getPassword().length != 0 && sOldPasswordPF.getBackground() == Color.LIGHT_GRAY) { 
					sOldPasswordPF.setBackground(Color.WHITE);
				} 
			}
		}
		//Student: Update Profile Actions 
		if (sUpdateProfile) {
			sUpdateProfile = false;
			updateStudentProfileInfo();
			updateStudentPWindow.dispose();
			sPortalWindow.dispose();
			studentPortalScreen();
			System.out.println("Updated student profile");
		}
		//Student: Update Security Actions
		else if (sUpdateSecurity) {
			sUpdateSecurity = false;
			updateStudentSecurityInfo();
			updateStudentSWindow.dispose();
			sPortalWindow.dispose();
			studentPortalScreen();
		}
		//Student: New Student Actions
		else {
			newStudent = false;
			newStudentWindow.dispose();
			addStudent();
			createTempData();
			studentPortalScreen();
		}
	}
	
	/**
	 * The verifyTeacher method verifies all information entered by the new user (Teacher) is valid, if so, 
	 * 		the user is forwarded to their portal. If any field is left blank or invalid, 
	 * 		the user is informed which fields need addressed. 
	 */
	private void verifyTeacher() {
		
		char[] password1 = tPasswordPF1.getPassword();
		char[] password2 = tPasswordPF2.getPassword();
		
		if (newTeacher || tUpdateProfile) {
			
			//Teacher: FirstName
			if (tFirstNameTF.getText().isEmpty()) { 
				JOptionPane.showMessageDialog(this,"Blank Field: First","Error",JOptionPane.ERROR_MESSAGE);
				tFirstNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tFirstNameTF.getText().isEmpty() && tFirstNameTF.getBackground() == Color.LIGHT_GRAY) { 
				tFirstNameTF.setBackground(Color.WHITE);
			}
			
			//Teacher: LastName
			if (tLastNameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Last","Error",JOptionPane.ERROR_MESSAGE);
				tLastNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tLastNameTF.getText().isEmpty() && tLastNameTF.getBackground() == Color.LIGHT_GRAY) { 
				tLastNameTF.setBackground(Color.WHITE);
			}
			
			//Teacher: SchoolName
			if (tSchoolNameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: School","Error",JOptionPane.ERROR_MESSAGE);
				tSchoolNameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tSchoolNameTF.getText().isEmpty() && tSchoolNameTF.getBackground() == Color.LIGHT_GRAY) { 
				tSchoolNameTF.setBackground(Color.WHITE);
			}
			
			//Teacher: Username
			if (tUsernameTF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Username","Error",JOptionPane.ERROR_MESSAGE);
				tUsernameTF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tUsernameTF.getText().isEmpty() && tUsernameTF.getBackground() == Color.LIGHT_GRAY) { 
				tUsernameTF.setBackground(Color.WHITE);
			}
		}
		
		if (newTeacher || tUpdateSecurity) {
			
			//Teacher: Password
			if (tPasswordPF1.getPassword().length == 0) {
				if (newTeacher) {
					JOptionPane.showMessageDialog(this,"Blank Field: Password","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF1.setBackground(Color.LIGHT_GRAY);
					return;
				}
				//Teacher: New Password
				else if (tUpdateSecurity) {
					JOptionPane.showMessageDialog(this,"Blank Field: New Password","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF1.setBackground(Color.LIGHT_GRAY);
					return;
				}
			}
			else if (tPasswordPF1.getPassword().length != 0 && tPasswordPF1.getBackground() == Color.LIGHT_GRAY) { 
				tPasswordPF1.setBackground(Color.WHITE);
			}
			//Teacher: Re-enter Password
			if (tPasswordPF2.getPassword().length == 0) {
				if (newTeacher) {
					JOptionPane.showMessageDialog(this,"Blank Field: Re-enter Password","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF2.setBackground(Color.LIGHT_GRAY);
					return;
				}
				//Teacher: Re-enter New Password
				else if (tUpdateSecurity) {
					JOptionPane.showMessageDialog(this,"Blank Field: Re-enter New Password","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF2.setBackground(Color.LIGHT_GRAY);
					return;
				}
			}
			else if (tPasswordPF2.getPassword().length != 0 && tPasswordPF2.getBackground() == Color.LIGHT_GRAY) { 
				tPasswordPF2.setBackground(Color.WHITE);
			}
			
			//Teacher: Security Question 1
			if (tSecurityQ1TF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Security Question 1","Error",JOptionPane.ERROR_MESSAGE);
				tSecurityQ1TF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tSecurityQ1TF.getText().isEmpty() && tSecurityQ1TF.getBackground() == Color.LIGHT_GRAY) { 
				tSecurityQ1TF.setBackground(Color.WHITE);
			}
			
			//Teacher: Security Question 2
			if (tSecurityQ2TF.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this,"Blank Field: Security Question 2","Error",JOptionPane.ERROR_MESSAGE);
				tSecurityQ2TF.setBackground(Color.LIGHT_GRAY);
				return;
			}
			else if (!tSecurityQ2TF.getText().isEmpty() && tSecurityQ2TF.getBackground() == Color.LIGHT_GRAY) { 
				tSecurityQ2TF.setBackground(Color.WHITE);
			}
			
			//Teacher: Password Equivalency
			if (isPasswordCorrect(password1, password2) != true) {
				if (newTeacher) {
					JOptionPane.showMessageDialog(this,"Passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF1.setText("");
					tPasswordPF2.setText("");
					return;
				}
				//Teacher: New Password Equivalency
				else if (tUpdateSecurity) {
					JOptionPane.showMessageDialog(this,"New passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					tPasswordPF1.setText("");
					tPasswordPF2.setText("");
					return;
				}
			}
			//Teacher: Old Password Equivalency
			if (tUpdateSecurity) {
				
				char[] oldPassword1 = teacher.getPassword();
				char[] oldPassword2 = tOldPasswordPF.getPassword();
				
				if (isPasswordCorrect(oldPassword1, oldPassword2) != true) {
					JOptionPane.showMessageDialog(this,"Old passwords do not match!","Error",JOptionPane.ERROR_MESSAGE);
					tOldPasswordPF.setText("");
					return;
				}
			}
		}
		if (login == true && tUpdateProfile == false && tUpdateSecurity == false) {
			return;
		} 
		//Teacher: Update Profile Actions
		else if (tUpdateProfile) {
			tUpdateProfile = false;
			updateTeacherProfileInfo();
			updateTeacherPWindow.dispose();
			tPortalWindow.dispose();
			teacherPortalScreen();
		}
		//Teacher: Update Security Actions
		else if (tUpdateSecurity) {
			tUpdateSecurity = false;
			updateTeacherSecurityInfo();
			updateTeacherSWindow.dispose();
			tPortalWindow.dispose();
			teacherPortalScreen();
		}
		//Teacher: New User Actions
		else {
			newTeacherWindow.dispose();
			addTeacher();
			createTempData();
			teacherPortalScreen();
		}
	}
	
	/**
	 * The addTeacher method generates a new Teacher object.
	 */
	private void addTeacher() {
		
		String lastName = tLastNameTF.getText();
		String firstName = tFirstNameTF.getText();
		String schoolName = tSchoolNameTF.getText();
		String userName = tUsernameTF.getText();
		char[] password = tPasswordPF1.getPassword();
		String sA1 = tSecurityQ1TF.getText();
		String sQ1 = (String)tSecurityList1.getSelectedItem();
		String sA2 = tSecurityQ2TF.getText();
		String sQ2 = (String)tSecurityList2.getSelectedItem();
		
		teacher = new Teacher(lastName, firstName, schoolName, userName, password, sA1, sQ1, sA2, sQ2);
		
		//Add teacher to database 
		
	}
	
	
	/**
	 * The addStudent method generates a new Student object. 
	 */
	private void addStudent() {
		
		String lastName = sLastNameTF.getText();
		String firstName = sFirstNameTF.getText();
		String schoolName = sSchoolNameTF.getText();
		char[] password = sPasswordPF1.getPassword();
		String sA1 = sSecurityQ1TF.getText();
		String sQ1 = (String)sSecurityList1.getSelectedItem();
		String sA2 = sSecurityQ2TF.getText();
		String sQ2 = (String)sSecurityList2.getSelectedItem();
		String grade = (String)sGradeList.getSelectedItem();
		
		student = new Student(lastName, firstName, schoolName, password, sA1, sQ1, sA2, sQ2, grade);
		
		//Add student to database
		
	}
	
	
	/**
	 * The updateTeacherProfileInfo method updates the Teacher's profile information, including last, first, and school name. 
	 */
	private void updateTeacherProfileInfo() {
		
		teacher.setLastName(tLastNameTF.getText());
		teacher.setFirstName(tFirstNameTF.getText());
		teacher.setSchoolName(tSchoolNameTF.getText());
		
		//Update Teacher profile information in database
		
	}
	
		
	/**
	 * The updateTeacherSecurityInfo method updates the Teacher's security information, including password and security questions.
	 */
	private void updateTeacherSecurityInfo() {
		
		teacher.setPassword(tOldPasswordPF.getPassword());
		teacher.setSecuirtyAnswer1(tSecurityQ1TF.getText());
		teacher.setSecurityQuestion1((String)tSecurityList1.getSelectedItem());
		teacher.setSecurityAnswer2(tSecurityQ2TF.getText());
		teacher.setSecurityQuestion2((String)tSecurityList1.getSelectedItem());
		
		//Update Teacher security information in database
		
	}
	
	
	/**
	 * The updateStudentProfileInfo method updates the Student's profile information, inluding first, last, and school name.
	 */
	private void updateStudentProfileInfo() {
		
		student.setLastName(sLastNameTF.getText());
		student.setFirstName(sFirstNameTF.getText());
		student.setSchoolName(sSchoolNameTF.getText());
		
		//Update Student profile information in database
		
	}
	
	
	/**
	 * The updateStudentSecurityInfo method updates the Student's security information, including password and security questions. 
	 */
	private void updateStudentSecurityInfo() {
		
		student.setPassword(sPasswordPF1.getPassword());
		student.setSecuirtyAnswer1(sSecurityQ1TF.getText());
		student.setSecurityQuestion1((String)sSecurityList1.getSelectedItem());
		student.setSecurityAnswer2(sSecurityQ2TF.getText());
		student.setSecurityQuestion2((String)sSecurityList1.getSelectedItem());
		
		//Update Student security information in database
		
	}
	
	
	/**
	 * The teacherPortalScreen method generates the Teacher portal GUI. 
	 */
	private void teacherPortalScreen() {
		
		//Initialize Window
		Dimension teacherPortalSize = new Dimension(teacherPortalWidth, teacherPortalHeight);
		tPortalWindow = new JFrame("Teacher Portal");
		tPortalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tPortalWindow.setSize(teacherPortalSize);
		tPortalWindow.setLayout(new BorderLayout());
		tPortalWindow.setResizable(false);
		tPortalWindow.setLocationRelativeTo(null);
		tPortalWindow.setMinimumSize(teacherPortalSize);
		tPortalWindow.setMaximumSize(teacherPortalSize);
		tPortalWindow.setPreferredSize(teacherPortalSize);
		
		//TeacherPortal JPanel
		teacherPortal = new JPanel();
		teacherPortal.setLayout(new GridLayout(1, 1));
		teacherPortal.setSize(teacherPortalSize);
		
		//Create JTabbedPane & JComponent's
		tTabbedPane = new JTabbedPane();
		
		//TeacherProfileComponent
		tProfileComponent = makeTeacherProfilePanel();
		tTabbedPane.addTab("Dashboard", tProfileComponent);
		tTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

		//TeacherClassComponent
		tClassComponent = makeTeacherClassPanel();
		tTabbedPane.addTab("Class", tClassComponent);
		tTabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		
		//Add JTabbedPane to JPanel
		teacherPortal.add(tTabbedPane);
		
		//Add JPanel to JFrame
        tPortalWindow.add(teacherPortal, BorderLayout.CENTER);
         
        //Display the window
        tPortalWindow.pack();
        tPortalWindow.setVisible(true);
		
		//Enable scrolling tabs
        tTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
	}
	
	
	/**
	 * The createTempData method generates data needed for demonstration purposes.
	 */
	private void createTempData() {
		
		if (login == true) {
		tFirstNameL = new JLabel("First");
		tFirstNameL.setForeground(Color.WHITE);
		tFirstNameL.setFont(masterFont);
		tFirstNameL.setBounds(275, 25, 200, 30);
		
		tLastNameL = new JLabel("Last");
		tLastNameL.setForeground(Color.WHITE);
		tLastNameL.setFont(masterFont);
		tLastNameL.setBounds(275, 25, 200, 30);
		
		tSchoolNameL = new JLabel("School");
		tSchoolNameL.setForeground(Color.WHITE);
		tSchoolNameL.setFont(masterFont);
		tSchoolNameL.setBounds(25, 85, 200, 30);
	
		tUsernameL = new JLabel("Username");
		tUsernameL.setForeground(Color.WHITE);
		tUsernameL.setFont(masterFont);
		tUsernameL.setBounds(275, 85, 200, 30);
				
		tPasswordL1 = new JLabel("Password");
		tPasswordL1.setForeground(Color.WHITE);
		tPasswordL1.setFont(masterFont);
		tPasswordL1.setBounds(25, 145, 200, 30);
				
		tPasswordL2 = new JLabel("Re-enter Password");
		tPasswordL2.setForeground(Color.WHITE);
		tPasswordL2.setFont(masterFont);
		tPasswordL2.setBounds(275, 145, 200, 30);
				
		tSecurityQ1L = new JLabel("Security Question 1");
		tSecurityQ1L.setForeground(Color.WHITE);
		tSecurityQ1L.setFont(masterFont);
		tSecurityQ1L.setBounds(25, 205, 200, 30);
				
		tSecurityQ2L = new JLabel("Security Question 2");
		tSecurityQ2L.setForeground(Color.WHITE);
		tSecurityQ2L.setFont(masterFont);
		tSecurityQ2L.setBounds(25, 265, 200, 30);
		
		tPasswordPF1 = new JPasswordField();
		tPasswordPF1.setText("saveme");
		tPasswordPF1.setBounds(25, 175, 200, 30);
		tPasswordPF1.addActionListener(this);
		tPasswordPF1.setBorder(compound);
		
		tPasswordPF2 = new JPasswordField();
		tPasswordPF2.setText("saveme");
		tPasswordPF2.setBounds(275, 175, 200, 30);
		tPasswordPF2.addActionListener(this);
		tPasswordPF2.setBorder(compound);
		
		teacherAccount = true;
		
		String lastName = "Neff";
		tLastNameTF = new JTextField();
		tLastNameTF.setText("Neff");
		String firstName = "Jon";
		tFirstNameTF = new JTextField();
		tFirstNameTF.setText(firstName);
		String schoolName = "UNCW";
		tSchoolNameTF = new JTextField();
		tSchoolNameTF.setText(schoolName);
		String userName = "jmn7080";
		tUsernameTF = new JTextField();
		tUsernameTF.setText(userName);
		char[] password = { 's', 'a', 'v', 'e', 'm', 'e'};
		String sA1 = "Amelia";
		tSecurityQ1TF = new JTextField();
		tSecurityQ1TF.setText(sA1);
		tSecurityQ1TF.setBorder(compound);
		
		String[] tSecurityQuestions1 = {
		        "What high school did you attend?",
		        "In what city were you born?",
		        "What is your favorite movie?",
		        "What is your middle name?",
		        "What is your mother's maiden name?",
		        "What street did you grow up on?",
		        "What was the make of your first car?",
		        "What is your favorite color?",
		        "What is your father's middle name?"
		};
		tSecurityList1 = new JComboBox(tSecurityQuestions1);
		tSecurityList1.setEditable(false);
		tSecurityList1.addActionListener(this);
		tSecurityList1.setBounds(25, 235, 275, 30);
		tSecurityList1.setBorder(compound);
		tSecurityList1.setBackground(Color.WHITE);
	
		String[] tSecurityQuestions2 = {
		         "What is the name of your first grade teacher?",
		         "What was your high school mascot?",
		         "What is your favorite web browser?",
		         "What is your favorite website?",
		         "What is your favorite animal?",
		         "What is your favorite social media website?",
		         "What is the name of your favorite pet?",
		         "What is the name of your first boyfriend or girlfriend?",
		         "What is your favorite musician?"
		};	
		tSecurityList2 = new JComboBox(tSecurityQuestions2);
		tSecurityList2.setEditable(false);
		tSecurityList2.addActionListener(this);
		tSecurityList2.setBounds(25, 295, 275, 30);
		tSecurityList2.setBorder(compound);
		tSecurityList2.setBackground(Color.WHITE);
		
		String sQ1 = (String)tSecurityList1.getItemAt(1);
		String sA2 = "Mrs. Cover";
		tSecurityQ2TF = new JTextField();
		tSecurityQ2TF.setText(sA2);
		tSecurityQ2TF.setBorder(compound);
		String sQ2 = (String)tSecurityList2.getItemAt(4);
		
		teacher = new Teacher(lastName, firstName, schoolName, userName, password, sA1, sQ1, sA2, sQ2);
		}
		
		JTable flashTable1, flashTable2;
		String[] fColumnNames = {"Subject", "Total"};
		
		Object[][] flash1 = {{"Chemistry", "24"}};
		Object[][] flash2 = {{"Computer", "18"}};
		
		flashTable1 = new JTable(flash1, fColumnNames);
		flashTable2 = new JTable(flash2, fColumnNames);
		
		table1 = new JTable(data1, columnNames);
		table2 = new JTable(data2, columnNames);
		    
		Class class1 = new Class("CSC 450", table1, flashTable1);
		Class class2 = new Class("Premier Team", table2, flashTable2);
		
		FlashCard fC1 = new FlashCard("Science");
		String[] card1 =  {"Hello", "World"};
		fC1.addCard(card1);
		
		if (teacherAccount == true) {
			teacher.addClass(class1);
			teacher.addClass(class2);
		}
		
	}
	
	
	/**
	 * The makeTeacherProfilePanel method generates the GUI Teacher profile tab component.
	 * @return		JComponent Teacher profile tab
	 */
	protected JComponent makeTeacherProfilePanel() {
		
		//Initiate profilePanel
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new BorderLayout());
		profilePanel.setSize(teacherPortalWidth, teacherPortalHeight);
		profilePanel.setBorder(compound);
		
		//Generate profilePanel's left component
		TitledBorder leftPanelTitle, usernameTitle, firstNameTitle, lastNameTitle, schoolNameTitle, sQ1Title, sQ2Title;
		leftPanelTitle = BorderFactory.createTitledBorder("Profile Information");
		usernameTitle = BorderFactory.createTitledBorder("Username");
		firstNameTitle = BorderFactory.createTitledBorder("First");
		lastNameTitle = BorderFactory.createTitledBorder("Last");
		schoolNameTitle = BorderFactory.createTitledBorder("School");
		sQ1Title = BorderFactory.createTitledBorder("Security Question 1");
		sQ2Title = BorderFactory.createTitledBorder("Security Question 2");
	
		//LeftPanel JPanel 
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(6,1,1,1));
		leftPanel.setBorder(leftPanelTitle);
		
		//Username JLabel
		JLabel usernameL = new JLabel(tUsernameTF.getText());
		usernameL.setBorder(usernameTitle);
		usernameL.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(usernameL);
		
		//FirstName JLabel
		JLabel firstNameL = new JLabel(tFirstNameTF.getText());
		firstNameL.setBorder(firstNameTitle);
		firstNameL.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(firstNameL);
		
		//LastName JLabel
		JLabel lastNameL = new JLabel(tLastNameTF.getText());
		lastNameL.setBorder(lastNameTitle);
		lastNameL.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(lastNameL);
		
		//SchoolName JLabel
		JLabel schoolNameL = new JLabel(tSchoolNameTF.getText());
		schoolNameL.setBorder(schoolNameTitle);
		schoolNameL.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(schoolNameL);

		//Security Question 1 JLabel
		JLabel sQ1L = new JLabel((String)tSecurityList1.getSelectedItem());
		sQ1L.setBorder(sQ1Title);
		sQ1L.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(sQ1L);
		
		//Security Question 2 JLabel
		JLabel sQ2L = new JLabel((String)tSecurityList2.getSelectedItem());
		sQ2L.setBorder(sQ2Title);
		sQ2L.setFont(new Font("Candara", Font.BOLD, 20));
		leftPanel.add(sQ2L);
		
		//----------------------------------------------------------------------------
		
		//Generate profilePanel's right component 
		TitledBorder topTitle, middleTitle, bottomTitle, controlsTitle;
		topTitle = BorderFactory.createTitledBorder("Profile");
		middleTitle =  BorderFactory.createTitledBorder("Class");
		bottomTitle = BorderFactory.createTitledBorder("Study");
		controlsTitle = BorderFactory.createTitledBorder("Controls");
		
		//Main rightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(3,1,1,1));
		
		//TopRightPanel
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new GridLayout(3,1,1,1));
		topRightPanel.setBorder(topTitle);
		
		//EditProfile JButton
		JButton editProfileB = new JButton("Edit Profile");
		editProfileB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateTeacherProfile();
	        }
	    	});
		topRightPanel.add(editProfileB);
		
		//EditSecurity JButton
		JButton editSecurityB = new JButton("Edit Security");
		editSecurityB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateTeacherSecurity();
	        }
	    	});
		topRightPanel.add(editSecurityB);
		
		//Logout JButton
		JButton logoutB = new JButton("Logout");
		logoutB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		tPortalWindow.dispose();
	        		loginScreen();
	        }
	    	});
		topRightPanel.add(logoutB);
		
		//MiddleRightPanel
		JPanel middleRightPanel = new JPanel();
		middleRightPanel.setLayout(new GridLayout(3,1,1,1));
		middleRightPanel.setBorder(middleTitle);
		
		//NewClass JButton
		JButton newClassB = new JButton("New Class");
		newClassB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		addClassTeacher();
	        }
	    	});
		middleRightPanel.add(newClassB);
		
		//EditClass JButton
		JButton editClassB = new JButton("Edit Class");
		editClassB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		deleteClassTeacher();
	        }
	    	});
		middleRightPanel.add(editClassB);
		
		//BottomRightPanel
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(3,1,1,1));
		bottomRightPanel.setBorder(bottomTitle);
		
		//NewStudy JButton
		JButton newStudyB = new JButton("New Study");
		newStudyB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		createStudyName();
				newFlashCard = new FlashCard(subjectNameTF.getText());
	        }
	    	});
		bottomRightPanel.add(newStudyB);
		
		//EditStudy JButton
		JButton editStudyB = new JButton("Edit Study");
		editStudyB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		deleteStudyMaterial();
	        }
	    	});
		bottomRightPanel.add(editStudyB);
		
		//Add JPanel's to main JPanel
		rightPanel.setBorder(controlsTitle);
		rightPanel.add(topRightPanel);
		rightPanel.add(middleRightPanel);
		rightPanel.add(bottomRightPanel);
		
		//ProfileSplit JSplitPane
		JSplitPane profileSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftPanel, rightPanel);
		profileSplit.setResizeWeight(0.75);
		profileSplit.setDividerLocation(0.75);
		
		profilePanel.add(profileSplit);
		return profilePanel;
	}
	
	
	/**
	 * The makeTeacherClassPanel method generates the Teacher GUI class panel component. 
	 * @return		JComponent Teacher class tab
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected JComponent makeTeacherClassPanel()  {
		
		//Initiate Component
		tClassPanel = new JPanel();
		tClassPanel.setSize(teacherPortalWidth, teacherPortalHeight);
		teacherClasses = teacher.getClasses();
		tClassTabs = new JTabbedPane();
		
		for (Class c: teacherClasses) {
			
			//Initiate mainPanel
			JPanel mainPanel = new JPanel();
			mainPanel.setLayout(new BorderLayout());
			mainPanel.setSize(teacherPortalWidth, teacherPortalHeight);
			mainPanel.setBorder(compound);
			
			
			//Generate mainPanel's top component
			JPanel topPanel = new JPanel();
			JLabel leftLabel = new JLabel("Class Roster", SwingConstants.CENTER);
			JLabel rightLabel = new JLabel("Study Material", SwingConstants.CENTER);
			topPanel.setLayout(new GridLayout(1,2,5,5));
			topPanel.add(leftLabel);
			topPanel.add(rightLabel);
			
			
			//Generate mainPanel's middle component
			//Left-side JSplitPane
			JTable classTable = c.getClassTable();
			classTable.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			classTable.setGridColor(Color.BLACK);
			classTable.getTableHeader().setFont(new Font("Candara", Font.BOLD, 14));
			classTable.setDefaultRenderer(Object.class, new TableCellRenderer(){
	            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
	            
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                if (row%2 == 0){
	                    c.setBackground(Color.WHITE);
	                }
	                else {
	                    c.setBackground(Color.ORANGE);
	                }                        
	                return c;
	            }
	        });
			
			JScrollPane scrollClassPane = new JScrollPane();
			scrollClassPane.createHorizontalScrollBar();
			scrollClassPane.createVerticalScrollBar();
			scrollClassPane.add(classTable);
	        scrollClassPane.setViewportView(classTable);
	        scrollClassPane.setEnabled(false);
	        
	        JPanel leftComponent = new JPanel();
	        leftComponent.add(scrollClassPane, BorderLayout.SOUTH);
	        leftComponent.add(classTable.getTableHeader(), BorderLayout.NORTH);
	        leftComponent.setBorder(compound);
	        
	        //Right-side JSplitPane
			JTable flashTable = c.getFlashTable();
			flashTable.setBorder(new EtchedBorder(EtchedBorder.RAISED));
			flashTable.setGridColor(Color.BLACK);
			flashTable.getTableHeader().setFont(new Font("Candara", Font.BOLD, 14));
			flashTable.setDefaultRenderer(Object.class, new TableCellRenderer(){
	            private DefaultTableCellRenderer DEFAULT_RENDERER =  new DefaultTableCellRenderer();
	            
	            @Override
	            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                Component c = DEFAULT_RENDERER.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                if (row%2 == 0){
	                    c.setBackground(Color.WHITE);
	                }
	                else {
	                    c.setBackground(Color.ORANGE);
	                }                        
	                return c;
	            }
	        });
			
			JScrollPane scrollFlashPane = new JScrollPane();
			scrollFlashPane.createHorizontalScrollBar();
			scrollFlashPane.createVerticalScrollBar();
			scrollFlashPane.add(flashTable);
	        scrollFlashPane.setViewportView(flashTable);
	        scrollFlashPane.setEnabled(false);
	        
	        JPanel rightComponent = new JPanel();
	        rightComponent.add(scrollFlashPane, BorderLayout.SOUTH);
	        rightComponent.add(flashTable.getTableHeader(), BorderLayout.NORTH);			
	        rightComponent.setBorder(compound);
	        
	        JSplitPane mainSplitPane;
			mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
	                leftComponent, rightComponent);
			mainSplitPane.setResizeWeight(1.0);
			mainSplitPane.setOneTouchExpandable(true);
			mainSplitPane.setContinuousLayout(true);
			
			//Generate mainPanel's bottom component
			JPanel bottomPanel = new JPanel();
			bottomPanel.setLayout(new GridLayout(1, 1, 1, 1));
			JLabel bottomLabel = new JLabel("Class Key: " + c.getClassCode(), SwingConstants.CENTER);
			bottomPanel.add(bottomLabel);
			
			//Add components to mainPanel
			mainPanel.add(topPanel, BorderLayout.PAGE_START);
			mainPanel.add(mainSplitPane, BorderLayout.CENTER);
			mainPanel.add(bottomPanel, BorderLayout.PAGE_END);
			
			
			String className = c.getName();
	        tClassTabs.addTab(className, mainPanel);
		}
		
		tClassPanel.add(tClassTabs);
		return tClassPanel;
	}
	
	
	/**
	 * The updateTeacherProfile method generates the GUI that allows a Teacher to update profile information.
	 */
	private void updateTeacherProfile() {
		
		//Initialize Window
		Dimension dimension = new Dimension(updateTeacherPWidth, updateTeacherPHeight);
		updateTeacherPWindow = new JFrame("Update Profile");
		updateTeacherPWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateTeacherPWindow.setSize(dimension);
		updateTeacherPWindow.setLayout(new BorderLayout());
		updateTeacherPWindow.setResizable(false);
		updateTeacherPWindow.setLocationRelativeTo(null);
		updateTeacherPWindow.setMinimumSize(dimension);
		updateTeacherPWindow.setMaximumSize(dimension);
		updateTeacherPWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, updateTeacherPWidth, updateTeacherPHeight);
		updateTeacherPWindow.add(background, BorderLayout.CENTER);
						
		//Teacher: FirstName JLabel | JTextField
		tFirstNameL.setBounds(25, 25, 75, 40);
		tFirstNameL.setForeground(Color.BLACK);
		background.add(tFirstNameL);
		tFirstNameTF.setBounds(95, 25, 210, 40);
		tFirstNameTF.setBorder(compound);
		background.add(tFirstNameTF);
		
		//Teacher: LastName JLabel | JTextField
		tLastNameL.setBounds(25, 70, 75, 40);
		tLastNameL.setForeground(Color.BLACK);
		background.add(tLastNameL);
		tLastNameTF.setBounds(95, 70, 210, 40);
		tLastNameTF.setBorder(compound);
		background.add(tLastNameTF);
						
		//Teacher: SchoolName JLabel | JTextField
		tSchoolNameL.setBounds(25, 115, 75, 40);
		tSchoolNameL.setForeground(Color.BLACK);
		background.add(tSchoolNameL);
		tSchoolNameTF.setBounds(95, 115, 210, 40);
		tSchoolNameTF.setBorder(compound);
		background.add(tSchoolNameTF);
		
		//Teacher: "Update" JButton
		JButton updateB = new JButton("Update");
		updateB.setBounds(95, 170, 100, 45);
		updateB.setBorder(compound);
		updateB.setOpaque(true);
		updateB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		tUpdateProfile = true;
	        		verifyTeacher();
	        }
	    	});
		background.add(updateB);
		
		//Teacher: "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(205, 170, 100, 45);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateTeacherPWindow.dispose();
	        }
	    	});
		background.add(exitB);
         
        //Display the window
        updateTeacherPWindow.pack();
        updateTeacherPWindow.setVisible(true);
	}
	
	
	/**
	 * The updateTeacherSecurity method generates the GUI that allows a Teacher to update security information. 
	 */
	private void updateTeacherSecurity() {
		
		//Initialize Window
		Dimension dimension = new Dimension(updateTeacherSWidth, updateTeacherSHeight);
		updateTeacherSWindow = new JFrame("Update Security");
		updateTeacherSWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateTeacherSWindow.setSize(dimension);
		updateTeacherSWindow.setLayout(new BorderLayout());
		updateTeacherSWindow.setResizable(false);
		updateTeacherSWindow.setLocationRelativeTo(null);
		updateTeacherSWindow.setMinimumSize(dimension);
		updateTeacherSWindow.setMaximumSize(dimension);
		updateTeacherSWindow.setPreferredSize(dimension);
		
		//Set background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, updateTeacherSWidth, updateTeacherSHeight);
		updateTeacherSWindow.add(background, BorderLayout.CENTER);
		
		//Teacher: New Password JLabel | JTextField
		JLabel passwordIconL = new JLabel(new ImageIcon(passwordImage));
		passwordIconL.setBounds(25, 55, 35, 35);
		passwordIconL.setBackground(Color.WHITE);
		passwordIconL.setOpaque(true);
		background.add(passwordIconL);
		tNewPasswordL1 = new JLabel("Enter New Password");
		tNewPasswordL1.setFont(masterFont);
		tNewPasswordL1.setBounds(25, 25, 150, 35);
		tNewPasswordL1.setForeground(Color.BLACK);
		tNewPasswordL1.setBackground(Color.WHITE);
		background.add(tNewPasswordL1);
		tPasswordPF1.setBounds(60, 55, 150, 35);
		tPasswordPF1.setText("");
		background.add(tPasswordPF1);
		
		//Teacher: Re-enter New Password JLabel | JTextField
		JLabel passwordIconL2 = new JLabel(new ImageIcon(passwordImage));
		passwordIconL2.setBounds(215, 55, 35, 35);
		passwordIconL2.setBackground(Color.WHITE);
		passwordIconL2.setOpaque(true);
		background.add(passwordIconL2);
		tNewPasswordL2 = new JLabel("Re-enter New Password");
		tNewPasswordL2.setFont(masterFont);
		tNewPasswordL2.setBounds(215, 25, 150, 35);
		tNewPasswordL2.setForeground(Color.BLACK);
		tNewPasswordL2.setBackground(Color.WHITE);
		background.add(tNewPasswordL2);
		tPasswordPF2.setBounds(250, 55, 150, 35);
		tPasswordPF2.setText("");
		background.add(tPasswordPF2);
		
		//Teacher: Enter Old Password JLabel | JTextField
		JLabel passwordIcon3 = new JLabel(new ImageIcon(passwordImage));
		passwordIcon3.setBounds(25, 120, 35, 35);
		passwordIcon3.setBackground(Color.WHITE);
		passwordIcon3.setOpaque(true);
		background.add(passwordIcon3);
		tOldPasswordL = new JLabel("Enter Old Password");
		tOldPasswordL.setFont(masterFont);
		tOldPasswordL.setBounds(25, 90, 150, 35);
		tOldPasswordL.setForeground(Color.BLACK);
		tOldPasswordL.setBackground(Color.WHITE);
		background.add(tOldPasswordL);
		tOldPasswordPF = new JPasswordField();
		tOldPasswordPF.setBounds(60, 120, 150, 35);
		tOldPasswordPF.setBorder(compound);
		background.add(tOldPasswordPF);
		
		//Teacher: Security Question 1 JLabel | JComboBox | JTextField
		tSecurityQ1L.setBounds(25, 155, 200, 35);
		tSecurityQ1L.setForeground(Color.BLACK);
		background.add(tSecurityQ1L);
		tSecurityList1.setBounds(25, 185, 225, 35);
		background.add(tSecurityList1);
		tSecurityQ1TF.setBounds(260, 185, 140, 35);
		background.add(tSecurityQ1TF);
		
		//Teacher: Security Question 2 JLabel | JComboBox | JTextField
		tSecurityQ2L.setBounds(25, 220, 200, 35);
		tSecurityQ2L.setForeground(Color.BLACK);
		background.add(tSecurityQ2L);	
		tSecurityList2.setBounds(25, 250, 225, 35);
		background.add(tSecurityList2);	
		tSecurityQ2TF.setBounds(260, 250, 140, 35);
		background.add(tSecurityQ2TF);
		
		//Teacher: "Update" JButton
		JButton updateB = new JButton("Update");
		updateB.setBounds(85, 300, 110, 50);
		updateB.setBorder(compound);
		updateB.setOpaque(true);
		updateB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		tUpdateSecurity = true;
	        		verifyTeacher();
	        }
	    	});
		background.add(updateB);
		
		//Teacher: "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(205, 300, 110, 50);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateTeacherSWindow.dispose();
	        }
	    	});
		background.add(exitB);
         
        //Display the window
        updateTeacherSWindow.pack();
        updateTeacherSWindow.setVisible(true);	
	}

	/**
	 * The addClassTeacher method generates the GUI used to create a new Class.  
	 */
	private void addClassTeacher() {
		
		//Initiate Window
		Dimension dimension = new Dimension(addClassTeacherWidth, addClassTeacherHeight);
		tAddClassWindow = new JFrame("Add Class");
		tAddClassWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tAddClassWindow.setSize(dimension);
		tAddClassWindow.setLayout(new BorderLayout());
		tAddClassWindow.setResizable(false);
		tAddClassWindow.setLocationRelativeTo(null);
		tAddClassWindow.setMinimumSize(dimension);
		tAddClassWindow.setMaximumSize(dimension);
		tAddClassWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, addClassTeacherWidth, addClassTeacherHeight);
		tAddClassWindow.add(background, BorderLayout.CENTER);
		
		//Teacher: ClassName JLabel | JTextField
		classNameL = new JLabel("Class Name");
		classNameL.setForeground(Color.DARK_GRAY);
		classNameL.setFont(new Font("Candara", Font.BOLD, 16));
		classNameL.setBounds(45, 10, 210, 40);
		background.add(classNameL);
		
		classNameTF = new JTextField();
		classNameTF.setBounds(45, 45, 210, 40);
		classNameTF.setBorder(compound);
		background.add(classNameTF);
		
		//Teacher: "Enter" JButton
		JButton enterB = new JButton("Enter");
		enterB.setBounds(45, 95, 100, 50);
		enterB.setBorder(compound);
		enterB.setOpaque(true);
		enterB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		verifyClassName();
	        }
	    	});
		background.add(enterB);
				
		//Teacher: "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(155, 95, 100, 50);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		tAddClassWindow.dispose();
	        }
	    	});
		background.add(exitB);
		         
		//Display the window
	    tAddClassWindow.pack();
		tAddClassWindow.setVisible(true);
	}
	
	/**
	 * The verifyClassName method verifies the class name input by user is valid, if so, it creates and adds new class
	 * 		to Teacher account. 
	 */
	private void verifyClassName() {
		
		if (classNameTF.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this,"Blank Field: Class Name","Error",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else {
			Class newClass = new Class(classNameTF.getText());
			teacher.addClass(newClass);
			tAddClassWindow.dispose();
			tPortalWindow.dispose();
			teacherPortalScreen();
		}
	}
	
	/**
	 * The deleteClassTeacher method allows the user (Teacher) to delete or rename a selected Class of choice. 
	 */
	private void deleteClassTeacher() {
		
		//Initiate Window
		Dimension dimension = new Dimension(deleteClassTeacherWidth, deleteClassTeacherHeight);
		tDeleteClassWindow = new JFrame("Edit Class");
		tDeleteClassWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tDeleteClassWindow.setSize(dimension);
		tDeleteClassWindow.setLayout(new BorderLayout());
		tDeleteClassWindow.setResizable(false);
		tDeleteClassWindow.setLocationRelativeTo(null);
		tDeleteClassWindow.setMinimumSize(dimension);
		tDeleteClassWindow.setMaximumSize(dimension);
		tDeleteClassWindow.setPreferredSize(dimension);
		
		//DeleteClassList JList
		TitledBorder classListBorder = BorderFactory.createTitledBorder("Class List");
		deleteClassModel = new DefaultListModel<>();
	    deleteClassList = new JList<>(deleteClassModel);
	    deleteClassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    deleteClassList.setBorder(classListBorder);
		deleteClassList.addMouseListener(this);
	    
	    //Add classes
	    for (Class c: teacherClasses) {
	    		deleteClassModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

	    //LeftPanel JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    leftPanel.add(new JScrollPane(deleteClassList));
	    
	    //RightPanel JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(3,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 30));

	    //Teacher: "Delete" JButton
	    JButton deleteB = new JButton("Delete");
	    deleteB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = deleteClassList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index >= 0) {
	        	  	deleteSelectedClass = teacherClasses.get(index);
	        	  	teacher.removeClass(deleteSelectedClass);
	        	  	deleteClassModel.remove(index);
	          }
	        }
	      });
	    
	    //Teacher: "Rename" JButton
	    JButton renameB = new JButton("Rename");
	    renameB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          ListSelectionModel selmodel = deleteClassList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index == -1)
	            return;
	          deleteSelectedClass = teacherClasses.get(index);
	          Object item = deleteClassModel.getElementAt(index);
	          String text = JOptionPane.showInputDialog("Rename item", item);
	          String newItem = null;

	          if (text != null) {
	            newItem = text.trim();
	          } else
	            return;

	          if (!newItem.isEmpty()) {
	            deleteClassModel.remove(index);
	            deleteSelectedClass.setName(newItem);
	            deleteClassModel.add(index, newItem);
	          }
	        }
	      });
	    
	    //Teacher: "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	tDeleteClassWindow.dispose();
	        	tPortalWindow.dispose();
	        	teacherPortalScreen();
	        }
	    	});
	    
	    rightPanel.add(deleteB);
	    rightPanel.add(renameB);
	    rightPanel.add(exitB);

	    panel.add(leftPanel);
	    panel.add(rightPanel);

	    tDeleteClassWindow.add(panel);
	   
	    //Display the window
	    tDeleteClassWindow.pack();
	    tDeleteClassWindow.setVisible(true);
	}
	
	/**
	 * The createStudyName method generates the GUI that allows a user to create new study material. 
	 */
	private void createStudyName() {
		
		//Initiate Window
		Dimension dimension = new Dimension(createStudyWidth, createStudyHeight);
		createStudyNameWindow = new JFrame("Create Study");
		createStudyNameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createStudyNameWindow.setSize(dimension);
		createStudyNameWindow.setLayout(new BorderLayout());
		createStudyNameWindow.setResizable(false);
		createStudyNameWindow.setLocationRelativeTo(null);
		createStudyNameWindow.setMinimumSize(dimension);
		createStudyNameWindow.setMaximumSize(dimension);
		createStudyNameWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, createStudyWidth, createStudyHeight);
		createStudyNameWindow.add(background, BorderLayout.CENTER);
				
		//Teacher || Student: Password JLabel | JTextField
		JLabel subjectNameL = new JLabel("Subject Name");
		subjectNameL.setForeground(Color.DARK_GRAY);
		subjectNameL.setFont(new Font("Candara", Font.BOLD, 16));
		subjectNameL.setBounds(45, 10, 210, 40);
		background.add(subjectNameL);
		subjectNameTF = new JTextField();
		subjectNameTF.setBounds(45, 45, 210, 40);
		subjectNameTF.setBorder(compound);
		background.add(subjectNameTF);
		
		//Teacher || Student: "Enter" JButton
		JButton enterB = new JButton("Enter");
		enterB.setBounds(45, 95, 100, 50);
		enterB.setBorder(compound);
		enterB.setOpaque(true);
		enterB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          if (verifyStudyName() == true) {
	        	  	newFlashCard = new FlashCard(subjectNameTF.getText());
	        	  	createStudyNameWindow.dispose();
	        	  	createStudyMaterial();
	          }
	        }
	      });
		background.add(enterB);
			
		//Teacher || Student: "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(155, 95, 100, 50);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	createStudyNameWindow.dispose();
	        }
	      });
		background.add(exitB);
		         
		//Display the window
		createStudyNameWindow.pack();
		createStudyNameWindow.setVisible(true);
		
	}
	
	
	/**
	 * The verifyStudyName method verifies the subject name input by user is valid.
	 * @return	boolean - true if valid, false if invalid
	 */
	private boolean verifyStudyName() {
		
		if (subjectNameTF.getText().isEmpty()) { 
			JOptionPane.showMessageDialog(this,"Blank Field: Subject Name","Error",JOptionPane.ERROR_MESSAGE);
			subjectNameTF.setBackground(Color.LIGHT_GRAY);
			return false;
		}
		else {
			return true;
		}
	}
	
	
	/**
	 * The createStudyMaterial method generates the GUI that allows a user to input new study material. 
	 */
	private void createStudyMaterial() {
		
		//Initialize Window
		Dimension createStudySize = new Dimension(createStudyMaterialWidth, createStudyMaterialHeight);
		createStudyMaterialWindow = new JFrame("Create Study Material");
		createStudyMaterialWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createStudyMaterialWindow.setSize(createStudySize);
		createStudyMaterialWindow.setLayout(new BorderLayout());
		createStudyMaterialWindow.setResizable(false);
		createStudyMaterialWindow.setLocationRelativeTo(null);
		createStudyMaterialWindow.setMinimumSize(createStudySize);
		createStudyMaterialWindow.setMaximumSize(createStudySize);
		createStudyMaterialWindow.setPreferredSize(createStudySize);
		
		//StudyPanel JPanel
		JPanel studyPanel = new JPanel();
		studyPanel.setLayout(new BoxLayout(studyPanel, BoxLayout.PAGE_AXIS));
		
		//TopPanel JPanel
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new GridLayout(1,2,1,1));
		
		//Question JLabel
		JLabel questionL = new JLabel("Question", SwingConstants.CENTER);
		topPanel.add(questionL);
		
		//Answer JLabel
		JLabel answerL = new JLabel("Answer", SwingConstants.CENTER);
		topPanel.add(answerL);
		
		//MiddlePanel JPanel
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new BorderLayout());
		middlePanel.setPreferredSize(new Dimension(createStudyMaterialWidth, createStudyMaterialHeight));
		
		//Left & Right JTextArea's
		leftTA = new JTextArea();
		rightTA = new JTextArea();
		
		//StudySplit JSplitPane	
		JSplitPane studySplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftTA, rightTA);
		studySplit.setResizeWeight(0.5);
		studySplit.setDividerLocation(0.5);
		studySplit.setOneTouchExpandable(true);
		studySplit.setContinuousLayout(true);
		
		middlePanel.add(studySplit);
		
		//BottomPanel JPanel
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout());
		
		//Teacher || Student: "Add" JButton
		JButton addB = new JButton("Add");
		addB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        	String[] card = {leftTA.getText(),rightTA.getText()};
	    		leftTA.setText("");
	    		rightTA.setText("");
	    		newFlashCard.addCard(card);
	        }
	      });
		bottomPanel.add(addB);
		
		//Teacher || Student: "Done" JButton
		JButton doneB = new JButton("Done");
		doneB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	        		createStudyMaterialWindow.dispose();
	        		if (teacherAccount) {
	        			assignStudyMaterial();
	        		}
	        		else if (studentAccount) {
	        			createStudyStudent();
	        		}
	        }
	      });
		bottomPanel.add(doneB);
		
		studyPanel.add(topPanel);
		studyPanel.add(middlePanel);
		studyPanel.add(bottomPanel);
		
		//Display the window
		createStudyMaterialWindow.add(studyPanel);
		createStudyMaterialWindow.pack();
		createStudyMaterialWindow.setVisible(true);
	}
	
	
	/**
	 * The assignStudyMaterial method allows the user to assign the newly created study material (FlashCard)
	 * 		to a Class, specified by the user. 
	 */
	private void assignStudyMaterial() {
		
		//Initiate Window
		tAssignStudyWindow = new JFrame("Assign Study Material");
		tAssignStudyWindow.setSize(assignStudyMaterialWidth, assignStudyMaterialHeight);
	    tAssignStudyWindow.setLocationRelativeTo(null);
	    tAssignStudyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		tAddStudyModel = new DefaultListModel<>();
	    JList classList = new JList<>(tAddStudyModel);
	    classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    classList.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		classList.addMouseListener(this);
	    
	    for (Class c: teacherClasses) {
	    		tAddStudyModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    //LeftPanel JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    leftPanel.add(new JScrollPane(classList));
	    
	    //RightPanel JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(3,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 30));

	    //Teacher: "Assign" JButton
	    JButton assignB = new JButton("Assign");
	    assignB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = classList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          Class selectedClass = teacherClasses.get(index);
	          if (index >= 0 && previousClass != selectedClass) {
	        	  	selectedClass.addFlashCard(newFlashCard);
	        	  	previousClass = selectedClass;
	          }
	        }
	    });
	    rightPanel.add(assignB);
		
	    //Teacher: "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        tAssignStudyWindow.dispose();
	        	tPortalWindow.dispose();
	        	teacherPortalScreen();
	        }
	    	});
	    rightPanel.add(exitB);
	    
	    panel.add(leftPanel);
	    panel.add(rightPanel);

	    //Display the window
	    tAssignStudyWindow.add(panel);
	    tAssignStudyWindow.setVisible(true);
	}
	
	
	/**
	 * The deleteStudyMaterial method allows the user to delete the selected study material from a Class,
	 * 		specified by the user. 
	 */
	private void deleteStudyMaterial() {

		//Initiate Window
		tDeleteStudyWindow = new JFrame("Edit Study");
		tDeleteStudyWindow.setSize(deleteStudyMaterialWidth, deleteStudyMaterialHeight);
	    tDeleteStudyWindow.setLocationRelativeTo(null);
	    tDeleteStudyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ClassList JList
		tClassModel = new DefaultListModel<>();
		TitledBorder classListBorder = BorderFactory.createTitledBorder("Class List");
		JList classList = new JList<>(tClassModel);
		classList.setBorder(classListBorder);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.addMouseListener(this);
		
		//StudyList JList
		tStudyModel = new DefaultListModel<>();
		TitledBorder studyListBorder = BorderFactory.createTitledBorder("Study List");
	    JList studyList = new JList<>(tStudyModel);
	    studyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    studyList.setBorder(studyListBorder);
		studyList.addMouseListener(this);
	    
		//Add classes
	    for (Class c: teacherClasses) {
	    		tClassModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(1,3,1,1));
	    panel.setBorder(compound);

	    //LeftPanel JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    leftPanel.add(new JScrollPane(classList));
	    
	    //MiddlePanel JPanel
	    JPanel middlePanel = new JPanel();
	    middlePanel.setLayout(new BorderLayout());
	    middlePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
	    middlePanel.add(new JScrollPane(studyList));
	    
	    //RightPanel JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(3,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 30));

	    //Teacher: "Select" JButton
	    JButton selectB = new JButton("Select");
	    selectB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = classList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index >= 0 && isClassSelected == false) {
	        	  	tSelectedClass = teacherClasses.get(index);
	        	  	tFlashCards = tSelectedClass.getFlashCards();
	        	  	addFlashCards(tFlashCards, tStudyModel);
	        	  	tDeleteStudyWindow.revalidate();
	        	  	isClassSelected = true;
	        	  	previousIndex = index;
	          }
	          else if (index >= 0 && isClassSelected == true) {
	        	  	if (index >= 0 && index != previousIndex) {
	        	  		tStudyModel.clear();
	        	  		tSelectedClass = teacherClasses.get(index);
		        	  	tFlashCards = tSelectedClass.getFlashCards();
		        	  	addFlashCards(tFlashCards, tStudyModel);
		        	  	tDeleteStudyWindow.revalidate();
		        	  	previousIndex = index;
	        	  	}
	          }
	        }
	      });
	    rightPanel.add(selectB);
	    
	    //Teacher: "Delete" JButton
	    JButton deleteB = new JButton("Delete");
	    deleteB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = studyList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          System.out.println(index);
	          if (index >= 0) {
	        	  	FlashCard selectedFlashCard = tFlashCards.get(index);
	        	  	tSelectedClass.removeFlashCard(selectedFlashCard, index);
	        	  	tStudyModel.remove(index);
	        	  	tDeleteStudyWindow.revalidate();
	          }
	        }
	      });
	    rightPanel.add(deleteB);
	    
	    //Teacher: "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	tDeleteStudyWindow.dispose();
	        	tPortalWindow.dispose();
	        	teacherPortalScreen();
	        }
	    	});
	    rightPanel.add(exitB);

	    panel.add(leftPanel);
	    panel.add(middlePanel);
	    panel.add(rightPanel);

	    //Display the window
	    tDeleteStudyWindow.add(panel);
	    tDeleteStudyWindow.setVisible(true);
	}
	
	
	/**
	 * The addFlashCards method adds all study material for a selected class to the a JList, allowing the user to 
	 * 		select and delete study material from the JList. 
	 * @param flashCards
	 * @param model
	 */
	private void addFlashCards(ArrayList<FlashCard> flashCards, DefaultListModel<String> model) {
		for (FlashCard fC : flashCards) {
	  		model.addElement(fC.getName());
	  	}
	}
	
	
	/**
	 *  The studentPortalScreen method generates the Student portal GUI, in which the Student will interact with. 
	 */
	private void studentPortalScreen() {
		
		// Initialize Windows
		Dimension dimension = new Dimension(studentPortalWidth, studentPortalHeight);
		sPortalWindow = new JFrame("Student Portal");
		sPortalWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sPortalWindow.setSize(dimension);
		sPortalWindow.setLayout(new BorderLayout());
		sPortalWindow.setResizable(false);
		sPortalWindow.setLocationRelativeTo(null);
		sPortalWindow.setMinimumSize(dimension);
		sPortalWindow.setMaximumSize(dimension);
		sPortalWindow.setPreferredSize(dimension);
		
		//StudentPortal JPanel
		studentPortal = new JPanel();
		studentPortal.setLayout(new GridLayout(1, 1));
		studentPortal.setSize(dimension);
				
		//Student JTabbedPane & JComponent's
		sTabbedPane = new JTabbedPane();

		sProfileComponent = makeStudentProfilePanel();
		sTabbedPane.addTab("Dashboard", sProfileComponent);
		sTabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		
		//Add JTabbedPane to JPanel
		studentPortal.add(sTabbedPane);
				
		//Add JPanel to JFrame
		sPortalWindow.add(studentPortal, BorderLayout.CENTER);
		         
		//Display the window
		sPortalWindow.pack();
		sPortalWindow.setVisible(true);
				
		//Enable scrolling tabs
		sTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);  
	}
	
	
	/**
	 * The makeStudentProfilePanel method generates the components found on the Student portal. 
	 * @return		JComponent - containing all components found on the Student portal. 
	 */
	private JComponent makeStudentProfilePanel() {
		
		//Initiate profilePanel
		JPanel profilePanel = new JPanel();
		profilePanel.setLayout(new GridLayout(1,3,1,1));
		profilePanel.setSize(studentPortalWidth, studentPortalHeight);
		profilePanel.setBorder(compound);
				
		//Generate profilePanel's left component
		TitledBorder leftPanelTitle, usernameTitle, firstNameTitle, lastNameTitle, schoolNameTitle, sQ1Title, sQ2Title;
		leftPanelTitle = BorderFactory.createTitledBorder("Profile Information");
		usernameTitle = BorderFactory.createTitledBorder("Username");
		firstNameTitle = BorderFactory.createTitledBorder("First");
		lastNameTitle = BorderFactory.createTitledBorder("Last");
		schoolNameTitle = BorderFactory.createTitledBorder("School");
		sQ1Title = BorderFactory.createTitledBorder("Security Question 1");
		sQ2Title = BorderFactory.createTitledBorder("Security Question 2");
		
		//Main leftPanel
		JPanel leftPanel = new JPanel();
		leftPanel.setLayout(new GridLayout(6,1,1,1));
		leftPanel.setBorder(leftPanelTitle);
				
		//Username JLabel
		JLabel username = new JLabel(student.getUsername());
		username.setBorder(usernameTitle);
		username.setFont(masterFont);
		leftPanel.add(username);
				
		//FirstName JLabel
		JLabel firstName = new JLabel(student.getFirstName());
		firstName.setBorder(firstNameTitle);
		firstName.setFont(masterFont);
		leftPanel.add(firstName);
				
		//LastName JLabel
		JLabel lastName = new JLabel(student.getLastName());
		lastName.setBorder(lastNameTitle);
		lastName.setFont(masterFont);
		leftPanel.add(lastName);
				
		//SchoolName JLabel
		JLabel schoolName = new JLabel(student.getSchoolName());
		schoolName.setBorder(schoolNameTitle);
		schoolName.setFont(masterFont);
		leftPanel.add(schoolName);

		//Security Question 1 JLabel | JTextField
		JLabel sQ1L = new JLabel((String)sSecurityList1.getSelectedItem());
		sQ1L.setBorder(sQ1Title);
		sQ1L.setFont(masterFont);
		leftPanel.add(sQ1L);
				
		//Security Question 2 JLabel | JTextField
		JLabel sQ2L = new JLabel((String)sSecurityList2.getSelectedItem());
		sQ2L.setBorder(sQ2Title);
		sQ2L.setFont(masterFont);
		leftPanel.add(sQ2L);
				
		//Generate middle component
		TitledBorder characterTitle, gameTitle;
		characterTitle = BorderFactory.createTitledBorder("Character");
		gameTitle = BorderFactory.createTitledBorder("Game");
		
		//Main middlePanel JPanel
		JPanel middlePanel = new JPanel();
		middlePanel.setLayout(new GridLayout(2,1,1,1));
		
		//MiddleTopPanel JPanel
		JPanel middleTopPanel = new JPanel();
		middleTopPanel.setBorder(characterTitle);
		
		//Character Image JLabel
		Character character = student.getCurrentCharacter();
		ImageIcon icon = new ImageIcon("Resource/" + character.getCharacterName() + ".png");
		Image image = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
		characterImage = new JLabel(new ImageIcon(image));
		middleTopPanel.add(characterImage);
		middlePanel.add(middleTopPanel, BorderLayout.PAGE_START);
		
		//Student: "Change Character" JButton
		JButton changeCharacterB = new JButton("Change Character");
		changeCharacterB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		changeC = true;
	        		changeCharacter();
	        }
	    	});
		middleTopPanel.add(changeCharacterB, BorderLayout.PAGE_END);
		
		//MiddleBottomPanel JPanel
		TitledBorder scoreTitle = BorderFactory.createTitledBorder("Score");
		scoreTitle.setTitleColor(Color.WHITE);
		JPanel middleBottomPanel = new JPanel();
		middleBottomPanel.setBorder(gameTitle);
		middleBottomPanel.setLayout(new GridLayout(4,1,1,1));

		//ScoreL JLabel
		JLabel scoreL = new JLabel(Integer.toString(student.getScore()) + "    ");
		scoreL.setFont(new Font("Candara", Font.PLAIN, 30));
		scoreL.setBackground(Color.BLUE);
		scoreL.setOpaque(true);
		scoreL.setForeground(Color.WHITE);
		scoreL.setHorizontalAlignment(SwingConstants.RIGHT);
        scoreL.setVerticalAlignment(SwingConstants.CENTER);
		scoreL.setBorder(scoreTitle);
		middleBottomPanel.add(scoreL);
		
		//Student "New Game" JButton
		JButton newGameB = new JButton("New Game");
		newGameB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		sPortalWindow.dispose();
	        		runGame();
	        }
	    	});
		middleBottomPanel.add(newGameB);
		
		//Student "Store" JButtom
		JButton storeB = new JButton("Store");
		storeB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		//store();
	        }
	    	});
		middleBottomPanel.add(storeB);
		
		//Student "Logout" JButton
		JButton logoutB = new JButton("Logout");
		logoutB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		sPortalWindow.dispose();
	        		loginScreen();
	        }
	    	});
		middleBottomPanel.add(logoutB);
		
		middlePanel.add(middleBottomPanel, BorderLayout.PAGE_END);
	
		//Generate right component 
		TitledBorder topTitle, middleTitle, bottomTitle, title;
		topTitle = BorderFactory.createTitledBorder("Profile");
		middleTitle =  BorderFactory.createTitledBorder("Class");
		bottomTitle = BorderFactory.createTitledBorder("Study");
		title = BorderFactory.createTitledBorder("Controls");
				
		//Main rightPanel
		JPanel rightPanel = new JPanel();
		rightPanel.setLayout(new GridLayout(3,1,1,1));
				
		//TopRightPanel
		JPanel topRightPanel = new JPanel();
		topRightPanel.setLayout(new GridLayout(3,1,1,1));
		topRightPanel.setBorder(topTitle);
				
		//Student "Edit Profile" JButton
		JButton editProfileB = new JButton("Edit Profile");
		editProfileB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentProfile();
			}
		});
		topRightPanel.add(editProfileB);
		
		//Student "Edit Security" JButton
		JButton editSecurityB = new JButton("Edit Security");
		editSecurityB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateStudentSecurity();
			}
		});
		topRightPanel.add(editSecurityB);
				
		//MiddleRightPanel
		JPanel middleRightPanel = new JPanel();
		middleRightPanel.setLayout(new GridLayout(3,1,1,1));
		middleRightPanel.setBorder(middleTitle);
				
		//Student: "Add Class" JButton
		JButton addClassB = new JButton("Add Class");
		addClassB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addClassStudent();
			}
		});
		middleRightPanel.add(addClassB);
				
		//Student: "Edit Class" JButton
		JButton editClassB = new JButton("Edit Class");
		editClassB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteClassStudent();
			}
		});
		middleRightPanel.add(editClassB);
				
		//BottomRightPanel
		JPanel bottomRightPanel = new JPanel();
		bottomRightPanel.setLayout(new GridLayout(3,1,1,1));
		bottomRightPanel.setBorder(bottomTitle);
		
		//Student: "New Study" JButton
		JButton newStudyB = new JButton("New Study");
		newStudyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createStudyName();
				newFlashCard = new FlashCard(subjectNameTF.getText());
			}
		});
		bottomRightPanel.add(newStudyB);
				
		//Student: "Edit Study" JButton
		JButton editStudyB = new JButton("Edit Study");
		editStudyB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteStudentStudy();
			}
		});
		bottomRightPanel.add(editStudyB);
				
		//Add JPanel's to main JPanel
		rightPanel.setBorder(title);
		rightPanel.add(topRightPanel);
		rightPanel.add(middleRightPanel);
		rightPanel.add(bottomRightPanel);
				
		profilePanel.add(leftPanel);
		profilePanel.add(middlePanel);
		profilePanel.add(rightPanel);
		return profilePanel;
	}
	
	
	/**
	 * The updateStudentProfile method generates the GUI that allows a Student to update profile information.
	 */
	private void updateStudentProfile() {
		
		//Initiate Windows
		Dimension dimension = new Dimension(updateStudentPWidth, updateStudentPHeight);
		updateStudentPWindow = new JFrame("Update Profile");
		updateStudentPWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateStudentPWindow.setSize(dimension);
		updateStudentPWindow.setLayout(new BorderLayout());
		updateStudentPWindow.setResizable(false);
		updateStudentPWindow.setLocationRelativeTo(null);
		updateStudentPWindow.setMinimumSize(dimension);
		updateStudentPWindow.setMaximumSize(dimension);
		updateStudentPWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, updateStudentPWidth, updateStudentPHeight);
		updateStudentPWindow.add(background, BorderLayout.CENTER);
						
		//Student: FirstName JLabel | JTextField
		sFirstNameL.setBounds(25, 25, 75, 40);
		sFirstNameL.setForeground(Color.BLACK);
		background.add(sFirstNameL);
		sFirstNameTF.setBounds(95, 25, 210, 40);
		sFirstNameTF.setBorder(compound);
		background.add(sFirstNameTF);
		
		//Teacher: LastName JLabel | JTextField
		sLastNameL.setBounds(25, 70, 75, 40);
		sLastNameL.setForeground(Color.BLACK);
		background.add(sLastNameL);
		sLastNameTF.setBounds(95, 70, 210, 40);
		sLastNameTF.setBorder(compound);
		background.add(sLastNameTF);
						
		//Teacher: SchoolName JLabel | JTextField
		sSchoolNameL.setBounds(25, 115, 75, 40);
		sSchoolNameL.setForeground(Color.BLACK);
		background.add(sSchoolNameL);
		sSchoolNameTF.setBounds(95, 115, 210, 40);
		sSchoolNameTF.setBorder(compound);
		background.add(sSchoolNameTF);
		
		//Teacher: "Update" JButton
		JButton updateB = new JButton("Update");
		updateB.setBounds(95, 170, 100, 45);
		updateB.setBorder(compound);
		updateB.setOpaque(true);
		updateB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		sUpdateProfile = true;
	        		verifyStudent();
	        }
	    	});
		background.add(updateB);
		
		//Teacher: "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(205, 170, 100, 45);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateStudentPWindow.dispose();
	        }
	    	});
		background.add(exitB);
         
        //Display the window
        updateStudentPWindow.pack();
        updateStudentPWindow.setVisible(true);
	}
	
	
	/**
	 * The updateStudentSecurity method generates the GUI that allows a Student to update security information.
	 */
	private void updateStudentSecurity() {
			
		//Initialize Window
		Dimension dimension = new Dimension(updateStudentSWidth, updateStudentSHeight);
		updateStudentSWindow = new JFrame("Update Security");
		updateStudentSWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		updateStudentSWindow.setSize(dimension);
		updateStudentSWindow.setLayout(new BorderLayout());
		updateStudentSWindow.setResizable(false);
		updateStudentSWindow.setLocationRelativeTo(null);
		updateStudentSWindow.setMinimumSize(dimension);
		updateStudentSWindow.setMaximumSize(dimension);
		updateStudentSWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, updateStudentSWidth, updateStudentSHeight);
		updateStudentSWindow.add(background, BorderLayout.CENTER);
		
		//Student New Password JLabel | JTextField
		JLabel passwordIconL = new JLabel(new ImageIcon(passwordImage));
		passwordIconL.setBounds(25, 55, 35, 35);
		passwordIconL.setBackground(Color.WHITE);
		passwordIconL.setOpaque(true);
		background.add(passwordIconL);
		sNewPasswordL1 = new JLabel("Enter New Password");
		sNewPasswordL1.setFont(masterFont);
		sNewPasswordL1.setBounds(25, 25, 150, 35);
		sNewPasswordL1.setForeground(Color.BLACK);
		sNewPasswordL1.setBackground(Color.WHITE);
		background.add(sNewPasswordL1);
		sPasswordPF1.setBounds(60, 55, 150, 35);
		sPasswordPF1.setText("");
		background.add(sPasswordPF1);
		
		//Student Re-enter New Password JLabel | JTextField
		JLabel passwordIconL2 = new JLabel(new ImageIcon(passwordImage));
		passwordIconL2.setBounds(215, 55, 35, 35);
		passwordIconL2.setBackground(Color.WHITE);
		passwordIconL2.setOpaque(true);
		background.add(passwordIconL2);
		sNewPasswordL2 = new JLabel("Re-enter New Password");
		sNewPasswordL2.setFont(masterFont);
		sNewPasswordL2.setBounds(215, 25, 150, 35);
		sNewPasswordL2.setForeground(Color.BLACK);
		sNewPasswordL2.setBackground(Color.WHITE);
		background.add(sNewPasswordL2);
		sPasswordPF2.setBounds(250, 55, 150, 35);
		sPasswordPF2.setText("");
		background.add(sPasswordPF2);
		
		//Student Enter Old Password JLabel | JTextField
		JLabel passwordIcon3 = new JLabel(new ImageIcon(passwordImage));
		passwordIcon3.setBounds(25, 120, 35, 35);
		passwordIcon3.setBackground(Color.WHITE);
		passwordIcon3.setOpaque(true);
		background.add(passwordIcon3);
		sOldPasswordL = new JLabel("Enter Old Password");
		sOldPasswordL.setFont(masterFont);
		sOldPasswordL.setBounds(25, 90, 150, 35);
		sOldPasswordL.setForeground(Color.BLACK);
		sOldPasswordL.setBackground(Color.WHITE);
		background.add(sOldPasswordL);
		sOldPasswordPF = new JPasswordField();
		sOldPasswordPF.setBounds(60, 120, 150, 35);
		sOldPasswordPF.setBorder(compound);
		background.add(sOldPasswordPF);
		
		//Student Security Question 1 JLabel | JComboBox | JTextField
		sSecurityQ1L.setBounds(25, 155, 200, 35);
		sSecurityQ1L.setForeground(Color.BLACK);
		background.add(sSecurityQ1L);
		sSecurityList1.setBounds(25, 185, 225, 35);
		background.add(sSecurityList1);
		sSecurityQ1TF.setBounds(260, 185, 140, 35);
		background.add(sSecurityQ1TF);
		
		//Student Security Question 2 JLabel | JComboBox | JTextField
		sSecurityQ2L.setBounds(25, 220, 200, 35);
		sSecurityQ2L.setForeground(Color.BLACK);
		background.add(sSecurityQ2L);	
		sSecurityList2.setBounds(25, 250, 225, 35);
		background.add(sSecurityList2);	
		sSecurityQ2TF.setBounds(260, 250, 140, 35);
		background.add(sSecurityQ2TF);
		
		//Student "Update" JButton
		JButton saveB = new JButton("Update");
		saveB.setBounds(85, 300, 110, 50);
		saveB.setBorder(compound);
		saveB.setOpaque(true);
		saveB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		sUpdateSecurity = true;
	        		verifyStudent();
	        }
	    	});
		background.add(saveB);
		
		//Student "Exit" JButton
		JButton backB = new JButton("Exit");
		backB.setBounds(205, 300, 110, 50);
		backB.setBorder(compound);
		backB.setOpaque(true);
		backB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		updateStudentSWindow.dispose();
	        }
	    	});
		background.add(backB);
         
        //Display the window
        updateStudentSWindow.pack();
        updateStudentSWindow.setVisible(true);
		
	}
	
	
	/**
	 * The addClassStudent method generates the GUI used to create a new Class.
	 */
	private void addClassStudent() {

		//Initiate Window
		Dimension dimension = new Dimension(addClassStudentWidth, addClassStudentHeight);
		sAddClassWindow = new JFrame("Add Class");
		sAddClassWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sAddClassWindow.setSize(dimension);
		sAddClassWindow.setLayout(new BorderLayout());
		sAddClassWindow.setResizable(false);
		sAddClassWindow.setLocationRelativeTo(null);
		sAddClassWindow.setMinimumSize(dimension);
		sAddClassWindow.setMaximumSize(dimension);
		sAddClassWindow.setPreferredSize(dimension);
		
		//Set Background
		//JLabel background = new JLabel(new ImageIcon("Resource/loginBackground.jpg"));
		JLabel background = new JLabel();
		background.setBounds(0, 0, addClassStudentWidth, addClassStudentHeight);	
		sAddClassWindow.add(background, BorderLayout.CENTER);
		
		//Student ClassKey JLabel | JTextField
		JLabel classKeyL = new JLabel("Class Key");
		classKeyL.setForeground(Color.DARK_GRAY);
		classKeyL.setFont(new Font("Candara", Font.BOLD, 16));
		classKeyL.setBounds(45, 10, 210, 40);
		background.add(classKeyL);
		
		classKeyTF = new JTextField();
		classKeyTF.setBounds(45, 45, 210, 40);
		classKeyTF.setBorder(compound);
		background.add(classKeyTF);
		
		//Student "Enter" JButton
		JButton enterB = new JButton("Enter");
		enterB.setBounds(45, 95, 100, 50);
		enterB.setBorder(compound);
		enterB.setOpaque(true);
		enterB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		//searchClassCode();
				sAddClassWindow.dispose();
				sPortalWindow.dispose();
				studentPortalScreen();
	        }
	    	});
		background.add(enterB);
				
		//Student "Exit" JButton
		JButton exitB = new JButton("Exit");
		exitB.setBounds(155, 95, 100, 50);
		exitB.setBorder(compound);
		exitB.setOpaque(true);
		exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        		sAddClassWindow.dispose();
	        }
	    	});
		background.add(exitB);
		
		//Display the window
	    sAddClassWindow.pack();
		sAddClassWindow.setVisible(true);
	}
	
	
	/**
	 * The deleteClassStudent method allows the user (Student) to delete or rename a selected Class of choice.
	 */
	private void deleteClassStudent() {
		
		//Attributes
		ArrayList<Class> studentClasses = student.getClasses();
		
		//Initiate Window
		Dimension dimension = new Dimension(deleteClassTeacherWidth, deleteClassTeacherHeight);
		sDeleteClassWindow = new JFrame("Edit Class");
		sDeleteClassWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sDeleteClassWindow.setSize(dimension);
		sDeleteClassWindow.setLayout(new BorderLayout());
		sDeleteClassWindow.setResizable(false);
		sDeleteClassWindow.setLocationRelativeTo(null);
		sDeleteClassWindow.setMinimumSize(dimension);
		sDeleteClassWindow.setMaximumSize(dimension);
		sDeleteClassWindow.setPreferredSize(dimension);
		
		//DeleteClassList JList
		TitledBorder classListBorder = BorderFactory.createTitledBorder("Class List");
		deleteClassModel = new DefaultListModel<>();
	    deleteClassList = new JList<>(deleteClassModel);
	    deleteClassList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    deleteClassList.setBorder(classListBorder);
		deleteClassList.addMouseListener(this);
	    
	    //Add classes
	    for (Class c: studentClasses) {
	    		deleteClassModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

	    //LeftPanel JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    leftPanel.add(new JScrollPane(deleteClassList));
	    
	    //RightPanel JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(3,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 30));

	    //Student "Delete" JButton
	    JButton deleteB = new JButton("Delete");
	    deleteB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = deleteClassList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index >= 0) {
	        	  	deleteSelectedClass = studentClasses.get(index);
	        	  	if (deleteSelectedClass.getName() != "Personal") {
	        	  		student.removeClass(deleteSelectedClass);
	        	  		deleteClassModel.remove(index);
	        	  	}
	          }
	        }
	      });
	    
	    //Student "Rename" JButton
	    JButton renameB = new JButton("Rename");
	    renameB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	          ListSelectionModel selmodel = deleteClassList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index == -1)
	            return;
	          deleteSelectedClass = studentClasses.get(index);
	          if (deleteSelectedClass.getName() != "Personal") {
	        	  	Object item = deleteClassModel.getElementAt(index);
	        	  	String text = JOptionPane.showInputDialog("Rename Class", item);
	        	  	String newItem = null;

	        	  	if (text != null) {
	        	  		newItem = text.trim();
	        	  	} else
	        	  		return;

	        	  	if (!newItem.isEmpty()) {
	        	  		deleteClassModel.remove(index);
	        	  		deleteSelectedClass.setName(newItem);
	        	  		deleteClassModel.add(index, newItem);
	        	  	}
	          }
	        }
	      });
	    
	    //Student "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	sDeleteClassWindow.dispose();
	        	sPortalWindow.dispose();
	        	studentPortalScreen();
	        }
	    	});
	    
	    rightPanel.add(deleteB);
	    rightPanel.add(renameB);
	    rightPanel.add(exitB);

	    panel.add(leftPanel);
	    panel.add(rightPanel);

	    sDeleteClassWindow.add(panel);
	   
	    //Display the window
	    sDeleteClassWindow.pack();
	    sDeleteClassWindow.setVisible(true);
	}
	
	
	/**
	 * The createStudyStudent method allows the user (Student) to assign created study material (FlashCard) to 
	 * 		a specified Class of choice. 
	 */
	private void createStudyStudent() {
		
		//Initiate Window
		sAssignStudyWindow = new JFrame("Assign Study Material");
		sAssignStudyWindow.setSize(assignStudyWidth, assignStudyHeight);
		sAssignStudyWindow.setLocationRelativeTo(null);
		sAssignStudyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Class> sClasses = student.getClasses();
		
		sAddStudyModel = new DefaultListModel<>();
	    JList classList = new JList<>(sAddStudyModel);
	    classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	    classList.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		classList.addMouseListener(this);
	    
		//Add classes
	    for (Class c: sClasses) {
	    		sAddStudyModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
	    
	    //Left JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
	    leftPanel.add(new JScrollPane(classList));
	    
	    //Right JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(2,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(75, 10, 75, 20));

	    //Student: "Assign" JButton
	    JButton assignB = new JButton("Assign");
	    assignB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = classList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index >= 0) {
	        	  	Class selectedClass = sClasses.get(index);
	        	  	selectedClass.addFlashCard(newFlashCard);
	          }
	        }
	      });
	    rightPanel.add(assignB);
		
	    //Student: "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        sAssignStudyWindow.dispose();
	        	sPortalWindow.dispose();
	        	studentPortalScreen();
	        }
	    	});
	    rightPanel.add(exitB);

	    panel.add(leftPanel);
	    panel.add(rightPanel);

	    sAssignStudyWindow.add(panel);
	    sAssignStudyWindow.setVisible(true);
		
	}
	
	
	/**
	 * The deleteStudyStudent method allows the user (Student) to delete study material (FlashCard) from a 
	 * 		specified Class of choice. 
	 */
	private void deleteStudentStudy() {
		
		//Initiate Window
		sDeleteStudyWindow = new JFrame("Edit Study");
		sDeleteStudyWindow.setSize(deleteStudyWidth, deleteStudyHeight);
		sDeleteStudyWindow.setLocationRelativeTo(null);
		sDeleteStudyWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ArrayList<Class> sClasses = student.getClasses();
		
		//Student: classList JList
		TitledBorder classListBorder = BorderFactory.createTitledBorder("Class List");
		sClassModel = new DefaultListModel<>();
		JList classList = new JList<>(sClassModel);
		classList.setBorder(classListBorder);
		classList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		classList.addMouseListener(this);
		
		//Student: studyList JList
		TitledBorder studyListBorder = BorderFactory.createTitledBorder("Study List");
		sStudyModel = new DefaultListModel<>();
	    JList studyList = new JList<>(sStudyModel);
	    studyList.setBorder(studyListBorder);
	    studyList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		studyList.addMouseListener(this);
	    
	    //Add classes
	    for (Class c: sClasses) {
	    		sClassModel.addElement(c.getName());
	    }
	    
	    //Main JPanel
	    JPanel panel = new JPanel();
	    panel.setLayout(new GridLayout(1,3,1,1));
	    panel.setBorder(compound);

	    //Left JPanel
	    JPanel leftPanel = new JPanel();
	    leftPanel.setLayout(new BorderLayout());
	    leftPanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    leftPanel.add(new JScrollPane(classList));
	    
	    //Middle JPanel
	    JPanel middlePanel = new JPanel();
	    middlePanel.setLayout(new BorderLayout());
	    middlePanel.setBorder(BorderFactory.createEmptyBorder(1, 1, 1, 1));
	    middlePanel.add(new JScrollPane(studyList));
	    
	    //Right JPanel
	    JPanel rightPanel = new JPanel();
	    rightPanel.setLayout(new GridLayout(3,1,1,1));
	    rightPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 50, 10));

	    //Student: "Select" JButton
	    JButton selectB = new JButton("Select");
	    selectB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = classList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          sSelectedClass = sClasses.get(index);
	          if (index >= 0 && isClassSelected == false) {
	        	  	sFlashCards = sSelectedClass.getFlashCards();
	        	  	addFlashCards(sFlashCards, sStudyModel);
	        	  	sDeleteStudyWindow.revalidate();
	        	  	isClassSelected = true;
	        	  	previousClass = sSelectedClass;
	          }
	          else if (index >= 0 && isClassSelected == true) {
	        	  	if (index >= 0 && previousClass != sSelectedClass) {
	        	  		sStudyModel.clear();
		        	  	sFlashCards = sSelectedClass.getFlashCards();
		        	  	addFlashCards(sFlashCards, sStudyModel);
		        	  	sDeleteStudyWindow.revalidate();
		        	  	previousClass = sSelectedClass;
	        	  	}
	          }
	        }
	      });
	    rightPanel.add(selectB);
	    
	    //Student: "Delete" JButton
	    JButton deleteB = new JButton("Delete");
	    deleteB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent event) {
	          ListSelectionModel selmodel = studyList.getSelectionModel();
	          int index = selmodel.getMinSelectionIndex();
	          if (index >= 0) {
	        	  	FlashCard selectedFlashCard = sFlashCards.get(index);
	        	  	sSelectedClass.removeFlashCard(selectedFlashCard, index);
	        	  	sStudyModel.remove(index);
	        	  	sDeleteStudyWindow.revalidate();
	          }
	        }
	      });
	    rightPanel.add(deleteB);
	    
	    //Student: "Exit" JButton
	    JButton exitB = new JButton("Exit");
	    exitB.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	isClassSelected = false;
	        	sDeleteStudyWindow.dispose();
	        	sPortalWindow.dispose();
	        	studentPortalScreen();
	        }
	    	});
	    rightPanel.add(exitB);

	    //Add components to main panel
	    panel.add(leftPanel);
	    panel.add(middlePanel);
	    panel.add(rightPanel);

	    //Display the window
	    sDeleteStudyWindow.add(panel);
	    sDeleteStudyWindow.setVisible(true);
	}
	
	/**
	 * The changeCharacter method allows the user (Student) to select and change their character,
	 * 		to a character available in their personal bank of characters. 
	 */
	
	@SuppressWarnings("rawtypes")
	private void changeCharacter() {
		
		//Initiate Window
		changeCharacterWindow = new JFrame("Update Character");
		changeCharacterWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		changeCharacterWindow.setLayout(new BorderLayout());
		changeCharacterWindow.setSize(500, 400);
		changeCharacterWindow.setLocationRelativeTo(null);
		studentCharacters = student.getCharacters();
		JSplitPane splitPane;
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		
		//Character Names JList
		characterModel = new DefaultListModel<>();
		TitledBorder characterBorder = BorderFactory.createTitledBorder("Character List");
		JList characterList = new JList<>(characterModel);
		characterList.setBorder(characterBorder);
		characterList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		characterList.addMouseListener(this);
		
		for (Character c: studentCharacters) {
    			characterModel.addElement(c.getCharacterName());
		}
        
		//Character Image JLabel
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
         
        JScrollPane listScrollPane = new JScrollPane(characterList);
        JScrollPane pictureScrollPane = new JScrollPane(picture);
        
        //Create a split pane with the two scroll panes in it.
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                                   listScrollPane, pictureScrollPane);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerLocation(150);
        
        
        JPanel buttonPanel = new JPanel();
        Dimension dimension = new Dimension(500, 100);
        buttonPanel.setPreferredSize(dimension);
        buttonPanel.setMinimumSize(dimension);
        buttonPanel.setLayout(new GridLayout(1,2,5,5));
        buttonPanel.setBorder(compound);
        
        //Student: "Update" JButton
      	JButton updateB = new JButton("Update");
      	updateB.setBounds(145, 25, 100, 50);
      	updateB.setBorder(compound);
      	updateB.setOpaque(true);
      	updateB.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			ListSelectionModel selmodel = characterList.getSelectionModel();
      	        int index = selmodel.getMinSelectionIndex();
      	        Character selectedCharacter = studentCharacters.get(index);
      	        student.setCharacter(selectedCharacter);
      		}
     	});
      	buttonPanel.add(updateB);
      		
      	//Student: "Exit" JButton
      	JButton exitB = new JButton("Exit");
      	exitB.setBounds(255, 25, 100, 50);
      	exitB.setSize(100, 50);
      	exitB.setBorder(compound);
      	exitB.setOpaque(true);
      	exitB.addActionListener(new ActionListener() {
      		public void actionPerformed(ActionEvent e) {
      			changeC = false;
      			changeCharacterWindow.dispose();
      			sPortalWindow.dispose();
      	        studentPortalScreen();
      		}
      	});
      	buttonPanel.add(exitB);
 
        //Provide minimum sizes for the two components in the split pane
        Dimension minimumSize = new Dimension(100, 50);
        listScrollPane.setMinimumSize(minimumSize);
        pictureScrollPane.setMinimumSize(minimumSize);
 
        //Provide a preferred size for the split pane
        splitPane.setPreferredSize(new Dimension(500, 300));
        updateLabel(student.getCurrentCharacter().getCharacterName());
		
	    //Display the window
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.add(splitPane, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.PAGE_END);
        changeCharacterWindow.add(mainPanel);
        changeCharacterWindow.pack();
        changeCharacterWindow.setVisible(true);
	}
	
	/**
	 * The runGame method generates an instance of the game selected by the user. 
	 */
	
	private void runGame() {
		
		//Initialize Window
		Dimension gameSize = new Dimension(boardWidth, boardHeight);
		gameWindow = new JFrame(gameName);
		gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameWindow.setSize(gameSize);
		gameWindow.setResizable(false);
		gameWindow.setVisible(true);
		gameWindow.setSize(gameSize);
		gameWindow.setMinimumSize(gameSize);
		gameWindow.setMaximumSize(gameSize);
		gameWindow.setPreferredSize(gameSize);
		gameWindow.setLocationRelativeTo(null);
		gameWindow.add(new Board());
		
		//Display the window
		gameWindow.pack();
		gameWindow.setVisible(true);
		
	}
	
	
	/**
	 * The updateLabel method updates the image (Character) displayed while the user (Student) selects a character name
	 * 		in the JList. 
	 */
	@SuppressWarnings("unused")
	protected void updateLabel (String name) {
        Image image = new ImageIcon("Resource/" + name + ".png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon icon = new ImageIcon(image);
        picture.setIcon(icon);
        if  (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }
	
	private void healthPanel() {
		
		
		healthPanel.setBounds(260, 500, 200, 30);
		healthPanel.setBackground(Color.CYAN);
		healthPanel.setBorder(BorderFactory.createLoweredBevelBorder());
		healthPanel.setLayout(new GridLayout(1, 10, -5, -5));
		con.add(healthPanel);
		
		
		for (int i = 0; i < 10; i++) {
			JTextField j = new JTextField();
			j.setBackground(Color.green);
			healthBar.add(j);
		}
		
		for (int i = 0; i < healthBar.size(); i++) {
			healthPanel.add(healthBar.get(i));
		}
		
		mainPanel.setBounds(10, 10, 700, 475);
		mainPanel.setBackground(Color.PINK);
		
		ImageIcon s3 = new ImageIcon("Resource/water.png");
		Image waterBackground = s3.getImage().getScaledInstance(600, 275, Image.SCALE_DEFAULT);
			
		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("Resource/waterb.png"));
		mainPanel.add(background);
		background.setBounds(10, 10, 680, 455);
		

		con.add(mainPanel);
		
		
		
		buttonPanel.setBounds(350,400,200,30);
		buttonPanel.setBackground(Color.black);
		
		con.add(buttonPanel);
		
		JButton jB = new JButton();
		JButton jB2 = new JButton();
		jB.setText("Damage!");
		jB2.setText("Health!");
		jB2.addActionListener(dH);
		jB.setFocusPainted(false);
		jB.addActionListener(dH);
		buttonPanel.add(jB);
		buttonPanel.add(jB2);
		
		powerPanel();
		livesPanel();
		timePanel();
		weaponPanel();
		menuPanel();
		
	}
	
	public void powerPanel() {
		
		powerPanel.setBounds(190, 490, 50, 50);
		powerPanel.setBackground(Color.YELLOW);
		con.add(powerPanel);
		
	}
	
	public void livesPanel() {
		
		livesPanel.setBounds(50, 495, 120, 40);
		livesPanel.setBackground(Color.ORANGE);
		livesPanel.setLayout(new GridLayout(1, 3, -5, -5));
		con.add(livesPanel);
		
		JLabel j1 = new JLabel(new ImageIcon("Resource/apple.png"));
		j1.setBackground(Color.MAGENTA);
		JLabel j2 = new JLabel(new ImageIcon("Resource/apple.png"));
		j2.setBackground(Color.MAGENTA);
		JLabel j3 = new JLabel(new ImageIcon("Resource/apple.png"));
		j3.setBackground(Color.MAGENTA);
		livesPanel.add(j1);
		livesPanel.add(j2);
		livesPanel.add(j3);
		
	}
	
	public void weaponPanel() {
		
		weaponPanel.setBounds(480, 490, 50, 50);
		weaponPanel.setBackground(Color.RED);
		con.add(weaponPanel);
	}
	
	public void timePanel() {
		
		timePanel.setBounds(550, 495, 120, 40);
		timePanel.setBackground(Color.cyan);
		con.add(timePanel);
		
	}
	
	public void menuPanel() {
		
		menuPanel.setBounds(700, 350, 175, 200);
		menuPanel.setBackground(Color.black);
		menuPanel.setLayout(new GridLayout(3, 1, 0, 0));
		con.add(menuPanel);
		
		
		ImageIcon s3 = new ImageIcon("Resource/PNG_simple_1.png");
		Image button = s3.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
	
		
		JButton jb1 = new JButton((new ImageIcon("Resource/PNG_simple_1.png")));
		jb1.setBorder(BorderFactory.createLoweredBevelBorder());
		jb1.setLayout(new FlowLayout());
		jb1.setBounds(0, 30, 175, 75);
		
		JLabel jl1 = new JLabel();
		jl1.setText("PAUSE");
		jl1.setFont(new Font("Arial Black", Font.BOLD, 16));
		jl1.setForeground(new Color(51, 153, 255));
		jb1.add(jl1, BorderLayout.CENTER);
		menuPanel.add(jb1);
		
		JButton jb2 = new JButton((new ImageIcon("Resource/PNG_simple_1.png")));
		jb2.setBorder(BorderFactory.createLoweredBevelBorder());
		jb2.setLayout(new FlowLayout());
		jb2.setBounds(0, 30, 175, 75);
		
		JLabel jl2 = new JLabel();
		jl2.setText("RESTART");
		jl2.setFont(new Font("Arial Black", Font.BOLD, 16));
		jl2.setForeground(new Color(51, 153, 255));
		jb2.add(jl2, BorderLayout.CENTER);
		menuPanel.add(jb2);
		
		
		
		JButton jb3 = new JButton((new ImageIcon("Resource/PNG_simple_1.png")));
		jb3.setBorder(BorderFactory.createLoweredBevelBorder());
		jb3.setLayout(new FlowLayout());
		jb3.setBounds(0, 30, 175, 75);
		
		JLabel jl3 = new JLabel();
		jl3.setText("EXIT");
		jl3.setFont(new Font("Arial Black", Font.BOLD, 16));
		jl3.setForeground(new Color(51, 153, 255));
		jb3.add(jl3, BorderLayout.CENTER);
		
		
		
		menuPanel.add(jb3);
		
		
	}
	
	public void damageReceived() {
		hp = hp - 1;
		if (hp >= 0) {
			for (int i = hp; hp < healthBar.size(); i++) {
				healthBar.get(i).setBackground(Color.red);
			}
		}
	}
	
	public void healthReceived() {
		hp = hp + 1;
		if (hp <= 10) {
			for (int i = 0; i < hp; i++) {
				healthBar.get(i).setBackground(Color.green);
			}
		}
	}
	
	public class damageHandler implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			if (arg0.getActionCommand().equals("Damage!")) {
				damageReceived();
				return;
			}
			else if (arg0.getActionCommand().equals("Health!")) {
				healthReceived();
				return;
			}
		}
	}
	
	/**
     * Checks the passed-in array against the correct password.
     * After this method returns, you should invoke eraseArray
     * on the passed-in array.
	 * @param input
	 * @param correctPassword
	 * @return
	 */
	private static boolean isPasswordCorrect(char[] input, char[] correctPassword) {
	    boolean isCorrect = true;
	    
	    if (input.length != correctPassword.length) {
	        isCorrect = false;
	    } else {
	        isCorrect = Arrays.equals (input, correctPassword);
	    }

	    // Zero out the password.
	    Arrays.fill(correctPassword,'0');

	    return isCorrect;
	}
	
	// Must be called from the event dispatch thread.
    protected void resetFocus() {
        passwordPF.requestFocusInWindow();
    }
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		// LoginScreen
		// Process username and password
		if (arg0.getActionCommand().equals("LOGIN")) {
			
			// Verify username is valid and acquire associated password for account
			String username = usernameTF.getText();
			
			// Database: Check for username
			if (username == "jmn7080") {
				// Get password from database
			//	correctPassword = getAssociatedPassword(username);
				
				// Retrieve password input by user
				char[] input = passwordPF.getPassword();
				char[] correctPassword = { 's', 'a', 'v', 'e', 'm', 'e'};
				
				if (isPasswordCorrect(input, correctPassword)) {
					JOptionPane.showMessageDialog(newUserWindow,"Success! You typed the right password.");
					loginWindow.dispose();
					createTempData();
					teacherPortalScreen();
					
		        } else {
		        			JOptionPane.showMessageDialog(newUserWindow,"Invalid password. Try again.",
		                    "Error Message", JOptionPane.ERROR_MESSAGE);
		                usernameTF.setText("");
		                passwordPF.setText("");
		                
		                // Zero out the possible password, for security.
		                Arrays.fill(input, '0');
		        }
				
				// Zero out the possible password, for security.
	            Arrays.fill(input, '0');
	            
	            passwordPF.selectAll();
	            resetFocus();
			} 
		}
	}	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getSource() == usernameTF) {
				usernameTF.setOpaque(true);
		}
		
		if (arg0.getSource() == passwordPF) {
			passwordPF.setOpaque(true);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (changeC) {
			JList list = (JList)e.getSource();
			characterList = list;
			ListSelectionModel selmodel = characterList.getSelectionModel();
			int index = selmodel.getMinSelectionIndex();
			Character selectedCharacter = studentCharacters.get(index);
			updateLabel(selectedCharacter.getCharacterName());
    			changeCharacterWindow.revalidate();
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent arg0) {
		
	}
}

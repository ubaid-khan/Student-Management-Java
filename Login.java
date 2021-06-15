import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener{
	
	public static int WIDTH = 500;
	public static int HEIGHT = 500;
	
	JTextField usernameTextField;
	JPasswordField passwordTextField;
	JButton loginButton;
	
	//set up login GUI
	Login(){
		initializeGUI();
		
	}
	
	public void initializeGUI() {
		
		//JFrame
		setTitle("Log in");
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null);//to center the window
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		//Login components below
		
		JLabel systemTitleLabel = new JLabel("Student Management System");
		systemTitleLabel.setBounds(150, 50, 200, 30);
		add(systemTitleLabel);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(50, 100, 200, 30);
		add(usernameLabel);
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(150,100,  200,30); // x y width height
		add(usernameTextField);
		
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(50, 150, 200, 30); // increase y1 for making it below the other
		add(passwordLabel);
		
		
		passwordTextField = new JPasswordField();
		passwordTextField.setBounds(150,150,200,30);
		add(passwordTextField);
		
		
		loginButton = new JButton("Get me in");
		loginButton.setBounds(150, 200, 100, 30);
		loginButton.addActionListener(this);
		add(loginButton);
		
		setVisible(true);
	}
	
	public void validateUser() {
		if(usernameTextField.getText().toString().equals("ubaid") && passwordTextField.getText().toString().equals("khan")){
			//present next screen
			new Dashboard();
			dispose();
		}
		else {
		JOptionPane.showMessageDialog(this, "Invalid credentials");
		//return false;
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		validateUser();
		
	}
	
}
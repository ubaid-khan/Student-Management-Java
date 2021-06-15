import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class InsertStudent extends JFrame implements ActionListener{
	
	JTextField studentNameTextField,studentBranchTextField,studentMobileTextField,studentGenderTextField;
	JButton insertButton,backToHome;
	
	
	public InsertStudent() {
		// TODO Auto-generated constructor stub
		initializeGUI();
	}
	public void initializeGUI() {
		
		//set frame attributes
		setTitle("Insert Record");
		setSize(Login.WIDTH, Login.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		
		JLabel insertStudentTitle = new JLabel("Insert a new student");
		insertStudentTitle.setBounds(150, 50, 200, 30);
		add(insertStudentTitle);
		
		
		JLabel studentNameLabel = new JLabel("Student Name :");
		studentNameLabel.setBounds(50, 100, 200, 30);
		add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setBounds(150, 100,200,30);
		add(studentNameTextField);
		
		JLabel studentBranchLabel = new JLabel("Branch :");
		studentBranchLabel.setBounds(50, 150, 200, 30);
		add(studentBranchLabel);
		
		studentBranchTextField = new JTextField();
		studentBranchTextField.setBounds(150, 150,200,30);
		add(studentBranchTextField);
		
		
		JLabel studentMobileLabel = new JLabel("Mobile :");
		studentMobileLabel.setBounds(50, 200, 200, 30);
		add(studentMobileLabel);
		
		studentMobileTextField = new JTextField();
		studentMobileTextField.setBounds(150, 200,200,30);
		add(studentMobileTextField);
		
		JLabel studentGenderLabel = new JLabel("Gender :");
		studentGenderLabel.setBounds(50, 250, 200, 30);
		add(studentGenderLabel);
		
		studentGenderTextField = new JTextField();
		studentGenderTextField.setBounds(150, 250,200,30);
		add(studentGenderTextField);
		
		
		
		insertButton = new JButton("Insert into database");
		insertButton.setBounds(150, 300, 200, 30);
		insertButton.addActionListener(this);
		add(insertButton);
		
		backToHome = new JButton("Dashboard");
		backToHome.setBounds(150, 350, 200, 30);
		backToHome.addActionListener(this);
		add(backToHome);
		
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//insert the student into database
		if(e.getSource() == insertButton) {
		try {
		Connection connection = DatabaseProvider.initiateConnection();
		String insertQuery = "INSERT INTO students (student_name, branch, mobile, gender) VALUES (?,?,?,?);";
		PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
		
		preparedStatement.setString(1, studentNameTextField.getText().toString());
		preparedStatement.setString(2, studentBranchTextField.getText().toString());
		preparedStatement.setString(3, studentMobileTextField.getText().toString());
		preparedStatement.setString(4, studentGenderTextField.getText().toString());
		
		int rowsInserted = preparedStatement.executeUpdate();
		
		if(rowsInserted > 0) {
			System.out.println("student inserted successfully");
		}
		else {
			System.out.println("unable to insert given student data !");
		}
		
		}catch(SQLException se) {
			System.out.println("SQLException" + se.getCause());
			se.printStackTrace();
		}
	 }
		
		if(e.getSource() == backToHome) {
			dispose();
			new Dashboard();
		}
   }



}

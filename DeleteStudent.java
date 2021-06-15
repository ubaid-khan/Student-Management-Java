import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class DeleteStudent extends JFrame implements ActionListener{
	JTextField studentNameTextField;
	JButton deleteButton;
	public DeleteStudent() {
		// TODO Auto-generated constructor stub
		
		setTitle("Delete Record");
		setSize(Login.WIDTH, Login.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		
		JLabel viewAllStudentsTitle = new JLabel("Delete a student");
		viewAllStudentsTitle.setBounds(150, 10, 200, 30);
		add(viewAllStudentsTitle);
		
		JLabel studentNameLabel = new JLabel("Enter the student name to delete ?");
		studentNameLabel.setBounds(50, 50, 200, 30);
		add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setBounds(250, 50, 200, 30);
		add(studentNameTextField);
		
		deleteButton  = new JButton("delete this student");
		deleteButton.setBounds(250, 100, 200, 30);
		deleteButton.addActionListener(this);
		add(deleteButton);
		
	
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String deleteQuery = "DELETE FROM students WHERE student_name = '" + studentNameTextField.getText().toString() + "';";
			Connection connection = DatabaseProvider.initiateConnection();
			PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
			int row = deleteStatement.executeUpdate();
			JOptionPane.showMessageDialog(this, "Record deleted successfully");
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}

}

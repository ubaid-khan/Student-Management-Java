import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Dashboard extends JFrame implements ActionListener{
	
	private JButton addStudentButton, searchStudentButton, viewAllStudentsButton, deleteStudentButton;
	
	Dashboard(){
		initializeGUI();
		setClickListener();
	}
	
	public void initializeGUI() {
		setTitle("Dashboard");
		setSize(Login.WIDTH, Login.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel dashboardTitle = new JLabel("Choose an action");
		dashboardTitle.setBounds(50, 50, 200, 30);
		add(dashboardTitle);
		
		addStudentButton = new JButton("add new student");
		addStudentButton.setBounds(120, 100, 200,30);
		add(addStudentButton);
		
		searchStudentButton = new JButton("search student");
		searchStudentButton.setBounds(120, 150, 200,30);
		add(searchStudentButton);
		
		viewAllStudentsButton = new JButton("view all students");
		viewAllStudentsButton.setBounds(120, 200, 200,30);
		add(viewAllStudentsButton);
		
		
		deleteStudentButton = new JButton("remove student");
		deleteStudentButton.setBounds(120, 250, 200,30);
		add(deleteStudentButton);
		
		
		setVisible(true);	
	}
	
	private void setClickListener() {
		addStudentButton.addActionListener(this);
		searchStudentButton.addActionListener(this);
		viewAllStudentsButton.addActionListener(this);
		deleteStudentButton.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == addStudentButton) {
			dispose();
			new InsertStudent();
		}
		
		if(e.getSource() == searchStudentButton) {
			dispose();
			new SearchStudent();
		}
		
		if(e.getSource() == viewAllStudentsButton) {
			dispose();
			new ViewAllStudents();
		}
		
		if(e.getSource() == deleteStudentButton) {
			dispose();
			new DeleteStudent();
		}
		
	}
	
}

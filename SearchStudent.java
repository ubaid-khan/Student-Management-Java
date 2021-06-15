import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class SearchStudent extends JFrame implements ActionListener{
	JTextField studentNameTextField;
	JButton getStudentButton,backToHomeButton;
	JTable jtable;
	JScrollPane jscrollpane;
	DefaultTableModel tableModel;
	
	public SearchStudent() {
		// TODO Auto-generated constructor stub
		setUpJTable();
		initializeGUI();
	}
	private void initializeGUI() {
		//set frame attributes
		setTitle("Update Record");
		setSize(Login.WIDTH, Login.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		JLabel searchStudentsTitle = new JLabel("Search an individual student");
		searchStudentsTitle.setBounds(150, 10, 200, 30);
		add(searchStudentsTitle);
		
		
		JLabel studentNameLabel = new JLabel("Enter the student name:");
		studentNameLabel.setBounds(10,50, 200, 30);
		add(studentNameLabel);
		
		studentNameTextField = new JTextField();
		studentNameTextField.setBounds(150, 50, 200, 30);
		add(studentNameTextField);
		
		getStudentButton = new JButton("search");
		getStudentButton.setBounds(150, 100, 100, 30);
		getStudentButton.addActionListener(this);
		add(getStudentButton);
		
		backToHomeButton = new JButton("Dashboard");
		backToHomeButton.setBounds(250, 100, 100, 30);
		backToHomeButton.addActionListener(this);
		add(backToHomeButton);
		
		
		//setVisible(true);
		add(jscrollpane);
		setVisible(true);
	}
	private void setUpJTable() {
		//setting up jtable
		String[] columnNames = {"Student Name", "Branch" , "Mobile" , "Gender"};
		
		tableModel = new DefaultTableModel();
		tableModel.setColumnIdentifiers(columnNames);
		
		jtable = new JTable();
		jtable.setModel(tableModel);
		jtable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jtable.setFillsViewportHeight(true);
		
		jscrollpane = new JScrollPane(jtable);
		jscrollpane.setBounds(60, 140, 400, 200);
		jscrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jscrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == getStudentButton) {
		Connection connection = null;
		try {
			connection = DatabaseProvider.initiateConnection();
			String searchQuery = "SELECT * FROM students WHERE student_name = '" + studentNameTextField.getText().toString() + "';";
			
			PreparedStatement searchQueryPreparedStatement = connection.prepareStatement(searchQuery);
			//searchQueryPreparedStatement.setString(1, studentNameTextField.getText().toString());
			ResultSet searchResults = searchQueryPreparedStatement.executeQuery();
			
			System.out.println("Record found with current data:");
			System.out.println("Name\tBranch\tMobile\tGender");
		
			//display current data
			while(searchResults.next()) {
				String name = searchResults.getString("student_name");
				String branch = searchResults.getString("branch");
				String mobile = searchResults.getString("mobile");
				String gender = searchResults.getString("gender");
				
				tableModel.addRow(new Object[] {name,branch,mobile,gender});
				
				System.out.println(name + "\t" + branch + "\t" + mobile + "\t" + gender);
			}	
			
		}catch(SQLException se) {
			se.printStackTrace();
		   }
		}
		
		if(e.getSource() == backToHomeButton) {
			dispose();
			new Dashboard();
		}
		
	}
	

}

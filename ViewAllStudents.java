import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewAllStudents extends JFrame implements ActionListener{
	
	DefaultTableModel tableModel;
	JTable jtable;
	JScrollPane jscrollpane;
	JButton viewAllStudentsButton,backToHomeButton;
	
	
	public ViewAllStudents() {
		// TODO Auto-generated constructor stub
		setUpJTable();
		initializeGUI();

	}
	
	private void initializeGUI() {
		setTitle("Show All Records");
		setSize(Login.WIDTH, Login.HEIGHT);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		
		JLabel viewAllStudentsTitle = new JLabel("Here's the list of all the students");
		viewAllStudentsTitle.setBounds(150, 10, 200, 30);
		add(viewAllStudentsTitle);
		
		viewAllStudentsButton = new JButton("view all students");
		viewAllStudentsButton.setBounds(150, 100, 100, 30);
		viewAllStudentsButton.addActionListener(this);
		add(viewAllStudentsButton);		
		
		backToHomeButton = new JButton("Dashboard");
		backToHomeButton.setBounds(250, 100, 100, 30);
		backToHomeButton.addActionListener(this);
		add(backToHomeButton);
		
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
		if(e.getSource() == viewAllStudentsButton) {
		Connection connection = null;
		try {
			connection = DatabaseProvider.initiateConnection();
			String viewAllQuery = "SELECT * FROM students;";
			
			PreparedStatement searchQueryPreparedStatement = connection.prepareStatement(viewAllQuery);
			ResultSet searchResults = searchQueryPreparedStatement.executeQuery();
		
			//display current data
			while(searchResults.next()) {
				String name = searchResults.getString("student_name");
				String branch = searchResults.getString("branch");
				String mobile = searchResults.getString("mobile");
				String gender = searchResults.getString("gender");
				
				tableModel.addRow(new Object[] {name,branch,mobile,gender});
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

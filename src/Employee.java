import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.text.MessageFormat;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Employee {
	// Used to build the GUI
	private JFrame frame;
	private JTextField jtxtfname;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNiNumber;
	private JTextField jtxtlname;
	private JLabel lblFirstname;
	private JTextField jtxtemail;
	private JLabel lblSurname;
	private JLabel lblGender;
	private JLabel lblDob;
	private JTextField jtxtsalary;
	private JLabel lblAge;
	private JTextField jtxtemployeeid;
	private JLabel lblSalray;
	private JTextField jtxtphonenumber;
	private JButton btnPrint;
	private JButton btnReset;
	private JButton btnExit;
	private JLabel lblNewLabel;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	DefaultTableModel model = new DefaultTableModel();
	private JLabel lblNewLabel_1;
	private JTextField jtxtdepartment;
	private JTextField jtxtposition;
	
	private JComboBox jtxtsmonth;
	private JComboBox jtxtsday;
	private JComboBox jtxtsyear;
	
	private JComboBox jtxtemonth;
	private JComboBox jtxteday;
	private JComboBox jtxteyear;
	
	private JRadioButton currButton;
	
	private String dates[]
	        = { "", "1", "2", "3", "4", "5",
	            "6", "7", "8", "9", "10",
	            "11", "12", "13", "14", "15",
	            "16", "17", "18", "19", "20",
	            "21", "22", "23", "24", "25",
	            "26", "27", "28", "29", "30",
	            "31" };
    private String months[]
        = { "", "01", "02", "03", "04",
            "04", "05", "06", "07",
            "08", "09", "10", "11", "12" };
    private String years[]
        = { "", "1998",
            "1999", "2000", "2001", "2002",
            "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010",
            "2011", "2012", "2013", "2014",
            "2015", "2016", "2017", "2018",
            "2019", "2020", "2021", "2022" };
    private JComboBox jtxtbmonth;
    private JComboBox jtxtbday;
    private JComboBox jtxtbyear;
    private JLabel lblEx;
	
    
    // Functions used for input validation
    public boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if(!Character.isLetter(c)) {
                return false;
            }
        }

        return true;
    }
    
    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean patternMatches(String emailAddress) {
        return Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" 
                + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")
          .matcher(emailAddress)
          .matches();
    }
	    
	// This class creates the login page 
	static class CreateLoginForm extends JFrame implements ActionListener
	{
	    // create username, password, and submit fields
	    JButton b1;
	    JPanel newPanel;
	    JLabel userLabel, passLabel;
	    final JTextField  textField1, textField2;

	    //calling constructor
	    CreateLoginForm()
	    {

	        //create label for username
	        userLabel = new JLabel();
	        userLabel.setText("Username");      //set label value for textField1

	        //create text field to get username from the user
	        textField1 = new JTextField(15);    //set length of the text

	        //create label for password
	        passLabel = new JLabel();
	        passLabel.setText("Password");      //set label value for textField2

	        //create text field to get password from the user
	        textField2 = new JPasswordField(15);    //set length for the password

	        //create submit button
	        b1 = new JButton("SUBMIT"); //set label to button

	        //create panel to put form elements
	        newPanel = new JPanel(new GridLayout(3, 1));
	        newPanel.add(userLabel);    //set username label to panel
	        newPanel.add(textField1);   //set text field to panel
	        newPanel.add(passLabel);    //set password label to panel
	        newPanel.add(textField2);   //set text field to panel
	        newPanel.add(b1);           //set button to panel

	        //set border to panel
	        add(newPanel, BorderLayout.CENTER);

	        //perform action on button click
	        b1.addActionListener(this);     //add action listener to button
	        setTitle("LOGIN FORM");         //set title to the login form
	    }

	    // button checks if username and password are correct
	    public void actionPerformed(ActionEvent ae)     //pass action listener as a parameter
	    {
	        String userValue = textField1.getText();        //get user entered username from the textField1
	        String passValue = textField2.getText();        //get user entered password from the textField2

	        //check if username and passwords match
	        if (userValue.equals("username") && passValue.equals("password")) {  
	            // if it matches it sends the user to the interface
	        	JOptionPane.showMessageDialog(null, "Login Successful");
	        	EventQueue.invokeLater(new Runnable() {
	    			public void run() {
	    				try {		
	    					Employee window = new Employee();
	    					window.frame.setVisible(true);
	    				} catch (Exception e) {
	    					e.printStackTrace();
	    				}
	    			}
	    		});
	        	
	        	
	        	
	        }
	        else{
	            //show error message
	        	JOptionPane.showMessageDialog(null, "Please enter valid username and password");
	        }
	    }
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Opens the login form
					Employee.CreateLoginForm form = new Employee.CreateLoginForm();
					form.setSize(900,200);  //set size of the frame
			        form.setVisible(true);  //make form visible to the user			
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/*
	 loadList updates the table shown to the user based on the records currently stored in the sqlite database.
	 This function is called at the start of the program and after every add, edit, and delete command 
	 so that the table is always showing the most up to date database. 
	 */
	public void loadList() {
		try
		{
			// connects to the database and creates a command to select the first record
			conn = EmployeeData.ConnectDB();
			String query = "select * from Employee";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			// resets the current table
			while(model.getRowCount() > 0)
			{
			    model.removeRow(0);
			}
			
			DefaultListModel DLM = new DefaultListModel();
			// iterates through each record and adds it to the table GUI shown to the user
			while(rs.next()) {
				DLM.addElement(rs.getString("EmpID"));
				model.addRow(new Object[] {
						rs.getString("Firstname"),
						rs.getString("Lastname"),
						rs.getString("Email"),
						rs.getString("Start"),
						rs.getString("End"),
						rs.getString("Salary"),
						rs.getString("EmpID"),
						rs.getString("PhoneNum"),
						rs.getString("Department"),
						rs.getString("Position"),
						rs.getString("DOB"),
				});
			}
			// closes all connections
			pst.close();
			conn.close();
			if (rs != null) 
				rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Employee() {
		// displays connection made when successfully logging in
		JOptionPane.showMessageDialog(null, "Connection Made");
		initialize();
		Object col[] = {"Firstname", "Lastname", "Email", "Start", "End", "Salary", "EmpID", "PhoneNum", "Department", "Position", "DOB"};
		model.setColumnIdentifiers(col);
		// loads current contents of the database
		loadList();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 1400, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		jtxtfname = new JTextField();
		jtxtfname.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtfname.setBounds(272, 59, 209, 28);
		frame.getContentPane().add(jtxtfname);
		jtxtfname.setColumns(10);
		
		
		
		// Add button 
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create sql command to add new record 
				String sql = "INSERT INTO employee(Firstname, Lastname, Email, Start, End, Salary, EmpID, PhoneNum, Department, Position, DOB)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				
				try
				{
					// connects to database
					conn = EmployeeData.ConnectDB();
					
					int flag = 0;
					
					String firstname = jtxtfname.getText();
					String lastname = jtxtlname.getText();
					String email = jtxtemail.getText();
					String start = (String)jtxtsmonth.getSelectedItem() + "/" + (String)jtxtsday.getSelectedItem() + "/" + (String)jtxtsyear.getSelectedItem();
					String end = (String)jtxtemonth.getSelectedItem() + "/" + (String)jtxteday.getSelectedItem() + "/" + (String)jtxteyear.getSelectedItem();
					String salary = jtxtsalary.getText();
					String ID = jtxtemployeeid.getText();
					String phone = jtxtphonenumber.getText();
					String department = jtxtdepartment.getText();
					String position = jtxtposition.getText();
					String DOB = (String)jtxtbmonth.getSelectedItem() + "/" + (String)jtxtbday.getSelectedItem() + "/" + (String)jtxtbyear.getSelectedItem();
					
								
					// input validation 
					if(jtxtfname.getText().isEmpty() || jtxtlname.getText().isEmpty() || jtxtemail.getText().isEmpty() || jtxtsalary.getText().isEmpty() ||jtxtemployeeid.getText().isEmpty() || jtxtphonenumber.getText().isEmpty() || jtxtdepartment.getText().isEmpty() || jtxtposition.getText().isEmpty()){
						flag = 1;
					}
					else if(!isAlpha(firstname) || !isAlpha(lastname) || !isAlpha(department) || !isAlpha(position)) {
						flag = 1;
					}
					else if(!patternMatches(email)) {
						flag = 1;
					}
					else if((String)jtxtsmonth.getSelectedItem() == "" || (String)jtxtsday.getSelectedItem() == "" || (String)jtxtsyear.getSelectedItem() == "") {
						flag = 1;
					}
					else if((String)jtxtemonth.getSelectedItem() == "" || (String)jtxteday.getSelectedItem() == "" || (String)jtxteyear.getSelectedItem() == "") {
						flag = 1;
						if (currButton.isSelected()) {
							end = "CURR";
							flag = 0;
						}
					}
					else if(!isNumeric(salary)) {
						flag = 1;
					}
					else if(!isNumeric(ID) || ID.length() != 3) {
						flag = 1;
					}
					else if(!isNumeric(phone) || phone.length() != 10) {
						flag = 1;
					}
					else if((String)jtxtbmonth.getSelectedItem() == "" || (String)jtxtbday.getSelectedItem() == "" || (String)jtxtbyear.getSelectedItem() == "") {
						flag = 1;
					}
					
					
					// if input is invalid it outputs incorrect format
					if (flag == 1) {
						JOptionPane.showMessageDialog(null, "Incorrect Format");
						return;
					}
					// used if current button is selected
					if (currButton.isSelected()) {
						end = "CURR";
					}
					
					// accesses database and adds new record 
					pst = conn.prepareStatement(sql);
					pst.setString(1,  jtxtfname.getText());
					pst.setString(2,  jtxtlname.getText());
					pst.setString(3,  jtxtemail.getText());
					pst.setString(4, start);
					pst.setString(5,  end);
					pst.setString(6,  jtxtsalary.getText());
					pst.setString(7,  jtxtemployeeid.getText());
					pst.setString(8,  jtxtphonenumber.getText());
					pst.setString(9,  jtxtdepartment.getText());
					pst.setString(10,  jtxtposition.getText());
					pst.setString(11,  DOB);
					pst.execute();
					
					pst.close();
					conn.close();
					JOptionPane.showMessageDialog(null, "Employee Record Successfully Added");
				}
				catch(Exception ev)
				{
					System.err.format(ev.getMessage());
					JOptionPane.showMessageDialog(null, "Error");
				}
				// re updates the table 
				loadList();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(272, 460, 214, 47);
		frame.getContentPane().add(btnNewButton);
		
		
		
		
		
		
		// allows the table to scroll
		scrollPane = new JScrollPane();
		scrollPane.setBounds(531, 53, 819, 639);
		frame.getContentPane().add(scrollPane);
		
		// creates table with all column headers
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Firstname", "Lastname", "Email", "Start", "End", "Salary", "EmpID", "PhoneNum","Department", "Position", "DOB"
			}
		));
		table.setFont(new Font("Tahoma", Font.BOLD, 14));
		scrollPane.setViewportView(table);
		
		
		// print button
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				try 
				{
					// accesses printer and prints contents of the table 
					table.print();
				}
				catch(java.awt.print.PrinterException ev)
				{
					System.err.format("No Printer Found", ev.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(272, 581, 214, 47);
		frame.getContentPane().add(btnPrint);
		
		
		
		
		// Reset button
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// clears every input field 
				jtxtfname.setText(null);
				jtxtlname.setText(null);
				jtxtemail.setText(null);
				jtxtsalary.setText(null);
				jtxtemployeeid.setText(null);
				jtxtphonenumber.setText(null);
				jtxtdepartment.setText(null);
				jtxtposition.setText(null);
				JOptionPane.showMessageDialog(null, "Form Data Reset");
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(272, 428, 209, 21);
		frame.getContentPane().add(btnReset);
		
		
		
		
		
		// exit button 
		btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame = new JFrame("Exit");
				if (JOptionPane.showConfirmDialog(frame, "Confirm if you want to exit", "Employee Database System", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
					System.exit(0);
				}
				
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(1160, 703, 214, 47);
		frame.getContentPane().add(btnExit);
		
		
		
		
		// delete button
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// creates sql delete command
				conn = EmployeeData.ConnectDB();
				String sql = "DELETE FROM employee WHERE EmpID = ?";
				
				
				// asks user to input Employee ID 
				String value = JOptionPane.showInputDialog(null, "Enter Employee ID to Delete Record");
				
				
				// input validation 
				if (value.length() != 3) {
					JOptionPane.showMessageDialog(null, "Incorrect Format");
					return;
				}
				
				try
				{
					// runs sql delete command 
					pst = conn.prepareStatement(sql);
					pst.setString(1, value);
					int i = pst.executeUpdate();
					// checks if given ID exists and if so deletes corresponding record
			        if (i > 0) {
			        	JOptionPane.showMessageDialog(null, "Employee Record Successfully Deleted");
			        } else {
			        	JOptionPane.showMessageDialog(null, "Employee does not exist");
			        }
			        
				}
				catch (SQLException ed) {
		            System.out.println(ed.getMessage());
		        }
				finally
				{
					try
					{
						loadList();
						pst.close();
						conn.close();
					}
					catch(SQLException es)
					{
						es.printStackTrace();
					}
				}
				
			}
		});
		btnDelete.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnDelete.setBounds(20, 581, 214, 47);
		frame.getContentPane().add(btnDelete);
		
		
		
		
		// edit button
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
	
                try
                {
                	conn = EmployeeData.ConnectDB();
    		
    				int flag = 0;
					
					String firstname = jtxtfname.getText();
					String lastname = jtxtlname.getText();
					String email = jtxtemail.getText();
					String start = (String)jtxtsmonth.getSelectedItem() + "/" + (String)jtxtsday.getSelectedItem() + "/" + (String)jtxtsyear.getSelectedItem();
					String end = (String)jtxtemonth.getSelectedItem() + "/" + (String)jtxteday.getSelectedItem() + "/" + (String)jtxteyear.getSelectedItem();
					String salary = jtxtsalary.getText();
					String ID = jtxtemployeeid.getText();
					String phone = jtxtphonenumber.getText();
					String department = jtxtdepartment.getText();
					String position = jtxtposition.getText();
					String DOB = (String)jtxtbmonth.getSelectedItem() + "/" + (String)jtxtbday.getSelectedItem() + "/" + (String)jtxtbyear.getSelectedItem();
					
								
					// input validation
					if(jtxtfname.getText().isEmpty() || jtxtlname.getText().isEmpty() || jtxtemail.getText().isEmpty() || jtxtsalary.getText().isEmpty() ||jtxtemployeeid.getText().isEmpty() || jtxtphonenumber.getText().isEmpty() || jtxtdepartment.getText().isEmpty() || jtxtposition.getText().isEmpty()){
						flag = 1;
					}
					else if(!isAlpha(firstname) || !isAlpha(lastname) || !isAlpha(department) || !isAlpha(position)) {
						flag = 1;
					}
					else if(!patternMatches(email)) {
						flag = 1;
					}
					else if((String)jtxtsmonth.getSelectedItem() == "" || (String)jtxtsday.getSelectedItem() == "" || (String)jtxtsyear.getSelectedItem() == "") {
						flag = 1;
					}
					else if((String)jtxtemonth.getSelectedItem() == "" || (String)jtxteday.getSelectedItem() == "" || (String)jtxteyear.getSelectedItem() == "") {
						flag = 1;
						if (currButton.isSelected()) {
							end = "CURR";
							flag = 0;
						}
					}
					else if(!isNumeric(salary)) {
						flag = 1;
					}
					else if(!isNumeric(ID) || ID.length() != 3) {
						flag = 1;
					}
					else if(!isNumeric(phone) || phone.length() != 10) {
						flag = 1;
					}
					else if((String)jtxtbmonth.getSelectedItem() == "" || (String)jtxtbday.getSelectedItem() == "" || (String)jtxtbyear.getSelectedItem() == "") {
						flag = 1;
					}
					
					// if input is invalid
					if (flag == 1) {
						JOptionPane.showMessageDialog(null, "Incorrect Format");
						return;
					}
					// if radio curr button is selected, updates end date field
					if (currButton.isSelected()) {
						end = "CURR";
					}
					
					// creates sql edit commmand based on given input
					String sql = "update employee set Firstname='"+ firstname +
							"' , Lastname='"+ lastname +
							"', Email='"+ email +
							"', Start='"+ start +
							"', End='"+ end +
							"', Salary='"+ salary +
							"', EmpID='"+ ID +
							"', PhoneNum='"+ phone +
							"', Department='"+ department +
							"', Position='"+ position +
							"', DOB='"+ DOB +
							"' WHERE EmpID='"+ ID +"'  ";
    				
					
                    pst = conn.prepareStatement(sql);
                    
                    // if record exists it updates it, if not it tells user employee does not exist
                    int i = pst.executeUpdate();
                    
                    if (i > 0) {
                    	JOptionPane.showMessageDialog(null, "Record Updated");
                    } else {
                    	JOptionPane.showMessageDialog(null, "Employee does not exist");
                    }
                    
                    pst.close();
                    conn.close();
                } 
                catch(Exception ev)
                {
                    System.err.format(ev.getMessage());
                    JOptionPane.showMessageDialog(null, "Error");
                } 
                loadList();
			}
		});
		editButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		editButton.setBounds(20, 460, 214, 47);
		frame.getContentPane().add(editButton);
		
		
		// generates labels and text fields
		lblNiNumber = new JLabel("Last Name");
		lblNiNumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNiNumber.setBounds(94, 98, 164, 14);
		frame.getContentPane().add(lblNiNumber);
		
		jtxtlname = new JTextField();
		jtxtlname.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtlname.setColumns(10);
		jtxtlname.setBounds(272, 91, 209, 28);
		frame.getContentPane().add(jtxtlname);
		
		lblFirstname = new JLabel("Email");
		lblFirstname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblFirstname.setBounds(94, 130, 164, 14);
		frame.getContentPane().add(lblFirstname);
		
		jtxtemail = new JTextField();
		jtxtemail.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtemail.setColumns(10);
		jtxtemail.setBounds(272, 123, 209, 28);
		frame.getContentPane().add(jtxtemail);
		
		lblSurname = new JLabel("Start Date");
		lblSurname.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSurname.setBounds(94, 162, 164, 14);
		frame.getContentPane().add(lblSurname);
		
		lblGender = new JLabel("End Date");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(94, 194, 164, 14);
		frame.getContentPane().add(lblGender);
		
		lblDob = new JLabel("Salary");
		lblDob.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDob.setBounds(94, 223, 164, 21);
		frame.getContentPane().add(lblDob);
		
		jtxtsalary = new JTextField();
		jtxtsalary.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtsalary.setColumns(10);
		jtxtsalary.setBounds(272, 219, 209, 28);
		frame.getContentPane().add(jtxtsalary);
		
		lblAge = new JLabel("Employee ID");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAge.setBounds(94, 255, 164, 21);
		frame.getContentPane().add(lblAge);
		
		jtxtemployeeid = new JTextField();
		jtxtemployeeid.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtemployeeid.setColumns(10);
		jtxtemployeeid.setBounds(272, 251, 108, 28);
		frame.getContentPane().add(jtxtemployeeid);
		
		lblSalray = new JLabel("Phone Number");
		lblSalray.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSalray.setBounds(94, 284, 164, 28);
		frame.getContentPane().add(lblSalray);
		
		jtxtphonenumber = new JTextField();
		jtxtphonenumber.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtphonenumber.setColumns(10);
		jtxtphonenumber.setBounds(272, 284, 209, 28);
		frame.getContentPane().add(jtxtphonenumber);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(94, 66, 164, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Employee Database Management System");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(388, 11, 748, 31);
		frame.getContentPane().add(lblNewLabel_1);
		

		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDepartment.setBounds(94, 316, 164, 28);
		frame.getContentPane().add(lblDepartment);
		
		JLabel lblPosition = new JLabel("Position");
		lblPosition.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPosition.setBounds(94, 352, 164, 28);
		frame.getContentPane().add(lblPosition);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblDateOfBirth.setBounds(94, 391, 164, 28);
		frame.getContentPane().add(lblDateOfBirth);
		
		jtxtdepartment = new JTextField();
		jtxtdepartment.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtdepartment.setColumns(10);
		jtxtdepartment.setBounds(272, 316, 209, 28);
		frame.getContentPane().add(jtxtdepartment);
		
		jtxtposition = new JTextField();
		jtxtposition.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtposition.setColumns(10);
		jtxtposition.setBounds(272, 352, 209, 28);
		frame.getContentPane().add(jtxtposition);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(20, 548, 516, 22);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 53, 511, 2);
		frame.getContentPane().add(separator_1);
		
		jtxtsmonth = new JComboBox(months);
		jtxtsmonth.setBounds(272, 161, 58, 22);
		frame.getContentPane().add(jtxtsmonth);
		
		jtxtsday = new JComboBox(dates);
		jtxtsday.setBounds(340, 161, 40, 22);
		frame.getContentPane().add(jtxtsday);
		
		jtxtsyear = new JComboBox(years);
		jtxtsyear.setBounds(388, 162, 58, 22);
		frame.getContentPane().add(jtxtsyear);
		
		jtxtemonth = new JComboBox(months);
		jtxtemonth.setBounds(272, 193, 58, 22);
		frame.getContentPane().add(jtxtemonth);
		
		jtxteday = new JComboBox(dates);
		jtxteday.setBounds(340, 193, 40, 22);
		frame.getContentPane().add(jtxteday);
		
		jtxteyear = new JComboBox(years);
		jtxteyear.setBounds(388, 193, 58, 22);
		frame.getContentPane().add(jtxteyear);
		
		currButton = new JRadioButton("CURRENT");
		currButton.setFont(new Font("Tahoma", Font.PLAIN, 10));
		currButton.setBounds(452, 193, 73, 23);
		frame.getContentPane().add(currButton);
		
		jtxtbmonth = new JComboBox(months);
		jtxtbmonth.setBounds(272, 391, 58, 22);
		frame.getContentPane().add(jtxtbmonth);
		
		jtxtbday = new JComboBox(dates);
		jtxtbday.setBounds(340, 391, 40, 22);
		frame.getContentPane().add(jtxtbday);
		
		jtxtbyear = new JComboBox(years);
		jtxtbyear.setBounds(388, 392, 58, 22);
		frame.getContentPane().add(jtxtbyear);
		
		lblEx = new JLabel("*3 digits");
		lblEx.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEx.setBounds(391, 251, 90, 28);
		frame.getContentPane().add(lblEx);
	}
}

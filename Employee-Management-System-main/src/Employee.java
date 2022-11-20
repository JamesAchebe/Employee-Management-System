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
import java.awt.event.ActionEvent;
import java.sql.*;
import javax.swing.*;

public class Employee {

	private JFrame frame;
	private JTextField jtxtfname;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblNiNumber;
	private JTextField jtxtlname;
	private JLabel lblFirstname;
	private JTextField jtxtemail;
	private JLabel lblSurname;
	private JTextField jtxtstartdate;
	private JLabel lblGender;
	private JTextField jtxtenddate;
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
	private JTextField jtxtDOB;
	
	/**
	 * Launch the application.
	 */
	
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
					/*
					Employee.CreateLoginForm form = new Employee.CreateLoginForm();
					form.setSize(900,200);  //set size of the frame
			        form.setVisible(true);  //make form visible to the user
			        */
					Employee window = new Employee();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/**
	 * Create the application.
	 */
	public void loadList() {
		try
		{
			String query = "select * from Employee";
			PreparedStatement pst = conn.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			while(model.getRowCount() > 0)
			{
			    model.removeRow(0);
			}
			
			DefaultListModel DLM = new DefaultListModel();
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
			pst.close();
			if (rs != null) 
				rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public Employee() {
		initialize();
		
		conn = EmployeeData.ConnectDB();
		Object col[] = {"Firstname", "Lastname", "Email", "Start", "End", "Salary", "EmpID", "PhoneNum", "Department", "Position", "DOB"};
		model.setColumnIdentifiers(col);
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
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String sql = "INSERT INTO employee(Firstname, Lastname, Email, Start, End, Salary, EmpID, PhoneNum, Department, Position, DOB)VALUES(?,?,?,?,?,?,?,?,?,?,?)";
				
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1,  jtxtfname.getText());
					pst.setString(2,  jtxtlname.getText());
					pst.setString(3,  jtxtemail.getText());
					pst.setString(4,  jtxtstartdate.getText());
					pst.setString(5,  jtxtenddate.getText());
					pst.setString(6,  jtxtsalary.getText());
					pst.setString(7,  jtxtemployeeid.getText());
					pst.setString(8,  jtxtphonenumber.getText());
					pst.setString(9,  jtxtdepartment.getText());
					pst.setString(10,  jtxtposition.getText());
					pst.setString(11,  jtxtDOB.getText());
					pst.execute();
					
					rs.close();
					pst.close();
				}
				catch(Exception ev)
				{
					System.err.format(ev.getMessage());
					JOptionPane.showMessageDialog(null, "System Update Completed");
				}
				//updateTable();
				/*
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				model.addRow(new Object[] {
						jtxtEmployeeID.getText(),
						jtxtNINumber.getText(),
						jtxtFirstname.getText(),
						jtxtSurname.getText(),
						jtxtGender.getText(),
						jtxtDOB.getText(),
						jtxtAge.getText(),
						jtxtSalary.getText()
				});
				if (table.getSelectedRow() == -1) {
					if (table.getRowCount() == 0) {
						JOptionPane.showMessageDialog(null, "Membership Update Confirmed", "Employee Database System", JOptionPane.OK_OPTION);
					}
				}
				*/
				loadList();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(272, 460, 214, 47);
		frame.getContentPane().add(btnNewButton);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(531, 53, 819, 639);
		frame.getContentPane().add(scrollPane);
		
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
		
		jtxtstartdate = new JTextField();
		jtxtstartdate.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtstartdate.setColumns(10);
		jtxtstartdate.setBounds(272, 155, 209, 28);
		frame.getContentPane().add(jtxtstartdate);
		
		lblGender = new JLabel("End Date");
		lblGender.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblGender.setBounds(94, 194, 164, 14);
		frame.getContentPane().add(lblGender);
		
		jtxtenddate = new JTextField();
		jtxtenddate.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtenddate.setColumns(10);
		jtxtenddate.setBounds(272, 187, 209, 28);
		frame.getContentPane().add(jtxtenddate);
		
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
		jtxtemployeeid.setBounds(272, 251, 209, 28);
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
		
		btnPrint = new JButton("Print");
		btnPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Printing in Progress");
				MessageFormat footer = new MessageFormat("Page  {0, number, integer}");
				
				try 
				{
					table.print();
				}
				catch(java.awt.print.PrinterException ev)
				{
					System.err.format("No Printer Found", ev.getMessage());
				}
				
			}
		});
		btnPrint.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnPrint.setBounds(20, 646, 214, 47);
		frame.getContentPane().add(btnPrint);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtxtfname.setText(null);
				jtxtlname.setText(null);
				jtxtemail.setText(null);
				jtxtstartdate.setText(null);
				jtxtenddate.setText(null);
				jtxtsalary.setText(null);
				jtxtemployeeid.setText(null);
				jtxtphonenumber.setText(null);
			}
		});
		btnReset.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReset.setBounds(272, 428, 209, 21);
		frame.getContentPane().add(btnReset);
		
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
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(94, 66, 164, 14);
		frame.getContentPane().add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Employee Database Management System");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel_1.setBounds(162, 11, 748, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = EmployeeData.ConnectDB();
				
				String sql = "DELETE FROM employee WHERE EmpID = ?";
				
				String value = JOptionPane.showInputDialog(null, "Enter Employee ID to Delete Record");
				
				try
				{
					pst = conn.prepareStatement(sql);
					pst.setString(1, value);
					pst.execute();
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
		btnDelete.setBounds(20, 530, 214, 47);
		frame.getContentPane().add(btnDelete);
		
		JButton editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = EmployeeData.ConnectDB();
				String sql = "update employee set Firstname='"+ jtxtfname.getText() +"' , Lastname='"+ jtxtlname.getText() + "', Email='"+ jtxtemail.getText() +"', Start='"+ jtxtstartdate.getText() +"', End='"+ jtxtenddate.getText() +"', Salary='"+ jtxtsalary.getText() +"', EmpID='"+ jtxtemployeeid.getText() +"', PhoneNum='"+ jtxtphonenumber.getText() +"', Department='"+ jtxtdepartment.getText() +"', Position='"+ jtxtposition.getText() +"', DOB='"+ jtxtDOB.getText() +"' WHERE EmpID='"+ jtxtemployeeid.getText() +"'  ";

				/*
				 * 
				 * String sql = "UPDATE employee SET "
                        + "Firstname='"+jtxtfname.getText()+"',"
                        + "Lastname='"+jtxtlname.getText()+"',"
                        + "Email='"+jtxtemail.getText()+"',"
                        + "Start='"+jtxtstartdate.getText()+"',"
                        + "End='"+jtxtenddate.getText()+"',"
                        + "Salary='"+jtxtsalary.getText()+"',"
                        + "EmpID='"+jtxtemployeeid.getText()+"',"
                        + "PhoneNum='"+jtxtphonenumber.getText()+"',"
                		+ "Department='"+jtxtdepartment.getText()+"',"
                        + "Position='"+jtxtposition.getText()+"',"
                        + "DOB='"+jtxtDOB.getText()+"',"
                        + " WHERE EmpID='"+jtxtemployeeid.getText()+"'";
				 */
                try
                {
                    pst = conn.prepareStatement(sql);
                    
                    
                    pst.execute();
                    
                    JOptionPane.showMessageDialog(null, "Record Updated");
                    
                    pst.close();
                } //end try
                catch(Exception ev)
                {
                    System.err.format(ev.getMessage());
                    JOptionPane.showMessageDialog(null, "System Update Completed");
                } //end catch
                loadList();
			}
		});
		editButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		editButton.setBounds(20, 588, 214, 47);
		frame.getContentPane().add(editButton);
		
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
		
		jtxtDOB = new JTextField();
		jtxtDOB.setFont(new Font("Tahoma", Font.BOLD, 18));
		jtxtDOB.setColumns(10);
		jtxtDOB.setBounds(272, 391, 209, 28);
		frame.getContentPane().add(jtxtDOB);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 517, 516, 22);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 53, 511, 2);
		frame.getContentPane().add(separator_1);
	}
}

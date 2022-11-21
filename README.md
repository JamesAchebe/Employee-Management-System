# Employee-Management-System
This program was built using Java Swing to build the GUI and sqlite as the database.
After forking/downloading this project. It should be able to be run in a Java ide such as Eclipse. 
We built the GUI with Eclipse Window Builder.

## Logging In
After running the code, a window should pop up prompting the user to input a username and password and select submit.
Here are the login details:
Username: "username"
Password: "password"

Upon logging in a message should appear saying "Login Successful" followed by "Connection Made". 
Then the Employee Database Management System window should appear. 

![program image](https://i.imgur.com/P3nGDbg.png)


## Main Functionality
On the Employee Database Management System window.

On the right is the database which updates based on what is in the employee.db file. 

On the top left is the input form for using the add and edit buttons. 

On the bottom left is the delete and print button. 

## Add button
The user must fill out all input fields and after valid input, the program sends the input data to the database file to be recorded.
Then the table is updated based on the updated database. 

Input Validation:
- All fields must be filled out 
- First Name, Last Name, Department, and Positon must be all letters
- Email must be in email format (must have @ and .)
- End Date input must be filled OR Current button must be selected
- Salary must be numerical
- Employee ID must be 3 numbers 
- Phone Number must be 10 numbers no dashes

Ex Input:
- First Name: matthew
- Last Name: Pham
- Email: username@domain.com
- Start Date: 02/15/2020
- End Date: 7/12/2021 (or CURR)
- Salary: 70000
- Employee ID: 333
- Phone Number: 1112223333
- Department: Engineering
- Position: Software Developer
- Date of Birth: 05/15/2002

## Edit Button
Edit button also uses the same input form as the Add button. 
Employee ID MUST match an existing record, and all other fields will be editted depending on what is currently in the input fields.
Input Validation is done the same as for the Add button. However, if the ID doesnt not exist in the database, a "Employee Record not Found" message will pop up. 

## Delete Button 
Delete button opens up a pop up that asks the user to input an employee ID in order to delete the corresponding record.
Performs input validation on the ID and deletes it if it exists. 

## Print Button
Print button opens up a window with a printing GUI that can print the database shown in the table on the right. 









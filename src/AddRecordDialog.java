/*
 * 
 * This is a dialog for adding new Employees and saving records to file
 * 
 * */

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class AddRecordDialog extends JDialog implements ActionListener {
	JTextField idField, ppsField, surnameField, firstNameField, salaryField;
	JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;
	JButton save, cancel;
	EmployeeDetails parent;
	
	public AddRecordDialog(EmployeeDetails parent) {
		setTitle("Add Record");
		setModal(true);
		this.parent = parent;
		this.parent.setEnabled(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JScrollPane scrollPane = new JScrollPane(dialogPane());
		setContentPane(scrollPane);
		
		getRootPane().setDefaultButton(save);
		
		setSize(500, 370);
		setLocation(350, 250);
		setVisible(true);
	}

	
	public Container dialogPane() {
		JPanel empDetails, buttonPanel;
		empDetails = new JPanel(new MigLayout());
		buttonPanel = new JPanel();
		JTextField field;

		empDetails.setBorder(BorderFactory.createTitledBorder("Employee Details"));

		empDetails.add(new JLabel("ID:"), Miglayout_Manager.miglayout1);
		empDetails.add(idField = new JTextField(20), Miglayout_Manager.miglayout2);
		idField.setEditable(false);
		

		empDetails.add(new JLabel("PPS Number:"), Miglayout_Manager.miglayout1);
		empDetails.add(ppsField = new JTextField(20), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("Surname:"), Miglayout_Manager.miglayout1);
		empDetails.add(surnameField = new JTextField(20), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("First Name:"), Miglayout_Manager.miglayout1);
		empDetails.add(firstNameField = new JTextField(20), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("Gender:"), Miglayout_Manager.miglayout1);
		empDetails.add(genderCombo = new JComboBox<String>(this.parent.gender), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("Department:"), Miglayout_Manager.miglayout1);
		empDetails.add(departmentCombo = new JComboBox<String>(this.parent.department), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("Salary:"), Miglayout_Manager.miglayout1);
		empDetails.add(salaryField = new JTextField(20), Miglayout_Manager.miglayout2);

		empDetails.add(new JLabel("Full Time:"), Miglayout_Manager.miglayout1);
		empDetails.add(fullTimeCombo = new JComboBox<String>(this.parent.fullTime), Miglayout_Manager.miglayout2);

		buttonPanel.add(save = new JButton("Save"));
		save.addActionListener(this);
		save.requestFocus();
		buttonPanel.add(cancel = new JButton("Cancel"));
		cancel.addActionListener(this);

		empDetails.add(buttonPanel,Miglayout_Manager.miglayout3 );
		
		for (int i = 0; i < empDetails.getComponentCount(); i++) {
			empDetails.getComponent(i).setFont(this.parent.font1);
			if (empDetails.getComponent(i) instanceof JComboBox) {
				empDetails.getComponent(i).setBackground(Color_Class.white);
			}
			else if(empDetails.getComponent(i) instanceof JTextField){
				field = (JTextField) empDetails.getComponent(i);
				if(field == ppsField)
					field.setDocument(new JTextFieldLimit(9));
				else
				field.setDocument(new JTextFieldLimit(20));
			}
		}
		idField.setText(Integer.toString(this.parent.getNextFreeId()));
		return empDetails;
	}


	public void addRecord() {
		boolean fullTime = false;
		Employee theEmployee;

		if (((String) fullTimeCombo.getSelectedItem()).equalsIgnoreCase("Yes"))
			fullTime = true;
		
		theEmployee = new Employee(Integer.parseInt(idField.getText()), ppsField.getText().toUpperCase(), surnameField.getText().toUpperCase(),
				firstNameField.getText().toUpperCase(), genderCombo.getSelectedItem().toString().charAt(0),
				departmentCombo.getSelectedItem().toString(), Double.parseDouble(salaryField.getText()), fullTime);
		this.parent.currentEmployee = theEmployee;
		this.parent.addRecord(theEmployee);
		this.parent.displayRecords(theEmployee);
	}

	
	public boolean checkInput() {
		boolean valid = true;
				if (ppsField.getText().equals("")) {
			ppsField.setBackground(Color_Class.red);
			valid = false;
		}
		if (this.parent.correctPps(this.ppsField.getText().trim(), -1)) {
			ppsField.setBackground(Color_Class.red);
			valid = false;
		}
		if (surnameField.getText().isEmpty()) {
			surnameField.setBackground(Color_Class.red);
			valid = false;
		}
		if (firstNameField.getText().isEmpty()) {
			firstNameField.setBackground(Color_Class.red);
			valid = false;
		}
		if (genderCombo.getSelectedIndex() == 0) {
			genderCombo.setBackground(Color_Class.red);
			valid = false;
		}
		if (departmentCombo.getSelectedIndex() == 0) {
			departmentCombo.setBackground(Color_Class.red);
			valid = false;
		}
		try {
			Double.parseDouble(salaryField.getText());
			
			if (Double.parseDouble(salaryField.getText()) < 0) {
				salaryField.setBackground(Color_Class.red);
				valid = false;
			}
		}
		catch (NumberFormatException num) {
			salaryField.setBackground(Color_Class.red);
			valid = false;
		}
		if (fullTimeCombo.getSelectedIndex() == 0) {
			fullTimeCombo.setBackground(Color_Class.red);
			valid = false;
		}
		return valid;
	}

	public void actionPerformed(ActionEvent e) {
	
		if (e.getSource() == save) {
			
			if (checkInput()) {
				addRecord();
				dispose();
				this.parent.changesMade = true;
			}
			
			else {
				JOptionPane.showMessageDialog(null, "Wrong values or format! Please check!");
				Color_Class c = new Color_Class(ppsField, surnameField, firstNameField, salaryField, genderCombo, departmentCombo, fullTimeCombo);
				c.setWhite();
			}
			}
		else if (e.getSource() == cancel)
			dispose();
	}
}
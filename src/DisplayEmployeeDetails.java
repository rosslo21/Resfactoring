import javax.swing.JTextField;

public class DisplayEmployeeDetails {
	
	JTextField searchByIdField, searchBySurnameField;
	String[] gender = { "", "M", "F" };
	EmployeeDetails parent;
	private boolean change = false;
	String[] department = { "", "Administration", "Production", "Transport", "Management" };
	
	// display current Employee details
		public void displayRecords(Employee thisEmployee) {
			int countGender = 0;
			int countDep = 0;
			boolean found = false;

			searchByIdField.setText("");
			searchBySurnameField.setText("");
			// if Employee is null or ID is 0 do nothing else display Employee
			// details
			if (thisEmployee == null) {
			} else if (thisEmployee.getEmployeeId() == 0) {
			} else {
				// find corresponding gender combo box value to current employee
				while (!found && countGender < gender.length - 1) {
					if (Character.toString(thisEmployee.getGender()).equalsIgnoreCase(gender[countGender]))
						found = true;
					else
						countGender++;
				} // end while
				found = false;
				// find corresponding department combo box value to current employee
				while (!found && countDep < department.length - 1) {
					if (thisEmployee.getDepartment().trim().equalsIgnoreCase(department[countDep]))
						found = true;
					else
						countDep++;
				} // end while
				this.parent.idField.setText(Integer.toString(thisEmployee.getEmployeeId()));
				this.parent.ppsField.setText(thisEmployee.getPps().trim());
				this.parent.surnameField.setText(thisEmployee.getSurname().trim());
				this.parent.firstNameField.setText(thisEmployee.getFirstName());
				this.parent.genderCombo.setSelectedIndex(countGender);
				this.parent.departmentCombo.setSelectedIndex(countDep);
				this.parent.salaryField.setText(this.parent.format.format(thisEmployee.getSalary()));
				// set corresponding full time combo box value to current employee
				if (thisEmployee.getFullTime() == true)
					this.parent.fullTimeCombo.setSelectedIndex(1);
				else
					this.parent.fullTimeCombo.setSelectedIndex(2);
			}
			change = false;
		}// end display records
	
	
	

}

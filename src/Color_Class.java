
	import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;

	public class Color_Class {
		
		JTextField ppsField, surnameField, firstNameField, salaryField;
		JComboBox<String> genderCombo, departmentCombo, fullTimeCombo;

		static Color red = new Color(255, 150, 150);
		static Color white = Color.WHITE;
		static Color foreground = new Color(65, 65, 65);
		
		
		public Color_Class(JTextField ppsField, JTextField surnameField, JTextField firstNameField, JTextField salaryField,JComboBox<String> genderCombo, JComboBox<String> departmentCombo, JComboBox<String> fullTimeCombo) {
			
			this.ppsField = ppsField;
			this.surnameField = surnameField;
			this.firstNameField = firstNameField;
			this.salaryField = salaryField;
			this.genderCombo = genderCombo;
			this.departmentCombo = departmentCombo;
			this.fullTimeCombo = fullTimeCombo;
		}

			public void setToWhite() {
			ppsField.setBackground(UIManager.getColor("TextField.background"));
			surnameField.setBackground(UIManager.getColor("TextField.background"));
			firstNameField.setBackground(UIManager.getColor("TextField.background"));
			salaryField.setBackground(UIManager.getColor("TextField.background"));
			genderCombo.setBackground(UIManager.getColor("TextField.background"));
			departmentCombo.setBackground(UIManager.getColor("TextField.background"));
			fullTimeCombo.setBackground(UIManager.getColor("TextField.background"));
	}
		
		
		public void setWhite() {		
			ppsField.setBackground(Color_Class.white);		
		surnameField.setBackground(Color_Class.white);
		
			firstNameField.setBackground(Color_Class.white);
			salaryField.setBackground(Color_Class.white);
			genderCombo.setBackground(Color_Class.white);
			departmentCombo.setBackground(Color_Class.white);
			fullTimeCombo.setBackground(Color_Class.white);
		}
	}



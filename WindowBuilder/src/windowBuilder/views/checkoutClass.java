package windowBuilder.views;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class checkoutClass extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public checkoutClass() {
		
		initLabel();
		
		//setLayout(groupLayout);

	}

	private  String firstName;
	private  String lastName;
	private  String address;
	private  String city;
	private  String state;
	private  String zipCode;
	private  String phoneNum;

	
	private JTextField first;
	private JTextField last;
	private JTextField address1;
	private JTextField city1;
	private JTextField state1;
	private JTextField zipNum;
	private JTextField cellNum;
	
	
	
	
	
	

	public void Equalize()
	{
		firstName = first.getText();
		lastName = last.getText();
		address = address1.getText();;
		city = city1.getText();
		state = state1.getText();
		zipCode = zipNum.getText();
		phoneNum = cellNum.getText();
		
		
        //JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");     
	}
			//int.parseint(cellNum)
			
			
	public boolean checkZip()
	{				 
		if(zipCode.length() != 5 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkPhone()
	{
		if(phoneNum.length() != 10 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	public String getFullName()
	{
		return firstName + " " + lastName;
	}
	
	public String getAddress()
	{
		return address;
	}
	
	public String getCity()
	{
		return city;
	}
	
	public String getState()
	{
		return state;
	}


	private void initLabel()
	{
		setLayout(null);
		JLabel firstName = new JLabel("First Name");
		firstName.setBounds(10, 207, 80, 14);
		
		JLabel lastName = new JLabel("Last Name");
		lastName.setBounds(157, 207, 80, 14);
		
		JLabel address = new JLabel("Address");
		address.setBounds(305, 207, 49, 14);
		
		JLabel stateInitials = new JLabel("State");
		stateInitials.setBounds(436, 207, 49, 14);
		
		JLabel phone = new JLabel("Phone Number");
		phone.setBounds(10, 278, 90, 14);
		
		JLabel cityName = new JLabel("City");
		cityName.setBounds(305, 278, 49, 14);
		
		JLabel zip = new JLabel("Zip Code");
		zip.setBounds(436, 278, 70, 14);
		add(zip);
		
		
		add(cityName);
		add(phone);
		add(stateInitials);		
		add(address);
		add(firstName);
		add(lastName);
		
		first = new JTextField();
		first.setBounds(10, 225, 80, 20);
		add(first);
		first.setColumns(10);
		
		last = new JTextField();
		last.setBounds(157, 225, 80, 20);
		add(last);
		last.setColumns(10);
		
		address1 = new JTextField();
		address1.setBounds(305, 225, 80, 20);
		add(address1);
		address1.setColumns(10);
		
		state1 = new JTextField();
		state1.setBounds(435, 225, 25, 20);
		add(state1);
		state1.setColumns(10);
		
		cellNum = new JTextField();
		cellNum.setBounds(10, 296, 96, 20);
		add(cellNum);
		cellNum.setColumns(10);
		
		city1 = new JTextField();
		city1.setBounds(305, 296, 96, 20);
		add(city1);
		city1.setColumns(10);
		
		
		zipNum = new JTextField();
		zipNum.setBounds(436, 296, 50, 20);
		add(zipNum);
		zipNum.setColumns(10);
		
		
		
		
		
		
	}
}
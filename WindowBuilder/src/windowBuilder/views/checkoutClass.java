 package windowBuilder.views;

import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.JList;
//Ralph's-Branch---Contains-all-classes-I'm-working-on
import javax.swing.border.BevelBorder;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import java.awt.Font;



/**
 * The goal of the Checkout class is to do what for many people is at the end of the shopping experience
 * 
 * 
 *  
 * @author Aaron Flores
 *date Started 10/28
 */
 //Customer-Specifics

public class checkoutClass extends JPanel {
	

	/**
	 * Create the panel.
	 */
	public checkoutClass() {
		
		initLabel();
		createEvents();
		
		shoppingList = new DefaultListModel<Object>();
		quantityList = new DefaultListModel<Object>();
		//setLayout(groupLayout);

	}

	private  String firstName;
	private  String lastName;
	private  String address;
	private  String city;
	private  String state;
	private  String zipCode;
	private  String phoneNum;
	private  String email;

	
	private JTextField first;
	private JTextField last;
	private JTextField address1;
	private JTextField city1;
	private JTextField state1;
	private JTextField zipNum;
	private JTextField cellNum;
	
	boolean zipCheck;
	boolean phoneCheck;
	boolean firstCheck;
	boolean lastCheck;
	boolean addressCheck;
	boolean cityCheck;
	boolean stateCheck;
	boolean emailCheck;
	
	private JButton enter;
 // Ralph's-Branch---Contains-all-classes-I'm-working-on
	private static JList checkoutList;
	private static JList checkoutQuantity;
	private JTextArea textArea_2;
	private JTextField emailAddress;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextArea textArea_3;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_5;
	private JLabel lblNewLabel_6;
	private JRadioButton rdbtnNewRadioButton_3;
	private JRadioButton rdbtnNewRadioButton_4;
	private JLabel lblNewLabel_7;

	private JList shopList;
	
	public static DefaultListModel<Object> shoppingList;  
	public static DefaultListModel<Object> quantityList;
	private static JTextArea totalPrice;
	private static JTextArea taxArea;
	
 //Customer-Specifics
	
	/*public void addListeners()
	{
		enter.addActionListener(new ActionListener())
		{
			@Override
        	public void actionPerformed(ActionEvent e) 
			{
        	
			}
		
		}
	}*/
		public static void transferCart()
		{
			addTo(cartClass.CartList_items_2,shoppingList);
			addTo(cartClass.ToCartQuantityList_items_4, quantityList);
			
			checkoutList.setModel(shoppingList);
			checkoutQuantity.setModel(quantityList);
			
			totalPrice.setText("$"+String.valueOf(cartClass.sum)+".00");
			double taxTot = cartClass.sum *.0725;
			DecimalFormat shorten = new DecimalFormat("#.00");
			
			String taxTransfer = shorten.format(taxTot);
			taxArea.setText("$"+ taxTransfer);
			
		}
		
		/**
		 * Used to add one DefaultListModel to another.<br>
		 * 
		 * @param <T>     used to represent the type of object stored
		 * @param from    the list that represents the change coming 'from'
		 * @param to      the list that represents the change going 'to'
		 */
		protected static <T> void addTo(ListModel<T> from, DefaultListModel<T> to) { //method used to add one ListModel to another DefaultListModel
		    for (int index = 0; index < from.getSize(); index++) {
		        to.addElement(from.getElementAt(index));
		    }
		}
		
		
		private void createEvents() 
		{ //this method initializes all event elements of the panel
		
			enter.addActionListener(new ActionListener()
			{ //this action method for button: 'enter' pays and checks inputs 
				public void actionPerformed(ActionEvent e) 
				{
					
					
					try
					{
						Equalize();
						 zipCheck = checkZip();
						 phoneCheck = checkPhone();
						 firstCheck = checkFirst();
						 lastCheck = checkLast();
						 addressCheck = checkAddress();
						 cityCheck = checkCity();
						 stateCheck = checkState();
						 emailCheck = checkEmail();
						 
					}
					 finally
					 {
						 if(zipCheck == false)
							{
								JOptionPane.showMessageDialog(null, "Zip Code Must Be 5 Digits, Input Again", "Alert", JOptionPane.ERROR_MESSAGE); //Displays a pop-up messa
							}
						 else if(phoneCheck == false)
							{
								JOptionPane.showMessageDialog(null, "Phone Number Must Be 10 Digits, Input Again ", "Alert", JOptionPane.ERROR_MESSAGE); //Displays a pop-up messa
							}
						 else if(firstCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "First Name Cannot Be Empty, Input Again ", "Alert", JOptionPane.ERROR_MESSAGE);
						 }
						 else if(lastCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "Last Name Cannot Be Empty, Input Again ", "Alert", JOptionPane.ERROR_MESSAGE);
						 }
						 else if(addressCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "Address Cannot Be Empty, Input Again ", "Alert", JOptionPane.ERROR_MESSAGE);
						 }	
						 else if(cityCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "City Cannot Be Empty, Input Again ", "Alert", JOptionPane.ERROR_MESSAGE);
						 }
						 else if(stateCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "State Not Accepted , Input 2-Letter Abbreviation ", "Alert", JOptionPane.ERROR_MESSAGE);
						 }
						 else if(emailCheck == false)
						 {
							 JOptionPane.showMessageDialog(null, "Email Cannot be left Empty", "Alert", JOptionPane.ERROR_MESSAGE);
						 }
					 }
					
				}
			});	
		}	
	public void Equalize()
	{
		firstName = first.getText();
		lastName = last.getText();
		address = address1.getText();;
		city = city1.getText();
		state = state1.getText();
		zipCode = zipNum.getText();
		phoneNum = cellNum.getText();
		email = emailAddress.getText();
		
        //JOptionPane.showMessageDialog(null, "Woah! Bad input, numbers only!");     
	}
			//int.parseint(cellNum)
			
	public boolean checkEmail()
	{
		if(email.length() == 0)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
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
	
	public boolean checkFirst()
	{
		if(firstName.length() == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkLast()
	{
		if(lastName.length() == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkAddress()
	{
		if(address.length() == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkCity()
	{
		if(city.length() == 0 )
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public boolean checkState()
	{
		if(state.length() != 2 )
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

	public String getEmail()
	{
		return email;
	}

	private void initLabel()
	{
		JLabel firstName = new JLabel("First Name");
		
		JLabel lastName = new JLabel("Last Name");
		
		JLabel address = new JLabel("Address");
		
		JLabel stateInitials = new JLabel("State");
		
		JLabel phone = new JLabel("Phone");
		
		JLabel cityName = new JLabel("City");
		
		JLabel zip = new JLabel("Zip");
		
		first = new JTextField();
		first.setColumns(10);
		
		last = new JTextField();
		last.setColumns(10);
		
		address1 = new JTextField();
		address1.setColumns(10);
		
		state1 = new JTextField();
		state1.setColumns(10);
		
		cellNum = new JTextField();
		cellNum.setColumns(10);
		
		city1 = new JTextField();
		city1.setColumns(10);
		
		
		zipNum = new JTextField();
		zipNum.setColumns(10);
		
		
		enter = new JButton("Pay");
		
		checkoutList = new JList<Object>();
		checkoutList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		checkoutQuantity = new JList<Object>();
		checkoutQuantity.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		totalPrice = new JTextArea();
		totalPrice.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		taxArea = new JTextArea();
		taxArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		textArea_2 = new JTextArea();
		textArea_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		emailAddress = new JTextField();
		emailAddress.setColumns(10);
		
		lblNewLabel = new JLabel("E-Mail");
		
		lblNewLabel_1 = new JLabel("Total:");
		
		lblNewLabel_2 = new JLabel("Tax:");
		
		lblNewLabel_3 = new JLabel("Shipping:");
		
		JRadioButton rdbtnNewRadioButton = new JRadioButton("7-10 Days Standard Shipping");
		rdbtnNewRadioButton.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("3-Day Shipping");
		rdbtnNewRadioButton_1.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("Next Day Shipping");
		rdbtnNewRadioButton_2.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		textArea_3 = new JTextArea();
		textArea_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_4 = new JLabel("Grand Total:");
		
		lblNewLabel_5 = new JLabel("Qty:");
		
		lblNewLabel_6 = new JLabel("Products to Checkout");
		
		rdbtnNewRadioButton_3 = new JRadioButton("Credit/Debit Card");
		rdbtnNewRadioButton_3.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		rdbtnNewRadioButton_4 = new JRadioButton("Electronic Check");
		rdbtnNewRadioButton_4.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		
		lblNewLabel_7 = new JLabel("* Secure payment info will be entered after selection is made from above");
		lblNewLabel_7.setFont(new Font("Lucida Grande", Font.ITALIC, 9));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(33)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNewRadioButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNewRadioButton_1)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnNewRadioButton_2))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
												.addComponent(first, GroupLayout.PREFERRED_SIZE, 98, GroupLayout.PREFERRED_SIZE))
											.addGap(14)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(lastName, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
													.addGap(18))
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(last, 0, 0, Short.MAX_VALUE)
													.addPreferredGap(ComponentPlacement.UNRELATED)))
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(address1, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
												.addComponent(address, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(phone, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)
												.addComponent(cellNum, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(emailAddress, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNewLabel))
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout.createSequentialGroup()
													.addComponent(cityName, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
													.addGap(47))
												.addComponent(city1, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)))
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnNewRadioButton_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(rdbtnNewRadioButton_4)
											.addGap(52)))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(zip, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(state1, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
											.addComponent(stateInitials, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
										.addComponent(enter, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
										.addComponent(zipNum, GroupLayout.PREFERRED_SIZE, 57, GroupLayout.PREFERRED_SIZE))
									.addGap(68))))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(106)
									.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_4)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_3)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_2)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(taxArea, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(lblNewLabel_1)
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(totalPrice, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
											.addComponent(checkoutList, GroupLayout.PREFERRED_SIZE, 274, GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(ComponentPlacement.UNRELATED)))
								.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel_6)
									.addGap(74)))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_5)
								.addComponent(checkoutQuantity, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
							.addGap(141))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(23)
							.addComponent(lblNewLabel_7)))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(26)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_6)
						.addComponent(lblNewLabel_5))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE, false)
						.addComponent(checkoutList, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)
						.addComponent(checkoutQuantity, GroupLayout.PREFERRED_SIZE, 129, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(totalPrice, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_1))
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(taxArea, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2))
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_3))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_4)))
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(rdbtnNewRadioButton)
						.addComponent(rdbtnNewRadioButton_1)
						.addComponent(rdbtnNewRadioButton_2))
					.addGap(26, 26, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(firstName, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lastName, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(stateInitials, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(address, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(first, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(address1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(last, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(state1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(zip, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(phone, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(cityName, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(zipNum, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(cellNum, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(emailAddress, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(city1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(87)
							.addComponent(enter, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(rdbtnNewRadioButton_3)
								.addComponent(rdbtnNewRadioButton_4))
							.addGap(18)
							.addComponent(lblNewLabel_7)))
					.addGap(41))
		);
		setLayout(groupLayout);
		
		/*JList shopList = new JList();
		shopList = cartClass.addTo(JListCartList ,shopList);
		shopList.setBounds(10, 23, 475, 104);
		add(shopList);
		
		JList list = new JList();
		list.setBounds(495, 23, 32, 104);
		add(list);
		
		*/
		
		
		
		
	}
}
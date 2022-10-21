/**
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import javax.swing.JPanel;
import java.util.Arrays;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.ListSelectionModel;
import javax.swing.ImageIcon;

public class cartClass extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static DefaultListModel<Object> CartList_items_2; //DefaultListModel list used to create list containing items added Cart List
	public static JList<Object> JListCartList; //JList element that displays the Products added to the Shopping List
	public static JTextArea textAreaCartTotal;  // JTextArea that displays the current total of Products added to the Shopping List
	public static int[] cartPriceArray = new int[20]; //an arrray that is used to tabulate the total amount of the Cart
	public static Object[] prices2 = new String[20]; //used to track the order of indexes that were added to the shopping list
	private JButton btnEmptyCart; //button that empties the Cart
	private JButton btnRemoveItem; //button that removes selected product from cart
	private static String total; //String variable that is used in the setCartPriceTotal() method
	private static int counter = 0; //int variable that is used in the addCartprice() method
	public static int sum; ////int variable that is used in the addCartprice() method and rearrangeArray() method
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public cartClass() {
		
		initComponents(); //calls initComponents() method; builds all structural elements of the panel
		createEvents(); //calls createEvents()() method; builds all events that happen (actions)
		textAreaCartTotal.setText("$0.00"); //sets the initial total of the Shopping List to $0.00
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param index
	 */
	public static void addCartprice(int index) {
		
		Object cost = productSearchClass.prices[index]; //this creates an object variable that is initialized from the passed parameter/prices[]
		int cost_value = Integer.parseInt(cost.toString()); //this converts the object to integer
		cartPriceArray[counter] = cost_value; //this loads the cartPriceArray[]
		counter++; //increments counter variable
		sum = 0; //sets the variable initially to 0
		for(int i = 0; i < cartPriceArray.length; i++){ //loop to add up the total price that is in cartPriceArray
			sum += cartPriceArray[i]; //adds up the cartPriceArray[] and stores it in the variable sum
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public static void setCartPriceTotal() {
	
		textAreaCartTotal.setText(""); //clears text from textAreaTotal
		total = Integer.toString(sum); //converts integer to String needed to display in textAreaTotal box
		textAreaCartTotal.append("$" + total + ".00"); //displays the current total price from the shopping list in the textAreaTotal box

	}
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param element
	 */
	public static void rearrangeCart(int element) {
		
		int[] cartPriceArray2 = new int[cartPriceArray.length -1]; //initializes the temp cartPriceArray2 to the length of cartPriceArray.length -1]
		for(int i = 0, k = 0; i < cartPriceArray.length; i++){ //loop each element of cartPriceArray
			if(cartPriceArray[i] != element){ //if statement; if cartPriceArray at index i does not equal parameter x
				cartPriceArray2[k] = cartPriceArray[i];	//if statement is true, then load cartPriceArray2 at index k with cartPriceArray at index i
				k++; //increment k
			}
			
		}
		cartPriceArray = cartPriceArray2; //after for loop, set cartPriceArray[] to temp cartPriceArray2[]	
		int addedUp = Arrays.stream(cartPriceArray).sum(); //add  up the total sum of cartPriceArray and set it to temp int variable: addedUp		
		if (addedUp == 0) {	//if addUp is equal to 0			
			textAreaCartTotal.setText("$0.00"); //this resets the textAreaCartTotal box back to empty
			cartPriceArray = new int[10]; // this resets the cartPriceArray[]
			counter = 0; //this resets the variabale counter
			sum = 0; //this resets the variable sum			
		}

	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void createEvents() {
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnEmptyCart.addActionListener(new ActionListener() { //this action method for button: btnEmptyCart emptys the cart
			public void actionPerformed(ActionEvent e) {	
			
			productSearchClass.ToProductSearchList_items_1.removeAllElements(); //this clears all elemnts from DefaultList Model: ProductSearchList_items_1
			CartList_items_2.removeAllElements(); //this clears all elements from DefaultListModel: CartList_items_2
			productSearchClass.ToCartShopList_items_3.removeAllElements(); //this clears all elements from DefaultListModel: ToCartShopList_items_3
			textAreaCartTotal.setText("$0.00"); //this resets the textAreaCartTotal box back to empty
			cartPriceArray = new int[10]; // this resets the cartPriceArray[]
			//productSearchClass.remember = 0;
			sum = 0;//this resets the variable sum	
			
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnRemoveItem.addActionListener(new ActionListener() { //this action method for button: btnRemoveItem removes a selected product from cart
			public void actionPerformed(ActionEvent e) {
			
			int select = JListCartList.getSelectedIndex(); //sets temp int variable: select to the selected index from JListCartList
			int remove = cartPriceArray[select];	//uses select variable as the index for cartPriceArray and assigns to temp variable remove					
			rearrangeCart(remove); //calls rearrangeArray() methd and passes variable remove as parameter
			int added = Arrays.stream(cartPriceArray).sum(); //adds up the total sum of cartPriceArray and assigns it to temp int variable: added		
			textAreaCartTotal.setText(""); //empties the textAreaCartTotal text
			textAreaCartTotal.append("$" + added + ".00"); //sets the textAreaCartTotal text with the variable: added 			
			CartList_items_2.remove(select); //removes the slected product from CartList_items_2
			//String n = Integer.toString(newTotal);
						
			}
		});
		
	
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void initComponents() { //this method initializes all structural elements of the panel
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Items currently in Cart");
		lblNewLabel.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Full basket.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		textAreaCartTotal = new JTextArea();
		textAreaCartTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Total = ");
		lblNewLabel_1.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Dollar.png")));
		
		btnEmptyCart = new JButton("Empty");
		
		JLabel lblNewLabel_1_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		JLabel lblNewLabel_2 = new JLabel("Empty Cart:");
		lblNewLabel_2.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Remove from basket.png")));
		
		btnRemoveItem = new JButton("Remove");
	
		JLabel lblNewLabel_3 = new JLabel("Remove Item:");
		lblNewLabel_3.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Remove from basket.png")));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(12, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveItem, Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnEmptyCart)
									.addGap(47)
									.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
										.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel_1)
											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel_1_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))))
							.addGap(103))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(158))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnEmptyCart)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel_2))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoveItem)
								.addComponent(lblNewLabel_3)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(228, Short.MAX_VALUE))
		);
		CartList_items_2= new DefaultListModel<Object>();
		JListCartList = new JList<Object>(); //this creates a new JList element that will display the Products added to the Shopping List
		JListCartList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JListCartList.setModel(new AbstractListModel<Object>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		JListCartList.setVisibleRowCount(5);
		JListCartList.setName("");
		scrollPane.setViewportView(JListCartList);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}	
}

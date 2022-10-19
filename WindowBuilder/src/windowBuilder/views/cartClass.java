/**
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import javax.swing.JPanel;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cartClass extends JPanel {
	
	public static JList JListCartList; //JList element that displays the Products added to the Shopping List
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
	 * @param x
	 */
	public static void addCartprice(int x) {
		
		Object cost = productSearchClass.prices[x]; //this creates an object variable that is initialized from the passed parameter/prices[]
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
	 * @param x
	 */
	public static void rearrangeArray(int x) {
		
		int[] cartPriceArray2 = new int[cartPriceArray.length -1]; //initializes the temp cartPriceArray2 to the length of cartPriceArray.length -1]
		for(int i = 0, k = 0; i < cartPriceArray.length; i++){ //loop each element of cartPriceArray
			if(cartPriceArray[i] != x){ //if statement; if cartPriceArray at index i does not equal parameter x
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
	
		btnEmptyCart.addActionListener(new ActionListener() { //this action method for button: btnEmptyCart emptys the cart
			public void actionPerformed(ActionEvent e) {	
			
			productSearchClass.ToProductSearchList_items_1.removeAllElements(); //this clears all elemnts from DefaultList Model: ProductSearchList_items_1
			productSearchClass.CartList_items_2.removeAllElements(); //this clears all elements from DefaultListModel: CartList_items_2
			productSearchClass.ToCartShopList_items_3.removeAllElements(); //this clears all elements from DefaultListModel: ToCartShopList_items_3
			textAreaCartTotal.setText("$0.00"); //this resets the textAreaCartTotal box back to empty
			cartPriceArray = new int[10]; // this resets the cartPriceArray[]
			//productSearchClass.remember = 0;
			sum = 0;//this resets the variable sum	
			
			}
		});
		
		btnRemoveItem.addActionListener(new ActionListener() { //this action method for button: btnRemoveItem removes a selected product from cart
			public void actionPerformed(ActionEvent e) {
			
			int select = JListCartList.getSelectedIndex(); //sets temp int variable: select to the selected index from JListCartList
			int remove = cartPriceArray[select];	//uses select variable as the index for cartPriceArray and assigns to temp variable remove
			
			
			rearrangeArray(remove); //calls rearrangeArray() methd and passes variable remove as parameter
			int added = Arrays.stream(cartPriceArray).sum(); //adds up the total sum of cartPriceArray and assigns it to temp int variable: added
		
			textAreaCartTotal.setText(""); //empties the textAreaCartTotal text
			textAreaCartTotal.append("$" + added + ".00"); //sets the textAreaCartTotal text with the variable: added 
			
			productSearchClass.CartList_items_2.remove(select); //removes the slected product from CartList_items_2
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		textAreaCartTotal = new JTextArea();
		textAreaCartTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Total = ");
		
		btnEmptyCart = new JButton("Empty");
		
		JLabel lblNewLabel_1_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		JLabel lblNewLabel_2 = new JLabel("Empty Cart:");
		
		btnRemoveItem = new JButton("Remove");
	
		JLabel lblNewLabel_3 = new JLabel("Remove Item:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnEmptyCart)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveItem, Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
							.addGap(103))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(173))))
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
						.addComponent(lblNewLabel_1)
						.addComponent(btnEmptyCart)
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
					.addContainerGap(236, Short.MAX_VALUE))
		);
		
		JListCartList = new JList(); //this creates a new JList element that will display the Products added to the Shopping List
		JListCartList.setVisibleRowCount(5);
		JListCartList.setName("");
		scrollPane.setViewportView(JListCartList);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}	
}

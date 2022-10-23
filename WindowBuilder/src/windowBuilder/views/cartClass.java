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
	public static DefaultListModel<Object> ToCartQuantityList_items_4;
	
	public static JList<Object> JListCartList; //JList element that displays the Products added to the Shopping List
	public static JTextArea textAreaCartTotal;  // JTextArea that displays the current total of Products added to the Shopping List
	public static JList<Object> JListCartQuantity;
	
	public static int[] cartPriceArray = new int[20]; //an arrray that is used to tabulate the total amount of the Cart
	public static Object[] prices2 = new String[20]; //used to track the order of indexes that were added to the shopping list
	
	private JButton btnEmptyCart; //button that empties the Cart
	private JButton btnRemoveItem; //button that removes selected product from cart
	
	private static String total; //String variable that is used in the setCartPriceTotal() method
	private static int counter = 0; //int variable that is used in the addCartprice() method
	public static int sum; ////int variable that is used in the addCartprice() method and rearrangeArray() method
	private JButton btnAddItem;
	
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
		JListCartQuantity.setModel(ToCartQuantityList_items_4);
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
	private void createEvents() { //this method initializes all event elements of the panel
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnEmptyCart.addActionListener(new ActionListener() { //this action method for button: btnEmptyCart emptys the cart
			public void actionPerformed(ActionEvent e) {	
			
				//JOptionPane.showMessageDialog(null, JListCartQuantity.getModel());
				
				ToCartQuantityList_items_4.removeAllElements(); //empties out the quantity box
				productSearchClass.txtpnProductDescription.setText(null); //clears out the product description area in productSearchClass 
				productSearchClass.displayLabel.setIcon(null); //clears out the image displayed in productSearchClass
				productSearchClass.ToProductSearchList_items_1.removeAllElements(); //this clears all elemnts from DefaultList Model: ProductSearchList_items_1
				CartList_items_2.removeAllElements(); //this clears all elements from DefaultListModel: CartList_items_2
				productSearchClass.ToCartShopList_items_3.removeAllElements(); //this clears all elements from DefaultListModel: ToCartShopList_items_3
				textAreaCartTotal.setText("$0.00"); //this resets the textAreaCartTotal box back to empty
				cartPriceArray = new int[10]; // this resets the cartPriceArray[]
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 512, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(401, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		ToCartQuantityList_items_4 = new DefaultListModel<Object>();
		
		JLabel lblItemsInCart = new JLabel("Items currently in Cart");
		lblItemsInCart.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Full basket.png")));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		textAreaCartTotal = new JTextArea();
		textAreaCartTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblTotal = new JLabel("Total = ");
		lblTotal.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Dollar.png")));
		
		btnEmptyCart = new JButton("Empty");
		
		JLabel lblNoShipOrTaxes = new JLabel("*Does not include S&H or taxes");
		lblNoShipOrTaxes.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		JLabel lblEmptyCart = new JLabel("Empty Cart:");
		lblEmptyCart.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Remove from basket.png")));
		
		btnRemoveItem = new JButton("Remove");
	
		JLabel lblRemoveItem = new JLabel("Remove Item:");
		lblRemoveItem.setIcon(new ImageIcon(cartClass.class.getResource("/icons/Remove from basket.png")));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblQuantity = new JLabel("Qty:");
		
		JLabel lblAddOneToCart = new JLabel("Add One to Cart:");
		lblAddOneToCart.setIcon(new ImageIcon(cartClass.class.getResource("/icons/basket2.png")));
		
		btnAddItem = new JButton("Add One");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRemoveItem)
								.addComponent(lblEmptyCart))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnEmptyCart)
								.addComponent(btnRemoveItem, GroupLayout.PREFERRED_SIZE, 83, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblAddOneToCart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddItem, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNoShipOrTaxes, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTotal)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
					.addGap(521))
				.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
					.addGap(83)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblItemsInCart)
							.addGap(81))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblQuantity, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(20)
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblQuantity)
								.addComponent(lblItemsInCart))
							.addGap(6)
							.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(30)
							.addComponent(lblNoShipOrTaxes, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddItem)
								.addComponent(lblAddOneToCart)
								.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblTotal))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createSequentialGroup()
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnRemoveItem)
									.addComponent(lblRemoveItem))
								.addGap(9)
								.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
									.addComponent(btnEmptyCart)
									.addComponent(lblEmptyCart)))))
					.addContainerGap(209, Short.MAX_VALUE))
		);
		
		JListCartQuantity = new JList<Object>();
		scrollPane_1.setViewportView(JListCartQuantity);
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

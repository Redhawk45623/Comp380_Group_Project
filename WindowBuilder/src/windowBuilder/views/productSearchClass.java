/**
 * 
 * 
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

/**
 * 
 * 
 * 
 * 
 * 
 * 
 * @author Ralph Ramirez
 *
 */
public class productSearchClass extends JPanel {
	
	public Object[] products; //array that is loaded from file containing Product Names, used to load combobox
	public Object[] productIDs; //array that is loaded from read file containing Product IDs, used to load combobox
	public static Object[] prices = new String[20]; //array that is loaded from file containing prices, used to load combobox
	public static int[] prices2 = new int[20]; ////used to track the order of indexes that were added to the shopping list
	private int[] priceArray = new int[50]; //array used to store the prices in order of added to the shopping list after pressing add to list button
	
	public static DefaultListModel ToProductSearchList_items_1; //DefaultListModel list used to create list containing items added to the Search List
	public static DefaultListModel CartList_items_2; //DefaultListModel list used to create list containing items added Cart List
	public static DefaultListModel ToCartShopList_items_3; //DefaultListModel list used to create list containing items added Cart List from Shop List
	
	private JComboBox cbProducts_1; //Combobox that lists all products for sale
	
	private boolean check = false; // boolean variable that controls visibility to see the product image (rdbtnSeeImage)
	private boolean check2 = false; // boolean variable that controls visibility to see and use the the Shpping List (rdbtnUseList)
	private boolean check3 = false; // boolean variable that controls visibility to see the product description (rdbtnSeeDescription)
		
	public static int add = 0; //incremented variable used as index for priceArray, used for btnAddList_1 action event 
	private int sum; //incremented variable used as index for addPrices() 
	private int counter = 0; //incremented variable used as index for addPrices() 
	private int number; ////incremented variable used as index for createEvents -> btnAddList_1
	
	private JPanel panel_1; //panel that is used to hold all elements for the Shopping List option
	private JPanel panel_2; //panel that is used to hold the btnAddCart button and lblAdd2Cart label
	private JPanel panel_3; //panel that is used to hold the image diaplay area
	private JPanel panel_4; //panel that is used to hold the product desciption area
	
	private JScrollPane scrollPane; //scrollpane element for the Shopping List
	
	private JButton btnAddList_1; //button to add products from combobox to the Shopping List
	private JButton btnAddCart; //button to add products straight to cart (radio button -> rdbtnUseList not selected)
	private JButton btnAddAll; //button used to add all contents of Shopping List to Cart
	private JButton btnRemoveAll; //button to remove all contents of the Shopping List
	private JButton btnAddOneToCart; //button to add one selected item from Shopping List to Cart
	
	private JLabel lblRemoveAllFromList;  ///////////////////
	private JLabel lblNewLabel;	          //               //
	private JLabel lblNoShipAndTaxes;     //  all labels   //
	private JLabel lblAddAllToList;       //               //
	private JLabel lblAddOneToList;       ///////////////////
	
	private JList JListShopList_1; //JList element for Shopping List
	
	private JRadioButton rdbtnUseList; //radio button used to list the Shopping List option
	private JRadioButton rdbtnSeeImage; //radio button to see the product image
	private JRadioButton rdbtnSeeDescription; //radio button to see the product image
	
	private JTextPane txtpnProductDescription; //text pane for product description
	
	private JTextArea textAreaTotal_1; //text area to display the total cost of the products added to the Shopping List
	
	/**
	 * 
	 * 
	 * 
	 * Create the panel.
	 */
	public productSearchClass() throws FileNotFoundException{
		
		initComponents(); //calls initComponents() 
		createEvents(); //calls createEvents();
		loadArrayFromTxtFile(); //calls loadArrayFromTxtFile()
		loadCombobox();	//calls loadCombobox()
		
		panel_1.setVisible(false); //hides all elements in panel 1 (Shopping List option)
		panel_2.setVisible(true); //reveals all elemnts in panel_2 (btnAddCart button and associated label)
		panel_3.setVisible(false); //hides the elements in panel_3 (display image area)
		panel_4.setVisible(false); //hides the elements in panel_4 (product description area)
		
	}
		
	/**
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadArrayFromTxtFile() throws FileNotFoundException { //method that loads the combobox
		
		String namepath = "/Users/Zeina/Desktop/productNames.txt"; //path for the Product Names file
		File file = new File(namepath);
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			products = br.lines().toArray(); //loads products[] with Product Names 
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String IDpath = "/Users/Zeina/Desktop/productIDs.txt"; //path for the Product IDs file
		File file2 = new File(IDpath);
			
		try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
			productIDs = br2.lines().toArray(); //loads Product IDs[] with Product IDs
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pricespath = "/Users/Zeina/Desktop/prices.txt"; //path for Product Prices
		File file3 = new File(pricespath);
			
		try (BufferedReader br3 = new BufferedReader(new FileReader(file3))) {
			prices = br3.lines().toArray(); //loads prices[] with Product Prices
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 */
	public void addPrices(int x) { //method to add up the prices
		
		Object first = prices[x]; //this creates an object variable that is initialized from the passed parameter/prices[]
		int second = Integer.parseInt(first.toString()); //this converts the object to integer
		priceArray[counter] = second; //this loads the priceArray[]
		counter++; //increments counter variable
		sum = 0; //sets the variable initially to 0
		for(int i = 0; i < priceArray.length; i++){ //loop to add up the total price that is in priceArray
		sum += priceArray[i]; //adds up the priceArray[] and stores it in the variable sum
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void loadCombobox() { //method to load the combobox
		
		for(int i = 0; i < products.length; i++) { //method loop
			String line = productIDs[i].toString(); //pulls Object element from productIDs[] and converts to String variable line
			String line2 = products[i].toString(); //pulls Object element from products[] and converts to String variable line2
			cbProducts_1.addItem(line + " - " + line2 + " - " + "Price: " + "$" + prices[i] + ".00"); //loads the JComboBox cbProducts_1 with Products IDs, Product Names, and Prices
			
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void setPriceTotal() {
		
		textAreaTotal_1.setText(""); //clears text from textAreaTotal
		String z = Integer.toString(sum); //converts integer to String needed to display in textAreaTotal box
		textAreaTotal_1.append("$" + z + ".00"); //displays the current total price from the shopping list in the textAreaTotal box
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param <T>
	 * @param from
	 * @param to
	 */
	protected static <T> void addTo(ListModel<T> from, DefaultListModel<T> to) { //method not used 
	    for (int index = 0; index < from.getSize(); index++) {
	        to.addElement(from.getElementAt(index));
	    }
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void createEvents() { //method that stores all action events
		
		btnAddList_1.addActionListener(new ActionListener() { //button action method that adds item from combobox to Shopping List
			public void actionPerformed(ActionEvent e) {				
				
				int send = cbProducts_1.getSelectedIndex(); //creates variable send to pass to addPrices() method from cbProducts_1
				
				prices2[add] = send; //used to track the order of indexes that were added to the shopping list
				number = prices2[add];  //assigns variable d the index stored in prices2 array starting at index 0
				add++; //increments the add variable for next use
				
				addPrices(send); //calls addPrices method				
				setPriceTotal(); //calls setPriceTotal method			
				
				ToCartShopList_items_3.addElement(cbProducts_1.getSelectedItem()); 				
				ToProductSearchList_items_1.addElement(cbProducts_1.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel items
				JListShopList_1.setModel(ToProductSearchList_items_1); //this lists the selected DefaultListModel items in the JListShopList shopping list
							
				//JOptionPane.showMessageDialog(null,S);
				
			} 
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddCart.addActionListener(new ActionListener() { //button action method that adds product from combobox to Cart
			public void actionPerformed(ActionEvent e) {
				
				int index = cbProducts_1.getSelectedIndex(); //creates variable index to pass to addPrices() method
				cartClass.addCartprice(index); //calls the	addCartprice() from cartClass with index variable as parameter			
				cartClass.setCartPriceTotal(); //calls the setCartPriceTotal() from cartClass			
				CartList_items_2.addElement(cbProducts_1.getSelectedItem()); //adds the product stored in combobox (cbProducts_1) to DefaultListModel -> CartList_items_2
				cartClass.JListCartList.setModel(CartList_items_2); //places the items in the cart (cartClass.JListCartList) from CartList_items_2
				JOptionPane.showMessageDialog(null,"Added selected item to Cart!"); //Displays a pop-up message
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddOneToCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddAll.addActionListener(new ActionListener() { //button action method that adds all selected Products from Shopping List to Cart
			public void actionPerformed(ActionEvent e) {
								
				int begn = 0; //sets the beginning point of the selection value
				int end = JListShopList_1.getModel().getSize() - 1; //sets the end point of the selection value
				if (end >= 0) { //loop to select all items in the shopping using begn and end variables
					JListShopList_1.setSelectionInterval(begn, end); //selects all items in the Shopping List (JListShopList_1) at once
					
				}
				
				int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates an array that stores the selected items in the Shopping List
				
				for (int i = 0; i < selectedIx.length; i++) { //loop 
					
					int temp = prices2[i]; //sets variable temp for each element in prices[]
					cartClass.addCartprice(temp); //calls addCartprice() from cartClass passing u variable as a parameter
					cartClass.setCartPriceTotal(); //calls setCartPriceTotal() from cartClass		
					
				}
				
				cartClass.JListCartList.setModel(ToCartShopList_items_3); //sets the Cart List (JListCartList)  in cartClass with all items from DefaultListModel ToCartShopList_items_3
				ToProductSearchList_items_1.clear(); //clears all items from DefaultListModel -> ToProductSearchList_items_1
				textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; //resets the priceArray[]
				JOptionPane.showMessageDialog(null,"Moved all items in shopping list to Cart!"); //displays pop-up message
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnRemoveAll.addActionListener(new ActionListener() { //button action method that removes all selected Products in Shopping List
			public void actionPerformed(ActionEvent e) {
				
				ToProductSearchList_items_1.removeAllElements(); //this clears all elements from DefaultListModel -> ToProductSearchList_items_1
				textAreaTotal_1.setText("$0.00"); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; //this resets the priceArray[]
			}
		});
	
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		rdbtnUseList.addActionListener(new ActionListener() { //radio button action method that toggles on or off the Shopping List 
			public void actionPerformed(ActionEvent e) {
				
				textAreaTotal_1.setText("$0.00"); //sets the inital value of Shopping List (textAreaTotal_1) upon first use
				
				if (check2 == false) { //if check2 is false (off), run the code				
					panel_1.setVisible(true); //turn on panel_1 (Shopping List option)
					check2 = true; //set the check2 variable to true (on)				
					panel_2.setVisible(false); //turn off panel_2 (btnAddCart button and associated label)				
				}else {	//if check2 is true (on)				
					panel_1.setVisible(false); //turn off panel_1 (Shopping List option)
					check2 = false;	 //set the check2 variable to false (off)				
					panel_2.setVisible(true); //turn on panel_2 (btnAddCart button and associated label)
				}				
				
			}
		});
		
		rdbtnSeeDescription.addActionListener(new ActionListener() { //radio button action method that togles on or off the Product Desciption area
			public void actionPerformed(ActionEvent e) {
				
				if (check3 == false) {	//if check3 is false (off), run the code				
					panel_4.setVisible(true); //turn on panel_4 (product description area)
					check3 = true; //set the check3 variable to true (on)									
				}else { //if check3 is true (on)
					panel_4.setVisible(false); //turn off panel_4 (product description area)
					check3 = false;	 //set the check3 variable to false(off)									
				}
				
			}
		});
		
		rdbtnSeeImage.addActionListener(new ActionListener() { //radio button action method that toggles on or off the See Image area
			public void actionPerformed(ActionEvent e) {
				
				if (check == false) {	//if check is false (off), run the code				
					panel_3.setVisible(true); ////turn on panel_3 (display image area)
					check = true;	//set the check variable to true (on)								
				}else {//if check is true (on)
					panel_3.setVisible(false); //turn off panel_3 ((display image area)
					check = false; //set the check variable to false(off)										
				}
				
			}
		});
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	@SuppressWarnings("unchecked")
	private void initComponents() throws FileNotFoundException { ////method that stores components
		
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(9, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		cbProducts_1 = new JComboBox();
		cbProducts_1.setMaximumRowCount(10);
		
		ToProductSearchList_items_1= new DefaultListModel();
		CartList_items_2= new DefaultListModel();
		ToCartShopList_items_3 = new DefaultListModel();
		
		JLabel lblProducts = new JLabel("Available Products:");
		
		panel_1 = new JPanel();
		
		rdbtnUseList = new JRadioButton("Shop using List");
		
		panel_2 = new JPanel();
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 302, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(52, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(262, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnUseList)
					.addContainerGap(381, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnUseList)
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducts)
						.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblAdd2Cart = new JLabel("Add to Cart:");
		
		btnAddCart = new JButton("Add To Cart");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_2.createSequentialGroup()
					.addComponent(lblAdd2Cart)
					.addGap(6)
					.addComponent(btnAddCart, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_2.createSequentialGroup()
							.addGap(5)
							.addComponent(lblAdd2Cart))
						.addComponent(btnAddCart))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btnAddAll = new JButton("Add All");
		
		btnRemoveAll = new JButton("Remove All");
		
		lblRemoveAllFromList = new JLabel("    Remove All from List:");
		
		lblNewLabel = new JLabel("Total = ");
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNoShipAndTaxes = new JLabel("*Does not include S&H or taxes");
		lblNoShipAndTaxes.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		btnAddList_1 = new JButton("Add Now");
		
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		
		btnAddOneToCart = new JButton("Add One");
		
		lblAddAllToList = new JLabel("Add all from List:");
		
		lblAddOneToList = new JLabel("Add one from List:");
		
		panel_3 = new JPanel();
		
		rdbtnSeeDescription = new JRadioButton("See Description");
		
		rdbtnSeeImage = new JRadioButton("See Image");
		
		panel_4 = new JPanel();
	
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblAdd2List)
										.addComponent(lblAddOneToList)
										.addComponent(lblAddAllToList)
										.addComponent(lblRemoveAllFromList, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE))
									.addGap(4))
								.addComponent(rdbtnSeeDescription)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(rdbtnSeeImage)
									.addGap(35))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnAddList_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAddOneToCart, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
								.addComponent(btnAddAll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNoShipAndTaxes, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))
						.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(6)
							.addComponent(panel_3, 0, 0, Short.MAX_VALUE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddList_1)
						.addComponent(lblAdd2List))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(49)
							.addComponent(rdbtnSeeDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnSeeImage))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(14)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnAddOneToCart)
						.addComponent(lblAddOneToList))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddAll)
								.addComponent(lblAddAllToList))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoveAll)
								.addComponent(lblRemoveAllFromList)))
						.addComponent(lblNoShipAndTaxes, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 211, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(45, Short.MAX_VALUE))
		);
		
		txtpnProductDescription = new JTextPane();
		txtpnProductDescription.setText("This is a desciption of an item that will give general details and information for that product. The information will be displayed here if the description radio box is selected.");
		txtpnProductDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnProductDescription, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(15, Short.MAX_VALUE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(17)
					.addComponent(txtpnProductDescription, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(19, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
		
		JLabel lblTempLabelForImage = new JLabel("Display Image Here");
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(77, Short.MAX_VALUE)
					.addComponent(lblTempLabelForImage)
					.addGap(74))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(94, Short.MAX_VALUE)
					.addComponent(lblTempLabelForImage)
					.addGap(89))
		);
		panel_3.setLayout(gl_panel_3);
		
		JListShopList_1 = new JList();
		scrollPane.setViewportView(JListShopList_1);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
}

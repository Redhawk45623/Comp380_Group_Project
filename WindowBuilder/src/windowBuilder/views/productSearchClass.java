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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;
import javax.swing.AbstractListModel;
import javax.swing.SwingConstants;

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
	
	private static final long serialVersionUID = 1L;
	public Object[] products; //array that is loaded from file containing Product Names, used to load combobox
	public Object[] productIDs; //array that is loaded from read file containing Product IDs, used to load combobox
	public static Object[] prices = new String[20]; //array that is loaded from file containing prices, used to load combobox
	public static int[] prices2 = new int[20]; ////used to track the order of indexes that were added to the shopping list
	public static int[] priceArray = new int[50]; //array used to store the prices in order of added to the shopping list after pressing add to list button
	public static int[] imagesIndex = new int[20];
	public static Object[] descriptionsArray = new String[10];
	public static Integer[] trackImages = new Integer[10];
	
	public static DefaultListModel<Object> ToProductSearchList_items_1; //DefaultListModel list used to create list containing items added to the Search List
	public static DefaultListModel<Object> ToCartShopList_items_3; //DefaultListModel list used to create list containing items added to Cart List from Shop List
	public static DefaultListModel<Object> ToQuantityList_items_4;
	
	private JComboBox<String> cbProducts; //Combobox that lists all products for sale
	
	private boolean check = false; // boolean variable that controls visibility to see the product image (rdbtnSeeImage)
	private boolean check2 = false; // boolean variable that controls visibility to see and use the the Shpping List (rdbtnUseList)
	private boolean check3 = false; // boolean variable that controls visibility to see the product description (rdbtnSeeDescription)
	private boolean addedOne = false;
	private boolean checkRepeats  = false;
	private boolean checkCartRepeats = false;
	
	public static int add = 0; //incremented variable used as index for priceArray, used for btnAddList_1 action event 
	private static int sum; //incremented variable used as index for addPrices() 
	private static int total;
	private static int grandtotal;
	private static int counter = 0; //incremented variable used as index for addPrices() 
	private int track = 0;
	public static int quantAdded;
	
	private JPanel panel_1; //panel that is used to hold all elements for the Shopping List option
	private JPanel panel_2; //panel that is used to hold the btnAddCart button and lblAdd2Cart label
	private JPanel panel_3; //panel that is used to hold the image display area
	private JPanel panel_4; //panel that is used to hold the product desciption area
	
	private JScrollPane scrollPaneShopList; //scrollpane element for the Shopping List
	private JScrollPane scrollPaneQuantity;
	
	private JButton btnAddToList; //button to add products from combobox to the Shopping List
	private JButton btnAddCart; //button to add products straight to cart (radio button -> rdbtnUseList not selected)
	private JButton btnAddAllToCart; //button used to add all contents of Shopping List to Cart
	private JButton btnRemoveAll; //button to remove all contents of the Shopping List
	private JButton btnAddOneToList; //button to add one selected item from Shopping List to Cart
	private JButton btnRemoveOneItem;
	
	private JLabel lblRemoveAllFromList;  ///////////////////
	private JLabel lblNewLabel;	          //               //
	private JLabel lblAddAllToList;       //  ALL LABELS   //
	private JLabel lblAddOneToList;       //               //
	public static JLabel displayLabel;    //               //
	private JLabel lblNewLabel_2;         //               //
	private JLabel lblProductDescrip;     //               //
	private JLabel lblImageDescrip;       //               //
	private JLabel lblNoShipNotaxes;      ///////////////////
	
	private JList<Object> JListShopList_1; //JList element for Shopping List
	private JList<Object> JListQuantity;
	
	private JRadioButton rdbtnUseList; //radio button used to list the Shopping List option
	private JRadioButton rdbtnSeeImage; //radio button to see the product image
	private JRadioButton rdbtnSeeDescription; //radio button to see the product image
	
	public static JTextPane txtpnProductDescription; //text pane for product description
	
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
		loadProductsFromTxtFile(); //calls loadArrayFromTxtFile(), reads .txt files for product arrays
		loadProductCombobox();	//calls loadCombobox()
		loadProductDescriptions(); //calls loadProductDescriptions()
		
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
	public void loadProductsFromTxtFile() throws FileNotFoundException { //method that loads the combobox
		
		java.net.URL url = getClass().getResource("/productDatabase/productNames.txt");
		File file = new File(url.getPath());
		//String namepath = "/Users/Zeina/Desktop/productNames.txt"; //path for the Product Names file stored on my computer locally
		//File file = new File(namepath);
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			products = br.lines().toArray(); //loads products[] with Product Names 
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.net.URL url2 = getClass().getResource("/productDatabase/productIDs.txt");
		File file2 = new File(url2.getPath());
		//String IDpath = "/Users/Zeina/Desktop/productIDs.txt"; //path for the Product IDs file stored on my computer locally
		//File file2 = new File(IDpath);
			
		try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
			productIDs = br2.lines().toArray(); //loads Product IDs[] with Product IDs
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.net.URL url3 = getClass().getResource("/productDatabase/prices.txt");
		File file3 = new File(url3.getPath());
		//String pricespath = "/Users/Zeina/Desktop/prices.txt"; //path for Product Prices file stored on my computer locally
		//File file3 = new File(pricespath);
			
		try (BufferedReader br3 = new BufferedReader(new FileReader(file3))) {
			prices = br3.lines().toArray(); //loads prices[] with Product Prices
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void loadProductDescriptions() throws FileNotFoundException { //method used to load the Descriptions of Products into array
		
		int i; //declares int i
		java.net.URL url = getClass().getResource("/productDescriptions/descriptions.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		File file = new File(url.getPath()); //creats a File Object from the 'url' variable
		
		for (i = 0; i < descriptionsArray.length; i++) { //for loop using the length of descriptionsArray[]
			try (BufferedReader br = new BufferedReader(new FileReader(file))) { //BufferedReader to begin reading lines of descriptions.txt
				descriptionsArray = br.lines().toArray(); //loads the descriptionsArray[] with each line from the descriptions.txt file
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public String setDescriptions(int x) { //method used to return a description String using parameter 'x' descriptionsArray.  Converts to String too
		
		String descrip = descriptionsArray[x].toString();
		return descrip;
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 */
	public void setImageIndex(Integer number) {
				
		trackImages[track] = number;
		track++;
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 */
	public void removeImageIndex(Integer number) { //'number' = the index of the image that is to be removed
		
		ArrayList<Integer> arr_new = new ArrayList<>();
		
		for (int i=0; i<trackImages.length; i++) {	
			
			if (trackImages[i]!=number) {				
				arr_new.add(trackImages[i]);				
			}
			
		}
		
		trackImages = arr_new.toArray(new Integer[0]);
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 * @throws IOException
	 */
	public void loadImages(int number) throws IOException { //method used to load panel_3 (Image Display Panel) and panel_4 (Product Description Panel)
		
		String display; //string variable that is used in switch/case below
		
		switch (number) { //switch case that chooses which image and description are displayed
		  case 0:	
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/tv.jpg"))); //dispays the image in panel_3			  		  
			  display = setDescriptions(number); //dispays the description in panel_4 based on the value of the parameter: 'number'
			  lblImageDescrip.setText("    Vintage TV"); //sets the small label used in the image display area (panel_3)
			  txtpnProductDescription.setText(display);	//sets the desciption String to be displayed in panel_4	  		  
			  break;
		  case 1:		  
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera.jpg"))); 
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Vintage Camera");
			  txtpnProductDescription.setText(display);
			  break;
		  case 2:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/atari.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Atari Games");
			  txtpnProductDescription.setText(display);
			  break;
		  case 3:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/boombox.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("1980s Boombox");
			  txtpnProductDescription.setText(display);
			  break;
		  case 4:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/phone.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Rotary Phone");
			  txtpnProductDescription.setText(display);
			  break;
		  case 5:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/cassette.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Cassette Tape");
			  txtpnProductDescription.setText(display);
			  break;
		  case 6:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera2.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Film Camera");
			  txtpnProductDescription.setText(display);
			  break;
		  case 7:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/top.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Vintage Top");
			  txtpnProductDescription.setText(display);
			  break;
		  case 8:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/sign.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Rustic Sign");
			  txtpnProductDescription.setText(display);
			  break;
		  case 9:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/nintendo.jpg")));
			  display = setDescriptions(number);
			  lblImageDescrip.setText("Nintendo Console");
			  txtpnProductDescription.setText(display);
			  break;
		
		}
					
		
	}		
		
		//JOptionPane.showMessageDialog(null,displayLabel);	
		
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 */
	public void addPrices(int x) { //method to add up the prices
		
		Object first = prices[x]; //this creates an object variable that is initialized from the passed parameter, used to get selected index and match to price
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
	 * @param x
	 */
	public void addPrices2(int x) { //method to add up the prices after adding a quantity of one from the shopping list
		
		grandtotal = x + grandtotal; //sets variable: grandtotal to the sum of grandtotal and the amount of the passed variable 'x'
		priceArray[counter] = x; //this loads the priceArray[]
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
	public void loadProductCombobox() { //method to load the combobox
		
		for(int i = 0; i < products.length; i++) { //method loop
			String line = productIDs[i].toString(); //pulls Object element from productIDs[] and converts to String variable line
			String line2 = products[i].toString(); //pulls Object element from products[] and converts to String variable line2
			cbProducts.addItem(line + " - " + line2 + " - " + "Price: " + "$" + prices[i] + ".00"); //loads the JComboBox cbProducts_1 with Products IDs, Product Names, and Prices
			
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void setPriceTotal() { //method that sets the total price text area (textAreaTotal_1) in Shopping List
		
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
	protected static <T> void addTo(ListModel<T> from, DefaultListModel<T> to) { //method used to add one ListModel to another DefaultListModel
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
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		MouseListener mouseListener = new MouseAdapter() { //mouse action listener to detect when a user clicks on an item in the Shopping List
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) { //detects only one click
        	           
		            int x = 0;		
					int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates an array that stores the index of the clicked on product. Will be only one index			    
					int image = selectedIx[x]; //assigns temp variable: 'image' to the index found at selectedIx[x]					 
					image = trackImages[image]; //sets the temp variable: 'image' to the index found at trackImages[image]
					//JOptionPane.showMessageDialog(null,image);
						try {
							loadImages(image); //calls the loadImages() using the temp variable: 'image' (index of trackImages[image]) to display correct image in panel_3
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

		        }	        
		        
		    }
		};
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */		
		btnAddToList.addActionListener(new ActionListener() { //button action method that adds item from combobox to Shopping List
			public void actionPerformed(ActionEvent e) {
				
				checkRepeats = false; //sets the boolean to false when button is pressed. This is used to check if the selected product has already been added to Shopping List
				addedOne = true; //sets the boolean variable:'addedOne' to true.  This is to establish at least one product has been added to the Shopping List 
				
				int cbIndex = cbProducts.getSelectedIndex(); //creates variable send to pass to addPrices() method from cbProducts_1					
				String verify = cbProducts.getItemAt(cbIndex); //sets variable: 'verify' to the selected product usine variable: 'cbIndex'
				
				for (int i=0; i<JListShopList_1.getModel().getSize(); i++) {
					
					if (ToProductSearchList_items_1.get(i) == verify) {
						
						checkRepeats = true;
						JOptionPane.showMessageDialog(null,"Already Added to Shopping List");
						
					}
										
				}
				//JOptionPane.showMessageDialog(null,verify);
				
				if (checkRepeats == false) {
					
					setImageIndex(cbIndex); //uses cbIndex variable as a parameter to call setImageIndex().  This tracks the order of indexes to be used for the description and image display
						
					try {
						loadImages(cbIndex); //calls the loadImages() using 'cbIndex' as a parameter
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			
					prices2[add] = cbIndex; //used to track prices
					add++; //increments the add variable for prices[] for the next use				
					addPrices(cbIndex); //calls addPrices method				
					setPriceTotal(); //calls setPriceTotal method							
					ToCartShopList_items_3.addElement(cbProducts.getSelectedItem()); //	//This adds the selected element from cbProducts to DefaultListModel ToCartShopList_items_3			
					ToProductSearchList_items_1.addElement(cbProducts.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel ToProductSearchList_items_1
								
					ToQuantityList_items_4.addElement("1"); //adds a 1 to the DefaultListModel: 'ToQuantityList_items_4'
					JListQuantity.setModel(ToQuantityList_items_4); //sets the model of the Shopping List quantity box
					
					JListShopList_1.setModel(ToProductSearchList_items_1); //this lists the selected DefaultListModel items in the JListShopList shopping list							
					JListShopList_1.addMouseListener(mouseListener); //a listener that detects when a product is selected in the shopping list
					//JOptionPane.showMessageDialog(null,S);
				}
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
				
				checkCartRepeats = false; //always sets the boolean to false when button is pressed				
				cartClass.check = true; //always sets the boolean to true when button is pressed
				int index = cbProducts.getSelectedIndex(); //creates variable index to pass to addPrices() method.  Used to get the selected product from combobox			
				String verify = cbProducts.getItemAt(index); //sets variable: 'verify' to the selected product usine variable: 'index'
				
				for (int i=0; i<cartClass.JListCartList.getModel().getSize(); i++) { //for loop that checks the cartClass.CartList_items_2 for a product (variable: 'verify') already added to the Cart
					
					if (cartClass.CartList_items_2.get(i) == verify) { //if statement that looks for a product already added to the Cart
						
						checkCartRepeats = true; //if there is already a product a user is attempting to add again, set the boolean to true
						JOptionPane.showMessageDialog(null,"Already Added to Cart! Add quanity from Cart page..."); //display pop-up message
						
					}				
					
				}
				
				if (checkCartRepeats == false) { //if the boolean is false, run the code
					cartClass.addCartprice(index); //calls the	addCartprice() from cartClass with index variable as parameter			
					cartClass.setCartPriceTotal(); //calls the setCartPriceTotal() from cartClass
					
					cartClass.ToCartQuantityList_items_4.addElement("1"); //adds a 1 to the DefaultListModel: 'ToCartQuantityList_items_4' in the cartClass
					cartClass.JListCartQuantity.setModel(cartClass.ToCartQuantityList_items_4); //sets the model (cartClass quantity box)
					
					cartClass.CartList_items_2.addElement(cbProducts.getSelectedItem()); //adds the product stored in combobox (cbProducts_1) to DefaultListModel -> CartList_items_2
					cartClass.JListCartList.setModel(cartClass.CartList_items_2); //places the items in the cart (cartClass.JListCartList) from CartList_items_2
					JOptionPane.showMessageDialog(null,"Added selected item to Cart!"); //Displays a pop-up message
				}
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddOneToList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (! JListShopList_1.isSelectionEmpty()){ //if an item is selected in the Shopping List, then run the following code
					
					int x = 0;		
					int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates a temp array that stores the selected index in the Shopping List. Will only be one item in array			    
					int image = selectedIx[x]; //assigns temp variable image to the element at index 0 from temp array selectedIx
					//Object product = JListShopList_1.getSelectedValue(); //gets the selected product info and sets it to the Object variable: product. Used in the pop-up message
					image = trackImages[image];	//assigns the temp variable 'image' the index stored at trackImages[] using the index: 'image' established from the code before				
					Object productPrice = prices[image]; //
					
					total = Integer.parseInt(productPrice.toString()); //converts the int variable: 'total' to an int from the Object variable: 'productPrice'	
					addPrices2(total); //calls addPrices2() to add up the total in Shopping List after a quantity has been added
					setPriceTotal(); //calls setPriceTotal method	
				
					int index = selectedIx[x]; ////assigns temp variable: 'index' to the element at index 0 from temp array selectedIx
					Object number = ToQuantityList_items_4.getElementAt(index); //sets Object variable: 'number' to the price stored at ToQuantityList_items_4
					int convertedNumber2 = Integer.parseInt(number.toString()); //converts the Object: 'number' to int: 'convertedNumber2'
					int addedUp = convertedNumber2+ 1; //adds the price of convertedNumber2	+ 1		
					ToQuantityList_items_4.setElementAt(addedUp, index); //sets the quantity displayed in quantity box for selected item at the right spot
					//JOptionPane.showMessageDialog(null, "Added one more -> " + products[image]); //pop-up message displaying the product which the user added a quantity
				}
				
				else { //if the button is pushed and nothing is selected in the Shopping List, display the pop-up message
					
					JOptionPane.showMessageDialog(null, "Please select an item to add!"); //displays pop-up message
				}
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddAllToCart.addActionListener(new ActionListener() { //button action method that adds all selected Products from Shopping List to Cart
			public void actionPerformed(ActionEvent e) {
				
				cartClass.check = false;
				
				int begn = 0; //sets the beginning point of the selection value
				int end = JListShopList_1.getModel().getSize() - 1; //sets the end point of the selection value
				if (end >= 0) { //loop to select all items in the shopping using begn and end variables
					JListShopList_1.setSelectionInterval(begn, end); //selects all items in the Shopping List (JListShopList_1) at once
					
				}
				
				int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates an array that stores the selected items in the Shopping List				
				
				if (! JListShopList_1.isSelectionEmpty()){ //checks to see if there is at least one item in the Shopping List to add to Cart
					for (int i = 0; i < selectedIx.length; i++) { //loop 
					
						int temp = prices2[i]; //sets variable temp for each element in prices[]
						cartClass.addCartprice(temp); //calls addCartprice() from cartClass passing u variable as a parameter
						cartClass.setCartPriceTotal(); //calls setCartPriceTotal() from cartClass		
					
					}
					
					quantAdded = cartClass.sum + grandtotal; //sets temp variable: 'quantAdded' to the total of 'cartClass.sum' and 'grandtotal'
					
					cartClass.textAreaCartTotal.setText(""); //resets the textAreaCartTotal in case there has been a quantity added
					cartClass.textAreaCartTotal.append("$" + quantAdded + ".00"); //sets the textAreaCartTotal to the variable: 'quantAdded' to account for any added items from the Shopping List				
					addTo(ToQuantityList_items_4 , cartClass.ToCartQuantityList_items_4); //calls the addTo() which takes a list 'from' and adds it 'to' another list. Basically, sets the quanity in the cart from Shopping List								
					cartClass.JListCartList.setModel(ToCartShopList_items_3); //sets the Cart List (JListCartList)  in cartClass with all items from DefaultListModel ToCartShopList_items_3
					
					ToQuantityList_items_4.removeAllElements(); //clears out the quantity box from the Shoppong List
					ToProductSearchList_items_1.clear(); //clears all items from DefaultListModel -> ToProductSearchList_items_1
					textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
					priceArray = new int[50]; //resets the priceArray[]
					txtpnProductDescription.setText(null); //resets the description area
					displayLabel.setIcon(null); //resets the display image area
					//JOptionPane.showMessageDialog(null,"Moved all items in shopping list to Cart!"); //displays pop-up message
				}
				else { //if there is nothing in the Shopping List, then display pop-up message
					
					JOptionPane.showMessageDialog(null,"Please add products to the Shopping List first!"); //displays pop-up message
				}
				
			}
		});
		
		btnRemoveOneItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (! JListShopList_1.isSelectionEmpty()){
				
					int x = 0;		
					int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates a temp array that stores the selected index in the Shopping List. Will only be one item in array			    
					int image = selectedIx[x]; //assigns temp variable image to the element at index 0 from temp array selectedIx					
					int productPrice = priceArray[image]; //sets int variable:'productPrice' to the price found at priceArray[image]
					
					int w = trackImages[image];
					removeImageIndex(w);
					txtpnProductDescription.setText(null); //resets the description area
					displayLabel.setIcon(null); //resets the display image area
										
					ToCartShopList_items_3.removeElementAt(image);
					ToProductSearchList_items_1.removeElementAt(image); //removes the Product from Shopping List at index specified by variable: 'index'
					ToQuantityList_items_4.removeElementAt(image); //removes the quantity amounf from quantity box at index specified by variable: 'index'
					
					sum = sum - productPrice;
					textAreaTotal_1.setText("$" + sum + ".00"); 
					
					if (ToProductSearchList_items_1.isEmpty()) {
						
						textAreaTotal_1.setText("$0.00"); //this resets the textAreaTotal box back to empty
						
					}					
					//JOptionPane.showMessageDialog(null,sum);					
				}
				else {
					
					JOptionPane.showMessageDialog(null,"Please select an item to remove!");
					
				}
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
				
				if ( addedOne == true){ //checks to see if at least one product is in the Shopping List before trying to clear the Shopping List
					
					addedOne = false; //resets the boolean variable: 'addedOne' to false. this establishes that no product has been added to the Shopping List
					txtpnProductDescription.setText(null); //clears out the description text area
					displayLabel.setIcon(null); //clears out the product image from panel_03
					ToQuantityList_items_4.removeAllElements(); //clears out the DefaultListModel -> ToQuantityList_items_4 (numbers displayed in the quanity box)
					ToProductSearchList_items_1.removeAllElements(); //this clears all elements from DefaultListModel -> ToProductSearchList_items_1
					textAreaTotal_1.setText("$0.00"); //this resets the textAreaTotal box back to empty
					priceArray = new int[50]; //this resets the priceArray[]
					trackImages = new Integer[20]; //this resets the trackImages[]
					track = 0; //resets track variable to 0
					JOptionPane.showMessageDialog(null,"Removed all products from Shopping List"); //displays pop-up message 
					
				}
				else { //if there is nothing added to the Shopping List yet...
					
					JOptionPane.showMessageDialog(null,"Nothing in Shopping List to remove!"); //displays pop-up message
					
				}
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
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
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
		/**
		 * 
		 * 
		 * 
		 * 
		 */
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
		
		cbProducts = new JComboBox<String>();
		cbProducts.setMaximumRowCount(10);
		
		ToProductSearchList_items_1= new DefaultListModel<Object>();
		ToCartShopList_items_3 = new DefaultListModel<Object>();
		ToQuantityList_items_4 = new DefaultListModel<Object>();
		
		JLabel lblProducts = new JLabel("Products:");
		lblProducts.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Gear.png")));
		
		panel_1 = new JPanel();
		
		rdbtnUseList = new JRadioButton("Shop using List with Description and Image options");
		
		panel_2 = new JPanel();
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_2, GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProducts)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addComponent(rdbtnUseList))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnUseList)
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducts))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 556, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(12, Short.MAX_VALUE))
		);
		
		JLabel lblAdd2Cart = new JLabel("Add to Cart:");
		
		btnAddCart = new JButton("Add To Cart");
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2.setHorizontalGroup(
			gl_panel_2.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addContainerGap(274, Short.MAX_VALUE)
					.addComponent(lblAdd2Cart)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAddCart, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.addGap(31))
		);
		gl_panel_2.setVerticalGroup(
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_2.createSequentialGroup()
					.addGroup(gl_panel_2.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddCart)
						.addComponent(lblAdd2Cart))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel_2.setLayout(gl_panel_2);
		
		scrollPaneShopList = new JScrollPane();
		scrollPaneShopList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btnAddAllToCart = new JButton("Add All");
		
		btnRemoveAll = new JButton("Remove All");
		
		lblRemoveAllFromList = new JLabel("Remove all:");
		lblRemoveAllFromList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Remove from basket.png")));
		
		lblNewLabel = new JLabel("Total = ");
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btnAddToList = new JButton("Add Now");
		
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		lblAdd2List.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Price list.png")));
		
		btnAddOneToList = new JButton("Add One");
		
		lblAddAllToList = new JLabel("Add all to Cart:");
		lblAddAllToList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/basket2.png")));
		
		lblAddOneToList = new JLabel("Add one to List:");
		lblAddOneToList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/basket2.png")));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		rdbtnSeeDescription = new JRadioButton("See Description");
		
		rdbtnSeeImage = new JRadioButton("See Image");
		
		panel_4 = new JPanel();
		
		scrollPaneQuantity = new JScrollPane();
		scrollPaneQuantity.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblQuantity = new JLabel("Qty:");
		
		btnRemoveOneItem = new JButton("Remove");
		
		lblNewLabel_2 = new JLabel("Remove one item:");
		lblNewLabel_2.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Remove from basket.png")));
		
		lblNoShipNotaxes = new JLabel("*Total does not include S&H or taxes");
		lblNoShipNotaxes.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
	
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(0)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(14)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblAddOneToList)
												.addComponent(lblAddAllToList)
												.addComponent(lblRemoveAllFromList)
												.addComponent(lblNewLabel_2))
											.addGap(4))
										.addComponent(rdbtnSeeDescription)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(rdbtnSeeImage)
											.addGap(35)))
									.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addGap(2)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
												.addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
													.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(btnRemoveOneItem)
														.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE))
													.addGap(18)
													.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel_1.createSequentialGroup()
													.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addComponent(btnAddAllToCart)
														.addComponent(btnAddOneToList))
													.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
													.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_1.createSequentialGroup()
															.addGap(12)
															.addComponent(lblNewLabel)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
														.addComponent(lblNoShipNotaxes, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE))
													.addGap(11))))
										.addGroup(gl_panel_1.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(scrollPaneShopList, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(lblQuantity, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(scrollPaneQuantity, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)))))
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(lblAdd2List)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddToList, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)))
					.addGap(21))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAdd2List)
								.addComponent(btnAddToList))
							.addGap(49)
							.addComponent(rdbtnSeeDescription)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnSeeImage)
							.addGap(47))
						.addComponent(scrollPaneShopList, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblQuantity)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPaneQuantity, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddOneToList)
						.addComponent(btnAddOneToList)
						.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(lblAddAllToList)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(btnAddAllToCart)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRemoveOneItem)
										.addComponent(lblNewLabel_2))
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnRemoveAll)
										.addComponent(lblRemoveAllFromList))
									.addGap(12)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(4)
							.addComponent(lblNoShipNotaxes, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(panel_3, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		JListQuantity = new JList<Object>();
		JListQuantity.setModel(new AbstractListModel<Object>() {
			private static final long serialVersionUID = 1L;
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		scrollPaneQuantity.setViewportView(JListQuantity);
		
		txtpnProductDescription = new JTextPane();
		txtpnProductDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblProductDescrip = new JLabel("Product Description");
		lblProductDescrip.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(txtpnProductDescription, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblProductDescrip, GroupLayout.PREFERRED_SIZE, 104, GroupLayout.PREFERRED_SIZE)
							.addGap(67))))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(10)
					.addComponent(lblProductDescrip, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtpnProductDescription, GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE)
					.addGap(49))
		);
		panel_4.setLayout(gl_panel_4);
	
		
		displayLabel = new JLabel("");
		
		lblImageDescrip = new JLabel("Product Image");
		lblImageDescrip.setHorizontalAlignment(SwingConstants.CENTER);
		lblImageDescrip.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(18, Short.MAX_VALUE)
					.addComponent(displayLabel, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addGap(14))
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addGap(54)
					.addComponent(lblImageDescrip, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblImageDescrip, GroupLayout.PREFERRED_SIZE, 13, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(displayLabel, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JListShopList_1 = new JList<Object>();
		JListShopList_1.setToolTipText("");
		scrollPaneShopList.setViewportView(JListShopList_1);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		
		
	}
}

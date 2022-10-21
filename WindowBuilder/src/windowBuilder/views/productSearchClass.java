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
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.ImageIcon;

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
	private int[] priceArray = new int[50]; //array used to store the prices in order of added to the shopping list after pressing add to list button
	public static int[] imagesIndex = new int[20];
	public static Object[] descriptionsArray = new String[10];
	public static int[] trackImages = new int[20];
	
	public static DefaultListModel<Object> ToProductSearchList_items_1; //DefaultListModel list used to create list containing items added to the Search List
	public static DefaultListModel<Object> ToCartShopList_items_3; //DefaultListModel list used to create list containing items added Cart List from Shop List
	
	private JComboBox<String> cbProducts_1; //Combobox that lists all products for sale
	
	private boolean check = false; // boolean variable that controls visibility to see the product image (rdbtnSeeImage)
	private boolean check2 = false; // boolean variable that controls visibility to see and use the the Shpping List (rdbtnUseList)
	private boolean check3 = false; // boolean variable that controls visibility to see the product description (rdbtnSeeDescription)
		
	public static int add = 0; //incremented variable used as index for priceArray, used for btnAddList_1 action event 
	private int sum; //incremented variable used as index for addPrices() 
	private int counter = 0; //incremented variable used as index for addPrices() 
	private int track = 0;
	
	private JPanel panel_1; //panel that is used to hold all elements for the Shopping List option
	private JPanel panel_2; //panel that is used to hold the btnAddCart button and lblAdd2Cart label
	private JPanel panel_3; //panel that is used to hold the image display area
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
	private JLabel lblAddOneToList;       //               //
	public static JLabel displayLabel;    ///////////////////
	
	private JList<Object> JListShopList_1; //JList element for Shopping List
	
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
		loadArrayFromTxtFile(); //calls loadArrayFromTxtFile(), reads .txt files for product arrays
		loadCombobox();	//calls loadCombobox()
		loadDescriptions();
		
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
	
	public void loadDescriptions() throws FileNotFoundException {
		
		int i;
		java.net.URL url = getClass().getResource("/productDescriptions/descriptions.txt");
		File file = new File(url.getPath());
		
		for (i = 0; i < descriptionsArray.length; i++) {
			try (BufferedReader br = new BufferedReader(new FileReader(file))) {
				descriptionsArray = br.lines().toArray();  
				//JOptionPane.showMessageDialog(null,descriptionsArray[i]);	
			} catch (FileNotFoundException e) {
			throw e;
			} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		}
		
	}
	
	public String setDescriptions(int x) {
		
		String descrip = descriptionsArray[x].toString();
		return descrip;
		
	}
	
	public void setImageIndex(int number) {
				
		trackImages[track] = number;
		track++;
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 * @throws IOException
	 */
	public void loadImages(int number) throws IOException {
		
		String display; //string variable that is used in switch/case below
		
		switch (number) { //switch case that chooses which image and description are displayed
		  case 0:	
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/tv.jpg")));			  
			  if (check3 == true) {				  
				  display = setDescriptions(number);
				  txtpnProductDescription.setText(display);
			  }			  
			  break;
		  case 1:		  
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 2:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/atari.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 3:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/boombox.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 4:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/phone.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 5:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/cassette.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 6:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera2.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 7:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/top.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 8:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/sign.jpg")));
			  display = setDescriptions(number);
			  txtpnProductDescription.setText(display);
			  break;
		  case 9:
			  displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/nintendo.jpg")));
			  display = setDescriptions(number);
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
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		MouseListener mouseListener = new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (e.getClickCount() == 1) {
        	           
		            int x = 0;		
					int[] selectedIx = JListShopList_1.getSelectedIndices(); //creates an array that stores the selected items in the Shopping List				    
					int image = selectedIx[x];					 
					image = trackImages[image];
					
						try {
							loadImages(image);
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
		btnAddList_1.addActionListener(new ActionListener() { //button action method that adds item from combobox to Shopping List
			public void actionPerformed(ActionEvent e) {				
				
				int send = cbProducts_1.getSelectedIndex(); //creates variable send to pass to addPrices() method from cbProducts_1	
				setImageIndex(send);
					
				try {
					loadImages(send);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				prices2[add] = send; //used to track prices
				add++; //increments the add variable for prices[] for the next use				
				addPrices(send); //calls addPrices method				
				setPriceTotal(); //calls setPriceTotal method							
				ToCartShopList_items_3.addElement(cbProducts_1.getSelectedItem()); //	//This adds the selected element from cbProducts to DefaultListModel ToCartShopList_items_3			
				ToProductSearchList_items_1.addElement(cbProducts_1.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel ToProductSearchList_items_1
				JListShopList_1.setModel(ToProductSearchList_items_1); //this lists the selected DefaultListModel items in the JListShopList shopping list							
				JListShopList_1.addMouseListener(mouseListener); //a listener that detects when a product is selected in the shopping list
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
				cartClass.CartList_items_2.addElement(cbProducts_1.getSelectedItem()); //adds the product stored in combobox (cbProducts_1) to DefaultListModel -> CartList_items_2
				cartClass.JListCartList.setModel(cartClass.CartList_items_2); //places the items in the cart (cartClass.JListCartList) from CartList_items_2
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
				txtpnProductDescription.setText(null); //resets the description area
				displayLabel.setIcon(null); //resets the display image area
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
				
				txtpnProductDescription.setText(null);
				displayLabel.setIcon(null);
				ToProductSearchList_items_1.removeAllElements(); //this clears all elements from DefaultListModel -> ToProductSearchList_items_1
				textAreaTotal_1.setText("$0.00"); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; //this resets the priceArray[]
				trackImages = new int[20];
				track = 0;
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
		
		cbProducts_1 = new JComboBox<String>();
		cbProducts_1.setMaximumRowCount(10);
		
		ToProductSearchList_items_1= new DefaultListModel<Object>();
		ToCartShopList_items_3 = new DefaultListModel<Object>();
		
		JLabel lblProducts = new JLabel("Products:");
		lblProducts.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Gear.png")));
		
		panel_1 = new JPanel();
		
		rdbtnUseList = new JRadioButton("Shop using List with Descriptions and Images options");
		
		panel_2 = new JPanel();
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(222, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
					.addGap(64))
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblProducts)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE))
						.addComponent(rdbtnUseList))
					.addContainerGap(34, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(rdbtnUseList)
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
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
			gl_panel_2.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_2.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addComponent(lblAdd2Cart)
					.addGap(6)
					.addComponent(btnAddCart, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
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
		
		lblRemoveAllFromList = new JLabel("Remove all:");
		lblRemoveAllFromList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Remove from basket.png")));
		
		lblNewLabel = new JLabel("Total = ");
		lblNewLabel.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Dollar.png")));
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNoShipAndTaxes = new JLabel("*Does not include S&H or taxes");
		lblNoShipAndTaxes.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		btnAddList_1 = new JButton("Add Now");
		
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		lblAdd2List.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/Price list.png")));
		
		btnAddOneToCart = new JButton("Add One");
		
		lblAddAllToList = new JLabel("Add all to Cart:");
		lblAddAllToList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/basket2.png")));
		
		lblAddOneToList = new JLabel("Add one to Cart:");
		lblAddOneToList.setIcon(new ImageIcon(productSearchClass.class.getResource("/icons/basket2.png")));
		
		panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
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
										.addComponent(lblRemoveAllFromList))
									.addGap(4))
								.addComponent(rdbtnSeeDescription)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addComponent(rdbtnSeeImage)
									.addGap(35))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(8)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddAll)
								.addComponent(btnAddOneToCart)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(btnAddList_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(6)
									.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel_1.createSequentialGroup()
											.addComponent(lblNewLabel)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNoShipAndTaxes, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(6)
									.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
								.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))))
					.addGap(50))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdd2List)
						.addComponent(btnAddList_1))
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
						.addComponent(lblAddOneToList)
						.addComponent(btnAddOneToCart)
						.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAddAllToList)
								.addComponent(btnAddAll))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoveAllFromList)
								.addComponent(btnRemoveAll)))
						.addComponent(lblNoShipAndTaxes, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addComponent(panel_3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(panel_4, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		
		txtpnProductDescription = new JTextPane();
		txtpnProductDescription.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_4.createSequentialGroup()
					.addContainerGap(9, Short.MAX_VALUE)
					.addComponent(txtpnProductDescription, GroupLayout.PREFERRED_SIZE, 152, GroupLayout.PREFERRED_SIZE))
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtpnProductDescription, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		panel_4.setLayout(gl_panel_4);
	
		
		displayLabel = new JLabel("");
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel_3.createSequentialGroup()
					.addContainerGap(44, Short.MAX_VALUE)
					.addComponent(displayLabel, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_panel_3.createSequentialGroup()
					.addContainerGap()
					.addComponent(displayLabel, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		
		JListShopList_1 = new JList<Object>();
		scrollPane.setViewportView(JListShopList_1);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		
		
	}
}

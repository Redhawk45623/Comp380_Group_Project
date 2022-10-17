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
	
	public Object[] productIDs;
	public Object[] products;
	public static Object[] prices = new String[20];
	public static int[] prices2 = new int[20];
	private int add = 0;
	private int sum;
	static int[] priceArray = new int[50];
	private int counter = 0;
	public static DefaultListModel ToProductSearchList_items_1;
	public static DefaultListModel CartList_items_2;
	public static DefaultListModel ToCartShopList_items_3;
	private JComboBox cbProducts_1;
	private JButton btnAddList_1;
	private JButton btnAddCart;
	private boolean check2 = false;
	public static int remember;
	public static int addThemUp;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JButton btnAddAll;
	private JButton btnRemoveAll;
	private JLabel lblRemoveAllFromList;
	private JLabel lblNewLabel;
	private JTextArea textAreaTotal_1;
	private JLabel lblNewLabel_1;
	private JList JListShopList_1;
	private JRadioButton rdbtnUseList;
	private JPanel panel_2;
	private JLabel lblAddAllToList;
	private JLabel lblAddOneToList;
	int d;
	
	/**
	 * 
	 * 
	 * 
	 * Create the panel.
	 */
	public productSearchClass() throws FileNotFoundException{
		
		initComponents();
		createEvents();
		loadArrayFromTxtFile();
		loadCombobox();	
		
		panel_1.setVisible(false);
		panel_2.setVisible(true);
		
		
	}
	
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadArrayFromTxtFile() throws FileNotFoundException {
		
		String namepath = "/Users/Zeina/Desktop/productNames.txt";
		File file = new File(namepath);
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			products = br.lines().toArray();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String IDpath = "/Users/Zeina/Desktop/productIDs.txt";
		File file2 = new File(IDpath);
			
		try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
			productIDs = br2.lines().toArray();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String pricespath = "/Users/Zeina/Desktop/prices.txt";
		File file3 = new File(pricespath);
			
		try (BufferedReader br3 = new BufferedReader(new FileReader(file3))) {
			prices = br3.lines().toArray();
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
	public void addPrices(int x) {
		
		Object first = prices[x]; //this creates an object variable that is initialized from the passed parameter/prices[]
		int second = Integer.parseInt(first.toString()); //this converts the object to integer
		priceArray[counter] = second; //this loads the priceArray[]
		counter++;
		sum = 0;
		for(int i = 0; i < priceArray.length; i++){ //loop to add up the total price that is in priceArray
		sum += priceArray[i];
		}
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public void loadCombobox() {
		
		for(int i = 0; i < products.length; i++) {
			String line = productIDs[i].toString();
			String line2 = products[i].toString();		
			cbProducts_1.addItem(line + " - " + line2 + " - " + "Price: " + "$" + prices[i] + ".00");
			
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
	protected static <T> void addTo(ListModel<T> from, DefaultListModel<T> to) {
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
	private void createEvents() {
		
		btnAddList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				int send = cbProducts_1.getSelectedIndex(); //creates variable index to pass to addPrices() method
				
				prices2[add] = send; //used to track the order of indexes that were added to the shopping list
				d = prices2[add];  //assigns variable d the index stored in prices2 array starting at index 0
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
		btnAddCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int index = cbProducts_1.getSelectedIndex();
				cartClass.addCartprice(index);				
				cartClass.setCartPriceTotal();				
				CartList_items_2.addElement(cbProducts_1.getSelectedItem());
				cartClass.JListCartList.setModel(CartList_items_2);
				//JOptionPane.showMessageDialog(null,"Added selected item to Cart!");
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnAddAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				int begn = 0; //sets the beginning point of the selection value
				int end = JListShopList_1.getModel().getSize() - 1; //sets the end point of the selection value
				if (end >= 0) { //loop to select all items in the shopping using begn and end variables
					JListShopList_1.setSelectionInterval(begn, end);
					
				}
				
				int[] selectedIx = JListShopList_1.getSelectedIndices();
				
				for (int i = 0; i < selectedIx.length; i++) {
					
					int u = prices2[i];
					cartClass.addCartprice(u);
					cartClass.setCartPriceTotal();					
					
				}
				
				//cartClass.setCartPriceTotal();
				
				cartClass.JListCartList.setModel(ToCartShopList_items_3);
				ToProductSearchList_items_1.clear();
				textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; 
				JOptionPane.showMessageDialog(null,"Moved all items in shopping list to Cart!");
				
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ToProductSearchList_items_1.removeAllElements(); //this clears all elements from DefaultListModel items and from the shopping list
				textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; //this resets the priceArray[]
			}
		});
		
		/**
		 * 
		 * 
		 * 
		 * 
		 */
		rdbtnUseList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (check2 == false) {
					
					panel_1.setVisible(true);
					check2 = true;
					
					panel_2.setVisible(false);
					
				}else {
					
					panel_1.setVisible(false);
					check2 = false;
					
					panel_2.setVisible(true);
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
	private void initComponents() throws FileNotFoundException {
		
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
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 514, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 297, GroupLayout.PREFERRED_SIZE)
							.addGap(13))
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(rdbtnUseList)))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(256, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 184, GroupLayout.PREFERRED_SIZE)
					.addGap(70))
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
					.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE))
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
		
		btnAddAll = new JButton("Add All");
		
		btnRemoveAll = new JButton("Remove All");
		
		lblRemoveAllFromList = new JLabel("    Remove All from List:");
		
		lblNewLabel = new JLabel("Total = ");
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		btnAddList_1 = new JButton("Add Now");
		
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		
		JButton btnNewButton = new JButton("Add One");
		
		lblAddAllToList = new JLabel("Add all from List:");
		
		lblAddOneToList = new JLabel("Add one from List:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(12)
							.addComponent(lblRemoveAllFromList, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(28)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAdd2List)
								.addComponent(lblAddOneToList)
								.addComponent(lblAddAllToList))
							.addGap(12)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnAddList_1, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
									.addGroup(gl_panel_1.createSequentialGroup()
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING, false)
											.addComponent(btnNewButton, Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
											.addComponent(btnAddAll, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
											.addComponent(lblNewLabel_1, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
											.addGroup(Alignment.TRAILING, gl_panel_1.createSequentialGroup()
												.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))))))))
					.addGap(58))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddList_1)
						.addComponent(lblAdd2List))
					.addGap(14)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel)
						.addComponent(btnNewButton)
						.addComponent(lblAddOneToList))
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddAll)
								.addComponent(lblAddAllToList))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoveAll)
								.addComponent(lblRemoveAllFromList))
							.addContainerGap(93, Short.MAX_VALUE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		
		JListShopList_1 = new JList();
		scrollPane.setViewportView(JListShopList_1);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
}

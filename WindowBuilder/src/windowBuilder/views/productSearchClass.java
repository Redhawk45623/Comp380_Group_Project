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
import javax.swing.AbstractListModel;
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
import javax.swing.JTabbedPane;
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
	private int sum;
	static int[] priceArray = new int[50];
	private int counter = 0;
	public static DefaultListModel items_1;
	public static DefaultListModel items_2;
	public static DefaultListModel items_3;
	private JComboBox cbProducts_1;
	private JButton btnAddList_1;
	private JButton btnAddCart;
	private boolean check = true;
	private boolean check2 = false;
	public static int remember;
	public static int addThemUp;
	private JPanel panel_1;
	private JScrollPane scrollPane;
	private JLabel lblShopList;
	private JLabel lblAddAll;
	private JButton btnAddAll;
	private JButton btnRemoveAll;
	private JLabel lblRemoveAllFrom;
	private JLabel lblNewLabel;
	private JTextArea textAreaTotal_1;
	private JLabel lblNewLabel_1;
	private JList JListShopList_1;
	private JRadioButton rdbtnUseList;
	private JPanel panel_2;
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
				addPrices(send); //calls addPrices method				
				setPriceTotal();
				
				if (check == true) {
				items_3.addElement(cbProducts_1.getSelectedItem()); 
				
				}
				else {
					items_1.addElement(cbProducts_1.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel items
					JListShopList_1.setModel(items_1);
					
				}
				
				if (check == true ) {
				items_1.addElement(cbProducts_1.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel items
				JListShopList_1.setModel(items_1); //this lists the selected DefaultListModel items in the JListShopList shopping list
							
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
		btnAddCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (items_3.isEmpty()) {
					int index = cbProducts_1.getSelectedIndex();
					cartClass.addCartprice(index);				
					cartClass.setCartPriceTotal();				
					items_2.addElement(cbProducts_1.getSelectedItem());
					cartClass.JListCartList.setModel(items_2);
					JOptionPane.showMessageDialog(null,"Added selected item to Cart!");
				}
				else {
					
					int index = cbProducts_1.getSelectedIndex();
					cartClass.addCartprice(index);				
					cartClass.setCartPriceTotal();				
					items_2.addElement(cbProducts_1.getSelectedItem());
					addTo(items_2, items_3);
					cartClass.JListCartList.setModel(items_2);
					
					//JOptionPane.showMessageDialog(null,"Not empty!");
					
				}
				
				//JOptionPane.showMessageDialog(null,c);
				
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
							
				if (check == true) {
					
					check = false;
					cartClass.textAreaCartTotal.append("$" + sum + ".00");
					remember = sum;
					cartClass.JListCartList.setModel(items_3);
					items_1.clear();
					textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
					priceArray = new int[50]; 
					JOptionPane.showMessageDialog(null,"Moved all items in shopping list to Cart!");
				}
				else {
										
					addThemUp = remember + sum;					
					cartClass.setCartPriceTotalFromList();					
					cartClass.JListCartList.removeAll();
					addTo(items_1, items_3);						
					items_1.clear();
					textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
					priceArray = new int[50];
					JOptionPane.showMessageDialog(null,"Moved all items in shopping list to Cart!");
				    remember = remember + sum;
				}
				//JOptionPane.showMessageDialog(null,x);
				
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
				
				items_1.removeAllElements(); //this clears all elements from DefaultListModel items and from the shopping list
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
		
		items_1 = new DefaultListModel();
		items_2 = new DefaultListModel();
		items_3 = new DefaultListModel();
		
		JLabel lblProducts = new JLabel("Available Products:");
		
		panel_1 = new JPanel();
		
		rdbtnUseList = new JRadioButton("Shop using List");
		
		panel_2 = new JPanel();
	
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addComponent(rdbtnUseList))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(19)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 197, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 310, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(12)
					.addComponent(rdbtnUseList)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducts)
						.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 392, GroupLayout.PREFERRED_SIZE))
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
		
		lblShopList = new JLabel("  Shopping List:");
		
		lblAddAll = new JLabel("Add all from List to Cart:");
		
		btnAddAll = new JButton("Add All");
		
		btnRemoveAll = new JButton("Remove All");
		
		lblRemoveAllFrom = new JLabel("    Remove All from List:");
		
		lblNewLabel = new JLabel("Total = ");
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		btnAddList_1 = new JButton("Add Now");
		
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addComponent(lblAddAll, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddAll, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
							.addGap(45)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(29)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addComponent(lblRemoveAllFrom, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(58)
							.addComponent(lblShopList, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(22)
							.addComponent(lblAdd2List)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddList_1)))
					.addContainerGap(64, Short.MAX_VALUE))
		);
		gl_panel_1.setVerticalGroup(
			gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup()
					.addGap(5)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdd2List)
						.addComponent(btnAddList_1))
					.addGap(26)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShopList))
					.addGap(18)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_1.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNewLabel))
								.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_1.createSequentialGroup()
							.addGap(5)
							.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblAddAll)
								.addComponent(btnAddAll))))
					.addGap(12)
					.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRemoveAllFrom)
						.addComponent(btnRemoveAll))
					.addContainerGap(109, Short.MAX_VALUE))
		);
		
		JListShopList_1 = new JList();
		scrollPane.setViewportView(JListShopList_1);
		panel_1.setLayout(gl_panel_1);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}
}

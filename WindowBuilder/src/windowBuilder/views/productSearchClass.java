/**
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
import javax.swing.LayoutStyle.ComponentPlacement;
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
	public Object[] prices = new String[20];
	private JTextArea textAreaTotal;
	private int sum;
	int[] priceArray = new int[50];
	private int counter = 0;
	private DefaultListModel items_1;
	private JList JListShopList_1;
	private JButton btnAddList;
	private JButton btnRemoveAll;
	private JComboBox cbProducts_1;
	private JButton btnAddList_1;
	private JButton btnAddCart;
	private JButton btnAddAll;
	private JButton btnRemoveAll_1;
	private JTextArea textAreaTotal_1;
	
	
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
	 */
	private void createEvents() {
		
		btnAddList_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int send = cbProducts_1.getSelectedIndex(); //creates variable index to pass to addPrices() method										                      
				addPrices(send); //calls addPrices method
				
				setPriceTotal();
								
				items_1.addElement(cbProducts_1.getSelectedItem());  //This adds the selected element from cbProducts to DefaultListModel items
				JListShopList_1.setModel(items_1); //this lists the selected DefaultListModel items in the JListShopList shopping list
							
				//JOptionPane.showMessageDialog(null,S);
				
			}
		});
		
		btnRemoveAll_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				items_1.removeAllElements(); //this clears all elements from DefaultListModel items and from the shopping list
				textAreaTotal_1.setText(""); //this resets the textAreaTotal box back to empty
				priceArray = new int[50]; //this resets the priceArray[]
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
		cbProducts_1.setMaximumRowCount(5);
		
		items_1 = new DefaultListModel();
		
		JLabel lblProducts = new JLabel("Available Products:");
		
		btnAddList_1 = new JButton("Add Now");
				
		JLabel lblAdd2List = new JLabel("Add to Shopping List:");
		
		btnAddCart = new JButton("Add To Cart");
		
		JLabel lblAdd2Cart = new JLabel("Add to Cart:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel lblShopList = new JLabel("  Shopping List:");
		
		btnAddAll = new JButton("Add All");
		
		JLabel lblAddAll = new JLabel("Add all from List to Cart:");
		
		textAreaTotal_1 = new JTextArea();
		textAreaTotal_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblNewLabel = new JLabel("Total = ");
		
		btnRemoveAll_1 = new JButton("Remove All");
		
		JLabel lblRemoveAllFrom = new JLabel("    Remove All from List:");
		
		JLabel lblNewLabel_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblProducts, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAdd2List, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnAddList_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblAdd2Cart, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnAddCart, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbProducts_1, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblShopList, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(17)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblRemoveAllFrom, GroupLayout.PREFERRED_SIZE, 151, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddAll, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRemoveAll_1, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnAddAll, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
									.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
										.addGroup(gl_panel.createSequentialGroup()
											.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
											.addGap(6)
											.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
										.addComponent(lblNewLabel_1))))))
					.addGap(129))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(25)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblProducts)
						.addComponent(cbProducts_1, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAdd2List)
						.addComponent(lblAdd2Cart)
						.addComponent(btnAddList_1)
						.addComponent(btnAddCart))
					.addGap(26)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblShopList))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(btnAddAll)
							.addComponent(lblAddAll))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(2)
									.addComponent(lblNewLabel))
								.addComponent(textAreaTotal_1, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1)))
					.addGap(7)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRemoveAll_1)
						.addComponent(lblRemoveAllFrom))
					.addContainerGap(143, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
		JListShopList_1 = new JList();
		scrollPane.setViewportView(JListShopList_1);
		JListShopList_1.setName("");
		JListShopList_1.setVisibleRowCount(5);
		JListShopList_1.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
	}
}

package windowBuilder.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.MutableComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JList;
import java.awt.Rectangle;
import java.awt.ComponentOrientation;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.border.EtchedBorder;
import javax.swing.AbstractListModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductSearch extends JFrame {

	private JPanel contentPane;
	private JButton btnOrder;
	private JLabel lblProducts;
	private JButton btnAddList;
	private JLabel lblAdd2List;
	private JLabel lblAdd2Cart;
	private JButton btnAddCart;
	private JLabel lblShopList;
	private JLabel lblNewLabel_3;
	private JTextArea textAreaTotal;
	private JMenuBar menuBar;
	private JLabel lblAdd2Cart2;
	private JLabel lblFromShopList;
	private JButton btnAddCartFromList;
	private JMenu menuProductSearch;
	private JLabel lblAddAll;
	private JButton btnAddAll;
	private JLabel lblRemoveItemList;
	private JButton btnRemoveAll;
	private JLabel lbAddQuantity;
	private JButton btnAddQuantity;
	private JMenu menuShopCart;
	private JMenu menuHelp;
	private JComboBox cbProducts;
	private JList JListShopList;
	private JScrollPane scrollPane;
	private Object[] prices = new String[20];
	int[] priceArray = new int[50];
	private DefaultListModel items;
	private int[] selectedIndex = new int[25];
	private int counter2 = 0;
	private int counter = 0;
	private JLabel lblRemoveAllFrom;
	private int priceTotal;
	private int check = 0;
	private int sum;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductSearch frame = new ProductSearch();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public ProductSearch() throws FileNotFoundException {
		setTitle("PRODUCT SEARCH");
		
		initComponents();
		createEvents();
		fillComboFromTxtFile();
		//addPrices();
	
		
	}
	
	public void fillComboFromTxtFile() throws FileNotFoundException {
		
		String namepath = "/Users/Zeina/Desktop/productNames.txt";
		File file = new File(namepath);
			
		BufferedReader br = new BufferedReader(new FileReader(file));
		Object[] products = br.lines().toArray();
		
		String IDpath = "/Users/Zeina/Desktop/productIDs.txt";
		File file2 = new File(IDpath);
			
		BufferedReader br2 = new BufferedReader(new FileReader(file2));
		Object[] productIDs = br2.lines().toArray();
		
		String pricespath = "/Users/Zeina/Desktop/prices.txt";
		File file3 = new File(pricespath);
			
		BufferedReader br3 = new BufferedReader(new FileReader(file3));
		prices = br3.lines().toArray();
		
		for(int i = 0; i < products.length; i++) {
			String line = productIDs[i].toString();
			String line2 = products[i].toString();
			
			cbProducts.addItem(line + " - " + line2 + " - " + "Price: " + "$" + prices[i] + ".00");
			Object k = prices[i];
			int l = Integer.parseInt(k.toString());
			prices[i] = l;
			//JOptionPane.showMessageDialog(null,prices[i]);
		}
	}
	
	public int addPrices(int x) {
		Object first = prices[x];
		int second = Integer.parseInt(first.toString());
		priceArray[counter2] = second;
		counter2++;
		return second;
		
		/*Object x = prices[0];
		Object q = prices[8];
		int l = Integer.parseInt(x.toString());
		int m = Integer.parseInt(q.toString());
		int t = l + m;
		JOptionPane.showMessageDialog(null,t);*/
	}
	
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 525);
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuBar.setForeground(new Color(0, 102, 0));
		menuBar.setName("");
		setJMenuBar(menuBar);
		
		menuProductSearch = new JMenu("Product Search");
		menuProductSearch.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuBar.add(menuProductSearch);
		
		menuShopCart = new JMenu("Shopping Cart");
		menuBar.add(menuShopCart);
		
		menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnOrder = new JButton("Order");
		
		lblProducts = new JLabel("Available Products:");
		
		cbProducts = new JComboBox();
		cbProducts.setMaximumRowCount(5);
		
		items = new DefaultListModel();
		
		btnAddList = new JButton("Add Now");
		btnAddList.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				//items.add(0,cbProducts.getSelectedItem());  //This adds the item at the top of the shopping list	
				
				selectedIndex[counter] = cbProducts.getSelectedIndex();
								
				int T = selectedIndex[counter];
				int S = addPrices(T);
				//String R = String.valueOf(S);
				
				sum = 0;
				for(int i = 0; i < priceArray.length; i++){
				  sum += priceArray[i];
				}
				//cbTotal.addItem("Total: " + sum);
				textAreaTotal.setText("");
				String z = Integer.toString(sum);
				textAreaTotal.append("$" + z + ".00");
								
				items.addElement(cbProducts.getSelectedItem());  //This adds the item to shopping list in sequential order
				JListShopList.setModel(items);
				
				
				
				//JOptionPane.showMessageDialog(null,S);
			}
		});	
		
		
		btnAddQuantity = new JButton("Add 1");
		btnAddQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
			}
		});
		
		lblAdd2List = new JLabel("Add to Shopping List:");
		
		lblAdd2Cart = new JLabel("Add to Cart:");
		
		btnAddCart = new JButton("Add Now");
		
		lblShopList = new JLabel("Shopping List:");
		
		lblNewLabel_3 = new JLabel("Total = ");
		
		textAreaTotal = new JTextArea();
		textAreaTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblAdd2Cart2 = new JLabel(" Add to Cart");
		
		lblFromShopList = new JLabel("from Shopping List:");
		
		btnAddCartFromList = new JButton("Add Now");
		
		lblAddAll = new JLabel("Add all from List to Cart:");
		
		btnAddAll = new JButton("Add All");
		
		lblRemoveItemList = new JLabel("Remove Item from List:");
		
		btnRemoveAll = new JButton("Remove All");
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				items.removeAllElements();
				textAreaTotal.setText("");
				priceArray = new int[50];
			}
		});
		
		lbAddQuantity = new JLabel("Add Quantity (+1):");
		
		scrollPane = new JScrollPane();
		
		JButton btnRemove_1 = new JButton("Remove Item");
		btnRemove_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							
				try {
					int x = JListShopList.getSelectedIndex();
					items.remove(x);
					
					/*for(int i = x; i < priceArray.length - 1; i++ ) {
						priceArray[i] = priceArray[i + 1];
					}*/
									
		        } catch (Exception d) {
		        	JOptionPane.showMessageDialog(null,"Please Select Item From Shopping List to Remove");
		        }
				
			}
		});
		
		lblRemoveAllFrom = new JLabel("    Remove All from List:");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(lblProducts)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 313, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(30)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(66)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblAdd2Cart2)
											.addComponent(lblShopList)))
									.addComponent(lblAddAll, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblRemoveItemList, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblRemoveAllFrom, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addComponent(lbAddQuantity)
								.addComponent(lblFromShopList))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
											.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
												.addComponent(btnAddCartFromList, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(btnAddQuantity, Alignment.LEADING)
												.addComponent(btnAddAll, Alignment.LEADING))
											.addPreferredGap(ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
											.addComponent(lblNewLabel_3)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textAreaTotal, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
										.addComponent(btnRemove_1, Alignment.LEADING)
										.addComponent(btnRemoveAll, Alignment.LEADING)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(18)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnOrder))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addComponent(lblAdd2List)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddList)
							.addGap(30)
							.addComponent(lblAdd2Cart)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAddCart)))
					.addGap(35))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProducts))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddList)
						.addComponent(lblAdd2List)
						.addComponent(lblAdd2Cart)
						.addComponent(btnAddCart))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblShopList)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(6)
							.addComponent(lblAdd2Cart2))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddCartFromList)
								.addComponent(textAreaTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)
								.addComponent(lblFromShopList))))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnAddQuantity)
						.addComponent(lbAddQuantity))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddAll)
						.addComponent(btnAddAll))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(381)
							.addComponent(btnOrder))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoveItemList)
								.addComponent(btnRemove_1))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblRemoveAllFrom)
								.addComponent(btnRemoveAll))))
					.addContainerGap())
		);
		
		JListShopList = new JList();
		scrollPane.setViewportView(JListShopList);
		JListShopList.setName("");
		JListShopList.setVisibleRowCount(5);
		JListShopList.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		contentPane.setLayout(gl_contentPane);
		// TODO Auto-generated method stub
		
	}

	private void createEvents() {
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				cbProducts.getSelectedItem();
				
			}
		});
		// TODO Auto-generated method stub
		
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
	}
}

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
	private JLabel lblRemoveList;
	private JButton btnRemove;
	private JLabel lbAddQuantity;
	private JButton btnAddQuantity;
	private JMenu menuShopCart;
	private JMenu menuHelp;
	private JComboBox cbProducts;
	private JList JListShopList;
	private JScrollPane scrollPane;

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
		Object[] prices = br3.lines().toArray();
		
		for(int i = 0; i < products.length; i++) {
			String line = productIDs[i].toString();
			String line2 = products[i].toString();
			String line3 = prices[i].toString();
			cbProducts.addItem(line + " - " + line2 + " - " + line3);
		}
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
		
		DefaultListModel items = new DefaultListModel();
		btnAddList = new JButton("Add Now");
		btnAddList.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.addElement(cbProducts.getSelectedItem());
				JListShopList.setModel(items);
				
			}
		});	
		
		btnAddQuantity = new JButton("Add 1");
		btnAddQuantity.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				items.addElement(JListShopList.getSelectedValue());
				JListShopList.setModel(items);
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
		
		lblRemoveList = new JLabel("Remove from List:");
		
		btnRemove = new JButton("Remove");
		
		lbAddQuantity = new JLabel("Add Quantity (+1):");
		
		scrollPane = new JScrollPane();
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(33)
							.addComponent(lblProducts)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblAddAll)
								.addComponent(lblRemoveList))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRemove)
								.addComponent(btnAddAll))
							.addGap(167))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(51)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnOrder)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGap(32)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblFromShopList)
											.addComponent(lblAdd2Cart2)
											.addComponent(lbAddQuantity)
											.addComponent(lblShopList))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPane.createSequentialGroup()
													.addGap(6)
													.addComponent(btnAddCartFromList)
													.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
													.addComponent(lblNewLabel_3)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(textAreaTotal, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED))
												.addGroup(gl_contentPane.createSequentialGroup()
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(btnAddQuantity)))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(lblAdd2List)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAddList)
								.addGap(24)
								.addComponent(lblAdd2Cart)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnAddCart))))
					.addGap(487))
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
						.addComponent(btnAddCart)
						.addComponent(lblAdd2Cart))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblShopList)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(textAreaTotal, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_3)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(22)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnAddCartFromList)
										.addComponent(lblFromShopList)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblAdd2Cart2)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lbAddQuantity)
								.addComponent(btnAddQuantity))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAddAll)
								.addComponent(lblAddAll))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemove)
								.addComponent(lblRemoveList))))
					.addGap(364)
					.addComponent(btnOrder)
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
}

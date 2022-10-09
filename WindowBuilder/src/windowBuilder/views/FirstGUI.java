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

public class FirstGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnOrder;
	private final ButtonGroup btgEntree = new ButtonGroup();
	private final ButtonGroup btgMeats = new ButtonGroup();
	private JLabel lblNewLabel;
	private JButton btnNewButton;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JTextArea textArea;
	private JMenuBar menuBar;
	private JLabel lblNewLabel_4;
	private JLabel lblNewLabel_6;
	private JButton btnNewButton_2;
	private JMenu mnNewMenu;
	private JLabel lblNewLabel_7;
	private JList list_1;
	private JTextPane txtpnWelcomeToOur;
	private JLabel lblNewLabel_8;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel_9;
	private JButton btnNewButton_4;
	private JLabel lblNewLabel_10;
	private JTextArea textArea_1;
	private JLabel lblNewLabel_11;
	private JLabel lblNewLabel_12;
	private JTextArea textArea_2;
	private JTextArea textArea_3;
	private JButton btnNewButton_5;
	private JLabel lblNewLabel_14;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JLabel lblNewLabel_15;
	private JLabel lblNewLabel_16;
	private JButton btnNewButton_8;
	private JLabel lblNewLabel_13;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JMenu mnNewMenu_1;
	private JMenu mnNewMenu_2;
	private JComboBox cbProducts;
	private String s2;
	private Object s;
	private String [] values;
	private JList list;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FirstGUI frame = new FirstGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FirstGUI() {
		setTitle("Comp380 Group Project - Practice GUI");
		
		initComponents();
		createEvents();
		
		
		
	}
	
	private void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 970, 525);
		
		menuBar = new JMenuBar();
		menuBar.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuBar.setForeground(new Color(0, 102, 0));
		menuBar.setName("");
		setJMenuBar(menuBar);
		
		mnNewMenu = new JMenu("Product Search");
		mnNewMenu.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		menuBar.add(mnNewMenu);
		
		mnNewMenu_1 = new JMenu("Shopping Cart");
		menuBar.add(mnNewMenu_1);
		
		mnNewMenu_2 = new JMenu("Help");
		menuBar.add(mnNewMenu_2);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		btnOrder = new JButton("Order");
		
		lblNewLabel = new JLabel("Available Products:");
		
		cbProducts = new JComboBox();
		cbProducts.setMaximumRowCount(5);
		cbProducts.setModel(new DefaultComboBoxModel(new String[] {"ID: 01, Name: Widget 1, Price: $5", "ID: 02, Name: Widget 2, Price: $10", "ID: 03, Name: Widget 3, Price: $15", "ID: 04, Name: Widget 4, Price: $20", "ID: 05, Name: Widget 5, Price: $25", "ID: 06 Name: Widget 6, Price: $30", "ID: 07, Name: Widget 7, Price: $40", "ID: 08, Name: Widget 8, Price: $50", "ID: 09, Name: Widget 9, Price: $60", "ID: 10, Name: Widget 10, Price: $70"}));
		
		btnNewButton = new JButton("Add Now");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name = (String)cbProducts.getSelectedItem();
				JOptionPane.showMessageDialog(null, name);
				
				JOptionPane.showMessageDialog(null, list);
				
				
			    //JOptionPane.showMessageDialog(null, name2);
			}
		});
		
		
		
		lblNewLabel_1 = new JLabel("Add to Shopping List:");
		
		lblNewLabel_5 = new JLabel("Add to Cart:");
		
		btnNewButton_1 = new JButton("Add Now");
		
		lblNewLabel_2 = new JLabel("Shopping List:");
		
		list = new JList();
		list.setVisibleRowCount(5);
		list.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_3 = new JLabel("Total = ");
		
		textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_4 = new JLabel(" Add to Cart");
		
		lblNewLabel_6 = new JLabel("from Shopping List:");
		
		btnNewButton_2 = new JButton("Add Now");
		
		lblNewLabel_7 = new JLabel("Quickview Shopping Cart:");
		
		list_1 = new JList();
		list_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		txtpnWelcomeToOur = new JTextPane();
		txtpnWelcomeToOur.setBackground(UIManager.getColor("Button.background"));
		txtpnWelcomeToOur.setText("Welcome to our practice GUI! You can browse our selected products for sale via the drop-down menu and add to your shopping list or straight into the shopping cart.  You will see the running total of your shopping list and add selected items or add all to the cart. Blah, blah, blah...");
		
		lblNewLabel_8 = new JLabel("Add all from List to Cart:");
		
		btnNewButton_3 = new JButton("Add All");
		
		lblNewLabel_9 = new JLabel("Remove from List:");
		
		btnNewButton_4 = new JButton("Remove");
		
		lblNewLabel_10 = new JLabel("Total:");
		
		textArea_1 = new JTextArea();
		textArea_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		lblNewLabel_11 = new JLabel("Tax:");
		
		lblNewLabel_12 = new JLabel("Grand Total:");
		
		textArea_2 = new JTextArea();
		textArea_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		textArea_3 = new JTextArea();
		textArea_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		btnNewButton_5 = new JButton("Checkout");
		
		lblNewLabel_14 = new JLabel("Add Quantity (+1):");
		
		btnNewButton_6 = new JButton("Add 1");
		
		btnNewButton_7 = new JButton("Remove");
		
		lblNewLabel_15 = new JLabel("Remove Item:");
		
		lblNewLabel_16 = new JLabel("Add Quantity (+1):");
		
		btnNewButton_8 = new JButton("Add 1");
		
		lblNewLabel_13 = new JLabel("To Checkout:");
		lblNewLabel_13.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 13));
		
		rdbtnNewRadioButton = new JRadioButton("See Product Description");
		rdbtnNewRadioButton.setToolTipText("Select the item in the list for a product description!");
		
		rdbtnNewRadioButton_1 = new JRadioButton("See In-Stock Qty");
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(lblNewLabel_1)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton)
								.addGap(24)
								.addComponent(lblNewLabel_5)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(btnNewButton_1)
								.addPreferredGap(ComponentPlacement.RELATED))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(51)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addComponent(btnOrder)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(32)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblNewLabel_6)
													.addComponent(lblNewLabel_4)
													.addComponent(lblNewLabel_14))
												.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
													.addGroup(gl_contentPane.createSequentialGroup()
														.addGap(6)
														.addComponent(btnNewButton_2)
														.addPreferredGap(ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
														.addComponent(lblNewLabel_3)
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(textArea, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
													.addGroup(gl_contentPane.createSequentialGroup()
														.addPreferredGap(ComponentPlacement.RELATED)
														.addComponent(btnNewButton_6))))
											.addGroup(gl_contentPane.createSequentialGroup()
												.addGap(15)
												.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
													.addComponent(lblNewLabel_2)
													.addComponent(rdbtnNewRadioButton_1)
													.addComponent(rdbtnNewRadioButton))
												.addPreferredGap(ComponentPlacement.UNRELATED)
												.addComponent(list, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)))
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(33)
								.addComponent(lblNewLabel)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 311, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(51)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_8)
								.addComponent(lblNewLabel_9))
							.addGap(6)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_4)
								.addComponent(btnNewButton_3))
							.addGap(167)))
					.addPreferredGap(ComponentPlacement.RELATED, 73, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(58)
								.addComponent(txtpnWelcomeToOur, GroupLayout.PREFERRED_SIZE, 384, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(lblNewLabel_10)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
									.addGroup(gl_contentPane.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblNewLabel_12))
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addGap(73)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblNewLabel_15)
											.addComponent(lblNewLabel_16, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE))
										.addGap(6)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
											.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
												.addComponent(btnNewButton_8, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
												.addComponent(lblNewLabel_11))
											.addComponent(btnNewButton_7, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
									.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
									.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)))
							.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
								.addGap(34)
								.addComponent(lblNewLabel_7)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_13)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnNewButton_5)))
					.addGap(78))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(cbProducts, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton)
								.addComponent(lblNewLabel_1)
								.addComponent(btnNewButton_1)
								.addComponent(lblNewLabel_5)))
						.addComponent(txtpnWelcomeToOur, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addGap(26)
							.addComponent(rdbtnNewRadioButton)
							.addGap(18)
							.addComponent(rdbtnNewRadioButton_1))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(list, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_7)
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)))
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(4)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textArea_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_10))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textArea_2, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_11))
									.addGap(18)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(textArea_3, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_12)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(textArea, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblNewLabel_3))
										.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
											.addComponent(lblNewLabel_15)
											.addComponent(btnNewButton_7)))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton_8)
										.addComponent(lblNewLabel_16))))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_5)
								.addComponent(lblNewLabel_13)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(22)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewButton_2)
										.addComponent(lblNewLabel_6)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(6)
									.addComponent(lblNewLabel_4)))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_14)
								.addComponent(btnNewButton_6))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_3)
								.addComponent(lblNewLabel_8))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton_4)
								.addComponent(lblNewLabel_9))))
					.addGap(364)
					.addComponent(btnOrder)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
		// TODO Auto-generated method stub
		
	}

	private void createEvents() {
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,  textFirstName.getText());
				
				cbProducts.getSelectedItem();
				
			}
		});
		// TODO Auto-generated method stub
		
	}
}

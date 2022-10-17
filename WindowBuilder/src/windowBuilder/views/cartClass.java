/**
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import javax.swing.JPanel;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class cartClass extends JPanel {
	public static JList JListCartList;
	public static JTextArea textAreaCartTotal;
	public static int[] cartPriceArray = new int[10];
	private static int counter = 0;
	public static int sum;
	private JButton btnRemoveAll;
	private JButton btnRemoveItem;
	private static String total;
	public static Object[] prices2 = new String[20];
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	public cartClass() {
		
		initComponents();
		createEvents();
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param x
	 */
	public static void addCartprice(int x) {
		
		Object cost = productSearchClass.prices[x];
		int cost_value = Integer.parseInt(cost.toString());
		cartPriceArray[counter] = cost_value;
		counter++;
		sum = 0;
		for(int i = 0; i < cartPriceArray.length; i++){ //loop to add up the total price that is in priceArray
			sum += cartPriceArray[i];
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
	 * @param x
	 */
	public static void rearrangeArray(int x) {
		
		int[] cartPriceArray2 = new int[cartPriceArray.length -1];
		for(int i = 0, k = 0; i < cartPriceArray.length; i++){
			if(cartPriceArray[i] != x){
				cartPriceArray2[k] = cartPriceArray[i];	
				k++;
			}
			
		}
		cartPriceArray = cartPriceArray2;		
		int added = Arrays.stream(cartPriceArray).sum();		
		if (added == 0) {				
			textAreaCartTotal.setText("$0.00"); //this resets the textAreaTotal box back to empty
			cartPriceArray = new int[10];
			counter = 0;
			sum = 0;			
		}

	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 */
	private void createEvents() {
	
		btnRemoveAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
			
			productSearchClass.ToProductSearchList_items_1.removeAllElements();
			productSearchClass.CartList_items_2.removeAllElements(); //this clears all elements from DefaultListModel items and from the shopping list
			productSearchClass.ToCartShopList_items_3.removeAllElements();
			textAreaCartTotal.setText(""); //this resets the textAreaTotal box back to empty
			cartPriceArray = new int[10];
			productSearchClass.remember = 0;
			}
		});
	
		btnRemoveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			int q = JListCartList.getSelectedIndex();
			int r = cartPriceArray[q];	//r = The price of the element removed
			
			
			rearrangeArray(r);
			int added = Arrays.stream(cartPriceArray).sum();
		
			textAreaCartTotal.setText("");
			textAreaCartTotal.append("$" + added + ".00");
			
			productSearchClass.CartList_items_2.remove(q);
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
	private void initComponents() {
		JPanel panel = new JPanel();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JLabel lblNewLabel = new JLabel("Items currently in Cart");
		
		JScrollPane scrollPane = new JScrollPane();
		
		textAreaCartTotal = new JTextArea();
		textAreaCartTotal.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JLabel lblNewLabel_1 = new JLabel("Total = ");
		
		btnRemoveAll = new JButton("Empty");
		
		JLabel lblNewLabel_1_1 = new JLabel("*Does not include S&H or taxes");
		lblNewLabel_1_1.setFont(new Font("Lucida Grande", Font.PLAIN, 9));
		
		JLabel lblNewLabel_2 = new JLabel("Empty Cart:");
		
		btnRemoveItem = new JButton("Remove");
	
		JLabel lblNewLabel_3 = new JLabel("Remove Item:");
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap(40, Short.MAX_VALUE)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnRemoveAll)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveItem, Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
							.addGap(103))
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(173))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(15)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(btnRemoveAll)
						.addComponent(lblNewLabel_2))
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnRemoveItem)
								.addComponent(lblNewLabel_3)))
						.addGroup(gl_panel.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 11, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(236, Short.MAX_VALUE))
		);
		
		JListCartList = new JList();
		JListCartList.setVisibleRowCount(5);
		JListCartList.setName("");
		scrollPane.setViewportView(JListCartList);
		panel.setLayout(gl_panel);
		setLayout(groupLayout);
		
	}	
}

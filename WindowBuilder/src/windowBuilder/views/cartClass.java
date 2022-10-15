/**
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import javax.swing.JPanel;

import java.io.FileNotFoundException;

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
	private static int[] cartPriceArray = new int[50];
	private static int counter = 0;
	public static int sum;
	private JButton btnRemove;
	private JButton btnRemoveItem;
	private static String total;
	private int p;
	
	public cartClass() {
		
		initComponents();
		createEvents();
		
	}
	
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

public static void setCartPriceTotalFromList() {
	
	textAreaCartTotal.setText(""); //clears text from textAreaTotal
	String z = Integer.toString(productSearchClass.addThemUp); //converts integer to String needed to display in textAreaTotal box
	textAreaCartTotal.append("$" + z + ".00");
	
}

public static void setCartPriceTotal() {
	
	textAreaCartTotal.setText(""); //clears text from textAreaTotal
	total = Integer.toString(sum); //converts integer to String needed to display in textAreaTotal box
	textAreaCartTotal.append("$" + total + ".00"); //displays the current total price from the shopping list in the textAreaTotal box

}

private void createEvents() {
	
	btnRemove.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			productSearchClass.items_1.removeAllElements();
			productSearchClass.items_2.removeAllElements(); //this clears all elements from DefaultListModel items and from the shopping list
			productSearchClass.items_3.removeAllElements();
			textAreaCartTotal.setText(""); //this resets the textAreaTotal box back to empty
			cartPriceArray = new int[50];
			
		}
	});
	
	btnRemoveItem.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
			int q = JListCartList.getSelectedIndex();
			int r = cartPriceArray[q];	//r = The price of the element removed
			p = Integer.parseInt(total);
			int newTotal = p - r;
			textAreaCartTotal.setText("");
			JOptionPane.showMessageDialog(null,r);
			JOptionPane.showMessageDialog(null,newTotal);
			textAreaCartTotal.append("$" + newTotal + ".00");
			
			productSearchClass.items_2.remove(q);
			//cartPriceArray[q] = 0;
			
		}
	});
		
	
}
	
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
		
		btnRemove = new JButton("Empty");
		
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
					.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel)
							.addGap(165))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_3)
								.addComponent(lblNewLabel_2))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
								.addGroup(gl_panel.createSequentialGroup()
									.addComponent(btnRemove)
									.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnRemoveItem, Alignment.LEADING)
								.addComponent(lblNewLabel_1_1, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE))
							.addGap(103))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(21)
					.addComponent(lblNewLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(textAreaCartTotal, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(btnRemove)
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

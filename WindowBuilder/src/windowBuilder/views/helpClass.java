package windowBuilder.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

/**
* @author Matthew Bellman
* @version 2022.11.07
*/
public class helpClass extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField addressField;

	public helpClass() {
		
		JLabel lblNewLabel = new JLabel("Phone Number: X-XXX-XXX-XXXX");
		
		JLabel lblNewLabel_1 = new JLabel("Email: support@oldvintage.shop");
		
		JTextArea txtrIfYouHave = new JTextArea();
		txtrIfYouHave.setWrapStyleWord(true);
		txtrIfYouHave.setLineWrap(true);
		txtrIfYouHave.setBackground(SystemColor.control);
		txtrIfYouHave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrIfYouHave.setText("If you have any questions about our store or our products, you can contact us using one of the methods below.");
		txtrIfYouHave.setEditable(false);
		
		JButton btnSend = new JButton("Send");
		
		btnSend.addActionListener(new ActionListener() {
			
			// Send Button Clicked
			public void actionPerformed(ActionEvent e) {
				System.out.println("Hello World"); // TEMP: BUTTON CLICK CHECKER
				
				// Email Address Checker
				// Email Content Checker
				// Write file (send)
				// Display "Send confirmation" message
				// Clear fields and open Home Pane
				
			}
			
		});
				
		JLabel lblNewLabel_2 = new JLabel("Or you can contact us directly by filling out the form below:");
		
		addressField = new JTextField();
		
		JLabel lblNewLabel_3 = new JLabel("* Email Address:");
		
		JLabel lblNewLabel_4 = new JLabel("* Required Field");
		
		JLabel lblNewLabel_5 = new JLabel("* Email Meassage:");
		
		JScrollPane scrollPane = new JScrollPane();
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblNewLabel_4, Alignment.LEADING)
								.addComponent(lblNewLabel, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(btnSend)
								.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)))
						.addComponent(txtrIfYouHave, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
						.addComponent(lblNewLabel_5)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(addressField, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
						.addComponent(lblNewLabel_2))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrIfYouHave, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_1))
					.addPreferredGap(ComponentPlacement.RELATED, 86, Short.MAX_VALUE)
					.addComponent(lblNewLabel_2)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(addressField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel_5)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 357, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(btnSend))
					.addContainerGap())
		);
		
		JTextArea emailArea = new JTextArea();
		emailArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailArea.setWrapStyleWord(true);
		emailArea.setLineWrap(true);
		scrollPane.setViewportView(emailArea);
		setLayout(groupLayout);
	
	}
}

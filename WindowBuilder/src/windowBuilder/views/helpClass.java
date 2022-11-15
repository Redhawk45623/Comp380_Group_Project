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
	private JTextArea emailArea;

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
			
			// Send Button Clicked (2/5)
			public void actionPerformed(ActionEvent e) {
				
				// Checks to see if the email address is valid.
				if (!adressCheck())
					return;
				
				// Checks to see if the email message is not blank.
				if (emailArea.getText().isEmpty()) {
					// Pop up to tell the user that the message area is empty.
					System.out.println("X"); // REPLACE THIS WITH POP UP
					return;
				}
				
				System.out.println(emailArea.getText());
				
				// Write file (send) [Will be a private void function]
				// Display "Send confirmation" message
				// Clear fields and open Home Pane
				
			}

			// Checks to see if the email address is valid.
			private boolean adressCheck() {
				
				String email = addressField.getText();
				boolean check = true;
				
				/* Valid Email address:
				 * 1. Exactly one '@'
				 * 2. Exactly one '.' after '@'
				 */
				if (email.isEmpty()) {
					// Pop up to tell the user that the email address field is empty
					System.out.println("E"); // REPLACE THIS WITH POP UP
					return false;
				}
				
				if (email.contains("@")) {
					email = email.substring(email.indexOf("@") + 1);
					
					if (email.contains("@") || !email.contains(".")) {
						check = false; // Invalid
					}
					
					email = email.substring(email.indexOf(".") + 1);
					
					if (email.isEmpty() || email.contains(".")) {
						check = false;
					}
				} else {
					check = false; // Invalid
				}
				
				if (!check) {
					// Pop up to tell the user that the email address is not valid
					System.out.println("X"); // REPLACE THIS WITH POP UP
				}
				
				return check;
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
		
		emailArea = new JTextArea();
		emailArea.setFont(new Font("Tahoma", Font.PLAIN, 11));
		emailArea.setWrapStyleWord(true);
		emailArea.setLineWrap(true);
		scrollPane.setViewportView(emailArea);
		setLayout(groupLayout);
	
	}
}

package windowBuilder.views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Font;
import java.awt.SystemColor;

/**
* @author Matthew Bellman
* @version 2022.11.07
*/
public class helpClass extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public helpClass() {
		
		JLabel lblNewLabel = new JLabel("Phone Number: X-XXX-XXX-XXXX");
		
		JLabel lblNewLabel_1 = new JLabel("Email: support@oldvintage.shop");
		
		JTextArea txtrIfYouHave = new JTextArea();
		txtrIfYouHave.setLineWrap(true);
		txtrIfYouHave.setBackground(SystemColor.control);
		txtrIfYouHave.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtrIfYouHave.setText("If you have any questions about our store or our products, you can contact us using one of the methods below.");
		txtrIfYouHave.setEditable(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(txtrIfYouHave, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 175, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(txtrIfYouHave, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNewLabel)
					.addGap(11)
					.addComponent(lblNewLabel_1))
		);
		setLayout(groupLayout);
		
	}
}

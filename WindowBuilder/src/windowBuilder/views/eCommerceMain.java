/**
 * 
 * 
 * 
 * 
 * 
 */
package windowBuilder.views;

import java.awt.EventQueue;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class eCommerceMain extends JFrame {

	private JPanel contentPane;
	JPanel search_panel = new productSearchClass();
	JPanel cart_panel = new cartClass();
	/**
	 * 
	 * 
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					eCommerceMain frame = new eCommerceMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 
	 * 
	 * Create the frame.
	 * @throws FileNotFoundException 
	 */
	public eCommerceMain() throws FileNotFoundException {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 525, 525);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 502, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(7, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 484, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(88, Short.MAX_VALUE))
		);
		
		JPanel home_panel = new JPanel();
	
		tabbedPane.addTab("Home", null, home_panel, null);
				
		tabbedPane.addTab("Product Search", null, search_panel, null);
		
		tabbedPane.addTab("View Cart", null, cart_panel, null);
		
		JPanel checkout_panel = new JPanel();
		tabbedPane.addTab("Checkout", null, checkout_panel, null);
		
		JPanel help_panel = new JPanel();
		tabbedPane.addTab("Help", null, help_panel, null);
		contentPane.setLayout(gl_contentPane);
	}

}

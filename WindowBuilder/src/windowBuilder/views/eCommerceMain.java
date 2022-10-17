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
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Toolkit;

/**
 * 
 * 
 * 
 * 
 * @author Ralph Ramirez
 *
 */
public class eCommerceMain extends JFrame {

	private JPanel contentPane;
	JPanel search_panel = new productSearchClass(); //instantiating an object of productSearchClass() which is a JPanel
	JPanel cart_panel = new cartClass(); //iinstantiating an object of cartClass()
	
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
		setBounds(100, 100, 525, 700);
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
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 562, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel home_panel = new JPanel();
	
		tabbedPane.addTab("Home", null, home_panel, null);
		
		JLabel lblNewLabel = new JLabel("This is the Home page that will be modified later...");
		GroupLayout gl_home_panel = new GroupLayout(home_panel);
		gl_home_panel.setHorizontalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(83)
					.addComponent(lblNewLabel)
					.addContainerGap(111, Short.MAX_VALUE))
		);
		gl_home_panel.setVerticalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(195)
					.addComponent(lblNewLabel)
					.addContainerGap(227, Short.MAX_VALUE))
		);
		home_panel.setLayout(gl_home_panel); //home tab
				
		tabbedPane.addTab("Product Search", null, search_panel, "Click to search for products"); //Product Search tab
		
		tabbedPane.addTab("View Cart", null, cart_panel, "Click to view Cart"); //View Cart tab
		
		JPanel checkout_panel = new JPanel();
		tabbedPane.addTab("Checkout", null, checkout_panel, null);
		
		JPanel help_panel = new JPanel();
		tabbedPane.addTab("Help", null, help_panel, null);
		contentPane.setLayout(gl_contentPane);
	}
}

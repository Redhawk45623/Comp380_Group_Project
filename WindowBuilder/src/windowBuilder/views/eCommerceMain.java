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
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 
 * 
 * 
 * 
 * @author Ralph Ramirez
 *
 */
public class eCommerceMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel search_panel = new productSearchClass(); //instantiating an object of productSearchClass() which is a JPanel
	JPanel cart_panel = new cartClass(); //instantiating an object of cartClass() which is a JPanel
	
	ImageIcon homeIcon = new ImageIcon(this.getClass().getResource("/icons/Home.png"));
	ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/icons/Search2.png"));
	ImageIcon cartIcon = new ImageIcon(this.getClass().getResource("/icons/shop-cart-icon.png"));
	ImageIcon checkoutIcon = new ImageIcon(this.getClass().getResource("/icons/Dollar.png"));
	ImageIcon helpIcon = new ImageIcon(this.getClass().getResource("/icons/Help.png"));
	
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
		setTitle("- Comp380 'Think Tank' Group Project -");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 560, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 535, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 662, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel home_panel = new JPanel();	
		tabbedPane.addTab("Home", homeIcon, home_panel, null);
		
		JLabel lblNewLabel = new JLabel("This is the Home page that will be modified later...");
		GroupLayout gl_home_panel = new GroupLayout(home_panel);
		gl_home_panel.setHorizontalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(96)
					.addComponent(lblNewLabel)
					.addContainerGap(99, Short.MAX_VALUE))
		);
		gl_home_panel.setVerticalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(132)
					.addComponent(lblNewLabel)
					.addContainerGap(468, Short.MAX_VALUE))
		);
		home_panel.setLayout(gl_home_panel); //home tab	
				
		tabbedPane.addTab("Product Search", searchIcon, search_panel, "Click to search for products"); //Product Search tab
		
		tabbedPane.addTab("View Cart", cartIcon, cart_panel, "Click to view Cart"); //View Cart tab
		
		JPanel checkout_panel = new JPanel();
		tabbedPane.addTab("Checkout", checkoutIcon, checkout_panel, null);
		
		JPanel help_panel = new JPanel();
		tabbedPane.addTab("Help", helpIcon, help_panel, null);
		contentPane.setLayout(gl_contentPane);
	}
}

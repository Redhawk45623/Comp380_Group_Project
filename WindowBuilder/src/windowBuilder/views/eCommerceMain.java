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
import javax.swing.UIManager;

/**
 * This class holds the main method and is the launching point of the software.<br>
 * It creates a tabbedPane via WindowBuilder which holds essential panels for the<br>
 * user to navigate. All JPanels are instantiated from this main class and exist as<br>
 * panels (accessible via the tabs) for easy navigation for the user. <p>
 * 
 * At the release of this version, the tabs are: Home, Product Search, View Cart,<br>
 * Checkout, and Help.<p>
 * 
 * The 'Home' tab will is still under construction as of the release of this version.<p>
 * 
 * The 'Product Search' tab uses a JPanel object of productSearchClass which holds the constructor<br>
 * and methods necessary to allow a user to search for a product with added features. The <br>
 * 'View Cart' tab uses a JPanel object of cartClass which holds the constructor and methods<br>
 * necessary to allow a user to view products added to the cart with some features.<p>
 * 
 * At the release of this version, the 'Checkout' tab and 'Help' tab are still under construction.<br>
 * 
 * @author Ralph Ramirez 
 * @version 2022.10.28
 */ 
public class eCommerceMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JPanel search_panel = new productSearchClass(); // Instantiates an object of productSearchClass() which is a JPanel
	JPanel cart_panel = new cartClass(); // Instantiates an object of cartClass() which is a JPanel
	JPanel help_panel = new helpClass();
	
	// Sets the icons for the TabbedPane
	ImageIcon homeIcon = new ImageIcon(this.getClass().getResource("/icons/Home.png"));
	ImageIcon searchIcon = new ImageIcon(this.getClass().getResource("/icons/Search2.png"));
	ImageIcon cartIcon = new ImageIcon(this.getClass().getResource("/icons/shop-cart-icon.png"));
	ImageIcon checkoutIcon = new ImageIcon(this.getClass().getResource("/icons/Dollar.png"));
	ImageIcon helpIcon = new ImageIcon(this.getClass().getResource("/icons/Help.png"));
	
	/**
	 * Launches the application.<br>
	 * Manages 'Look and Feel' of the running application<br>
	 * Creates the tabbedPane frame that holds the major panels of the application.<br>
	 * 
	 */
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
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
	 * Initializes all components of the GUI tabbedPane and creates the working frame with tabbedPane.<br>
	 * Code largely written by WindowBuilder design.<br>
	 * Only modified code exists for custom tabs with icons.<br>
	 *
	 * @throws FileNotFoundException 
	 */
	public eCommerceMain() throws FileNotFoundException {
		setResizable(false); // Constructor
		
		setTitle("- 'Think Tank' Group Project: 'Vintage Shop' -");

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
					.addComponent(tabbedPane, GroupLayout.PREFERRED_SIZE, 525, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(403, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(tabbedPane, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		JPanel home_panel = new JPanel();	
		tabbedPane.addTab("Home", homeIcon, home_panel, null);		
		JLabel lblNewLabel = new JLabel("This is the Home page that will be modified later...");
		JLabel lblNewLabel_1 = new JLabel("We could possibly add a custom logo and incorporate");
		JLabel lblNewLabel_2 = new JLabel("the team name along with team member names...");
		
		GroupLayout gl_home_panel = new GroupLayout(home_panel);
		gl_home_panel.setHorizontalGroup(
			gl_home_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, gl_home_panel.createSequentialGroup()
					.addGap(133)
					.addGroup(gl_home_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_1)
						.addGroup(gl_home_panel.createSequentialGroup()
							.addGap(8)
							.addComponent(lblNewLabel_2))
						.addGroup(gl_home_panel.createSequentialGroup()
							.addGap(7)
							.addComponent(lblNewLabel)))
					.addContainerGap(133, Short.MAX_VALUE))
		);
		gl_home_panel.setVerticalGroup(
			gl_home_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_home_panel.createSequentialGroup()
					.addGap(132)
					.addComponent(lblNewLabel)
					.addGap(75)
					.addComponent(lblNewLabel_1)
					.addGap(11)
					.addComponent(lblNewLabel_2)
					.addContainerGap(331, Short.MAX_VALUE))
		);
		home_panel.setLayout(gl_home_panel); // Home tab	
		
		///// Add panels below /////////
				
		tabbedPane.addTab("Product Search", searchIcon, search_panel, "Click to search for products"); // Product Search tab
		
		tabbedPane.addTab("View Cart", cartIcon, cart_panel, "Click to view Cart"); // View Cart tab
		
		JPanel checkout_panel = new JPanel(); /// Default when TabbedPane was created.....change this like the 'Product Search' and 'View Cart' tabs above
		tabbedPane.addTab("Checkout", checkoutIcon, checkout_panel, null);
		
		//JPanel help_panel = new JPanel();
		tabbedPane.addTab("Help", helpIcon, help_panel, null);
		contentPane.setLayout(gl_contentPane);
	}
}

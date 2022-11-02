package windowBuilder.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * This class is used to handle most tasks that are regulated to product details.<br>
 * This includes loading arrays that handle product IDs, Names, and Prices (simulated database) that will be displayed in the combobox in productSearchClass.<br>
 * It also includes methods that handle the manipulation of product specifics such as associated image and description features.<br>
 * 
 * @author Ralph Ramirez
 * @version 2022.10.28 
 */
public class productClass {
	
	public static Object[] descriptionsArray = new String[25]; // Array used to store the description Strings
	public Integer[] trackImages = new Integer[25]; // Array used to track the indexes of images as they are added or removed from Shopping List
	public static Object[] namePaths = new String[25]; // Array used to store the paths of the images in the source folder
	public static Object[] nameDisplay = new String[25]; // Array used to store the names of the products that are displayed in the image display area of the Shopping List
	public int track = 0; // Variable used int the setImageIndex() method
	
	/**
	 * Calls loadProductsFromTxtFile() which loads loads the arrays that are used to load the combobox in productSearchClass.<br>
	 * Calls loadProductDescriptions() method to load descriptionsArray[].<br>
	 * Calls loadNamePathsAndDescrip() method to load the namePaths[] and nameDisplay[].<br>
	 * 
	 * @throws FileNotFoundException
	 * @wbp.parser.entryPoint
	 */
	public productClass() throws FileNotFoundException { //constructor
		
		loadProductsFromTxtFile(); // Loads the arrays that are used to load the combobox in productSearchClass.
		loadProductDescriptions(); // Calls loadProductDescriptions() method to load descriptionsArray[]
		loadNamePathsAndDescrip(); // Calls the loadNamePathsAndDescrip() method to load the namePaths[] and nameDisplay[]
	}
	
	/**
	 * Loads the arrays used to display products in the combobox for productSearchClass. <br>
	 * Simulates the database retrieval of crucial product information: ID, Name, and Price.<br>
	 * Loads this data from the text files located in the productDatabase folder.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadProductsFromTxtFile() throws FileNotFoundException { // Method that loads the arrays used to load combobox (products in drop-down list)
		
		//java.net.URL url = getClass().getResource("/productDatabase/productNames.txt"); 
		//File file = new File(url.getPath());
			
		File file = new File(System.getProperty("user.dir") + "/src/productDatabase/productNames.txt"); // Path to productNames.txt file stored in src/productDatabase

		//String namepath = "/Users/Zeina/Desktop/productNames.txt"; //path for the Product Names file stored on my computer locally
		//File file = new File(namepath);
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			productSearchClass.products = br.lines().toArray(); // Loads products[] with Product Names 
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//String IDpath = "/Users/Zeina/Desktop/productIDs.txt"; //path for the Product IDs file stored on my computer locally
		//File file2 = new File(IDpath);
		
		File file2 = new File(System.getProperty("user.dir") + "/src/productDatabase/productIDs.txt");  // Path to productIDs.txt file stored src/productDatabase
		
		try (BufferedReader br = new BufferedReader(new FileReader(file2))) {
			productSearchClass.productIDs = br.lines().toArray(); // Loads Product IDs[] with Product IDs
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//String pricespath = "/Users/Zeina/Desktop/prices.txt"; //path for Product Prices file stored on my computer locally
		//File file3 = new File(pricespath);
		
		File file3 = new File(System.getProperty("user.dir") + "/src/productDatabase/prices.txt"); // Path to prices.txt file stored in src/productDatabase
		
		try (BufferedReader br3 = new BufferedReader(new FileReader(file3))) {
			productSearchClass.prices = br3.lines().toArray(); // Loads prices[] with Product Prices
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the descriptionsArray[] which will hold all the String descriptions for our products.<br>
	 * Loads this data from the text files located in the productDescriptions folder.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadProductDescriptions() throws FileNotFoundException { // Method used to load the Descriptions of Products into array
		
		//int i; //declares int i
		//java.net.URL url = getClass().getResource("/productDescriptions/descriptions.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		//File file = new File(url.getPath()); //creats a File Object from the 'url' variable
		
		File file = new File(System.getProperty("user.dir") + "/src/productDescriptions/descriptions.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			for (int i = 0; i < descriptionsArray.length; i++)
				descriptionsArray = br.lines().toArray();
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Used to return the product description that is displayed in panel_04 in productSearchClass.<br>
	 * 
	 * @param choose   index used for descriptionsArray[]
	 * @return         returns the String description for the product
	 */
	public String setDescriptions(int choose) { // Method used to return a description String using parameter 'x' descriptionsArray.  Converts to String too
		
		//String descrip = descriptionsArray[choose].toString(); // Sets the String variable: 'descrip' to the String found in descriptionsArray[]
		return descriptionsArray[choose].toString(); // Returns the String found in descriptionsArray[choose]
		
	}
	
	/**
	 * Tracks the index order of images as they are added to the Shopping List in productSearchClass.<br>
	 * 
	 * @param number   loads the trackImages[] array with this Integer
	 */
	public void setImageIndex(Integer number) { // Method used to set the index order of images as they are added to the Shopping List
		
		trackImages[track] = number; // Loads the trackImages[] with the index of the selected item added to the Shopping List
		track++; // Increments the variable: 'track' for next use
		
	}
	
	/**
	 * Removes image indexes that were stored to trackImages[] to match what is currently in the Shopping List.<br>
	 * Uses a temp ArrayList to build new array minus the image index then sets trackImages[].<br>
	 * 
	 * @param number   the index that is to be removed from trackImages[]
	 */
	public void removeImageIndex(Integer number) { // 'number' = the index of the image that is to be removed
		
		ArrayList<Integer> arr_new = new ArrayList<>(); // Creates a temp ArrayList: 'arr_new' 
		
		for (int i = 0; i < trackImages.length; i++) { // for-loop using the length of trackImages		
			if (trackImages[i] != number) { // If the element at trackImages[i] does not = the parameter: 'number'			
				arr_new.add(trackImages[i]); // Adds the element found at trackImages[i] to the ArrayList: 'arr_new'			
			}			
		}		
		trackImages = arr_new.toArray(new Integer[0]); // Sets the trackImages[] to the newly arranged ArrayList: 'arr_new'
	}
	
	/**
	 * Loads the namePaths[] with the paths of text files that contain the description
	 * strings used in panel_04 of productSearchClass.<br>
	 * Loads the nameDisplay[] with the titles of the products used in panel_03 of productSearchClass.<br>
	 * Used primarily for the loadImages() method.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadNamePathsAndDescrip() throws FileNotFoundException { // Method used to load the paths for the images and titles of products...all found in the productDescriptions folder in src
		 
		//java.net.URL url = getClass().getResource("/productDescriptions/paths.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		//File file = new File(url.getPath()); //creats a File Object from the 'url' variable
		
		File file = new File(System.getProperty("user.dir") + "/src/productDescriptions/paths.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) { // BufferedReader to begin reading lines of paths.txt
			for (int i = 0; i < namePaths.length; i++) // for-loop using the length of namePaths[]
				namePaths = br.lines().toArray(); // Loads the namePaths[] with each line from the paths.txt file
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//java.net.URL url2 = getClass().getResource("/productDescriptions/displayNames.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		//File file2 = new File(url2.getPath()); //creats a File Object from the 'url2' variable
		
		File file2 = new File(System.getProperty("user.dir") + "/src/productDescriptions/displayNames.txt");
		
		try (BufferedReader br = new BufferedReader(new FileReader(file2))) { // BufferedReader to begin reading lines of displayNames.txt
			for (int i = 0; i < nameDisplay.length; i++) // for-loop using the length of nameDisplay[]
				nameDisplay = br.lines().toArray(); // Loads the nameDisplay[] with each line from the displayNames.txt file
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Used to display images of products in panel_03 (image display area) of productSearchClass.<br>
	 * Displays small label of the Product name in panel_03 above image.<br>
	 * Displays the description of the product in panel_04.<br>
	 * 
	 * @param number   index used for namePaths[], nameDisplay[], and passed parameter for setDescriptions() method
	 */
	public void loadImages(int number) { // Loads images into image display panel and descriptions into descriptions panel
		
		String display;	 // Declares a String variable: 'display'	
		Object namePath = namePaths[number]; // Assigns an Object variable: 'namePath' to the String path found at namePaths[] using parameter: 'number'
		String path2 = namePath.toString(); // Converts the Object variable: 'namePath' to a String variable: 'path2' 	
		Object descrip = nameDisplay[number]; // Assigns an Object variable: 'descrip' to the String found at nameDisplay[] using parameter: 'number'
		String descrip2 = descrip.toString(); // Converts the Object variable: 'descrip' to a String variable: 'descrip2'		
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path2)); // Assigns the ImageIcon variable: 'icon' to the path found using variable: 'path2'		
		productSearchClass.displayLabel.setIcon(icon); // Displays the icon in the image display panel of productSearchClass
		display = setDescriptions(number); // Assigns the String variable: 'display' using the setDescriptions() method
		productSearchClass.lblImageDescrip.setText(descrip2); // Sets the small label that is the title of the image in the image display panel of productSearchClass
		productSearchClass.txtpnProductDescription.setText(display); // Sets the description for the product in the description panel in productSearchClass	
		
	}	
	
}

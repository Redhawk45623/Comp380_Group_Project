

package windowBuilder.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/** 
 * This class is used to handle most tasks that are regulated to product details. <br>
 * This includes loading arrays that handle product IDs, Names, and Prices that will <br>
 * be dispayed in the combobox in productSearchClass. It also includes methods that<br>
 * handle the manipulation of product specifics such as associated image and description <br>
 * features.<br>
 * 
 * @author Ralph Ramirez
 * @version 2022.10.28 
 */
public class productClass {
	
	public static Object[] descriptionsArray = new String[20]; //array used to store the description Strings
	public Integer[] trackImages = new Integer[20]; //array used to track the indexes of images as they are added or removed from Shopping List
	public static Object[] namePaths = new String[20]; //array used to store the paths of the images in the source folder
	public static Object[] nameDisplay = new String[20]; //array used to store the names of the products that are displayed in the image display area of the Shopping List
	public int track = 0; //variable used int the setImageIndex() method
	
	/**
	 * Calls loadProductsFromTxtFile() which loads loads the arrays that are used to load the combobox in productSearchClass.<br>
	 * Calls loadProductDescriptions() method to load descriptionsArray[].<br>
	 * Calls loadNamePathsAndDescrip() method to load the namePaths[] and nameDisplay[].<br>
	 * 
	 * @throws FileNotFoundException
	 * @wbp.parser.entryPoint
	 */
	public productClass() throws FileNotFoundException { //constructor
		
		loadProductsFromTxtFile(); //loads the arrays that are used to load the combobox in productSearchClass.
		loadProductDescriptions(); //calls loadProductDescriptions() method to load descriptionsArray[]
		loadNamePathsAndDescrip(); //calls the loadNamePathsAndDescrip() method to load the namePaths[] and nameDisplay[]
	}
	
	/**
	 * Loads the arrays used to display products in the combobox for productSearchClass. <br>
	 * Simulates the database retrieval of crucial product information: ID, Name, and Price.<br>
	 * Loads this data from the text files loacted in the productDatabase folder.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadProductsFromTxtFile() throws FileNotFoundException { //method that loads the arrays used to load combobox (products in drop-down list)
		
		java.net.URL url = getClass().getResource("/productDatabase/productNames.txt"); //path to productNames.txt file stored in productDatabase folder in src folder
		File file = new File(url.getPath());
		//String namepath = "/Users/Zeina/Desktop/productNames.txt"; //path for the Product Names file stored on my computer locally
		//File file = new File(namepath);
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			productSearchClass.products = br.lines().toArray(); //loads products[] with Product Names 
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.net.URL url2 = getClass().getResource("/productDatabase/productIDs.txt"); //path to productIDs.txt file stored in productDatabase folder in src folder
		File file2 = new File(url2.getPath());
		//String IDpath = "/Users/Zeina/Desktop/productIDs.txt"; //path for the Product IDs file stored on my computer locally
		//File file2 = new File(IDpath);
			
		try (BufferedReader br2 = new BufferedReader(new FileReader(file2))) {
			productSearchClass.productIDs = br2.lines().toArray(); //loads Product IDs[] with Product IDs
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		java.net.URL url3 = getClass().getResource("/productDatabase/prices.txt"); //path to prices.txt file stored in productDatabase folder in src folder
		File file3 = new File(url3.getPath());
		//String pricespath = "/Users/Zeina/Desktop/prices.txt"; //path for Product Prices file stored on my computer locally
		//File file3 = new File(pricespath);
			
		try (BufferedReader br3 = new BufferedReader(new FileReader(file3))) {
			productSearchClass.prices = br3.lines().toArray(); //loads prices[] with Product Prices
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Loads the descriptionsArray[] which will hold all the String descriptions for our products.<br>
	 * Loads this data from the text files loacted in the productDescriptions folder.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadProductDescriptions() throws FileNotFoundException { //method used to load the Descriptions of Products into array
		
		int i; //declares int i
		java.net.URL url = getClass().getResource("/productDescriptions/descriptions.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		File file = new File(url.getPath()); //creats a File Object from the 'url' variable
		
		for (i = 0; i < descriptionsArray.length; i++) { //for loop using the length of descriptionsArray[]
			try (BufferedReader br = new BufferedReader(new FileReader(file))) { //BufferedReader to begin reading lines of descriptions.txt
				descriptionsArray = br.lines().toArray(); //loads the descriptionsArray[] with each line from the descriptions.txt file
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Used to return the product description that is displayed in panel_04 in productSearchClass.<br>
	 * 
	 * @param choose   index used for descriptionsArray[]
	 * @return         returns the String description for the product
	 */
	public String setDescriptions(int choose) { //method used to return a description String using parameter 'x' descriptionsArray.  Converts to String too
		
		String descrip = descriptionsArray[choose].toString(); //sets the String variable: 'descrip' to the String found in descriptionsArray[]
		return descrip; //returns the String
		
	}
	
	/**
	 * Tracks the index order of images as they are added to the Shopping List in productSearchClass.<br>
	 * 
	 * @param number   loads the trackImages[] array with this Integer
	 */
	public void setImageIndex(Integer number) { //method used to set the index order of images as they are added to the Shopping List
		
		trackImages[track] = number; //loads the trackImages[] with the index of the selected item added to the Shopping List
		track++; //increments the variable: 'track' for next use
		
	}
	
	/**
	 * Removes image indexes that were stored to trackImages[] to match what is currently in the Shopping List.<br>
	 * Uses a temp ArrayList to build new array minus the image index then sets trackImages[].<br>
	 * 
	 * @param number   the index that is to be removed from trackImages[]
	 */
	public void removeImageIndex(Integer number) { //'number' = the index of the image that is to be removed
		
		ArrayList<Integer> arr_new = new ArrayList<>(); //creates a temp ArrayList: 'arr_new' 
		
		for (int i=0; i<trackImages.length; i++) { //for-loop using the length of trackImages		
			if (trackImages[i]!=number) { //if the element at trackImages[i] does not = the parameter: 'number'			
				arr_new.add(trackImages[i]); //adds the element found at trackImages[i]	to the ArrayList: 'arr_new'			
			}			
		}		
		trackImages = arr_new.toArray(new Integer[0]); //sets the trackImages[] to the newly arranged ArrayList: 'arr_new'
	}
	
	/**
	 * Loads the namePaths[] with the paths of text files that contain the description
	 * strings used in panel_04 of productSearchClass.<br>
	 * Loads the nameDisplay[] with the titles of the products used in panel_03 of productSearchClass.<br>
	 * Used primarily for the loadImages() method.<br>
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadNamePathsAndDescrip() throws FileNotFoundException { //method used to load the paths for the images and titles of products...all found in the productDescriptions folder in src
		
		int i; 
		java.net.URL url = getClass().getResource("/productDescriptions/paths.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		File file = new File(url.getPath()); //creats a File Object from the 'url' variable
		
		for (i = 0; i < namePaths.length; i++) { //for-loop using the length of namePaths[]
			try (BufferedReader br = new BufferedReader(new FileReader(file))) { //BufferedReader to begin reading lines of paths.txt
				namePaths = br.lines().toArray(); //loads the namePaths[] with each line from the paths.txt file
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		java.net.URL url2 = getClass().getResource("/productDescriptions/displayNames.txt"); //sets a java.net.URL variable: 'url' to the .txt file found at the specified path
		File file2 = new File(url2.getPath()); //creats a File Object from the 'url2' variable
		
		for (i = 0; i < nameDisplay.length; i++) { //for -oop using the length of nameDisplay[]
			try (BufferedReader br = new BufferedReader(new FileReader(file2))) { //BufferedReader to begin reading lines of displayNames.txt
				nameDisplay = br.lines().toArray(); //loads the nameDisplay[] with each line from the displayNames.txt file
			} catch (FileNotFoundException e) {
				throw e;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	/**
	 * Used to display images of products in panel_03 (image display area) of productSearchClass.<br>
	 * Displays small label of the Product name in panel_03 above image.<br>
	 * Displays the description of the product in panel_04.<br>
	 * 
	 * @param number   index used for namePaths[], nameDisplay[], and setDescriptions() method
	 */
	public void loadImages(int number) { //loads images into image display panel and descriptions into descriptions panel
		
		String display;	 //decalres a String variable: 'display'	
		Object namePath = namePaths[number]; //assigns an Object variable: 'namePath' to the String path found at namePaths[] using parameter: 'number'
		String path2 = namePath.toString(); //converts the Object variable: 'namePath' to a String variable: 'path2' 	
		Object descrip = nameDisplay[number]; //assigns an Object variable: 'descrip' to the String found at nameDisplay[] using parameter: 'number'
		String descrip2 = descrip.toString(); //converts the Object variable: 'descrip' to a String variable: 'descrip2'		
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path2)); //assigns the ImageIcon variable: 'icon' to the path found using variable: 'path2'		
		productSearchClass.displayLabel.setIcon(icon); //displays the icon in the image display panel of productSearchClass
		display = setDescriptions(number); //assigns the String variable: 'display' using the setDescriptions() method
		productSearchClass.lblImageDescrip.setText(descrip2); //sets the small label that is the title of the image in the image display panel of productSearchClass
		productSearchClass.txtpnProductDescription.setText(display); // sets the desciption for the product in the description panel in productSearchClass	
		
	}	
	
}

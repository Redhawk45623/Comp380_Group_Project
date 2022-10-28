/**
 * 
 * 
 * 
 * 
 * 
 */

package windowBuilder.views;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.ImageIcon;

/**
 * 
 * 
 * 
 * 
 * @author Ralph Ramirez
 *
 */
public class productClass {
	
	public static Object[] descriptionsArray = new String[20]; //array used to store the description Strings
	public Integer[] trackImages = new Integer[20]; //array used to track the indexes of images as they are added or removed from Shopping List
	public static Object[] namePaths = new String[20]; //array used to store the paths of the images in the source folder
	public static Object[] nameDisplay = new String[20]; //array used to store the names of the products that are displayed in the image display area of the Shopping List
	public int track = 0; //variable used int the setImageIndex() method
	
	public productClass() throws FileNotFoundException { //constructor
		
		loadProductsFromTxtFile(); //loads the arrays that are used to load the combobox in productSearchClass
		loadProductDescriptions(); //calls loadProductDescriptions() method to load descriptionsArray[]
		loadNamePathsAndDescrip(); //calls the loadNamePathsAndDescrip() method to load the namePaths[] and nameDisplay[]
	}
	
	/**
	 * 
	 * 
	 * 
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
	 * 
	 * 
	 * 
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
	 * 
	 * 
	 * 
	 * 
	 * @param x
	 * @return
	 */
	public String setDescriptions(int x) { //method used to return a description String using parameter 'x' descriptionsArray.  Converts to String too
		
		String descrip = descriptionsArray[x].toString(); //sets the String variable: 'descrip' to the String found in descriptionsArray[]
		return descrip; //returns the String
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 */
	public void setImageIndex(Integer number) { //method used to set the index order of images as they are added to the Shopping List
		
		trackImages[track] = number; //loads the trackImages[] with the index of the selected item added to the Shopping List
		track++; //increments the variable: 'track' for next use
		
	}
	
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
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
	 * 
	 * 
	 * 
	 * 
	 * @throws FileNotFoundException
	 */
	public void loadNamePathsAndDescrip() throws FileNotFoundException { //method used to load the paths for the images and titles of products...all found in the productDescriptions folder in src
		
		int i; //declares int i
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
	 * 
	 * 
	 * 
	 * 
	 * @param number
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
	/**
	 * 
	 * 
	 * 
	 * 
	 * @param number
	 * @throws IOException
	 */
	/*public void loadImages(int number) throws IOException { //method used to load panel_3 (Image Display Panel) and panel_4 (Product Description Panel)
		
		String display; //string variable that is used in switch/case below
		
		switch (number) { //switch case that chooses which image and description are displayed
		  case 0:
			  loadImagesAlt(number);
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/tv.jpg"))); //dispays the image in panel_3			  		  
			  display = setDescriptions(number); //dispays the description in panel_4 based on the value of the parameter: 'number'
			  productSearchClass.lblImageDescrip.setText("Vintage TV"); //sets the small label used in the image display area (panel_3)
			  productSearchClass.txtpnProductDescription.setText(display);	//sets the desciption String to be displayed in panel_4	  		  
			  
			  break;
		  case 1:
			  loadImagesAlt(number);
			  /*productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera.jpg"))); 
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Vintage Camera");
			  productSearchClass.txtpnProductDescription.setText(display);
			  
			  break;
		  case 2:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/atari.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Atari Games");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 3:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/boombox.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("1980s Boombox");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 4:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/phone.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Rotary Phone");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 5:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/cassette.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Cassette Tape");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 6:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/camera2.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Film Camera");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 7:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/top.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Vintage Top");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 8:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/sign.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Rustic Sign");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		  case 9:
			  productSearchClass.displayLabel.setIcon(new ImageIcon(this.getClass().getResource("/images/nintendo.jpg")));
			  display = setDescriptions(number);
			  productSearchClass.lblImageDescrip.setText("Nintendo Console");
			  productSearchClass.txtpnProductDescription.setText(display);
			  break;
		
		}

	}*/
	
	
}

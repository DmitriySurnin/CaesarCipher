package com.surnin.dmitrii.caesarcodeapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Bruteforcer {


	//Setting up instance variables and setter/getter methods
	public final int NUMBER_OF_LETTERS = 26;
	private int numberOfPositions = 0;

	private double[] knownFrequencies = new double[NUMBER_OF_LETTERS];
	private double[] observedFreq = new double[NUMBER_OF_LETTERS];

	public double[] getKnownFrequencies() {
		return knownFrequencies;
	}

	public void setKnownFrequencies(double[] knownFrequencies) {
		this.knownFrequencies = knownFrequencies;
	}

	//This method reads text from a long file, breaks it down into individual characters, and stores it in the knownFrequencies array
	public void train(String trainingFileName) {
		String tempString = "";
		double totalChars = 0.0;
		try {
			@SuppressWarnings("resource")
			Scanner FileIO = new Scanner(new File(trainingFileName)).useDelimiter("[ *-,!?.]+"); //reading text from a file using
			//the delimiter so we get all of the contents
			while(FileIO.hasNext()){
				tempString += FileIO.next().toLowerCase();//storing contents into a string, all lower case
			}
			FileIO.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.exit(0);
		}

		//Figuring out total number of English letters(a-z) used to determine the frequencies
		for(int j = 0; j < tempString.length(); j++){
			char ch = tempString.charAt(j);
			if(Character.isAlphabetic(ch)){
				totalChars++;
			}
		}

		//Initializing the knownFrequencies array with each individual letter count a-z
		for (int k = 0; k <= tempString.length()-1; k++){
			char ch = tempString.charAt(k);
			double chValue = (double) ch;
			if (Character.isAlphabetic(ch)) {
				if(chValue >= 97 && chValue <= 122){
					knownFrequencies[ch - 'a']++;
				}
			}
		}

		//Divide the individual letter counts by the total to get a decimal number
		//for the frequency and store that into the knownFrequencies array.
		for (int i = 0; i < knownFrequencies.length; i++) {
			if(knownFrequencies[i] > 0){

				knownFrequencies[i] = knownFrequencies[i]/totalChars;

			}
		}

	}

	//This method does practically the same thing in the train method except it doesn't read from a file, and it compiles all of the
//cipher text characters to find the frequencies that will be used later to determine the key
	public void setObservedFreq(String tempString)//String parameter takes in the cipher text
	{
		//Finding total number of lower case English letters (a-z)
		double totalChars = 0.0;
		for(int j = 0; j < tempString.length(); j++){
			char ch = tempString.charAt(j);
			if(Character.isAlphabetic(ch)){
				totalChars++;
			}
		}
		//Initializing observedFreq with the number of letters in the string.
		for (int k = 0; k <= tempString.length()-1; k++){
			char ch = tempString.charAt(k);
			double chValue = (double) ch;
			if (Character.isAlphabetic(ch)) {
				if(chValue >= 97 && chValue <= 122){
					observedFreq[ch - 'a']++;
				}
			}

		}
		//Re-initializing with a decimal frequency.
		for (int i = 0; i < NUMBER_OF_LETTERS; i++) {
			if(observedFreq[i] > 0){
				observedFreq[i] = observedFreq[i]/totalChars;
			}
		}
	}

	//This method subtracts the absolute value of the observedFreq from the knownFrequencies, sum all those together and store it
//in a variable that will be return in the method. The smallest distance value means the cipher text has been decoded.
	public double findDistance(){
		double distance = 0.0;
		for(int x = 0; x < NUMBER_OF_LETTERS; x++){
			distance += Math.abs(knownFrequencies[x] - observedFreq[x]);
		}
		return(distance);
	}

	//This method finds a int value that will be used as the key to decipher the cipherText
	public int findNumberOfPositions(String cipherText){
		int smallestIndex = 0;
		double [] indexArray = new double [NUMBER_OF_LETTERS];

		//We are going through all possible shifts (up to 25) to see and storing those distances into the indexArray.
		for(int i = 0; i < NUMBER_OF_LETTERS; i ++){

			setObservedFreq(decrypt(cipherText,i));
			indexArray[i] = findDistance();

		}

		//Determine which index in the array has the smallest distance
		double currentValue = indexArray[0];
		for (int j=0; j < NUMBER_OF_LETTERS; j++) {
			if (indexArray[j] < currentValue)
			{
				currentValue = indexArray[j];
				smallestIndex = j;
			}
		}
		return smallestIndex; //The index is returned and will be used for the key when the message is decrypted
	}

	//Read in a file that contains cipher text decrypt it using the key that was found in the findNumberOfPositions method
//then write the plain text into a output file.
	public int decrypt(String cipherTextFileName, String outputFileName) {
		String tempString = "";


		try {
			@SuppressWarnings("resource")
			Scanner FileIO = new Scanner(new File(cipherTextFileName)).useDelimiter("[ *-,!?.]+");

			while(FileIO.hasNext()){
				tempString += FileIO.next().toLowerCase();//read into a file and store lower case text it into tempString
			}
			FileIO.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.exit(0);
		}

		numberOfPositions = findNumberOfPositions(tempString); //call our findNumberOfPositions method to find the key

		try {
			Scanner scan = new Scanner(new File(cipherTextFileName));
			PrintWriter writer = new PrintWriter(new File(outputFileName));
			while(scan.hasNextLine()){
				writer.println(decrypt(scan.nextLine(), numberOfPositions)); //key is then used to decrypt the message and gets
				//printed into another file.
			}
			writer.close();
			scan.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.err.println(e);
			System.exit(0);
		}

		return numberOfPositions;
	}

	//Caesar Cipher encrypt and decrypt methods
	public String decrypt(String ciphertext, int numberOfPositions) {
		// TODO Auto-generated method stub
		return encrypt(ciphertext, -numberOfPositions);
	}

	public String encrypt(String msg, int offset){

		offset = offset % 26 + 26;
		StringBuilder encoded = new StringBuilder();
		for (char i : msg.toCharArray()) {
			if (Character.isLowerCase(i)) {
				int j = (i - 'a' + offset) % 26;
				encoded.append((char) (j + 'a'));
			}
			else if(Character.isUpperCase(i)){
				int h = (i - 'A' + offset) % 26;
				encoded.append((char) (h + 'A'));
			}
			else {
				encoded.append(i);
			}
		}
		return encoded.toString();
	}
	public static void main(String[] args) {
		// args[0] contains the filename of the training file
		// args[1] contains the filename of the cipher text file
		// args[2] contains the filename of the output file
		Bruteforcer cb = new Bruteforcer();
		cb.train(args[0]);
		cb.decrypt(args[1], args[2]);
	}
}


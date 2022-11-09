package com.surnin.dmitrii.caesarcodeapp;

public class Encoder {
	public static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

	// create encryptData() method for encrypting user input string with given shift key
	public static String encode(String inputStr, int key)
	{
		String encryptStr = "";

		for (int i = 0; i < inputStr.length(); i++)
		{
			if (Character.isLetter(inputStr.charAt(i))){
				int pos = ALPHABET.indexOf(inputStr.charAt(i));
				int encryptPos = (key + pos) % 52;
				char encryptChar = ALPHABET.charAt(encryptPos);
				encryptStr += encryptChar;
			}
			else {
				encryptStr += (char)(inputStr.charAt(i));
			}
		}
		return encryptStr;
	}

	public static String decode(String encrypted, int key) {
		return encode(encrypted, -key);
	}
}

package com.surnin.dmitrii.caesarcodeapp;

public class Encoder {
	public static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

	// create encryptData() method for encrypting user input string with given shift key
	public static String encode(String inputStr, int key)
	{
		 String encryptStr = "";
		String ALPHABET;
		boolean language = Character.UnicodeBlock.of(inputStr.charAt(1)).equals(Character.UnicodeBlock.CYRILLIC)?true:false;
		if (language)
		ALPHABET = ALPHABET_EN;
		else ALPHABET = ALPHABET_RU;

		for (int i = 0; i < inputStr.length(); i++)
		{
			if (Character.isLetter(inputStr.charAt(i))){
				int pos = ALPHABET.indexOf(inputStr.charAt(i));
				int encryptPos = ((key + pos) % ALPHABET.length()); //
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
		boolean language = Character.UnicodeBlock.of(encrypted.charAt(1)).equals(Character.UnicodeBlock.CYRILLIC)?true:false;
		String ALPHABET;
		if (language)
			ALPHABET = ALPHABET_EN;
		else ALPHABET = ALPHABET_RU;
		int deckey = (ALPHABET.length()-key)%ALPHABET.length();
		return encode(encrypted, deckey);
	}
}

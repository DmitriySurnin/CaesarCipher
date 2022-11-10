package com.surnin.dmitrii.caesarcodeapp;

public class Encoder {
	public static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

	// create encryptData() method for encrypting user input string with given shift key
	public static String encode(String inputStr, int key)
	{
		 String encryptStr = "";
		 String ALPHABET = "";
		if (Character.UnicodeBlock.of(inputStr.charAt(1)).equals(Character.UnicodeBlock.CYRILLIC))
		ALPHABET = ALPHABET_EN;
		else ALPHABET = ALPHABET_RU;

		for (int i = 0; i < inputStr.length(); i++)
		{
			if (Character.isLetter(inputStr.charAt(i))){
				int pos = ALPHABET.indexOf(inputStr.charAt(i));
				int encryptPos = (key + pos) % ALPHABET.length();
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

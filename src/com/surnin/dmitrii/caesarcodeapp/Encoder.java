package com.surnin.dmitrii.caesarcodeapp;

public class Encoder {
	// TODO replace with arrays, maybe make a new class
	public static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

	public static String encode(String inputStr, int key) {
		StringBuilder sb = new StringBuilder();
		String ALPHABET = "";
		if (Character.UnicodeBlock.of(inputStr.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
			ALPHABET = ALPHABET_RU;
		} else {
			ALPHABET = ALPHABET_EN;
		}

		for (int i = 0; i < inputStr.length(); i++) {
			if (Character.isLetter(inputStr.charAt(i))) {
				int pos = ALPHABET.indexOf(inputStr.charAt(i));
				int encryptPos = ((key + pos) % ALPHABET.length()); //
				char encryptChar = ALPHABET.charAt(encryptPos);
				sb.append(encryptChar);

			} else {
				sb.append((char) (inputStr.charAt(i)));
			}
		}
		return sb.toString();
	}

	public static String decode(String encryptStr, int key) {
		String ALPHABET = "";
		if (Character.UnicodeBlock.of(encryptStr.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
			ALPHABET = ALPHABET_RU;
		} else {
			ALPHABET = ALPHABET_EN;
		}
		int deckey = (ALPHABET.length() - key) % ALPHABET.length();
		return encode(encryptStr, deckey);
	}
}

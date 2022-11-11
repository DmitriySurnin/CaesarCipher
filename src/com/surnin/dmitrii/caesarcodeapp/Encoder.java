package com.surnin.dmitrii.caesarcodeapp;

public class Encoder {
	// TODO replace with arrays, maybe make a new class


	public static String encode(String inputStr, int key) {
		StringBuilder sb = new StringBuilder();
		Alphabet alpha = new Alphabet();
		String alphabet = "";
		String s = System.lineSeparator();

		if (Character.UnicodeBlock.of(inputStr.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
			alphabet = String.valueOf(alpha.getAlphabet_ru());
		} else {
			alphabet = String.valueOf(alpha.getAlphabet_en());
		}

		for (int i = 0; i < inputStr.length(); i++) {
			if (((inputStr.charAt(i)) != ('\r')) || ((inputStr.charAt(i)) != '\n')) {
				if (Character.isDigit(inputStr.charAt(i))) {
					sb.append(inputStr.charAt(i));
				} else {
					int pos = alphabet.indexOf(inputStr.charAt(i));
					int encryptPos = ((key + pos) % alphabet.length()); //
					char encryptChar = alphabet.charAt(encryptPos);
					sb.append(encryptChar);
				}
			} else {
				sb.append(inputStr.charAt(i));
			}
		}
		return sb.toString();

	}

	public static String decode(String encryptStr, int key) {
		Alphabet alpha = new Alphabet();
		String alphabet = "";
		if (Character.UnicodeBlock.of(encryptStr.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
			alphabet = String.valueOf(alpha.getAlphabet_ru());
		} else {
			alphabet = String.valueOf(alpha.getAlphabet_en());
		}
		int deckey = (alphabet.length() - key) % alphabet.length();
		return encode(encryptStr, deckey);
	}

}


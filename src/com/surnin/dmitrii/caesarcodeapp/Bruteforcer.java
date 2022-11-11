package com.surnin.dmitrii.caesarcodeapp;

import java.util.HashMap;
import java.util.Map;


public class Bruteforcer {

	public static int bruteForce(String encryptStr) {
		Alphabet alpha = new Alphabet();
		char[] alphabet;
		//String alphabet = "";
		Map<Integer, Integer> keys = new HashMap<>();
		// what language used
		if (Character.UnicodeBlock.of(encryptStr.charAt(0)).equals(Character.UnicodeBlock.CYRILLIC)) {
			alphabet = alpha.getAlphabet_ru();
		} else {
			alphabet = alpha.getAlphabet_en();
		}
		//find pattern and add to map with amount
		for (int i = 0; i < alphabet.length; i++) {
			int count = 0;
			String decoded;
			decoded = Encoder.decode(encryptStr, i);
			if (decoded.indexOf(", ") > 0) {
				for (int j = 0; j < alphabet.length; j++) {
					if (decoded.indexOf(", ") > 0) {
						count++;
					}
				}
			}
			keys.put(i, count);
		}
		//Find max number of usages
		Map.Entry<Integer, Integer> maxEntry = null;
		for (Map.Entry<Integer, Integer> entry : keys.entrySet())
		{ if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			{
				maxEntry = entry;
			}
		}
		return maxEntry.getKey();
	}
}


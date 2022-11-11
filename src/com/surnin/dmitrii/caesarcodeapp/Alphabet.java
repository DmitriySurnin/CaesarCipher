package com.surnin.dmitrii.caesarcodeapp;

public class Alphabet {
	//public static final String ALPHABET_EN = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	//public static final String ALPHABET_RU = "абвгдеёжзийклмнопрстуфхцчшщъыьэюяАБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";

	char[] alphabet_en = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z',
			'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'
			,' ','!','@','#','$','%','^','&','*','(',')','{','}','[',']','\\','|','\'','"',';',':','?','/','>','.',',','<','\r','\n','-','_','=','+'};

	char[] alphabet_ru = {'а','б','в','г','д','е','ё','ж','з','и','й','к','л','м','н','о','п','р','с','т','у','ф','х','ц','ч','ш','щ','ъ','ы','ь','э','ю','я'
			,'А','Б','В','Г','Д','Е','Ё','Ж','З','И','Й','К','Л','М','Н','О','П','Р','С','Т','У','Ф','Х','Ц','Ч','Ш','Щ','Ъ','Ы','Ь','Э','Ю','Я'
			,' ','!','@','#','$','%','^','&','*','(',')','{','}','[',']','\\','|','\'','"',';',':','?','/','>','.',',','<','\r','\n','-','_','=','+'};

	public char[] getAlphabet_en() {
		return alphabet_en;
	}

	public void setAlphabet_en(char[] alphabet_en) {
		this.alphabet_en = alphabet_en;
	}

	public char[] getAlphabet_ru() {
		return alphabet_ru;
	}

	public void setAlphabet_ru(char[] alphabet_ru) {
		this.alphabet_ru = alphabet_ru;
	}
}

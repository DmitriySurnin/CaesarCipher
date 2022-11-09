package com.surnin.dmitrii.caesarcodeapp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Filenames {
	public static String parse (String file){
		Path path = Paths.get(file);
		String filename = path.getFileName().toString();
		return filename;
	}
	public static String rename (String inputFileName){
		String filename = parse(inputFileName);
		String newFilename= inputFileName;
		if (filename.contains("decoded")){
			newFilename = inputFileName.replaceFirst("decoded","encoded");
		}else if (filename.contains("encoded")){
			newFilename = inputFileName.replaceFirst("encoded","decoded");
		}
		else
		{
			int lastDotIndex = inputFileName.lastIndexOf('.');
			newFilename = inputFileName.substring(0, lastDotIndex ) + "(encoded)" + inputFileName.substring(lastDotIndex);
		}
		return newFilename;

	}
}

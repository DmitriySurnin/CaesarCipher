package com.surnin.dmitrii.caesarcodeapp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Filenames {
	public static String rename (String inputFileName){
		Path path = Paths.get(inputFileName);
		String parentDir = path.getParent().toString();
		String filename = path.getFileName().toString();
		StringBuilder sb = new StringBuilder();
		if (filename.contains("decoded")){
			sb.append(parentDir + "\\");
			sb.append(filename.replaceFirst("decoded","encoded"));
			filename = sb.toString();

		}else if (filename.contains("encoded")){
			sb.append(parentDir + "\\");
			sb.append(filename.replaceFirst("encoded","decoded"));
			filename = sb.toString();
		}
		else
		{
			sb.append(parentDir + "\\");
			sb.append(filename.replaceAll("\\.","(encoded)."));
			filename = sb.toString();
		}
		return filename;

	}
}

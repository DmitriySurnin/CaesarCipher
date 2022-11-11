package com.surnin.dmitrii.caesarcodeapp;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Filenames {
	public static String renameFile(String inputFileName) {
		Path path = Paths.get(inputFileName);
		String parentDir = path.getParent().toString();
		String filename = path.getFileName().toString();
		StringBuilder sb = new StringBuilder();
		// that makes the program work in any OS
		String separator = System.getProperty("file.separator");
		if (filename.contains("decoded")) {
			sb.append(parentDir + separator);
			sb.append(filename.replaceFirst("decoded", "encoded"));
			filename = sb.toString();

		} else if (filename.contains("encoded")) {
			sb.append(parentDir + separator);
			sb.append(filename.replaceFirst("encoded", "decoded"));
			filename = sb.toString();
		} else {
			sb.append(parentDir + separator);
			sb.append(filename.replaceAll("\\.", "(encoded)."));
			filename = sb.toString();
		}
		return filename;

	}
}

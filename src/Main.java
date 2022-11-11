import com.surnin.dmitrii.caesarcodeapp.Encoder;
import com.surnin.dmitrii.caesarcodeapp.Filenames;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
	public static void main(String[] args) {
		String command = args[0];
		String filePath = args[1];
		int key = Integer.parseInt(args[2]);
		String outputFilename = "";

		if ("encode".equals(command)) {
			Path file = Path.of(filePath);
			try {
				String original = Files.readString(file, StandardCharsets.UTF_8);
				String encoded = Encoder.encode(original, key);
				outputFilename = Filenames.rename(filePath);
				Files.write(Path.of(outputFilename), encoded.getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		} else if ("decode".equals(command)) {
			Path file = Path.of(filePath);
			try {
				String encoded = Files.readString(file, StandardCharsets.UTF_8);
				String decoded = Encoder.decode(encoded, key);
				outputFilename = Filenames.rename(filePath);
				Files.write(Path.of(outputFilename), decoded.getBytes(StandardCharsets.UTF_8));
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
//		else if ("bruteForce".equals(command)){
//			Path file = Path.of(filePath);
//			try {
//				String encoded = Files.readString(file, StandardCharsets.UTF_8);
//				int keyFound = Bruteforcer.bruteForce(encoded);
//				String decoded = Encoder.decode(encoded, keyFound);
//				outputFilename = Filenames.rename(filePath);
//				Files.write(Path.of(outputFilename), decoded.getBytes(StandardCharsets.UTF_8));
//			} catch (IOException e) {
//				throw new RuntimeException(e);
//			}
//
//
//		}
	}
}
package ygq.study.javase.rmi;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class PathUtil {

	public static Path getPath(String classpath) throws Exception {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL resource = classLoader.getResource(classpath);
		Path path = Paths.get(resource.toURI());
		return path;
	}
	
	public static String getBase64(String classpath) {
		try {
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL resource = classLoader.getResource(classpath);
			byte[] readAllBytes = Files.readAllBytes(Paths.get(resource.toURI()));
			String encodeToString = Base64.getEncoder().encodeToString(readAllBytes);
			return encodeToString;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

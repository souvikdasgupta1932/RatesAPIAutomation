package runner;

import java.io.*;
import java.util.Properties;

public class Inputs {

	public static FileReader reader= null;
	public static String Endpoint = null;
	
	public static void inputs() throws IOException {
		
		reader = new FileReader("./src/main/resources/config.properties");
		Properties p = new Properties();
		p.load(reader);
		Endpoint = p.getProperty("Endpoint");
	}
	
	

}

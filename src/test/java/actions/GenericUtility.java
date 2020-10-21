package actions;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenericUtility {

	static void writeTexttoFile(String filePath, String text) throws IOException {

		File file = new File(filePath+"temp.json");
        file.createNewFile();
		FileWriter fileWriter = new FileWriter(filePath+"temp.json");
	    PrintWriter printWriter = new PrintWriter(fileWriter);
	    printWriter.print(text);
	    printWriter.close();
		

	}

}

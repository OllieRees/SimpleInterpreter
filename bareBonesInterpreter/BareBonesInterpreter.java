package bareBonesInterpreter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import variables.Variable;

public class BareBonesInterpreter {

	public static void main(String[] args) {
		File file = FileSelector.selectFileWindow();
		FileParser fr = null;
		if(file != null)
			try {
				fr = new FileParser(file);
			} catch (FileNotFoundException e) {
				System.err.println("ERROR: File could not be found");
			}
			try {
				if(fr != null)
					FileParser.parseFile();
			} catch(IOException e) {
				System.err.println("File wasn't ready");
			} 
		}
}

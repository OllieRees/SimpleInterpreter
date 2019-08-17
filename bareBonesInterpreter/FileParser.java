package bareBonesInterpreter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import commands.Command;

/** Reads the given file
 * 
 * @author Ollie Rees
 *
 */
public class FileParser {
	
	private static BufferedReader br;
	
	public FileParser(String filepath) throws FileNotFoundException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
	}
	
	public FileParser(File file) throws FileNotFoundException {
		br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	}

	/** Parses the file through the interpreter.  
	 * @throws IOException 
	 * 
	 */
	public static void parseFile() throws IOException { 
		while(br.ready()) {
			FileParser.executeLine();
		}
	}
	
	/** Reads, parses and executes the next line from the file.
	 * 
	 */
	private static void executeLine() {
		String line = readNextLine();
		if(line != null) {
			Command cmd = FileParser.parseLine(line);
			if(cmd != null)		
				cmd.execute();
		}
	}
	
	/** Parse a line, executing the command
	 * 
	 * @param line the line to execute
	 */
	private static Command parseLine(String line) {
		try {
			return CommandParser.determineCommand(line);
		} catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}
	
	/** Reads the next line from the file.
	 * 
	 * @return The next line from the file
	 */
	static String readNextLine() {
		String nextLine = null;
		try {
			nextLine = br.readLine();
		} catch(IOException e) {
			return null;
		}
		
		return nextLine;
	}
}

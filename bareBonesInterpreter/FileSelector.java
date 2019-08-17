package bareBonesInterpreter;

import java.io.File;
import javax.swing.JFileChooser;

public class FileSelector {
	
	public static File selectFileWindow() {
		JFileChooser jfc = new JFileChooser("./");
		int returnValue = jfc.showOpenDialog(null);
		
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File selectedFile = jfc.getSelectedFile();
			System.out.println(selectedFile.getAbsolutePath());
			return selectedFile;
		}
		
		return null;
	}
}

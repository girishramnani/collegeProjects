package notepad;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Filechooser extends JFileChooser {
	FileNameExtensionFilter filefilter;
 public Filechooser() {
	 super();
	 filefilter = new FileNameExtensionFilter("Text files(.txt and .rtf)", "txt","rtf");
	 setFileFilter(filefilter);
	 setVisible(true);
	 
	 
 }
}

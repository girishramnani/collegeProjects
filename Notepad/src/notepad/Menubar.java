package notepad;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class Menubar extends JMenuBar {
	private MainPane mainpane;
	private JMenu file = new JMenu("File");
	private JMenu edit = new JMenu("Edit");
	private JMenuItem newFile = new JMenuItem("New");
	private JMenuItem open = new JMenuItem("Open");
	private JMenuItem close = new JMenuItem("Close");
	private JMenuItem save = new JMenuItem("Save");
	private Integer i ;
	private File openedFile ;
	private MainFrame mainframe;
	private Filechooser filechooser;
	private ActionListener fileOpen = new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {

			int j =filechooser.showSaveDialog(getParent());
			
			if (j==JFileChooser.APPROVE_OPTION){
				Path file = filechooser.getSelectedFile().toPath();
				mainframe.setTitle(file.toString());
			String t = mainpane.getText();
			if(openedFile ==null){
			try {
				
				
				Files.write(file, t.getBytes(), StandardOpenOption.CREATE);
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(mainframe, e1.getCause());
			}
		}
		
			else{
				int k=0;
				try {
					if(!Files.notExists(file, LinkOption.NOFOLLOW_LINKS)){
					 k=JOptionPane.showConfirmDialog(getParent(), "Are you sure about overwriting this file");
					}
				} catch (HeadlessException e2) {
					System.out.println(e2.getCause());
				}
				if(k==JOptionPane.OK_OPTION){
					try {
						Files.write(file,t.getBytes() , StandardOpenOption.CREATE);
					} catch (IOException e1) {

						JOptionPane.showMessageDialog(mainframe, e1.getCause());
					}
					
				}
			}
		
		}
		}
	};
	public Menubar(MainFrame mainFrame) {
		this.mainframe = mainFrame;
		openedFile=null;
		 filechooser = new Filechooser();
		close.addActionListener((ev)-> System.exit(0));
		save.addActionListener(fileOpen);
		newFile.addActionListener((ev)-> {
			mainpane.setText("");
			mainFrame.setTitle("");
			openedFile=null;
		});
		
		
		open.addActionListener((ev)-> {i =filechooser.showOpenDialog(getParent());
		if (i == JFileChooser.APPROVE_OPTION){
			SwingUtilities.invokeLater(()->{
				try {
					openedFile = filechooser.getSelectedFile();
					mainFrame.setTitle(openedFile.getAbsolutePath());
					mainpane.setPage(openedFile.toURI().toURL());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(mainframe, e.getCause());
				}
				
			});
		}
		
		
		});
		
		file.setMnemonic(KeyEvent.VK_F);
		file.add(newFile);
		file.add(open);
		file.add(save);
		file.add(close);
		add(file);
		
	}

	public void setEditorPane(MainPane mainpane) {
		this.mainpane =mainpane;
		
		
	}

}

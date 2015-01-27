package notepad;
import java.awt.BorderLayout;
import java.awt.ScrollPane;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class MainFrame extends JFrame {
	private Menubar menubar ;
	private MainPane mainpane;
	
	public MainFrame() {
		
		changeUi();
		mainpane = new MainPane();
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menubar = new Menubar(this);
		setJMenuBar(menubar);
		menubar.setEditorPane(mainpane);
		add(new JScrollPane(mainpane),BorderLayout.CENTER);
		
		
		
		
		
		
		
		setSize(400,500);
		setVisible(true);
		
		
		
	}
	private void changeUi() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			SwingUtilities.updateComponentTreeUI(this);
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> new MainFrame());
	}

}

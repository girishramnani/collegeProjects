package ty;
//The imports
import java.awt.BorderLayout;
import java.awt.event.WindowListener;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.stream.events.StartDocument;

import com.jsyn.swing.JAppletFrame;

public class MusicPlayer extends Application{

	//Variables to get the height and width of the frame
	static int w, h;
	
	//Initialization method of the JavaFx Application
	static HearDAHDSR applet;
	static JAppletFrame frame2;
	static window1 window;
	static JFrame frame;
	static Panels panel;
	@Override
	public void start(Stage primaryStage)
    {	
		
	}
	
	public static void main(String[] args) {
		
		applet = new HearDAHDSR();
	
		
		//User defined class containing the frames
		window = new window1();
		//window2 window = new window2();
		//then you set to your node
		frame = window.frame();
		frame.setSize(700, 500);
		frame.setVisible(true);
		
		//User defined class containing all panels
		panel = new Panels();
		
		StringBuilder b = new StringBuilder();
		
		//Retrieving the width and height of the frame
		w = frame.getContentPane().getWidth();
		h = frame.getContentPane().getHeight();

		//
		JPanel bar_panel = panel.Bar();
		JPanel tabbed_playlist = panel.Playlist(w,h);
		
		frame.getContentPane().add(bar_panel, BorderLayout.NORTH);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);

		//Adding the Playlist and Library tabs
		tabbedPane.addTab("Playlist", tabbed_playlist);
		tabbedPane.addTab("Library", panel.Library(w,h));
		
//Launch the JavaFx Application
		launch(args);;
	}

}


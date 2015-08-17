package ty;
import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Music {
	Media hit;
	MediaPlayer m;
	int index;
	int cnt= 0;
	String a = "";

	//Mediaplayer method to play
	MediaPlayer play(String s) {
		
		a = new File(s).toURI().toString();
		
		//check if a file is already playing
		try {
			
			if (m.getCurrentTime().toMillis() > 0)
				cnt++;
			if(cnt>=5)
				m.stop();
		} catch (NullPointerException ne) {
		}
		
		hit = new Media(a);
		m = new MediaPlayer(hit);
		
		m.play();
		return m;
	}

	//Method to pause
	void pause(){
		for(int i=0;i<5;i++)
		m.pause();
	}
	
	//Method to resume
	void resume(){
		for(int i=0;i<5;i++)
		m.play();
	}
	
	//Method to stop
	void stop(){
		for(int i=0; i<5 ; i++)
		m.stop();
		cnt=0;
	}
}
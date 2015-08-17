package ty;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.SwingUtilities;

public class ty1 {
	
	
	
	public ty1(){
		
		
		
		
		
	}

	public static void main(String args[]){
		
		/*SwingUtilities.invokeLater(new Runnable(){
			
			public void run(){
				
				new ty1();
				
				}
				
				});
				
			*/
		
		URL oracle = null;
		try {
			oracle = new URL("https://www.google.com?q=define+dog");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        BufferedReader in = null;
		try {
			in = new BufferedReader(
			new InputStreamReader(oracle.openStream()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        String inputLine;
        try {
			while ((inputLine = in.readLine()) != null)
			    System.out.println(inputLine);
		} catch (IOException e) {	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}

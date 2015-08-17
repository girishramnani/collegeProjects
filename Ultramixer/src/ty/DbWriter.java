package ty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

public class DbWriter {
	
	//Method to write data to db file
	void write(String path) {
		try {
			File file = new File(Paths.get("").toAbsolutePath().toString()
					+ "\\db.txt");
			
			//check if a file exists or not
			if (!file.exists()) {
				file.createNewFile();
			}
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(Paths.get("")
					.toAbsolutePath().toString()
					+ "\\db.txt"));
			String line;
			boolean found = false;
			
			//check whether the path already exists in the db
			while ((line = br.readLine()) != null) {
				if (path.equals(line)) {
					found = true;
					break;
				}
			}
			
			//if doesn't exist write to db
			if (found == false) {
				FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);

				BufferedWriter bw = new BufferedWriter(fw);

				bw.write(path + "\n");
				bw.close();
			}else{
				JOptionPane.showMessageDialog(null,"This file already exists","Error",JOptionPane.PLAIN_MESSAGE);
			}
		} catch (IOException e) {
		}
	}

	//Method to read the db
	@SuppressWarnings("resource")
	String[] read() {
		String[] paths = new String[0];
		BufferedReader br = null;
		int i = 0;
		try {
			String line;

			br = new BufferedReader(new FileReader(Paths.get("")
					.toAbsolutePath().toString()
					+ "\\db.txt"));

			while ((line = br.readLine()) != null) {
				i++;
			}
			if (i > 0)
				paths = new String[i];
			br = new BufferedReader(new FileReader(Paths.get("")
					.toAbsolutePath().toString()
					+ "\\db.txt"));
			i = 0;
			while ((line = br.readLine()) != null) {
				paths[i] = line;
				i++;
			}

			br.close();
		} catch (IOException e) {

		}
		return paths;
	}
}

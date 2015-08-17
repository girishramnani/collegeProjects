package ty;

//Imports
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.nio.file.Path;

import javafx.scene.media.MediaView;

import javax.sound.sampled.AudioFileFormat;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Panels extends JFrame {
	
	//Auto generated serialID
	/**
	 * 
	 */
	private static final long serialVersionUID = -5060414558371584870L;
	
	//
	int index;
	boolean isPlaying = false, started = false;
	Music m = new Music();
	MediaView mv;
	Path p;
	fileVisitor visitor = new fileVisitor();
	DbWriter db = new DbWriter();
	String[] paths, names;
	AudioFileFormat audio;
	JList<String> list;
	JScrollPane scroll;
	int cnt=0;

	//Method returning the playlist panel. This is dummy
	JPanel Playlist(int w, int h) {

		getContentPane().setLayout(getLayout());
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		String[] pLists = { "Recently Added", "Recently Played" };
		String[] s = { "abc", "xyz" };

		JList<String> list = new JList<String>(pLists);
		JList<String> list1 = new JList<String>(s);
		JScrollPane scroll = new JScrollPane(list);
		JScrollPane scroll1 = new JScrollPane(list1);
		scroll.setPreferredSize(new Dimension((int) (w * 0.3),
				(int) (h * 0.815)));
		scroll1.setPreferredSize(new Dimension((int) (w * 0.65),
				(int) (h * 0.815)));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		panel.add(scroll);
		panel.add(scroll1);
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		return panel;

	}

	//Method returning the library panel
	JPanel Library(int w, int h) {
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		//Create a JFileChooser for adding files/folders to the library
		JFileChooser fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
		
		//Read the paths from the database
		paths = db.read();
		
		//Convert the paths to names to be displayed in the list
		names = new String[paths.length];
		int i = 0;
		for (String x : paths) {
			names[i] = new File(x).getName();
			i++;
		}
		
		//The list of songs to be displayed in the library
		list = new JList<String>(names);
		JPanel panel = new JPanel();
		
		//Buttons for adding music files
		JButton add = new JButton("Add Folder to Library");
		JButton addf = new JButton("Add File to Library");
		//JButton addf = new JButton("Add File to Library");
		//JButton addf = new JButton("Add File to Library");
		panel.add(add);
		panel.add(addf);
		scroll = new JScrollPane(list);
		scroll.setPreferredSize(new Dimension((int) (w * 0.95),
				(int) (h * 0.75)));
		getContentPane().setLayout(getLayout());
		panel.add(scroll);

		//Action listener for adding folder button
		add.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				fc.showDialog(Panels.this, "Select Folder");
				try {

					p = fc.getSelectedFile().toPath();
					visitor.visit(p);
					paths = db.read();
					int i = 0;
					names = new String[paths.length];
					System.out.println(paths.length);
					for (String x : paths) {
						names[i] = new File(x).getName();
						i++;
					}
				} catch (NullPointerException ne) {

				}

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		
		//Action listener for adding file button
		addf.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				fc.setFileFilter(new FileNameExtensionFilter("Audio", "mp3"));
				fc.showDialog(Panels.this, "Select File");

				try {
					p = fc.getSelectedFile().toPath();
					db.write(p.toString());
					paths = db.read();
					int i = 0;
					names = new String[paths.length];
					System.out.println(paths.length);
					for (String x : paths) {
						names[i] = new File(x).getName();
						i++;
					}
				} catch (NullPointerException e) {

				}
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		//Action listener for the list
		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				super.mouseClicked(e);

				// TODO Auto-generated method stub

				@SuppressWarnings("unchecked")
				JList<String> l = (JList<String>) e.getSource();
				if (e.getClickCount() == 2) {
					index = l.locationToIndex(e.getPoint());

					mv = new MediaView(m.play(paths[index]));
					isPlaying = true;
					started = true;
				}

			}
			// public void mouseExit(MouseEvent e){
			// super.mouseExited(e);
			// stop t = new stop();
			// t.run(e);
			// }

		});
		return panel;
	}

	//Method returning the top bar of the music player
	JPanel Bar() {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);

		JButton btnPrevious = new JButton("Previous");
		panel.add(btnPrevious);

		JButton btnPlaypause = new JButton("Play/Pause");
		panel.add(btnPlaypause);

		JButton btnNext = new JButton("Next");
		panel.add(btnNext);

		JButton btnStop = new JButton("Stop");
		panel.add(btnStop);
		
		btnStop.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					m.stop();
					isPlaying = false;
					started = false;
					list.clearSelection();
					
				} catch (Exception ee) {

				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnPlaypause.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (isPlaying == false && started == false) {
					m.play(paths[0]);
					list.setSelectedIndex(0);
					isPlaying = true;
					started = true;
				} else if (isPlaying == true) {
					m.pause();
					isPlaying = false;
				} else if (isPlaying == false && started == true) {
					isPlaying = true;
					m.resume();
					list.setSelectedIndex(0);
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		btnPrevious.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub

				if (index == 0) {
					index = paths.length - 1;
				} else {
					index--;
				}
				m.play(paths[index]);
				list.setSelectedIndex(index);
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

		btnNext.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				if (index == (paths.length - 1)) {
					index = 0;
				} else {
					index++;
				}
				m.play(paths[index]);
				list.setSelectedIndex(index);
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		return panel;
	}
}

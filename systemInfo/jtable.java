import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class jtable extends JFrame{
	private JTable jt ;
	public jtable(){
		setTitle("System Properties");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		jt = new JTable(new model());
		getContentPane().add(new JScrollPane(jt));
		pack();
		setVisible(true);
	}
	public static void main(String[] args) {
		new jtable();
	}

}

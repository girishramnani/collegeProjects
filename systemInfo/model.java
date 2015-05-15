import java.util.Map;
import java.util.Properties;

import javax.swing.table.AbstractTableModel;


public class model extends AbstractTableModel{
	private Map hmap;
	Object[] list;

	public model() {
		super();
		hmap = System.getProperties();
		System.out.println(hmap.size());
		list = hmap.keySet().toArray();
	}
	@Override
	public int getRowCount() {
		return hmap.size();
	}
@Override
public String getColumnName(int column) {
	if (column==0){
		return "Property name";
	}
	else{
		return "Property value";
	}
}
	@Override
	public int getColumnCount() {
		return 2;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex ==0){
			return list[rowIndex];
		}
		else{
			return hmap.get(list[rowIndex]);
		}
	}
	

}

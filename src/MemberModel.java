import java.util.Vector;                                   
import javax.swing.table.AbstractTableModel;               

public class MemberModel extends AbstractTableModel {      

	
	Vector column = new Vector();                      
	Vector list = new Vector();                        

	public MemberModel() {                             
		column.add("이름");
		column.add("출석");
		column.add("결석");
		column.add("지각");
		column.add("주소");
		column.add("연락처");
		column.add("이메일");
	}
	                                                    
	public String getColumnName(int index) {            
		return String.valueOf(column.get(index));
	}
	
	public void setList(Vector list) {                  
		this.list = list;
	}

	@Override
	public int getColumnCount() {                      
		
		return column.size(); 
	}

	@Override 
	public int getRowCount() {                        
		
		return list.size(); 
		
	}

	@Override                                          
	public Object getValueAt(int row, int col) {
		Vector vec = (Vector) list.get(row);
		return vec.get(col);
	}
}
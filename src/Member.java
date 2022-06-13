import java.awt.BorderLayout;   
import java.awt.Choice;          
import java.awt.Color;           
import java.awt.Dimension;        
import java.awt.GridLayout;      
import java.awt.FlowLayout;       

import javax.swing.JButton;      
import javax.swing.JFrame;       
import javax.swing.JOptionPane;  
import javax.swing.JLabel;       
import javax.swing.JPanel;       
import javax.swing.JScrollPane;  
import javax.swing.JTable;       
import javax.swing.JTextField;   

import java.awt.event.ActionEvent;      
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;        
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;     
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;           
import java.awt.event.WindowAdapter;

import java.io.UnsupportedEncodingException;

import java.sql.Connection;                  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Vector;                   
public class Member extends JFrame implements ActionListener {  	 
	
	JLabel l_name, l_attend, l_absent, l_late, l_addr, l_phone, l_phone1, l_phone2, l_email, l_email1;
	JTextField txt_name, txt_attend, txt_absent, txt_late, txt_addr, txt_phone1, txt_phone2, txt_phone3, txt_email_id, txt_email_addr, txt_keyword;
	Choice ch_email, ch_category;
	JTable table;
	JScrollPane scroll;
	JButton bt_regist, bt_edit, bt_delete, bt_select, bt_getListAll, bt_exit;
	JPanel south, north, center, p2, p3, p4, p5, p6, p7;
	                                                              
	String driver = "com.mysql.jdbc.Driver";                      
	String url = "jdbc:mysql://localhost:3306/java_db";       
	String userid = "root";                                       
	String passwd = "123456";

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;

	String sql;
	
	String category;

	MemberModel model = new MemberModel();                         
	int selRow;                                                    
	int idx;                                                       

	public Member() {
		super("학생 관리 프로그램");

		l_name = new JLabel("이    름");           
		l_attend = new JLabel(" 출   석");
		l_absent = new JLabel("결   석");
		l_late = new JLabel("지   각");
		l_addr = new JLabel("주    소");
		l_phone = new JLabel("연락처");
		l_phone1 = new JLabel("-");
		l_phone2 = new JLabel("-");
		l_email = new JLabel("이메일");
		l_email1 = new JLabel("@");

		txt_name = new JTextField(5);              
		txt_attend = new JTextField(2);
		txt_absent = new JTextField(2);
		txt_late = new JTextField(2);
		txt_addr = new JTextField(25);
		txt_phone1 = new JTextField(5);
		txt_phone2 = new JTextField(5);
		txt_phone3 = new JTextField(5);
		txt_email_id = new JTextField(6);
		txt_email_addr = new JTextField(7);
		txt_keyword = new JTextField(6);

		                                         
		ch_email = new Choice();                 
		ch_email.add("URL");
		ch_email.add("naver.com");                
		ch_email.add("google.com");
		ch_email.add("nate.com");
		ch_email.add("paran.com");
		ch_email.add("hanmail.net");
		
		ch_category=new Choice();
		ch_category.add("검색 카테고리");     
		ch_category.add("name");
		ch_category.add("attend");
		ch_category.add("absent");
		ch_category.add("late");
		ch_category.add("addr");
		ch_category.add("phone");
		ch_category.add("email");

		bt_regist = new JButton("등록");                   
		bt_edit = new JButton("수정");
		bt_delete = new JButton("삭제");
		bt_select = new JButton("검색");
		bt_getListAll = new JButton("전체");
		bt_exit = new JButton("종료");

		table = new JTable(model);
		scroll = new JScrollPane(table);

		north = new JPanel();                            
		center = new JPanel();
		south = new JPanel();
		
		p2 = new JPanel();                                
		p3 = new JPanel();
		p4 = new JPanel();
		p5 = new JPanel();
		p6= new JPanel();
		p7= new JPanel();
		
		north.setPreferredSize(new Dimension(500, 220));      
		center.setPreferredSize(new Dimension(500, 300));
		south.setPreferredSize(new Dimension(500, 35));

		add(north, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		
		north.setLayout(new GridLayout(6, 1));                
		north.add(p2);
		north.add(p3);
		north.add(p4);
		north.add(p5);
		north.add(p6);
		
		p2.setLayout(new FlowLayout(FlowLayout.LEFT));
		p3.setLayout(new FlowLayout(FlowLayout.LEFT));
		p4.setLayout(new FlowLayout(FlowLayout.LEFT));
		p5.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		p2.add(l_name);
		p2.add(txt_name);
		p2.add(l_attend);
		p2.add(txt_attend);
		p2.add(l_absent);
		p2.add(txt_absent);
		p2.add(l_late);
		p2.add(txt_late);
		p3.add(l_addr);
		p3.add(txt_addr);
		p4.add(l_phone);
		p4.add(txt_phone1);
		p4.add(l_phone1);
		p4.add(txt_phone2);
		p4.add(l_phone2);
		p4.add(txt_phone3);
		p5.add(l_email);
		p5.add(txt_email_id);
		p5.add(l_email1);
		p5.add(txt_email_addr);
		p5.add(ch_email);
		
		p6.add(bt_regist);
		p6.add(bt_edit);
		p6.add(bt_delete);
		p6.add(bt_exit);

		ch_email.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ie) {
				Object obj = ie.getSource();
				if (obj.equals(ch_email)) {
					if (ch_email.getSelectedIndex() == 0) {
						txt_email_addr.setText("");
					} else {
						// txt_email_addr.setText(ch_email.getSelectedItem());
						txt_email_addr.setText((String) ie.getItem());
					}
				}
			}
		});
		

		center.setLayout(new GridLayout(1, 1));           
		center.add(scroll);
		
		south.setLayout(new GridLayout(1, 1));            
		south.add(p7);

		p7.setLayout(new FlowLayout(FlowLayout.LEFT));    
				p7.setBackground(Color.gray);
				p7.add(ch_category);
				p7.add(txt_keyword);
				p7.add(bt_select);
				p7.add(bt_getListAll);
				
			    ch_category.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent ie) {
						Object obj = ie.getSource();
					 	category=(String)ie.getItem();
					}
			    });

		                                                 
		bt_regist.addActionListener(this);
		bt_edit.addActionListener(this);
		bt_delete.addActionListener(this);
		bt_select.addActionListener(this);
		bt_getListAll.addActionListener(this);
		bt_exit.addActionListener(this);

		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				selRow = table.getSelectedRow();
				System.out.println(selRow);

				String name = (String) table.getValueAt(selRow, 0);
				String attend = table.getValueAt(selRow, 1).toString();
				String absent = table.getValueAt(selRow, 2).toString();
				String late = table.getValueAt(selRow, 3).toString();
				String addr = (String) table.getValueAt(selRow, 4);
				String phone = (String) table.getValueAt(selRow, 5);
				String email = (String) table.getValueAt(selRow, 6);
				                              
				idx = (Integer) model.getValueAt(selRow, 7);
				System.out.println(idx);

				txt_name.setText(name);
				txt_attend.setText(attend);
				txt_absent.setText(absent);
				txt_late.setText(late);
				txt_addr.setText(addr);
				txt_phone1.setText(phone.substring(0, phone.indexOf("-")));
				txt_phone2.setText(phone.substring(phone.indexOf("-") + 1, phone.lastIndexOf("-")));
				txt_phone3.setText(phone.substring(phone.lastIndexOf("-") + 1));
				txt_email_id.setText(email.substring(0, email.indexOf("@")));
				txt_email_addr.setText(email.substring(email.lastIndexOf("@")+1));
			}
		});

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				closeDB();
				System.exit(0);
			}
		}); // end windowListner

		                                                
		connect();
		getListAll();

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	public boolean connect() {
		boolean isConnect = false;
		try {			                                      
			Class.forName(driver);			                                      
			con = DriverManager.getConnection(url, userid, passwd);
			isConnect = true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return isConnect;
	}

	public void getListAll() {
		String sql = "select * from member order by idx desc";     
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			Vector list = new Vector();
			while (rs.next()) {
				Vector record = new Vector();
				record.add(rs.getString("name"));
				record.add(rs.getInt("attend"));
				record.add(rs.getInt("absent"));
				record.add(rs.getInt("late"));
				record.add(rs.getString("addr"));
				record.add(rs.getString("phone"));
				record.add(rs.getString("email"));
				record.add(rs.getInt("idx"));		
				list.add(record);
			}
			model.setList(list);
			this.repaint();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}	
	
	public int regist() {
		int result = 0;
		sql = "Insert into member(name,attend,absent,late,addr,phone,email)";
		sql = sql + "Value(?,?,?,?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, txt_name.getText());
			pstmt.setInt(2, Integer.parseInt(txt_attend.getText()));
			pstmt.setInt(3, Integer.parseInt(txt_absent.getText()));
			pstmt.setInt(4, Integer.parseInt(txt_late.getText()));
			pstmt.setString(5, txt_addr.getText());
			pstmt.setString(6, txt_phone1.getText()+"-"+ txt_phone2.getText()+"-"+txt_phone3.getText());
			pstmt.setString(7,txt_email_id.getText()+"@"+txt_email_addr.getText());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}

	public int delete() {
		int result = 0;
		String sql = "Delete from member where idx=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return result;
	}
	
	public int edit(){
		int result =0;
		String sql = "Update member set name=?, attend=?, absent=?, late=?, addr=?, phone=?, email=? where idx=?";
		try {
			pstmt = con.prepareStatement(sql);			
			pstmt.setString(1, txt_name.getText());
			pstmt.setInt(2, Integer.parseInt(txt_attend.getText()));
			pstmt.setInt(3, Integer.parseInt(txt_absent.getText()));
			pstmt.setInt(4, Integer.parseInt(txt_late.getText()));
			pstmt.setString(5, txt_addr.getText());
			pstmt.setString(6,	txt_phone1.getText() + "-" + txt_phone2.getText() +"-"+ txt_phone3.getText());
			pstmt.setString(7,	txt_email_id.getText() + "@" + txt_email_addr.getText());
			pstmt.setInt(8, idx);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean select(String category, String keyword){
		boolean result = false;
		String sql ="select * from member where " + category + " like '%" + keyword + "%' order by idx desc";		

		System.out.println(sql);	
		try {
			pstmt=con.prepareStatement(sql);
			rs = pstmt.executeQuery();			
			Vector list = new Vector();
			while (rs.next()) {
				Vector record = new Vector();
				record.add(rs.getString("name"));
				record.add(rs.getInt("attend"));
				record.add(rs.getInt("absent"));
				record.add(rs.getInt("late"));
				record.add(rs.getString("addr"));
				record.add(rs.getString("phone"));
				record.add(rs.getString("email"));
				record.add(rs.getInt("idx")); 
				list.add(record);
			}
			model.setList(list);
			this.repaint();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void clear(){		
		txt_name.setText("");
		txt_attend.setText(""); 
		txt_absent.setText("");
		txt_late.setText("");
		txt_addr.setText(""); 
		txt_phone1.setText(""); txt_phone2.setText(""); txt_phone3.setText("");
		txt_email_id.setText(""); txt_email_addr.setText("");
		txt_name.requestFocus();
	}
	 
	public void closeDB() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(bt_regist)) {
			int result = regist();
			if (result != 0) {
				JOptionPane.showMessageDialog(getParent(), "등록성공");
				getListAll();
				clear();
				table.updateUI();
			} else {
				JOptionPane.showMessageDialog(getParent(), "등록실패");
			}
		}

		if (obj.equals(bt_edit)) {
			if(JOptionPane.showConfirmDialog(getParent(),	(String)table.getValueAt(selRow, 0)+ "님의 정보를 수정 하시겠습니까?")==0){
				int result = edit();
				if(result !=0){
					getListAll();
					clear();
					table.updateUI();
				}
			}
		}

		if (obj.equals(bt_delete)) {
			if(JOptionPane.showConfirmDialog(getParent(),	(String) table.getValueAt(selRow, 0)+ "님의 정보를 삭제 하시겠습니까?")==0){
				int result = delete();
				if (result != 0) {
					getListAll();
					clear();
					table.updateUI();			
				}
			}
		}

		if (obj.equals(bt_select)) {
			select(category, txt_keyword.getText());
		}

		if (obj.equals(bt_getListAll)) {
			getListAll();
		}

		if (obj.equals(bt_exit)) {
			closeDB();
			System.exit(0);

		}
	}

	public static void main(String[] args) {
		Member m = new Member();
	}
}
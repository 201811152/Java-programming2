import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
 
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class Login extends JFrame{
    private Main main;
    private Member Frm;
   
    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;
   
    public static void main(String[] args) {
    }
 
    public Login() {
        setTitle("������ �α���");
        setSize(290, 160);
        setResizable(false);
        setLocation(810, 460);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
       
        JPanel panel = new JPanel();
        placeLoginPanel(panel);
       
        add(panel);
       
        setVisible(true);
    }
   
    public void placeLoginPanel(JPanel panel){
        panel.setLayout(null);     
        JLabel userLabel = new JLabel("ID   :");
        userLabel.setBounds(45, 10, 80, 25);
        panel.add(userLabel);
       
        JLabel passLabel = new JLabel("PW :");
        passLabel.setBounds(45, 35, 80, 25);
        panel.add(passLabel);
       
        userText = new JTextField(20);
        userText.setBounds(85, 10, 160, 25);
        panel.add(userText);
       
        passText = new JPasswordField(20);
        passText.setBounds(85, 35, 160, 25);
        panel.add(passText);
        passText.addActionListener(new ActionListener() {          
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();        
            }
        });
       
        btnInit = new JButton("�ʱ�ȭ");
        btnInit.setBounds(45, 80, 90, 20);
        panel.add(btnInit);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });
       
        btnLogin = new JButton("�α���");
        btnLogin.setBounds(155, 80, 90, 20);
        panel.add(btnLogin);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }
   
    public void isLoginCheck(){
        if(userText.getText().equals("admin") && new String(passText.getPassword()).equals("123456")){
            JOptionPane.showMessageDialog(null, "�α��� ����");
            bLoginCheck = true;

            if(isLogin()){
                main.showFrame();
            }                  
        }else{
            JOptionPane.showMessageDialog(null, "�α��� ����. ���̵� Ȥ�� ��й�ȣ�� Ȯ���ϼ���.");
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
   
    public boolean isLogin() {     
        return bLoginCheck;
    }
 
}
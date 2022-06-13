import javax.swing.JFrame;
 
public class Main{
    Login showlogin;
    Member Frm;
   
    public static void main(String[] args) {
       
        Main main = new Main();
        main.showlogin = new Login();	
        main.showlogin.setMain(main);
    }
   
    public void showFrame(){
    	showlogin.dispose();
        this.Frm = new Member();
    }
}
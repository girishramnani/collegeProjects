package basiclayout;

import javax.swing.JOptionPane;

class LoginPage{ 
        
        public static void main(String arg[]){ 
                try { 
                    Form frame=new Form(); 
                    frame.setResizable(false);
                    frame.setLocation(400, 250);
                    frame.setSize(350,150); 
                    frame.setVisible(true); 
                } 
                catch(Exception e){ 
                    JOptionPane.showMessageDialog(null, e.getMessage()); 
                } 
        } 
    }

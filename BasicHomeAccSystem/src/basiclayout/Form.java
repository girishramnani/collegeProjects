    package basiclayout;

    
    import static basiclayout.SQLHandler.*;
    import javax.swing.*; 
    import java.awt.*; 
    import java.awt.event.*; 
    import java.sql.*; 
    
    class Form extends JFrame implements ActionListener{ 
            JButton SUBMIT; 
            JPanel panel; 
            JLabel label1,label2; 
            final JTextField text1; 
            final JPasswordField text2; 
    
            
            
            Form(){ 
                label1 = new JLabel(); 
                label1.setText("UserName:"); 
                text1 = new JTextField(15); 

                label2 = new JLabel(); 
                label2.setText("Password:"); 
                text2 = new JPasswordField(15); 
                SUBMIT=new JButton("Login"); 
                
                panel=new JPanel(new GridLayout(3,5, 10, 10)); 
                panel.add(label1); 
                panel.add(text1); 
                panel.add(label2); 
                panel.add(text2); 
                panel.add(SUBMIT); 
                
                add(panel,BorderLayout.CENTER); 
                SUBMIT.addActionListener(this); 
                setTitle("LOGIN FORM"); 
                setDefaultCloseOperation(EXIT_ON_CLOSE);
            }
            
        public void actionPerformed(ActionEvent ae){ 
            String value1=text1.getText(); 
            String value2=text2.getText();

            String user1=""; 
            String pass1=""; 
            
                try{ 

                    initialize_connection();


                    ResultSet resultset1 = statement1.executeQuery("SELECT * FROM login where username='"+value1+"' && password='"+value2+"'"); 

                    while (resultset1.next()) { 
                        user1 = resultset1.getString("username"); 
                        pass1 = resultset1.getString("password"); 
                    } 
                    if(value1.equals("") || value2.equals("")){
                        if(value1.equals("") && value2.equals(""))
                        {
                            JOptionPane.showMessageDialog(this,"Gone Mad or what? Enter atleast something in both fields","Error",JOptionPane.ERROR_MESSAGE);
                        } 
                        else if(value1.equals(""))
                        {
                            JOptionPane.showMessageDialog(this,"Ahh! You didn't enter anything in username field","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else if(value2.equals(""))
                        {
                            JOptionPane.showMessageDialog(this,"Ahh! You didn't enter anything in password field","Error",JOptionPane.ERROR_MESSAGE);
                        } 
                    }                    
                    else if (value1.equals(user1) && value2.equals(pass1)) {
                        dispose();
                        JOptionPane.showMessageDialog(this,"Perfect ! Press OK to begin the program");
                        Thread.sleep(2000);
                        dispose();
                        NewJFrame.main();
                    } 
                    
                    else { 
                        JOptionPane.showMessageDialog(this," Incorrect username & password combination ! ","Error",JOptionPane.ERROR_MESSAGE); 
                    } 
                } 
                catch(Exception e){ 
                    System.out.println(e.getMessage()); 
                } 
            } 
        } 
    
     
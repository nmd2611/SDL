import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.io.*;



public class SignupFrame extends JFrame implements ActionListener  {

    JButton b1;
    JLabel l1,l2,l3,l4;
    JTextField t1,t2,t3;
    JPasswordField t4;

    public SignupFrame()
    {
        setLayout(null);

        l1 = new JLabel("First Name");     // creating a  label
        l2 = new JLabel("Last Name");

        l3 = new JLabel("Enter a username");     // creating a  label
        l4 = new JLabel("Create a password");

        t1 = new JTextField(20);    // textfield
        t2 = new JTextField(20);

        t3 = new JTextField(20);    // textfield
        t4 = new JPasswordField(20);

        b1 = new JButton("SIGN UP");

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();



        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("SignUp  Page");
        
    }

    public void setLocationAndSize(){
        l1.setBounds(350,150,120,30);
        l2.setBounds(350,220,120,30);

        l3.setBounds(300,290,320,30);
        l4.setBounds(300,360,320,30);

        t1.setBounds(470,150,120,30);
        t2.setBounds(470,220,120,30);

        t3.setBounds(470,290,120,30);
        t4.setBounds(470,360,120,30);

        b1.setBounds(410,450, 130, 30);

    }

    public void addComponentstoUI(){
        add(l1);
        add(l2);

        add(l3);
        add(l4);

        add(t1);
        add(t2);

        add(t3);
        add(t4);

        add(b1);
        //add(l2);
    }

    

    public void addActionEvent()
    {
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            String u, p;
            String f,l;

            f = t1.getText().toString();
            l = t2.getText().toString();

            u = t3.getText().toString();
            p = t4.getText().toString();

            try{
                Client.registerCustomer(f, l,u, p);
                JOptionPane.showMessageDialog(this, "Registration Successful!!"); 
                Client.sendChoice(1);

                new LoginFrame();
                dispose();
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }

       
            //Client.sendCredentials(u, p);

            //System.out.println(u + "  " + p);
        }

    }

    public String getUsername()
    {
        return t1.getText().toString();
    }

    public String getPassword()
    {
        return t2.getText().toString();
    }
    
}


 class App3 {
    public static void main(String[] args) throws Exception {
        SignupFrame obj = new SignupFrame();

      
    }
}

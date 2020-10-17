import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.io.*;



public class CustomerFrame extends JFrame implements ActionListener  {

    JButton b1,b2,b3,b4;
    JLabel l1;
    

    public CustomerFrame()
    {
        setLayout(null);

        l1 = new JLabel("Customer Page");     // creating a  label

        b1 = new JButton("Login");
        b2 = new JButton("Sign up");
        b3 = new JButton("Chat");
        b4 = new JButton("Exit");

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();



        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("Customer  Page");
        
    }

    public void setLocationAndSize(){
        l1.setBounds(400,50,300,30);

        b1.setBounds(400,150,120,30);
        b2.setBounds(400,220,120,30);        

        b3.setBounds(400,290,120,30);
        b4.setBounds(400,360,120,30);

        // t1.setBounds(470,150,120,30);
        // t2.setBounds(470,220,120,30);

        // b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
    }

    

    public void addActionEvent()
    {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            System.out.println("Login");
            Client.sendChoice(1);

            new LoginFrame();
            dispose();
        }
        else if(e.getSource() == b2)
        {
            System.out.println("Sign Up");
            Client.sendChoice(2);

            new SignupFrame();
            dispose();
        }
        else if(e.getSource() == b3)
        {
            System.out.println("Chat");
            Client.sendChoice(3);
        }
        else
        {
            System.out.println("Exit");
            Client.sendChoice(4);

            dispose();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }

    
}


//  class A {
//     public static void main(String[] args) throws Exception {
//         CustomerFrame obj = new CustomerFrame();

      
//     }
// }

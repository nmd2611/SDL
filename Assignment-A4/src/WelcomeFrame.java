import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.io.*;
import java.util.*;



public class WelcomeFrame extends JFrame implements ActionListener  {

    JButton b1,b2,b3;
    JLabel l1;
    

    public WelcomeFrame()
    {
        setLayout(null);
        setUIFont (new javax.swing.plaf.FontUIResource("Times New Roman",Font.BOLD,17));

        l1 = new JLabel("Welcome To Food Ordering System!");     // creating a  label

        b1 = new JButton("Customer");
        b2 = new JButton("Hotel");
        b3 = new JButton("Exit");

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();


     
        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("Welcome  Page");

        
        
    }

    public void setLocationAndSize(){
        l1.setBounds(350,50,300,30);

        b1.setBounds(400,150,120,30);
        b2.setBounds(400,220,120,30);        

        b3.setBounds(400,290,120,30);

        // t1.setBounds(470,150,120,30);
        // t2.setBounds(470,220,120,30);

        // b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);

        add(b1);
        add(b2);
        add(b3);
        //add(l2);
    }

    

    public void addActionEvent()
    {
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            System.out.println("Customer");
            ServerConnection.sendChoice(1);

            new CustomerFrame();
            dispose();

        }
        else if(e.getSource() == b2)
        {
            System.out.println("Hotel");
            ServerConnection.sendChoice(2);

            new LoginFrame();
            dispose();
        }
        else
        {
            System.out.println("Exit");
            ServerConnection.sendChoice(3);

            dispose();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
    }

    public static void setUIFont (javax.swing.plaf.FontUIResource f){
        Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
          Object key = keys.nextElement();
          Object value = UIManager.get (key);
          if (value instanceof javax.swing.plaf.FontUIResource)
            UIManager.put (key, f);
          }
        } 

    
}


//  class A {
//     public static void main(String[] args) throws Exception {
//         WelcomeFrame obj = new WelcomeFrame();

      
//     }
// }

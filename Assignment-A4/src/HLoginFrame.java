import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.io.*;
import java.util.*;



public class HLoginFrame extends JFrame implements ActionListener  {

    JButton b1;
    JLabel l1,l2,l3;
    JTextField t1;
    JPasswordField t2;

    public HLoginFrame()
    {
        setLayout(null);

        l1 = new JLabel("Enter username");     // creating a  label
        l2 = new JLabel("Enter password");

        t1 = new JTextField(20);    // textfield
        t2 = new JPasswordField(20);

        b1 = new JButton("LOGIN");

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();



        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("Login Page");
        
    }

    public void setLocationAndSize(){
        l1.setBounds(350,150,120,30);
        l2.setBounds(350,220,120,30);

        t1.setBounds(470,150,120,30);
        t2.setBounds(470,220,120,30);

        b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);
        add(l2);

        add(t1);
        add(t2);

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
            String u;
            String p;
            u = t1.getText().toString();
            p = t2.getText().toString();

            Client.sendCredentials(u, p);

            try{
                boolean status = Client.getStatus();

                if(status)
                {
                    System.out.println("Balle Balle");

                    Hotel h = Client.getHotel();
                    //Customer c = Client.getCustomer();

                    System.out.println("Welcome " + h.getUserName() );

                    //Vector<Hotel> hotels = Client.getHotels();

                    new AfterLoginHFrame(h);

                    dispose();

                    ///System.out.println(hotels);
                }
                else{
                    System.out.println("Not balle balle");
                    JOptionPane.showMessageDialog(this, "Invalid Credentials!!"); 
                    Client.sendChoice(1);
                }
            }
            catch(Exception tp)
            {
                tp.printStackTrace();
            }
           

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


//  class App2 {
//     public static void main(String[] args) throws Exception {
//         LoginFrame obj = new LoginFrame();

      
//     }
// }

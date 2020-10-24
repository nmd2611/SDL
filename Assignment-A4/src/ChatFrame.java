import javax.swing.*;
import java.awt.*;
import java.awt.event.*;  
import java.io.*;
import java.util.*;



public class ChatFrame extends JFrame implements ActionListener  {

    JButton b1,b2,b3;
    JLabel l1;
    JTextArea sp1;
    JTextArea t;

    public ChatFrame()
    {
        setLayout(null);
        

        l1 = new JLabel("Welcome To Chat Section!");     // creating a  label
        sp1 = new JTextArea("Enter your message to get started");

        b1 = new JButton("Send Message");
        b2 = new JButton("OVER");

        t = new JTextArea();
        // b3 = new JButton("Exit");

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();


     
        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("Chat  Page");

        
        
    }

    public void setLocationAndSize(){
        l1.setBounds(350,50,300,30);

        sp1.setBounds(200,100,600,400);
        sp1.setEditable(false);

        t.setBounds(200,550,300,70);

        b1.setBounds(520,560,160,30);
        b2.setBounds(690,560,120,30);        

        // b3.setBounds(400,290,120,30);

        // t1.setBounds(470,150,120,30);
        // t2.setBounds(470,220,120,30);

        // b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);
        add(sp1);

        add(t);

        add(b1);
        add(b2);

        // add(b1);
        // add(b2);
        // add(b3);
        //add(l2);
    }

    

    public void addActionEvent()
    {
        b1.addActionListener(this);
        b2.addActionListener(this);
        //b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            System.out.println("Send Message");

            // if(sp1.getText().toString().equals(""))
            //     sp1.setText("\t \t \t YOUs : " + t.getText().toString());

           // System.out.println(tp + "  bla bla  " + t.getText().toString());

            //sp1.setText(tp + "\n \t \t \t YOU : " + t.getText().toString() );
            sp1.append("\n \t \t \t YOU : " + t.getText().toString());
            sp1.repaint();
            try{

                String r =  Client.sendMessageToServer(t.getText().toString());

                if(r.equals("OVER"))
                    dispose();

                sp1.setText(sp1.getText().trim() + "\n SERVER : " + r);
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }

            sp1.repaint();
            
            t.setText("");
            //dispose();

        }
        else if(e.getSource() == b2)
        {
            System.out.println("OVER");

            try{

                String r =  Client.sendMessageToServer("OVER");
                
               // sp1.setText(sp1.getText().trim() + "\n SERVER : " + r);
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
            //ServerConnection.sendChoice(2);

            dispose();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
        
    }

    
    
}


//  class A {
//     public static void main(String[] args) throws Exception {
//         WelcomeFrame obj = new WelcomeFrame();

      
//     }
// }

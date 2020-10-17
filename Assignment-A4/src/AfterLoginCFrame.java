import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;  
import java.io.*;
import java.util.*;


public class AfterLoginCFrame extends JFrame implements ActionListener  {

    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    JTable menu,cart;
    Map<String, Integer> m;
    JScrollPane sp1,sp2;
    JTableHeader h;
    JTabbedPane tp; 
    JPanel add,delete;

    JTextField tAdd,tDelete,tQuantity;
    JButton bAdd,bDelete;
    JLabel lAdd,lDelete,lQuantity;
    

    public AfterLoginCFrame(Customer c , Vector<Hotel> hotels)
    {
        setLayout(null);

        l1 = new JLabel("Welcome " + c.getFName());     // creating a  label
        l2 = new JLabel("Hotel " + hotels.get(0).getUserName() + "\'s Menu ");
        l3 = new JLabel("Your Cart");

        add = new JPanel();
        delete = new JPanel();

        add.setLayout(null);
        delete.setLayout(null);

        tp=new JTabbedPane();

        m = hotels.get(0).getMenu();
        String col[] = {"SR. NO.", "ITEM", "M.R.P."};
        String col2[] = {"SR. NO.", "ITEM", "M.R.P.", "QUANTITY"};

        // map to string

        String[][] data = new String[10][3];
        String[][] data2 ={{"1","Ice", "100", "2"}};

        

        //ArrayList<ArrayList<String>> a = new ArrayList<ArrayList<String>>();
        int count = 0;

        for(Map.Entry<String,Integer> entry : m.entrySet()){
            data[count][0] = (count+1) + "";
             data[count][1] = entry.getKey();
             data[count][2] = entry.getValue().toString();
                count++;
        }
        
        menu = new JTable(data,col);
        menu.setEnabled(false);

        cart = new JTable(data2, col2);
        cart.setEnabled(false);

        // h = new JTableHeader()
        // h = menu.getTableHeader();
        
        sp1 = new JScrollPane(menu);
        sp2 = new JScrollPane(cart); 

        b1 = new JButton("Place Order");

        tAdd = new JTextField();
        tDelete = new JTextField();
        tQuantity = new JTextField();

        bAdd = new JButton("Add");
        bDelete = new JButton("Delete");

        lAdd = new JLabel("Item Name");
        lQuantity = new JLabel("Quantity");
        lDelete = new JLabel("Item Name");

        //add(sp1);
       // sp1.setViewportView(menu);
       // sp1.setRowHeaderView(menu);
        
      //  menu.setFillsViewportHeight(true); 

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();

        // data[5][0] = "6";
        // data[5][1] = "tes";
        // data[5][2] = "testing";



        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("After  Login  Page");
        
    }

    public void setLocationAndSize(){
        l1.setBounds(450,10,300,30);
        l2.setBounds(450,60,300,30);
        l3.setBounds(690,420,300,30);

        sp1.setBounds(250,100,500,150);
        sp2.setBounds(550,450,400,150);

        b1.setBounds(660,610,150,30);

        tp.setBounds(70,400,400,180);

        // Add Item Panel
        lAdd.setBounds(5,5,150,30);
        tAdd.setBounds(5,50,150,30);

        lQuantity.setBounds(170,5,150,30);
        tQuantity.setBounds(170,50,150,30);

        bAdd.setBounds(100,90,100,30);

        // Delete Item Panel
        lDelete.setBounds(75,5,150,30);
        tDelete.setBounds(75,50,150,30);

        bDelete.setBounds(100,90,100,30);

        // b1.setBounds(400,150,120,30);
        // b2.setBounds(400,220,120,30);        

        // b3.setBounds(400,290,120,30);

        // t1.setBounds(470,150,120,30);
        // t2.setBounds(470,220,120,30);

        // b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);
        add(l2);
        add(l3);

        add(sp1);
        add(sp2);

        add(b1);

        tp.add("Add Item", add);
        tp.add("Delete Item", delete);

        add.add(lAdd);
        add.add(lQuantity);
        add.add(tAdd);
        add.add(tQuantity);
        add.add(bAdd);


        delete.add(lDelete);
        delete.add(tDelete);
        delete.add(bDelete);

        add(tp);
        
      //  add(menu);
      //  add(h);

        // add(b1);
        // add(b2);
        // add(b3);
        //add(l2);
    }

    

    public void addActionEvent()
    {
         b1.addActionListener(this);
        // b2.addActionListener(this);
        // b3.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            JOptionPane.showMessageDialog(this, "Your order has been placed successfully. Thank You!");  
            // System.out.println("Customer");
            // ServerConnection.sendChoice(1);

            //new CustomerFrame();
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

    
}


//  class A {
//     public static void main(String[] args) throws Exception {
//         AfterLoginCFrame obj = new AfterLoginCFrame();

      
//     }
// }

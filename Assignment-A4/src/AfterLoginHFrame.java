import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.awt.event.*;  
import java.io.*;
import java.util.*;


public class AfterLoginHFrame extends JFrame implements ActionListener  {

    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    JTable menu,cart;
    Map<String, Integer> m;
    JScrollPane sp1,sp2;
   
    JTabbedPane tp; 
    JPanel add,delete, modify;

    JTextField tAdd,tDelete,tPrice, tModify, tNewPrice;
    JButton bAdd,bDelete, bModify;
    JLabel lAdd,lDelete, lPrice, lModify, lNewPrice;

    String[][] data;
    String[][] data2;

    int idx;

    Customer c ;
    Vector<Hotel> hotels;

    Hotel h;
    

    public AfterLoginHFrame(Hotel h)
    {
        setLayout(null);
        idx = 0;

        this.h = h;

        // this.c = c;
        // this.hotels = hotels;

         l1 = new JLabel("Welcome " + h.getUserName());     // creating a  label
        // l2 = new JLabel("Hotel " + hotels.get(0).getUserName() + "\'s Menu ");
        // l3 = new JLabel("Your Cart");

         add = new JPanel();
         delete = new JPanel();
         modify = new JPanel();

         add.setLayout(null);
         delete.setLayout(null);
         modify.setLayout(null);

         tp=new JTabbedPane();

         m = h.getMenu();
         String col[] = {"SR. NO.", "ITEM", "M.R.P."};
        // String col2[] = {"SR. NO.", "ITEM", "M.R.P.", "QUANTITY"};

        // // map to string

          data = new String[10][3];
        //  data2 =new String[10][4];

        

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

        // cart = new JTable(data2, col2);
        // cart.setEnabled(false);

        // // h = new JTableHeader()
        // // h = menu.getTableHeader();
        
         sp1 = new JScrollPane(menu);
        // sp2 = new JScrollPane(cart); 

         b1 = new JButton("Exit");

        tAdd = new JTextField();
        tDelete = new JTextField();
        tPrice = new JTextField();
        tNewPrice = new JTextField();
        tModify = new JTextField();


        bAdd = new JButton("Add");
        bDelete = new JButton("Delete");
        bModify = new JButton("Modify");

        lAdd = new JLabel("Item Name");
        lPrice = new JLabel("MRP");
        lDelete = new JLabel("Item Name");
        lModify = new JLabel("Item Name");
        lNewPrice = new JLabel("New Price");

      

        setLocationAndSize();
        addComponentstoUI();
        addActionEvent();

        setVisible(true);   // shows the application window
        setSize(1000,700);   // set size of the app window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        setLocation(500,200);   // an offset for the window from top left 
        setTitle("After  Login  Page  for  Hotel");
        
    }

    public void fetchMenuAgain()
    {
        m = h.getMenu();
        int count = 0;
        int i;

        for(i=0;i<data.length;i++)
        {
            data[i][0] = null;
            data[i][1] = null;
            data[i][2] = null;
        }


        for(Map.Entry<String,Integer> entry : m.entrySet()){
            data[count][0] = (count+1) + "";
             data[count][1] = entry.getKey();
             data[count][2] = entry.getValue().toString();
                count++;
        }
    }

    public void setLocationAndSize(){
        l1.setBounds(450,10,300,30);
        //l2.setBounds(450,60,300,30);
        //l3.setBounds(690,420,300,30);

         sp1.setBounds(250,100,500,150);
        // sp2.setBounds(550,450,400,150);

         b1.setBounds(660,610,150,30);

        tp.setBounds(70,400,400,180);

        // Add Item Panel
        lAdd.setBounds(5,5,150,30);
        tAdd.setBounds(5,50,150,30);

        lPrice.setBounds(170,5,150,30);
        tPrice.setBounds(170,50,150,30);

        bAdd.setBounds(100,90,100,30);

        // Delete Item Panel
        lDelete.setBounds(75,5,150,30);
        tDelete.setBounds(75,50,150,30);

        bDelete.setBounds(100,90,100,30);

        // Modify Item Panel
        lModify.setBounds(5,5,150,30);
        tModify.setBounds(5,50,150,30);

        lNewPrice.setBounds(170,5,150,30);
        tNewPrice.setBounds(170,50,150,30);

        bModify.setBounds(100,90,100,30);

        // b1.setBounds(400,150,120,30);
        // b2.setBounds(400,220,120,30);        

        // b3.setBounds(400,290,120,30);

        // t1.setBounds(470,150,120,30);
        // t2.setBounds(470,220,120,30);

        // b1.setBounds(410,300, 100, 30);

    }

    public void addComponentstoUI(){
        add(l1);
       // add(l2);
      //  add(l3);

         add(sp1);
        // add(sp2);

        add(b1);

         tp.add("Add Item", add);
         tp.add("Delete Item", delete);
         tp.add("Modify Item", modify);

        add.add(lAdd);
        add.add( lPrice);
        add.add(tAdd);
        add.add(tPrice);
        add.add(bAdd);


        delete.add(lDelete);
        delete.add(tDelete);
        delete.add(bDelete);


        modify.add(lModify);
        modify.add( lNewPrice);
        modify.add(tModify);
        modify.add(tNewPrice);
        modify.add(bModify);

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
         bAdd.addActionListener(this);
         bDelete.addActionListener(this);
         bModify.addActionListener(this);
         
    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == b1)
        {
            //JOptionPane.showMessageDialog(this, "");  

            dispose();
            dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));


        }
        else if(e.getSource() == bAdd)
        {
            System.out.println("Item added");


            if(!tAdd.getText().equals("") && !tPrice.getText().equals(""))
            {

                Hotel.addItemToDb(h.getUserId(), tAdd.getText().toString() , Integer.parseInt(tPrice.getText()) );

                // data2[idx][0] = (idx+1) + "";
                // data2[idx][1] = tAdd.getText().toString();
                // data2[idx][2] = m.get(data2[idx][1])+ "";
                // data2[idx][3] =  tPrice.getText().toString() ;
                 idx++;

                 tAdd.setText("");
                 tPrice.setText("");

                fetchMenuAgain();
                menu.repaint();

                JOptionPane.showMessageDialog(this, "Item added to Menu!");
            }
            else{
                JOptionPane.showMessageDialog(this, "Fields cannot be empty!"); 
            }
        }
        else if(e.getSource() == bDelete)
        {
            System.out.println("Item deleted");
            String item = tDelete.getText().toString();

            Hotel.deleteItemFromDb(h.getUserId() , item);
            // data[idx - 1][0]  = null;
            // data[idx - 1][1]  = null;
            // data[idx - 1][2]  = null;
            idx--;
            fetchMenuAgain();
            menu.repaint();

            tDelete.setText("");

            JOptionPane.showMessageDialog(this, "Item deleted from menu!"); 
            


            //ServerConnection.sendChoice(3);

            // dispose();
            // dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }

        else if(e.getSource() == bModify)
        {
            System.out.println("Item modified");
        

            Hotel.modifyItemToDb(Integer.parseInt(tNewPrice.getText().toString()), h.getUserId(), tModify.getText().toString());
            
            fetchMenuAgain();
            menu.repaint();

            tModify.setText("");
            tNewPrice.setText("");

            JOptionPane.showMessageDialog(this, "Item Modified from menu!"); 
            


            //ServerConnection.sendChoice(3);

            // dispose();
            // dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));

        }
    }

    
}



import java.io.*;
import java.util.*;
import java.sql.*;

public class Customer implements Serializable {

     static Scanner sc = new Scanner(System.in) ;

    private int id;
    private String userName, fName, lName;
    private String email;
    private long phone;
    private String password;

    // name,price,quantity
    private ArrayList<MyTriplet<String, Integer, Integer>> arr;

    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    static{

        try{
            Class.forName("org.mariadb.jdbc.Driver");
            //System.out.println("Driver loaded");
        
            con = DriverManager.getConnection("jdbc:mariadb://localhost/SDL1", "root", "kalilinux");
               
            System.out.println("Connection established again");
    
            stmt = con.createStatement();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    

    Customer(int id, String userName, String fName, String lName, String email, long phone, String password) {
        this.id = id;
        this.userName = userName;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.phone = phone;
        this.password = password;

       

        arr = new ArrayList<MyTriplet<String, Integer, Integer>>();

        

    }

    public String getUserName() {
        return userName;
    }

    public String getFName() {
        return fName;
    }

    public String getLName() {
        return lName;
    }

    public int getUserId() {
        return id;
    }

    public String getUserEmail() {
        return email;
    }

    public long getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public void afterLogin(Vector<Hotel> hotels) {
        // Scanner sc = new Scanner(System.in);

        System.out.println("List Of Hotels :");
       
        hotels.forEach(t -> {
            System.out.println((hotels.indexOf(t) + 1)  + ". " + t.getUserName());
           
        });

        System.out.println("*************************");
        System.out.print("Select a Hotel from the list : ");
        int ch;
        ch = sc.nextInt();
        ch--;
        order(hotels.get(ch));

        // sc.close();
    }

    public void addItem(Hotel h) {
        // Scanner sc = new Scanner(System.in);
        String n;
        int price, quantity;
        System.out.print("Enter item name : ");
        n = sc.next();

        price = h.findPrice(n);
        //System.out.println(price);

        if (price == -1)
            System.out.println("Item not found!");
        else {
            System.out.print("Enter item quantity : ");
            quantity = sc.nextInt();

            MyTriplet<String, Integer, Integer> t = new MyTriplet<String, Integer, Integer>(n, price, quantity);
            arr.add(t);

            System.out.println("\nItem added to cart !\n");

        }

        // sc.close();
    }

    // delete not working
    public void deleteItem() {
        // Scanner sc = new Scanner(System.in);
        String n;
        System.out.print("Enter item to be deleted : ");
        n = sc.next();

        arr.forEach((t) -> {
            String item = t.getFirst();
            if (n.equals(item)) {
                int p = t.getSecond();
                int q = t.getThird();

                System.out.println(item + "  " + p + "  " + q);

                MyTriplet<String, Integer, Integer> temp = new MyTriplet<String, Integer, Integer>(item, p, q);

                System.out.println(arr.indexOf(temp));

                arr.remove(temp);

            }
        });
        System.out.println("Success");
        // if (del == 1)
        // System.out.println("Item deleted successfully !");
        // else
        // System.out.println("Item not found !");

        // sc.close();
    }

    public void displayItems() {
        // Scanner sc = new Scanner(System.in);
        System.out.println("*************************");
        System.out.println("Name \t Price \t Quantity");
        if(arr.isEmpty())
        {
            System.out.println("Your cart is empty \n");
            
        }
        else 
        {
            arr.forEach((t) -> {
                System.out.printf("%-5s %5s %10s" , t.getFirst() , t.getSecond(), t.getThird());
                System.out.println();
                //System.out.println(t.getFirst() + " \t " + t.getSecond() + " \t " + t.getThird());
            });
        }
        

        System.out.println("*************************");

        // sc.close();
    }

    public void checkout(int hotel_id)
    {
        int total = 0;
       int i =0;
       for(i=0;i < arr.size();i++)
       {
           total += (arr.get(i).getSecond() * arr.get(i).getThird());
       }

       try{
        stmt.executeUpdate("insert into orders(customer_id, amount, hotel_id) values (" + id + ","+ total + "," + hotel_id + ")");
      // System.out.println(id+" "+ total + " " + hotel_id);
    }
       catch(Exception e)
       {
           System.out.println(e.getMessage());
       }

       System.out.println("Your Total amount is Rs."+ total);
       System.out.println("Thank You");
        
    }

    public void order(Hotel h) {
        // Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to " + h.getUserName());

        System.out.println("Menu Card -> ");
        h.displayItems();

        int ch = 1;
        do {
            System.out.println("1. Add item to Cart \n2. Delete item from Cart \n3. Display Cart \n4. Checkout \n5. Exit");

            ch = sc.nextInt();

            switch (ch) {
                case 1: // Add Item
                    addItem(h);
                    break;

                case 2:
                    // Delete Item
                    deleteItem();
                    break;

                case 3:

                    // Display list
                    displayItems();
                    break;

                case 4: 
                    checkout(h.getUserId());
                    break;

            }

        } while (ch != 5 && ch != 4);

        // sc.close();
    }

}
import java.util.*;
import java.io.*;
import java.sql.*;

public class Hotel implements Serializable {

    static Scanner sc = new Scanner(System.in);

    private int id;
    private String userName;
    private String password;

    // map for storing the list of dishes
    public Map<String, Integer> map;

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

    Hotel(int id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;

        map = new HashMap<String, Integer>();

        try{

            rs =  stmt.executeQuery("select * from items where hotel_id = " + id );

            while(rs.next()){
                map.put(rs.getString(3), rs.getInt(4));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public String getUserName() {
        return userName;
    }

    public int getUserId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void addItem() {
       // Scanner sc = new Scanner(System.in);
        String n;
        int p;
        System.out.print("Enter item name : ");
        n = sc.next();

        System.out.print("Enter item price : ");
        p = sc.nextInt();

        map.put(n, p);
      //  sc.close();
    }

    public void deleteItem() {
        //Scanner sc = new Scanner(System.in);
        String n;
        System.out.print("Enter item to be deleted : ");
        n = sc.next();

        boolean exists = map.containsKey(n);

        if (exists)
            map.remove(n);
        else
            System.out.println("Item not found !");

           // sc.close();

    }

    public void modifyItem() {
     //   Scanner sc = new Scanner(System.in);
        String n;
        System.out.print("Enter item name : ");
        n = sc.next();

        int newp;
        System.out.print("Enter new price : ");
        newp = sc.nextInt();

        map.replace(n, newp);

        System.out.println("Item modified successfully !");

       // sc.close();
    }

    public void displayItems() {

        System.out.println("Item name \t Price");
        System.out.println("*************************");
        map.forEach((k, v) -> {
            System.out.printf("%-15s %5s" , k,v);
            System.out.println();
            //System.out.println("     --->    Price : " + v);
        });
        System.out.println("*************************");

    }

    public int findPrice(String item) {

        if (map.get(item) != null)
            return map.get(item);

        return -1;
    }

    public void afterLogin() {

       // Scanner sc = new Scanner(System.in);

        int ch = 1;
        do {
            System.out.println("1. Add Item \n2. Delete Item \n3. Modify Item \n4. Display List \n5. Exit");
            ch = sc.nextInt();

            switch (ch) {

                case 1:
                    // Add Item
                    addItem();
                    break;

                case 2:
                    // Delete Item
                    deleteItem();
                    break;

                case 3:
                    // Modify Item
                    modifyItem();
                    break;

                case 4:
                    // Display list
                    displayItems();
                    break;

                case 5:
                    break;

            }

        } while (ch != 5);
       // sc.close();
    }
}

// A Java program for a Server 
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Server  {
    // declaring socket and input stream

    static ServerSocket ss; // to establish a connection
    static Socket soc;

    static BufferedReader in; // for accepting input from client
    static PrintWriter out; // for sending/writing data to client

    static LinkedList<Customer> cust;
    static Vector<Hotel> hotels;

    static ObjectOutputStream oout;
    static ObjectInputStream oin;

    static ArrayList<ClientHandler> clients;

    // Required for implementing threadpool
    static ExecutorService pool ;

    // Required for database connectivity
    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    static {

        cust = new LinkedList<Customer>();
        hotels = new Vector<Hotel>();
        
        try {
            // reading data from client
            Class.forName("org.mariadb.jdbc.Driver");
            System.out.println("Driver loaded");
    
            con = DriverManager.getConnection("jdbc:mariadb://localhost/SDL1", "root", "kalilinux");
           
            System.out.println("Connection established");

            stmt = con.createStatement();

            pool = Executors.newFixedThreadPool(4);
           
          

        } catch (Exception e) {
            System.out.println(e);

            
        }
    }

    public static void forCustomer() throws UnknownHostException, IOException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        int ch;
        /* 1. login
            2. signup
            3. exit    
        */
        do{
         ch = Integer.parseInt(in.readLine()); 
        System.out.println("Server received -" + ch);

        System.out.println(Thread.currentThread().getName());

        if(ch == 1)
        {
            System.out.println("Inside 1");

            String userName, password;
        userName = in.readLine();
        password = in.readLine();

        System.out.println(userName + "  " + password);
        boolean found = false;

        try{
            rs = stmt.executeQuery("select * from customer where userName = " + "\"" + userName + "\" and password = " + "\"" + password + "\""  );
        
        if(rs.next()){
            Customer tp = new Customer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), "TP", 123, rs.getString(5));
            
            out.println(true);
            found = true;

            // sending the customer object
            oout.writeObject(tp);

            rs = stmt.executeQuery("select * from hotel");

            Vector<Hotel> newH = new Vector<Hotel>();
            while(rs.next())
            {
                Hotel temp = new Hotel(rs.getInt(1), rs.getString(2) , rs.getString(3));
                newH.add(temp);
            }

            // sending the list of hotels
            oout.writeObject(newH);
             //c.afterLogin(hotels);

             System.out.println("Login successful!");

        }
        else{
            out.println(false);
            System.out.println("Invalid credentials !!");
        }
    }
        catch(Exception e)
        {
            System.out.println(e);
        }
        }
        else if(ch == 2)
        {
            // signup not working
            Customer c1 = (Customer)oin.readObject();
            System.out.println("User data received.");

            try{
                String u = c1.getUserName();
                String p = c1.getPassword();
                String f = c1.getFName();
                String l = c1.getLName();
                System.out.println(u+ " " + p);
                stmt.executeUpdate("INSERT INTO customer(fname,lname,userName, password) values ( \"" + f + "\" , \"" + l + "\" , \"" + u + "\" , \"" + p + "\" )" ) ;
                System.out.println("User registered.");
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
           
            
         //   cust.add(c1);

          
         //  out.println("User added successfully!");
        }

        else if(ch == 3)
        {
            // chatbox
            System.out.println("Inside Chat");
            String s;
            String response;
            
            while(true)
            {
                s = (String)oin.readObject();
                System.out.println("[FROM CLIENT] : " + s);

                //if(s.toUpperCase().equals("OVER"))
                //oout.writeObject("OVER");

                System.out.print("[SERVER (Enter your message)] : ");
                response = sc.nextLine();

                

                System.out.println("Waiting for client's response..");
                oout.writeObject(response);

                if(response.toUpperCase().equals("OVER"))
                break;
            }

        }
    }while(ch != 4);

        
        sc.close();
    }

    public static void forHotel() throws IOException {

        boolean found = false;
        
        System.out.println("Inside Hotel");
        String userName, password;

        userName = in.readLine();
        password = in.readLine();

        System.out.println(userName + "  " + password);

        found = false;

        try{
            rs = stmt.executeQuery("select * from hotel where userName = " + "\"" + userName + "\" and password = " + "\"" + password + "\""  );
            
           
            if(rs.next()){
                Hotel tp = new Hotel(rs.getInt(1), rs.getString(2), rs.getString(3));
                
                out.println(true);
                found = true;
                

                oout.writeObject(tp);
        }
        else{
            System.out.println("Invalid credentials !!");
        }
        
        if(found)
        {
            System.out.println("hotel Login success");
        }
    }
        catch(Exception e){

        }
    

    }

  

    public static void main(String args[]) throws IOException, ClassNotFoundException{

        // cust.add(new Customer(1, "nmd", "N", "D", "a@g.com", 12345, "12345"));
        // cust.add(new Customer(2, "abc", "A", "B", "abcd@g.com", 54321, "54321"));

        // hotels.add(new Hotel(101, "Jyoti", "admin"));
        // hotels.add(new Hotel(102, "Krishna", "admin"));

        clients = new ArrayList<>();

        ss = new ServerSocket(5000);

        while(true)
           {
            System.out.println("[SERVER] Waiting for clients...");
           
            soc = ss.accept();

            in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

            // sending data to client
            out = new PrintWriter(soc.getOutputStream(), true);
    
            // setting up object stream
           // System.out.println("hey");
           oout = new ObjectOutputStream(soc.getOutputStream());
           oin = new ObjectInputStream(soc.getInputStream());
    

            System.out.println("Client connected successfully on PORT 5000.");

            ClientHandler clientThread = new ClientHandler(soc);

            clients.add(clientThread);

            pool.execute(clientThread);
           }
       
    }
}
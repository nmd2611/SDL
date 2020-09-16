
// A Java program for a Server 
import java.net.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    static ExecutorService pool = Executors.newFixedThreadPool(4);

    static {

        cust = new LinkedList<Customer>();
        hotels = new Vector<Hotel>();
        
        try {
            // reading data from client
           
           

           
          

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
        //System.out.println("Server received --" + ch);

        if(ch == 1)
        {
            String userName, password;
        userName = in.readLine();
        password = in.readLine();

        System.out.println(userName + "  " + password);

        boolean found = false;
        for (Customer c : cust) {
            if (c.getUserName().equals(userName) && c.getPassword().equals(password)) {

                // out.println("Welcome " + c.getUserName());
                out.println(true);
                found = true;

                // sending the customer object
                oout.writeObject(c);

                // sending the list of hotels
                oout.writeObject(hotels);
                 //c.afterLogin(hotels);

                 System.out.println("Login successful!");

                // Perform necessary action after login
                // redirect user to the ordering screen where he/she will be able to place
                // orders
            }
        }
        if (!found) {
            out.println(false);
            System.out.println("Invalid credentials !!");
        }

        // out.println(found);
        }
        else if(ch == 2)
        {
            // signup not working
            Customer c1 = (Customer)oin.readObject();
            System.out.println("User data received.");
            cust.add(c1);

            System.out.println("User registered.");
           out.println("User added successfully!");
        }

        else if(ch == 3)
        {
            // chatbox
          //  System.out.println("Chat");
            String s;
            String response;
            
            while(true)
            {
                s = (String)oin.readObject();
                System.out.println("[FROM CLIENT] : " + s);

                if(s.toUpperCase().equals("OVER"))
                break;

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

        System.out.println("For Hotel");
        String userName, password;

        userName = in.readLine();
        password = in.readLine();

        System.out.println(userName + "  " + password);

        boolean found = false;
        for (Hotel h : hotels) {
            if (h.getUserName().equals(userName) && h.getPassword().equals(password)) {

                // out.println("Welcome " + c.getUserName());
                oout.writeObject(true);
                found = true;

                oout.writeObject(h);
            }
        }

        if(!found)
        {
            System.out.println("Invalid credentials !!");
        }


    }

    public static void main(String args[]) throws IOException, ClassNotFoundException{

        cust.add(new Customer(1, "nmd", "N", "D", "a@g.com", 12345, "12345"));
        cust.add(new Customer(2, "abc", "A", "B", "abcd@g.com", 54321, "54321"));


        hotels.add(new Hotel(101, "Jyoti", "admin"));
        hotels.add(new Hotel(102, "Krishna", "admin"));

       

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

// A Java program for a Client 
import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    // initialize socket and input output streams

    static Scanner sc ;
    static Socket soc;
    static BufferedReader br;

    static BufferedReader in;
    static PrintWriter out;

    static ObjectOutputStream oout;
    static ObjectInputStream oin;

    static {
        sc =  new Scanner(System.in);
        try {
            

            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void forCustomer() throws ClassNotFoundException, IOException {

        int ch;
        do{
        System.out.println(" ********** Customer Page ********** ");
        System.out.println("1. Login \n2. SignUp \n3. Chat \n4. Exit");

       
        String userName, password;
        ch = sc.nextInt();

     //   System.out.println(Thread.currentThread().getName());

        out.println(ch);
        if (ch == 1) {
            System.out.print("Enter UserName : ");
            userName = sc.next();
            System.out.print("Enter Password : ");
            password = sc.next();

            out.println(userName);
            out.println(password);

            boolean found = Boolean.parseBoolean(in.readLine());
            System.out.println(found);

            Customer c;
            if (found) {
            c = (Customer) oin.readObject();

            System.out.println("Welcome " + c.getUserName());

             Vector<Hotel> hotels = (Vector<Hotel>) oin.readObject();

             c.afterLogin(hotels);

            } else {
            System.out.println("Invalid Credentials");
            }
            
        }
        else if(ch == 2)
        {   
            // Signup
            //String userName, password;
            String fName, lName, email;
            long phone;
            System.out.print("Enter First Name : ");
            fName = sc.next();

            System.out.print("Enter Last Name : ");
            lName = sc.next();

            // System.out.print("Enter Phone no. : ");
            // phone = sc.nextLong();

            // System.out.print("Enter Email id : ");
            // email = sc.next();

            System.out.print("Select a user name : ");
            userName = sc.next();

            System.out.print("Enter password : ");
            password = sc.next();


            Customer n = new Customer(3, userName, fName, lName, "test", 12345, password);

            // sending the user data to the server
            oout.writeObject(n);

            String msg = in.readLine();

            System.out.println(msg);
        }

        else if(ch == 3)
        {
            // chat box
            System.out.println("Welcome to the chat section. ");
            System.out.println("Type something to interact with the server (Type OVER to close the chat section)");
            
            String s,response;

            while(true)
            {
                System.out.print("[CLIENT (Enter your message)] : ");
                s = sc.nextLine();
                oout.writeObject(s);

                if(s.toUpperCase().equals("OVER"))
                break;

                System.out.println("Waiting for server's response..");
                response =(String) oin.readObject();
                System.out.println("[FROM SERVER] : " + response);

                if(response.toUpperCase().equals("OVER"))
                break;
            }
            
        }
    }while(ch != 4);
        
        
    }

    public static void forHotel() throws ClassNotFoundException, IOException {

        int ch;
            System.out.println(" ********** Hotel Page ********** ");
       // System.out.println("1. Login \n2. SignUp \n3. Chat \n4. Exit");
            String userName, password;
        
    
            System.out.print("Enter UserName : ");
            userName = sc.next();
            System.out.print("Enter Password : ");
            password = sc.next();

            out.println(userName);
            out.println(password);

             boolean found = (boolean)oin.readObject();
             //System.out.println(found + " tp");

             Hotel h;
             if (found) {
                h = (Hotel) oin.readObject();

             System.out.println("Welcome " + h.getUserName());

            //  Vector<Hotel> hotels = (Vector<Hotel>) oin.readObject();

              h.afterLogin();
            } else {
            System.out.println("Invalid Credentials");
            }
 

    }

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        
        System.out.println("Client started.");
         soc = new Socket("localhost", 5000);

         // sending data to server
         out = new PrintWriter(soc.getOutputStream(), true);

         // for getting input from server
         in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

         // setting up object stream
         oin = new ObjectInputStream(soc.getInputStream());
         oout = new ObjectOutputStream(soc.getOutputStream());

         ServerConnection serverOut = new ServerConnection(soc);

         new Thread(serverOut).start();

    }
}
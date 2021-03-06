import java.io.*;
import java.net.*;
import java.util.*;


public class ClientHandler implements Runnable {
    
    static Socket soc;

    static BufferedReader in; // for accepting input from client
    static PrintWriter out; // for sending/writing data to client

    static ObjectOutputStream oout;
    static ObjectInputStream oin;

    ClientHandler(Socket socket_client) throws IOException
    {
        soc = socket_client;
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream() , true);
    }

    @Override
    public void run()
    {
        try {

            String m1 = "********** WELCOME TO THE FOOD ORDERING SYSTEM **********";
            String m2 = "1. Customer  2. Admin  3. Exit ";
            out.println(m1);

            int ch;
            do{
            out.println(m2);

             ch = Integer.parseInt(in.readLine());

            System.out.println("Server received " + ch);

            switch (ch) {
                case 1:
                    // customer
                    Server.forCustomer();
                    break;
                case 2:
                    // admin
                    Server.forHotel();
                    break;

                case 3:
                    // exit
                    break;
                default:

                    break;

            }
        }while(ch != 3);

        System.out.println("Server shutting down.");
        
            
        } catch (Exception e) {
            System.out.println(e);
        }
        finally{

            try{
                // in.close();
                soc.close();
                // out.close();
            }

            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }
}
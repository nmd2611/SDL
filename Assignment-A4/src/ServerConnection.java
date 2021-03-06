
import java.io.*;
import java.net.*;
import java.util.*;


public class ServerConnection implements Runnable {
    public static Socket soc;
    public BufferedReader in;

     public static PrintWriter out;

     public static WelcomeFrame wframe;

    public ServerConnection(Socket s) throws IOException {
        soc = s;
        in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
        out = new PrintWriter(soc.getOutputStream(), true);
    }

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        try {
            int ch;
            // Accepting the welcome message
            System.out.println(in.readLine());
            do{
            System.out.println(in.readLine());

            wframe  = new WelcomeFrame();

             ch = sc.nextInt();

            out.println(ch);

            switch (ch) {
                case 1:
                    // customer
                    Client.forCustomer();
                    break;

                case 2:
                    // hotel
                    Client.forHotel();
                    break;

                case 3:
                    break;
            }
        }while(ch != 3);

            System.out.println("Thank You!");

            

        } catch (Exception e) {
            System.out.println(e);
        }
        finally{
            try{
                soc.close();
                // in.close();
            
                // sc.close();
            }

            catch(Exception e)
            {
                System.out.println(e.getMessage());
            }
        }

    }

    public static void sendChoice(int ch)
    {
       // System.out.println("before Value sent");
        out.println(ch);
        //System.out.println("Value sent");
    }

}
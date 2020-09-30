import java.io.*;
import java.util.*;

public class App {

    Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int user;

        LinkedList<Customer> cust = new LinkedList<Customer>();
        Vector<Hotel> hotels = new Vector<Hotel>();

        cust.add(new Customer(1, "nmd", "N", "D", "a@g.com", 12345, "12345"));
        cust.add(new Customer(2, "abc", "A", "B", "abcd@g.com", 54321, "54321"));

        hotels.add(new Hotel(101, "Jyoti", "admin123"));
        hotels.add(new Hotel(102, "Krishna", "admin"));

        System.out.println("");
        System.out.println("********** WELCOME TO THE FOOD ORDERING SYSTEM **********");

        System.out.println("1. Customer \n2. Admin \n3. Exit ");
        user = sc.nextInt();

        switch (user) {
            case 1:
                System.out.println(" ********** Customer Page ********** ");
                System.out.println("1. Login \n2. SignUp \n3. Exit");
                int ch;
                ch = sc.nextInt();
                if (ch == 1) {
                    // Login

                    String userName, password;

                    System.out.print("Enter UserName : ");
                    userName = sc.next();
                    System.out.print("Enter Password : ");
                    password = sc.next();

                    boolean found = false;
                    for (Customer c : cust) {
                        if (c.getUserName().equals(userName) && c.getPassword().equals(password)) {
                            System.out.println("Welcome " + c.getUserName());
                            found = true;

                            c.afterLogin(hotels);

                            // Perform necessary action after login
                            // redirect user to the ordering screen where he/she will be able to place
                            // orders
                        }
                    }
                    if (!found) {
                        System.out.println("Invalid credentials !!");
                    }

                } else if (ch == 2) {
                    // Sign Up
                    String userName, password;
                    String fName, lName, email;
                    long phone;
                    System.out.println("Enter First Name : ");
                    fName = sc.next();

                    System.out.println("Enter Last Name : ");
                    lName = sc.next();

                    System.out.println("Enter phone no. : ");
                    phone = sc.nextLong();

                    System.out.println("Enter email id : ");
                    email = sc.next();

                    System.out.println("Select a user name : ");
                    userName = sc.next();

                    System.out.println("Enter password : ");
                    password = sc.next();

                    cust.add(new Customer(1, userName, fName, lName, email, phone, password));

                    System.out.println("Signup Successful !!");

                }
                break;

            case 2:
                System.out.println(" ********** Hotel Page ********** ");
                String userName, password;

                System.out.print("Enter UserName : ");
                userName = sc.next();
                System.out.print("Enter Password : ");
                password = sc.next();

                for (Hotel a : hotels) {
                    if (a.getUserName().equals(userName) && a.getPassword().equals(password)) {
                        System.out.println("Welcome " + a.getUserName());

                        a.afterLogin();
                        // perform required actions

                        // redirect admin to page where he can add dishes, remove dishes, modify details
                        // of existing dishes
                    } else
                        System.out.println("Invalid Credentials");
                }

                break;

            case 3:
                break;
        }

        System.out.println("Have a Good Day !!");
        sc.close();

    }
}

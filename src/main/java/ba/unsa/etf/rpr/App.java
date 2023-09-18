package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.GlassesManager;
import ba.unsa.etf.rpr.business.UserManager;
import ba.unsa.etf.rpr.business.OrderManager;
import ba.unsa.etf.rpr.domain.Glasses;
import ba.unsa.etf.rpr.domain.User;
import ba.unsa.etf.rpr.domain.Order;
import ba.unsa.etf.rpr.exceptions.GlassesException;
import org.apache.commons.cli.*;

import java.io.PrintWriter;
import java.util.List;

public class App {

    private static final Option getAllGlasses = new Option("gg", "get-all-glasses", false, "Get all glasses from the database");
    private static final Option getAllUsers = new Option("gu", "get-all-users", false, "Get all users from the database");
    private static final Option getAllOrders = new Option("go", "get-all-orders", false, "Get all orders from the database");


    public static void main(String[] args) {
        CommandLineParser commandLineParser = new DefaultParser();
        Options options = addOptions();

        try {
            CommandLine commandLine = commandLineParser.parse(options, args);

            if (commandLine.hasOption("gg")) {
                try {
                    // Dohvatanje svih naočara i ispis
                    List<Glasses> glassesList = GlassesManager.getAll();
                    for (Glasses glasses : glassesList) {
                        System.out.println("Glasses ID: " + glasses.getId());
                        System.out.println("Name: " + glasses.getName());
                        System.out.println("Category: " + glasses.getCategory());
                        System.out.println("Price: " + glasses.getPrice());
                        System.out.println();
                    }
                    System.out.println("All glasses fetched successfully.");
                } catch (GlassesException e) {
                    System.out.println("Error while fetching glasses: " + e.getMessage());
                }
            } else if (commandLine.hasOption("gu")) {
                try {
                    // Dohvatanje svih korisnika i ispis
                    List<User> userList = UserManager.getAll();
                    for (User user : userList) {
                        System.out.println("User ID: " + user.getId());
                        System.out.println("First Name: " + user.getFirstName());
                        System.out.println("Last Name: " + user.getLastName());
                        System.out.println("Email: " + user.getEmail());
                        System.out.println();
                    }
                    System.out.println("All users fetched successfully.");
                } catch (GlassesException e) {
                    System.out.println("Error while fetching users: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else if (commandLine.hasOption("go")) {
                try {
                    // Dohvatanje svih narudžbi i ispis
                    List<Order> orderList = OrderManager.getAll();
                    for (Order order : orderList) {
                        System.out.println("Order ID: " + order.getId());
                        System.out.println("Glasses ID: " + order.getGlassesID().getId());
                        System.out.println("User ID: " + order.getUserID().getId());
                        System.out.println("Total: " + order.getTotal());
                        System.out.println();
                    }
                    System.out.println("All orders fetched successfully.");
                } catch (GlassesException e) {
                    System.out.println("Error while fetching orders: " + e.getMessage());
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println("Invalid option entered.");
                printFormattedOptions(options);
            }

        } catch (ParseException e) {
            System.out.println("Error while parsing the command line arguments: " + e.getMessage());
            printFormattedOptions(options);
        }
    }

}

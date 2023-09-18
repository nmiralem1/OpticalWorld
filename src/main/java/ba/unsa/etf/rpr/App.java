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
}
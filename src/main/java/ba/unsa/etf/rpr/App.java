package ba.unsa.etf.rpr;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

public class App {

    public static void main(String[] args) {

        try (OutputStream output = new FileOutputStream("src/main/resources/db.properties")) {

            Properties prop = new Properties();

            // set the properties value
            // save properties to project root folder
            prop.store(output, null);

            System.out.println(prop);

        } catch (IOException io) {
            io.printStackTrace();
        }

    }
}
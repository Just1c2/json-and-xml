package training;

import org.xml.sax.SAXException;
import training.model.ProductList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Application {
    static Scanner sc = new Scanner(System.in);
    public static void menu() {
        System.out.println("===============MENU===============");
        System.out.println("1. Load data from database");
        System.out.println("2. Write data to JSON");
        System.out.println("3. Write data to XML");
        System.out.println("4. Find student by name");
        System.out.println("5. Exit");
    }

    public static void main(String[] args) throws SQLException, IOException, ParserConfigurationException, TransformerException, SAXException {
        ProductList productList = new ProductList();
        menu();
        while (true) {
            int choice;
            System.out.println("Your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    productList.displayFromDb();
                    menu();
                    break;
                case 2:
                    productList.writeToJson();
                    menu();
                    break;
                case 3:
                    productList.writeToXml();
                    menu();
                    break;
                case 4:
                    productList.findByName();
                    menu();
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }
}

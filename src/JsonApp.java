import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class JsonApp {
    public static void main(String[] args) throws IOException {
       // create writer
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("customer.json"));

        //create object customer
        JSONObject customer = new JSONObject();//{}
        customer.put("id", 1);
        customer.put("name", "Khoi");
        customer.put("email", "khoi@gmail.com");
        customer.put("age", 23);

        //create customer address
        JSONObject address = new JSONObject();
        address.put("street", "Hai Ba Trung");
        address.put("city", "Hanoi");
        address.put("state", "HBT");

        customer.put("address", address);

        //payment method
        JSONArray pm = new JSONArray();//[]
        pm.addAll(Arrays.asList("Momo", "Visa"));

        customer.put("paymentMethod", pm);

        //create projects
        JSONArray projects = new JSONArray();

        //create 1st project
        JSONObject p1 = new JSONObject();
        p1.put("title", "Java with Json application");
        p1.put("budget", 5000);

        //create 2nd project
        JSONObject p2 = new JSONObject();
        p2.put("title", "Java with XML");
        p2.put("budget", 3000);

        //add projects
        projects.addAll(Arrays.asList(p1, p2));

        //add projects to customer
        customer.put("projects", projects);

        //write json to file
        FileWriter fileWriter = new FileWriter("Customer.json");
        fileWriter.write(customer.toJSONString());
        fileWriter.flush();

    }
}

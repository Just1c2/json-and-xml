package testjson.entity;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class JsonApp {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws IOException {
      /* JsonWriter();
       JsonReader();*/
        jsonEditData();


    }
    public static void JsonReader() throws IOException {
        //Reader reader = Files.newBufferedReader(Paths.get("customer.json"));
        //FileReader reader = new FileReader("customer.json");

        //Parser phan tich file json, xml
        /*ObjectMapper objectMapper = new ObjectMapper();
        JsonNode parser = objectMapper.readTree(reader);

        System.out.println(parser.path("id").asLong());*/

        //gson
        Reader reader = Files.newBufferedReader(Paths.get("customer.json"));
        JsonObject parser = JsonParser.parseReader(reader).getAsJsonObject();

        System.out.println(parser.get("id").getAsLong());
        System.out.println(parser.get("name").getAsString());
        System.out.println(parser.get("email").getAsString());
        System.out.println(parser.get("age").getAsInt());

        //read project

        for (JsonElement project : parser.get("projects").getAsJsonArray()) {
            JsonObject object = project.getAsJsonObject();
            System.out.println(object.get("title").getAsString());
            System.out.println(object.get("budget").getAsDouble());
        }

        //read payment method
        for (JsonElement pm: parser.get("paymentMethod").getAsJsonArray()) {
            System.out.println(pm.getAsString());
        }
        reader.close();


    }

    public static void JsonWriter() throws IOException {
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

    public static void jsonEditData() throws IOException {
        //find by id -> edit element -> update
        JsonReader();
//        JsonElement update = parser.get("projects").getAsJsonArray();
        String changedName;
        System.out.println("Enter id you want to change: ");
        int id1 = sc.nextInt();
        JsonObject person = new JsonObject();
        if (person.get("id").getAsInt() == id1){

        }
        JsonWriter();
    }
}

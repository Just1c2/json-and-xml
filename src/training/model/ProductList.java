package training.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import training.entity.Product;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

import static training.Connect.getConn;


public class ProductList {
    Scanner sc = new Scanner(System.in);
    ArrayList<Product> products = new ArrayList<>();
    private int id1;
    private String name1;
    private String email1;
    private String mobile1;
    public void displayFromDb() throws SQLException {
        Connection connection = getConn();
        String query = "select * from product";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            id1 = resultSet.getInt("id");
            name1 = resultSet.getString("name");
            email1 = resultSet.getString("email");
            mobile1 = resultSet.getString("mobile");
            System.out.println("ID: " + id1 + "   |  Name: " + name1 + "   |  Email: " + email1 + "   |  Mobile: " + mobile1);
            Product s = new Product(id1, name1, email1, mobile1);
            products.add(s);
        }
    }
    public void writeToJson() throws IOException {
        Writer writer = new FileWriter("product.json");
        Gson gson = new Gson();
        gson.toJson(products, writer);
        writer.close();
    }
    public void writeToXml() throws TransformerException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document dom = builder.newDocument();
        Element root = dom.createElement("list");
        dom.appendChild(root);
        for (Product prod : products) {
            Element user = dom.createElement("user");
            Attr attr = dom.createAttribute("id");
            attr.setValue(String.valueOf(prod.getId()));
            Element name = dom.createElement("name");
            name.setTextContent(prod.getName());
            Element email = dom.createElement("email");
            email.setTextContent(prod.getEmail());
            Element phone = dom.createElement("phone");
            phone.setTextContent(prod.getMobile());


            user.appendChild(name);
            user.appendChild(email);
            user.appendChild(phone);

            root.appendChild(user);

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(new DOMSource(dom), new StreamResult((new File("product.xml"))));
        }
    }
    public void findByName() throws IOException, ParserConfigurationException, SAXException {
        //readFromJson();
        //readFromXml();
        System.out.println("Enter name: ");
        String name = sc.next();
        for (Product s : products ) {
            if (s.getName().equals(name)) {
                System.out.println(s);
            }
        }
    }
    public void readFromJson() throws IOException {
        Gson gson = new Gson();
        FileReader reader = new FileReader("product.json");
        products = gson.fromJson(reader, new TypeToken<List<Product>>(){}.getType());

        reader.close();
    }
    public void readFromXml() throws IOException, SAXException, ParserConfigurationException {
        DocumentBuilder builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
        Document dom = builder.parse(new File("product.xml"));
        dom.normalizeDocument();
        Element root = dom.getDocumentElement();
    }
}

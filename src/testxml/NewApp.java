package testxml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static testxml.Connection.getCon;

public class NewApp {
    static int id;
    static String name1;
    static String email1;
    static String phone1;
    static String role1;
    public static void readFromDb() throws SQLException {
        java.sql.Connection con = getCon();
        String query = "select * from student";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()) {
            id = resultSet.getInt(1);
            name1 = resultSet.getString("name");
            email1 = resultSet.getString(3);
            phone1 = resultSet.getString("phone");
            role1 = resultSet.getString("role");
        }
    }
    public static void writeXml() throws ParserConfigurationException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        Document dom = builder.newDocument();
        Element root = dom.createElement("user");
        dom.appendChild(root);
        Attr attr = dom.createAttribute("id");
        attr.setValue(String.valueOf(id));
        root.setAttributeNode(attr);
        Element name = dom.createElement("name");
        name.setTextContent(name1);
        Element email = dom.createElement("email");
        email.setTextContent(email1);
        Element phone = dom.createElement("phone");
        phone.setTextContent(phone1);
        Element role = dom.createElement("role");
        role.setTextContent(role1);

        root.appendChild(name);
        root.appendChild(email);
        root.appendChild(phone);
        root.appendChild(role);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(dom), new StreamResult((new File("student3.xml"))));
    }
    public static void main(String[] args) throws SQLException, ParserConfigurationException, TransformerException {
        readFromDb();
        writeXml();
    }
}

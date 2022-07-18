package testxml;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import testxml.entity.Student;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XmlApp {
    public  static void main(String[] args) throws JAXBException, ParserConfigurationException, TransformerException, IOException, SAXException {
//    writeXml();
//
        readXmlDom();
    }
/*
    public static void writeXml() throws JAXBException {
        //tao file xml
        File file = new File("Student1.xml");

        //jaxb
        JAXBContext context = JAXBContext.newInstance(Student.class);

        Marshaller marshaller = context.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        //tao cau truc xml theo ding dang cua entity
        Student student = new Student(1, "Thanh", "thanh@email.com", new String[]{"Student", "Employee", "Leader"}, "0903532643");

        //chuyen object sang xml file
        marshaller.marshal(student, System.out);
    }package
*/
    public static void writeXmlWithDom() throws ParserConfigurationException, TransformerException {
        DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        Document dom = builder.newDocument();//tao ra doi tuong document

        //tao root element
        Element root = dom.createElement("user");
        dom.appendChild(root);

        //tao ra attribute va cong them vao root
        Attr attr = dom.createAttribute("id");
        attr.setValue("1");
        root.setAttributeNode(attr);

        //tao ra cac the element con cua root aka user(name, email, roles, phone)
        Element name = dom.createElement("name");
        name.setTextContent("Thanh");
        Element email = dom.createElement("email");
        email.setTextContent("thanh@gmail.com");
        Element phone = dom.createElement("phone");
        phone.setTextContent("0903532643");
        Element roles = dom.createElement("roles");
        Element role1 = dom.createElement("role");
        role1.setTextContent("Student");
        Element role2 = dom.createElement("role");
        role2.setTextContent("Employee");
        Element role3 = dom.createElement("role");
        role3.setTextContent("Leader");

        roles.appendChild(role1);
        roles.appendChild(role2);
        roles.appendChild(role3);

        root.appendChild(name);
        root.appendChild(email);
        root.appendChild(phone);
        root.appendChild(roles);

        //DOM -> XML file
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(dom), new StreamResult((new File("student.xml"))));
    }
    public static void readXmlDom() throws ParserConfigurationException, IOException, SAXException {
    //1. Document
        DocumentBuilder builder = DocumentBuilderFactory.newDefaultInstance().newDocumentBuilder();
        Document dom = builder.parse(new File("student.xml"));

        //dinh dang format chuan xml
        dom.normalizeDocument();

        //doc xml
        Element root = dom.getDocumentElement();

        //print attribute
        System.out.println("id: " + root.getAttribute("id"));

        //elements
        System.out.println("name: " + root.getElementsByTagName("name").item(0).getTextContent());
        System.out.println("email: " + root.getElementsByTagName("email").item(0).getTextContent());
        System.out.println("phone: " + root.getElementsByTagName("phone").item(0).getTextContent());


    }
}

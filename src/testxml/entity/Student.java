package testxml.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;

@XmlRootElement
public class Student {
    private int id;
    private String name;
    private String email;
    private String[] roles;
    private String phone;

    public int getId() {
        return id;
    }
    @XmlAttribute
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    @XmlElement
    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", phone='" + phone + '\'' +
                '}';
    }

    @XmlElementWrapper(name = "roles")
    @XmlElement(name = "role")
    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPhone() {
        return phone;
    }
    @XmlElement
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student() {
    }

    public Student(int id, String name, String email, String[] roles, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roles = roles;
        this.phone = phone;
    }
}

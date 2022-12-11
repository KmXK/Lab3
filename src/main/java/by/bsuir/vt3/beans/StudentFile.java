package by.bsuir.vt3.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class StudentFile implements Serializable {
    @XmlElement
    private int id;
    @XmlElement
    private String name;
    @XmlElement
    private double avgGrade;

    public StudentFile(int id, String name, float avgGrade) {
        this.id = id;
        this.name = name;
        this.avgGrade = avgGrade;
    }

    public StudentFile() {
        this(0, "No name", 0.0f);
    }

    @Override
    public String toString() {
        return String.format("Id:%d, Name:%s, Average Grade:%.2f", getId(), getName(), getAvgGrade());
    }
}

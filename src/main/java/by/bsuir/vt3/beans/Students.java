package by.bsuir.vt3.beans;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "students")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Students {
    @XmlElements({
            @XmlElement(name = "student", type = StudentFile.class),
    })
    private List<StudentFile> students;
}

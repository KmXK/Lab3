package by.bsuir.vt3.beans;

import jakarta.xml.bind.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@XmlRootElement(name = "profiles")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Profiles {
    @XmlElements({
            @XmlElement(name = "profile", type = ArchiveProfile.class)
    })
    private List<ArchiveProfile> profiles;
}

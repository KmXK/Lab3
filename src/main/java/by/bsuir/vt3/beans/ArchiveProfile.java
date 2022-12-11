package by.bsuir.vt3.beans;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

@XmlRootElement(name = "profile")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class ArchiveProfile {
    @XmlElement
    private String username;
    @XmlElement
    private String password;
    @XmlElement
    private AccountType accType;

    public ArchiveProfile(String username, String password, AccountType accType) {
        this.username = username;
        this.password = password;
        this.accType = accType;
    }

    public ArchiveProfile() {
        this("guest", "guest", AccountType.GUEST);
    }
}

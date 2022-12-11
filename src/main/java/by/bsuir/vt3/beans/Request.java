package by.bsuir.vt3.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Request implements Serializable {
    private RequestType requestType;
    private String authToken;
    private String[] params;
    private StudentFile student;

    public Request() {
        this.requestType = null;
        this.authToken = null;
        this.params = null;
        this.student = null;
    }
}

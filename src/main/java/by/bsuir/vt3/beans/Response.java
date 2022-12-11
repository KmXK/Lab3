package by.bsuir.vt3.beans;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Response implements Serializable {
    private ResponseType responseType;
    private RequestType requestType;
    private String responseMessage;
    private String[] params;
    private StudentFile[] students;

    public Response() {
        this.responseMessage = null;
        this.responseType = null;
        this.params = null;
        this.students = null;
    }
}

package by.bsuir.vt3.server.controller;

import by.bsuir.vt3.beans.RequestType;
import by.bsuir.vt3.beans.Response;
import by.bsuir.vt3.beans.ResponseType;

public class ResponseConstructor {
    private final RequestType requestType;

    public ResponseConstructor(RequestType requestType) {
        this.requestType = requestType;
    }

    public Response constructFailResponse(String failMessage) {
        System.out.printf("FAIL: %s\n", failMessage);
        var response = new Response();
        response.setRequestType(requestType);
        response.setResponseType(ResponseType.FAIL);
        response.setResponseMessage(failMessage);
        return response;
    }

    public Response constructPassResponse(String passMessage) {
        System.out.printf("PASS: %s\n", passMessage);
        var response = new Response();
        response.setRequestType(requestType);
        response.setResponseType(ResponseType.PASS);
        response.setResponseMessage(passMessage);
        return response;
    }
}

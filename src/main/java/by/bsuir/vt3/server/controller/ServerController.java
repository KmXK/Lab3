package by.bsuir.vt3.server.controller;

import by.bsuir.vt3.beans.Request;
import by.bsuir.vt3.beans.Response;

public interface ServerController {
    Response action(Request request);
}

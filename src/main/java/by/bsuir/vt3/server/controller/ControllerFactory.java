package by.bsuir.vt3.server.controller;

import by.bsuir.vt3.server.controller.impl.ServerControllerImpl;
import lombok.Getter;

@Getter
public final class ControllerFactory {
    private static final ControllerFactory instance = new ControllerFactory();
    private final ServerController serverController = new ServerControllerImpl();

    private ControllerFactory() {
    }

    public static ControllerFactory getInstance() {
        return instance;
    }
}

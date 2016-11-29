package com.pes.takemelegends.Controller;

public class ControllerFactory {

    private static ControllerFactory instance;
    private EventController eventController;

    public static ControllerFactory getInstance() {
        if (instance == null) {
            instance = new ControllerFactory();
        }
        return instance;
    }

    public EventController getEventController() {
        if (eventController == null) eventController = new EventController();
        return eventController;
    }
}

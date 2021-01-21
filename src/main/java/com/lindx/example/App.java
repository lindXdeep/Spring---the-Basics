package com.lindx.example;

import com.lindx.example.beans.Client;
import com.lindx.example.loggers.ConsoleEventLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;

    private ConsoleEventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        super();
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        app.logEvent("Some event for 1");
        app.logEvent("Some event for 2");
    }

    public void logEvent(String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullname());
        eventLogger.logEvent(message);
    }
}

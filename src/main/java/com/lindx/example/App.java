package com.lindx.example;

import com.lindx.example.beans.Client;
import com.lindx.example.beans.Event;
import com.lindx.example.loggers.ConsoleEventLogger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
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

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event for 2");

        ctx.close();
    }

    public void logEvent(Event event, String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullname());
        
        event.setMsg(message);
        eventLogger.logEvent(event);
    }
}


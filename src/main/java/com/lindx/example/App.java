package com.lindx.example;

import java.util.Map;

import com.lindx.example.beans.Client;
import com.lindx.example.beans.Event;
import com.lindx.example.beans.EventType;

import com.lindx.example.loggers.EventLogger;
import com.lindx.example.spring.AppConfig;
;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    private Client client;

    private EventLogger defaultLogger;

    private Map<EventType, EventLogger> loggers; 

    public App(Client client, EventLogger eventLogger, Map<EventType, EventLogger> loggers) {
        super();
        this.client = client;
        this.defaultLogger = eventLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx_app = new AnnotationConfigApplicationContext();
            ctx_app.register(AppConfig.class);
            ctx_app.scan("com.lindx.example");
            ctx_app.refresh();

        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");

        App app = (App) ctx.getBean("app");

        Client client = ctx_app.getBean(Client.class);
        System.out.println(client.getGreeting());

        Event event = ctx_app.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx_app.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx_app.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    public void logEvent(EventType eventType, Event event, String msg) {

        String message = msg.replaceAll(client.getId(), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);
        if(logger == null){
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
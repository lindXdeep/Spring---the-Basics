package com.lindx.example;

import java.util.Map;

import javax.annotation.Resource;

import com.lindx.example.beans.Client;
import com.lindx.example.beans.Event;
import com.lindx.example.beans.EventType;
import com.lindx.example.loggers.EventLogger;
import com.lindx.example.spring.AppConfig;
import com.lindx.example.spring.LoggerConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
@Service
public class App {

    @Autowired
    private Client client;

    @Resource(name = "defaultLogger")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers; 

    public App() {
    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggersMap) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggersMap;
    }

    public static void main(String[] args) {

        AnnotationConfigApplicationContext ctx_app = new AnnotationConfigApplicationContext();
            ctx_app.register(AppConfig.class, LoggerConfig.class);
            ctx_app.scan("com.lindx.example");
            ctx_app.refresh();

        App app = (App) ctx_app.getBean("app");

        Client client = ctx_app.getBean(Client.class);
        System.out.println(client.getGreeting());

        Event event = ctx_app.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx_app.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx_app.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx_app.close();
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
package com.lindx.example.loggers;

import com.lindx.example.beans.Event;

public class ConsoleEventLogger implements EventLogger {

    public ConsoleEventLogger() {
    }

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
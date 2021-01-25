package com.lindx.example.loggers;

import com.lindx.example.beans.Event;

public class ConsoleEventLogger implements EventLogger {

    @Override
    public void logEvent(Event event) {
        System.out.println(event.toString());
    }
}
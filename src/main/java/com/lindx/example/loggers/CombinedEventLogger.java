package com.lindx.example.loggers;

import java.util.Collection;

import javax.annotation.Resource;

import com.lindx.example.beans.Event;

import org.springframework.stereotype.Component;

@Component
public class CombinedEventLogger implements EventLogger {

    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }
}
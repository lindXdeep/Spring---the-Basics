package com.lindx.example.spring;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.Map;

import javax.annotation.Resource;

import com.lindx.example.beans.EventType;
import com.lindx.example.loggers.EventLogger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggerConfig {

    @Resource(name = "consoleEventLogger")
    private EventLogger consoleEventLogger;

    @Resource(name = "fileEventLogger")
    private EventLogger fileEventLogger;

    @Resource(name = "combinedEventLogger")
    private EventLogger combinedEventLogger;

    @Resource(name = "cacheFileEventLogger")
    private EventLogger cacheEventLogger;

    @Bean
    public Collection<EventLogger> combinedLoggers(){
        Collection<EventLogger> loggers = new ArrayList<>(2);
            loggers.add(consoleEventLogger);
            loggers.add(fileEventLogger);
        return loggers;
    }

    @Bean
    public EventLogger defaultLogger() {
        return cacheEventLogger;
    }

    @Bean
    public Map<EventType, EventLogger> loggerMap(){
        Map<EventType, EventLogger> map = new EnumMap<>(EventType.class);
            map.put(EventType.INFO, consoleEventLogger);
            map.put(EventType.ERROR, combinedEventLogger);
        return map; 
    }
}

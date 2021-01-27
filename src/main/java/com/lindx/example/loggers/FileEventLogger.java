package com.lindx.example.loggers;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.annotation.PostConstruct;

import com.lindx.example.beans.Event;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileEventLogger implements EventLogger {

    private File file;

    @Value("${events.file:log/events_log.txt}")     // внедряем стринг значение
    private String filename;

    public FileEventLogger() {
    }

    public FileEventLogger(String filename) {
        this.filename = filename;
    }
    
    @PostConstruct              // помечаем метод как init-метод
    public void init() throws IOException {

        file = new File(filename);

        if(file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("cant write to file: " + filename);
        }else if(!file.exists()){
            
                file.createNewFile();
          
        }
    }

    @Override
    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString(), Charset.forName("UTF-8"),true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

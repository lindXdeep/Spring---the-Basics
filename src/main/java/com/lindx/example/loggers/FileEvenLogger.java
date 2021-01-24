package com.lindx.example.loggers;

import java.io.File;
import java.nio.charset.Charset;

import com.lindx.example.beans.Event;

import org.apache.commons.io.FileUtils;

public class FileEvenLogger implements EventLogger {

    private File file;
    protected String filename;

    public FileEvenLogger(String filename) {
        this.filename = filename;
    }

    public void init() {

        file = new File(filename);

        if(file.exists() && !file.canWrite()){
            throw new IllegalArgumentException("cant write to file: " + filename);
        }else if(!file.exists()){
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new IllegalArgumentException("Cant create log file", e);
            }
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

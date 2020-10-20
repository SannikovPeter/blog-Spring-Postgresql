package com.example.blog.core;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

@Component
public class TitleManager {

    private HashMap<String, String> langMap = new HashMap<>();
    private final String fileLink = TitleLanguage.RU.getLink();

    @PostConstruct
    public void init() {
        HashMap<String, String> result = new HashMap<>();
        File file = new File(fileLink);
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(file));
        } catch (IOException e) {
            System.out.println("File don't download");
        }
        for (HashMap.Entry<Object, Object> entry : properties.entrySet()) {
            result.put((String) entry.getKey(), (String) entry.getValue());
        }
        langMap.putAll(result);
    }

    public HashMap<String, String> getLangMap() {
        return langMap;
    }
}

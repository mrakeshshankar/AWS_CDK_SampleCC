package com.myorg;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestClass {
    
    public static void main(String[] args) {
        List list = List.of(new MediaConcurrency("VOICE", 1), new MediaConcurrency("CHAT", 1));

        try {
            String content = new String(Files.readAllBytes(Paths.get("src/resources/flows/SampleUDARN.json")));
            content = content.replace("{QueueARN}","TEST");
            System.out.println(content);
        } catch (Exception e) {
            // TODO: handle exception
        }
        
    }
}

package com.example.demo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CommandLineDataLoader implements CommandLineRunner {
 
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
 
    @Override 
    public void run(String... strings) throws Exception {
        logger.info("Starting..");
        
    }
}
package com.concept.knowledge.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ConceptConfigServer {
    public static void main (String[] args){
        SpringApplication.run(ConceptConfigServer.class, args);
    }
}

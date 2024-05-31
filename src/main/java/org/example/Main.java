package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new  AnnotationConfigApplicationContext(AppConfig.class);
        ConsoleApplication consoleApplication = context.getBean(ConsoleApplication.class);
        consoleApplication.start();
    }
}
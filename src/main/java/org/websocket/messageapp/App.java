package org.websocket.messageapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Hello world!
 *git remote add origin https://github.com/nandishkotadia/MessageAppMongo.git
git push -u origin master
 */
@SpringBootApplication
@ComponentScan(basePackages = {"org.websocket.message.service","org.websocket.user.service","org.websocket.lastread.service"})
@EnableMongoRepositories(basePackages = "org.websocket.repositories")
public class App
{
   public static void main( String[] args )
   {
      System.out.println( "Hello World!" );
      SpringApplication.run(App.class, args);
   }
}

package pl.justynababinska;

import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import pl.justynababinska.di.LinguController;

@SpringBootApplication
public class SpringExcerciseDiApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(SpringExcerciseDiApplication.class, args);
        LinguController controller = ctx.getBean(LinguController.class);
        controller.mainLoop();
        
        ctx.close();
    }
	
    @Bean
    Scanner scanner() {
        return new Scanner(System.in);
    }
}
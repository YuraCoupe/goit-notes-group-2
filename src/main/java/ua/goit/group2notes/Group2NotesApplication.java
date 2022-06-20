package ua.goit.group2notes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class Group2NotesApplication {

	public static void main(String[] args) {
		SpringApplication.run(Group2NotesApplication.class, args);
	}

}

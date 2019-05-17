package ru.chausov.to_do_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.chausov.to_do_list.configs.YAMLConfig;


@SpringBootApplication
public class ToDoListApplication {

	@Autowired
	private YAMLConfig yamlConfig;


	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext("/");

	}
}

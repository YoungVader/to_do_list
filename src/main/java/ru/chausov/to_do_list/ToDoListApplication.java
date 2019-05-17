package ru.chausov.to_do_list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.chausov.to_do_list.configs.YAMLConfig;


@SpringBootApplication
public class ToDoListApplication {

	@Autowired YAMLConfig yamlConfig;

	public static void main(String[] args) {
		SpringApplication.run(ToDoListApplication.class, args);
	}
}

package ru.chausov.to_do_list.servlets.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chausov.to_do_list.data_base.entities.Task;
import ru.chausov.to_do_list.data_base.entities.User;
import ru.chausov.to_do_list.data_base.entities.Visit;
import ru.chausov.to_do_list.data_base.repositories.TasksRepository;
import ru.chausov.to_do_list.data_base.repositories.UsersRepository;
import ru.chausov.to_do_list.data_base.repositories.VisitsRepository;


import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final VisitsRepository visitsRepository;
    private final UsersRepository usersRepository;
    private final TasksRepository tasksRepository;

    private final EntityManager entityManager;

    @Transactional
    @GetMapping("/visits")
    public Iterable<Visit> getVisits() {
        return visitsRepository.findAll();
    }

    @Transactional
    @GetMapping("/users")
    public Iterable<User> getUsers() {
        List<User> users = entityManager.createQuery("SELECT a FROM User a", User.class).getResultList();

        return usersRepository.findAll();
    }

    @Transactional
    @GetMapping("/tasks")
    public Iterable<Task> getTasks() {
        return tasksRepository.findAll();
    }
}

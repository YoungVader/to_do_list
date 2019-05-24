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
        List<Long> users = entityManager.createQuery("SELECT a.id FROM User a", Long.class).getResultList();

        System.out.println("TEST PRINT");
        for(Long u : users) {
            System.out.println("user id " + u);
        }
       // usersRepository.deleteAll();
       // usersRepository.saveAll(users);
        return usersRepository.findAll();
    }

    @Transactional
    @GetMapping("/tasks")
    public Iterable<Task> getTasks() {
        return tasksRepository.findAll();
    }
}

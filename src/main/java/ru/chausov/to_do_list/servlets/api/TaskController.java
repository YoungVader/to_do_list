package ru.chausov.to_do_list.servlets.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.chausov.to_do_list.data_base.entities.Task;
import ru.chausov.to_do_list.data_base.repositories.TasksRepository;

import javax.transaction.Transactional;


@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TasksRepository tasksRepository;

    @Transactional
    @GetMapping("/table")
    public Iterable<Task> getTasks() {
        return tasksRepository.findAll();
    }
}

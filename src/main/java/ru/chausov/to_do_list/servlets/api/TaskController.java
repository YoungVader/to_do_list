package ru.chausov.to_do_list.servlets.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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

    @Transactional
    @PostMapping("/add")
    public Iterable<Task> addTask(@RequestParam(value = "id", required = false) Long id, Task task) {
        tasksRepository.save(task);

        return tasksRepository.findAll();
    }

    @Transactional
    @PostMapping("/delete")
    public Iterable<Task> deleteTask(@RequestParam(value = "id") Long id) {
        tasksRepository.deleteById(id);

        return tasksRepository.findAll();
    }

    @Transactional
    @PostMapping("/update")
    public Iterable<Task> updateTask(@RequestParam(value = "id") Long id, Task task) {
        tasksRepository.save(task);

        return tasksRepository.findAll();
    }

    @Transactional
    @PostMapping("/done")
    public Iterable<Task> makeTaskDone(@RequestParam(value = "id") Long id, Task task) {
        task.setDone(true);
        tasksRepository.save(task);

        return tasksRepository.findAll();
    }
}

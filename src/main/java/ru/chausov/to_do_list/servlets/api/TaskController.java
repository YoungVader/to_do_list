package ru.chausov.to_do_list.servlets.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.chausov.to_do_list.data_base.entities.Task;
import ru.chausov.to_do_list.data_base.repositories.TasksRepository;

import javax.transaction.Transactional;


@RestController
@Data
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
    @PostMapping("/add") // Нужен ли id?
    public Task addTask(@RequestParam(value = "id", required = false) Long id, Task task) {
        return tasksRepository.save(task);
    }

    @Transactional
    @PostMapping("/delete")
    public void deleteTask(@RequestParam(value = "id") Long id) {
        tasksRepository.deleteById(id);
    }

    @Transactional
    @PostMapping("/update") // посмотри @PathVariable
    public Task updateTask(@RequestParam(value = "id") Long id, Task task) {
        return tasksRepository.save(task);
    }

    @Transactional
    @PostMapping("/done") // посмотри @PathVariable
    public Task setTaskDone(@RequestParam(value = "id") Long id, Task task) {
        task.setDone(true);
        return tasksRepository.save(task);
    }
}

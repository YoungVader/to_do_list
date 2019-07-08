package ru.chausov.to_do_list.servlet.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;

import javax.transaction.Transactional;


@RestController
@Data
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;

    @Transactional
    @GetMapping("/table")
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    @PostMapping("/add")
    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @PostMapping("/delete")
    public void deleteTask(@PathVariable(value = "id") Long id) {
        taskRepository.deleteById(id);
    }

    @Transactional
    @PostMapping("/update")
    public Task updateTask(@PathVariable(value = "id") Long id, Task task) {
        return taskRepository.save(task);
    }

    @Transactional
    @PostMapping("/done")
    public Task setTaskDone(@RequestParam(value = "id") Long id, Task task) {
        task.setDone(true);
        return taskRepository.save(task);
    }
}

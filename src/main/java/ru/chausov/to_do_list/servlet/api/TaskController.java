package ru.chausov.to_do_list.servlet.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Map;


@RestController
@Data
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/table")
    public Iterable<Task> getTasks() {
        return taskRepository.findAll();
    }

    @Transactional
    @PostMapping("/add")
    public ModelAndView addTask(Principal authUser, Task task, Map<String, Object> model) {
        User user = userRepository.findByUsername(authUser.getName());

        task.setUser(user);

        model.put("task", taskRepository.save(task));

        return new ModelAndView("redirect:/index", model);
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

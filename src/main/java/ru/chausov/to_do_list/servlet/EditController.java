package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import java.security.Principal;
import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/edit")
public class EditController {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    @GetMapping("/profile")
    public ModelAndView editProfile(Principal authUser, Map<String, Object> model){
        User user = userRepository.findByUsername(authUser.getName());

        model.put("user", user);

        if(user.getGender().equals("male"))
            model.put("gender", false);
        else if(user.getGender().equals("female"))
            model.put("gender", true);

        return new ModelAndView("edit_profile", model);
    }

    @GetMapping("/task")
    public ModelAndView editTask(String id, Map<String, Object> model){
        Task task = taskRepository.findById(Long.parseLong(id)).get();

        model.put("task", task);

        return new ModelAndView("edit_task", model);
    }
}

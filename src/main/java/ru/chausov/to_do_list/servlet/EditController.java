package ru.chausov.to_do_list.servlet;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.Task;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.TaskRepository;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;

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

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN') or hasAuthority('MODER')")
    public ModelAndView editUser(Principal authUser, Long id, Map<String, Object> model){
        User user = userRepository.findById(id).get();

        model.put("user", user);

//        if(user.getGender().equals("male"))
//            model.put("gender", false);
//        else if(user.getGender().equals("female"))
//            model.put("gender", true);

        User authorisedUser = userRepository.findByUsername(authUser.getName());

        if(authorisedUser.getRoles().contains(Role.ADMIN)) {
            if (user.getRoles().contains(Role.ADMIN))
                model.put("role_admin", true);

            if (user.getRoles().contains(Role.MODER))
                model.put("role_moder", true);

            if (user.getRoles().contains(Role.USER))
                model.put("role_user", true);

            model.put("is_admin", true);
        }
        else
            model.put("is_admin", false);

        if(user.getUsername().equals("admin"))
            model.put("role_changeable", false);
        else
            model.put("role_changeable", true);

        return new ModelAndView("edit_user", model);
    }

    @GetMapping("/task")
    public ModelAndView editTask(Long id, Map<String, Object> model){
        Task task = taskRepository.findById(id).get();

        model.put("task", task);

        return new ModelAndView("edit_task", model);
    }
}

package ru.chausov.to_do_list.servlet;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Collections;
import java.util.Map;


@Data
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/table")
    public ModelAndView getUsers(Map<String, Object> model) {
        model.put("users", userRepository.findAll());

        return new ModelAndView("table_users", model);
    }

    @Transactional
    @PostMapping("/add")
    public ModelAndView addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if(userFromDb != null) {
            model.put("message", "User already exists!");
            return new ModelAndView("registration", model);
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));

        model.put("user", userRepository.save(user));
        return new ModelAndView("redirect:/login", model);
    }

    @Transactional
    @PostMapping("/delete")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateUser(Principal authUser, User user, Map<String, Object> model) {
        User userToUpdate = userRepository.findByUsername(authUser.getName());

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthDate(user.getBirthDate());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setCompany(user.getCompany());
        userToUpdate.setAddress(user.getAddress());

        model.put("user", userRepository.save(userToUpdate));

        return new ModelAndView("redirect:/index", model);
    }
}

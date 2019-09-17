package ru.chausov.to_do_list.servlet;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;
import ru.chausov.to_do_list.data_base.type.Role;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Data
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @GetMapping("/table")
    public ModelAndView getUsers(Principal authUser, Map<String, Object> model) {
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

        Set<Role> roles = new HashSet<>();

        roles.add(Role.USER);

        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        model.put("user", userRepository.save(user));

        return new ModelAndView("redirect:/login", model);
    }

    @Transactional
    @PostMapping("/delete")
    public ModelAndView deleteUser(Principal authUser, Long id) {
        User user = userRepository.findByUsername(authUser.getName());

        if(user.getRoles().contains(Role.ADMIN)) {
            User userToDelete = userRepository.findById(id).get();

            if(!userToDelete.equals("admin"))
                userRepository.deleteById(id);
        }
        return new ModelAndView("redirect:/users/table");
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateUser(Principal authUser, Long id, User user, String userRole, String userEdit, Map<String, Object> model) {
        User updater = userRepository.findByUsername(authUser.getName());

        User userToUpdate = userRepository.findById(id).get();

        boolean updateHimself = false;

        if(updater.getId().equals(id))
            updateHimself = true;

        if(updater.getRoles().contains("ADMIN")
                || updater.getRoles().contains("MODER")
                || updateHimself) {
            userToUpdate.setName(user.getName());
            userToUpdate.setLastName(user.getLastName());
            userToUpdate.setBirthDate(user.getBirthDate());
            userToUpdate.setGender(user.getGender());
            userToUpdate.setCompany(user.getCompany());
            userToUpdate.setAddress(user.getAddress());

            if (userRole != null && !userToUpdate.getUsername().equals("admin")) {
                Set<Role> roles = new HashSet<>();

                if (userRole.equals("admin")) {
                    roles.add(Role.ADMIN);

                    userToUpdate.setRoles(roles);
                } else if (userRole.equals("moder")) {
                    roles.add(Role.MODER);

                    userToUpdate.setRoles(roles);
                } else {
                    roles.add(Role.USER);

                    userToUpdate.setRoles(roles);
                }
            }

            model.put("user", userRepository.save(userToUpdate));

            if (userEdit != null)
                return new ModelAndView("redirect:/users/table", model);

            if (!userToUpdate.getPassword().equals(user.getPassword())) {
                userToUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

                return new ModelAndView("redirect:/logout", model);
            }
        }

        return new ModelAndView("redirect:/index", model);
    }
}

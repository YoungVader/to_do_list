package ru.chausov.to_do_list.servlet;


import lombok.Data;
import lombok.RequiredArgsConstructor;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;


@Data
@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/table")
    public ModelAndView getUsers(Principal authUser, Map<String, Object> model) {
        model.put("users", userRepository.findAll());
//        Iterable<User> users = userRepository.findAll();
//
//        model.put("users", users);
//
//        ArrayList<String> roles = new ArrayList<>();
//
//        Iterator iterator = users.iterator();
//
//        while (iterator.hasNext()) {
//            User user = (User) iterator.next();
//
//            if(user.getRole().equals(Role.ADMIN))
//                roles.add("Admin");
//            else if(user.getRole().equals(Role.MODER))
//                roles.add("Moder");
//            else if(user.getRole().equals(Role.USER))
//                roles.add("User");
//            System.out.println(user.getRole());
//        }
//
//        model.put("roles", roles);

        User user = userRepository.findByUsername(authUser.getName());

        if(user.getRole().equals(Role.ADMIN))
            model.put("role_admin", true);
        else
            model.put("role_admin", false);

        if(user.getRole().equals(Role.MODER))
            model.put("role_moder", true);
        else
            model.put("role_moder", false);

        if(user.getRole().equals(Role.USER))
            model.put("role_user", true);
        else
            model.put("role_user", false);

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

        user.setRole(Role.USER);

        model.put("user", userRepository.save(user));

        return new ModelAndView("redirect:/login", model);
    }

    @Transactional
    @PostMapping("/delete")
    public ModelAndView deleteUser(String id) {
        userRepository.deleteById(Long.parseLong(id));

        return new ModelAndView("redirect:/users/table");
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateUser(String id, User user, String userRole, String userEdit, Map<String, Object> model) {
        User userToUpdate = userRepository.findById(Long.parseLong(id)).get();

        userToUpdate.setName(user.getName());
        userToUpdate.setLastName(user.getLastName());
        userToUpdate.setBirthDate(user.getBirthDate());
        userToUpdate.setGender(user.getGender());
        userToUpdate.setCompany(user.getCompany());
        userToUpdate.setAddress(user.getAddress());
        if(userRole != null){
            if(userRole.equals("admin"))
                userToUpdate.setRole(Role.ADMIN);
            else if(userRole.equals("moder"))
                userToUpdate.setRole(Role.MODER);
            else
                userToUpdate.setRole(Role.USER);
        }

        model.put("user", userRepository.save(userToUpdate));

        if(userEdit != null)
            return new ModelAndView("redirect:/users/table", model);

        if(!userToUpdate.getUsername().equals(user.getUsername())
                || !userToUpdate.getPassword().equals(user.getPassword())){
            userToUpdate.setUsername(user.getUsername());
            userToUpdate.setPassword(user.getPassword());

            return new ModelAndView("redirect:/logout", model);
        }

        return new ModelAndView("redirect:/index", model);
    }
}

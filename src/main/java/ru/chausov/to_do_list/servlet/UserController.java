package ru.chausov.to_do_list.servlet;


import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UserRepository;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Map;


@Data
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;

    @Transactional
    @GetMapping("/table")
    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

//    @Transactional
//    @PostMapping("/add")
//    public User addUser(User user) throws Exception {
////        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
////                getAuthentication().getDetails();
//        return userRepository.save(user);
//    }

    @Transactional
    @PostMapping("/delete")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userRepository.deleteById(id);
    }

//    @Transactional
//    @PostMapping("/update")
//    public User updateUser(@PathVariable(value = "id") Long id, User user) {
//        return userRepository.save(user);
//    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView updateUser(Principal authUser, User user, Map<String, Object> model) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User foundUser = userRepository.findByUsername(authUser.getName());

        if (user.getName() != null)
            foundUser.setName(user.getName());

        if (user.getLastName() != null)
            foundUser.setLastName(user.getLastName());

        if (user.getBirthDate() != null)
            foundUser.setBirthDate(user.getBirthDate());

        if (user.getGender() != null)
            foundUser.setGender(user.getGender());

        if (user.getCompany() != null)
            foundUser.setCompany(user.getCompany());

        if (user.getAddress() != null)
            foundUser.setAddress(user.getAddress());

        model.put("user", userRepository.save(foundUser));

        return new ModelAndView("redirect:/index", model);
    }
}

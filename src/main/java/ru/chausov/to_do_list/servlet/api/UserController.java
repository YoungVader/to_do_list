package ru.chausov.to_do_list.servlet.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.chausov.to_do_list.data_base.entity.User;
import ru.chausov.to_do_list.data_base.repository.UsersRepository;

import javax.transaction.Transactional;


@Data
@RestController
@RequestMapping("/api/users/")
@RequiredArgsConstructor
public class UserController {

    private final UsersRepository usersRepository;

    @Transactional
    @GetMapping("/table")
    public Iterable<User> getUsers() {
        return usersRepository.findAll();
    }

    @Transactional
    @PostMapping("/add")
    public User addUser(User user) throws Exception {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().
//                getAuthentication().getDetails();
        AuthenticationManagerBuilder auth = (AuthenticationManagerBuilder) SecurityContextHolder
        .getContext().getAuthentication();

        InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>
                mngConfig = auth.inMemoryAuthentication();

        String password = "123";

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encryptedPassword = passwordEncoder.encode(password);
        System.out.println("Encoded password of 123=" + encryptedPassword);

        UserDetails newUser = org.springframework.security.core.userdetails.User.
                withUsername("user" + user.getId()).password(encryptedPassword).roles("USER").build();

        mngConfig.withUser(newUser);

        return usersRepository.save(user);
    }

    @Transactional
    @PostMapping("/delete")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    @PostMapping("/update")
    public User updateUser(@PathVariable(value = "id") Long id, User user) {
        return usersRepository.save(user);
    }
}

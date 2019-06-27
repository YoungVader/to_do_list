package ru.chausov.to_do_list.servlet.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    public User addUser(User user) {
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

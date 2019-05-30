package ru.chausov.to_do_list.servlets.api;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.chausov.to_do_list.data_base.entities.User;
import ru.chausov.to_do_list.data_base.repositories.UsersRepository;

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
    public void addUser(@RequestParam(value = "id", required = false) Long id, User user) {
        usersRepository.save(user);
    }

    @Transactional
    @PostMapping("/delete")
    public void deleteUser(@RequestParam(value = "id") Long id) {
        usersRepository.deleteById(id);
    }

    @Transactional
    @PostMapping("/update")
    public void updateUser(@RequestParam(value = "id") Long id, User user) {
        usersRepository.save(user);
    }
}

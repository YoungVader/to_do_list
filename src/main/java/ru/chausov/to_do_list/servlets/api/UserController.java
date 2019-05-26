package ru.chausov.to_do_list.servlets.api;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.chausov.to_do_list.data_base.entities.User;
import ru.chausov.to_do_list.data_base.repositories.UsersRepository;

import javax.transaction.Transactional;
import java.time.LocalDate;


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
    public Iterable<User> addUser(@RequestParam(value = "id", required = false) Long id,
                                  @RequestParam(value = "name", required = false) String name,
                                  @RequestParam(value = "lastName", required = false) String lastName,
                                  @RequestParam(value = "birthDate", required = false) LocalDate birthDate,
                                  @RequestParam(value = "address", required = false) String address,
                                  @RequestParam(value = "company", required = false) String company) {
        User user = User.builder().id(id)
                .name(name)
                .lastName(lastName)
                .birthDate(birthDate)
                .address(address)
                .company(company).build();

        usersRepository.save(user);

        return usersRepository.findAll();
    }
}

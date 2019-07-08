package ru.chausov.to_do_list.data_base.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chausov.to_do_list.data_base.entity.User;


public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}

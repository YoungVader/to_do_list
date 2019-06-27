package ru.chausov.to_do_list.data_base.repository;

import org.springframework.data.repository.CrudRepository;
import ru.chausov.to_do_list.data_base.entity.Task;


public interface TasksRepository extends CrudRepository<Task, Long> {
}

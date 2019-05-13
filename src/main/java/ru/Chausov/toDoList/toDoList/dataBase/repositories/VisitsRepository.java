package ru.Chausov.toDoList.toDoList.dataBase.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.Chausov.toDoList.toDoList.dataBase.entities.Visit;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
}
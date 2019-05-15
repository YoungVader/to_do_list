package ru.chausov.to_do_list.data_base.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.chausov.to_do_list.data_base.entities.Visit;

@Repository
public interface VisitsRepository extends CrudRepository<Visit, Long> {
}
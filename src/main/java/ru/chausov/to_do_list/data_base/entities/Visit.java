package ru.chausov.to_do_list.data_base.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Builder
@Entity
public class Visit {
    @Id
    @GeneratedValue
    final private Long id;

    private String description;
}

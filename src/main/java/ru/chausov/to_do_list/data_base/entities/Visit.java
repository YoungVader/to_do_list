package ru.chausov.to_do_list.data_base.entities;

import lombok.Builder;
import lombok.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Value
@Builder
@Entity
public class Visit {
    @Id
    @GeneratedValue
    Long id;

    String description;
}

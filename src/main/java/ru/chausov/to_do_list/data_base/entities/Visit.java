package ru.chausov.to_do_list.data_base.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Builder
@Getter
@Setter
@Entity
public class Visit {
    @Id
    @GeneratedValue
    public Long id;

    private String description;
}

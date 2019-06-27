package ru.chausov.to_do_list.data_base.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Visit {
    @Id
    @GeneratedValue
    private Long id; //how to make final to get through might be uninitialised?

    private String description;
}

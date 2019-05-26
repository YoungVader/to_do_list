package ru.chausov.to_do_list.data_base.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id; //how to make final to get through might be uninitialised?
    private String name;
    private String lastName;
    private LocalDate birthDate;
    private String address;
    private String company;
}

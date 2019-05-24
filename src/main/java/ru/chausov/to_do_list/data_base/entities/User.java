package ru.chausov.to_do_list.data_base.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id; //how to make final to get through might be uninitialised?
    @Column(name="name")
    private String name;
    @Column(name="lastName")
    private String lastName;
    @Column(name="birthDate")
    private LocalDateTime birthDate;
    @Column(name="address")
    private String address;
    @Column(name="company")
    private String company;
}

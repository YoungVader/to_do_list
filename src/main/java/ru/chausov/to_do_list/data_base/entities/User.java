package ru.chausov.to_do_list.data_base.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@Entity
public class User {
    @Id
    @GeneratedValue
    final private Long id;
    private String name;
    private String lastName;
    private Date birthDate; // Класс Date устарел. LocalDateTime/ZonedDateTime/Instant
    private String address;
    private String company;
}

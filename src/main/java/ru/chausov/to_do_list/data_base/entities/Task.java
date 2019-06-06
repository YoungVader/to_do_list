package ru.chausov.to_do_list.data_base.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@Table(name="tasks")
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id; //how to make final to get through might be uninitialised?
    private String name;
    private String description;

    // по факту ForeignKey, смотри @OneToMany
    private Long userId; //how to make final to get through might be uninitialised?
    private LocalDateTime receivedDate;
    private LocalDateTime toBeDone;
    private boolean done;
}

package ru.chausov.to_do_list.data_base.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
    private LocalDateTime receivedDate;
    private LocalDateTime toBeDone;
    private boolean done;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
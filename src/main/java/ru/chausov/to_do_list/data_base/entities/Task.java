package ru.chausov.to_do_list.data_base.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue
    private Long id; //how to make final to get through might be uninitialised?
    private String name;
    private String description;
    private Long userId; //how to make final to get through might be uninitialised?
    private Date receivedDate;
    private Date toBeDone;
    private boolean done;
}

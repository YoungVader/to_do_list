package ru.Chausov.toDoList.toDoList.dataBase.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Visit {
    @Id
    @GeneratedValue
    public Long id;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

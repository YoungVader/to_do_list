package ru.chausov.to_do_list.data_base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.chausov.to_do_list.data_base.type.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private boolean active;
    private String name;
    private String lastName;
    private String birthDate;
    private String gender;
    private String address;
    private String company;
    private Role role;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,
                fetch = FetchType.EAGER)
    private List<Task> tasks = new ArrayList<>();

}

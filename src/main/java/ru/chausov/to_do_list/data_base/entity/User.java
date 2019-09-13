package ru.chausov.to_do_list.data_base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.chausov.to_do_list.data_base.type.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String company;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @JsonIgnore
    @OneToMany(mappedBy = "user", orphanRemoval = true,
                fetch = FetchType.EAGER)
    private final List<Task> tasks = new ArrayList<>();

}

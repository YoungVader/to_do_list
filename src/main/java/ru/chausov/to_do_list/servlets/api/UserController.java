package ru.chausov.to_do_list.servlets.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.chausov.to_do_list.data_base.entities.User;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


@Data
@Builder
@RequiredArgsConstructor
public class UserController {
    private final Statement statement;

    private boolean createUser(User user) {
        String createQuery = "INSERT INTO Tasks VALUES("
                + user.getId() + ", " + user.getName()
                + ", " + user.getLastName()
                + ", " + user.getBirthDate()
                + ", " + user.getAddress()
                + ", " + user.getCompany()
                + ")";


        boolean created;

        try {
            create(createQuery);

            created = true;
        } catch (SQLException e) {
            created = false;
        }

        return created;
    }

    private boolean updateUser(User user) {
        //some code
        return false;
    }

    private boolean deleteUser(Long id) {
        //some code
        return false;
    }


    public ResultSet create(String sqlCreateQuery) throws SQLException {
        return statement.executeQuery(sqlCreateQuery);
    }


    public ResultSet update(String sqlUpdateQuery) throws SQLException {
        return statement.executeQuery(sqlUpdateQuery);
    }


    public ResultSet delete(String sqlDeleteQuery) throws SQLException {
        return statement.executeQuery(sqlDeleteQuery);
    }
}

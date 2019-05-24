package ru.chausov.to_do_list.servlets.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.chausov.to_do_list.data_base.entities.Task;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Builder
@RequiredArgsConstructor
public class TaskController {

    private final Statement statement;

    private boolean createTask(Task task) {

        String createQuery = "INSERT INTO Tasks VALUES("
                + task.getId() + ", " + task.getDescription()
                + ", " + task.getName()
                + ", " + task.getReceivedDate()
                + ", " + task.getToBeDone()
                + ", " + task.getUserId()
                + ", " + task.isDone()
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

    private boolean updateTask(Task task) {
        //some code
        return false;
    }

    private boolean deleteTask(Long id) {
        //some code
        return false;
    }

    private boolean setTaskDone(Long id) {
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

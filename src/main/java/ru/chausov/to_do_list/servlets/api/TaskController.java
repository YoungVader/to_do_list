package ru.chausov.to_do_list.servlets.api;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.chausov.to_do_list.data_base.entities.Task;
import ru.chausov.to_do_list.data_base.interfaces.CRUD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Data
@Builder
@RequiredArgsConstructor
public class TaskController implements CRUD {

    private final Statement statement; // Читай описание javadoc в классе (Ctrl+клик). Класс не thread-safe

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
            create(createQuery); // SQL Injection, используй PreparedStatement, если хочешь низкоуровнево.
            //Всё что тебе надо есть в CrudRepository (по аналогии с VisitsRepository)

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

    @Override
    public ResultSet create(String sqlCreateQuery) throws SQLException {
        return statement.executeQuery(sqlCreateQuery);
    }

    @Override
    public ResultSet update(String sqlUpdateQuery) throws SQLException {
        return statement.executeQuery(sqlUpdateQuery);
    }

    @Override
    public ResultSet delete(String sqlDeleteQuery) throws SQLException {
        return statement.executeQuery(sqlDeleteQuery);
    }
}

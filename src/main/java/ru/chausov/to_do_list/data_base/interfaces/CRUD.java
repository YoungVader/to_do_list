package ru.chausov.to_do_list.data_base.interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

//Зачем?
public interface CRUD {
    ResultSet create(String sqlCreateQuery) throws SQLException;
    ResultSet update(String sqlUpdateQuery) throws SQLException;
    ResultSet delete(String sqlDeleteQuery) throws SQLException;
}

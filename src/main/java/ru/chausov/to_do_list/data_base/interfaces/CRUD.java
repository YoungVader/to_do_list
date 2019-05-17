package ru.chausov.to_do_list.data_base.interfaces;

public interface CRUD {
    boolean create(String sqlCreateRequest);
    boolean update(String sqlUpdateRequest);
    boolean delete(String sqlDeleteRequest);
}

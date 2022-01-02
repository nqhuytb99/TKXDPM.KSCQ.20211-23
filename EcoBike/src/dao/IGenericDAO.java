package dao;

import mapper.RowMapper;
import java.util.List;


public interface IGenericDAO<T> {

    <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);

    void update(String sql, Object... parameters);

    int insert(String sql, Object... parameters);

    int count(String sql, Object... parameters);
}


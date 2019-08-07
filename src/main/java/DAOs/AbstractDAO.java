package DAOs;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<E> {
    @NotNull
    List<E> getAll() throws SQLException;

    @NotNull
    E getById(Long id) throws SQLException;

    void insert(E e) throws SQLException;

    void update(E e) throws SQLException;

    void delete(Long id) throws SQLException;
}

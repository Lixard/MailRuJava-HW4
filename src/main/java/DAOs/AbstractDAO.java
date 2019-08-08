package DAOs;

import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.List;

public interface AbstractDAO<E> {
    @NotNull
    List<E> getAll() throws SQLException;

    @NotNull
    E getById(Long id) throws SQLException;

    boolean insert(E e);

    boolean update(E e);

    boolean delete(Long id);
}

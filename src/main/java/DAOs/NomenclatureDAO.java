package DAOs;

import DB_Entities.Nomenclature;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public final class NomenclatureDAO implements AbstractDAO<Nomenclature> {
    @NotNull
    private final Statement statement;

    public NomenclatureDAO(@NotNull Statement statement) {
        this.statement = statement;
    }

    @NotNull
    private static Nomenclature createEntity(@NotNull ResultSet resultSet) throws SQLException {
        final long id = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final long code = resultSet.getLong("code");

        return new Nomenclature(id, name, code);
    }

    @NotNull
    @Override
    public List<Nomenclature> getAll() throws SQLException {
        final List<Nomenclature> result = new LinkedList<>();

        final ResultSet resultSet = statement.executeQuery("select * from nomenclature;");
        while (resultSet.next()) {
            result.add(createEntity(resultSet));
        }
        return result;
    }

    @NotNull
    @Override
    public Nomenclature getById(Long id) throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select * from nomenclature where id =" + id + ";");
        return createEntity(resultSet);
    }

    @Override
    public boolean insert(@NotNull Nomenclature nomenclature) {
        try {
            statement.executeUpdate("insert into nomenclature(id, name, code) values (" + nomenclature.getId() + ",'" +
                    nomenclature.getName() + "'," + nomenclature.getCode() + ");");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NotNull Nomenclature nomenclature) {
        try {
            statement.executeUpdate("update nomenclature set name = '" + nomenclature.getName() + "', code = " +
                    nomenclature.getCode() + " where id = " + nomenclature.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            statement.executeUpdate("delete from nomenclature where id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

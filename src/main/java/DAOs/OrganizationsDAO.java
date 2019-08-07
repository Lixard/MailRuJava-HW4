package DAOs;

import Entities.Organizations;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public final class OrganizationsDAO implements AbstractDAO<Organizations> {
    @NotNull
    private final Statement statement;

    public OrganizationsDAO(@NotNull Statement statement) {
        this.statement = statement;
    }


    @NotNull
    private static Organizations createEntity(@NotNull ResultSet resultSet) throws SQLException {
        final long id = resultSet.getLong("id");
        final String name = resultSet.getString("name");
        final long inn = resultSet.getLong("inn");
        final long settlement_acc = resultSet.getLong("settlement_acc");

        return new Organizations(id, name, inn, settlement_acc);
    }

    @NotNull
    @Override
    public List<Organizations> getAll() throws SQLException {
        final List<Organizations> result = new LinkedList<>();

        final ResultSet resultSet = statement.executeQuery("select * from organizations;");
        while (resultSet.next()) {
            result.add(createEntity(resultSet));
        }
        return result;
    }

    @NotNull
    @Override
    public Organizations getById(Long id) throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select * from organizations where id =" + id + ";");
        return createEntity(resultSet);
    }

    @Override
    public void insert(@NotNull Organizations organizations) throws SQLException {
        statement.executeUpdate("insert into organizations(id, name, inn, settlement_acc) values " +
                "(" + organizations.getId() + "," + organizations.getName() + ","
                + organizations.getInn() + "," + organizations.getSettlement_acc() + ");");
    }

    @Override
    public void update(@NotNull Organizations organizations) throws SQLException {
        statement.executeUpdate("update organizations set name = " + organizations.getName() + ", inn = " +
                organizations.getInn() + ", settlement_acc =" + organizations.getSettlement_acc() +
                ") where id = " + organizations.getId() + ";");
    }

    @Override
    public void delete(Long id) throws SQLException {
        statement.executeUpdate("delete from organizations where id = " + id + ";");
    }
}

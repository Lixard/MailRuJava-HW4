package DAOs;

import Entities.Waybills;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class WaybillsDAO implements AbstractDAO<Waybills> {
    @NotNull
    private final Statement statement;

    public WaybillsDAO(@NotNull Statement statement) {
        this.statement = statement;
    }

    @NotNull
    private static Waybills createEntity(@NotNull ResultSet resultSet) throws SQLException {
        final long org_send_id = resultSet.getLong("org_send_id");
        final long number = resultSet.getLong("number");
        final Date date = resultSet.getDate("date");

        return new Waybills(org_send_id, number, date);
    }
    @NotNull
    @Override
    public List<Waybills> getAll() throws SQLException {
        final List<Waybills> result = new LinkedList<>();

        final ResultSet resultSet = statement.executeQuery("select * from waybills;");
        while (resultSet.next()) {
            result.add(createEntity(resultSet));
        }
        return result;
    }

    @NotNull
    @Override
    public Waybills getById(Long id) throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select * from waybills where org_send_id =" + id + ";");
        return createEntity(resultSet);
    }

    @Override
    public boolean insert(@NotNull Waybills waybills) {
        try {
            statement.executeUpdate("insert into waybills(org_send_id, number, date) values (" + waybills.getOrg_send_id() +
                    "," + waybills.getNumber() + ",'" + waybills.getDate() + "');");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NotNull Waybills waybills) {
        try {
            statement.executeUpdate("update waybills set number = " + waybills.getNumber()+ ", date = '" +
                    waybills.getDate() + "' where org_send_id = " + waybills.getOrg_send_id() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            statement.executeUpdate("delete from waybills where org_send_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

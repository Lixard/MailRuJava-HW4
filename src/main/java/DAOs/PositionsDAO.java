package DAOs;

import DB_Entities.Positions;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public final class PositionsDAO implements AbstractDAO<Positions> {
    @NotNull
    private final Statement statement;

    public PositionsDAO(@NotNull Statement statement) {
        this.statement = statement;
    }

    @NotNull
    private static Positions createEntity(@NotNull ResultSet resultSet) throws SQLException {
        final long nomenclature_id = resultSet.getLong("nomenclature_id");
        final long waybill_id = resultSet.getLong("waybill_id");
        final long price = resultSet.getLong("price");
        final long amount = resultSet.getLong("amount");

        return new Positions(nomenclature_id, waybill_id, price, amount);
    }

    @Override
    @NotNull
    public List<Positions> getAll() throws SQLException {
        final List<Positions> result = new LinkedList<>();

        final ResultSet resultSet = statement.executeQuery("select * from positions;");
        while (resultSet.next()) {
            result.add(createEntity(resultSet));
        }
        return result;
    }

    @Override
    @NotNull
    public Positions getById(Long id) throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select * from positions where nomenclature_id =" + id + ";");
        return createEntity(resultSet);
    }

    @Override
    public boolean insert(@NotNull Positions positions) {
        try {
            statement.executeUpdate("insert into positions(nomenclature_id, waybill_id, price, amount) values (" +
                    positions.getNomenclature_id() + "," + positions.getWaybill_id() + "," + positions.getPrice() + "," +
                    positions.getAmount() + ");");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(@NotNull Positions positions) {
        try {
            statement.executeUpdate("update positions set waybill_id = " + positions.getWaybill_id() +
                    ", price = " + positions.getPrice() + ", amount = " + positions.getAmount() + "where nomenclature_id =" +
                    positions.getNomenclature_id() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean delete(Long id) {
        try {
            statement.executeUpdate("delete from positions where nomenclature_id = " + id + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

import QueryEntities.DataAmountPrice;
import QueryEntities.OrganizationWithAmount;
import QueryEntities.OrganizationWithGoods;
import QueryEntities.OrganizationWithPrice;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public final class Queries {

    @NotNull
    private final Statement statement;

    public Queries(@NotNull Statement statement) {
        this.statement = statement;
    }

    public List<OrganizationWithAmount> top10OrganizationsByAmountOfGoods() throws SQLException {
        List<OrganizationWithAmount> result = new LinkedList<>();
        final ResultSet resultSet = statement.executeQuery("select organizations.id," +
                " organizations.name, organizations.inn, organizations.settlement_acc, sum(positions.amount)" +
                " from organizations" +
                " join waybills on organizations.id = waybills.org_send_id" +
                " join positions on waybills.org_send_id = positions.waybill_id" +
                " group by organizations.id" +
                " order by sum(positions.amount) desc" +
                " limit 10");

        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            long inn = resultSet.getLong(3);
            long settlementAcc = resultSet.getLong(4);
            long amount = resultSet.getInt(5);
            result.add(new OrganizationWithAmount(id, name, inn, settlementAcc, amount));
        }
        return result;
    }

    public List<OrganizationWithPrice> OrganizationsWherePriceOfGoodsGreaterThanValue(int value) throws SQLException {
        List<OrganizationWithPrice> result = new LinkedList<>();
        final ResultSet resultSet = statement.executeQuery("select organizations.id, organizations.name," +
                " organizations.inn, organizations.settlement_acc, positions.price\n" +
                "from organizations " +
                "join waybills on organizations.id = waybills.org_send_id " +
                "join positions on waybills.org_send_id = positions.waybill_id " +
                "group by organizations.id, positions.price " +
                "having positions.price > " + value +
                " order by organizations.id asc");

        while (resultSet.next()) {
            long id = resultSet.getLong(1);
            String name = resultSet.getString(2);
            long inn = resultSet.getLong(3);
            long settlementAcc = resultSet.getLong(4);
            long price = resultSet.getLong(5);
            result.add(new OrganizationWithPrice(id, name, inn, settlementAcc, price));
        }
        return result;
    }

    public List<DataAmountPrice> AmountAndPricePerPeriodEveryDay(@NotNull String date1,@NotNull String date2) throws SQLException {
        List<DataAmountPrice> result = new LinkedList<>();
        final ResultSet resultSet = statement.executeQuery("select waybills.date, sum(positions.amount), sum(positions.price) " +
                "from organizations " +
                "join waybills on organizations.id = waybills.org_send_id " +
                "join positions on waybills.org_send_id = positions.waybill_id " +
                "where waybills.date between '" + date1 + "' and '" + date2 +
                "' group by waybills.date " +
                "union " +
                "select null,sum(positions.amount), sum(positions.price) " +
                "from organizations " +
                "join waybills on organizations.id = waybills.org_send_id " +
                "join positions on waybills.org_send_id = positions.waybill_id " +
                "where waybills.date between '" + date1 + "' and '" + date2 +
                "' order by date asc");

        while (resultSet.next()) {
            Date date = resultSet.getDate(1);
            long amount = resultSet.getLong(2);
            long price = resultSet.getLong(3);
            result.add(new DataAmountPrice(date, amount, price));
        }
        return result;
    }

    public String AvgPricePerPeriod(@NotNull String date1,@NotNull String date2) throws SQLException {
        final ResultSet resultSet = statement.executeQuery("select sum(positions.price)/count(positions.price)\n" +
                "from positions\n" +
                "join waybills on positions.nomenclature_id = waybills.org_send_id\n" +
                "where waybills.date between '" + date1 + "' and '" + date2 + "'");
        resultSet.next();
        return "avgPrice: " + resultSet.getString(1);
    }

    public List<OrganizationWithGoods> organizationsWithGoodsPerPeriod(@NotNull String date1,@NotNull String date2) throws SQLException {
        List<OrganizationWithGoods> result = new LinkedList<>();
        final ResultSet resultSet = statement.executeQuery("select organizations.id, organizations.name, " +
                "nomenclature.id, nomenclature.name " +
                "from organizations " +
                "join waybills on organizations.id = waybills.org_send_id " +
                "join positions on waybills.org_send_id = positions.waybill_id " +
                "join nomenclature on positions.nomenclature_id = nomenclature.id " +
                "where waybills.date between '" + date1 + "' and '" + date2 +
                "' union all " +
                "select  organizations.id, organizations.name, null, null " +
                "from organizations " +
                "join waybills on organizations.id = waybills.org_send_id " +
                "where waybills.date between '" + date1 + "' and '" + date2 + "' ");
        while (resultSet.next()) {
            long organizationID = resultSet.getLong(1);
            String organizationName = resultSet.getString(2);

            long nomenclatureID = resultSet.getLong(3);
            String nomenclatureName = resultSet.getString(4);
            result.add(new OrganizationWithGoods(organizationID, organizationName, nomenclatureID, nomenclatureName));
        }
        return result;
    }
}

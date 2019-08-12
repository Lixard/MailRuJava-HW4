import QueryEntities.DataAmountPrice;
import QueryEntities.OrganizationWithAmount;
import QueryEntities.OrganizationWithGoods;
import QueryEntities.OrganizationWithPrice;
import org.flywaydb.core.Flyway;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;

public final class App {

    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/MailRuHW4";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "123";
    private static final Flyway flyway = Flyway.configure().dataSource(URL, USERNAME, PASSWORD).load();

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD)) {
            flyway.migrate();
            Statement statement = connection.createStatement();
            Queries queries = new Queries(statement);

            List<OrganizationWithAmount> list1 = queries.top10OrganizationsByAmountOfGoods();
            System.out.println(Arrays.toString(list1.toArray()));

            List<OrganizationWithPrice> list2 = queries.OrganizationsWherePriceOfGoodsGreaterThanValue(200);
            System.out.println(Arrays.toString(list2.toArray()));

            List<DataAmountPrice> list3 = queries.AmountAndPricePerPeriodEveryDay("11-03-2019","13-03-2019");
            System.out.println(Arrays.toString(list3.toArray()));

            System.out.println(queries.AvgPricePerPeriod("11-03-2019","13-03-2019"));

            List<OrganizationWithGoods> list4 = queries.organizationsWithGoodsPerPeriod("13-03-2019","15-03-2019");
            System.out.println(Arrays.toString(list4.toArray()));
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

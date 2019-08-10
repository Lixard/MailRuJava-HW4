import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.sql.Statement;

public class Queries {

    @NotNull
    private final Statement statement;

    public Queries(@NotNull Statement statement) {
        this.statement = statement;
    }

    public boolean top10OrganizationsByQuantityOfGoods() {
        try {
            statement.executeQuery("select * \n" +
                    "from organizations \n" +
                    "join waybills on organizations.id = waybills.org_send_id\n" +
                    "join positions on waybills.org_send_id = positions.waybill_id\n" +
                    "order by positions.amount desc");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

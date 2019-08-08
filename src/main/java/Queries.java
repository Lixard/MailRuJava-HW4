import org.jetbrains.annotations.NotNull;

import java.sql.Statement;
import java.util.List;

public class Queries {

    @NotNull
    private final Statement statement;

    public Queries(@NotNull Statement statement) {
        this.statement = statement;
    }

    public void top10ByQuantityOfGoods() {
        statement.executeQuery("select")
    }
}

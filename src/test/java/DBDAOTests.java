import DAOs.NomenclatureDAO;
import DAOs.OrganizationsDAO;
import DAOs.PositionsDAO;
import DAOs.WaybillsDAO;
import Entities.Nomenclature;
import Entities.Organizations;
import Entities.Positions;
import Entities.Waybills;
import org.flywaydb.core.Flyway;
import org.jetbrains.annotations.NotNull;
import org.junit.*;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DBDAOTests {
    @SuppressWarnings("NullableProblems")
    @NotNull
    private Connection connection;
    @SuppressWarnings("NullableProblems")
    @NotNull
    private Statement statement;
    @NotNull
    private static final String URL = "jdbc:postgresql://127.0.0.1:5432/MailRuHW4";
    @NotNull
    private static final String USERNAME = "postgres";
    @NotNull
    private static final String PASSWORD = "123";

    @BeforeClass
    public static void flywaySet() {
        final Flyway flyway = Flyway.configure().dataSource(URL, USERNAME, PASSWORD).load();
        flyway.clean();
        flyway.migrate();
    }

    @Before
    public void setConnection() throws SQLException {
        connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        statement = connection.createStatement();
    }

    @Test
    public void insertNomenclatureTest() {
        NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(statement);
        Nomenclature obj = new Nomenclature(100,"Новая номенклатура",456768663);
        Assert.assertTrue(nomenclatureDAO.insert(obj));
    }

    @Test
    public void updateNomenclatureTest() {
        NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(statement);
        Nomenclature obj = new Nomenclature(5, "Обновленная номенклатура", 12345678);
        Assert.assertTrue(nomenclatureDAO.update(obj));
    }

    @Test
    public void deleteNomenclatureTest() {
        NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(statement);
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        positionsDAO.delete((long) 5);
        Assert.assertTrue(nomenclatureDAO.delete((long) 5));
    }

    @Test
    public void insertOrganizationTest() {
        OrganizationsDAO organizationsDAO = new OrganizationsDAO(statement);
        Organizations obj = new Organizations(100,"Новая организация",123456,123123);
        Assert.assertTrue(organizationsDAO.insert(obj));
    }

    @Test
    public void updateOrganizationTest() {
        OrganizationsDAO organizationsDAO = new OrganizationsDAO(statement);
        Organizations obj = new Organizations(10,"Обновленная организация",123456,123123);
        Assert.assertTrue(organizationsDAO.update(obj));
    }

    @Test
    public void deleteOrganizationTest() {
        WaybillsDAO waybillsDAO = new WaybillsDAO(statement);
        OrganizationsDAO organizationsDAO = new OrganizationsDAO(statement);
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        positionsDAO.delete((long)8);
        waybillsDAO.delete((long)8);
        Assert.assertTrue(organizationsDAO.delete((long)8));
    }

    @Test
    public void insertPositionTest() {
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        NomenclatureDAO nomenclatureDAO = new NomenclatureDAO(statement);
        Nomenclature nomenclature = new Nomenclature(20,"Новая номенклатура",123456);
        Positions position = new Positions(20,1, 500, 200);
        nomenclatureDAO.insert(nomenclature);
        Assert.assertTrue(positionsDAO.insert(position));
    }

    @Test
    public void updatePositionTest() {
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        Positions position = new Positions(15,1,200,300);
        Assert.assertTrue(positionsDAO.update(position));
    }

    @Test
    public void deletePositionTest() {
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        Assert.assertTrue(positionsDAO.delete((long)5));
    }

    @Test
    public void insertWaybillTest() throws ParseException {
        WaybillsDAO waybillsDAO = new WaybillsDAO(statement);
        OrganizationsDAO organizationsDAO = new OrganizationsDAO(statement);
        Organizations organization = new Organizations(50,"Новая организация", 552,32032);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "29-11-2015";
        Date date = formatter.parse(dateInString);
        Waybills waybill = new Waybills(50,3231245, date);
        organizationsDAO.insert(organization);
        Assert.assertTrue(waybillsDAO.insert(waybill));
    }

    @Test
    public void updateWaybillTest() throws ParseException {
        WaybillsDAO waybillsDAO = new WaybillsDAO(statement);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String dateInString = "29-11-2015";
        Date date = formatter.parse(dateInString);
        Waybills waybills = new Waybills(13,12345, date);
        Assert.assertTrue(waybillsDAO.update(waybills));
    }

    @Test
    public void deleteWaybillTest() {
        WaybillsDAO waybillsDAO = new WaybillsDAO(statement);
        PositionsDAO positionsDAO = new PositionsDAO(statement);
        positionsDAO.delete((long)13);
        Assert.assertTrue(waybillsDAO.delete((long)13));
    }

    @After
    public void closeConnection() throws SQLException {
        statement.close();
        connection.close();
    }
}

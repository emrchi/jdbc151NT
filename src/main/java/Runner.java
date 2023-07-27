import java.sql.ResultSet;
import java.sql.SQLException;

public class Runner {

    public static void main(String[] args)  {

        //Database'e baglan.
        JdbcUtils.connectToDatabase();

        //Statement olustur.
        JdbcUtils.createStatement();

        //Query calistir.   workers adinda table olustur.
        JdbcUtils.execute("CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)");

        //executeQuery() methodu ile resultSet datatype'i dondurerek,
        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        JdbcUtils.executeQuery("SELECT company, number_of_employees  FROM companies \n" +
                                   "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies \n" +
                                   "WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))");






    }
}

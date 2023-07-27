import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CountryTest {

    /*
 Given
   User connects to the database
   (Kullanıcı veritabanına bağlanır)

 When
   User sends the query to get the region ids from "countries" table
   (Kullanıcı, 'countries' table'dan 'region id' almak üzere query gönderir )

 Then
   Assert that the number of region ids greater than 1 is 17.
   (1'den büyük region id'lerin sayısının 17 olduğunu doğrula )

 And
   User closes the connection
*/


    //1.yol;
    @Test
    public void countryTest() throws SQLException {
        JdbcUtils.connectToDatabase();
        JdbcUtils.createStatement();
        String sql = "select count(region_id) from countries where region_id>1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);
        resultSet.next();
        int result = resultSet.getInt(1);
        System.out.println("result = " + result);
        assertEquals(17, result);
        JdbcUtils.closeConnection();
    }

    //2.yol;

    @Test
    public void countryTest2() throws SQLException {
        JdbcUtils.connectToDatabase();
        JdbcUtils.createStatement();
        String sql = "select region_id from countries where region_id>1";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);
        List<Integer> region_idList = new ArrayList<>();
        while (resultSet.next()){
            region_idList.add(resultSet.getInt("region_id"));
        }
        System.out.println("region_idList = " + region_idList);

        assertEquals(17, region_idList.size());
        JdbcUtils.closeConnection();

    }
}

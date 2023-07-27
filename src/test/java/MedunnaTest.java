import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class MedunnaTest {
      /*
 Given
   User connects to the database
   (Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6))

 When
   User sends the query to get the created room
   (Kullanıcı, oluşturulan odayı getirmek için sorgu gönderir)

 Then
   Assert that room is created properly
   (Odanın düzgün kaydedildiğini doğrular)

 And
   User closes the connection
*/

    @Test
    public void medunnaTest() throws SQLException {
        //User connects to the database
        JdbcUtils.connectToMedunnaDatabase();
        JdbcUtils.createStatement();
        String sql = "select * from room where room_number = 326556421";
        ResultSet resultSet = JdbcUtils.executeQuery(sql);
        resultSet.next();
        assertEquals("123.00", resultSet.getString(5));
        assertEquals("Database Test İçin Oluşturuldu", resultSet.getString(6));
        assertEquals("mark_twain", resultSet.getString("created_by"));
        JdbcUtils.closeConnection();

    }
}

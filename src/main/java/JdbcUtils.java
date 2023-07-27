import java.sql.*;

public class JdbcUtils {

    //Bu Class'da tekrarli yapilacak islemlerin metodlari bulunacak.

    private static Connection connection;    //connection i ve statement'i burada tanimladim yani Class seviyesinde cunku
    private static Statement statement;      // diger methodlarinda ulasabilmesi ve method body disindan ulasilabilmesi icin.
    private static ResultSet resultSet;

    //Connection: Database'e baglanma methodu

    public static Connection connectToDatabase(){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }

    //Medunna DataBase'e baglanma methodu
    //Host name: medunna.com, Database name: medunna_db_v2, Username: select_user, Password: Medunna_pass_@6

    public static Connection connectToMedunnaDatabase(){

        try {
            connection = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db_v2","select_user","Medunna_pass_@6");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;

    }


    // Statement olustur methodu

    public static Statement createStatement() {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return statement;
    }

    //Execute methodu ile Query calistiran method

    public static boolean execute(String sql){
        try {
            return statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //executeQuery(), executeUpdate(), closeConnection() methodları ödev....

    //executeQuery() methodu ile Query calistiran method

    public static ResultSet executeQuery(String sql){
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return resultSet;

    }

    //executeUpdate() methodu ile degisiklik yapan method.

    public static int executeUpdate(String sql){
        try {
            return statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //close() methodu ile connection ve statament'i kapatan method.

    public static void closeConnection() {

        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

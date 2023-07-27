import java.sql.*;

public class ExecuteQuery02 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        Statement statement = connection.createStatement();

        //1. Örnek: companies tablosundan en yüksek ikinci number_of_employees değeri olan company ve number_of_employees değerlerini çağırın.

        //1.yol: Offset ve Limit kullanarak Hard code

        System.out.println("\n====1.yol====\n");
        String sql1 = "select company, number_of_employees from companies order by number_of_employees desc offset 1 limit 1";
        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()){  //ResultSet son satira gelip "false" dondukten sonra kapatilir. Kapali ResultSet uzerinde islem yapilamaz, yapilirsa exception atar.
            System.out.println(resultSet.getObject(1)+ "--" +resultSet.getString(2));
        }



        //2. Yol: Sub Query kullanarak Dinamik code

        System.out.println("\n====2.yol====\n");
        String sql2 = "SELECT company, number_of_employees  FROM companies \n" +
                      "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies \n" +
                      "WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))";

        ResultSet resultSet1 = statement.executeQuery(sql2);
        while (resultSet1.next()) {
            System.out.println(resultSet1.getObject(1) + "--" + resultSet1.getString(2));
        }









        connection.close();
        statement.close();
    }
}

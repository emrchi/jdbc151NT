import java.sql.*;
import java.util.Random;

public class ExecuteUpdate01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        Statement statement = connection.createStatement();

        // 1. Örnek: number_of_employees değeri ortalama çalışan sayısından az olan number_of_employees değerlerini 16000 olarak UPDATE edin.

        String sql = "update companies set number_of_employees = 16000 where number_of_employees < (select avg(number_of_employees) from companies)";
        int guncellenenSatirSayisi = statement.executeUpdate(sql); //ExecuteUpdate () methodu guncellenen satir sayisini int deger olarak dondurur.
        System.out.println("guncellenenSatirSayisi = " + guncellenenSatirSayisi);

        //Guncelleme sonrasi datayi okumak icin DQL(Select) kullaniyoruz.

        String sql1 = "select * from companies";

        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()){
            System.out.println(resultSet.getObject(1)+ "--" + resultSet.getObject(2)+ "--" +resultSet.getObject(3));
        }







        connection.close();
        statement.close();

    }
}

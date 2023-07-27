import java.sql.*;

public class PreparedStatement01 {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        Statement statement = connection.createStatement();

        // PreparedStatement method gibi dusunebiliriz. Guncellemeleri tekrar tekrar parametreleri degistirerek yapabiliriz.

        //1. Örnek: Prepared statement kullanarak company adı IBM olan number_of_employees değerini 9999 olarak güncelleyin.

        //PreparedStatement olusturmak icin
        //1.adim: Prepared statement query'si olustur.--> Parametrelendirme yapilacak hyerlere ? gir.
        String sql = "update companies set number_of_employees = ? where company = ?";   // ?--> Paremetrelendirme.

        //2.adim: Prepared statement objesi olustur.
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        //3.adim: Prepared statement objesi ile setInt setString gibi methodlarla Parametrelendirmelerin yerine koymak
        // istedigimiz degerleri yerlestir ve executeUpdate ile Query'yi calistir.

        preparedStatement.setInt(1, 9999);  //Burada 1. soru isaretine 9999 koy yaptik
        preparedStatement.setString(2, "IBM"); //Burada 2. soru isaretine IBM koy yaptik
        preparedStatement.executeUpdate();

        //2. Örnek: Prepared statement kullanarak company adı CASPER olan number_of_employees değerini 10000 olarak güncelleyin.

        preparedStatement.setInt(1,10000);
        preparedStatement.setString(2, "CASPER");
        preparedStatement.executeUpdate();

        //3. Örnek: Prepared statement kullanarak company adı MICROSOFT olan number_of_employees değerini 15000 olarak güncelleyin.

        preparedStatement.setInt(1,15000);
        preparedStatement.setString(2, "MICROSOFT");
        preparedStatement.executeUpdate();

        //4. Örnek: Prepared statement kullanarak company adı GOOGLE olan number_of_employees değerini 22000 olarak güncelleyin.

        preparedStatement.setInt(1,22000);
        preparedStatement.setString(2, "GOOGLE");
        preparedStatement.executeUpdate();

        //5. Örnek: Prepared statement kullanarak company adı APPLE olan number_of_employees değerini 30000 olarak güncelleyin.

        preparedStatement.setInt(1,30000);
        preparedStatement.setString(2, "APPLE");
        preparedStatement.executeUpdate();

        //Guncelleme sonrasi datayi okumak icin DQL(Select) kullan.

        String sql1 = "select * from companies";

        ResultSet resultSet = statement.executeQuery(sql1);
        while (resultSet.next()){
            System.out.println(resultSet.getObject(1)+ "--" + resultSet.getObject(2)+ "--" +resultSet.getObject(3));
        }





        connection.close();
        statement.close();
    }
}

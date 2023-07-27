import java.sql.*;

public class ExecuteQuery01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        Statement statement = connection.createStatement();
        //1. Örnek:  region id'si 1 olan "country name" değerlerini çağırın.
        System.out.println("\n====1.ornek====\n");
        String sql1 = "select country_name from countries where region_id = 1;";
        boolean r1 = statement.execute(sql1 );
        System.out.println("r1 = " + r1);//true ==. DQL ile data cagiriyorum data donuyor.

        //Datayi cagirip okumak icin executeQuery() methodunu kullanmaliyiz.
        ResultSet resultSet = statement.executeQuery(sql1); //bize bir Resultset dondurecek.Bir nevi collection yani.
                                                            //next() methodu ile hep bir alt satira geciyoruz data varsa true yoksa false donduruyor.

        /* 1. ve imkansiz yol

        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString(1));
        resultSet.next();
        System.out.println(resultSet.getString(1));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));
        resultSet.next();
        System.out.println(resultSet.getString("country_name"));

         */
        //Asil yol.. yapilmasi gereken

        while (resultSet.next()){   //resultSet.next() false verene kadar resultSet.getString("country_name") yazdir.
            System.out.println(resultSet.getString("country_name"));
        }

        //2.Örnek: "region_id"nin 2'den büyük olduğu "country_id" ve "country_name" değerlerini çağırın.

        System.out.println("\n====2.ornek====\n");
        String sql2 = "select country_id, country_name from countries where region_id>2";
        ResultSet resultSet2 = statement.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getString("country_id")+" "+resultSet2.getString("country_name"));
        }


        //3.Örnek: "number_of_employees" değeri en düşük olan satırın tüm değerlerini çağırın.

        System.out.println("\n====3.ornek====\n");
        String sql3 = "select * from companies where number_of_employees = (select min(number_of_employees) from companies)";
        ResultSet resultSet3 = statement.executeQuery(sql3);
        while (resultSet3.next()) {
            System.out.println(resultSet3.getObject(1) + " " + resultSet3.getString(2) + " " + resultSet3.getObject(3));//getobject() methodu da kullanilabilir.
        }
        connection.close();
        statement.close();


    }
}

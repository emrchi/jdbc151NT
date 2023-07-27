import java.sql.*;


public class Execute01 {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        //1. Adim: Connection: Driver'a kaydol,
        Class.forName("org.postgresql.Driver");//JDBC 4 sonrasi yapmaya gerek kalmadi.

        //2. Adim: Connection: Database'e baglan,
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");

        //3. Adim: Statement olustur,
        Statement statement = connection.createStatement();

        //4. Adim: Query calistir,

        /*
        execute() methodu DDL(create,drop,alter table) ve DQL(select) ile kullanilir.
        1) eger execute() methodu DDL ile kullanilirsa hep "false" doner. Cunku DDL ile data cagirilmaz.
        1) eger execute() methodu DQL ile kullanilirsa data dondugunde "true" data donmediginde "false" doner.
         */

        //1.Örnek: "workers" adında bir table oluşturup "worker_id, worker_name, worker_salary" sütunlarını ekleyin.
        String sql1 = "CREATE TABLE workers (worker_id VARCHAR(20), worker_name VARCHAR(20), worker_salary INT)";
        boolean r1 = statement.execute(sql1);
        System.out.println("r1 = " + r1);//false

        //2.Örnek: Table'a worker_address sütunu ekleyin.
        String sql2  = "ALTER TABLE workers ADD worker_address VARCHAR (100)";
        boolean r2 = statement.execute(sql2);
        System.out.println("r2 = " + r2);//false

        //3.Örnek: workers table'ını silin.
        String sql3 = "DROP TABLE workers";
        boolean r3 = statement.execute(sql3);
        System.out.println("r3 = " + r3);//false

        //5. Adim: Baglantiyi kapat,
        connection.close();
        statement.close();




    }

}

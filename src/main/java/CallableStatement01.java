import java.sql.*;

public class CallableStatement01 {

    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","Number1g@r");
        Statement statement = connection.createStatement();

        //1. Örnek: Selamlama yapan bir function'ı Callable Statement ile çağırınız

        //Callable Statement adımları:
        //1. Adim; Function'i olusturan kodu yaziniz.   SQL'deki Function bi nevi Java'daki method olarak kabul edilebilir.

        String sql = "CREATE OR REPLACE FUNCTION selamlama(x TEXT) RETURNS TEXT AS $$ \n" +
                     "BEGIN RETURN 'Merhaba '|| x ||', nasılsın?'; END; $$ LANGUAGE plpgsql;";

        //2. Adim; Function kodunu calistiriniz.
        statement.execute(sql);

        //3. Adim; CallableStatement objesi olusturarak  Function'i cagiriniz.
        CallableStatement callableStatement = connection.prepareCall("{? = call selamlama(?)}");
        //selamlama Function'inini cagirmak icin hazirladim. sabit bir syntax gibi dusunebiliriz?-> return type ve parametre icin kullandim asagida atayacagim.

        //4. Adim; ReturnType icin registerOutParameter() methodunu, parametreler icin ise setString(), setInt() gibi metodlari kullaniniz.
        callableStatement.registerOutParameter(1,Types.VARCHAR);
        callableStatement.setString(2,"Ayse");

        //5.Adim; execute() methodunu ile callableStatement'i calistiriniz.
        callableStatement.execute();

        //6. Adim; Sonucu okumak icin collableStatement'tan data turunu cagiriniz.
        //collableStatement'da donen data resultSet icinde donmez. Direkt collableStatement icerisinden alinir.
        System.out.println(callableStatement.getObject(1));


        //2. Örnek: İki sayıyı toplayan bir function yazınız ve Callable Statement ile çağırınız.

        String sql2 = "CREATE or REPLACE FUNCTION toplama(x INT, Y INT) RETURNS NUMERIC AS $$ BEGIN\n" +
                      "RETURN X + Y ; END; $$ LANGUAGE plpgsql;";

        statement.execute(sql2);

        CallableStatement callableStatement1 = connection.prepareCall("{? = call toplama(?,?)}");

        callableStatement1.registerOutParameter(1, Types.NUMERIC);
        callableStatement1.setInt(2,45);
        callableStatement1.setInt(3,6);

        callableStatement1.execute();


        System.out.println(callableStatement1.getBigDecimal(1));


        connection.close();
        statement.close();
    }
}

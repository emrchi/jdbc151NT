public class Test01 {

    public static void main(String[] args) {


        //En basit manada bir test case:

        String str = "Merhaba";

        if(str.contains("er")){
            System.out.println("PASSED");
        }else{
            System.out.println("FAILED");
        }

        //Otomasyon testi yapabilmemiz icin bir test frameworkune (jUnit, testNG, Cucumber ...) ihtiyacimiz var.

    }
}

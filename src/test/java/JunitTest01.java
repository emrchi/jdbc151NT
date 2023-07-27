import org.junit.Test;

import static org.junit.Assert.*;


public class JunitTest01 {

    //test metodlari public ve void olmali. birde @Test anitation'i kullanmaliyim.

    @Test
    public void test01() {

        assertEquals(5, "hello".length());   //assertEquals() methodunda parantez icindeki parametreler birbirine esit ise passed, degilse failed.
        assertTrue("Merhaba".contains("a"));         //assertTrue() methodunda parantez icindeki kosul true ise passed false ise failed.
        assertFalse("google.com".contains("a"));     //assertFalse() methodunda parantez icindeki kosul false ise passed false ise failed.
        assertEquals(65,'A');
    }
}

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

class MainTest {

     @Test
     @Timeout(value = 22, unit = TimeUnit.SECONDS)
     @Disabled("This test only checks execution time and is disabled by default.")
     void mainShouldFinishInLessThan22Seconds() throws Exception{
         Main.main(null);
     }
}

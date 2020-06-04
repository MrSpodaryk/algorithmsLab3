import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {

    @Test
    void dijkstraTest() {
        Main.init();
        Main.readFromFile();
        double d = Main.dijkstra();
        System.out.println(d);
        Assertions.assertEquals(31.46153846153846, d);
    }

}

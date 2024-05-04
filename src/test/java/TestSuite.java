import org.github.zkkv.Main;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

public class TestSuite {

    private OutputStream out;
    private final String rn = System.lineSeparator();

    @BeforeEach
    void setUp() {
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
        System.setIn(new FileInputStream(FileDescriptor.in));
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
    }

    @Test
    void singleName() {
        String str = "1" + rn
                + "jack" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        assertEquals(28, out.toString().length());
    }

    @Test
    void twoNamesAlwaysPossible1() {
        String str = "2" + rn
                + "jack" + rn
                + "john" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        assertTrue(actual.indexOf("a") < actual.indexOf("o"));
    }

    @Test
    void twoNamesAlwaysPossible2() {
        String str = "2" + rn
                + "jack" + rn
                + "kacj" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        assertTrue(actual.indexOf("j") < actual.indexOf("k"));
    }

    @Test
    void threeNamesImpossible() {
        String str = "3" + rn
                + "jack" + rn
                + "alex" + rn
                + "john" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String expected = "Impossible" + rn;
        assertEquals(expected, out.toString());
    }

    @Test
    void threeNamesPossible() {
        String str = "3" + rn
                + "jack" + rn
                + "alex" + rn
                + "sam" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        assertTrue(actual.indexOf("j") < actual.indexOf("a"));
        assertTrue(actual.indexOf("a") < actual.indexOf("s"));
    }

    @Test
    void fourNamesImpossible() {
        String str = "4" + rn
                + "alex" + rn
                + "blex" + rn
                + "xlex" + rn
                + "xleb" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String expected = "Impossible" + rn;
        assertEquals(expected, out.toString());
    }

    @Test
    void fiveNamesPossible() {
        String str = "5" + rn
                + "alex" + rn
                + "alek" + rn
                + "bob" + rn
                + "bobby" + rn
                + "jack" + rn;
        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        assertTrue(actual.indexOf("a") < actual.indexOf("b"));
        assertTrue(actual.indexOf("x") < actual.indexOf("k"));
        assertTrue(actual.indexOf("b") < actual.indexOf("j"));
    }

    @Test
    void twentySixNamesPossibleDirect() {
        StringBuilder sb = new StringBuilder("26" + rn);
        for (int i = 0; i < 26; i++) {
            sb.append((char)('a' + i)).append(rn);
        }
        String str = sb.toString();

        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        for (int i = 0; i < 25; i++) {
            char c1 = (char)('a' + i);
            char c2 = (char)('b' + i);
            assertTrue(actual.indexOf(c1) < actual.indexOf(c2));
        }
    }

    @Test
    void twentySixNamesPossibleReverse() {
        StringBuilder sb = new StringBuilder("26" + rn);
        for (int i = 0; i < 26; i++) {
            sb.append((char)('z' - i)).append(rn);
        }
        String str = sb.toString();

        ByteArrayInputStream byteStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(byteStream);
        Main.main(null);

        String actual = out.toString();
        assertEquals(28, actual.length());
        for (int i = 0; i < 25; i++) {
            char c1 = (char)('a' + i);
            char c2 = (char)('b' + i);
            assertTrue(actual.indexOf(c1) > actual.indexOf(c2));
        }
    }
}

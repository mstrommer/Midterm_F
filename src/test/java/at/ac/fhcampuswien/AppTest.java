package at.ac.fhcampuswien;

import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

@Timeout(2)
public class AppTest {

    private PrintStream originalOut;
    private InputStream originalIn;
    private ByteArrayOutputStream bos;
    private PrintStream ps;

    @BeforeAll
    public static void init(){
        System.out.println("Testing Midterm");
    }

    @AfterAll
    public static void finish(){
        System.out.println("Finished Testing Midterm");
    }

    @BeforeEach
    public void setupStreams() throws IOException {
        originalOut = System.out;
        originalIn = System.in;

        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        PipedOutputStream pos = new PipedOutputStream();
        PipedInputStream pis = new PipedInputStream(pos);
        System.setIn(pis);
        ps = new PrintStream(pos, true);
    }

    @AfterEach
    public void tearDownStreams() {
        // undo the binding in System
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    @Test
    public void predictCorona1(){
        assertEquals("121144", App.predictCoronaInfections(111361, 2, 4.3));
    }

    @Test
    public void predictCorona2(){
        assertEquals("3", App.predictCoronaInfections(1, 20, 5));
    }

    @Test
    public void predictCorona3(){
        assertEquals("107105", App.predictCoronaInfections(106891, 2, 0.1));
    }

    @Test
    public void predictCorona4(){
        assertEquals("Input not valid.", App.predictCoronaInfections(0, 2, 4.3));
    }

    @Test
    public void predictCorona5(){
        assertEquals("1200", App.predictCoronaInfections(1200, 0, 4.3));
    }

    @Test
    public void predictCorona6(){
        assertEquals("Input not valid.", App.predictCoronaInfections(5, -1, 9.3));
    }

    @Test
    public void printTriangle1(){

        ps.println(2);
        String output = "Enter rows:" + System.lineSeparator() +
                "* * " + System.lineSeparator() +
                "* "  + System.lineSeparator();

        App.printTriangle();
        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void printTriangle2(){
        ps.println(0);
        String output = "Enter rows:" + System.lineSeparator();

        App.printTriangle();
        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void printTriangle3(){
        ps.println(-1);
        String output = "Enter rows:" + System.lineSeparator() +
                "Rows cannot be negative."  + System.lineSeparator();

        App.printTriangle();
        // assertion
        assertEquals(output, bos.toString());
    }

    @Test
    public void printTriangle4(){
        ps.println(4);
        String output = "Enter rows:" + System.lineSeparator() +
                "* * * * " + System.lineSeparator() +
                "* * * " + System.lineSeparator() +
                "* * " + System.lineSeparator() +
                "* "  + System.lineSeparator();

        App.printTriangle();
        // assertion
        assertEquals(output, bos.toString());
    }
}

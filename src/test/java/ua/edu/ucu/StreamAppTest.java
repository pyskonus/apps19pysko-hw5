package ua.edu.ucu;

import ua.edu.ucu.stream.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author andrii
 */
public class StreamAppTest {
    
    private IntStream intStream;

    @Before
    public void init() {
        int[] intArr = {-1, 0, 1, 2, 3};
        intStream = AsIntStream.of(intArr);
    }
    
    @Test
    public void testStreamOperations() {
        System.out.println("streamOperations");
        int expResult = 42;
        int result = StreamApp.streamOperations(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testStreamToArray() {
        System.out.println("streamToArray");
        int[] expResult = {-1, 0, 1, 2, 3};
        int[] result = StreamApp.streamToArray(intStream);
        assertArrayEquals(expResult, result);        
    }

    @Test
    public void testStreamForEach() {
        System.out.println("streamForEach");
        String expResult = "-10123";
        String result = StreamApp.streamForEach(intStream);
        assertEquals(expResult, result);        
    }

    @Test
    public void testAverage() {
        Double expResult = 1.0;
        Double actResult = intStream.average();
        assertEquals(expResult, actResult);
    }

    @Test
    public void testMax() {
        Integer expResult = 3;
        Integer actResult = intStream.max();
        assertEquals(expResult, actResult);
    }

    @Test
    public void testMin() {
        Integer expResult = -1;
        Integer actResult = intStream.min();
        assertEquals(expResult, actResult);
    }

    @Test
    public void testCount() {
        long expResult = 5;
        long actResult = intStream.count();
        assertEquals(expResult, actResult);
    }

    @Test
    public void testSum() {
        Integer expResult = 5;
        Integer actResult = intStream.sum();
        assertEquals(expResult, actResult);
    }

    @Test
    public void testThrows() {
        IntStream emptyStream = AsIntStream.of();

        boolean allExceptions = true;

        try {
            emptyStream.average();
            allExceptions = false;
        } catch (IllegalArgumentException e1) {
            try {
                emptyStream.min();
                allExceptions = false;
            } catch (IllegalArgumentException e2) {
                try {
                    emptyStream.max();
                    allExceptions = false;
                } catch (IllegalArgumentException e3) {
                    try {
                        emptyStream.sum();
                        allExceptions = false;
                    } catch(IllegalArgumentException e4) {}
                }
            }
        }

        assertTrue(allExceptions);
    }
}

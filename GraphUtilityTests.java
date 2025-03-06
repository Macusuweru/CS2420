package assign07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static assign07.GraphUtility.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Contains tests for the GraphUtility class.
 *
 * @author Maxwe// and Ashley Pedersen
 * @version March 6, 2025
 */
public class GraphUtilityTests {
    List<Integer> smallSources;
    List<Integer> smallDest;
    List<Integer> medSources;
    List<Integer> medDest;
    List<Integer> cyclicSources;
    List<Integer> cyclicDest;

    @BeforeEach
    public void setUp() {
        smallSources = new ArrayList<>();
        smallSources.add(0);
        smallSources.add(1);
        smallDest = new ArrayList<>();
        smallDest.add(1);
        smallDest.add(2);

        medSources = new ArrayList<>();
        medSources.add(2);
        medSources.add(2);
        medSources.add(1);
        medSources.add(0);
        medSources.add(0);
        medDest = new ArrayList<>();
        medDest.add(3);
        medDest.add(4);
        medDest.add(2);
        medDest.add(1);
        medDest.add(2);

        cyclicSources = new ArrayList<>();
        cyclicSources.add(0);
        cyclicSources.add(1);
        cyclicSources.add(2);
        cyclicDest = new ArrayList<>();
        cyclicDest.add(1);
        cyclicDest.add(2);
        cyclicDest.add(0);
    }

    // --- areConnected() Tests ---

    @Test
    public void areConnectedSmallTest() {
        assertTrue(areConnected(smallSources, smallDest, 0, 2));
    }

    @Test
    public void areConnectedMedTest() {
        assertTrue(areConnected(medSources, medDest, 0, 4));
    }

    @Test
    public void areConnectedCyclicTest() {
        assertTrue(areConnected(cyclicSources, cyclicDest, 0, 2));
    }

    @Test
    public void areNotConnectedTest() {
        assertFalse(areConnected(medSources, medDest, 3, 4));
    }

    @Test
    public void areNotConnectedBackwardsTest() {
        assertFalse(areConnected(medSources, medDest, 2, 1));
    }

    @Test
    public void areConnectedInvalidSrcExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> areConnected(smallSources, smallDest, 3, 0));
    }

    @Test
    public void areConnectedInvalidDestExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> areConnected(smallSources, smallDest, 0, 3));
    }

    @Test
    public void areConnectedMismatchedSourcesExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> areConnected(smallSources, medDest, 0, 1));
    }

    // --- shortestPath() Tests ---
    @Test
    public void shortestPathTest() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        expected.add(2);
        assertArrayEquals(expected.toArray(), shortestPath(medSources, medDest, 0, 2).toArray());
    }

    @Test
    public void shortestPathLongerTest() {
        Integer[] expected = new Integer[]{0, 2, 3};
        assertArrayEquals(expected, shortestPath(medSources, medDest, 0, 3).toArray());
    }

    @Test
    public void shortestPathToSelfTest() {
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        assertArrayEquals(expected.toArray(), shortestPath(medSources, medDest, 0, 0).toArray());
    }

    @Test
    public void shortestPathInvalidSrcExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> shortestPath(medSources, medDest, 5, 0));
    }

    @Test
    public void shortestPathInvalidDestExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> shortestPath(medSources, medDest, 0, 5));
    }

    @Test
    public void shortestPathMismatchedSourcesExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> shortestPath(smallSources, medDest, 0, 1));
    }

    @Test
    public void shortestPathNoPathExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> shortestPath(medSources, medDest, 3, 4));
    }

    // --- sort() Tests ---
    @Test
    public void sortSmallTest() {
        Integer[] expected = new Integer[]{0, 1, 2};

        assertArrayEquals(expected, sort(smallSources, smallDest).toArray());
    }

    @Test
    public void sortMedTest() {
        Integer[] expected1 = new Integer[]{0, 1, 2, 3, 4};
        Integer[] expected2 = new Integer[]{0, 1, 2, 4, 3};

        assertTrue(Arrays.equals(expected1, sort(medSources, medDest).toArray()) || Arrays.equals(expected2, sort(medSources, medDest).toArray()));
    }

    @Test
    public void sortCycleExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> sort(cyclicSources, cyclicDest));
    }

    @Test
    public void sortMismatchedSourcesExceptionTest() {
        assertThrows(IllegalArgumentException.class, () -> sort(smallSources, medDest));
    }
}

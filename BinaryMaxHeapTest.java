package assign10;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BinaryMaxHeapTest {
	private BinaryMaxHeap<Integer> empty;
	private BinaryMaxHeap<Integer> single;
	private BinaryMaxHeap<Integer> populated;

	@BeforeEach
	void setUp() {
		empty = new BinaryMaxHeap<Integer>();

		single = new BinaryMaxHeap<Integer>();
		single.add(5);

		populated = new BinaryMaxHeap<Integer>();
		for (int i = 2; i <= 40; i += 2)
			populated.add(i);

	}

	// Tests for add
	@Test
	void testAddOneElementEmpty() {
		assertEquals(0, empty.size());
		empty.add(5);
		assertEquals(1, empty.size());
		assertEquals(5, empty.peek());
	}

	@Test
	void testAddManyElementsEmpty() {
		assertEquals(0, empty.size());
		empty.add(25);
		empty.add(20);
		empty.add(15);
		empty.add(10);
		empty.add(5);
		assertEquals(5, empty.size());
		assertEquals(25, empty.toArray()[0]);
		assertEquals(20, empty.toArray()[1]);
		assertEquals(15, empty.toArray()[2]);
		assertEquals(10, empty.toArray()[3]);
		assertEquals(5, empty.toArray()[4]);
	}

	@Test
	void testAddOneElementSingle() {
		assertEquals(1, single.size());
		single.add(4);
		assertEquals(2, single.size());
		assertEquals(5, single.extractMax());
		assertEquals(4, single.extractMax());
	}

	@Test
	void testAddManyElementsSingle() {
		assertEquals(1, single.size());
		single.add(4);
		single.add(3);
		single.add(2);
		single.add(1);
		single.add(0);
		assertEquals(6, single.size());
		assertEquals(5, single.extractMax());
		assertEquals(4, single.extractMax());
		assertEquals(3, single.extractMax());
		assertEquals(2, single.extractMax());
		assertEquals(1, single.extractMax());
		assertEquals(0, single.extractMax());
	}

	@Test
	void testAddMinimumElementPopulated() {
		assertEquals(20, populated.size());
		populated.add(1);
		assertEquals(21, populated.size());
		assertEquals(1, populated.toArray()[20]);
	}

	@Test
	void testAddMiddleElementsPopulated() {
		assertEquals(20, populated.size());
		populated.add(5);
		assertEquals(21, populated.size());
		assertEquals(5, populated.toArray()[20]);

	}

	@Test
	void testAddMaximumElementsPopulated() {
		assertEquals(20, populated.size());
		populated.add(41);
		assertEquals(21, populated.size());
		assertEquals(41, populated.peek());
	}

	@Test
	void testAddResize() {
		assertEquals(0, empty.size());
		for (int i = 0; i < 16; i++) {
			empty.add(i);
		}
		assertEquals(16, empty.size());
		for (int i = 16; i < 32; i++) {
			empty.add(i);
		}
		assertEquals(32, empty.size());
		for (int i = 32; i < 48; i++) {
			empty.add(i);
		}
		assertEquals(48, empty.size());
	}

	// Tests for peek
	@Test
	void testPeekEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			empty.peek();
		});
	}

	@Test
	void testPeekSingle() {
		assertEquals(5, single.peek());
	}

	@Test
	void testPeekPopulated() {
		assertEquals(40, populated.peek());
		assertEquals(20, populated.size());
		assertEquals(40, populated.extractMax());
		assertEquals(38, populated.peek());
		populated.add(39);
		assertEquals(39, populated.peek());
		populated.add(9);
		assertEquals(39, populated.peek());
	}

	// Tests for extract max
	@Test
	void testExtractEmpty() {
		assertThrows(NoSuchElementException.class, () -> {
			empty.extractMax();
		});
	}

	@Test
	void testExtractSingle() {
		assertEquals(5, single.extractMax());
		assertEquals(0, single.size());
		assertThrows(NoSuchElementException.class, () -> {
			single.extractMax();
		});
	}

	@Test
	void testExtractPopulated() {
		for (int i = 40; i > 0; i -= 2)
			assertEquals(i, populated.extractMax());
		assertTrue(populated.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			populated.extractMax();
		});
	}

	@Test
	void testSize() {
		assertEquals(0, empty.size());
		assertEquals(1, single.size());
		assertEquals(20, populated.size());
	}

	@Test
	void testIsEmpty() {
		assertTrue(empty.isEmpty());

		assertFalse(single.isEmpty());
		single.extractMax();
		assertTrue(single.isEmpty());

		assertFalse(populated.isEmpty());
		while (!populated.isEmpty())
			populated.extractMax();
		assertTrue(populated.isEmpty());
	}

	@Test
	void testClear() {
		empty.clear();
		assertTrue(empty.isEmpty());
		assertEquals(0, empty.size());

		single.clear();
		assertTrue(single.isEmpty());
		assertEquals(0, single.size());

		populated.clear();
		assertTrue(populated.isEmpty());
		assertEquals(0, populated.size());
		populated.add(3);
		assertEquals(3, populated.peek());
		assertEquals(1, populated.size());
	}

	@Test
	void testToArray() {
		assertTrue(empty.toArray() instanceof Object[]);
		assertTrue(empty.toArray().length == 0);
		assertEquals(5, single.toArray()[0]);
		single.add(3);
		single.add(6);
		single.add(7);
		Object[] actual = single.toArray();
		Object[] expected = new Object[4];
		expected[0] = 7;
		expected[1] = 5;
		expected[2] = 6;
		expected[3] = 3;
		
		assertTrue(expected[0] == actual[0]);
		assertTrue(expected[1] == actual[1]);
		assertTrue(expected[2] == actual[2]);
		assertTrue(expected[3] == actual[3]);
	}
	 @Test
	void test() {
	}
}

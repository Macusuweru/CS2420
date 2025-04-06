package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Binary Max Heap implementing the Priority Queue interface.
 * @author Maxwell and Cooper
 * @version April 6, 2025
 */
public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	private Comparator<? super E> cmp;
	private Object[] list;
	private int size;

	/**
	 * Constructor which takes both a list to immediately insert and a custom comparator
	 * @param list Initial elements in the Heap
	 * @param cmp Custom Comparator
	 */
	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this(cmp);
		for (E e : list)
			this.add(e);
	}

	/**
	 * Constructor which takes a list to immediately insert
	 * @param list Initial elements in the Heap
	 */
	public BinaryMaxHeap(List<? extends E> list) {
		this(list, (Comparator<? super E>) Comparator.naturalOrder());
	}

	/**
	 * Constructor which takes a custom comparator
	 * @param cmp Custom Comparator
	 */
	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = cmp;
		list = new Object[16];
		size = 0;
	}

	/**
	 * Basic constructor
	 */
	public BinaryMaxHeap() {
		this((Comparator<? super E>) Comparator.naturalOrder());
	}

	/**
	 * Adds the given item to this priority queue. O(1) in the average case, O(log
	 * N) in the worst case
	 * 
	 * @param item
	 */
	public void add(E item) {
		//Increment size. If the new item cannot fit into the backing array, double its size
		size++;
		if (size >= list.length) {
			Object[] newList = new Object[list.length * 2];
			for (int i = 1; i < list.length; i++)
				newList[i] = list[i];
			list = newList;
		}
		// Percolate up. It's only used here, so no need for a private function
		int index = size;
		// Stop if the parent is greater than the new entry, or we reached the head
		while (index > 1 && cmp.compare(get(index / 2), item) < 0) {
			list[index] = get(index / 2);
			index = index / 2;
		}
		list[index] = item;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue. O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		return get(1);
	}

	/**
	 * Returns and removes the maximum item this priority queue. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException {
		if (size == 0)
			throw new NoSuchElementException();
		E r = get(1);
		list[1] = null;
		percolateDown(1);
		size--;
		return r;
	}

	/**
	 * Returns the number of items in this priority queue. O(1)
	 */
	public int size() {
		return size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise. O(1)
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Empties this priority queue of items. O(1)
	 */
	public void clear() {
		size = 0;
		list = new Object[32];
	}

	/**
	 * Creates and returns an array of the items in this priority queue, in the same
	 * order they appear in the backing array. O(N)
	 * 
	 * (NOTE: This method is needed for grading purposes. The root item must be
	 * stored at index 0 in the returned array, regardless of whether it is stored
	 * there in the backing array.)
	 */
	public Object[] toArray() {
		Object[] array = new Object[size];
		for (int i = 0; i < size; i++)
			array[i] = list[i + 1];
		return array;
	}

	/**
	 * Private percolateDown function which recursively takes out of order entries and percolates it down the heap, correcting the overall structure.
	 * @param index The index of an out-of-place entry.
	 */
	private void percolateDown(int index) {
		// Three cases, no children, 1 child, 2 child
		// Must also account for the entry being null, in case of deletion, always treating it as a lesser value
		// 1 child: This child has no children, no recursion.
		if (index * 2 == size) {
			if (get(index) == null || cmp.compare(get(index), get(index * 2)) < 0) {
				E hold = get(index);
				list[index] = get(index * 2);
				list[index * 2] = hold;
			}
		} 
		// 2 children: Compare them. Swap parent with the greater child. Perform recursion on the swapped child index, now containing the percolated value.
		else if (index * 2 < size) {
			if (cmp.compare(get(index * 2), get(index * 2 + 1)) < 0) {
				if (get(index) == null || cmp.compare(get(index), get(index * 2 + 1)) < 0) {
					E hold = get(index);
					list[index] = get(index * 2 + 1);
					list[index * 2 + 1] = hold;
					percolateDown(index * 2 + 1);
				}
			} else {
				if (get(index) == null || cmp.compare(get(index), get(index * 2)) < 0) {
					E hold = get(index);
					list[index] = get(index * 2);
					list[index * 2] = hold;
					percolateDown(index * 2);
				}
			}
		}
		// 0 children: do nothing.
		//In the case that the final node with no children
		else if (get(index) == null) {
			E item = list[size];
			// Percolate up.
			int i = index;
			// Stop if the parent is greater than the new entry, or we reached the head
			while (index > 1 && cmp.compare(get(index / 2), item) < 0) {
				list[index] = get(index / 2);
				index = index / 2;
			}
			list[index] = item;
		}
	}
	/**
	 * Access the given index in the list array, casting it to E.
	 * @param index The index to access
	 * @return The entry of index in list, cast to E
	 */
	private E get(int index) {
		return (E) list[index];
	}
}
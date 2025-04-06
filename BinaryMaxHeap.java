package assign10;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

public class BinaryMaxHeap<E> implements PriorityQueue<E> {
	private Comparator<? super E> cmp;
	private Object[] list;
	private int size;

	public BinaryMaxHeap(List<? extends E> list, Comparator<? super E> cmp) {
		this(cmp);
		for (E e : list) {
			this.add(e);
		}
	}

	public BinaryMaxHeap(List<? extends E> list) {
		this(list, (Comparator<? super E>) Comparator.naturalOrder());
	}

	public BinaryMaxHeap(Comparator<? super E> cmp) {
		this.cmp = cmp;
	}

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
		percolateUp(item);
		size++;
	}

	/**
	 * Returns, but does not remove, the maximum item this priority queue. O(1)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E peek() throws NoSuchElementException {
		if (this.size == 0)
			throw new NoSuchElementException();
		return this.get(1);
	}

	/**
	 * Returns and removes the maximum item this priority queue. O(log N)
	 * 
	 * @return the maximum item
	 * @throws NoSuchElementException if this priority queue is empty
	 */
	public E extractMax() throws NoSuchElementException {
		if (this.size == 0)
			throw new NoSuchElementException();
		E r = this.get(1);
		this.list[1] = null;
		percolateDown(1);
		size--;
		return r;
	}

	/**
	 * Returns the number of items in this priority queue. O(1)
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Returns true if this priority queue is empty, false otherwise. O(1)
	 */
	public boolean isEmpty() {
		return this.size == 0;
	}

	/**
	 * Empties this priority queue of items. O(1)
	 */
	public void clear() {
		this.size = 0;
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
		for (int i = 0; i < size; i++) {
			array[i] = list[i + 1];
		}
		return array;
	}

	private void percolateUp(E value) {
		int index = this.size + 1;
		while (cmp.compare(get(index / 2), value) < 0 || index > 1) {
			list[index] = get(index / 2);
			index = index / 2;
		}
		list[index] = value;
	}

	private void percolateDown(int index) {
		// Three cases, no children, 1 child, 2 child
		if (index * 2 == this.size) {
			if (get(index) == null) {
				list[index] = get(index * 2);
				list[index * 2] = null;
			} else if (cmp.compare(get(index), get(index * 2)) < 0) {
				E hold = get(index);
				list[index] = get(index * 2);
				list[index * 2] = hold;
			}
		} else if (index * 2 + 1 <= this.size) {
			if (cmp.compare(get(index * 2), get(index * 2 + 1)) < 0) {
				if (get(index) == null) {
					list[index] = get(index * 2 + 1);
					list[index * 2] = null;
					percolateDown(index * 2 + 1);
				} else if (cmp.compare(get(index), get(index * 2 + 1)) < 0) {
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
	}

	private E get(int index) {
		return (E) list[index];
	}
}
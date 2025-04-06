package assign10;

import java.util.Comparator;
import java.util.List;

/**
 * This class contains generic static methods for sorting a descending delta-sorted list.
 * The result of both methods is that the list will be in descending order.
 * 
 * @author CS 2420 course staff, Maxwell, Cooper
 * @version April 6, 2025
 */
public class DeltaSorter {
	
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses the natural ordering of the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T extends Comparable<? super T>> void sort(List<T> list, int delta){
		BinaryMaxHeap<T> sorter = new BinaryMaxHeap<>();
		int i = 0;
		int j = 0;
		while(i < delta)
			sorter.add(list.get(i++));
		while(i < list.size()) {
			sorter.add(list.get(i++));
			list.set(j++, sorter.extractMax());
		}
		while(j < list.size())
			list.set(j++, sorter.extractMax());
	}
	
	/**
	 * Fully sorts a descending, delta-sorted list using a BinaryMaxHeap.
	 * After completing, the provided list will contain the same items in descending order.
	 * This version uses a provided comparator to order the elements.
	 * 
	 * @param list to sort that is currently delta-sorted and will be fully sorted
	 * @param delta value of the delta-sorted list
	 * @param cmp Comparator for ordering the elements
	 * @throws IllegalArgumentException if delta is less than 0 or greater than or
	 *         equal to the size of the list
	 */
	public static <T> void sort(List<T> list, int delta, Comparator<? super T> cmp){
		BinaryMaxHeap<T> sorter = new BinaryMaxHeap<>(cmp);
		int i = 0;
		int j = 0;
		while(i < delta)
			sorter.add(list.get(i++));
		while(i < list.size()) {
			sorter.add(list.get(i++));
			list.set(j++, sorter.extractMax());
		}
		while(j < list.size())
			list.set(j++, sorter.extractMax());
	}
}
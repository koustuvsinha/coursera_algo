package com.coursera.algorithm;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	/**
	 * Constructor to construct an empty deque
	 */
	public Deque() {
		
	}
	
	/**Check if the deque is empty
	 * @return true or false
	 */
	public boolean isEmpty() {
		return false;
	}
	
	/**
	 * @return the number of items on the deque
	 */
	public int size() {
		return 0;
	}
	
	/**
	 * @param item insert item at the front
	 */
	public void addFirst(Item item) {
		
	}
	
	/**
	 * @param item insert item at the last
	 */
	public void addLast(Item item) {
		
	}
	
	/**
	 * @return delete and return the item at the front
	 */
	public Item removeFirst() {
		return null;
	}
	
	/**
	 * @return delete and return the item at the end
	 */
	public Item removeLast() {
		return null;
	}
	
	/* return an iterator over items in order from front to end
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * @param args unit testing
	 */
	public static void main(String[] args) {
		
	}
	
}

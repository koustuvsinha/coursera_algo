package com.coursera.algorithm;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Item[] deque;
	private static final int INITIAL_ARRAY_LENGTH = 10;
	private static final int ARRAY_INCREASE_FACTOR = 2;
	private int arrayLength = 0;
	private int arrayFirst = 0;
	private int arrayLast = 0;
	
	/**
	 * Constructor to construct an empty deque
	 */
	@SuppressWarnings("unchecked")
	public Deque() {
		
		deque = (Item[])new Object[INITIAL_ARRAY_LENGTH];
		arrayLength = INITIAL_ARRAY_LENGTH;
		arrayFirst = arrayLast = 0;
		
	}
	
	/**Check if the deque is empty
	 * @return true or false
	 */
	public boolean isEmpty() {
		
		if(arrayFirst == arrayLast) {
			return true;
		}
		else {
			return false;
		}
		
	}
	
	/**
	 * @return the number of items on the deque
	 */
	public int size() {
		return arrayLast - arrayFirst;
	}
	
	/**
	 * @param item insert item at the front
	 */
	public void addFirst(Item item) {
		
		if(deque.length == arrayLength) {
			increaseArrayLength();
		}
		
		deque[--arrayFirst] = item;
	}
	
	/**
	 * @param item insert item at the last
	 */
	public void addLast(Item item) {
		
		if(deque.length == arrayLength) {
			increaseArrayLength();
		}
		
		deque[arrayLast++] = item;
		
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
	 * Increase the array by twice
	 */
	private void increaseArrayLength() {
		
		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[])new Object[arrayLength*ARRAY_INCREASE_FACTOR];
		
		for(int i=arrayFirst;i<arrayLast;i++) {
			newArray[i] = deque[i];
		}
		
		deque = newArray;
		newArray = null;
		
		arrayLength = arrayLength*ARRAY_INCREASE_FACTOR;
		
	}
	
	/**
	 * Reduce the array by half
	 */
	private void decreaseArrayLength() {
	
		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[])new Object[arrayLength/ARRAY_INCREASE_FACTOR];
		
		for(int i=arrayFirst;i<arrayLast;i++) {
			newArray[i] = deque[i];
		}
		
		deque = newArray;
		newArray = null;
		
		arrayLength = arrayLength/ARRAY_INCREASE_FACTOR;
	}
	
	/**
	 * @param args unit testing
	 */
	public static void main(String[] args) {
		
	}
	
}

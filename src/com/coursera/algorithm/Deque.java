package com.coursera.algorithm;

import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

	private Item[] deque;
	private static final int INITIAL_ARRAY_LENGTH = 10;
	private static final int ARRAY_INCREASE_FACTOR = 2;
	private int arrayLength = 0;
	private int arrayFirst = 0;
	private int arrayLast = 0;
	private int position = 0;
	
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
		
		if(item == null)
			throw new NullPointerException();
		
		if(size() == arrayLength) {
			increaseArrayLength();
		}
		
		if(arrayFirst == 0) {
			reArrangeArray();
			deque[0] = item;
			arrayFirst = 0;
		}
		else {
			deque[--arrayFirst] = item;
		}
		
		
		
		
	}
	
	/**
	 * @param item insert item at the last
	 */
	public void addLast(Item item) {
		
		if(item == null)
			throw new NullPointerException();
		
		if(size() == arrayLength) {
			increaseArrayLength();
		}
		
		deque[arrayLast++] = item;
		
	}
	
	/**
	 * @return delete and return the item at the front
	 */
	public Item removeFirst() {
		
		Item first = null;
		
		if(arrayFirst == 0) {
			first = deque[0];
			shiftArrayLeft();
		} else {
			first = deque[arrayFirst--];
		}
		
		
		
		if(size()<=arrayLength/4) {
			decreaseArrayLength();
		}
		
		return first;
	}
	
	/**
	 * @return delete and return the item at the end
	 */
	public Item removeLast() {
		
		Item last = deque[--arrayLast];
		
		if(size()<=arrayLength/4) {
			decreaseArrayLength();
		}
		
		return last;
	}
	
	/* return an iterator over items in order from front to end
	 * (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		
		return new DequeIterator<Item>();
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
	
	@SuppressWarnings("unchecked")
	private void reArrangeArray() {
		
		Item[] newArray = null;
		
		if(size()==arrayLength) {
			newArray = (Item[])new Object[arrayLength*ARRAY_INCREASE_FACTOR];
		} else {
			newArray = (Item[])new Object[arrayLength];
		}
		
		
		for(int i=0;i<arrayLast;i++) {
			newArray[i+1] = deque[i];
		}
		
		arrayLast++;
		
		deque = newArray;
		newArray = null;
		
	}
	
	private void shiftArrayLeft() {
		
		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[])new Object[arrayLength];
		
		for(int i=0;i<arrayLast-1;i++) {
			newArray[i] = deque[i+1];
		}
		
		arrayLast--;
		deque = newArray;
		newArray = null;
		
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
	
	
	@SuppressWarnings("hiding")
	private class DequeIterator<Item> implements Iterator<Item> {

		@Override
		public boolean hasNext() {
			
			if (position < arrayLast) {
				return true;
			} else {
				return false;
			}
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public Item next() {
			
			if(position < arrayLast) {
			
				return (Item) deque[position++];
			
			} else {
				
				throw new java.util.NoSuchElementException(); 
				
			}
			
		}

		@Override
		public void remove() {
			
			throw new UnsupportedOperationException();
			
		}
		
	}
	
	/**
	 * @param args unit testing
	 */
	public static void main(String[] args) {
		
		Deque<Integer> dequeArray = new Deque<Integer>();
		
		dequeArray.addLast(new Integer(6));
		dequeArray.addLast(new Integer(10));
		dequeArray.addFirst(new Integer(3));
		dequeArray.addFirst(new Integer(2));
		dequeArray.addFirst(new Integer(1));
		dequeArray.addFirst(new Integer(0));
		dequeArray.addLast(new Integer(11));
		dequeArray.addLast(new Integer(12));
		dequeArray.addLast(new Integer(13));
		dequeArray.addLast(new Integer(14));
		dequeArray.addLast(new Integer(15));
		
		System.out.println("Removing items...");
		System.out.println(dequeArray.removeLast());
		System.out.println(dequeArray.removeLast());
		System.out.println(dequeArray.removeLast());
		System.out.println(dequeArray.removeFirst());
		System.out.println(dequeArray.removeLast());
		//System.out.println(dequeArray.removeLast());
		
		Iterator<Integer> iterator = dequeArray.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}
		
	}
	
	
}

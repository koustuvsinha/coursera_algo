import java.util.Iterator;

/**
 * @author koustuvs Deque implementation for coursera
 * 
 * @param <Item>
 */
public class Deque<Item> implements Iterable<Item> {

	/**
	 * . deque in Item array
	 */
	private Item[] deque;
	/**
	 * . constant to set increase factor
	 */
	private static final int ARRAY_INCREASE_FACTOR = 2;
	/**
	 * . constant to set initial array length
	 */
	private static final int INITIAL_ARRAY_LENGTH = 10;
	/**
	 * . arraylength variable
	 */
	private int arrayLength = 0;
	/**
	 * . track first element
	 */
	private int arrayFirst = 0;
	/**
	 * . track last element
	 */
	private int arrayLast = 0;
	/**
	 * . track position for iterator
	 */
	private int position = 0;

	/**
	 * . Constructor to construct an empty deque
	 */
	@SuppressWarnings("unchecked")
	public Deque() {
		deque = (Item[]) new Object[INITIAL_ARRAY_LENGTH];
		arrayLength = INITIAL_ARRAY_LENGTH;
		arrayFirst = 0;
		arrayLast = 0;
	}

	/**.
	 * Check if the deque is empty
	 * .
	 * @return true or false
	 */
	public boolean isEmpty() {

		return arrayFirst == arrayLast;

	}

	/**
	 * @return the number of items on the deque
	 */
	public int size() {
		return arrayLast - arrayFirst;
	}

	/**
	 * @param item
	 *            insert item at the front
	 */
	public void addFirst( Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		if (arrayFirst == 0) {
			reArrangeArray();
			deque[0] = item;
			arrayFirst = 0;
		} else {
			deque[--arrayFirst] = item;
		}

		if (size() == arrayLength) {
			increaseArrayLength();
		}

	}

	/**
	 * @param item
	 *            insert item at the last
	 */
	public void addLast( Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		if (size() == arrayLength) {
			increaseArrayLength();
		}

		deque[arrayLast++] = item;

	}

	/**
	 * @return delete and return the item at the front
	 */
	public Item removeFirst() {

		Item first = null;

		if (arrayLast == 0) {
			throw new java.util.NoSuchElementException();
		}

		if (arrayFirst == 0) {
			first = deque[0];
			shiftArrayLeft();
		} else {
			first = deque[arrayFirst--];
		}

		if (size() <= arrayLength / 4) {
			decreaseArrayLength();
		}

		return first;
	}

	/**
	 * @return delete and return the item at the end
	 */
	public Item removeLast() {

		if (arrayLast == 0) {
			throw new java.util.NoSuchElementException();
		}

		Item last = deque[--arrayLast];

		if (size() <= arrayLength / 4) {
			decreaseArrayLength();
		}

		return last;
	}

	/*
	 * return an iterator over items in order from front to end (non-Javadoc)
	 * 
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public  Iterator<Item> iterator() {
		// TODO Auto-generated method stub

		return new DequeIterator<Item>();
	}

	/**.
	 * Increase the array by twice
	 */
	private void increaseArrayLength() {

		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[]) new Object[arrayLength
				* ARRAY_INCREASE_FACTOR];

		for (int i = arrayFirst; i < arrayLast; i++) {
			newArray[i] = deque[i];
		}

		deque = newArray;
		newArray = null;

		arrayLength = arrayLength * ARRAY_INCREASE_FACTOR;

	}

	/**.
	 * Rearranges the array
	 */
	@SuppressWarnings("unchecked")
	private void reArrangeArray() {

		Item[] newArray = null;

		if (size() == arrayLength) {
			increaseArrayLength();
			newArray = deque;
		} else {
			newArray = (Item[]) new Object[arrayLength];
		}

		for (int i = 0; i < arrayLast; i++) {
			newArray[i + 1] = deque[i];
		}

		arrayLast++;

		deque = newArray;
		newArray = null;

	}

	/**.
	 * shift elements to left
	 */
	private void shiftArrayLeft() {

		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[]) new Object[arrayLength];

		for (int i = 0; i < arrayLast - 1; i++) {
			newArray[i] = deque[i + 1];
		}

		arrayLast--;
		deque = newArray;
		newArray = null;

	}

	/**.
	 * Reduce the array by half
	 */
	private void decreaseArrayLength() {

		if (arrayLength != INITIAL_ARRAY_LENGTH) {

			@SuppressWarnings("unchecked")
			Item[] newArray = (Item[]) new Object[arrayLength
					/ ARRAY_INCREASE_FACTOR];

			for (int i = arrayFirst; i < arrayLast; i++) {
				newArray[i] = deque[i];
			}

			deque = newArray;
			newArray = null;

			arrayLength = arrayLength / ARRAY_INCREASE_FACTOR;

		}

	}

	/**Custom iterator.
	 * @author koustuvs
	 *
	 * @param <Item>
	 */
	@SuppressWarnings("hiding")
	private class DequeIterator<Item> implements Iterator<Item> {

		@Override
		public boolean hasNext() {

			return position < arrayLast;

		}

		@SuppressWarnings("unchecked")
		@Override
		public Item next() {

			if (position < arrayLast) {

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
	 * @param args
	 *            unit testing
	 */
	public static void main( String[] args) {

		Deque<Integer> dequeArray = new Deque<Integer>();

		int count = 1000;

		for (int i = 0; i < count; i++) {

			int randec = StdRandom.uniform(4);
			int randnum = StdRandom.uniform(count);

			switch (randec) {

			case 0:
				dequeArray.addFirst(randnum);
				System.out.println("Added first : " + randnum);
				break;
			case 1:
				dequeArray.addLast(randnum);
				System.out.println("Added last : "
						+ randnum);
				break;
			case 2 : 
				try {
					System.out.println("Remove first : " + dequeArray.removeFirst());
				} catch (java.util.NoSuchElementException e) {
					System.out.println("removeFirst no element found");
				}
				break;
			case 3 : 
				try {
					System.out.println("Remove Last : " + dequeArray.removeLast());
				} catch (java.util.NoSuchElementException e) {
					System.out.println("removeLast no element found");
				}
				
				break;
			default:
				break;

			}

		}

		System.out.println("Printing out array..");

		Iterator<Integer> iterator = dequeArray.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next().toString() + " ");
		}

	}

}

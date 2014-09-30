import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	/**
	 * randomQueue. implementation
	 */
	private Item[] randomQueue;
	/**
	 * 
	 */
	private static final int INITIAL_ARRAY_LENGTH = 10;
	/**
	 * 
	 */
	private static final int ARRAY_INCREASE_FACTOR = 2;
	/**
	 * 
	 */
	private int arrayLength = 0;
	/**
	 * 
	 */
	private int position = 0;
	/**
	 * 
	 */
	private int arrayLast = 0;

	/**
	 * 
	 */
	private int hole = -1;
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {

		randomQueue = (Item[]) new Object[INITIAL_ARRAY_LENGTH];
		arrayLength = INITIAL_ARRAY_LENGTH;
		arrayLast = 0;

	}

	/**
	 * @return true false
	 */
	public boolean isEmpty() {
		return arrayLast == 0;
	}

	/**
	 * @return size
	 */
	public int size() {
		return arrayLast;
	}

	/**
	 * @param item
	 *            item
	 */
	public void enqueue(Item item) {

		if (item == null) {
			throw new NullPointerException();
		}

		if (size() == arrayLength) {
			increaseArrayLength();
		}

		randomQueue[arrayLast++] = item;

	}

	/**
	 * @return item
	 */
	public Item dequeue() {
		
		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		}

		Item randomItem = null;
		int n = arrayLast;
		while (n > 0) {

			int random = StdRandom.uniform(n--);
			randomItem = randomQueue[random];

			if (randomItem != null) {
				randomQueue[random] = null;
				arrayLast--;
				break;
			}

		}

		if (size() <= arrayLength / 4) {
			decreaseArrayLength();
		}

		return randomItem;
	}

	/**
	 * @return item
	 */
	public Item sample() {

		if(isEmpty()) {
			throw new java.util.NoSuchElementException();
		}
		
		int random = StdRandom.uniform(arrayLast);
		Item randomItem = randomQueue[random];

		// System.out.println("Random position : " + random);

		return randomItem;
	}

	@Override
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator<Item>();
	}

	/**
	 * . Increase the array by twice
	 */
	private void increaseArrayLength() {

		@SuppressWarnings("unchecked")
		Item[] newArray = (Item[]) new Object[arrayLength
				* ARRAY_INCREASE_FACTOR];

		for (int i = 0; i < arrayLast; i++) {
			newArray[i] = randomQueue[i];
		}

		randomQueue = newArray;
		newArray = null;

		arrayLength = arrayLength * ARRAY_INCREASE_FACTOR;

	}

	/**
	 * . Reduce the array by half
	 */
	private void decreaseArrayLength() {

		if (arrayLength != INITIAL_ARRAY_LENGTH) {

			@SuppressWarnings("unchecked")
			Item[] newArray = (Item[]) new Object[arrayLength
					/ ARRAY_INCREASE_FACTOR];

			for (int i = 0; i < arrayLast; i++) {
				newArray[i] = randomQueue[i];
			}

			randomQueue = newArray;
			newArray = null;

			arrayLength = arrayLength / ARRAY_INCREASE_FACTOR;

		}
	}

	/**
	 * Custom Iterator.
	 * 
	 * @author koustuvs
	 * 
	 * @param <Item>
	 */
	@SuppressWarnings("hiding")
	private class RandomizedQueueIterator<Item> implements Iterator<Item> {

		@Override
		public boolean hasNext() {

			return position < arrayLast;

		}

		@SuppressWarnings("unchecked")
		@Override
		public Item next() {

			if (position < arrayLast) {

				return (Item) randomQueue[position++];

			} else {

				throw new java.util.NoSuchElementException();

			}

		}

		@Override
		public void remove() {

			throw new UnsupportedOperationException();

		}

	}

	public static void main(String args[]) {

		RandomizedQueue<Integer> randomQueue = new RandomizedQueue<Integer>();

		int count = 10;

		for (int i = 0; i < count; i++) {

			int randec = StdRandom.uniform(2);
			int randnum = StdRandom.uniform(count);

			switch (randec) {

			case 0:
				randomQueue.enqueue(randnum);
				System.out.println("Enqueued : " + randnum);
				break;
			case 1:
				System.out.println("Dequeued : "
						+ randomQueue.dequeue());
				break;
			default:
				break;

			}

		}

		System.out.println("Iteration..");

		Iterator<Integer> iterator = randomQueue.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next().toString());
		}

	}

}

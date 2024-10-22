package dtcc.itn262;

public class CircularArrayQueue<E> implements Queue<E> {
	private int front;      // Index of the front element
	private int rear;       // Index of the rear element
	private int size;       // Current number of elements
	private E[] listArray;       // Array to store queue elements
	private static final int DEFAULT_CAPACITY = 10;


	public CircularArrayQueue() {this(DEFAULT_CAPACITY);}

	@SuppressWarnings("unchecked")
	CircularArrayQueue(int capacity) {
		listArray = (E[]) new Object[capacity];
		front = rear = size = 0;
	}

	@Override
	public void enqueue(E it) throws IllegalStateException {
		if (size == listArray.length) {
			throw new IllegalStateException("Queue is full");
		}
		listArray[rear] = it;
		rear = (rear + 1) % listArray.length;
		size++;
	}

	@Override
	public E dequeue() {
		if (size == 0){
			System.out.println("Queue is empty");
			return null;
		}
		E it = listArray[front];
		listArray[front] = null; // Help garbage collection
		front = (front + 1) % listArray.length;
		size--;
		return it;
	}

	@Override
	public E frontValue() {
		if (size == 0) return null;
		return listArray[front];
	}

	@Override
	public int length() {
		return size;
	}
}



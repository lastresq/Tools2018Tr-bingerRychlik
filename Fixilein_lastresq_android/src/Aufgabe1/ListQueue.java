package Aufgabe1;

public class ListQueue extends DoubleLinkedList{

	/** Initializes an empty queue. */ 
	public ListQueue() {
		super();
	} 
	
	/** Copy constructor which initializes the queue with another queue.
	This constructor must COPY all elements of the other queue. */ 
	public ListQueue(ListQueue other) {
		super(other);
	} 
	
	/** Enqueues an element at the back of the queue. */ 
	public void enqueue(int val) {
		super.pushFront(val);
	} 
	
	/** Dequeues the element at the front of the queue. */ 
	public int dequeue() {
		return super.popBack();
	} 
	
	/** Returns the front element of the queue without removing it. */ 
	public int peek() {
		return super.peekBack();  
	}
	
	public static void main(String[] args) {
		ListQueue listq = new ListQueue();
		listq.enqueue(8);
		listq.enqueue(12);
		listq.enqueue(13);
		System.out.println(listq.elements());
		System.out.println(listq.peek());
		System.out.println(listq.dequeue());

	}
	

}

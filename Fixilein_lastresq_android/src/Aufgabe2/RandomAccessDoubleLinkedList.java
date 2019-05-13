package Aufgabe2;

public class RandomAccessDoubleLinkedList extends DoubleLinkedList {


	/** Initializes an empty list. */ 
	public RandomAccessDoubleLinkedList() {
		super();
	} 
	
	/** Copy constructor which initializes the list with another list. 
	This constructor must COPY all elements of the other list. */ 
	public RandomAccessDoubleLinkedList(RandomAccessDoubleLinkedList 
	other) {super(other);} 
	
	/** Inserts a new element with value val at the given index. If the 
	index is larger than the current size, the list is padded with 
	uninitialized DLNodes. If the node, which was at the given index 
	before the insertion, is not initialized, then that Node must be 
	reused. Should index be < 0, then do nothing. */ 
	public void insertAt (int index, int val) {
		if (index < 0) {
			throw new IllegalArgumentException("Index muss >= 0! sein");
		}
		
		DLNode node = head;
		for (int i = 0; i < index; i++) {
			if (node.getNext() == null) {
				pushBack(Integer.MIN_VALUE);
			}
			node = node.getNext();
		}
		if (node == null) {
			this.pushBack(val);
		} else {
			node.setVal(val);
		}
	} 
	
	/** Returns true if an element with the given value exists, false 
	otherwise. However, true is returned upon the first occurrence of 
	val. */ 
	public boolean contains (int val) {
		return super.search(val);  
	} 
	
	/** Removes the element at the given index. Returns false if 
	index > list’s size. */ 
	public boolean removeAt (int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Index muss  >= 0 sein!");
		}
		DLNode node = head;
		if (index > elements() || elements() == 0) {
			return false;
		} else {
			for (int i = 0; i < index; i++) {
				node = node.getNext();
			}
			
			DLNode prev = node.getPrev();
			DLNode next = node.getNext();
			if (prev == null) {
				head = next;
			} else {
				prev.setNext(next);
			}
			
			if (next == null) {
				tail = prev;
			} else {
				next.setPrev(prev);
			}
			super.elements--;
			return true;
		}
	} 

	
	/** Removes all elements with the given value. False if returned if 
	val was not found. */ 
	public boolean removeAll (int val) {
		DLNode node = head;
		int count = 0;
		for (int i = 0; i < elements(); i++) {
			if (node.getVal() == val) {
				removeAt(i);
				count++;
				i--;
			}
			node = node.getNext();
		}
		if (count > 0) {
			return true;
		} else {
			return false;  
		}
	} 
	
	/** Returns the integer value at the given index. If index > list’s 
	size, Integer.MIN_VALUE is returned.  */
	
	public int elementAt(int index) {
		if (index < 0) {
			throw new IllegalArgumentException("Index muss  >= 0 sein!");
		}
		DLNode node = head;
		if (index > elements()) {
			return Integer.MIN_VALUE;
		} else { 
			for (int i = 0; i < index; i++) {
				node = node.getNext();
			}
			if (node == null) {
				throw new IllegalArgumentException("Nicht Initialisiert!");
			}
			return node.getVal(); 
		}
	} 
	

	public static void main(String[] args) {
		RandomAccessDoubleLinkedList radl = new RandomAccessDoubleLinkedList();
		radl.pushFront(10);
		radl.pushFront(8);
		radl.insertAt(3, 4);
		
		System.out.println(radl.removeAt(2));
		System.out.println(radl.elementAt(0));
		
		System.out.println(radl.elementAt(1));
		System.out.println(radl.elementAt(2));
		
		radl.pushFront(15);
		
		System.out.println();
		System.out.println(radl.elementAt(0));
		System.out.println(radl.elementAt(1));
		
		System.out.println(radl.elementAt(2));
		System.out.println(radl.elementAt(3));
		
		System.out.println();
		
		System.out.println(radl.removeAll(13));
		System.out.println(radl.elementAt(0));
		System.out.println(radl.elementAt(1));
		
	}
	
}

package Aufgabe2;import Aufgabe2.DLNode;

public class DLNode {
	
	/** Reference to the next element in the list, or null if it is the
	last */
	private DLNode next;
	
	/** Reference to the previous element in the list, or null if it is
	the first */
	private DLNode prev;
	
	/** Holds the actual data. Must equal Integer.MIN_VALUE if node
	contains a list */
	private int val;
	
	/** Holds another list. Must be null if val != Integer.MIN_VALUE */
	private DoubleLinkedList list;
	
	public DLNode(){ 
		val = Integer.MIN_VALUE; 
		list = null; 
	}
	
	public boolean isInitialized(){ 
		return list != null || val != Integer.MIN_VALUE; 
	}
	
	public DoubleLinkedList getList() {
		return list;
	}

	public void setList(DoubleLinkedList list) {
		this.list = list;
	}

	public DLNode getNext() {
		return next;
	}

	public void setNext(DLNode next) {
		this.next = next;
	}

	public DLNode getPrev() {
		return prev;
	}

	public void setPrev(DLNode prev) {
		this.prev = prev;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}
}

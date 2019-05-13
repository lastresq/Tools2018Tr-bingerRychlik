package Aufgabe1; import Aufgabe1.DLNode;

public class DoubleLinkedList {

	/** Pointer to the first and last element of the list*/
	DLNode head, tail;
	private int elements = 0;


	/** Constructor initializes list with a standard size.*/
	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	/**Copy constructor initializes list with another list. This constructor
	 * must COPY all elements of the other list. The elements of the other list
	 * must NOT be changed!*/
	public DoubleLinkedList(DoubleLinkedList other) {
		DoubleLinkedList help = new DoubleLinkedList();
		help = other.clone();
		this.pushFront(help);
	}

	/**Clears all elements from the linked list*/
	public void clear() {
		 while (head != null && tail != null) {
		 popFront();
		 }
		head = null;
		tail = null;
		elements = 0;
	}

	/**Adds an element at the front of the linked list.*/
	public void pushFront(int val) {
		DLNode n = new DLNode();
		n.setVal(val);
		if (head == null) {
			head = n;
			tail = n;
		} else {
			n.setNext(head);
			head.setPrev(n);
			head = n;
			head.setPrev(null);
		}
		elements++;
	}

	/**Adds an element at the back of the linked list.*/
	public void pushBack(int val) {
		DLNode n = new DLNode();
		n.setVal(val);
		if (tail == null) {
			head = n;
			tail = n;
		} else {
			n.setPrev(tail);
			tail.setNext(n);
			tail = n;
		}
		elements++;
	}

	/**Removes and returns the front element of the linked list, or if the first
	 * element is another list, returns that list’s first element. Returns
	 * Integer.MIN_VALUE if empty*/
	public int popFront() {
		int val = 0;
		DLNode n = new DLNode();
		if (head == null) {
			val = Integer.MIN_VALUE;
		} else {
			if (head.getList() != null) {
				val = head.getList().popFront();
				elements--;
			} else {
				if (head == tail) {
					n = head;
					head = null;
					elements--;
					val = n.getVal();
				} else {
					n = head;
					head = head.getNext();
					head.setPrev(null);
					elements--;
					val = n.getVal();
				}
			}
		}
		return val;
	}

	/**Returns the front element of the list without removing it. If the first
	 * element is another list, returns that list’s first element Returns
	 * Integer.MIN_VALUE if empty*/
	public int peekFront() {
		int val = 0;
		if (head == null) {
			val = Integer.MIN_VALUE;
		} else {
			if (head.getList() != null) {
				val = head.getList().peekFront();
			} else {
				val = head.getVal();
			}
		}
		return val;
	}

	/**Removes and returns the element from the back of the linked list, or if
	 * the last element is another list, returns that list’s last element
	 * Returns Integer.MIN_VALUE if empty*/
	public int popBack() {
		int val = 0;
		DLNode n = new DLNode();
		if (tail == null) {
			return Integer.MIN_VALUE;
		} else {
			if (tail.getList() != null && tail.getVal() == Integer.MIN_VALUE) {
				return tail.getList().popBack();
			} else {
				if (head == tail) {
					n = tail;
					tail = null;
					head = null;
					elements--;
					val = n.getVal();
				} else {
					n = tail;
					val = n.getVal();
					tail = tail.getPrev();
					tail.setNext(null);
					elements--;
				}
			}
		}
		return val;
	}

	/**Returns the element at the back of the list without removing it. If the
	 * last element is another list, returns that list’s last element Returns
	 * Integer.MIN_VALUE if empty*/
	public int peekBack() {
		int val = 0;
		if (tail == null) {
			val = Integer.MIN_VALUE;
			head = null;
		} else {
			if (tail.getList() != null) {
				val = tail.getList().peekBack();
			} else {
				val = tail.getVal();
			}
		}
		return val;
	}

	/**Returns the number of elements in the double linked list and of all its
	 * sub-lists*/
	public int elements() {
		return elements;
	}

	/**Reverse the order of all elements. Does NOT change the order of
	 * sub-lists!*/
	public void reverse() {
		DoubleLinkedList help = new DoubleLinkedList();
		DLNode n = head;
		while (n != null) {
			if (n.getList() != null) {
				help.pushBackRecursive(n.getList());
				elements = elements - n.getList().elements();
				n.setList(null);
				n = head;
				if (head != tail) {
					head = head.getNext();
					head.setPrev(null);
				}
			} else {
				help.pushBack(this.popFront());
			}
			n = n.getNext();
		}
		this.clear();
		this.elements = 0;
		DLNode nhelp = help.head;
		while (nhelp != null) {
			if (nhelp.getList() != null) {
				this.pushFrontRecursive(nhelp.getList());
				help.elements = help.elements - nhelp.getList().elements();
				nhelp.setList(null);
				nhelp = help.head;
				help.head = help.head.getNext();
			} else {
				this.pushFront(help.popFront());
			}
			nhelp = nhelp.getNext();
		}
	}

	/**Deinitializes the object; think about it and comment what to do here.*/
	protected void finalize() {
		clear();
	}

	/**Adds all elements from another list at the front of this linked list.
	 */
	public void pushFront(DoubleLinkedList other) {
		DLNode n = other.tail;
		for (int i = 0; i < other.elements(); i++) {
			pushFront(n.getVal());
			n = n.getPrev();
		}
	}

	/**
	 * Adds all elements from another list at the back of this linked list.
	 */
	public void pushBack(DoubleLinkedList other) {
		DLNode n = other.head;
		for (int i = 0; i < other.elements(); i++) {
			pushBack(n.getVal());
			n = n.getNext();
		}
	}

	/**
	 * Clones this DoubleLinkedList instance and returns an exact COPY.
	 */
	public DoubleLinkedList clone() {
		DoubleLinkedList copied = new DoubleLinkedList();
		DLNode n = head;
		while (n != null) {
			if (n.getList() != null) {
				copied.pushBackRecursive(n.getList().clone());
			} else {
				copied.pushBack(n.getVal());
			}
			n = n.getNext();
		}
		return copied;
	}

	/**
	 * Returns true if the other list is equal to this one, false otherwise. The
	 * contents of the two lists must not be changed!
	 */
	public boolean equals(DoubleLinkedList other) {
		boolean isEqual = false;
		DLNode n = this.head;
		DLNode nother = other.head;

		while (n != null) {
			if (this.elements() == other.elements()) {
				isEqual = true;
			} else {
				return false;
			}
			if (n.getList() != null && nother.getList() != null) {
				if (n.getList().equals(nother.getList())) {
					isEqual = true;
				}
			}
			if (n.getVal() == nother.getVal()) {
				isEqual = true;
			} else {
				return false;
			}
			n = n.getNext();
			nother = nother.getNext();
		}
		return isEqual;
	}

	/**
	 * Returns a string representation of the list. See the exercise
	 * specification below for the exact string format!
	 */
	public String toString() {
		String outPut = new String();
		DLNode n = head;
		while (n != null) {
			if (n.getVal() == Integer.MIN_VALUE) {
				n = n.getNext();
			} else {
				outPut = outPut + n.getVal() + " ";
				n = n.getNext();
			}
		}
		return outPut;
	}

	/**
	 * Returns true if the element val exists in the list or in any of its
	 * sub-lists, false otherwise.
	 */
	public boolean search(int val) {
		DLNode n = head;
		while (n != null) {
			if (n.getList() != null) {
				if (n.getList().search(val)) {
					return true;
				}
			}
			if (n.getVal() == val) {
				return true;
			}
			n = n.getNext();
		}
		return false;
	}

	/** Adds another list to the first element of the linked list. */
	public void pushFrontRecursive(DoubleLinkedList list) {
		this.pushFront(Integer.MIN_VALUE);
		this.head.setList(list);
		elements = elements + list.elements();
	}

	/** Adds another list to the last element of the linked list. */
	public void pushBackRecursive(DoubleLinkedList list) {
		pushBack(Integer.MIN_VALUE);
		DLNode n = this.tail;
		n.setList(list);
		elements = elements + list.elements();
	}
	
	public static void main(String[] args) {
		DoubleLinkedList list = new DoubleLinkedList();
		list.pushFront(8);
		list.pushFront(3);
		list.pushFront(1);
		list.pushBack(10);

		DoubleLinkedList list1 = new DoubleLinkedList();
		list1.pushFront(8);
		list1.pushFront(20);
		list1.pushBack(5);
		list1.pushBack(19);

		System.out.println(list1.toString());
		list1.pushFrontRecursive(list1);
		System.out.println(list1.head.getList().toString());
		list1.pushBackRecursive(list1);
		list1.reverse();
		System.out.println(list1.toString());
		System.out.println(list1.tail.getList().toString());
		System.out.println(list1.tail.getPrev().getVal());

	}
}

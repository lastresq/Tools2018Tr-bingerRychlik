package Aufgabe2;
public class DoubleLinkedList {

	/**Pointer to the first and last element of the list*/
	protected DLNode head;
	protected DLNode tail;
	protected int elements = 0;

	/**Constructor initializes list with a standard size.*/
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
		DLNode node = new DLNode();
		if (head == null) {
			val = Integer.MIN_VALUE;
		} else {
			if (head.getList() != null) {
				val = head.getList().popFront();
				elements--;
			} else {
				if (head == tail) {
					node = head;
					head = null;
					elements--;
					val = node.getVal();
				} else {
					node = head;
					head = head.getNext();
					head.setPrev(null);
					elements--;
					val = node.getVal(); 
				}
			}
		}
		return val;
	}

	/**Returns the front element of the list without removing it. If the first
	 * element is another list, returns that list’s first element Returns
	 * Integer.MIN_VALUE if empty*/
	
	public int peekFront() {
		int value = 0;
		if (head == null) {
			value = Integer.MIN_VALUE;
		} else {
			if (head.getList() != null) {
				value = head.getList().peekFront();
			} else {
				value = head.getVal();
			}
		}
		return value;
	}

	/**Removes and returns the element from the back of the linked list, or if
	 * the last element is another list, returns that list’s last element
	 * Returns Integer.MIN_VALUE if empty*/
	
	public int popBack() {
		int val = 0;
		DLNode node = new DLNode();
		if (tail == null) {
			return Integer.MIN_VALUE;
		} else {
			if (tail.getList() != null && tail.getVal() == Integer.MIN_VALUE) {
				return tail.getList().popBack();
			} else {
				if (head == tail) {
					node = tail;
					tail = null;
					elements--;
					val = node.getVal();
				} else {
					node = tail;
					val = node.getVal();
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
	 * Integer.MIN_VALUE if empty */
	public int peekBack() {
		int value = 0;
		if (tail == null) {
			value = Integer.MIN_VALUE;
		} else {
			if (tail.getList() != null) {
				value = tail.getList().peekBack();
			} else {
				value = tail.getVal();
			}
		}
		return value;
	}

	/**Returns the number of elements in the double linked list and of all its
	 * sub-lists*/
	public int elements() {return elements;}

	/**Reverse the order of all elements. Does NOT change the order of
	 * sub-lists!*/
	public void reverse() {
		DoubleLinkedList help = new DoubleLinkedList();
		DLNode node = head;
		while (node != null) {
			if (node.getList() != null) {
				help.pushBackRecursive(node.getList());
				elements = elements - node.getList().elements();
				node.setList(null);
				node = head;
				if (head != tail) {
					head = head.getNext();
					head.setPrev(null);
				}
			} else {
				help.pushBack(this.popFront());
			}
			node = node.getNext(); 
		}
		this.clear();
		this.elements = 0;
		DLNode nodehelp = help.head;
		while (nodehelp != null) {
			if (nodehelp.getList() != null) {
				this.pushFrontRecursive(nodehelp.getList());
				help.elements = help.elements - nodehelp.getList().elements();
				nodehelp.setList(null);
				nodehelp = help.head;
				help.head = help.head.getNext();
			} else {
				this.pushFront(help.popFront());
			}
			nodehelp = nodehelp.getNext();
		}
	}

	/*** Deinitializes the object; think about it and comment what to do here.*/
	protected void finalize() {clear();}

	/*** Adds all elements from another list at the front of this linked list.*/
	public void pushFront(DoubleLinkedList other) {
		DLNode node = other.tail;
		for (int i = 0; i < other.elements(); i++) {
			pushFront(node.getVal());
			node = node.getPrev();
		}
	}

	/*** Adds all elements from another list at the back of this linked list.*/
	public void pushBack(DoubleLinkedList other) {
		DLNode node = other.head;
		for (int i = 0; i < other.elements(); i++) {
			pushBack(node.getVal());
			node = node.getNext();
		}
	}

	/** Clones this DoubleLinkedList instance and returns an exact COPY.*/
	public DoubleLinkedList clone() {
		DoubleLinkedList copied = new DoubleLinkedList();
		DLNode node = head;
		while (node != null) {
			if (node.getList() != null) {
				copied.pushBackRecursive(node.getList().clone());
			} else {
				copied.pushBack(node.getVal());
			}
			node = node.getNext();
		}
		return copied;
	}

	/**Returns true if the other list is equal to this one, false otherwise. The
	 * contents of the two lists must not be changed!*/
	
	public boolean equals(DoubleLinkedList other) {
		boolean isEqual = false;
		DLNode node = this.head;
		DLNode nodeother = other.head;

		while (node != null) {
			if (this.elements() == other.elements()) {
				isEqual = true;
			} else {
				return false;
			}
			if (node.getList() != null && nodeother.getList() != null) {
				if (node.getList().equals(nodeother.getList())) {
					isEqual = true;
				}
			}
			if (node.getVal() == nodeother.getVal()) {
				isEqual = true;
			} else {
				return false;
			}
			node = node.getNext();
			nodeother = nodeother.getNext();
		}
		return isEqual;
	}

	/**Returns a string representation of the list. See the exercise
	 * specification below for the exact string format!*/
	public String toString() {
		String out = new String();
		DLNode node = head;
		while (node != null) {
			if (node.getVal() == Integer.MIN_VALUE) {
				node = node.getNext();
			} else {
				out = out + node.getVal() + " ";
				node = node.getNext();
			}
		}
		return out;
	}

	/**Returns true if the element val exists in the list or in any of its
	 * sub-lists, false otherwise.*/
	public boolean search(int val) {
		DLNode node = head;
		while (node != null) {
			if (node.getList() != null) {
				if (node.getList().search(val)) {
					return true;
				}
			}
			if (node.getVal() == val) {
				return true;
			}
			node = node.getNext();
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
		DLNode node = this.tail;
		node.setList(list);
		elements = elements + list.elements();
	}
	
	public static void main(String[] args) {
		DoubleLinkedList list = new DoubleLinkedList();
		list.pushFront(8);
		list.pushFront(3);
		list.pushFront(1);
		list.pushBack(11);

		DoubleLinkedList list1 = new DoubleLinkedList();
		list1.pushFront(8);
		list1.pushFront(20);
		
		list1.pushBack(5);
		list1.pushBack(19);

		System.out.println(list1.toString());
		list1.pushFrontRecursive(list);
		System.out.println(list1.head.getList().toString());
		
		list1.pushBackRecursive(list);
		list1.reverse();
		
		System.out.println(list1.toString());
		System.out.println(list1.tail.getList().toString());
		System.out.println(list1.tail.getPrev().getVal());
 
	}
}

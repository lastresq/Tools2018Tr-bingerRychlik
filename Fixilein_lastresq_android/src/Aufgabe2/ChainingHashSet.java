package Aufgabe2;
public class ChainingHashSet {

	/** Array which stores overflow lists that are indexed by the hash code of
	 * their elements.*/
	protected RandomAccessDoubleLinkedList[] array;

	/**Initializes an empty hashtable with the given number of overflow lists. */
	public ChainingHashSet(int indexSize) {
		if (indexSize <= 0) {
			throw new IllegalArgumentException("Hashtable größe muss >= 0 sein!");
		}
		array = new RandomAccessDoubleLinkedList[indexSize];
		for (int i = 0; i < array.length; i++) {
			array[i] = new RandomAccessDoubleLinkedList();
		}
	}

	/**Inserts a new element val into the hashtable (hashcode = val %
	 * array.length), if it did not exist in the table before. Returns true if a
	 * new element was inserted, false if it already existed.*/
	public boolean insert(int val) {
		if (val < 0) {
			throw new IllegalArgumentException("Wert muss >= 0 sein!");
		}
		int h = val % array.length;
		if (array[h].contains(val)) {
			return false;
		} else {
			array[h].pushFront(val);
			return true;
		}
	}

	/**Returns true if the given value is already stored in the hashtable, false
	 * otherwise.*/
	public boolean contains(int val) {
		boolean Found = false;
		for (int i = 0; i < array.length; i++) {
			if (array[i] != null) {
				Found = array[i].contains(val);
				if (Found) {
					return true;
				}
			}
		}
		return Found;
	}

	/**Removes the given element from the hashtable if it exists. Returns true
	 * if the element was removed, false if no such element existed. */
	public boolean remove(int val) {
		if (array[val % array.length] != null) { 
			
			DLNode node = array[val % array.length].head; 
			while (node != null && node.getVal() != val) { 
				node = node.getNext();
			}
			if (node == null) { 
				return false;
			} else if (node.getVal() == val){
				DLNode prev = node.getPrev();
				DLNode nxt = node.getNext();

				if (prev == null) {
					array[val % array.length].head = nxt;
				} else {
					prev.setNext(nxt);
				}

				if (nxt == null) {
					array[val % array.length].tail = prev;
				} else {
					nxt.setPrev(prev);
				}
				array[val % array.length].elements--;
				return true;
			}
		}
		return false;
	}

	/**Counts the amount of values, which are stored in the same overflow list*/
	public int getOverflowCount(int hashVal) {
		if (hashVal < 0) {
			throw new IllegalArgumentException("Index muss >= 0 sein!");
		}
		if (array[hashVal] == null) {
			return 0;
		} else {
			return array[hashVal].elements();
		}
	}

	/** Counts the amount of elements in the hashtable by adding the amount of
	 * elements in each overflow list.*/
	public int elements() {
		int elem = 0;
		for (int i = 0; i < array.length; i++) {
			elem += getOverflowCount(i);
		}
		return elem;
	}

	public static void main(String[] args) {
		ChainingHashSet hash = new ChainingHashSet(20);

		System.out.println(hash.insert(18));
		System.out.println(hash.insert(31)); 
		
		System.out.println(hash.insert(13));
		System.out.println(hash.insert(8));
		
		System.out.println(hash.insert(3));
		System.out.println(hash.insert(10));
		
		System.out.println(hash.insert(38));
		System.out.println(hash.insert(58));

		System.out.println("");
		System.out.println(hash.getOverflowCount(0));
		System.out.println(hash.remove(3));
		
		System.out.println(hash.elements());
		System.out.println(hash.remove(58));
		
		System.out.println(hash.elements());
		System.out.println(hash.remove(31));
		
		System.out.println(hash.elements());
		System.out.println(hash.remove(4));
		
		System.out.println(hash.elements());

	}
	
}

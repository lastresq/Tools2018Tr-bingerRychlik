package Aufgabe1;
public class ListStack extends DoubleLinkedList{


	/** Initializes an empty stack. */ 
	public ListStack() { super();} 
	
	/** Copy constructor which initializes the stack with another stack.
	This constructor must COPY all elements of the other stack. */ 
	public ListStack(ListStack other) { super(other);} 
	
	/** Pushes an element onto the stack. */ 
	public void push(int val) { super.pushBack(val);} 
	
	/** Returns the top element of the stack and removes it. */ 
	public int pop() {return super.popBack();} 
	
	/** Returns the top element of the stack without removing it. */ 
	public int peek() {return super.peekBack();}
	
	public static void main(String[] args) {
		ListStack lists = new ListStack();
		lists.push(8);
		lists.push(13);
		lists.push(2);
		System.out.println(lists.elements());
		System.out.println(lists.peek());
		System.out.println(lists.popBack());
		System.out.println(lists.popBack());
	}

	
}

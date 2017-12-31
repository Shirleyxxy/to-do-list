/**
 * Implementation of DoublyLinkedList data structure.
 * 
 * @author Ruicong Sun, Shirley Xu
 * @param <T>
 */

public class DoublyLinkedList<T> implements LinkedList<T> {

	// the number of nodes in a linked list
	protected int count;
	// the head node
	protected DoublyLinkedListNode<T> head;
	// the tail node
	protected DoublyLinkedListNode<T> tail;

	/**
	 * Constructs an empty doubly linked list.
	 */
	public DoublyLinkedList() {
		head = null;
		tail = null;
		count = 0;
	}

	/**
	 * Checks if the linked list is empty.
	 * 
	 * @return true if the linked list is empty; false otherwise.
	 */
	public boolean isEmpty() {
		return head == null;
	}

	/**
	 * Gets the size of the linked list.
	 * 
	 * @return size of the linked list
	 */
	public int size() {
		return count;
	}

	/**
	 * Gets the value stored in the head node.
	 * 
	 * @return the value in the head node
	 */
	public T getFirst() {
		return head.getValue();
	}

	/**
	 * Gets the value stored in the tail node.
	 * 
	 * @return the value in the tail node
	 */
	public T getLast() {
		return tail.getValue();
	}

	/**
	 * Adds a node at the end of the doubly linked list.
	 * 
	 * @param element
	 */
	public void add(T element) {
		// Create a new node with the specified value
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(element);
		// if the linked list is empty
		if (isEmpty()) {
			head = newNode;
			tail = head;
			// if the linked list is not empty
		} else {
			newNode.setPrevious(tail);
			tail.setNext(newNode);
			tail = newNode;
		}
		count++;
	}

	/**
	 * Returns the node at the specified position in the linked list.
	 * 
	 * @param index
	 * @return the node at the specified position in the linked list
	 */
	public DoublyLinkedListNode<T> getNode(int index) {
		DoublyLinkedListNode<T> currentNode = head;
		while (index > 0) {
			currentNode = currentNode.getNext();
			index--;
		}
		return currentNode;
	}

	/**
	 * Returns the value stored at the specified position in the linked list.
	 * 
	 * @param index
	 * @return the value stored at the specified position in the linked list
	 */
	public T get(int index) {
		return getNode(index).getValue();
	}

	/**
	 * Adds a node with the specified value at the specified position in the linked
	 * list.
	 * 
	 * @param index
	 * @param element
	 */
	public void add(int index, T element) {
		// Create a new node with the specified value
		DoublyLinkedListNode<T> newNode = new DoublyLinkedListNode<T>(element);
		// if the new node needs to be inserted at the beginning of the list
		if (index == 0) {
			// if the list is empty
			if (head == null) {
				head = newNode;
				tail = head;
				// if the list is not empty
			} else {
				head.setPrevious(newNode);
				newNode.setNext(head);
				head = newNode;
			}
			// if the new node needs to be inserted at the end of the list
		} else if (index == size()) {
			// Call the add function defined above
			add(element);
			// Subtract 1 from count since 1 has been added to the count in the previous add
			// function
			count--;
			// if the new node needs to be inserted in the middle of the list
		} else {
			DoublyLinkedListNode<T> prevNode = getNode(index - 1);
			DoublyLinkedListNode<T> nextNode = prevNode.getNext();
			prevNode.setNext(newNode);
			newNode.setPrevious(prevNode);
			newNode.setNext(nextNode);
			nextNode.setPrevious(newNode);
		}
		count++;
	}

	/**
	 * Removes a node at the specified position in the linked list.
	 * 
	 * @param index
	 * @return the value stored in the removed node
	 */
	public T remove(int index) {
		DoublyLinkedListNode<T> removedNode = null;
		// if the node that needs to be removed is the head node
		if (index == 0) {
			removedNode = head;
			head = removedNode.getNext();
			// if the node that needs to be removed is the tail node
		} else if (index == size() - 1) {
			removedNode = tail;
			tail = removedNode.getPrevious();
			tail.setNext(null);
			// if the node that needs to be removed is in the middle of the list
		} else {
			DoublyLinkedListNode<T> prevNode = getNode(index - 1);
			removedNode = prevNode.getNext();
			DoublyLinkedListNode<T> nextNode = removedNode.getNext();
			prevNode.setNext(nextNode);
			nextNode.setPrevious(prevNode);
		}
		count--;
		return removedNode.getValue();
	}

	/**
	 * Clears the linked list.
	 */
	public void clear() {
		head = null;
		count = 0;
	}

	/**
	 * Returns a String representation of the doubly linked list.
	 * 
	 * @return String representation of the linked list
	 */
	public String toString() {
		String result = "";
		if (!isEmpty()) {
			DoublyLinkedListNode<T> currentNode = head;
			while (currentNode.getNext() != null) {
				result = result + currentNode.getValue().toString() + "\n";
				currentNode = currentNode.getNext();
			}
			return result + getLast().toString();
		} else {
			return result;
		}
	}

	/**
	 * Inserts a node with the given element at the head of the linked list.
	 * 
	 * @param element
	 */
	public void insertFirst(T element) {
		add(0, element);
	}

	/**
	 * Returns the index of the node that contains the given element. Returns -1 if
	 * the given element is not stored in the linked list.
	 * 
	 * @param element
	 * @return index
	 */
	public int find(T element) {
		for (int i = 0; i < count; i++) {
			if (get(i) == element) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Concatenates two DoublyLinkedLists.
	 * 
	 * @param another
	 * @return a DoublyLinkedList after concatenation
	 */
	public DoublyLinkedList<T> append(DoublyLinkedList<T> another) {
		if (this.isEmpty()) {
			return another;
		} else {
			if (another.isEmpty()) {
				return this;
			} else {
				this.tail.next = another.head;
				another.head.previous = this.tail;
				this.tail = another.tail;
				this.count += another.count;
				return this;
			}
		}
	}
}

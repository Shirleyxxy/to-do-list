/**
 * Implementation of LinkedListNode for use in DoublyLinkedList.
 * 
 * @author Ruicong Sun, Shirley Xu
 * @param <T>
 */
public class DoublyLinkedListNode<T> implements LinkedListNode<T> {

	protected T value;
	protected DoublyLinkedListNode<T> previous;
	protected DoublyLinkedListNode<T> next;

	/**
	 * Constructs a DoublyLinkedListNode with the specified value.
	 * 
	 * @param value
	 */
	public DoublyLinkedListNode(T value) {
		this.value = value;
	}

	/**
	 * Gets the value stored at this node.
	 * 
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

	/**
	 * Sets the value stored at this node.
	 * 
	 * @param value
	 */
	public void setValue(T value) {
		this.value = value;
	}

	/**
	 * Gets the previous node.
	 * 
	 * @return the previous node
	 */
	public DoublyLinkedListNode<T> getPrevious() {
		return previous;
	}

	/**
	 * Gets the next node.
	 * 
	 * @return the next node
	 */
	public DoublyLinkedListNode<T> getNext() {
		return next;
	}

	/**
	 * Sets the previous node.
	 * 
	 * @param node
	 */
	public void setPrevious(DoublyLinkedListNode<T> node) {
		previous = node;
	}

	/**
	 * Sets the next node.
	 * 
	 * @param node
	 */
	public void setNext(DoublyLinkedListNode<T> node) {
		next = node;
	}
}

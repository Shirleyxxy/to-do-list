/**
 * Implements the data structure for a To-do List. The back-end of a To-do List
 * is two DoublyLinkedLists that store different tasks.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class ToDoListModel {

	private DoublyLinkedList<Task> toDoList;
	private DoublyLinkedList<Task> doneList;

	/**
	 * Constructs a To-do List model that consists of an empty To-do list and an
	 * empty doneList.
	 */
	public ToDoListModel() {
		toDoList = new DoublyLinkedList<Task>();
		doneList = new DoublyLinkedList<Task>();
	}

	/**
	 * Gets the to-do list.
	 * 
	 * @return toDoList
	 */
	public DoublyLinkedList<Task> getToDoList() {
		return toDoList;
	}

	/**
	 * Gets the list for completed tasks.
	 * 
	 * @return doneList
	 */
	public DoublyLinkedList<Task> getDoneList() {
		return doneList;
	}

	/**
	 * Gets the String representation of a list of to-do tasks.
	 * 
	 * @return String representation of toDoList
	 */
	public String getToDoListString() {
		return toDoList.toString();
	}

	/**
	 * Gets the String representation of a list of completed tasks.
	 * 
	 * @return String representation of doneList
	 */
	public String getDoneListString() {
		return doneList.toString();
	}

	/**
	 * Adds a new task into a To-do List.
	 * 
	 * @param content
	 * @param inputPriority
	 */
	public void addTask(String content, int inputPriority) {
		// creates a task with the given content and priority level
		Task newTask = new Task(content, inputPriority);
		// adds the task at the end of the to-do list
		toDoList.add(newTask);
	}

	/**
	 * Deletes a task from a To-do List.
	 * 
	 * @param task
	 */
	public void deleteTask(String content) {
		int index = 0;
		DoublyLinkedListNode<Task> current;
		for (current = this.toDoList.head; current != null; current = current.next) {
			if (current.getValue().getTitle().equals(content)) {
				this.toDoList.remove(index);
				break;
			}
			index++;
		}
	}

	/**
	 * Edits a task in a To-do List.
	 * 
	 * @param task
	 * @param newContent
	 * @param newInputPriority
	 */
	public void editTask(String taskTitle, String newContent, int newInputPriority) {
		// checks the task to edit is in the list
		DoublyLinkedListNode<Task> current;
		for (current = this.toDoList.head; current != null; current = current.next) {
			if (current.getValue().getTitle().equals(taskTitle)) {
				current.getValue().setTitle(newContent);
				current.getValue().setPriority(newInputPriority);
			}
		}
	}

	/**
	 * Marks a given task as complete.
	 * 
	 * @param task
	 */
	public void finishTask(String taskTitle) {
		// checks the task to mark as done is in the list
		int index = 0;
		DoublyLinkedListNode<Task> current;
		for (current = this.toDoList.head; current != null; current = current.next) {
			if (current.getValue().getTitle().equals(taskTitle)) {
				current.getValue().markAsDone();
				toDoList.remove(index);
				doneList.add(current.getValue());
			}
			index++;
		}
	}

	/**
	 * Returns a subset of tasks that meet certain criteria.
	 * 
	 * @param selectLow
	 * @param selectMedium
	 * @param selectHigh
	 * @param selectDone
	 * @return newList
	 */
	public DoublyLinkedList<Task> selectTasks(boolean selectLow, boolean selectMedium, boolean selectHigh,
			boolean selectDone) {
		// creates a new list to store tasks that meet certain criteria
		DoublyLinkedList<Task> newList = new DoublyLinkedList<Task>();

		if (selectLow) {
			for (int i = 0; i < toDoList.size(); i++) {
				if (toDoList.get(i).getPriority() == 1) {
					newList.add(toDoList.get(i));
				}
			}
		}

		if (selectMedium) {
			for (int i = 0; i < toDoList.size(); i++) {
				if (toDoList.get(i).getPriority() == 2) {
					newList.add(toDoList.get(i));
				}
			}
		}

		if (selectHigh) {
			for (int i = 0; i < toDoList.size(); i++) {
				if (toDoList.get(i).getPriority() == 3) {
					newList.add(toDoList.get(i));
				}
			}
		}

		if (selectDone) {
			// checks every task in the Done List
			for (int j = 0; j < doneList.size(); j++) {
				newList.add(doneList.get(j));
			}
		}
		return newList;
	}

	/**
	 * Returns the task with the given title in the to-do list.
	 * 
	 * @param taskTitle
	 * @return task
	 */
	public Task findTask(String taskTitle) {
		for (int i = 0; i < toDoList.size(); i++) {
			if (toDoList.get(i).getTitle().equals(taskTitle)) {
				return toDoList.get(i);
			}
		}
		return null;
	}
}

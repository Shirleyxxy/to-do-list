/**
 * Implements a task with specific content, priority level, and status.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class Task implements Comparable {

	/** The title is the content of a task. */
	private String title;

	/** Priority has 3 levels: 1(low), 2(medium), 3(high). */
	private int priority;

	/** The default status is toDo. */
	private boolean toDo = true;

	/**
	 * Constructs a new task with the given content and priority level.
	 * 
	 * @param content
	 * @param inputPriority
	 */
	public Task(String content, int inputPriority) {
		title = content;
		priority = inputPriority;
	}

	/**
	 * Gets the content of a task.
	 * 
	 * @return content of a task
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Gets the priority level of a task.
	 * 
	 * @return priority level
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * Sets the content of a task.
	 * 
	 * @param newTitle
	 */
	public void setTitle(String newContent) {
		title = newContent;
	}

	/**
	 * Sets the priority level of a task.
	 * 
	 * @param newPriority
	 */
	public void setPriority(int newPriority) {
		priority = newPriority;
	}

	/**
	 * Checks if a task is done.
	 * 
	 * @return true if the task is done, false otherwise.
	 */
	public boolean checkIfDone() {
		return (toDo == false);
	}

	/**
	 * Marks a task as done.
	 * 
	 */
	public void markAsDone() {
		toDo = false;
	}

	/**
	 * Compares the priority level of a task with the priority level of another
	 * task.
	 * 
	 * @param o
	 * @return 1 if the current task has a higher priority; 0 if the two tasks are
	 *         of the same priority; -1 if the current task has a lower priority.
	 */
	public int compareTo(Object o) {
		Task anotherTask = (Task) o;
		if (this.getPriority() > anotherTask.getPriority()) {
			return 1;
		} else if (this.getPriority() == anotherTask.getPriority()) {
			return 0;
		} else {
			return -1;
		}
	}

	/**
	 * Displays a task as a String.
	 * 
	 * @return String representation of a task
	 */
	public String toString() {
		return getTitle();
	}
}
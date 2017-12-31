import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Tests the functionality of ToDoListModel.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class ToDoListModelTest {

	ToDoListModel tester = new ToDoListModel();

	@Test
	public void constructor() {
		assertNotNull(tester.getToDoListString());
		assertNotNull(tester.getDoneListString());
		assertNotNull(tester.getToDoList());
		assertNotNull(tester.getDoneList());
	}

	@Test
	/**
	 * test addTask, also test toString methods
	 */
	public void addTaskTest() {
		tester.addTask("HW7", 3);
		assertEquals("HW7", tester.getToDoListString());
		tester.addTask("B-day", 2);
		assertEquals("HW7\nB-day", tester.getToDoListString());
		assertEquals("", tester.getDoneListString());
	}

	@Test
	public void deleteTaskTest() {
		Task task1 = new Task("HW7", 3);
		Task task2 = new Task("B-day", 2);
		Task task3 = new Task("Concert", 1);
		// wouldn't find such a task since the list is currently empty, so do nothing
		tester.deleteTask("HW7");
		tester.addTask(task1.getTitle(), task1.getPriority());
		tester.deleteTask("HW7");
		assertEquals("", tester.getToDoListString());
		tester.addTask(task2.getTitle(), task2.getPriority());
		tester.addTask(task3.getTitle(), task3.getPriority());
		tester.deleteTask("B-day");
		assertEquals("Concert", tester.getToDoListString());

		// delete non-existent key
		tester.deleteTask("B-day");
		assertEquals("Concert", tester.getToDoListString());
		tester.deleteTask("Concert");
		assertEquals("", tester.getToDoListString());
	}

	@Test
	public void editTaskTest() {
		tester.editTask("HW7", "Final Proj", 3);
		assertEquals("", tester.getToDoListString());
		assertEquals("", tester.getDoneListString());
		tester.addTask("HW7", 3);
		tester.editTask("HW7", "Final Proj", 2);
		assertEquals("Final Proj", tester.getToDoListString());
		tester.addTask("B-day", 1);
		tester.addTask("Concert", 3);
		tester.editTask("B-day", "B-day cake", 2);
		tester.editTask("Concert", "Violin", 1);
		assertEquals("Final Proj\nB-day cake\nViolin", tester.getToDoListString());
	}

	@Test
	public void finishTaskTest() {
		tester.finishTask("HW7");
		assertEquals("", tester.getToDoListString());
		assertEquals("", tester.getDoneListString());
		tester.addTask("HW7", 3);
		tester.finishTask("HW7");
		assertEquals("", tester.getToDoListString());
		assertEquals("HW7", tester.getDoneListString());
		assertTrue(tester.getDoneList().head.getValue().checkIfDone());
		tester.addTask("B-day", 1);
		tester.addTask("Concert", 3);
		tester.finishTask("B-day");
		assertEquals("Concert", tester.getToDoListString());
		assertEquals("HW7\nB-day", tester.getDoneListString());
		tester.finishTask("Concert");
		assertEquals("", tester.getToDoListString());
		assertEquals("HW7\nB-day\nConcert", tester.getDoneListString());
	}

	@Test
	public void selectTasksTest() {
		assertEquals("", tester.selectTasks(true, true, true, true).toString());
		tester.addTask("Bus Ticket", 1);
		tester.addTask("Presentation", 2);
		tester.addTask("Final Write-up", 3);
		tester.addTask("Exam", 2);
		tester.addTask("Clean-up", 1);
		assertEquals("Bus Ticket\nClean-up", tester.selectTasks(true, false, false, false).toString());
		assertEquals("Presentation\nExam", tester.selectTasks(false, true, false, false).toString());
		assertEquals("Bus Ticket\nClean-up\nFinal Write-up", tester.selectTasks(true, false, true, false).toString());

		tester.finishTask("Bus Ticket");
		tester.finishTask("Exam");
		assertEquals("Bus Ticket\nExam", tester.selectTasks(false, false, false, true).toString());
	}

	@Test
	public void findTaskTest() {
		assertNull(tester.findTask("HW7"));
		tester.addTask("HW7", 3);
		tester.addTask("Final Proj", 1);
		assertEquals("HW7", tester.findTask("HW7").getTitle());
		assertEquals("Final Proj", tester.findTask("Final Proj").getTitle());
	}
}

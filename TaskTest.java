import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the functionality of Task.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class TaskTest {

	private Task task1;
	private Task task2;
	private Task task3;

	@Before
	public void setUp() {
		task1 = new Task("CS Assignment7", 3);
		task2 = new Task("Thanksgiving shopping", 2);
		task3 = new Task("Dance club meeting", 1);
	}

	@Test
	public void constructorTest() {
		assertNotNull(task1);
		assertNotNull(task2);
		assertNotNull(task3);
	}

	@Test
	public void getTitleTest() {
		assertEquals("CS Assignment7", task1.getTitle());
		assertEquals("Thanksgiving shopping", task2.getTitle());
		assertEquals("Dance club meeting", task3.getTitle());
	}

	@Test
	public void getPriorityTest() {
		assertEquals(3, task1.getPriority());
		assertEquals(2, task2.getPriority());
		assertEquals(1, task3.getPriority());
	}

	@Test
	public void setTitleTest() {
		task1.setTitle("CS Final Project");
		assertEquals("CS Final Project", task1.getTitle());

		task2.setTitle("Thanksgiving dinner prep");
		assertEquals("Thanksgiving dinner prep", task2.getTitle());

		task3.setTitle("Dance rehearsal");
		assertEquals("Dance rehearsal", task3.getTitle());
	}

	@Test
	public void setPriorityTest() {
		task1.setPriority(2);
		assertEquals(2, task1.getPriority());

		task2.setPriority(1);
		assertEquals(1, task2.getPriority());

		task3.setPriority(3);
		assertEquals(3, task3.getPriority());
	}

	@Test
	public void checkIfDoneTest() {
		assertFalse(task1.checkIfDone());
		assertFalse(task2.checkIfDone());
		assertFalse(task3.checkIfDone());

		task1.markAsDone();
		assertTrue(task1.checkIfDone());
	}

	@Test
	public void markAsDoneTest() {
		assertFalse(task1.checkIfDone());
		task1.markAsDone();
		assertTrue(task1.checkIfDone());

		assertFalse(task2.checkIfDone());
		task2.markAsDone();
		assertTrue(task2.checkIfDone());

		assertFalse(task3.checkIfDone());
		task3.markAsDone();
		assertTrue(task3.checkIfDone());
	}

	@Test
	public void compareToTest() {
		assertEquals(1, task1.compareTo(task2));
		assertEquals(1, task2.compareTo(task3));
		assertEquals(-1, task3.compareTo(task1));

		task2.setPriority(3);
		assertEquals(0, task2.compareTo(task1));
	}

	@Test
	public void toStringTest() {
		assertEquals("CS Assignment7", task1.toString());
		assertEquals("Thanksgiving shopping", task2.toString());
		assertEquals("Dance club meeting", task3.toString());
	}

}

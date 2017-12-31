import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

/**
 * User Interface for the To-Do List Application.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class ToDoListGUI {

	private JFrame frame;
	private ToDoListModel toDoModel = new ToDoListModel();
	// for displaying a list of tasks in different colors
	private JTextPane textPane = new JTextPane();
	StyledDocument doc = textPane.getStyledDocument();
	private StyleContext sc = new StyleContext();

	// set different display colors for different types of tasks
	final AttributeSet attrHigh = sc.addAttribute(sc.getEmptySet(), StyleConstants.Foreground, Color.RED);
	final AttributeSet attrMedium = sc.addAttribute(sc.getEmptySet(), StyleConstants.Foreground, Color.MAGENTA);
	final AttributeSet attrLow = sc.addAttribute(sc.getEmptySet(), StyleConstants.Foreground, Color.BLUE);
	final AttributeSet attrDone = sc.addAttribute(sc.getEmptySet(), StyleConstants.Foreground, Color.GRAY);

	final int MAXIMUM_NUM_TASKS = 20;
	private JComboBox<Task> taskList;
	// for selected tasks
	private JButton btnDisplay;
	private JButton addButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ToDoListGUI window = new ToDoListGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ToDoListGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 750);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		textPane.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPane.setEditable(false);
		textPane.setBackground(Color.WHITE);

		textPane.setBounds(23, 101, 365, 561);
		frame.getContentPane().add(textPane);
		defaultDisplay();

		JLabel lblNewLabel = new JLabel("Your To-Do List");
		lblNewLabel.setFont(new Font("Bradley Hand", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(54, 31, 302, 42);
		frame.getContentPane().add(lblNewLabel);

		taskList = new JComboBox<Task>();
		taskList.setBounds(445, 175, 314, 49);
		frame.getContentPane().add(taskList);

		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(400, 260, 117, 29);
		frame.getContentPane().add(btnDelete);

		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toDoModel.deleteTask(taskList.getSelectedItem().toString());
					update();
					// in case btnDelete is pressed when the to-do list is empty
				} catch (NullPointerException npe) {
					return;
				}
			}
		});

		JButton btnMarkAsDone = new JButton("Mark as Done");
		btnMarkAsDone.setBounds(536, 260, 117, 29);
		frame.getContentPane().add(btnMarkAsDone);

		btnMarkAsDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					toDoModel.finishTask(taskList.getSelectedItem().toString());
					update();
					// in case btnMarkAsDone is pressed when the to-do list is empty
				} catch (NullPointerException npe) {
					return;
				}
			}
		});

		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(677, 260, 117, 29);
		frame.getContentPane().add(btnEdit);

		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Task selected = toDoModel.findTask(taskList.getSelectedItem().toString());
					EditTaskDialog dialog = new EditTaskDialog(frame, "Edit the Task", selected.getTitle(),
							selected.getPriority());
					if (dialog.getTaskTitle().equals("")) {
						return;
					} else {
						selected.setTitle(dialog.getTaskTitle());
						selected.setPriority(dialog.getTaskPriority());
						update();
					}
					// in case btnEdit is pressed when the to-do list is empty
				} catch (NullPointerException npe) {
					return;
				}
			}
		});

		addButton = new JButton("Add a Task");
		addButton.setBounds(466, 448, 117, 29);
		frame.getContentPane().add(addButton);

		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddTaskDialog dialog = new AddTaskDialog(frame, "Add a New Task");
				Task newTask = new Task(dialog.getTaskTitle(), dialog.getTaskPriority());
				if (newTask.getTitle().equals("")) {
					return;
				} else {
					toDoModel.addTask(newTask.getTitle(), newTask.getPriority());
					update();
				}
			}
		});

		btnDisplay = new JButton("Display");
		btnDisplay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DoublyLinkedList<Task> showList = new DoublyLinkedList<Task>();
				DisplayDialog dd = new DisplayDialog(frame, "Display");
				Stack<Integer> temp = dd.getBtnHolder();
				int size = temp.size();
				for (int j = 0; j < size; j++) {
					if (temp.peek() == 0) {
						showList = showList.append(toDoModel.selectTasks(false, false, false, true));
					} else if (temp.peek() == 1) {
						showList = showList.append(toDoModel.selectTasks(true, false, false, false));
					} else if (temp.peek() == 2) {
						showList = showList.append(toDoModel.selectTasks(false, true, false, false));
					} else {
						showList = showList.append(toDoModel.selectTasks(false, false, true, false));
					}
					temp.pop();
				}
				// clear the original document
				try {
					doc.remove(0, doc.getLength());
				} catch (BadLocationException e1) {
					e1.printStackTrace();
				}

				// loop through the showList to display selected tasks
				for (int i = 0; i < showList.size(); i++) {
					// to-do tasks
					if (!showList.get(i).checkIfDone()) {
						if (showList.get(i).getPriority() == 3) {
							try {
								doc.insertString(doc.getLength(), showList.get(i).toString() + "\n", attrHigh);
							} catch (BadLocationException e1) {
								e1.printStackTrace();
							}

						} else if (showList.get(i).getPriority() == 2) {

							try {
								doc.insertString(doc.getLength(), showList.get(i).toString() + "\n", attrMedium);
							} catch (BadLocationException e2) {
								e2.printStackTrace();
							}

						} else {
							try {
								doc.insertString(doc.getLength(), showList.get(i).toString() + "\n", attrLow);
							} catch (BadLocationException e3) {
								e3.printStackTrace();
							}
						}
						// completed tasks
					} else {
						try {
							doc.insertString(doc.getLength(), showList.get(i).toString() + "\n", attrDone);
						} catch (BadLocationException e4) {
							e4.printStackTrace();
						}
					}
				}
			}

		});
		btnDisplay.setBounds(647, 448, 117, 29);
		frame.getContentPane().add(btnDisplay);
	}

	/**
	 * Updates the view of the text pane and the drop-down list for the JComboBox
	 * when the to-do list is modified.
	 */
	private void update() {
		// update the text pane
		defaultDisplay();
		// update the list for comboBox
		taskList.setModel(updateComboBoxModel());
		// disable addButton if the to-do list is full
		if (toDoModel.getToDoList().size() == MAXIMUM_NUM_TASKS) {
			addButton.setEnabled(false);
		} else {
			addButton.setEnabled(true);
		}
	}

	/**
	 * Constructs a model for the JComboBox.
	 * 
	 * @return ComboBoxModel
	 */
	private DefaultComboBoxModel updateComboBoxModel() {

		String[] tasks = new String[MAXIMUM_NUM_TASKS];

		for (int i = 0; i < toDoModel.getToDoList().size(); i++) {
			tasks[i] = toDoModel.getToDoList().get(i).toString();
		}
		DefaultComboBoxModel newModel = new DefaultComboBoxModel(tasks);
		return newModel;
	}

	/**
	 * Displays all the tasks in the to-do list by default.
	 */
	private void defaultDisplay() {
		// clear the original document
		try {
			doc.remove(0, doc.getLength());
		} catch (BadLocationException e1) {
			e1.printStackTrace();
		}
		// loop through the to-do list
		for (int i = 0; i < toDoModel.getToDoList().size(); i++) {
			if (toDoModel.getToDoList().get(i).getPriority() == 3) {
				try {
					doc.insertString(doc.getLength(), toDoModel.getToDoList().get(i).toString() + "\n", attrHigh);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}

			} else if (toDoModel.getToDoList().get(i).getPriority() == 2) {

				try {
					doc.insertString(doc.getLength(), toDoModel.getToDoList().get(i).toString() + "\n", attrMedium);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}

			} else {
				try {
					doc.insertString(doc.getLength(), toDoModel.getToDoList().get(i).toString() + "\n", attrLow);
				} catch (BadLocationException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

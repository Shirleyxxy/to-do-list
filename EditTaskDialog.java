import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;

/**
 * Dialog window for editing a selected task.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class EditTaskDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	final String highCommand = "high";
	final String midCommand = "medium";
	final String lowCommand = "low";

	private String taskTitle = "";
	private int taskPriority = 3;

	private ButtonGroup group = new ButtonGroup();
	private JTextField titleTextField;

	/**
	 * Create the dialog.
	 */
	public EditTaskDialog(Frame owner, String title, String defaultTitle, int defaultPriority) {
		super(owner, title, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel titlePanel = new JPanel();
			titlePanel.setLayout(new FlowLayout());
			{
				JLabel titleLabel = new JLabel("Task Content");
				// sets default task title before editing
				titleTextField = new JTextField(defaultTitle);
				titleTextField.setColumns(10);
				titlePanel.add(titleLabel);
				titlePanel.add(titleTextField);
			}
			contentPanel.add(titlePanel);

			JPanel priorityPanel = new JPanel();
			priorityPanel.setLayout(new FlowLayout());
			{
				JLabel priorityLabel = new JLabel("Task Priority");
				priorityPanel.add(priorityLabel);

				JRadioButton highButton = new JRadioButton("High");
				highButton.setActionCommand(highCommand);
				JRadioButton midButton = new JRadioButton("Medium");
				midButton.setActionCommand(midCommand);
				JRadioButton lowButton = new JRadioButton("Low");
				lowButton.setActionCommand(lowCommand);
				group.add(highButton);
				group.add(midButton);
				group.add(lowButton);

				// sets default priority level
				if (defaultPriority == 3) {
					highButton.setSelected(true);
				} else if (defaultPriority == 2) {
					midButton.setSelected(true);
				} else {
					lowButton.setSelected(true);
				}

				JPanel radioButtons = new JPanel();
				radioButtons.setLayout(new GridLayout(3, 1));
				radioButtons.add(highButton);
				radioButtons.add(midButton);
				radioButtons.add(lowButton);
				priorityPanel.add(radioButtons);

			}
			contentPanel.add(priorityPanel);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton doneButton = new JButton("Done");
				doneButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// deal with empty input
						if (titleTextField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Please provide the content of your task!");
						} else {
							done();
						}
					}
				});

				buttonPane.add(doneButton);
				getRootPane().setDefaultButton(doneButton);

			}
			{
				JButton cancelButton = new JButton("Cancel");

				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancel();
					}
				});

				buttonPane.add(cancelButton);
			}
		}

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/**
	 * Sets the dialog window invisible when the user clicks cancel.
	 */
	private void cancel() {
		taskTitle = "";
		taskPriority = 3;
		setVisible(false);
	}

	/**
	 * Updates title and priority level of the task when the user clicks done.
	 */
	private void done() {
		taskTitle = titleTextField.getText();
		String taskPriorityCommand = group.getSelection().getActionCommand();
		if (taskPriorityCommand.equals(highCommand)) {
			taskPriority = 3;
		} else if (taskPriorityCommand.equals(midCommand)) {
			taskPriority = 2;
		} else {
			taskPriority = 1;
		}
		setVisible(false);
	}

	/**
	 * Gets the title of the task.
	 * 
	 * @return taskTitle
	 */
	public String getTaskTitle() {
		return taskTitle;
	}

	/**
	 * Gets the priority level of the task.
	 * 
	 * @return taskPriority
	 */
	public int getTaskPriority() {
		return taskPriority;
	}
}

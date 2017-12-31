import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JCheckBox;

/**
 * Dialog window for displaying a subset of tasks selected by users.
 * 
 * @author Ruicong Sun, Shirley Xu
 */
public class DisplayDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	// keeps track of the selections for display
	private Stack<Integer> btnHolder = new Stack<Integer>();
	private JCheckBox[] box = new JCheckBox[4];

	/**
	 * Create the dialog.
	 */
	public DisplayDialog(Frame owner, String title) {
		super(owner, title, true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JCheckBox chckbxDisplayMediumPriority = new JCheckBox("To-do Tasks of Medium Priority");
			chckbxDisplayMediumPriority.setBounds(39, 69, 250, 23);
			contentPanel.add(chckbxDisplayMediumPriority);
			box[2] = chckbxDisplayMediumPriority;
		}
		{
			JCheckBox chckbxDisplayLowPriority = new JCheckBox("To-do Tasks of Low Priority");
			chckbxDisplayLowPriority.setBounds(39, 118, 250, 23);
			contentPanel.add(chckbxDisplayLowPriority);
			box[1] = chckbxDisplayLowPriority;
		}
		{
			JCheckBox chckbxDisplayCompletedTasks = new JCheckBox("Completed Tasks");
			chckbxDisplayCompletedTasks.setBounds(39, 168, 250, 23);
			contentPanel.add(chckbxDisplayCompletedTasks);
			box[0] = chckbxDisplayCompletedTasks;
		}
		{
			JCheckBox chckbxDisplayHighPriority = new JCheckBox("To-do Tasks of High Priority");
			chckbxDisplayHighPriority.setBounds(39, 23, 250, 23);
			contentPanel.add(chckbxDisplayHighPriority);
			box[3] = chckbxDisplayHighPriority;
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						okBtn();
					}
				});
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						cancelBtn();
					}
				});
				buttonPane.add(cancelButton);
			}
		}

		setVisible(true);
	}

	public Stack<Integer> getBtnHolder() {
		return btnHolder;
	}

	/**
	 * Stores selections in a stack for display when the user clicks ok.
	 */
	private void okBtn() {
		for (int j = 0; j < box.length; j++) {
			if (box[j].isSelected()) {
				btnHolder.push(j);
			}
		}
		setVisible(false);
	}

	/**
	 * Sets the dialog window invisible when the user clicks cancel.
	 */
	private void cancelBtn() {
		setVisible(false);
	}
}

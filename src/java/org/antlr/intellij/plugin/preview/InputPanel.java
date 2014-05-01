package org.antlr.intellij.plugin.preview;

import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.JBColor;
import com.intellij.ui.components.JBPanel;
import org.antlr.intellij.adaptor.parser.SyntaxError;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JBPanel {
	private JRadioButton inputRadioButton;
	private JRadioButton fileRadioButton;
	private JTextArea placeHolder;
	private JTextArea errorConsole;
	private JLabel startRuleLabel;
	private JPanel radioButtonPanel;
	private JPanel startRuleAndInputPanel;
	private TextFieldWithBrowseButton fileChooser;
	protected JPanel outerMostPanel;

	public static final String missingStartRuleLabelText =
		"Start rule: <select from navigator or grammar>";
	public static final String startRuleLabelText = "Start rule: ";

	public PreviewPanel previewPanel;

	public InputPanel(PreviewPanel previewPanel) {
		$$$setupUI$$$();

		this.previewPanel = previewPanel;

		FileChooserDescriptor singleFileDescriptor =
			FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor();
		fileChooser.addBrowseFolderListener("Select input file", null,
											previewPanel.project,
											singleFileDescriptor);
		fileChooser.setTextFieldPreferredWidth(40);
	}

	private void createUIComponents() {
		// TODO: place custom component creation code here
		outerMostPanel = this;
	}

	public JTextArea getErrorConsole() {
		return errorConsole;
	}

	public JLabel getStartRuleLabel() {
		return startRuleLabel;
	}

	public void replaceEditorComponent(JComponent ed) {

	}

	public void setStartRuleName(VirtualFile grammarFile, String startRuleName) {
		startRuleLabel.setText(startRuleLabelText + startRuleName);
		startRuleLabel.setForeground(JBColor.BLACK);
	}

	public void resetStartRuleLabel() {
		startRuleLabel.setText(missingStartRuleLabelText); // reset
		startRuleLabel.setForeground(JBColor.RED);
	}

	public void clearErrorConsole() {
		errorConsole.setText("");
	}

	public void displayErrorInParseErrorConsole(SyntaxError e) {
		String msg = getErrorDisplayString(e);
		errorConsole.insert(msg + '\n', errorConsole.getText().length());
	}

	public static String getErrorDisplayString(SyntaxError e) {
		return "line " + e.getLine() + ":" + e.getCharPositionInLine() + " " + e.getMessage();
	}

	/**
	 * Method generated by IntelliJ IDEA GUI Designer
	 * >>> IMPORTANT!! <<<
	 * DO NOT edit this method OR call it in your code!
	 *
	 * @noinspection ALL
	 */
	private void $$$setupUI$$$() {
		createUIComponents();
		outerMostPanel.setLayout(new BorderLayout(0, 0));
		startRuleAndInputPanel = new JPanel();
		startRuleAndInputPanel.setLayout(new BorderLayout(0, 0));
		outerMostPanel.add(startRuleAndInputPanel, BorderLayout.NORTH);
		startRuleLabel = new JLabel();
		startRuleLabel.setText("Label");
		startRuleAndInputPanel.add(startRuleLabel, BorderLayout.WEST);
		radioButtonPanel = new JPanel();
		radioButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		startRuleAndInputPanel.add(radioButtonPanel, BorderLayout.EAST);
		inputRadioButton = new JRadioButton();
		inputRadioButton.setSelected(true);
		inputRadioButton.setText("Input");
		radioButtonPanel.add(inputRadioButton);
		fileRadioButton = new JRadioButton();
		fileRadioButton.setText("File");
		radioButtonPanel.add(fileRadioButton);
		fileChooser = new TextFieldWithBrowseButton();
		radioButtonPanel.add(fileChooser);
		errorConsole = new JTextArea();
		errorConsole.setEditable(false);
		errorConsole.setRows(3);
		outerMostPanel.add(errorConsole, BorderLayout.SOUTH);
		placeHolder = new JTextArea();
		placeHolder.setEditable(false);
		placeHolder.setEnabled(true);
		placeHolder.setText("place holder");
		outerMostPanel.add(placeHolder, BorderLayout.CENTER);
	}

	/**
	 * @noinspection ALL
	 */
	public JComponent $$$getRootComponent$$$() {
		return outerMostPanel;
	}
}

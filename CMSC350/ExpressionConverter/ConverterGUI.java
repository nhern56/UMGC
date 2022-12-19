// CMSC 350 Data Structures and Analysis
// Project 1
// Nicolas Hernandez
// August 22, 2022

// This class contains the main method that creates GUI for expression converter
// and add ActionListener for buttons

package ExpressionConverter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.*;

//main class for GUI
public class ConverterGUI extends JFrame implements ActionListener {
	//declares text fields for input and output
	//declares JButtons for prefixToPostfix and postfixToPrefix
	private final JTextField input = new JTextField(20);
	private final JTextField output = new JTextField(20);
	private final JButton prefixToPostfix = new JButton("Prefix to Postfix");
	private final JButton postfixToPrefix = new JButton("Postfix to Prefix");

	//GUI components and settings
	private ConverterGUI() {
		super("Expression Converter");
		setSize(400, 160);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout());
		setLocationRelativeTo(null);
		setResizable(false);
		JComponent[] inputComponents = {new JLabel("Enter Expression"), input};
		makeFlowPanel(inputComponents);
		JComponent[] buttonComponents = {prefixToPostfix};
		makeFlowPanel(buttonComponents);
		prefixToPostfix.addActionListener(this);
		JComponent[] buttonComponents1 = {postfixToPrefix};
		makeFlowPanel(buttonComponents1);
		JComponent[] outputComponents = {new JLabel("Result"), output};
		makeFlowPanel(outputComponents);
		postfixToPrefix.addActionListener(this);
		output.setEditable(false);
	}	

	//adds JPanel
	private void makeFlowPanel(JComponent[] components) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		for (Component component:  components)
			panel.add(component);
		add(panel);
	}

	// action performed by buttons
	public void actionPerformed(ActionEvent e)
	{
		try {
			//accepts user input
			String expressionString = input.getText();
			if(e.getSource() == prefixToPostfix){
				String conversion = Conversion.prefixToPostfix(expressionString);
				output.setText(conversion);
			} else if (e.getSource() == postfixToPrefix) {
				String conversion = Conversion.postfixToPrefix(expressionString);
				output.setText(conversion);
			}
		} catch (IOException | SyntaxError ex){
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}

	//run program
	public static void main(String[] args) {
		ConverterGUI frame = new ConverterGUI();
		frame.setVisible(true);
	}
}

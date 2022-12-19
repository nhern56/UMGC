// CMSC 350 Data Structures and Analysis
// Project 1
// Nicolas Hernandez
// August 22, 2022

// This class contains a public methods that tokenizes string and converts prefix to postfix
// and vice versa


package ExpressionConverter;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
import java.util.*;

public class Conversion {

	// defines operators
	private static boolean isOperator(String term){
		return !switch (term.charAt(0)) {
			case '+', '-', '/', '*' -> true;
			default -> false;
		};
	}

	// tokenizes and stores tokens into an array list
	private static List<String> tokenizer(String expression) throws IOException {
		// takes a String and tokenizes it into an ArrayList
		StreamTokenizer tokenizeExpression = new StreamTokenizer(new StringReader(expression));
		List<String> tokens = new ArrayList<>();
		//allows '-' and '/' to be accepted as char
		tokenizeExpression.ordinaryChar('-');
		tokenizeExpression.ordinaryChar('/');
		//tokenizes next token read
		while (tokenizeExpression.nextToken() != StreamTokenizer.TT_EOF){
			//number
			if (tokenizeExpression.ttype == StreamTokenizer.TT_NUMBER){
				tokens.add(String.valueOf((int)tokenizeExpression.nval));
				//words
			}else if(tokenizeExpression.ttype == StreamTokenizer.TT_WORD){
				tokens.add(tokenizeExpression.sval);
				//operators
			}else{
				tokens.add(String.valueOf((char) tokenizeExpression.ttype));
			}
		}
		//updates and saves new list
		return tokens;
	}

	//converts prefix expressions to postfix expressions
	public static String prefixToPostfix(String expression) throws SyntaxError, IOException {
		// if there are more tokens
		if(!expression.equals("")){
			//tokenized string stored to array
			List<String> expressionToArray = tokenizer(expression);
			//creates operand stack
			Stack<String> operandStack = new Stack<>();
			//flips list of operands
			Collections.reverse(expressionToArray);
			//pop the next token from stack
			for (String token:expressionToArray) {
				//push to operand stack
				if (isOperator(token)){
					operandStack.push(token + " ");
				}else {
					//pops two operands off the operand stack and creates string
					//with two operands followed by operator
					try {
						String operand1 = operandStack.pop();
						String operand2 = operandStack.pop();
						String temp = operand1 + operand2 + token + " ";
						//push string onto operand stack
						operandStack.push(temp);
					}catch (EmptyStackException ex){
						throw new SyntaxError("Attempted to pop an empty stack!");
					}
				}
			}
			//pop postfix expression off of the stack
			String result = operandStack.pop();
			if (operandStack.empty()){
				return result;
			}else { // else throw new exception
				throw new SyntaxError("Stack is not empty!");
			}
		}else { //prevents empty string adding to stack
			throw new SyntaxError("Nothing entered.");
		}
	}

	//converts postfix expression prefix
	public static String postfixToPrefix(String expression) throws SyntaxError, IOException {
		//if there are more tokens
		if (!expression.equals("")){
			//tokenized string stored to array
			List<String> expressionToArray = tokenizer(expression);
			//creates operand stack
			Stack<String> operandStack = new Stack<>();
			//pop the next token from stack
			for (String token:expressionToArray) {
				//push to operand stack
				if (isOperator(token)){
					operandStack.push(token + " ");
				}else {
					//pops two operands off the operand stack and creates string
					//with operator followed by two operands
					try {
						String operand2 = operandStack.pop();
						String operand1 = operandStack.pop();
						String temp = token + " " + operand1 + operand2;
						//push string onto operand stack
						operandStack.push(temp);
					}catch (EmptyStackException ex){
						throw new SyntaxError("Attempted to pop an empty stack!");
					}
				}
			}
			//pop postfix expression off of the stack
			String result = operandStack.pop();
			if (operandStack.empty()){
				return result;
			}else { // else throw new exception
				throw new SyntaxError("Stack is not empty!");
			}
		} else { //prevents empty string adding to stack
			throw new SyntaxError("Nothing was entered.");
		}

	}
}

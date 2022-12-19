// CMSC 350 Data Structures and Analysis
// Project 1
// Nicolas Hernandez
// August 22, 2022

//SyntaxError catch exception

package ExpressionConverter;

//exception class
class SyntaxError extends Exception {
    //catches Exception aNd displays message
    public SyntaxError(String message){ super(message); }
}

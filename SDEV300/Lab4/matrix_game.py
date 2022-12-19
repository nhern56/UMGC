"""Python Matrix Application"""

import re
import numpy as np


def display_menu():
    """Display menu"""
    print("***********Welcome to the Python Matrix Application************")
    print("Welcome to the Python Matrix Application")
    print("Do you want to play the Matrix Game?")


def get_number():
    """Get and validate phone number"""
    while True:
        phone = input("Enter your phone number: (xxx-xxx-xxxx)\n")
        if re.match(r'^\d{3}-\d{3}-\d{4}$', phone):  # restricts format to (xxx-xxx-xxxx)
            print(phone, "Is a valid phone number\n")
            return True
        print("Your phone number is not in correct format. Please try again.")
        return get_number()  # if input does not match format restarts function


def get_zipcode():
    """get and validate zipcode"""
    while True:
        zipcode = input("Enter your zipcode: (xxxxx-xxxx)\n")
        if re.match(r'^\d{5}-\d{4}$', zipcode):  # restricts format to (xxxxx-xxxx)
            print(zipcode, "Is a valid zipcode\n")
            return True
        print("Your zipcode is not in correct format. Please try again.")
        return get_zipcode()  # if input does not match format restarts function


def display_menu2():
    """Second display menu for matrix operations"""
    print("Select a Matrix Operation from the list below:")
    print("a. Addition")
    print("b. Subtraction")
    print("c. Matrix Multiplication")
    print("d. Element by element multiplication")


def add_matrix():
    """add matrix operation"""
    print("You selected addition. The results are: ")
    result = np.add(matrix1, matrix2)
    print(result)
    result2 = np.add(matrix1.transpose(), matrix2.transpose())  # adds the transpose
    print("\nThe transpose: ")
    print(result2)
    print("\nThe row and column mean values of the results are: \n")
    matrix5 = result
    x_matrix = np.matrix(matrix5)
    print("Row: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(1))  # finds the mean of the rows
    print("Column: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(0))  # finds the mean of the columns


def subtract_matrix():
    """subtract matrix operation"""
    print("You selected subtracted. The results are: ")
    result = np.subtract(matrix1, matrix2)
    print(result)
    result2 = np.subtract(matrix1.transpose(), matrix2.transpose())  # subtracts the transpose
    print("\nThe transpose: ")
    print(result2)
    print("\nThe row and column mean values of the results are: \n")
    matrix5 = result
    x_matrix = np.matrix(matrix5)
    print("Row: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(1))  # finds the mean of the rows
    print("Column: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(0))  # finds the mean of the columns


def mult_matrix():
    """multiply matrix operation"""
    print("You selected multiplication. The results are: ")
    result = np.dot(matrix1, matrix2)
    print(result)
    result2 = np.dot(matrix1.transpose(), matrix2.transpose())  # multiplies the transpose
    print("\nThe transpose: ")
    print(result2)
    print("\nThe row and column mean values of the results are: \n")
    matrix5 = result
    x_matrix = np.matrix(matrix5)
    print("Row: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(1))  # finds the mean of the rows
    print("Column: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(0))  # finds the mean of the columns


def mult_elements():
    """multiply elements operation"""
    print("You selected multiply elements. The results are: ")
    result = np.multiply(matrix1, matrix2)
    print(result)
    result2 = np.multiply(matrix1.transpose(), matrix2.transpose())  # multiplies the transposes
    print("\nThe transpose: ")
    print(result2)
    print("\nThe row and column mean values of the results are: \n")
    matrix5 = result
    x_matrix = np.matrix(matrix5)
    print("Row: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(1))  # finds the mean of the rows
    print("Column: ")
    np.set_printoptions(precision=2)  # limits the decimal place to 2, for the mean
    print(x_matrix.mean(0))  # finds the mean of the columns


if __name__ == "__main__":
    display_menu()
    CHOICE = str(input("Enter Y for yes or N no: \n"))
    CHOICE = CHOICE.upper()
    if CHOICE == 'Y':
        get_number()
        get_zipcode()
        ROWS = 3
        COLUMNS = 3
        print("Enter your first matrix: ")
        print("Format for 3x3 matrix: (x x x x x x x x x)")
        elements = list(map(int, input().split()))  # gathers the elements from user input
        matrix1 = np.array(elements).reshape(ROWS, COLUMNS)  # reshapes the elements to 3x3 matrix
        print(matrix1)
        print("Enter your second matrix: ")
        elements2 = list(map(int, input().split()))  # gathers the elements from user input
        matrix2 = np.array(elements2).reshape(ROWS, COLUMNS)  # reshapes matrix to 3x3
        print(matrix2)
        display_menu2()
        USERCHOICE = str(input(""))
        USERCHOICE = USERCHOICE.lower()
        if USERCHOICE == 'a':
            add_matrix()
        if USERCHOICE == 'b':
            subtract_matrix()
        if USERCHOICE == 'c':
            mult_matrix()
        if USERCHOICE == 'd':
            mult_elements()
    if CHOICE == 'N':
        print("*********** Thanks for playing Python Numpy ***************")

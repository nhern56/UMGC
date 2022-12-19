//Nicolas Hernandez
//Project 2
//CMSC 350

import java.util.*;
import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //new array list
    public static List<Polynomial> polyList = new ArrayList<>();

    //scan file from JChooser, add to list
    public static ArrayList<String> readFile() {
        ArrayList<String> polyList = new ArrayList<>();
        JFileChooser chooser = new JFileChooser();

        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

        // Read the file
        int response = chooser.showOpenDialog(null);

        if (response == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                Scanner fileIn = new Scanner(file);
                if  (file.isFile()){
                    while (fileIn.hasNextLine()){
                        String expression = fileIn.nextLine();
                        polyList.add(expression);
                    }
                }
            } catch (NoSuchElementException nse) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"This file is empty!");
            } catch(FileNotFoundException fne) {
                JOptionPane.showMessageDialog(JOptionPane.getRootFrame(),"The file could not be found!");
            }
        }
        return polyList;
    }

    //check if list order is weak
    public static boolean checkWeakOrder(List<Polynomial> polyList) {
        boolean orderCheck = true;
        Polynomial previous = polyList.get(polyList.size() - 1);
        for (int i = polyList.size()-2; i > 0; i--) {
            if (previous.comparePoly(polyList.get(i)) < 0) {
                orderCheck = false;
            }
        }
        return orderCheck;
    }

    //display list and order strength
    public static void polyListProcess() {
        try {
            ArrayList<String> temp = readFile();
            for (String e : temp) {
                Polynomial p = new Polynomial(e);
                System.out.println(p);
                polyList.add(p);
            }
        } catch (InvalidPolynomialSyntax ex) {
            JOptionPane.showMessageDialog(JOptionPane.getRootFrame(), ex.getMessage());
        }

        // Call to check sorted for the Strong order check
        System.out.println("Strong Ordered: " + OrderedList.checkSorted(polyList));
        // Check for Weak order (exponents only)
        System.out.println("Weak Ordered: " + checkWeakOrder(polyList));
    }

    public static void main(String[] args) {
        polyListProcess();
    }
}

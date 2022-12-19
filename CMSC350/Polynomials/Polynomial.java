//Nicolas Hernandez
//Project 2
//CMSC 350

import java.util.Scanner;
import java.util.Iterator;

class Polynomial implements Iterable<Polynomial.Poly>, Comparable<Polynomial> {

    public static class Poly {
        // variables
        public double coef;
        public int exp;
        public Poly next;
        private final Poly head;

        //constructor
        public Poly(double c, int e, Poly head) {
            coef = c;
            exp = e;
            this.head = head;
            next = null;
        }

        //get methods
        public int getExponent() {
            return this.exp;
        }

        public double getCoefficient() {
            return this.coef;
        }

        public Poly getNext() {
            return next;
        }

        public Poly getHead() {
            return head;
        }

        //to string method
        @Override
        public String toString() {
            String termString = String.format("%.1f", Math.abs(coef));
            if (exp == 0) { //no variable
                return termString;
            } else if (exp == 1) { // do not display exponent
                return termString + "x";
            } else { // display exponent after variable
                return termString + "x^" + exp;
            }
        }
    }

    // declare Poly head
    public Poly head = null;

    // check syntax
    public Polynomial(String p) {
        Scanner ts = new Scanner(p);
        try {
            while (ts.hasNext()) { addTerm(ts.nextDouble(), ts.nextInt()); }
        } catch (Exception e) {
            e.printStackTrace();
            throw new InvalidPolynomialSyntax("Invalid syntax");
        }
    }

    // sort polynomials and for negative exponents
    public void addTerm(double coefficient, int exponent) {
        if (exponent < 0) throw new InvalidPolynomialSyntax("Invalid syntax: Negative exponent");

        Poly cur = head;

        if (cur == null) {
            head = new Poly(coefficient, exponent, null);
            head.next = null;
        } else {
            while (cur.next != null) cur = cur.next;
            cur.next = new Poly(coefficient, exponent, head);
        }
    }

    //compare polynomials
    @Override
    public int compareTo(Polynomial p3) {
        Poly thisCurrent = this.head;
        Poly otherCurrent = p3.head;

        while (thisCurrent != null && otherCurrent != null) {
            if (thisCurrent.getExponent() != otherCurrent.getExponent()) {
                return thisCurrent.getExponent() - otherCurrent.getExponent();
            } else if (thisCurrent.getCoefficient() != otherCurrent.getCoefficient()) {
                if (otherCurrent.getCoefficient() > thisCurrent.getCoefficient()) {
                    return -1;
                } else if (otherCurrent.getCoefficient() < thisCurrent.getCoefficient()) {
                    return 1;
                }
            }
            thisCurrent = thisCurrent.getNext();
            otherCurrent = otherCurrent.getNext();
        }

        if (thisCurrent == null && otherCurrent == null) return 0;

        if (thisCurrent == null) {
            return -1;
        } else {
            return 1;
        }
    }

    // compare polynomials
    public int comparePoly(Polynomial p2) {
        Poly thisPolyTerm = this.head;
        Poly otherPolyTerm = p2.head;

        while (thisPolyTerm != null && otherPolyTerm != null) {
            if (thisPolyTerm.getExponent() != otherPolyTerm.getExponent()) {
                return thisPolyTerm.getExponent() - otherPolyTerm.getExponent();
            } else {
                thisPolyTerm = thisPolyTerm.getNext();
                otherPolyTerm = otherPolyTerm.getNext();
            }
        }
        if (thisPolyTerm == null && otherPolyTerm == null) return 0;
        if (otherPolyTerm == null) return 1;
        return -1;
    }

    // iterator method - iterates across the terms of the
    // polynomial from highest exponent to lowest and returns
    // an object that contains the coefficient and exponent of the next term
    @Override
    public Iterator<Poly> iterator() {
        return new Iterator<>() {

            public Poly cur = head.getHead();

            @Override
            public boolean hasNext() {
                return (cur != null) && (cur.getNext() != null);
            }

            @Override
            public Poly next() {
                Poly data = cur;
                cur = cur.next;
                return data;
            }
        };
    }

    // to string method - builds expression to print
    @Override
    public String toString() {
        StringBuilder exp = new StringBuilder();

        if (head.coef > 0) {
            exp.append(head);
        } else {
            exp.append(" - ").append(head);
        }

        for (Poly temp = head.next; temp != null; temp = temp.next) {

            if ((temp.coef < 0)) {
                exp.append(" - ").append(temp);
            } else {
                exp.append(" + ").append(temp);
            }
        }
        return exp.toString();
    }

}
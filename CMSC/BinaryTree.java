import java.util.EmptyStackException;
import java.util.Stack;

public class BinaryTree {
    private Node parent, child;

    // turn string into an array, Loops through using a stack and adds children
    public BinaryTree(String input) throws InvalidTreeSyntax {
        Stack<Node> nodeStack = new Stack<>();
        String[] inputArray = input.substring(1, input.length()-1).split("(?<=\\()|(?=\\()|(?<=\\))|(?=\\))");
        // remove parenthesis and split the String into an array, keeps parenthesis
        parent = new Node(inputArray[0]);
        //setting the new first character to root
        for (int i = 1; i < inputArray.length - 1; i++){
        //there is another child. Child becomes parent if one exists
            if (inputArray[i].equals("(")) {
                nodeStack.push(parent);
                if (child != null) {
                    parent = child;
                }
                //current parent is the child
            }else if (inputArray[i].equals(")")) {
                try {
                    child = parent;
                    parent = nodeStack.pop();
                } catch (EmptyStackException emptyStack) {
                    throw new InvalidTreeSyntax("Incorrect parenthesis.");
                }
                //child is parent.
            }else{
                child = new Node(inputArray[i]);
                if (parent != null) {
                    parent.addChild(child);
                }
                //addChild with throw InvalidTreeSyntax
            }
        }
        if (this.treeNodes(parent) * 3 != input.length()) throw new InvalidTreeSyntax("Incorrect Syntax");
    }

    // determine if tree is balanced
    public boolean isBalanced() {
        return treeIsBalanced(this.parent);
    }
    //determines if a tree has the maximum nodes for the height
    public boolean isFull() {
        return treeIsFull(this.parent, treeHeight(this.parent), 0);
    }
    // determine if every node in tree has either 2 or 0 children
    public boolean isProper() {
        return treeIsProper(this.parent);
    }
    //finds the height of the binary tree
    public int height() {
        return treeHeight (this.parent)-1;
    }
    //find the amount of nodes in a binary tree.
    public int nodes() {
        return treeNodes(this.parent);
    }
    //print the info of the nodes in the binary tree in order
    public String inOrder() {
        return treeInOrder(this.parent);
    }

    // returns if tree is balanced using recursion
    private boolean treeIsBalanced(Node root) {
        if (root == null) {
            return true;
        }
        // return true if the difference is <= 1
        return (Math.abs(treeHeight(root.left) - treeHeight(root.right)) <= 1)
                && (treeIsBalanced(root.left) && treeIsBalanced(root.right));
    }
    // returns if tree full using recursion
    private boolean treeIsFull (Node root, int height, int index) {
        // if it is empty return true
        if (root == null) {
            return true;
        }
        //check to see if height is same among leaves
        if (root.left == null && root.right == null) {
            return (height == index + 1);
        }
        //one child empty
        if (root.left == null || root.right == null) {
            return false;
        }
        // call both children
        return treeIsFull(root.left, height, index + 1) && treeIsFull(root.right, height, index + 1);
    }
    // returns if tree is proper using recursion
    private boolean treeIsProper(Node root) {
        //base case
        if (root == null) {
            return true;
        }
        // returns true or false based on 2 or 0 children
        return ((root.left != null || root.right == null) && (root.left == null || root.right != null))
                && (treeIsProper(root.left) && treeIsProper(root.right));
    }
    // returns tree's height using recursion
    private int treeHeight(Node root) {
        //adds 1 to the larger of the left or right, 0 if null
        return (root == null) ? 0 : 1 + Math.max(treeHeight(root.left), treeHeight(root.right));
    }
    // returns number of tree's nodes using recursion
    private int treeNodes(Node root) {
        //adds one for both left and right, if null, 0
        return (root == null) ? 0 : 1 + treeNodes(root.left) + treeNodes(root.right);
    }
    // returns if tree is inOrder using recursion
    private String treeInOrder(Node root) {
        return (root == null) ? "" : "(" + treeInOrder(root.left) + root.info + treeInOrder(root.right) + ")";
    }

    //calls the root node toString
    @Override
    public String toString() {
        return parent.toString();
    }

    //nodes to be used in tree and methods to act on them
    public static class Node {
        private final String info;
        private Node left;
        private Node right;

        // Node constructor
        public Node(String info) {
            this.info = info;
        }

        // child constructor
        private void addChild(Node child) throws InvalidTreeSyntax {
            //conditions for nodes
            if (this.left == null) {
                this.setLeftSubTree(child);
            }
            else if (this.right == null) {
                this.setRightSubTree(child);
            }
            else {
                throw new InvalidTreeSyntax("Nodes can only have 2 children!");
            }
        }

        //setters methods for nodes
        private void setLeftSubTree(Node newLeft) {
            left = newLeft;
        }
        private void setRightSubTree(Node newRight) {
            right = newRight;
        }

        //to string method
        @Override
        public String toString() {
            return toString(this);
        }

        // display tree
        private static String toString(Node root) {
            return (root == null) ? "" : "(" + root.info + toString(root.left)+ toString(root.right) + ")";
        }
    }
}

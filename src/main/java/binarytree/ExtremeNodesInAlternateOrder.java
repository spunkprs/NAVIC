package binarytree;

import java.util.Stack;

public class ExtremeNodesInAlternateOrder {

    private Stack<Node> stackOne = new Stack<Node>();
    private Stack<Node> stackTwo = new Stack<Node>();

    public  void printExtremeNode(Node node) {
        stackOne.add(node);

        while (!stackOne.isEmpty() || !stackTwo.isEmpty()) {
            int countOne = 1;
            while (!stackOne.isEmpty()) {
                Node element = stackOne.pop();
                if (element.right != null) {
                    stackTwo.add(element.right);
                }

                if (element.left != null) {
                    stackTwo.add(element.left);
                }
                printElement(element, countOne);
                countOne++;
            }

            int countTwo = 1;
            while (!stackTwo.isEmpty()) {
                Node element = stackTwo.pop();
                if (element.left != null) {
                    stackOne.add(element.left);
                }
                if (element.right != null) {
                    stackOne.add(element.right);
                }
                printElement(element, countTwo);
                countTwo++;
            }
        }
    }

    private void printElement(Node element, int count) {
        if (count == 1) {
            System.out.print(element.data);
            System.out.print(" ");
        }
    }
}

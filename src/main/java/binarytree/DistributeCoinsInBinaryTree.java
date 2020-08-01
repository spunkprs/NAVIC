package binarytree;

public class DistributeCoinsInBinaryTree {
	
private int numberOfMoves = 0;
    
    public int distributeCoins(TreeNode root) {
        processToDistributeCoins(root);
        return numberOfMoves;
    }
    
    private void processToDistributeCoins(TreeNode node) {
        if (node != null) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            int leftZeroes = countNumberOfZeroes(leftNode);
            int rightZeroes = countNumberOfZeroes(rightNode);
            
            if (leftZeroes == 0 && rightZeroes > 0) {
                int moves = node.val - 1;
                numberOfMoves += moves;
                node.val = 1;
                rightNode.val += moves;
                processToDistributeCoins(rightNode);
            } else if (leftZeroes > 0 && rightZeroes == 0) {
                int moves = node.val - 1;
                numberOfMoves += moves;
                node.val = 1;
                leftNode.val += moves;
                processToDistributeCoins(leftNode);
            } else {
                numberOfMoves+= leftZeroes + rightZeroes;
                node.val = 1;
                leftNode.val += leftZeroes;
                rightNode.val += rightZeroes;
                processToDistributeCoins(leftNode);
                processToDistributeCoins(rightNode);
            }
        }
    }
    
    private int countNumberOfZeroes(TreeNode node) {
        int numberOfZeroes = 0;
        if (node != null) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            countNumberOfZeroes(leftNode);
            countNumberOfZeroes(rightNode);
            if (leftNode != null) {
                if (leftNode.val > 1) {
                    int moves =  leftNode.val - 1;
                    numberOfMoves += moves;
                    node.val += moves;
                    leftNode.val = 1;
                } else if (leftNode.val == 0) {
                    numberOfZeroes++;
                }
            }
            
            if (rightNode != null) {
                if (rightNode.val > 1) {
                    int moves =  rightNode.val - 1;
                    numberOfMoves += moves;
                    node.val += moves;
                    rightNode.val = 1;
                } else if (rightNode.val == 0) {
                    numberOfZeroes++;
                }
            }
        }
        return numberOfZeroes;
    }

}

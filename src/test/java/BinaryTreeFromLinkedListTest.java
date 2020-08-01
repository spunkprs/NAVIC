import binarytree.BinaryTreeFromLinkedList;
import org.junit.Assert;
import org.junit.Test;

public class BinaryTreeFromLinkedListTest {

    @Test
    public void shouldConvertLinkedListToBinaryTreeCaseOne() {
        BinaryTreeFromLinkedList.Node head = new BinaryTreeFromLinkedList.Node(1);
        BinaryTreeFromLinkedList.Node nodeOne = new BinaryTreeFromLinkedList.Node(2);
        BinaryTreeFromLinkedList.Node nodeTwo = new BinaryTreeFromLinkedList.Node(3);

        head.next = nodeOne;
        nodeOne.next = nodeTwo;

        BinaryTreeFromLinkedList.Tree root = BinaryTreeFromLinkedList.convert(head, null);
        Assert.assertNotNull(root);
    }

    @Test
    public void shouldConvertLinkedListToBinaryTreeCaseTwo() {
        BinaryTreeFromLinkedList.Node head = new BinaryTreeFromLinkedList.Node(1);
        BinaryTreeFromLinkedList.Node nodeOne = new BinaryTreeFromLinkedList.Node(2);
        BinaryTreeFromLinkedList.Node nodeTwo = new BinaryTreeFromLinkedList.Node(3);
        BinaryTreeFromLinkedList.Node nodeThree = new BinaryTreeFromLinkedList.Node(4);
        BinaryTreeFromLinkedList.Node nodeFour = new BinaryTreeFromLinkedList.Node(5);

        head.next = nodeOne;
        nodeOne.next = nodeTwo;
        nodeTwo.next = nodeThree;
        nodeThree.next = nodeFour;

        BinaryTreeFromLinkedList.Tree root = BinaryTreeFromLinkedList.convert(head, null);
        Assert.assertNotNull(root);
    }
}

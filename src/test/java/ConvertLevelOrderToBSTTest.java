import binarytree.ConvertLevelOrderToBST;
import binarytree.Node;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConvertLevelOrderToBSTTest {

    private ConvertLevelOrderToBST unit;

    @Before
    public void setUp() {
        unit = new ConvertLevelOrderToBST();
    }

    @Test
    public void shouldConvertToTreeFromLevelOrderTravsersalCaseOne() {
        int arr[] = {7, 4, 12, 3, 6, 8, 1, 5, 10};
        Node root = unit.constructBST(arr);
        Assert.assertNotNull(root);
    }

    @Test
    public void shouldConvertToTreeFromLevelOrderTravsersalCaseTwo() {
        int arr[] = {1, 3, 4, 6, 7, 8};
        Node root = unit.constructBST(arr);
        Assert.assertNotNull(root);
    }
}

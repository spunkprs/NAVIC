import org.junit.Test;

import binarytree.TreeAncestor;
import org.junit.Assert;

public class TreeAncestorTest {
	
	@Test
	public void shouldFetchKthAncestorForATreeCaseOne() {
		int parent[] = {-1,0,0,1,1,2,2};
		TreeAncestor treeAncestor = new TreeAncestor(7, parent);
		Assert.assertEquals(1, treeAncestor.getKthAncestor(3, 1));
	}
	
	@Test
	public void shouldFetchKthAncestorForATreeCaseTwo() {
		int parent[] = {-1, 0, 0, 0, 3};
		TreeAncestor treeAncestor = new TreeAncestor(5, parent);
		Assert.assertEquals(-1, treeAncestor.getKthAncestor(1, 5));
		Assert.assertEquals(-1, treeAncestor.getKthAncestor(3, 2));
		Assert.assertEquals(-1, treeAncestor.getKthAncestor(0, 1));
		Assert.assertEquals(0, treeAncestor.getKthAncestor(3, 1));
		Assert.assertEquals(-1, treeAncestor.getKthAncestor(3, 5));
	}

}

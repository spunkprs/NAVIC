import org.junit.Before;
import org.junit.Test;

import graph.ShortestPathToGetAllKeys;
import org.junit.Assert;

public class ShortestPathToGetAllKeysTest {
	
	private ShortestPathToGetAllKeys unit;
	
	@Before
	public void setUp() {
		unit = new ShortestPathToGetAllKeys();
	}
	
	@Test
	public void shouldFetchShortestPathToGetAllKeysCaseOne() {
		String words[] = {"@..aA", "..B#.", "....b"};
		Assert.assertEquals(6, unit.shortestPathAllKeys(words));
	}
	
	@Test
	public void shouldFetchShortestPathToGetAllKeysCaseTwo() {
		String words[] = {"@.a.A", "###.#", "b.A.B"};
		Assert.assertEquals(8, unit.shortestPathAllKeys(words));
	}

}

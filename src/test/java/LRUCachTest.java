import org.junit.Test;

import org.junit.Assert;
import list.LRUCache;

public class LRUCachTest {
	
	private LRUCache unit;
	
	@Test
	public void shouldImplementLRUCacheCaseOne() {
		unit = new LRUCache(2);
		unit.put(1, 1);
		unit.put(2, 2);
		Assert.assertEquals(1, unit.get(1));
		
		unit.put(3, 3);
		Assert.assertEquals(-1, unit.get(2));
		
		unit.put(4, 4);
		Assert.assertEquals(-1, unit.get(1));
		Assert.assertEquals(3, unit.get(3));
		Assert.assertEquals(4, unit.get(4));
	}
	
	@Test
	public void shouldImplementLRUCacheCaseTwo() {
		unit = new LRUCache(1);
		unit.put(2, 1);
		Assert.assertEquals(1, unit.get(2));
		
		unit.put(3, 2);
		Assert.assertEquals(-1, unit.get(2));
		
		Assert.assertEquals(2, unit.get(3));
	}
	
	@Test
	public void shouldImplementLRUCacheCaseThree() {
		unit = new LRUCache(10);
		
		unit.put(10, 13);
		unit.put(3, 17);
		unit.put(6, 11);
		unit.put(10, 5);
		unit.put(9, 10);
		Assert.assertEquals(-1, unit.get(13));
		unit.put(2, 19);
		Assert.assertEquals(19, unit.get(2));
		Assert.assertEquals(17, unit.get(3));
		unit.put(5, 25);
		Assert.assertEquals(-1, unit.get(8));
		unit.put(9, 22);
		unit.put(5, 5);
		unit.put(1, 30);
		Assert.assertEquals(-1, unit.get(11));
		unit.put(9, 12);
		Assert.assertEquals(-1, unit.get(7));
		Assert.assertEquals(5, unit.get(5));
		Assert.assertEquals(-1, unit.get(8));
		Assert.assertEquals(12, unit.get(9));
		unit.put(4, 30);
		unit.put(9, 3);
		Assert.assertEquals(3, unit.get(9));
		Assert.assertEquals(5, unit.get(10));
		Assert.assertEquals(5, unit.get(10));
		
		unit.put(6, 14);
		unit.put(3, 1);
		Assert.assertEquals(1, unit.get(3));
		unit.put(10, 11);
		Assert.assertEquals(-1, unit.get(8));
		unit.put(2, 14);
		Assert.assertEquals(30, unit.get(1));
		Assert.assertEquals(5, unit.get(5));
		Assert.assertEquals(30, unit.get(4));
		
		unit.put(11, 4);
		unit.put(12, 24);
		unit.put(5, 18);
		
		Assert.assertEquals(-1, unit.get(13));
		unit.put(7, 23);
		Assert.assertEquals(-1, unit.get(8));
		Assert.assertEquals(24, unit.get(12));
		
		unit.put(3, 27);
		unit.put(2, 12);
		Assert.assertEquals(18, unit.get(5));
		
		unit.put(2, 9);
		unit.put(13, 4);
		unit.put(8, 18);
		unit.put(1, 7);
		
		Assert.assertEquals(-1, unit.get(6));
		
		unit.put(9, 29);
		unit.put(8, 21);
		
		Assert.assertEquals(18, unit.get(5));
		
		unit.put(6, 30);
		unit.put(1, 12);
		
		Assert.assertEquals(-1, unit.get(10));
				
		unit.put(4, 15);	
		unit.put(7, 22);
		unit.put(11, 26);
		unit.put(8, 17);
		unit.put(9, 29);
		
		Assert.assertEquals(18, unit.get(5));
				
		unit.put(3, 4);
		unit.put(11, 30);
		
		Assert.assertEquals(-1, unit.get(12));
	}
}

import org.junit.Test;

import org.junit.Assert;
import list.LFUCache;

public class LFUCacheTest {
	
	@Test
	public void shouldTestLFUCaseOne() {
		LFUCache lfuCache = new LFUCache(2);
		
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		
		Assert.assertEquals(1, lfuCache.get(1));
		Assert.assertEquals(2, lfuCache.get(2));
		lfuCache.put(3, 3);
		Assert.assertEquals(2, lfuCache.get(2));
		Assert.assertEquals(3, lfuCache.get(3));
		
		lfuCache.put(4, 4);
		Assert.assertEquals(-1, lfuCache.get(1));
		Assert.assertEquals(-1, lfuCache.get(3));
		Assert.assertEquals(4, lfuCache.get(4));
	}
	
	@Test
	public void shouldTestLFUCaseTwo() {
		LFUCache lfuCache = new LFUCache(2);
		
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		
		Assert.assertEquals(1, lfuCache.get(1));
		lfuCache.put(3, 3);
		Assert.assertEquals(-1, lfuCache.get(2));
		Assert.assertEquals(3, lfuCache.get(3));
		
		lfuCache.put(4, 4);
		Assert.assertEquals(-1, lfuCache.get(1));
		Assert.assertEquals(3, lfuCache.get(3));
		Assert.assertEquals(4, lfuCache.get(4));
		
	}
	
	@Test
	public void shouldTestLFUCaseThree() {
		LFUCache lfuCache = new LFUCache(4);
		
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		
		Assert.assertEquals(1, lfuCache.get(1));
		lfuCache.put(3, 3);
		Assert.assertEquals(1, lfuCache.get(1));
		Assert.assertEquals(3, lfuCache.get(3));
		
		lfuCache.put(4, 4);
		Assert.assertEquals(1, lfuCache.get(1));
		lfuCache.put(4, 5);
		lfuCache.put(6, 6);
		Assert.assertEquals(3, lfuCache.get(3));
		Assert.assertEquals(5, lfuCache.get(4));
		Assert.assertEquals(-1, lfuCache.get(2));
	}
	
	@Test
	public void shouldTestLFUCaseFour() {
		LFUCache lfuCache = new LFUCache(0);
		lfuCache.put(0, 0);
		Assert.assertEquals(-1, lfuCache.get(0));
	}
	
	@Test
	public void shouldTestLFUCaseFive() {
		LFUCache lfuCache = new LFUCache(1);
		lfuCache.put(2, 1);
		Assert.assertEquals(1, lfuCache.get(2));
		lfuCache.put(3, 2);
		Assert.assertEquals(-1, lfuCache.get(2));
		Assert.assertEquals(2, lfuCache.get(3));
	}
	
	@Test
	public void shouldTestLFUCaseSix() {
		LFUCache lfuCache = new LFUCache(3);
		lfuCache.put(1, 1);
		lfuCache.put(2, 2);
		lfuCache.put(3, 3);
		lfuCache.put(4, 4);
		Assert.assertEquals(4, lfuCache.get(4));
		Assert.assertEquals(3, lfuCache.get(3));
		Assert.assertEquals(2, lfuCache.get(2));
		Assert.assertEquals(-1, lfuCache.get(1));
		lfuCache.put(5, 5);
		Assert.assertEquals(-1, lfuCache.get(1));
		Assert.assertEquals(2, lfuCache.get(2));
		Assert.assertEquals(3, lfuCache.get(3));
		Assert.assertEquals(-1, lfuCache.get(4));
		Assert.assertEquals(5, lfuCache.get(5));
	}
}

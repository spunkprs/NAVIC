package pt;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import arrays.MedianFromDataStream;

public class MedianFromDataStreamTest {
	
	private MedianFromDataStream unit;
	
	@Before
	public void setUp() {
		unit = new MedianFromDataStream();
	}
	
	@Test
	public void shouldFindMedianFromDataStreamCaseOne() {
		unit.addNum(1);
		unit.addNum(2);
		Assert.assertEquals(1.5, unit.findMedian(), 0.0001);
	}

}

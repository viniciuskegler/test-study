package br.vinicius.kegler.services;

import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

	
//	@Test
	public void test() {
		Assert.assertTrue(true);
		Assert.assertFalse(false);
		Assert.assertEquals(1, 1);
		Assert.assertEquals(0.51, 0.51, 0.01);
		
		int i = 5;
		Integer i2 = 5;
		Assert.assertEquals(Integer.valueOf(i), i2);
		Assert.assertEquals(i, i2.intValue());
		
		Assert.assertEquals("bola", "bola");
	}
	
}

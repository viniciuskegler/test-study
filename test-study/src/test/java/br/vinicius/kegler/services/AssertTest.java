package br.vinicius.kegler.services;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.junit.Assert;
import org.junit.Test;

public class AssertTest {

	
	@Test
	public void test() {
		Float a = null;
		Float b = null;
		Float c = null;
		String pcSt = formatPercentage(a / (b + c));
		Assert.assertTrue(1 == 1);
	}

	
	public static String formatPercentage(float f) {
		return formatFloat(f * 100f, 2) + "%";
	}
	
	public static String formatFloat(double f, int maxCasas) {
		return format(f, maxCasas);
	}
	
	public static String format(Object f, int maxCasas) {
		if(Double.isNaN((Double) f)){
			f = 0;
		}
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(maxCasas);
		df.setGroupingUsed(false);
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return df.format(f);
	}
}

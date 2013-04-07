package com.github.cutstock.algorithm;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.cutstock.utils.ArithmeticUtil;

public class CspConfiguration extends AlgorithmConfig {

	public static final double RC_EPS = 1.0e-6;

	public static final BigDecimal DEFAULT_ROLL_WIDTH = new BigDecimal(6000);

	public static final BigDecimal DEFAULT_HEAD_WIDTH = new BigDecimal(100);

	public static final BigDecimal DEFAULT_GAP_WIDTH = new BigDecimal(10);

	public void init() {
		if (rollWidth.compareTo(new BigDecimal(0)) <= 0) {
			rollWidth = DEFAULT_ROLL_WIDTH;
		}

		if (headWidth.compareTo(new BigDecimal(0)) <= 0) {
			headWidth = DEFAULT_HEAD_WIDTH;
		}

		if (gapWidth.compareTo(new BigDecimal(0)) <= 0) {
			gapWidth = DEFAULT_GAP_WIDTH;
		}

		rollWidth = ArithmeticUtil.subtract(rollWidth,headWidth);

	}

}

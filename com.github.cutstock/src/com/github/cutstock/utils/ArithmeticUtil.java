/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.cutstock.utils;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Nov 1, 2012
 */

import java.math.BigDecimal;

public class ArithmeticUtil {

	private static final int DEF_DIV_SCALE = 10;

	private static final BigDecimal THOUSAND = new BigDecimal(1000);
	private static final BigDecimal HUNDRED = new BigDecimal(100);
	private static final BigDecimal TEN = new BigDecimal(10);

	public static BigDecimal getTensVal(BigDecimal val) {
		return val.remainder(THOUSAND).remainder(HUNDRED);

	}

	public static String trim(String valStr) {
		for (int i = 0, len = valStr.length(); i < len; i++) {
			if (valStr.charAt(len - 1) == '.') {
				return valStr.substring(0, len - 1);
			}
			if (valStr.charAt(len - 1) == '0') {
				len--;
			} else {
				return valStr;
			}
		}
		return valStr;
	}

	public static String trim(BigDecimal val) {
		String valStr = String.valueOf(val.doubleValue());
		return trim(valStr);
	}

	public static int getTensIntVal(BigDecimal val) {
		return getTensVal(val).intValue();

	}

	public static boolean lessThan(BigDecimal left, BigDecimal right){
		return left.compareTo(right) < 0;
	}
	public static boolean greaterThan(BigDecimal left, BigDecimal right) {
		return left.compareTo(right) > 0;
	}

	public static boolean greaterThanOrEqual(BigDecimal left, BigDecimal right) {
		return left.compareTo(right) >= 0;
	}

	private static final int SCALE = 2;

	public static BigDecimal add(BigDecimal left, BigDecimal right) {
		return left.add(right).setScale(SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal subtract(BigDecimal left, BigDecimal right) {
		return left.subtract(right).setScale(SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal multiply(BigDecimal left, BigDecimal right) {
		return left.multiply(right).setScale(SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal divide(BigDecimal left, BigDecimal right) {
		return left.divide(right).setScale(SCALE, BigDecimal.ROUND_HALF_EVEN);
	}

	/**
	 * 向上提升，凑100整数，小于baseVal，提升到baseVal，大于则100
	 * 
	 * @param firstCutWidth
	 * @param baseVal
	 */
	public static BigDecimal roundUp(BigDecimal firstCutWidth, int baseVal) {
		BigDecimal tensVal = getTensVal(firstCutWidth);
		firstCutWidth = ArithmeticUtil.subtract(firstCutWidth, tensVal);
		BigDecimal subVal = ArithmeticUtil.subtract(tensVal, new BigDecimal(
				baseVal));
		if (subVal.doubleValue() >= 0) {
			// upto 100
			return ArithmeticUtil.add(firstCutWidth, new BigDecimal(100));
		} else {
			// upto 50
			return ArithmeticUtil.add(firstCutWidth, new BigDecimal(baseVal));
		}
	}
}

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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.cutstock.algorithm.ColPattern;
import com.github.cutstock.algorithm.CutStockResult;
import com.github.cutstock.algorithm.PatternInfo;
import com.github.cutstock.model.ComponentOrderInfo;
import com.github.cutstock.model.ComponentOrdersLoader;
import com.github.cutstock.model.CutstockResultInfo;
import com.github.cutstock.model.ProfileInfo.WidthAmountPair;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 16, 2012
 */
public class CutstockUtils {

	private static final int MIN_WIDTH = 1100;

	public static List<ColPattern> cloneColPatterns(List<ColPattern> colPatterns){
		List<ColPattern> clonePatterns  = new ArrayList<ColPattern>();
		for(int i =0;i<colPatterns.size();i++){
			ColPattern colPattern = colPatterns.get(i);
			
		}
		
		return clonePatterns;
	}
	
	
	public static double[] getWidthAmoutArrays(List<WidthAmountPair> pairs) {
		int halfSize = pairs.size();
		int size = halfSize * 2;
		double[] result = new double[size];
		int position = 0;
		for (WidthAmountPair pair : pairs) {
			double width = pair.getWidth();
			double amount = pair.getAmount();
			result[position] = width;
			result[position + halfSize] = amount;
			position++;
		}
		return result;
	}

	public static double[][] getWidthAmout(List<WidthAmountPair> pairs,
			int gapWidth) {
		Map<Double,Integer> widthAmountMap = new HashMap();
		for (WidthAmountPair pair : pairs) {
			double width = pair.getWidth();
			double amount = pair.getAmount();
			if(widthAmountMap.get(width)==null){
				widthAmountMap.put(width, (int)amount);
			}else{
				widthAmountMap.put(width, widthAmountMap.get(width)+(int)amount);
			}
		}
		double[][] result = new double[2][widthAmountMap.size()];
		int position = 0;
		for(Iterator<Entry<Double, Integer>> widthamountIt = widthAmountMap.entrySet().iterator();widthamountIt.hasNext();){
			Entry<Double, Integer> entry = widthamountIt.next();
			result[0][position] = entry.getKey() + gapWidth;
			result[1][position] = entry.getValue();
			position++;
		}
		
//		for (WidthAmountPair pair : pairs) {
//			double width = pair.getWidth();
//			double amount = pair.getAmount();
//			result[0][position] = width + gapWidth;
//			result[1][position] = amount;
//			position++;
//		}
		return result;
	}

	// check the left side orders whether has a orderName matching the one from
	// table
	private static String findMatchNameInOrders(String newName) {

		return null;
	}
	private final static BigDecimal ZERO_LEN = new BigDecimal(0);
	private final static int ROUND_UP = 50;
	public static ComponentOrdersLoader convert2componentOrder(
			final CutstockResultInfo resultInfo) {

		ComponentOrdersLoader componentOrdersLoader = new ComponentOrdersLoader(
				resultInfo, null) {
			@Override
			protected void postCreation(ComponentOrderInfo componentOrder) {
				BigDecimal firstCutWidth = componentOrder
						.calcuteFirstCutWidth(resultInfo.getGapWidth(),resultInfo.getHeadWidth());
				
				firstCutWidth = ArithmeticUtil.roundUp(firstCutWidth, ROUND_UP);
				BigDecimal originWidth = componentOrder.getOriginWidth();
				BigDecimal restWidth = ArithmeticUtil.subtract(originWidth,firstCutWidth);
				componentOrder
						.setFirstCutPattern(firstCutWidth.toPlainString());
				componentOrder.setRestWidth(restWidth);
				if (lessThan(restWidth, 1000)) {
					componentOrder.setFirstCutPattern(originWidth
							.toPlainString());
					componentOrder.setRestWidth(ZERO_LEN);
				}
			}

			private boolean lessThan(BigDecimal restWidth, int compareVal) {
				return restWidth.compareTo(new BigDecimal(compareVal)) == -1;
			}

		};

		return componentOrdersLoader;
	}

	public static CutStockResult convertArray2CutStock(
			List<double[]> cutstockList,BigDecimal gap) {
		CutStockResult result = new CutStockResult();
		for (double[] cutArray : cutstockList) {
			PatternInfo pInfo =new PatternInfo(1);
			for(double cutLen:cutArray){
				pInfo.addColumn(ArithmeticUtil.subtract(new BigDecimal(cutLen),gap));
			}
			result.addPatternInfo(pInfo);
		}
		return result;
	}

}

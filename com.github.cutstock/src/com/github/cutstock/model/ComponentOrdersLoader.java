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
package com.github.cutstock.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.github.cutstock.algorithm.ColPattern;
import com.github.cutstock.algorithm.PatternInfo;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 18, 2012
 */
public class ComponentOrdersLoader {

	private final CutstockResultInfo cutResultInfo;

	public ComponentOrdersLoader(CutstockResultInfo resultInfo, Map context) {
		this.cutResultInfo = resultInfo;
	}

	public List<ComponentOrderInfo> doCreateOrder() {
		List<ComponentOrderInfo> orderList = new ArrayList<ComponentOrderInfo>();
		Iterator<PatternInfo> resultIt = cutResultInfo.getCutResult().createIterator();

		while (resultIt.hasNext()) {
			PatternInfo patternInfo = resultIt.next();
			ComponentOrderInfo componentOrder = new ComponentOrderInfo();
			componentOrder.setProfileName(cutResultInfo.getProfileName());
			componentOrder.setColor(cutResultInfo.getColor());
			componentOrder.setOriginWidth(cutResultInfo.getOriginalWidth());

			List<ColPattern> colPatterns = patternInfo.getColPatterns();
			for (int i = 0; i < colPatterns.size(); i++) {
				ColPattern colPattern = colPatterns.get(i);
				BigDecimal colWidth = colPattern.getColWidth();
				int colNum = colPattern.getColNum();
				// componentOrder.setColPattern(colPattern);
				componentOrder.setColPattern(colWidth, colNum);
			}
			componentOrder.setPatternNum(patternInfo.getPatternNum());
			postCreation(componentOrder);
			orderList.add(componentOrder);
		}
		return orderList;

	}

	protected void postCreation(ComponentOrderInfo componentOrder) {
		// do nothing
	}

}

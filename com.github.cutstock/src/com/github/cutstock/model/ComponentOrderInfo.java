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
import java.util.List;

import com.github.cutstock.algorithm.ColPattern;
import com.github.cutstock.utils.ArithmeticUtil;
import com.github.cutstock.utils.StringUtil;

/**
 * 原型材名称 原长度 第一次下料 余料 长度 备注 数量 第二次下料方式
 * 
 * one single order contains one single pattern info
 * 
 * @author <a href="mailto:crazyfarmer.cn@gmail.com">crazyfarmer</a>
 * 
 */
public class ComponentOrderInfo {

	private String profileName;
	private BigDecimal width;
	private String firstCutPattern;
	private BigDecimal restWidth;
	private String color;// 备注
	private int patternNum;
	private String secondCutPattern;

	private final static String PLUS = Messages.ComponentOrderInfo_0;

	// --------------single roll width,roll number-----------------------
	private BigDecimal colWidth;
	private int colNum;

	// ------------------------------------------------------------------
	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public BigDecimal getOriginWidth() {
		return width;
	}

	public void setOriginWidth(BigDecimal bigDecimal) {
		this.width = bigDecimal;
	}

	public String getFirstCutPattern() {
		return firstCutPattern;
	}

	public void setFirstCutPattern(String firstCutPattern) {
		this.firstCutPattern = firstCutPattern;
	}

	public BigDecimal getRestWidth() {
		return restWidth;
	}

	public void setRestWidth(BigDecimal restWidth) {
		this.restWidth = restWidth;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getPatternNum() {
		return patternNum;
	}

	public void setPatternNum(int amount) {
		this.patternNum = amount;
	}

	public String getSecondCutPattern() {
		StringBuilder sb = new StringBuilder();
		if(!StringUtil.Empty(this.secondCutPattern)){
			return this.secondCutPattern;
		}
		for (ColPattern colPat : colPatterns) {
			sb.append(ArithmeticUtil.trim(colPat.getColWidth())).append(PLUS).append(colPat.getColNum()).append(" "); //$NON-NLS-1$
		}
		secondCutPattern = sb.toString();
		return secondCutPattern;
	}

	public void setSecondCutPattern(String secondCutPattern) {
		this.secondCutPattern = secondCutPattern;
	}

	
	public Object[] getColumns(){
		Object[] columns = new Object[7];
		columns[0] = this.profileName;
		columns[1] = this.width;
		columns[2] = ArithmeticUtil.trim(firstCutPattern);
		columns[3] = this.restWidth;
		columns[4] = this.color;
		columns[5] = this.patternNum;
		columns[6] = this.getSecondCut();
		return columns;
	}
	
//	@Override
//	public String toString() {
//		StringBuilder sb = new StringBuilder();
//		sb.append(profileName).append(",").append(width).append(",").append(firstCutPattern).append(",") //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
//				.append(restWidth).append(",").append(color).append(Messages.ComponentOrderInfo_6).append(patternNum).append(",") //$NON-NLS-1$ //$NON-NLS-3$
//				.append(getSecondCut());
//		return sb.toString();
//	}

	private Object getSecondCut() {
		if (StringUtil.Empty(secondCutPattern)) {
			return getSecondCutPattern();
		} else {
			return this.secondCutPattern;
		}
	}

	private List<ColPattern> colPatterns = new ArrayList<ColPattern>();

//	public void setColPattern(ColPattern colPattern) {
//		colPatterns.add(colPattern);
//	}

	public void setColPattern(BigDecimal colWidth, int colNum) {
		colPatterns.add(new ColPattern(colWidth, colNum));
	}

	public BigDecimal calcuteFirstCutWidth(BigDecimal gap, BigDecimal head) {
		BigDecimal cuttedWidth = new BigDecimal(0);
		BigDecimal patternWidth = new BigDecimal(0);
//		int patternNum = getPatternNum();
		int nums = 0;
		for (ColPattern colPattern : colPatterns) {
			BigDecimal colWidth = colPattern.getColWidth();
			int colNum = colPattern.getColNum();
			nums += colNum;
			patternWidth = patternWidth.add(colWidth.multiply(new BigDecimal(colNum)));
		}
		BigDecimal cuttedGap = gap.multiply(new BigDecimal((nums - 1)));
//		patternWidth = patternWidth.multiply(new BigDecimal(patternNum));
		cuttedWidth = cuttedWidth.add(patternWidth).add(cuttedGap).add(head);
		return cuttedWidth;
	}
}

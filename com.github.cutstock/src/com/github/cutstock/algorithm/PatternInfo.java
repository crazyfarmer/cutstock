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

package com.github.cutstock.algorithm;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * contains all infor about patterns
 * 
 * e.g.
 * 
 * 2400×3 4300×2 1200×16 24
 * 
 * 
 * patternNum is 24
 * 
 * colPatterns size is 3
 * 
 * colPattern is 2400×3
 * 
 * @author crazyfarmer
 * 
 */
public class PatternInfo {

	// number of all patterns
	private int patternNum;
	private final String SPACE = " ";

	private List<ColPattern> colPatterns;

	public PatternInfo(int patternNum) {
		this.patternNum = patternNum;
	}

	public PatternInfo() {
	}

	// pattern value maybe contains 0.9999,but in fact it should be 1
	public void addPattern(double[] pattern, double[] size) {
		for (int k = 0; k < pattern.length; k++) {
			BigDecimal patEle = new BigDecimal(pattern[k]);
			patEle = patEle.setScale(0, BigDecimal.ROUND_HALF_UP);
			int num = patEle.intValue();
			BigDecimal len = new BigDecimal(size[k]);
			if (num > 0) {
				addColumn(len, num);
			}
		}
	}

	public void addColumn(BigDecimal rollWidth){
		addColumn(rollWidth,1);
	}
	/**
	 * 
	 * @param rollWidth
	 *            each cut roll width
	 * @param num
	 *            the Number of Relevant roll
	 */
	public void addColumn(BigDecimal rollWidth, int num) {
		if (colPatterns == null) {
			colPatterns = new ArrayList<ColPattern>();
		}
		for(ColPattern colPat:colPatterns){
			if(colPat.getColWidth().equals(rollWidth)){
				//update col num
				colPat.setColNum(colPat.getColNum()+num);
				return;
			}
		}
		colPatterns.add(new ColPattern(rollWidth, num));
	}

	// public double[] toDoubleArray(){
	//
	// }
	public int getPatternNum() {
		return this.patternNum;
	}

	public void setPatternNum(int patternNum) {
		this.patternNum = patternNum;
	}

	public List<ColPattern> getColPatterns() {
		return colPatterns;
	}

	public String getPatternString() throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (ColPattern colPattern : colPatterns) {
			sb.append(colPattern.toString()).append(SPACE);
		}
		return sb.toString();
	}

	// e.g. 2600×4
	@Override
	public boolean equals(Object obj) {
		PatternInfo patternInfo = (PatternInfo) obj;
		try {
			if (this.getPatternString().equals(patternInfo.getPatternString())) {
				return true;
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

}

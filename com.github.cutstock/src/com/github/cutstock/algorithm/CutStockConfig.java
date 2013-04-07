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

import java.math.BigDecimal;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 16, 2012
 */
public class CutStockConfig {

	public static final double RC_EPS = 1.0e-6;

	public static final BigDecimal DEFAULT_ROLL_WIDTH = new BigDecimal(6000);

	public static final BigDecimal DEFAULT_HEAD_WIDTH = new BigDecimal(100);

	public static final BigDecimal DEFAULT_GAP_WIDTH = new BigDecimal(10);

	// 涓嬫枡闀垮害
	protected BigDecimal rollWidth;
	// 浼犺涓殑鏂欏ご
	protected BigDecimal headWidth;
	// 浼犺涓殑缂濋殭,搴旇鏄痭-1涓紝鏈�悗涓�釜娌℃湁缂濋殭
	protected BigDecimal gapWidth;

	// 涓嬫枡鐩爣瀹藉害鐨勬暟缁勶紝涓巃mount鏁扮粍瀵瑰簲
	protected double[] widthArray;
	// 涓嬫枡鐩爣瀹藉害鐨勬暟閲�	
	protected double[] amountArray;

	public CutStockConfig() {

	}

	public CutStockConfig(int lenWidth, int headWidth, int gapWidth) {
		this.rollWidth = new BigDecimal(lenWidth);
		this.headWidth = new BigDecimal(headWidth);
		this.gapWidth = new BigDecimal(gapWidth);
	}

	public BigDecimal getHeadWidth() {
		return headWidth;
	}

	public void setHeadWidth(BigDecimal headWidth) {
		this.headWidth = headWidth;
	}

	public BigDecimal getGapWidth() {
		return gapWidth;
	}

	public void setGapWidth(BigDecimal gapWidth) {
		this.gapWidth = gapWidth;
	}

	public void setRollWidth(BigDecimal rollWidth) {
		this.rollWidth = rollWidth;
	}

	public void setWidthArray(double[] widthArray) {
		this.widthArray = widthArray;
	}

	public void setAmountArray(double[] amountArray) {
		this.amountArray = amountArray;
	}

	public BigDecimal getRollWidth() {
		return rollWidth;
	}

	public double[] getWidthArray() {
		return widthArray;
	}

	public double[] getAmountArray() {
		return amountArray;
	}

}

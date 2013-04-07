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
import com.github.cutstock.algorithm.CutStockResult;
import com.github.cutstock.algorithm.PatternInfo;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 22, 2012
 */
public final class CutstockResultInfo {
	private String profileName;
	private BigDecimal originalWidth;
	private BigDecimal headWidth;
	private BigDecimal gapWidth;
	private String color;

	private CutStockResult cutstockResult;
	private BigDecimal restWidth;

	private static final BigDecimal DEF_GAP_WIDTH = new BigDecimal(10);
	private static final BigDecimal DEF_HEAD_WIDTH = new BigDecimal(100);

	public BigDecimal getOriginalWidth() {
		return originalWidth;
	}

	public BigDecimal getGapWidth() {
		return this.gapWidth;
	}

	public CutStockResult getCutResult() {
		return cutstockResult;
	}

	public String getProfileName() {
		return this.profileName;
	}

	public String getColor() {
		return this.color;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public void setOriginalWidth(BigDecimal originalWidth) {
		this.originalWidth = originalWidth;
	}

	public void setHeadWidth(BigDecimal headWidth) {
		this.headWidth = headWidth;

	}

	public void setGapWidth(BigDecimal gapWidth) {
		this.gapWidth = gapWidth;
	}

	public void setCutResult(CutStockResult cutResult) {
		this.cutstockResult = cutResult;
	}

	public BigDecimal getHeadWidth() {
		return this.headWidth;
	}

}

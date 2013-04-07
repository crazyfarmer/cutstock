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

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfamrer</a>
 * 
 * 
 * 
 * 
 * 
 * 
 *          型材名称 型材编码 型材颜色 长度 数量 拼接方式 材料备注 用处 材料分类 
 *          55断桥窗框QY018001 QY018001 室内430 2 
 *          55断桥窗框QY018001 QY018001 室内 1150 2
 */
public class ProfileInfo {

	private String profileName;
	private String profileCodeData;
	private String profileColor;

	private List<WidthAmountPair> widthAmountPairs = new ArrayList<WidthAmountPair>();

	public ProfileInfo(String name, String codeData, String color,
			BigDecimal width, int amount) {
		this.profileName = name;
		this.profileCodeData = codeData;
		this.profileColor = color;
		setWidth2AmountPair(width, amount);
	}

	public ProfileInfo() {
	}

	public void setName(String name) {
		this.profileName = name;
	}

	public void setCodeName(String codeData) {
		this.profileCodeData = codeData;
	}

	public void setColor(String color) {
		this.profileColor = color;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof ProfileInfo)) {
			return false;
		}

		ProfileInfo target = (ProfileInfo) object;
		return this.profileName.equals(target.getName())
				&& this.profileCodeData.equals(target.getCodeData())
				&& this.profileColor.equals(target.getColor());
	}

	public String getColor() {
		return this.profileColor;
	}

	public String getCodeData() {
		return this.profileCodeData;
	}

	public String getName() {
		return this.profileName;
	}

	public void setWidth2AmountPair(BigDecimal width, int amount) {
		widthAmountPairs.add(new WidthAmountPair(width, amount));
	}

	public List<WidthAmountPair> getWidthAmountPairs() {
		return widthAmountPairs;
	}

	public class WidthAmountPair {
		private BigDecimal width;
		private int amount;

		public WidthAmountPair(BigDecimal width, int amount) {
			this.width = width;
			this.amount = amount;
		}

		public double getWidth() {
			return width.doubleValue();
		}

		public double getAmount() {
			return (double)amount;
		}

	}
}

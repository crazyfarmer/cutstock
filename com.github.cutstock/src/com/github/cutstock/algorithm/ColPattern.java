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

import com.github.cutstock.CutStockPlugin;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 22, 2012
 */
public class ColPattern  implements Cloneable{
	private static  String PLUS = Messages.ColPattern_plus;
	private int colNum;
	private BigDecimal colWidth;

	public ColPattern(BigDecimal rollWidth, int num) {
		this.colWidth = rollWidth;
		this.colNum = num;
	}

	public int getColNum() {
		return this.colNum;
	}

	public void setColNum(int colNum) {
		this.colNum = colNum;
	}

	public BigDecimal getColWidth() {
		return this.colWidth;
	}

	public void setColWidth(BigDecimal colWidth) {
		this.colWidth = colWidth;
	}

	public String getColString(){
		return String.valueOf(colWidth.doubleValue());
	}
	@Override
	public String toString() {
//		CutStockPlugin.info(PLUS);
//		try {
//			byte[] newPlusBytes = new String(PLUS.getBytes(), "ISO-8859-1").getBytes("UTF-8");
//			String newPlus = new String(newPlusBytes);
//			CutStockPlugin.info(newPlus);
//			PLUS = newPlus;
//			String plugStr= "×";
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
		return String.valueOf(colWidth.doubleValue()) + " " + colNum;
	}
	@Override
	public Object clone(){
		
		
		return colNum;
		
	}
}
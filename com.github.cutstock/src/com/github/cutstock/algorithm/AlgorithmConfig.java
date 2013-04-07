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

public abstract class AlgorithmConfig {

    public static final String ALGORITHM_A ="algorithm_a";
    public static final String ALGORITHM_B ="algorithm_b";
    private String algorithmName;
    private int[] lenRange;
    
	protected BigDecimal rollWidth;
	protected BigDecimal headWidth;
	protected BigDecimal gapWidth;
	protected double[] size;
	protected double[] amount;
	
	public String getAlgorithmName() {
	return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
	this.algorithmName = algorithmName;
    }

    public int[] getLenRange() {
	return lenRange;
    }

    public void setLenRange(int[] lenRange) {
	this.lenRange = lenRange;
    }

   	public BigDecimal getRollWidth() {
		return rollWidth;
	}

	public void setRollWidth(BigDecimal rollWidth) {
		this.rollWidth = rollWidth;
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

	public double[] getSize() {
		return this.size;
	}

	public double[] getAmount() {
		return this.amount;
	}
 

}

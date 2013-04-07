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

public abstract class CutStockProblem implements ICutStockProblem{

	protected double rollWidth;
	protected double[] widthArray;
	protected double[] amountArray;
	protected double gapWidth;

	/**
	 * 
	 * 
	 */
	@Override
	public void init(double len, double[] widthArray, double[] amountArray,
			double gapWidth) {
		// because we add n gapwidth,but in fact we should have n-1(last cutted
		// has no gap)
		this.rollWidth = len;
		this.widthArray = widthArray;
		this.amountArray = amountArray;
		this.gapWidth = gapWidth;
	}

}

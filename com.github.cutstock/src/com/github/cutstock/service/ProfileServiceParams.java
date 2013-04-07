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
package com.github.cutstock.service;

import java.util.Set;

import com.github.cutstock.algorithm.CutStockConfig;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Feb 11, 2013
 */
public class ProfileServiceParams {

	private String inputFile;
	private String outputFile;
	private String excelVersion;
	private Set<String> rules;

	private int lenWidth;
	private int headWidth;
	private int gapWidth;


	public int getLenWidth() {
		return lenWidth;
	}

	public void setLenWidth(int lenWidth) {
		this.lenWidth = lenWidth;
	}

	public int getHeadWidth() {
		return headWidth;
	}

	public void setHeadWidth(int headWidth) {
		this.headWidth = headWidth;
	}

	public int getGapWidth() {
		return gapWidth;
	}

	public void setGapWidth(int gapWidth) {
		this.gapWidth = gapWidth;
	}

	public String getInputFile() {
		return inputFile;
	}

	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public String getExcelVersion() {
		return excelVersion;
	}

	public void setExcelVersion(String excelVersion) {
		this.excelVersion = excelVersion;
	}

	public Set<String> getRules() {
		return rules;
	}

	public void setRules(Set<String> rules) {
		this.rules = rules;
	}

}

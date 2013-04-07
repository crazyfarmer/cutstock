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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CutStockResult {

	private List<PatternInfo> patterns;

	public int calculateUsedNum() {
		int num = 0;
		for (PatternInfo patternInfo : patterns) {
			num += patternInfo.getPatternNum();
		}
		return num;
	}

	public void addPatternInfo(PatternInfo patternInfo) {
		if (patterns == null) {
			patterns = new ArrayList<PatternInfo>();
		}
		PatternInfo pInfo = checkPatternInfo(patternInfo);
		if (pInfo != null) {
			pInfo.setPatternNum(pInfo.getPatternNum() + 1);
		} else {
			patterns.add(patternInfo);
		}
	}

	private PatternInfo checkPatternInfo(PatternInfo patternInfo) {
		for (PatternInfo pInfo : patterns) {
			if (pInfo.equals(patternInfo)) {
				return pInfo;
			}
		}
		return null;
	}

	public Iterator<PatternInfo> createIterator() {
		return new CutstockIterator(this);
	}

	class CutstockIterator implements Iterator {

		private List<PatternInfo> patternInfos;
		private int position;

		public CutstockIterator(CutStockResult result) {
			position = 0;
			patternInfos = result.getPatterns();
			if (patternInfos == null) {
				patternInfos = new ArrayList();
			}
		}

		@Override
		public boolean hasNext() {
			if ( position <  patternInfos.size()&&patternInfos.size()>0) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return patternInfos.get(position++);
		}

		@Override
		public void remove() {
			patternInfos.remove(--position );
		}
	}

	public List<PatternInfo> getPatterns() {
		return patterns;
	}
}

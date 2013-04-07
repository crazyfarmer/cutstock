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
import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 13, 2012
 */
public class Profiles {

	List<ProfileInfo> profileList = new ArrayList<ProfileInfo>();

	public void add(String name, String codeData, String color,
			BigDecimal width, int amount) {
		ProfileInfo profileInfo = null;
		if (notExsits(name, codeData, color)) {
			profileList.add(new ProfileInfo(name, codeData, color, width,
					amount));
		} else {
			profileInfo = getProfile(name, codeData, color);
			profileInfo.setWidth2AmountPair(width, amount);
		}
	}

	private ProfileInfo getProfile(String name, String codeData, String color) {
		for (ProfileInfo profile : profileList) {
			if (profile.getName().equals(name)
					&& profile.getCodeData().equals(codeData)
					&& profile.getColor().equals(color)) {
				return profile;
			}
		}
		return null;
	}

	private boolean notExsits(String name, String codeData, String color) {
		return getProfile(name, codeData, color) == null ? true : false;
	}

	public List<ProfileInfo> getProfileList() {
		return profileList;
	}

	public int getSize() {
		return profileList.size();
	}

	public Iterator<ProfileInfo> createIterator() {
		return new ProfileIterator(profileList);
	}

}

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

import java.util.Iterator;
import java.util.List;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 15, 2012
 */
public class ProfileIterator implements Iterator  {

	List<ProfileInfo> profileList;
	private int position = 0;

	public ProfileIterator(List<ProfileInfo> profileList) {
		this.profileList = profileList;
	}

	@Override
	public boolean hasNext() {
		if (profileList.size() <= position) {
			return false;
		}
		return true;
	}

	@Override
	public Object next() {
		return profileList.get(position++);
	}

	@Override
	public void remove() {
		// throw new Exception("unsupport method");
	}

}

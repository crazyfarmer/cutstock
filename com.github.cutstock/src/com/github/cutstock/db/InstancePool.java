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
package com.github.cutstock.db;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 3, 2012
 */
public class InstancePool {
	public static Object get(String className) {
		return getInstance()._get(className);
	}

	public static void put(String className, Object obj) {
		getInstance()._put(className, obj);
	}

	private static InstancePool getInstance() {
		if (instance == null) {
			synchronized (InstancePool.class) {
				if (instance == null) {
					instance = new InstancePool();
				}
			}
		}

		return instance;
	}

	private InstancePool() {
		_classPool = Collections.synchronizedMap(new HashMap<Object, Object>());
	}

	private Object _get(String className) {
		className = className.trim();

		Object obj = _classPool.get(className);

		if (obj == null) {
			try {
				obj = Class.forName(className).newInstance();
				_put(className, obj);
			}
			catch (ClassNotFoundException cnofe) {
				System.err.print(cnofe.getMessage());
			}
			catch (InstantiationException ie) {
				System.err.print(ie.getMessage());
			}
			catch (IllegalAccessException iae) {
				System.err.print(iae.getMessage());
			}
		}

		return obj;
	}

	private void _put(String className, Object obj) {
		_classPool.put(className, obj);
	}

	private static InstancePool instance;

	private Map _classPool;

}


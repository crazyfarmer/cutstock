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
package com.github.cutstock.utils;

import java.io.InputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.github.cutstock.CutStockPlugin;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 24, 2012
 */
public class ResourceUtil {

	public static final String DEF_RESOURCE = "resources/strings.xml";
	private String xpath = "//string[@name='%s']";
	public static ResourceUtil INSTANCE = new ResourceUtil();
	public Document document;

	private ResourceUtil() {
		SAXReader reader = new SAXReader();
		InputStream is = CutStockPlugin.class.getClassLoader()
				.getResourceAsStream(DEF_RESOURCE);

		try {
			if (is != null) {
				document = reader.read(is);
			} else {
				throw new Exception("resource not found");
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getNodeValueByName(String name) {
		System.out.println("xml node " + name);
		Node node = document.selectSingleNode(String.format(xpath, name));
		return node.getText();
	}

}

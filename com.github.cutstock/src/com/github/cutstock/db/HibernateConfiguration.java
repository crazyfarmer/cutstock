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

import java.io.InputStream;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;

import com.github.cutstock.CutStockPlugin;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 3, 2012
 */
public class HibernateConfiguration extends SessionConfigure {

	@Override
	public void init() {
//		ClassLoader cl = getClass().getClassLoader();
//		Configuration cfg = new Configuration();
//		InputStream is = cl.getResourceAsStream("hibernate.cfg.xml");
//		cfg.addInputStream(is);
//		setSessionFactory(cfg.buildSessionFactory());
		ClassLoader cl = CutStockPlugin.class.getClassLoader();
		Configuration cfg = new AnnotationConfiguration();
		setSessionFactory(cfg.configure(
				cl.getResource("resources/hibernate.cfg.xml")).buildSessionFactory());

	}

}

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

import java.util.List;

import com.github.cutstock.db.beans.CodeNameTransRule;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 9, 2012
 */
public interface TransRuleDao {

	List<CodeNameTransRule> getAllTransRules();

	List<String> getAllCategories();

	List<CodeNameTransRule> getTransRulesByCategory(String category);

	CodeNameTransRule findMergeOrderInfo(String codeName, String category);

	String findMatchName(String codeName, String category);

	void addRule(CodeNameTransRule rule);

	void delRule(CodeNameTransRule rule);

	void updateRule(CodeNameTransRule rule);
}

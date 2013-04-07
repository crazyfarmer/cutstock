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

import org.hibernate.Session;

import com.github.cutstock.db.beans.CodeNameTransRule;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 9, 2012
 */
public class TransRuleDaoImpl extends BaseDao implements TransRuleDao {

	public TransRuleDaoImpl() {
		super();
	}

	public TransRuleDaoImpl(String configClass) {
		super(configClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.cutstock.db.TransRuleDao#findMergeName(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public CodeNameTransRule findMergeOrderInfo(String codeName, String category) {
		String hql = "FROM CodeNameTransRule WHERE ( CODENAMEA=:codeNameA or CODENAMEB=:codeNameB ) and CATEGORY=:category";
		hibernateUtil.createQuery(hql);
		hibernateUtil.setParam("codeNameA", codeName);
		hibernateUtil.setParam("codeNameB", codeName);
		hibernateUtil.setParam("category", category);
		List<CodeNameTransRule> list = hibernateUtil.query();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return null;
		}
	}

	@Override
	public String findMatchName(String codeName, String category) {
		return null;
	}

	@Override
	public List<String> getAllCategories() {
		Session session = hibernateUtil.openSession();
		String sql = "SELECT distinct category FROM CodeNameTransRule";
		return session.createSQLQuery(sql).list();
	}

	@Override
	public List<CodeNameTransRule> getTransRulesByCategory(String category) {
		String hql = "FROM CodeNameTransRule WHERE CATEGORY=:category";
		hibernateUtil.createQuery(hql);
		hibernateUtil.setParam("category", category);
		return hibernateUtil.query();
	}

	@Override
	public List<CodeNameTransRule> getAllTransRules() {
		String hql = "FROM CodeNameTransRule";
		return hibernateUtil.createQuery(hql).list();
	}

	@Override
	public void addRule(CodeNameTransRule rule) {
		hibernateUtil.insert(rule);
	}

	@Override
	public void delRule(CodeNameTransRule rule) {
		hibernateUtil.delete(rule);
	}

	@Override
	public void updateRule(CodeNameTransRule rule) {
		hibernateUtil.update(rule);
	}
}

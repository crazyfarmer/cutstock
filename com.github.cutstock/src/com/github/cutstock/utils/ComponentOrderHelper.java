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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.model.ComponentOrderInfo;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 17, 2012
 */
public final class ComponentOrderHelper {

	private static final Map<Object, Map> orderMatchCaches = new TreeMap();
	private static List<ComponentOrderInfo> orderListCopy;

	private Set<String> categories;

	public void setRuleCategories(Set<String> categories) {
		this.categories = categories;
	}

	public Set<String> getRuleCategories() {
//		if (categories == null || categories.size() == 0) {
//			categories = transRuleDao.getAllCategories();
//		}
		return categories;
	}

	public List<ComponentOrderInfo> genMergedOrderList(List<ComponentOrderInfo> orderList) {
		orderListCopy = new ArrayList(orderList);
		cacheOrderList(orderList);
		List<ComponentOrderInfo> newOrderList = genMergeOrderList0(orderList);
		return newOrderList;
	}

	private List<ComponentOrderInfo> genMergeOrderList0(List<ComponentOrderInfo> orderList) {
		// trasverse orderList
		List newOrderList = new ArrayList();
		List delOrder = new ArrayList();
		for (Iterator<ComponentOrderInfo> it = orderListCopy.iterator(); it.hasNext();) {
			ComponentOrderInfo curOrder = it.next();
			if(delOrder.contains(curOrder.getProfileName())){
				it.remove();
				continue;
			}
			CodeNameTransRule transRule = findMergeOrderNameFromDB(curOrder.getProfileName());
			if (transRule != null) {
				if(curOrder.getProfileName().equals(transRule.getCodeNameA())){
					delOrder.add(transRule.getCodeNameB());
				}else {
					delOrder.add(transRule.getCodeNameA());
				}
				curOrder.setProfileName(transRule.getMergedCodeName());
				//remove from 
			}
			
			// if (transRule == null) {
			// newOrderList.add(curOrder);
			// } else {
			// ComponentOrderInfo mergeOrderInfo =
			// findMergeOrderFromInputOrder(orderList,
			// curOrder.getProfileName());
			// if (mergeOrderInfo != null) {
			// ComponentOrderInfo newOrdrInfo = doMerge(curOrder,
			// mergeOrderInfo);
			// newOrderList.add(newOrdrInfo);
			// }
			// }
		}
		return orderListCopy;
	}

	private ComponentOrderInfo doMerge(ComponentOrderInfo curOrder, ComponentOrderInfo orderInfo) {
		ComponentOrderInfo newOrderInfo = new ComponentOrderInfo();
		newOrderInfo.setProfileName(curOrder.getProfileName() + "+" + orderInfo.getProfileName());
		newOrderInfo.setOriginWidth(curOrder.getOriginWidth());
		newOrderInfo.setFirstCutPattern(curOrder.getFirstCutPattern());
		newOrderInfo.setRestWidth(curOrder.getRestWidth());
		newOrderInfo.setColor(curOrder.getColor() + "+" + orderInfo.getColor());
		newOrderInfo.setPatternNum(curOrder.getPatternNum());
		newOrderInfo.setSecondCutPattern(curOrder.getSecondCutPattern());
		return newOrderInfo;

	}

	private static Map<String, ComponentOrderInfo> tempCache;

	private static ComponentOrderInfo findMergeOrderFromInputOrder(List orderList, String mergeName) {
		if (tempCache == null) {
			Map<String, ComponentOrderInfo> cache = getValue(orderList);
			tempCache = new HashMap(cache);
		}
		return tempCache.remove(mergeName);
	}

	private TransRuleDao transRuleDao;

	public TransRuleDao getTransRuleDao() {
		return transRuleDao;
	}

	public void setTransRuleDao(TransRuleDao transRuleDao) {
		this.transRuleDao = transRuleDao;
	}

	private CodeNameTransRule findMergeOrderNameFromDB(String orderName) {
		CodeNameTransRule transRule = null;
		for (String ruleCategory : getRuleCategories()) {
			transRule = transRuleDao.findMergeOrderInfo(orderName, ruleCategory);
			if (transRule != null) {
				break;
			}
		}
		return transRule;
	}

	private static Map<String, ComponentOrderInfo> getValue(List<ComponentOrderInfo> orderList) {
		return orderMatchCaches.get(orderList.toString());
	}

	private void cacheOrderList(List<ComponentOrderInfo> list) {
		if (!shouldCache(list)) {
			return;
		}
		Map<String, ComponentOrderInfo> orderMap = new HashMap();
		for (Iterator<ComponentOrderInfo> it = list.iterator(); it.hasNext();) {
			ComponentOrderInfo orderInfo = it.next();
			orderMap.put(orderInfo.getProfileName(), orderInfo);
		}
		orderMatchCaches.put(list.toString(), orderMap);
	}

	private boolean shouldCache(List<ComponentOrderInfo> orderList) {
		if (orderMatchCaches.get(orderList.toString()) != null) {
			return false;
		} else {
			return true;
		}
		// return orderMatchCaches.containsKey(orderList);
	}
}

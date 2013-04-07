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
package com.github.cutstock.db.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Nov 16, 2012
 */

@Entity
@Table(name = "CodeNameTransRule")
public class CodeNameTransRule {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "codeNameA")
	private String codeNameA;

	@Column(name = "codeNameB")
	private String codeNameB;

	@Column(name = "mergedCodeName")
	private String mergedCodeName;

	@Column(name = "category")
	private String category;

	public CodeNameTransRule() {

	}

	public CodeNameTransRule(String codeNameA, String codeNameB, String newName, String category) {
		this.codeNameA = codeNameA;
		this.codeNameB = codeNameB;
		this.mergedCodeName = newName;
		this.category = category;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCodeNameA() {
		return codeNameA;
	}

	public void setCodeNameA(String codeNameA) {
		this.codeNameA = codeNameA;
	}

	public String getCodeNameB() {
		return codeNameB;
	}

	public void setCodeNameB(String codeNameB) {
		this.codeNameB = codeNameB;
	}

	public String getMergedCodeName() {
		return mergedCodeName;
	}

	public void setMergedCodeName(String mergedCodeName) {
		this.mergedCodeName = mergedCodeName;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append(id).append(" ").append(codeNameA).append(" ").append(codeNameB).append(" ")
				.append(mergedCodeName).append(" ").append(category).append(" \n").toString();
	}

}

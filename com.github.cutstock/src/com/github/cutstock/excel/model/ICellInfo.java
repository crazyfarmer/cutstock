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
package com.github.cutstock.excel.model;

import com.github.cutstock.excel.model.rect.IExcelRectangle;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 8, 2013
 */
public interface ICellInfo {

	CellType getCellType();

	Object[] getColumns();

	IExcelRectangle getRect();

	byte[] getImage();
}

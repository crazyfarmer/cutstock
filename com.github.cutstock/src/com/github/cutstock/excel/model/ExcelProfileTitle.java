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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.github.cutstock.excel.model.rect.IExcelRectangle;
import com.github.cutstock.utils.ResourceUtil;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 25, 2012
 */
public class ExcelProfileTitle extends ExcelTitle {

	private byte[] logo;
	
	public ExcelProfileTitle(IExcelRectangle rect){
		this(ResourceUtil.INSTANCE.getNodeValueByName("profile_title"),rect);
	}
	
	public ExcelProfileTitle(String titleName, IExcelRectangle rect) {
		this.titleText = titleName;
		this.rect = rect;
		initDefaultSettings();
	}

	private void initDefaultSettings() {
//		style = ExcelUtil.getStyle(CellType.TITLE.type());
		InputStream is = null;
		try {
			is = getClass().getClassLoader().getResourceAsStream("resources/excel_logo.jpg");
			logo = IOUtils.toByteArray(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			IOUtils.closeQuietly(is);
		}
	}

	@Override
	public CellType getCellType() {
		return CellType.TITLE;
	}

	@Override
	public byte[] getImage(){
		return logo;
	}

}

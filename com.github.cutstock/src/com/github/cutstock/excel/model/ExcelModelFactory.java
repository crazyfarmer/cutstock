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

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.github.cutstock.excel.model.rect.IExcelRectangle;
import com.github.cutstock.excel.model.rect.ProfileCellRect;
import com.github.cutstock.excel.model.rect.ProfileSubTitleRect;
import com.github.cutstock.excel.model.rect.ProfileTitleRect;
import com.github.cutstock.excel.utils.ExcelUtil;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Dec 25, 2012
 */
public class ExcelModelFactory {

	public static IExcelRectangle createTitleRect(int startCol, int endCol, int startLine, int endLine) {
		return new ProfileTitleRect(startCol, endCol, startLine, endLine);
	}

	public static IExcelRectangle createSubTitleRect(int startCol, int endCol, int startLine, int endLine) {
		return new ProfileSubTitleRect(startCol, endCol, startLine, endLine);
	}

	public static ExcelTitle createMainTitle(String titleName, IExcelRectangle titleRect) {
		ExcelTitle profileTitle = new ExcelProfileTitle(titleName, titleRect);
		return profileTitle;
	}

	public static ExcelTitle createMainTitle(IExcelRectangle titleRect) {
		ExcelTitle profileTitle = new ExcelProfileTitle(titleRect);
		return profileTitle;
	}

	public static ExcelTitle createSubTitle(IExcelRectangle titleRect) {
		ExcelTitle profileTitle = new ExcelSubTitle(titleRect);
		return profileTitle;
	}
	public static ICellInfo createCells(String columnsStr, IExcelRectangle cellRect) {
		Object[] columnsArr = (Object[])columnsStr.split(",");
		return createCells(columnsArr,cellRect); 
	}
	
	public static ICellInfo createCells(Object[] columns, IExcelRectangle cellRect) {
		ProfileCells cellInfo = new ProfileCells(columns, cellRect);
		return cellInfo;
	}

	public static IExcelRectangle createCellRect(int startCol, int endCol, int startLine, int endLine) {
		return new ProfileCellRect(startCol, endCol, startLine, endLine);
	}

	// public static ICellInfo createColumns(IExcelRectangle titleRect){
	// ICellInfo columns
	// }
}

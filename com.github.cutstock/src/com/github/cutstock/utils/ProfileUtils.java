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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.dialogs.MessageDialog;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.model.ColumnType;
import com.github.cutstock.model.Profiles;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Oct 15, 2012
 */
public class ProfileUtils {

	public static Profiles parseFile(String filePath) {
		Workbook workbook = null;
		Sheet sheet = null;
		// CutStockPlugin.getLogger().log(new Status(0,
		// CutStockPlugin.PLUGIN_ID, filePath));
		InputStream is = null;
		try {
			is = new FileInputStream(filePath);
			if (filePath.toLowerCase().endsWith(".xls")) {
				workbook = new HSSFWorkbook(is);
			} else {
				workbook = new XSSFWorkbook(is);
			}

		} catch (IOException e) {
			MessageDialog.openError(null, "ERROR",
					"无法解析EXCEL文件,该文件已经损坏或不是一个标准的Excel文件");
		} finally {
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		sheet = workbook.getSheetAt(0);
		int sheetRowNum = sheet.getLastRowNum();
		Profiles proflies = new Profiles();
		for (int i = 1; i <= sheetRowNum; i++) {
			Row currentRow = sheet.getRow(i);
			if (currentRow != null) {
				Cell cell = currentRow.getCell(ColumnType.PROFILE_NAME);
				String name = (String) getCellValue(cell);
				if (StringUtil.Empty(name)) {
					break;
				}
				cell = currentRow.getCell(ColumnType.PROFILE_CODE_DATA);
				String codeData = (String) getCellValue(cell);
				cell = currentRow.getCell(ColumnType.PROFILE_COLOR);
				String color = (String) getCellValue(cell);

				cell = currentRow.getCell(ColumnType.PROFILE_WIDTH);
				double width = (Double) getCellValue(cell);
				cell = currentRow.getCell(ColumnType.PROFILE_AMOUNT);
				int amount = ((Double) getCellValue(cell)).intValue();

				proflies.add(name, codeData, color, new BigDecimal(width),
						amount);
			}
		}
		return proflies;
	}

	public static Object getCellValue(Cell cell) {
		if (cell != null) {
			switch (cell.getCellType()) {
			case HSSFCell.CELL_TYPE_FORMULA:
				return cell.getCellFormula();
			case HSSFCell.CELL_TYPE_NUMERIC:
				return cell.getNumericCellValue();
			case HSSFCell.CELL_TYPE_STRING:
				return "" + cell.getRichStringCellValue();
			default:
				return "";// 没有匹配的返回empty
			}
		}
		return "";
	}

}

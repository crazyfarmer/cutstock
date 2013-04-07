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
package com.github.cutstock.ui.views;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.ExceptionHandler;

import com.github.cutstock.ui.views.dataset.ViewDataTable;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 22, 2013
 */
public class ViewManager {

	public static void showView(String viewId) {
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().showView(viewId);
		} catch (PartInitException e) {
			// ExceptionHandler.process(e);
		}

	}

	public static void refreshView(String viewId) {
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) {
			return;
		}

		ViewDataTable view = (ViewDataTable) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView(viewId);
		if (view != null) {
			view.refresh();
		}
	}

	public static ViewDataTable getView(String tableViewID) {
		if (PlatformUI.getWorkbench().getActiveWorkbenchWindow() == null) {
			return null;
		}

		ViewDataTable view = (ViewDataTable) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
				.findView(tableViewID);
		return view;

	}

}
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
package com.github.cutstock.ui.editors;

import java.util.HashSet;
import java.util.List;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.EditorPart;

import com.github.cutstock.ui.views.ViewManager;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 19, 2013
 */
public abstract class AbstractEditor extends EditorPart {

	protected String tableViewID = "";
	protected boolean isDirty;

	protected void superviceControl(Text text) {
		superviceControl(text, 0);
	}

	protected void superviceControl(Text text, int limit) {
		if (limit > 0) {
			text.setTextLimit(limit);
		}
		text.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkDirty();
			}
		});
	}

	protected void checkDirty() {
		if (!isDirty) {
			isDirty = !isDirty;
			firePropertyChange(EditorPart.PROP_DIRTY);
		}
	}

	protected void refreshTable(Object tableElement, Object treeElement){
		ViewManager.getView(tableViewID).refresh(tableElement, treeElement);
	}
	protected void updateTable(){
//		ViewManager.getView(tableViewID).update(tableElements, treeElements);
	}
	
	protected void refreshViews(List<String> categories, List elements){
		ViewManager.getView(tableViewID).setInputs(new HashSet(categories), elements);
	}
	protected void refreshView() {
		ViewManager.refreshView(tableViewID);
	}

}

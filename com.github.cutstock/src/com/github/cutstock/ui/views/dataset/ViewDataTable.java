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
package com.github.cutstock.ui.views.dataset;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 22, 2013
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.ViewPart;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.ui.actions.CopyViewDataAction;
import com.github.cutstock.ui.actions.CutViewDataAction;
import com.github.cutstock.ui.actions.DeleteDataAction;
import com.github.cutstock.ui.actions.NewEditorAction;
import com.github.cutstock.ui.actions.PasteViewDataAction;
import com.github.cutstock.ui.editors.CallEditor;

public abstract class ViewDataTable extends ViewPart {

	protected Composite top;
	protected TableViewer tableViewer;
	protected ViewDataTable me;
	protected CategoryTreeViewer treeViewer;
	protected String editor = "";
	protected NewEditorAction newEditorAction;
	private CopyViewDataAction copyViewDataAction;
	private PasteViewDataAction pasteViewDataAction;
	private CutViewDataAction cutViewDataAction;

	protected void createToolBar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.FLAT);
		GridDataFactory.fillDefaults().span(2, 1).align(SWT.BEGINNING, SWT.CENTER).applyTo(toolBar);
		ToolBarManager tbm = new ToolBarManager(toolBar);
		copyViewDataAction = new CopyViewDataAction();
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.COPY.getId(), copyViewDataAction);
		pasteViewDataAction = new PasteViewDataAction();
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.PASTE.getId(), pasteViewDataAction);
		cutViewDataAction = new CutViewDataAction();
		getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.CUT.getId(), cutViewDataAction);
		tbm.add(new DeleteDataAction());
		tbm.add(newEditorAction);
//		tbm.add(copyViewDataAction);
//		tbm.add(pasteViewDataAction);
//		tbm.add(cutViewDataAction);
		tbm.update(true);
	}

	protected void setListener() {
		me = this;
		tableViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
				IWorkbenchPage page = workbenchWindow.getActivePage();
				if (page != null) {
					// Activate the part of the workbench page.
					if (!page.getActivePart().equals(me))
						page.activate(me);
				}
			}
		});
	}

	protected void hookCopyAndCutAction() {
		copyViewDataAction.setViewer(tableViewer);
		cutViewDataAction.setViewer(tableViewer);
		pasteViewDataAction.setViewer(tableViewer);
	}

	protected void hookDoubleClickCommand() {
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				IHandlerService handlerService = (IHandlerService) getSite().getService(IHandlerService.class);
				ICommandService commandService = (ICommandService) getSite().getService(ICommandService.class);

				try {
					Command callEditor = commandService.getCommand("com.github.cutstock.callEditor");
					Map<String, String> params = new HashMap<String, String>();
					params.put(CallEditor.CALL_PARAM, editor);
					ParameterizedCommand parameterizedCommand = ParameterizedCommand
							.generateCommand(callEditor, params);
					handlerService.executeCommand(parameterizedCommand, null);

				} catch (Exception e) {
					CutStockPlugin.logError("Editor not found: " + editor, e);
				}
			}
		});
	}

	abstract void setEditorName(String editor);

	public void setTableFilter(ViewerFilter viewerFilter) {
		tableViewer.addFilter(viewerFilter);
		tableViewer.refresh();
	}

	@Override
	public void setFocus() {
		if (top != null)
			top.setFocus();
	}

	
	public void setInputs(Set<String> categories,List elements){
		tableViewer.setInput(elements);
		treeViewer.setCategoriesInput(categories);
	}
	public void update(Object[] tableElements, Object[] treeElements) {
		if (tableViewer != null) {
			tableViewer.update(tableElements, null);
		}
		if (treeViewer != null) {
			treeViewer.update(treeElements, null);
		}
	}

	public void refresh(Object tableElement, Object treeElement) {
		if (tableViewer != null) {
			tableViewer.refresh(tableElement);
		}
		if (treeViewer != null) {
			treeViewer.refresh(treeElement);
		}
	}

	public void refresh() {
		if (tableViewer != null) {
			tableViewer.refresh();
		}
		if (treeViewer != null) {
			treeViewer.refresh();
		}
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}

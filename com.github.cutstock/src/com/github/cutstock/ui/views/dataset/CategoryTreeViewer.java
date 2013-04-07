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

import java.util.ArrayList;
import java.util.Set;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.CellLabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.widgets.Composite;

import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.ui.adapter.AbstractAdapter;


/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 22, 2013
 */
public class CategoryTreeViewer extends TreeViewer {
	private ViewDataTable viewDataTable;
	private CategoryFilter filter;
	protected TreeParent root;
	protected TreeParent all;

	public CategoryTreeViewer(Composite parent) {
		super(parent);
		root = new TreeParent(""); //$NON-NLS-1$
		all = new TreeParent("all"); //$NON-NLS-1$
		root.addChild(all);

		this.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				ISelection selection = event.getSelection();

				// Get the selection
				if (selection != null && selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection) selection).getFirstElement();
					if (obj != null) {
						filter.setFilterString((TreeObject) obj);
						viewDataTable.refresh();
					}
				}
			}
		});

	}

	class CategoryFilter extends ViewerFilter {

		private TreeObject categoryItem;

		public CategoryFilter() {

		}

		public void setFilterString(TreeObject string) {
			categoryItem = string;
		}

		@Override
		public boolean select(Viewer viewer, Object parentElement, Object element) {
			if (categoryItem == null || categoryItem.getName().equals(Messages.CategoryTreeViewer_2)) {
				return true;
			}
			if (categoryItem.getName().equals(((CodeNameTransRule) element).getCategory())) {
				return true;
			}
			return false;
		}
	}

	private CategoryTreeViewer me = this;
	private Set<String> inputElement;

	class TreeObject {
		private String name;
		private TreeParent parent;

		public TreeObject(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

	}

	class TreeParent extends TreeObject {

		private ArrayList<TreeObject> children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList<TreeObject>();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return children.toArray(new TreeObject[children.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}

		public void clear() {
			children.clear();
		}

	}

	public ViewDataTable getViewDataTable() {
		return viewDataTable;
	}

	public void setViewDataTable(ViewDataTable viewDataTable) {
		this.viewDataTable = viewDataTable;
		filter = new CategoryFilter();
		viewDataTable.setTableFilter(filter);
	}

	public void setCategoriesInput(Set<String> input) {
		this.inputElement = input;
		this.setContentProvider(new ViewContentProvider());
		this.setLabelProvider(new ViewLabelProvider());
		this.setInput(root);
		this.expandToLevel(2);

	}

	class ViewLabelProvider extends CellLabelProvider {

		String getText(Object obj) {
			if (obj instanceof TreeObject) {
				return ((TreeObject) obj).getName();
			}
			return obj.toString();
		}

		@Override
		public void update(ViewerCell cell) {
			cell.setText(getText(cell.getElement()));
		}

	}

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {

		@Override
		public void dispose() {

		}

		@Override
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		}

		@Override
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof TreeParent) {
				return ((TreeParent) parentElement).getChildren();
			}
			return new Object[0];
		}

		@Override
		public Object getParent(Object element) {
			return null;
		}

		@Override
		public boolean hasChildren(Object element) {
			if (element instanceof TreeParent) {
				return ((TreeParent) element).hasChildren();
			}
			return false;
		}

		@Override
		public Object[] getElements(Object parent) {
			int count = 0;
			if (parent == root) {
				clear();
				for (String treeItem : (Set<String>) inputElement) {
					all.addChild(new TreeObject(treeItem));
				}
			}
			count = ((Set) inputElement).size();
			if (count != 0) {
				me.getTree().setVisible(true);
				GridDataFactory.fillDefaults().hint(150, -1).grab(false, true).applyTo(me.getTree());
				me.getTree().getParent().layout(true);
			} else {
				me.getTree().setVisible(false);
				GridDataFactory.fillDefaults().hint(1, -1).grab(false, true).applyTo(me.getTree());
				me.getTree().getParent().layout(true);
			}
			return getChildren(parent);
		}
	}

	private void clear() {
		if (all != null)
			all.clear();

		root.clear();

		if (all != null)
			root.addChild(all);

	}
}
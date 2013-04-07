package com.github.cutstock.ui.views.dataset;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import com.github.cutstock.db.HibernateConfiguration;
import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.TransRuleDaoImpl;
import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.ui.actions.NewTransRuleAction;

public class ViewTransRuleTable extends ViewDataTable {

	public static final String ID = "com.github.cutstock.ui.views.dataset.viewTransRuleTable"; //$NON-NLS-1$

	protected String ruleColumns[];

	@Override
	public void createPartControl(Composite parent) {
		newEditorAction = new NewTransRuleAction();
		top = new Composite(parent, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).applyTo(top);
		GridDataFactory.swtDefaults().align(SWT.FILL, SWT.FILL).applyTo(top);

		setEditorName("TransRule"); //$NON-NLS-1$
		createToolBar(top);

		treeViewer = new CategoryTreeViewer(top);
		GridDataFactory.swtDefaults().hint(10, -1).applyTo(treeViewer.getTree());

		// treeViewer.setLabelProvider(new)
		TransRuleDao transRuleDao = new TransRuleDaoImpl(HibernateConfiguration.class.getName());
		treeViewer.setCategoriesInput(new HashSet(transRuleDao.getAllCategories()));
		treeViewer.expandAll();
		treeViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {

			}

		});

		// Mark the columns that are used by the search function.
		ruleColumns = new String[6];
		ruleColumns[0] = Messages.ViewTransRuleTable_0;
		ruleColumns[1] = Messages.ViewTransRuleTable_1;
		ruleColumns[2] = Messages.ViewTransRuleTable_2;
		ruleColumns[3] = Messages.ViewTransRuleTable_3;
		ruleColumns[4] = Messages.ViewTransRuleTable_4;
		ruleColumns[5] = Messages.ViewTransRuleTable_5;

		tableViewer = new TableViewer(top, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		Table table = tableViewer.getTable();
		GridDataFactory.fillDefaults().grab(true, true).applyTo(table);
		for (int i = 0; i < ruleColumns.length; i++) {
			new TableColumn(table, SWT.LEFT).setText(ruleColumns[i]);
			table.getColumn(i).pack();
		}
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		tableViewer.setColumnProperties(ruleColumns);
		tableViewer.setContentProvider(new TableViewerContentProvider());
		tableViewer.setLabelProvider(new TableViewerLabelProvider());
		tableViewer.setInput(transRuleDao.getAllTransRules());
		setListener();
		getSite().setSelectionProvider(tableViewer);
		treeViewer.setViewDataTable(this);
		hookCopyAndCutAction();
		hookDoubleClickCommand();
		tableViewer.setData("TYPE", "TRANSRULE"); //$NON-NLS-1$ //$NON-NLS-2$
	}
	
	
	class TableViewerContentProvider implements IStructuredContentProvider {
		@SuppressWarnings("unchecked")
		public Object[] getElements(Object element) {
			if (element instanceof List)
				return ((List) element).toArray();
			else
				return new Object[0];

		}

		public void dispose() {
		}

		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		}
	}

	class TableViewerLabelProvider implements ITableLabelProvider {
		public String getColumnText(Object element, int col) {
			CodeNameTransRule transRuleModel = (CodeNameTransRule) element;
			switch (col) {
			case 0:
				return String.valueOf(transRuleModel.getId());
			case 1:
				return transRuleModel.getCodeNameA();
			case 2:
				return transRuleModel.getCodeNameB();
			case 3:
				return transRuleModel.getMergedCodeName();
			case 4:
				return ""; //$NON-NLS-1$
			case 5:
				return transRuleModel.getCategory();
			}
			return null;
		}

		public Image getColumnImage(Object element, int columnIndex) {
			return null;
		}

		public void addListener(ILabelProviderListener listener) {
		}

		public void dispose() {
		}

		public boolean isLabelProperty(Object element, String property) {
			return false;
		}

		public void removeListener(ILabelProviderListener listener) {
		}
	}

	@Override
	void setEditorName(String editor) {
		this.editor = editor;
	}
}

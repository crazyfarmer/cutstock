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

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.TransRuleDaoImpl;
import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.ui.adapter.TransRuleAdapter;
import com.github.cutstock.ui.views.dataset.ViewTransRuleTable;
import com.github.cutstock.utils.StringUtil;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 19, 2013
 */
public class TransRuleEditor extends AbstractEditor {

	public static String ID = "com.github.cutstock.ui.editors.transruleEditor"; //$NON-NLS-1$
	private TransRuleDao ruleDao = new TransRuleDaoImpl();
	private CodeNameTransRule transRule;

	@Override
	public void doSave(IProgressMonitor monitor) {
		String index = txtIndex.getText();
		transRule.setCodeNameA(txtSourceA.getText());
		transRule.setCodeNameB(txtSourceB.getText());
		transRule.setMergedCodeName(txtMergedName.getText());
		transRule.setCategory(txtCategory.getText());

		this.isDirty = false;
		this.firePropertyChange(PROP_DIRTY);
		if (!StringUtil.Empty(index)) {
			// update
			ruleDao.updateRule(transRule);
		} else {
			// insert
			ruleDao.addRule(transRule);

		}

		refreshViews(ruleDao.getAllCategories(), ruleDao.getAllTransRules());
		refreshView();

	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		tableViewID = ViewTransRuleTable.ID;
		setSite(site);
		setInput(input);
		transRule = (CodeNameTransRule) ((TransRuleAdapter) input).getModel();
	}

	@Override
	public boolean isDirty() {
		return isDirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	// ////////////////////////////////////////////////////////////////////////
	// ///////////////////////////////UI Widgets///////////////////////////////
	// ///////////////////////////////////////////////////////////////////////
	private Display display;
	private Composite top;
	private Text txtIndex;
	private Text txtSourceA;
	private Text txtSourceB;
	private Text txtMergedName;
	private Text txtCategory;

	@Override
	public void createPartControl(Composite parent) {
		display = parent.getDisplay();

		top = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(top);

		Group ruleDescGroup = new Group(top, SWT.NONE);
		// GridLayoutFactory.swtDefaults().numColumns(2).applyTo(ruleDescGroup);
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		ruleDescGroup.setLayout(gridLayout);
		// GridDataFactory.swtDefaults().grab(false,
		// false).applyTo(ruleDescGroup);
		ruleDescGroup.setText(Messages.TransRuleEditor_addNewRule);

		Label labelIndex = new Label(ruleDescGroup, SWT.NONE);
		labelIndex.setText(Messages.TransRuleEditor_index);
		// GridDataFactory.swtDefaults().grab(false, false).applyTo(labelIndex);
		labelIndex.setLayoutData(new GridData(GridData.BEGINNING, GridData.CENTER, false, false));

		txtIndex = new Text(ruleDescGroup, SWT.BORDER);
		// GridDataFactory.swtDefaults().applyTo(txtIndex);
		GridData gridData = new GridData();
		gridData.minimumWidth = 70;
		gridData.widthHint = 100;
		gridData.grabExcessHorizontalSpace = false;
		txtIndex.setLayoutData(gridData);

		String id = String.valueOf(transRule.getId());
		if (!StringUtil.Empty(id) && id != "null") { //$NON-NLS-1$
			txtIndex.setText(id);
		}
		txtIndex.setEnabled(false);

		Label labelSourceA = new Label(ruleDescGroup, SWT.NONE);
		labelSourceA.setText(Messages.TransRuleEditor_souceA);
		GridDataFactory.swtDefaults().applyTo(labelSourceA);

		txtSourceA = new Text(ruleDescGroup, SWT.BORDER);
//		GridDataFactory.swtDefaults().applyTo(txtSourceA);
		txtSourceA.setLayoutData(gridData);
		String codeNameA = transRule.getCodeNameA();
		if (!StringUtil.Empty(codeNameA)) {
			txtSourceA.setText(codeNameA);
		}

		Label labelSourceB = new Label(ruleDescGroup, SWT.NONE);
		labelSourceB.setText(Messages.TransRuleEditor_sourceB);
		GridDataFactory.swtDefaults().applyTo(labelSourceB);

		txtSourceB = new Text(ruleDescGroup, SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(txtSourceB);
		String codeNameB = transRule.getCodeNameB();
		if (!StringUtil.Empty(codeNameB)) {
			txtSourceB.setText(transRule.getCodeNameB());
		}
		txtSourceB.setLayoutData(gridData);
		
		Label labelSourceCombo = new Label(ruleDescGroup, SWT.NONE);
		labelSourceCombo.setText(Messages.TransRuleEditor_newSource);
		GridDataFactory.swtDefaults().applyTo(labelSourceCombo);

		txtMergedName = new Text(ruleDescGroup, SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(txtMergedName);
		String mergedCodeName = transRule.getMergedCodeName();
		if (!StringUtil.Empty(mergedCodeName)) {
			txtMergedName.setText(mergedCodeName);
		}
		txtMergedName.setLayoutData(gridData);
		
		Label labelCate = new Label(ruleDescGroup, SWT.NONE);
		labelCate.setText(Messages.TransRuleEditor_ruleCategory);
		GridDataFactory.swtDefaults().applyTo(labelCate);
		txtCategory = new Text(ruleDescGroup, SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(txtCategory);
		String category = transRule.getCategory();
		if (!StringUtil.Empty(category)) {
			txtCategory.setText(category);
		}
		txtCategory.setLayoutData(gridData);
		
		superviceControl(txtIndex, 0);
		superviceControl(txtSourceA, 0);
		superviceControl(txtSourceB, 0);
		superviceControl(txtMergedName, 0);
		superviceControl(txtCategory, 20);
	}

	@Override
	public void setFocus() {

	}

}

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
package com.github.cutstock.ui.actions;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 27, 2013
 */
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.ui.adapter.TransRuleAdapter;
import com.github.cutstock.ui.editors.TransRuleEditor;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class NewTransRuleAction extends NewEditorAction {

	public final static String ACTIONTEXT = "NEW RULE"; //$NON-NLS-1$

	public NewTransRuleAction() {
		super(ACTIONTEXT);

		setToolTipText(Messages.NewTransRuleAction_add_newtransrule);
		setId(ICommandIds.CMD_NEW_RULE);
		setActionDefinitionId(ICommandIds.CMD_NEW_RULE);
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.NEW_RULE));
	}

	@Override
	public void run() {
		TransRuleAdapter editInput = new TransRuleAdapter();
		CodeNameTransRule rule = new CodeNameTransRule();
		editInput.setModle(rule);
		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.openEditor(editInput, TransRuleEditor.ID);
		} catch (PartInitException e) {
			CutStockPlugin.logError("Error opening Editor: " + TransRuleEditor.ID, e); //$NON-NLS-1$
		}
	}
}

package com.github.cutstock.ui.views.actions;

import org.eclipse.jface.action.Action;

import com.github.cutstock.ui.actions.ICommandIds;
import com.github.cutstock.ui.views.ViewManager;
import com.github.cutstock.ui.views.dataset.ViewTransRuleTable;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class OpenTransRuleTableAction extends Action {

	public OpenTransRuleTableAction() {
		super(Messages.OpenTransRuleTableAction_0);
		setToolTipText(Messages.OpenTransRuleTableAction_1);
		setId(ICommandIds.CMD_OPEN_TRANSRULE_ACTION);
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.CUTTING_RULE_16));
	}

	@Override
	public void run() {
		ViewManager.showView(ViewTransRuleTable.ID);
	}
}
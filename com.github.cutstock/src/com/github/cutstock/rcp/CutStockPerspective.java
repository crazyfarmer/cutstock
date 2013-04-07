package com.github.cutstock.rcp;

import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.eclipse.ui.IPlaceholderFolderLayout;

import com.github.cutstock.ui.views.NavigationView;
import com.github.cutstock.ui.views.dataset.ViewTransRuleTable;

public class CutStockPerspective implements IPerspectiveFactory {
	public static final String ID = "com.github.cutstock.perspective";
	public static final String ID_BOTTOM = "com.github.cutstock.perspective.bottomFolder";

	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.addView(NavigationView.ID, IPageLayout.LEFT, 0.2f, editorArea);
		layout.getViewLayout(NavigationView.ID).setCloseable(false);

		IPlaceholderFolderLayout folder = layout.createPlaceholderFolder(ID_BOTTOM, IPageLayout.BOTTOM, 0.6f,
				editorArea);
		folder.addPlaceholder(ViewTransRuleTable.ID);
	}

}

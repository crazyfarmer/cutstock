package com.github.cutstock.ui.views;


import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import com.github.cutstock.ui.editors.actions.OpenProfileEditorAction;
import com.github.cutstock.ui.views.actions.OpenTransRuleTableAction;
import com.github.cutstock.utils.images.EImages;

public class NavigationView extends ViewPart {

	public static final String ID = "com.github.cutstock.navigationView"; //$NON-NLS-1$
	private Composite top;

	@Override
	public void createPartControl(Composite parent) {
		ExpandBarManager expandBarManager = new ExpandBarManager();
		top = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(1).applyTo(top);
		final ExpandBar cuttingBar = new ExpandBar(expandBarManager, top, SWT.NONE,
				Messages.NavigationView_0, EImages.CUTTING_16,
				""); //$NON-NLS-1$
		cuttingBar.addAction(new OpenProfileEditorAction());
//		cuttingBar.addAction(new OpenCuttingTransAction());
		cuttingBar.addAction(new OpenTransRuleTableAction());
		
//		final ExpandBar outsourceBar = new ExpandBar(expandBarManager, top, SWT.NONE,
//			Messages.NavigationView_bar_outsource, EImage.OUT_16,
//			Messages.NavigationView_bar_outsource_tips);
//		outsourceBar.addAction(new OpenOutsourceEditorAction());
//		outsourceBar.addAction(new OpenOutRuleTableAction());
//		final ExpandBar glassBar = new ExpandBar(expandBarManager, top, SWT.NONE,
//			Messages.NavigationView_bar_glass, EImages.GLASS_16,
//			Messages.NavigationView_bar_glass_tips);
//		glassBar.addAction(new OpenGlassAction());
//		
//		final ExpandBar attachBar = new ExpandBar(expandBarManager, top, SWT.NONE,
//			Messages.NavigationView_bar_attach, EImages.ATTACH_16,
//			Messages.NavigationView_bar_attach_tips);
//		attachBar.addAction(new OpenAttachEditorAction());
	}

	@Override
	public void setFocus() {

	}

}

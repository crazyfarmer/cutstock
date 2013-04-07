package com.github.cutstock.ui.editors.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.github.cutstock.ui.editors.actions.messages"; //$NON-NLS-1$
	public static String OpenProfileEditorAction_0;
	public static String OpenProfileEditorAction_1;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

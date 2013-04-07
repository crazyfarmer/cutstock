package com.github.cutstock.algorithm;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.github.cutstock.algorithm.messages"; //$NON-NLS-1$
	public static String ColPattern_0;
	public static String ColPattern_plus;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

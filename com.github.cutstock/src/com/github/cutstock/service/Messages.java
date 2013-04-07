package com.github.cutstock.service;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "com.github.cutstock.service.messages"; //$NON-NLS-1$
	public static String ProfileOptimization_0;
	public static String ProfileOptimization_2;
	public static String ProfileOptimization_3;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}

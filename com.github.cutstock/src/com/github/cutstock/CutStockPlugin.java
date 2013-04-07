package com.github.cutstock;

import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.equinox.log.Logger;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

public class CutStockPlugin extends AbstractUIPlugin {

	public static final String PLUGIN_ID = "com.github.cutstock"; //$NON-NLS-1$

	private static CutStockPlugin plugin;

	public CutStockPlugin() {
	}

	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
	}

	public void stop(BundleContext context) throws Exception {
		plugin = null;
		super.stop(context);
	}

	public static CutStockPlugin getDefault() {
		return plugin;
	}

	public static ILog getLogger() {
		return getDefault().getLog();
	}

	public static void info(String msg){
		log0(new Status(IStatus.INFO,PLUGIN_ID,msg));
	}
	public static void logError(Exception e) {
		log0(new Status(IStatus.ERROR, PLUGIN_ID, e.getMessage(), e));
	}

	public static void logError(String msg, Throwable throwable) {
		log0(new Status(IStatus.ERROR, PLUGIN_ID, msg, throwable));
	}

	public static void logError(String msg) {
		log0(new Status(IStatus.ERROR, PLUGIN_ID, msg));
	}

	private static void log0(IStatus status) {
		getLogger().log(status);
	}
}

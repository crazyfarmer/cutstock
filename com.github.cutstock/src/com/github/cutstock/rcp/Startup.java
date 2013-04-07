package com.github.cutstock.rcp;

import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.db.HibernateConfiguration;
import com.github.cutstock.utils.HSQLServerUtil;
import com.github.cutstock.utils.HibernateUtil;

public class Startup implements IStartup {

	@Override
	public void earlyStartup() {

		final IWorkbench workbench = PlatformUI.getWorkbench();
		workbench.getDisplay().syncExec(new Runnable() {
			public void run() {
				IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();

				// If the data base is connected and if it is not new, then some
				// preferences are loaded
				// from the data base.
				// if (!Data.INSTANCE.getNewDBCreated())
				// if (DataBaseConnectionState.INSTANCE.isConnected())
				// PreferencesInDatabase.loadPreferencesFromDatabase();
//				HSQLServerUtil.getInstance().start("mem:testdb");
				// Opens the web browser editor.
				// if (window != null) {
				// OpenBrowserEditorAction action = new
				// OpenBrowserEditorAction();
				// action.run();
				// }

				// Check, if this is a newer Software, so do some update
				// Updater updater = new Updater();
				// updater.checkVersion();

			}
		});
	}

}

package com.github.cutstock.ui.editors.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.ui.actions.ICommandIds;
import com.github.cutstock.ui.editors.ProfileTransEditor;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class OpenProfileEditorAction extends Action {

	public OpenProfileEditorAction() {
		super(Messages.OpenProfileEditorAction_0);
		setToolTipText(Messages.OpenProfileEditorAction_1);
		setId(ICommandIds.CMD_OPEN_COLLECTION);
		setActionDefinitionId(ICommandIds.CMD_OPEN_COLLECTION);
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.CUTTING_COLLECTION_16));
	}

	@Override
	public void run() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		try {
			if (workbenchWindow != null) {
				IWorkbenchPage page = workbenchWindow.getActivePage();
				if (page != null) {
					page.openEditor(new ProfileInput(), ProfileTransEditor.ID);
				}
			}
		} catch (PartInitException e) {
			CutStockPlugin.logError("Error opening Editor: " + ProfileTransEditor.ID, e); //$NON-NLS-1$
		}
		 
	}
}
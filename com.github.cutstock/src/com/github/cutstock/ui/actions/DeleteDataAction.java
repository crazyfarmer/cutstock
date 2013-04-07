package com.github.cutstock.ui.actions;

import java.util.HashSet;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.TransRuleDaoImpl;
import com.github.cutstock.db.beans.CodeNameTransRule;
import com.github.cutstock.ui.views.ViewManager;
import com.github.cutstock.ui.views.dataset.ViewDataTable;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class DeleteDataAction extends Action {

	public DeleteDataAction() {
		super(""); //$NON-NLS-1$

		setId(ICommandIds.CMD_DELETE_DATASET);

		setActionDefinitionId(ICommandIds.CMD_DELETE_DATASET);
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.DEL_16));
	}
	 
	@Override
	public void run() {
		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		// Get the active part (view)
		IWorkbenchPart part = null;
		if (page != null)
			part = page.getActivePart();

		ISelection selection;

		// Cast the part to ViewDataSetTable
		if (part instanceof ViewDataTable) {
			ViewDataTable view = (ViewDataTable) part;
			if (view != null) {
				selection = view.getSite().getSelectionProvider().getSelection();
				if (selection != null && selection instanceof IStructuredSelection) {
					Object obj = ((IStructuredSelection) selection).getFirstElement();
					if (obj != null) {
						MessageBox messageBox = new MessageBox(workbenchWindow.getShell(), SWT.ICON_WARNING | SWT.OK
								| SWT.CANCEL);
						messageBox.setText(Messages.DeleteDataAction_0);
						messageBox.setMessage(Messages.DeleteDataAction_1);

						TransRuleDao ruleDao = new TransRuleDaoImpl();
						if (messageBox.open() == SWT.OK) {
							if(obj instanceof CodeNameTransRule){
								ruleDao.delRule((CodeNameTransRule) obj);
							}
//							if (obj instanceof TranRuleModel) {
//								TranRuleModel ruleData = (TranRuleModel) obj;
//								ruleData.setAction(DataModel.DELETE);
//								Database.getInstance().changeTransRuleData(ruleData);
//								Database.getInstance().getTransRuleData().remove(ruleData);
//								Database.getInstance().getTranRuleCategory();
//							} else if (obj instanceof OutsourceModel) {
//								OutsourceModel outData = (OutsourceModel) obj;
//								outData.setAction(DataModel.DELETE);
//								Database.getInstance().changeOutRuleData(outData);
//								Database.getInstance().getOutRuleData().remove(outData);
//								Database.getInstance().getOutRuleCategory();
//							}
						}
						
						view.setInputs(new HashSet(ruleDao.getAllCategories()), ruleDao.getAllTransRules());
						view.refresh();
					}
				}
			}
		} else {
			MessageDialog.openWarning(
					Display.getDefault().getActiveShell(),
					Messages.DeleteDataAction_2, part.getClass().getSimpleName() + "" //$NON-NLS-2$
							+ part.getTitle());
		}
	}
}

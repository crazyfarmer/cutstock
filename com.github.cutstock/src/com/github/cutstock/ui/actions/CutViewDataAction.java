package com.github.cutstock.ui.actions;

import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class CutViewDataAction extends Action {
	protected StructuredViewer viewer;
	protected TextStringTransfer transfer;

	public void setViewer(StructuredViewer viewer) {
		this.viewer = viewer;
	}

	public CutViewDataAction() {
		super(Messages.CutViewDataAction_tips_cut);
		transfer = new TextStringTransfer();
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.CUT_16));
	}

	public void run() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
//		DataModel[] gadgets = (DataModel[]) sel.toList().toArray(new DataModel[sel.size()]);
//		StringBuffer dataStringBuffer = new StringBuffer();
//
//		for (DataModel dataModel : gadgets) {
//			if (viewer.getData("TYPE").equals("TRANSRULE")) { //$NON-NLS-1$ //$NON-NLS-2$
//				TranRuleModel tModel = (TranRuleModel) dataModel;
//				tModel.setAction(DataModel.DELETE);
//				dataStringBuffer.append(tModel.getId()).append("\t").append(tModel.getSourceA()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(tModel.getSourceB()).append("\t").append(tModel.getComboSource()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(tModel.getDesciption()).append("\t").append(tModel.getCategory()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append("\n"); //$NON-NLS-1$
//				Database.getInstance().changeTransRuleData(tModel);
//				Database.getInstance().getTranRuleCategory();
//			} else {
//				OutsourceModel oModel = (OutsourceModel) dataModel;
//				oModel.setAction(DataModel.DELETE);
//				dataStringBuffer.append(oModel.getId()).append("\t").append(oModel.getOutName()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getCodeName()).append("\t").append(oModel.getPaintLen()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getWpm()).append("\t").append(oModel.getImgPath()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getDesc()).append("\t").append(oModel.getCategory()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
//				Database.getInstance().changeOutRuleData(oModel);
//				Database.getInstance().getOutRuleCategory();
//			}
//		}
//		transfer.setClipboardContents(dataStringBuffer.toString());
//
//		List<DataModel> dataRoot = (List<DataModel>) viewer.getInput();
//		for (int i = 0; i < gadgets.length; i++) {
//			dataRoot.remove(gadgets[i]);
//		}

		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		// Get the active part (view)
		IWorkbenchPart part = null;
		if (page != null) {
			part = page.getActivePart();
		}
//		if (part instanceof ViewDataTable) {
//			ViewDataTable view = (ViewDataTable) part;
//			view.refresh();
//		}
	}

}

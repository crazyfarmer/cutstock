package com.github.cutstock.ui.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.Transfer;

import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class CopyViewDataAction extends Action {
	protected StructuredViewer viewer;
	protected TextStringTransfer transfer;

	public void setViewer(StructuredViewer viewer) {
		this.viewer = viewer;
	}

	public CopyViewDataAction() {
		super(Messages.CopyViewDataAction_tips_copydata);
		transfer = new TextStringTransfer();
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.COPY_16));
	}

	public void run() {
		IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
//		DataModel[] gadgets = (DataModel[]) sel.toList().toArray(new DataModel[sel.size()]);
//		StringBuffer dataStringBuffer = new StringBuffer();
//
//		for (DataModel dataModel : gadgets) {
//			if (viewer.getData("TYPE").equals("TRANSRULE")) { //$NON-NLS-1$ //$NON-NLS-2$
//				TranRuleModel tModel = (TranRuleModel) dataModel;
//				dataStringBuffer.append(tModel.getId()).append("\t").append(tModel.getSourceA()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(tModel.getSourceB()).append("\t").append(tModel.getComboSource()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(tModel.getDesciption()).append("\t").append(tModel.getCategory()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append("\n"); //$NON-NLS-1$
//			} else {
//				OutsourceModel oModel = (OutsourceModel) dataModel;
//				dataStringBuffer.append(oModel.getId()).append("\t").append(oModel.getOutName()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getCodeName()).append("\t").append(oModel.getPaintLen()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getWpm()).append("\t").append(oModel.getImgPath()).append("\t") //$NON-NLS-1$ //$NON-NLS-2$
//						.append(oModel.getDesc()).append("\t").append(oModel.getCategory()).append("\n"); //$NON-NLS-1$ //$NON-NLS-2$
//			}
//		}
//		transfer.setClipboardContents(dataStringBuffer.toString());
	}
}

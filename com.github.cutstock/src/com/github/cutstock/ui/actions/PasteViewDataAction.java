package com.github.cutstock.ui.actions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import com.github.cutstock.ui.views.dataset.ViewDataTable;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

public class PasteViewDataAction extends Action {
	// protected Clipboard clipboard;
	protected StructuredViewer viewer;

	protected TextStringTransfer transfer;

	public void setViewer(StructuredViewer viewer) {
		this.viewer = viewer;
	}

	public PasteViewDataAction() {
		super("Paste");
		transfer = new TextStringTransfer();
		setImageDescriptor(ImageProvider.getImageDescriptor(EImages.PASTE_16));
	}

	public void run() {
		// DataModel[] gadgets = (DataModel[])
		// clipboard.getContents(DataModelTransfer.getInstance());
		String clipStr = transfer.getClipboardContents();
		if (clipStr == null)
			return;
		List list = (List) viewer.getInput();
//		List<DataModel> dataRoot = (List<DataModel>) viewer.getInput();
//		if (dataRoot == null) {
//			dataRoot = new ArrayList<DataModel>();
//		}
//		List<DataModel> readData = null;

		// id source a sourceb new desc
		String line = null;
		BufferedReader br = new BufferedReader(new StringReader(clipStr));

		try {
			while ((line = br.readLine()) != null) {
				String[] ruleModelStr = line.split("\t");

//				if (viewer.getData("TYPE").equals("TRANSRULE")) {
//					TranRuleModel transRuleModel = new TranRuleModel();
//					transRuleModel.setId(Integer.parseInt(ruleModelStr[0]));
//					transRuleModel.setSourceA(ruleModelStr[1]);
//					transRuleModel.setSourceB(ruleModelStr[2]);
//					transRuleModel.setComboSource(ruleModelStr[3]);
//					transRuleModel.setDescription(ruleModelStr[4]);
//					transRuleModel.setCategory(ruleModelStr[5]);
//					transRuleModel.setAction(DataModel.INSERT);
//					Database.getInstance().changeTransRuleData(transRuleModel);
//					dataRoot.add(transRuleModel);
//					Database.getInstance().getTranRuleCategory();
//
//				} else {
//					OutsourceModel outsourceModel = new OutsourceModel();
//					outsourceModel.setId(Integer.valueOf(ruleModelStr[0]));
//					outsourceModel.setOutName(ruleModelStr[1]);
//					outsourceModel.setCodeName(ruleModelStr[2]);
//					outsourceModel.setPaintLen(new BigDecimal(ruleModelStr[3]));
//					outsourceModel.setWpm(new BigDecimal(ruleModelStr[4]));
//					outsourceModel.setImgPath(ruleModelStr[5]);
//					outsourceModel.setDesc(ruleModelStr[6]);
//					outsourceModel.setCategory(ruleModelStr[7]);
//					outsourceModel.setAction(DataModel.INSERT);
//					Database.getInstance().changeOutRuleData(outsourceModel);
//					Database.getInstance().getOutRuleCategory();
//					dataRoot.add(outsourceModel);
//				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();

		// Get the active part (view)
		IWorkbenchPart part = null;
		if (page != null) {
			part = page.getActivePart();
		}
		if (part instanceof ViewDataTable) {
			ViewDataTable view = (ViewDataTable) part;
			view.refresh();
		}

	}
}
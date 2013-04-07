package com.github.cutstock.ui.editors;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.ui.adapter.AbstractAdapter;
import com.github.cutstock.ui.adapter.TransRuleAdapter;
import com.github.cutstock.ui.views.dataset.ViewDataTable;

public class CallEditor extends AbstractHandler implements IHandler {
	public static final String CALL_PARAM = "com.github.cutstock.editor.callEditorParam";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		String param = event.getParameter(CALL_PARAM);
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();
		String viewId = "com.github.cutstock.ui.views.dataset.view" + param + "Table";
		ViewDataTable view = (ViewDataTable) page.findView(viewId);

		ISelection selection = view.getSite().getSelectionProvider().getSelection();
		if (selection != null && selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			if (obj != null) {
				String editor = "com.github.cutstock.ui.editors." + param.toLowerCase() + "Editor";
				String adapterName = "com.github.cutstock.ui.adapter."+param+"Adapter";
				AbstractAdapter editorInput = null ;
				try {
					Class adapter = Class.forName(adapterName);
					editorInput = (AbstractAdapter) adapter.newInstance();
					editorInput.setModle(obj);
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				try {
					page.openEditor(editorInput, editor);
				} catch (PartInitException e) {
					CutStockPlugin.logError("Error opening Editor: " + editor, e);
				}
			}
		}
		return null;
	}

}

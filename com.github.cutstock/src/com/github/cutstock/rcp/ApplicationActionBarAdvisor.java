package com.github.cutstock.rcp;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import com.github.cutstock.ui.actions.DeleteDataAction;
import com.github.cutstock.ui.actions.NewTransRuleAction;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;

/**
 * An action bar advisor is responsible for creating, adding, and disposing of
 * the actions added to a workbench window. Each window will be populated with
 * new actions.
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

    private IWorkbenchAction openPreferencesAction;

    private IWorkbenchAction printAction;
    private IWorkbenchAction printActionTB;
    private IWorkbenchAction closeAction;
    private IWorkbenchAction closeAllAction;
    private IWorkbenchAction saveAction;
    private IWorkbenchAction saveActionTB;
    private IWorkbenchAction saveAllAction;
    private IWorkbenchAction exitAction;
    private IWorkbenchAction aboutAction;

    private DeleteDataAction deleteDataAction;

//    private OpenBrowserEditorAction OpenBrowserEditorAction;
//    private OpenBrowserEditorAction openBrowserEditorActionTB;
//    private NewOutRuleAction newOutRuleActionTB;
    private NewTransRuleAction newTransRuleActionTB;

    private IContributionItem showViewItem;

    private IWorkbenchAction cutAction;

    private IWorkbenchAction copyAction;

    private IWorkbenchAction pasteAction;

    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
	super(configurer);
    }

    @Override
    protected void makeActions(final IWorkbenchWindow window) {

//	newOutRuleActionTB = new NewOutRuleAction();
//	register(newOutRuleActionTB);
	newTransRuleActionTB = new NewTransRuleAction();
	register(newTransRuleActionTB);

	saveAction = ActionFactory.SAVE.create(window);
	saveAction.setText(Messages.ApplicationActionBarAdvisor_0);
	register(saveAction);
	saveActionTB = ActionFactory.SAVE.create(window);
	register(saveActionTB);

	closeAction = ActionFactory.CLOSE.create(window);
	closeAction.setText(Messages.ApplicationActionBarAdvisor_1);
	register(closeAction);

	closeAllAction = ActionFactory.CLOSE_ALL.create(window);
	closeAllAction.setText(Messages.ApplicationActionBarAdvisor_2);
	register(closeAllAction);

	saveActionTB = ActionFactory.SAVE.create(window);
	register(saveActionTB);

	saveAllAction = ActionFactory.SAVE_ALL.create(window);
	saveAllAction.setText(Messages.ApplicationActionBarAdvisor_3);
	register(saveAllAction);

	aboutAction = ActionFactory.ABOUT.create(window);
	aboutAction.setImageDescriptor(ImageProvider.getImageDescriptor(EImages.ABOUT_16));

	// T: Text of the actions in the main menu
	aboutAction.setText(Messages.ApplicationActionBarAdvisor_4);
	register(aboutAction);

	exitAction = ActionFactory.QUIT.create(window);
	exitAction.setText(Messages.ApplicationActionBarAdvisor_5);
	register(exitAction);

	deleteDataAction = new DeleteDataAction();
	register(deleteDataAction);
	
	
	cutAction = ActionFactory.CUT.create(window);
	register(cutAction);
	copyAction = ActionFactory.COPY.create(window);
	register(copyAction);
	pasteAction = ActionFactory.PASTE.create(window);
	register(pasteAction);
	
	
//	OpenBrowserEditorAction = new OpenBrowserEditorAction();
//	register(OpenBrowserEditorAction);
//	openBrowserEditorActionTB = new OpenBrowserEditorAction();
//	register(openBrowserEditorActionTB);

	showViewItem = ContributionItemFactory.VIEWS_SHORTLIST.create(window);
    }

    @Override
    protected void fillMenuBar(IMenuManager menuBar) {
	MenuManager fileMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_6, IWorkbenchActionConstants.M_FILE);
	fileMenu.add(saveAllAction);
//	fileMenu.add(openPreferencesAction);
	fileMenu.add(new Separator());
	fileMenu.add(exitAction);
	menuBar.add(fileMenu);

	MenuManager windowMenu = new MenuManager(Messages.ApplicationActionBarAdvisor_7, IWorkbenchActionConstants.M_WINDOW);
	MenuManager showViewMenuMgr = new MenuManager(Messages.ApplicationActionBarAdvisor_8, IWorkbenchActionConstants.M_VIEW);
	showViewMenuMgr.add(showViewItem);
	// windowMenu.add(showViewMenuMgr);
	menuBar.add(showViewMenuMgr);
//	menuBar.add(cutAction);
//	menuBar.add(copyAction);
//	menuBar.add(pasteAction);


    }

    @Override
    protected void fillCoolBar(ICoolBarManager coolBar) {
	IToolBarManager toolbar1 = new ToolBarManager(SWT.FLAT);
	IToolBarManager toolbar2 = new ToolBarManager(SWT.FLAT);
//	ToolBarContributionItem tbci1 = new ToolBarContributionItem(toolbar1, "");
//	ToolBarContributionItem tbci2 = new ToolBarContributionItem(toolbar2, "");

	newTransRuleActionTB.setImageDescriptor(ImageProvider.getImageDescriptor(EImages.CUTTING_RULE_20));
	newTransRuleActionTB.setText(Messages.ApplicationActionBarAdvisor_9);
	ActionContributionItem newTransRuleActionTBCI = new ActionContributionItem(newTransRuleActionTB);
	newTransRuleActionTBCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
	toolbar1.add(newTransRuleActionTBCI);

//	newOutRuleActionTB.setImageDescriptor(ImageProvider.getImageDescriptor(EImage.OUT_RULE_20));
//	newOutRuleActionTB.setText(Messages.ApplicationActionBarAdvisor_new_outsourcerule);
//	ActionContributionItem newOutRuleActionTBCI = new ActionContributionItem(newOutRuleActionTB);
//	newOutRuleActionTBCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
//	toolbar1.add(newOutRuleActionTBCI);

//	printActionTB.setImageDescriptor(ImageProvider.getImageDescriptor(EImages.PRINT_32));
//	printActionTB.setDisabledImageDescriptor(ImageProvider.getImageDescriptor(EImages.PRINT_DIS));
//	printActionTB.setText(Messages.ApplicationActionBarAdvisor_print_tb);
//	ActionContributionItem printActionTBCI = new ActionContributionItem(printActionTB);
//	printActionTBCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
//	toolbar2.add(printActionTBCI);

	saveActionTB.setImageDescriptor(ImageProvider.getImageDescriptor(EImages.SAVE_32));
	saveActionTB.setDisabledImageDescriptor(ImageProvider.getImageDescriptor(EImages.SAVE_DIS));
	// T: Text of the actions in the tool bar. Keep it short that it can be
	// placed under the icon.
	saveActionTB.setText(Messages.ApplicationActionBarAdvisor_10);
	ActionContributionItem saveCI = new ActionContributionItem(saveActionTB);
	saveCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
	toolbar2.add(saveCI);

//	openBrowserEditorActionTB.setImageDescriptor(ImageProvider.getImageDescriptor(EImage.BROWSER_32));
//	openBrowserEditorActionTB.setText(Messages.ApplicationActionBarAdvisor_explorer);
//	ActionContributionItem openBrowserEditorCI = new ActionContributionItem(openBrowserEditorActionTB);
//	openBrowserEditorCI.setMode(ActionContributionItem.MODE_FORCE_TEXT);
//	toolbar2.add(openBrowserEditorCI);

//	coolBar.add(tbci1);
//	coolBar.add(tbci2);

    }

}

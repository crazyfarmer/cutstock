package com.github.cutstock.ui.actions;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "com.github.cutstock.ui.actions.messages"; //$NON-NLS-1$
    public static String CopyViewDataAction_tips_copydata;
    public static String CutViewDataAction_tips_cut;
    public static String DeleteDataAction_0;
	public static String DeleteDataAction_1;
	public static String DeleteDataAction_2;
	public static String DeleteDataAction_confirm;
    public static String DeleteDataAction_confirm_text;
    public static String DeleteDataAction_tips_delete;
    public static String DeleteDataAction_warning;
    public static String DeleteDataAction_warning_text;
    public static String DeleteDataAction_warning_text_1;
    public static String NewOutRuleAction_tips_add_newoutsource;
    public static String NewTransRuleAction_add_newtransrule;
    public static String OpenAttachEditorAction_tips_attach_trans;
    public static String OpenAttachEditorAction_title_attach_trans;
    public static String OpenBrowserEditorAction_brower_title;
    public static String OpenBrowserEditorAction_tips_welcomepage;
    public static String OpenCollectionEditorAction_collection_optimization;
    public static String OpenCollectionEditorAction_tips_collection_optimization;
    public static String OpenCuttingTransAction_tips_transtxt;
    public static String OpenCuttingTransAction_title_transtxt;
    public static String OpenGlassAction_tips_glasstrans;
    public static String OpenGlassAction_title_glasstrans;
    public static String OpenOutRuleTableAction_tips_outrule;
    public static String OpenOutRuleTableAction_title_outrule;
    public static String OpenOutsourceEditorAction_tips_outsourcetrans;
    public static String OpenOutsourceEditorAction_title_outsource;
    public static String OpenTransRuleTableAction_tips_transrule;
    public static String OpenTransRuleTableAction_title_transrule;
    static {
	// initialize resource bundle
	NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}

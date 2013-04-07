package com.github.cutstock.ui.views;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
    private static final String BUNDLE_NAME = "com.github.cutstock.ui.views.messages"; //$NON-NLS-1$
    public static String NavigationView_0;
	public static String NavigationView_bar_attach;
    public static String NavigationView_bar_attach_tips;
    public static String NavigationView_bar_glass;
    public static String NavigationView_bar_glass_tips;
    public static String NavigationView_bar_outsource;
    public static String NavigationView_bar_outsource_tips;
    public static String NavigationView_bar_trans_tips;
    public static String NavigationView_bar_trans_title;
	public static String NavigationView_barTitle;
    
    public static String OpenCollectionEditorAction_collection_optimization;
    public static String OpenCollectionEditorAction_tips_collection_optimization;
    public static String OpenCuttingTransAction_tips_transtxt;
    public static String OpenCuttingTransAction_title_transtxt;
    public static String OpenTransRuleTableAction_tips_transrule;
    public static String OpenTransRuleTableAction_title_transrule;
    static {
	// initialize resource bundle
	NLS.initializeMessages(BUNDLE_NAME, Messages.class);
    }

    private Messages() {
    }
}

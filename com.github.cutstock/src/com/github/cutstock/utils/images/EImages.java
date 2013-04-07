package com.github.cutstock.utils.images;
public enum EImages implements IImage {
    	ADD_VIEW("/icons/"), 
    	ADD_BTN("/icons/add.png"), 
    	REFRESH_BTN("/icons/refresh.gif"), 
    	REMOVE_BTN("/icons/remove.gif"), 
    	DISREMOVE_BTN("/icons/disremove.gif"), 
    	SEL_ALL_BTN("/icons/selectall.gif"), 
    	DESEL_BTN("/icons/deselect.gif"), 
    	CONTACT_10("/icons/10/contact_10.png"),
	    SHOP_16("/icons/16/shop_16.png"),
	    TXT_TRANS_16("/icons/16/txt_trans_16.png"),
	    DROPUP("/icons/expandbar/dropuparrow.png"), 
	    IMPORT("/icons/16/import_16.png"), 
	    DROPDOWN("/icons/expandbar/dropdownarrow.png"), 
	    CLEAR("/icons/16/clear_16.gif"),
	    BROWSER_32("/icons/32/www_32.png"),
	    PRINT_32("/icons/32/printoo_32.png"),
	    PRINT_DIS("/icons/32/printoo_dis_32.png"),
	    SAVE_32("/icons/32/save_32.png"),
	    SAVE_DIS("/icons/32/save_dis_32.png"),
	    DEL_16("/icons/16/delete_16.png"),
	    NEW_RULE("/icons/16/plus_16.png"),
	    COPY_16("/icons/16/copy.png"),
	    OUT_16("/icons/16/out_16.png"),
	    CUTTING_16("/icons/16/cutting_16.png"),
	    CUTTING_COLLECTION_16("/icons/16/cutting_collection_16.png"),
	    CUT_16("/icons/16/cut.png"),
	    PASTE_16("/icons/16/paste.png"),
	    ABOUT_16("/icons/16/about_16.png"),
	    CUTTING_RULE_16("/icons/16/cutting_rule_16.png"),
	    CUTTING_RULE_20("/icons/20/cutting_rule_20.png"),
	    CUTTING_TRANS_16("/icons/16/cutting_trans_16.png"),
	    ;
    private String path;

    EImages(String path) {
	this.path = path;
    }

    public Class getLocation() {
	return EImages.class;
    }

    public String getPath() {
	return path;
    }

}

package com.github.cutstock.ui.editors.dialogs;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.widgets.List;

import com.github.cutstock.db.HibernateConfiguration;
import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.TransRuleDaoImpl;

public class TransRuleManager {
	private static TransRuleManager instance;
	private TransRuleDao transRuleDao;

	public static TransRuleManager getInstance() {
		if (instance == null) {
			instance = new TransRuleManager();
		}
		return instance;
	}

	private TransRuleManager() {
		transRuleDao = new TransRuleDaoImpl(HibernateConfiguration.class.getName());
	}

	private Set<String> selItems;
	private Set<String> availItems;


	public void setSelItems(Set<String> selItems) {
		this.selItems = selItems;
	}

	public void setAvailItems(Set<String> availItems) {
		this.availItems = availItems;
	}

	public Set<String> getAvailItems() {
		if (availItems == null) {
			availItems = new HashSet(transRuleDao.getAllCategories());
		}else{
			availItems = new HashSet(transRuleDao.getAllCategories());
			availItems.removeAll(selItems);
		}
		return availItems;
	}

	public Set<String> getSelItems() {
		if (selItems == null) {
			selItems = new HashSet<String>();
		}
		return selItems;
	}

}
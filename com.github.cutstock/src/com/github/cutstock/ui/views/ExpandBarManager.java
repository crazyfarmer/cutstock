package com.github.cutstock.ui.views;

import java.util.ArrayList;
import java.util.List;

public class ExpandBarManager {

	// List with all expand bars
	List<ExpandBar> expandBars = new ArrayList<ExpandBar>();

	/**
	 * Constructor Clears the list.
	 */
	public ExpandBarManager() {
		expandBars.clear();
	}

	/**
	 * Add a new expand bar
	 * 
	 * @param expandBar
	 *            A new expand bar
	 */
	public void addExpandBar(ExpandBar expandBar) {
		expandBars.add(expandBar);
	}

	/**
	 * Collapse the other expand bars
	 * 
	 * @param expandBar
	 *            Do not collapse this expand bar
	 */
	public void collapseOthers(ExpandBar expandBar) {

		for (ExpandBar nextExpandBar : expandBars) {
			if (nextExpandBar != expandBar)
				nextExpandBar.collapse(true);
		}
	}

}

package com.github.cutstock.service;

import java.math.BigDecimal;
import java.util.Comparator;

import com.github.cutstock.model.ComponentOrderInfo;

public class OrderComparator implements Comparator<ComponentOrderInfo> {

	@Override
	public int compare(ComponentOrderInfo o1, ComponentOrderInfo o2) {
		if(o1.getProfileName().equals(o2.getProfileName())){
			return (new BigDecimal(o2.getFirstCutPattern()).compareTo(new BigDecimal(o1.getFirstCutPattern())));
		}else{
			return 0;
		}
	}
	 
}


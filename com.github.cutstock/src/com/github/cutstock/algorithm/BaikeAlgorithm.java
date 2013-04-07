package com.github.cutstock.algorithm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.cutstock.utils.ArithmeticUtil;
import com.github.cutstock.utils.ArrayUtil;
import com.github.cutstock.utils.CutstockUtils;

public class BaikeAlgorithm extends CutStockProblem{

	@Override
	public CutStockResult start() {
		List<double[]> resultset =doCalc();
		CutStockResult result = CutstockUtils.convertArray2CutStock(resultset,new BigDecimal(gapWidth));
		return result;
	}
	public List<double[]> doCalc() {
		List<double[]> ret = new ArrayList<double[]>();
		List<Double> allCutsList = ArrayUtil.normalizeDatas(amountArray,widthArray);
		Collections.sort(allCutsList);
		double[] allLenArray = ArrayUtil.toDoubleArray(allCutsList);
		BigDecimal left = new BigDecimal(rollWidth);

		for (int i = 0; i < allLenArray.length; i++) {
			List<BigDecimal> tmp = new ArrayList<BigDecimal>();
			if (allLenArray[i] == 0) {
				continue;
			}
			left = ArithmeticUtil.subtract(left,new BigDecimal(allLenArray[i]));
			tmp.add(new BigDecimal(allLenArray[i]));
			allLenArray[i] = 0;
			int midPos = midLen(allLenArray, left);
			// prior to low value
			for (int j = midPos; j > 0; j--) {
				if (allLenArray[j] == 0) {
					continue;
				}
				left = ArithmeticUtil.subtract(left,new BigDecimal(allLenArray[j]));
				if (left.compareTo(new BigDecimal(0)) > 0) {
					tmp.add(new BigDecimal(allLenArray[j]));
					allLenArray[j] = 0;// flag not to read
				} else {
					left = ArithmeticUtil.add(left,new BigDecimal(allLenArray[j]));
				}
			}

			for (int j = midPos; j < allLenArray.length; j++) {
				if (allLenArray[j] == 0) {
					continue;
				}
				left = ArithmeticUtil.subtract(left,new BigDecimal(allLenArray[j]));
				if (left.compareTo(new BigDecimal(0)) > 0) {
					tmp.add(new BigDecimal(allLenArray[j]));
					allLenArray[j] = 0;// flag not to read
				} else {
					left = ArithmeticUtil.add(left,new BigDecimal(allLenArray[j]));
				}
			}
			left = new BigDecimal(rollWidth);
			ret.add(ArrayUtil.toArray(tmp));
		}
		return ret;
	}

	private int midLen(double[] arr, BigDecimal mid) {
		int pos = 0;
		for (double intVal : arr) {
			if (mid.divide(new BigDecimal(2)).compareTo(new BigDecimal(intVal))>=0) {
				pos++;
			}
		}
		return pos - 1;
	}

}

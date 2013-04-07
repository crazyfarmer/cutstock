package com.github.cutstock.algorithm;

public interface ICutStockProblem {

	void init(double len, double[] widthArray, double[] amountArray,
			double gapWidth);

	CutStockResult start();
}

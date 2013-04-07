/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.github.cutstock.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;

import com.github.cutstock.algorithm.BFAlgorithm;
import com.github.cutstock.algorithm.BaikeAlgorithm;
import com.github.cutstock.algorithm.ColPattern;
import com.github.cutstock.algorithm.CutStockProblem;
import com.github.cutstock.algorithm.CutStockResult;
import com.github.cutstock.algorithm.IlogAlgorithm;
import com.github.cutstock.algorithm.PatternInfo;
import com.github.cutstock.db.HibernateConfiguration;
import com.github.cutstock.db.TransRuleDao;
import com.github.cutstock.db.TransRuleDaoImpl;
import com.github.cutstock.excel.ProfileExcelWriter;
import com.github.cutstock.model.ComponentOrderInfo;
import com.github.cutstock.model.ComponentOrdersLoader;
import com.github.cutstock.model.CutstockResultInfo;
import com.github.cutstock.model.ProfileInfo;
import com.github.cutstock.model.ProfileInfo.WidthAmountPair;
import com.github.cutstock.model.Profiles;
import com.github.cutstock.utils.ArithmeticUtil;
import com.github.cutstock.utils.ArrayUtil;
import com.github.cutstock.utils.ComponentOrderHelper;
import com.github.cutstock.utils.CutstockUtils;
import com.github.cutstock.utils.ProfileUtils;

/**
 * @author <a href="crazyfarmer.cn@gmail.com">crazyfarmer.cn@gmail.com</a>
 * @date Jan 14, 2013
 */
public class ProfileOptimization implements IProfileService {

	@Override
	public void doService(final ProfileServiceParams params) {

		Job job = new Job(Messages.ProfileOptimization_0) {

			@Override
			protected IStatus run(IProgressMonitor monitor) {
				monitor.beginTask("", 100); //$NON-NLS-1$
				// execute the task ...
				monitor.setCanceled(true);

				List<ComponentOrderInfo> allOrderList = new ArrayList();
				// CutStockConfig
				// 1. transform file
				Profiles profiles = ProfileUtils.parseFile(params
						.getInputFile());
				monitor.worked(10);
				// 2.do optimaziton

				Iterator<ProfileInfo> profileInfoIt = profiles.createIterator();
				int allsize = profiles.getSize();
				int unitIndex = 1;
				int workedUnit = 80 / allsize;
				while (profileInfoIt.hasNext()) {
					CutStockProblem csp = new IlogAlgorithm();
					ProfileInfo profile = profileInfoIt.next();
					List<WidthAmountPair> pairs = profile.getWidthAmountPairs();
					double[][] widthAmountArrays = CutstockUtils.getWidthAmout(
							pairs, params.getGapWidth());
					// every width must be add gap width
					// 2.1 optimize each group cut stock
					// with cplex
					csp.init(params.getLenWidth() - params.getHeadWidth(),
							widthAmountArrays[0], widthAmountArrays[1],
							params.getGapWidth());
					CutStockResult cutResult = csp.start();

					// 2.2 if cplex DOES NOT work,use greedy algorithm instead.
					if (cutResult.getPatterns() == null) {
						cutResult = getBestCutStockResult(params.getLenWidth()
								- params.getHeadWidth(), widthAmountArrays[0],
								widthAmountArrays[1], params.getGapWidth());

					} else {
						// cplex never satisfy my needs,so we have to optimize
						// again
						// 1. checkout unused data
						// 2. tick out should-not-used data
						double[] size = widthAmountArrays[0];
						double[] amount = widthAmountArrays[1];
						Map<Double, Double> sizeamount = new HashMap<Double, Double>();

						for (int i = 0; i < size.length; i++) {
							sizeamount.put(size[i], amount[i]);
						}
						Iterator<PatternInfo> patternInfoIt = cutResult
								.createIterator();
						while (patternInfoIt.hasNext()) {
							PatternInfo patternInfo = patternInfoIt.next();
							int patternNum = patternInfo.getPatternNum();
							for (int availNum = 1; availNum <= patternNum; availNum++) {
								 boolean isAvailedPattern = checkValidPattern(params,sizeamount, patternInfo);
								 
								if(isAvailedPattern){
									removeUsedProfile(params, sizeamount,
											patternInfo);
								}else {
									//backward
									availNum=availNum-1;
									if(availNum==0){
										patternInfoIt.remove();
									}else{
										patternInfo.setPatternNum(availNum);
									}
									break;
								}
							}
						}
						// 3. fill the gap with these data
						// 4. use bfalgorithm process these data
						double[][] leftWidthAmountArrays = ArrayUtil
								.convert2widthamountArray(sizeamount);
						// 3.2 do get cutstock result
						if (sizeamount.size() > 0) {
							CutStockResult leftCutResult = getBestCutStockResult(
									params.getLenWidth()
											- params.getHeadWidth(),
									leftWidthAmountArrays[0],
									leftWidthAmountArrays[1],
									params.getGapWidth());
							// 3.3 add to cutresult
							combineCutResult(cutResult, leftCutResult);
						}

						// compara ilog with best fit ,to find better result;
						// but the num of widtharr should not too big(less than
						// 10)
						int logUsedNum = cutResult.calculateUsedNum();
						if (amount.length < 10) {
							CutStockResult cutResult1 = getBestCutStockResult(
									params.getLenWidth()
											- params.getHeadWidth(),
									widthAmountArrays[0], widthAmountArrays[1],
									params.getGapWidth());
							if (cutResult1.calculateUsedNum() < logUsedNum) {
								cutResult = cutResult1;
							}
						}
					}

					CutstockResultInfo resultInfo = new CutstockResultInfo();
					resultInfo.setProfileName(profile.getCodeData());
					resultInfo.setColor(profile.getColor());
					resultInfo.setOriginalWidth(new BigDecimal(params
							.getLenWidth()));
					resultInfo.setHeadWidth(new BigDecimal(params
							.getHeadWidth()));
					resultInfo
							.setGapWidth(new BigDecimal(params.getGapWidth()));
					resultInfo.setCutResult(cutResult);
					// 3. gen orderinfo
					ComponentOrdersLoader compOrder = CutstockUtils
							.convert2componentOrder(resultInfo);
					List<ComponentOrderInfo> orders = compOrder.doCreateOrder();
					allOrderList.addAll(orders);

					monitor.worked((unitIndex++) * workedUnit);
				}

				ComponentOrderHelper orderHelper = new ComponentOrderHelper();
				orderHelper.setRuleCategories(params.getRules());
				TransRuleDao transRuleDao = new TransRuleDaoImpl(
						HibernateConfiguration.class.getName());
				orderHelper.setTransRuleDao(transRuleDao);
				ProfileExcelWriter profileWriter = new ProfileExcelWriter();
				profileWriter.setOrderHelper(orderHelper);
				// 4. write excel
				Collections.sort(allOrderList, new OrderComparator());
				profileWriter.setOrderList(allOrderList);
				profileWriter.setSheetVersion(params.getExcelVersion());
				profileWriter.writeOutputFile(params.getOutputFile());
				monitor.worked(100);
				monitor.done();
				finishedUI();
				return Status.OK_STATUS;
			}

			private boolean checkValidPattern(
					final ProfileServiceParams params,
					Map<Double, Double> sizeamount, PatternInfo patternInfo) {
				for (Iterator<ColPattern> colPatIt = patternInfo
						.getColPatterns().iterator(); colPatIt
						.hasNext();) {
					ColPattern colPat = colPatIt.next();
					double key = colPat.getColWidth().add(new BigDecimal(params.getGapWidth())).doubleValue();
					double oriAmount = sizeamount.get(key);
					if (oriAmount< colPat.getColNum() ) {
						return false;
					}
				}
				return true;
			}
			private void removeUsedProfile(final ProfileServiceParams params,
					Map<Double, Double> sizeamount, PatternInfo patternInfo) {
					for (Iterator<ColPattern> colPatIt = patternInfo
							.getColPatterns().iterator(); colPatIt.hasNext();) {
						ColPattern colPat = colPatIt.next();
						double key = colPat.getColWidth()
								.add(new BigDecimal(params.getGapWidth()))
								.doubleValue();
						double oriAmount = sizeamount.get(key);
						BigDecimal leftAmount = ArithmeticUtil.subtract(
								new BigDecimal(oriAmount), new BigDecimal(
										colPat.getColNum()));
						sizeamount.put(key, leftAmount.doubleValue());
					}
			}

			private void combineCutResult(CutStockResult cutResult,
					CutStockResult leftCutResult) {
				for (Iterator<PatternInfo> cutIt = leftCutResult.getPatterns()
						.iterator(); cutIt.hasNext();) {
					cutResult.addPatternInfo(cutIt.next());
				}
			}
		};
		job.schedule();
	}

	private void finishedUI() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				MessageDialog.openInformation(null,
						Messages.ProfileOptimization_2,
						Messages.ProfileOptimization_3);
			}
		});
	}

	public CutStockResult getBestCutStockResult(int lenWidth,
			double[] widthArray, double[] amountArray, int gap) {

//		CutStockProblem bfAlgorithm = new BFAlgorithm();
//		bfAlgorithm.init(lenWidth, widthArray, amountArray, gap);
//		CutStockResult bestResult = bfAlgorithm.start();

		CutStockProblem bkAlgorithm = new BaikeAlgorithm();
		bkAlgorithm.init(lenWidth, widthArray, amountArray, gap);
		CutStockResult bestResult1 = bkAlgorithm.start();

//		if (bestResult.calculateUsedNum() > bestResult1.calculateUsedNum()) {
//			bestResult = bestResult1;
//		}
		return bestResult1;
	}
}

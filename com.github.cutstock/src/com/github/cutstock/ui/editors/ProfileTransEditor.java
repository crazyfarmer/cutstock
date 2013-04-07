package com.github.cutstock.ui.editors;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.PartInitException;

import com.github.cutstock.service.IProfileService;
import com.github.cutstock.service.ProfileOptimization;
import com.github.cutstock.service.ProfileServiceParams;
import com.github.cutstock.ui.editors.dialogs.RuleSelDialog;
import com.github.cutstock.ui.editors.dialogs.TransRuleManager;

public class ProfileTransEditor extends AbstractEditor {

	public static final String ID = "com.github.cutstock.editors.profileEditor"; //$NON-NLS-1$
	IPreferenceStore preferences;

	private boolean isPageModified;

	private Composite top;
	private Text inputTextFile;
	// private Combo ruleCombo;
	private Text textOutputDir;
	private Button btnAlgorithm1;
	private Button btnAlgorithm2;

	private Text txtMinValue;
	private Text txtMaxValue;

	private Text txtOriginLen;
	private Text txtHeadWidth;
	private Text txtGapWidth;

	private TransRuleManager transRuleManager = TransRuleManager.getInstance();

	
	public ProfileTransEditor(){
		
	}
	
	@Override
	public void doSave(IProgressMonitor monitor) {
	}

	@Override
	public void doSaveAs() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		setSite(site);
		setInput(input);
	}

	@Override
	public boolean isSaveAsAllowed() {
		return false;
	}

	@Override
	public void createPartControl(Composite parent) {
		top = new Composite(parent, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(2).applyTo(top);

		// group: select an excel file
		Group excelInputGroup = new Group(top, SWT.NONE);
		GridLayoutFactory.swtDefaults().numColumns(5).applyTo(excelInputGroup);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(excelInputGroup);
		excelInputGroup.setText(Messages.ProfileTransEditor_0);

		Label labelFile = new Label(excelInputGroup, SWT.LEFT);
		labelFile.setText(Messages.ProfileTransEditor_lbInputFile);
		GridDataFactory.swtDefaults().applyTo(labelFile);

		inputTextFile = new Text(excelInputGroup, SWT.BORDER);
		// inputTextFile.setText(CuttingPlugin.getDefault().getPreferenceStore()
		// .getDefaultString(COLLECTION_INPUT));
		superviceControl(inputTextFile, 0);
		GridDataFactory.fillDefaults().span(3, 1).applyTo(inputTextFile);
		Button selectFolderButton = new Button(excelInputGroup, SWT.PUSH);
		selectFolderButton.setText(Messages.ProfileTransEditor_1);
		selectFolderButton.addListener(SWT.Selection, new Listener() {
			private String selectedDir;

			public void handleEvent(Event event) {
				FileDialog fileDialog = new FileDialog(Display.getCurrent().getActiveShell());
				fileDialog.setFilterPath(selectedDir);
				fileDialog.setFilterNames(new String[] { "Excel Files", //$NON-NLS-1$
						"All Files (*.*)" }); //$NON-NLS-1$
				fileDialog.setFilterExtensions(new String[] { "*.xls", "*.Xls", "*.xlsx","*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String dir = fileDialog.open();
				if (dir != null) {
					inputTextFile.setText(dir);
					selectedDir = dir;
				}
			}
		});

		// second line
		// Label labelOriginalLen = new Label(excelInputGroup, SWT.NULL);
		// labelOriginalLen.setText("原始长度：");
		// GridDataFactory.fillDefaults().applyTo(labelOriginalLen);

		// optimlization algorithem
		Label labelAlgorithm = new Label(excelInputGroup, SWT.NULL);
		labelAlgorithm.setText(Messages.ProfileTransEditor_algorithm);
		GridDataFactory.fillDefaults().span(1, 3).applyTo(labelAlgorithm);

		btnAlgorithm1 = new Button(excelInputGroup, SWT.RADIO | SWT.LEFT);
		btnAlgorithm1.setText(Messages.ProfileTransEditor_profileAlgo);
		btnAlgorithm1.setSelection(true);
		btnAlgorithm1.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (btnAlgorithm1.getSelection()) {
					txtMinValue.setEnabled(false);
					txtMaxValue.setEnabled(false);
					txtOriginLen.setEnabled(true);
				}
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		GridDataFactory.fillDefaults().applyTo(btnAlgorithm1);

		Label origLabel = new Label(excelInputGroup, SWT.NONE);
		origLabel.setText(Messages.ProfileTransEditor_len);
		GridDataFactory.fillDefaults().applyTo(origLabel);

		txtOriginLen = new Text(excelInputGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().applyTo(txtOriginLen);
		superviceControl(txtOriginLen, 0);
		Label lenUnit = new Label(excelInputGroup, SWT.NULL);
		lenUnit.setText("mm"); //$NON-NLS-1$

		// btnAlgorithm2 = new Button(excelInputGroup, SWT.RADIO | SWT.LEFT);
		// btnAlgorithm2.setText(Messages.CollectionTransEditor_algorithm_b);
		// btnAlgorithm2.setSelection(false);
		// btnAlgorithm2.addSelectionListener(new SelectionListener() {
		//
		// @Override
		// public void widgetSelected(SelectionEvent e) {
		// if (btnAlgorithm2.getSelection()) {
		// txtMinValue.setEnabled(true);
		// txtMaxValue.setEnabled(true);
		// txtOriginLen.setEnabled(false);
		// }
		// }
		//
		// @Override
		// public void widgetDefaultSelected(SelectionEvent e) {
		//
		// }
		// });
		// GridDataFactory.fillDefaults().applyTo(btnAlgorithm2);
		//
		// Label rangeLabel = new Label(excelInputGroup, SWT.NONE);
		// rangeLabel.setText(Messages.CollectionTransEditor_len_range);
		// GridDataFactory.swtDefaults().applyTo(rangeLabel);
		//
		// txtMinValue = new Text(excelInputGroup, SWT.BORDER);
		// GridDataFactory.swtDefaults().applyTo(txtMinValue);
		// // Label grapLabel = new Label(excelInputGroup, SWT.NONE);
		// // grapLabel.setText("~");
		// // GridDataFactory.swtDefaults().align(SWT.BEGINNING,
		// // SWT.CENTER).applyTo(grapLabel);
		// txtMaxValue = new Text(excelInputGroup, SWT.BORDER);
		// GridDataFactory.swtDefaults().applyTo(txtMaxValue);

		Label headLabel = new Label(excelInputGroup, SWT.NONE);
		headLabel.setText(Messages.ProfileTransEditor_head);
		GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).applyTo(headLabel);
		txtHeadWidth = new Text(excelInputGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(txtHeadWidth);
		txtHeadWidth.setText("100"); //$NON-NLS-1$

		Label gapLabel = new Label(excelInputGroup, SWT.NONE);
		gapLabel.setText(Messages.ProfileTransEditor_gap);
		GridDataFactory.swtDefaults().align(SWT.END, SWT.CENTER).applyTo(gapLabel);
		txtGapWidth = new Text(excelInputGroup, SWT.BORDER);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER).applyTo(txtGapWidth);
		txtGapWidth.setText("10"); //$NON-NLS-1$

		// rule selection
		Label ruleLabel = new Label(excelInputGroup, SWT.NONE);
		ruleLabel.setText(Messages.ProfileTransEditor_optRules);
		GridDataFactory.fillDefaults().applyTo(ruleLabel);
		Button selRuleBtn = new Button(excelInputGroup, SWT.NONE);
		selRuleBtn.setText(Messages.ProfileTransEditor_selectRule);
		selRuleBtn.addSelectionListener(new SelectionListener() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				RuleSelDialog ruleSelDialog = new RuleSelDialog(Display.getCurrent().getActiveShell());
				ruleSelDialog.create();
				ruleSelDialog.open();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		new Label(excelInputGroup, SWT.NULL);

		Group outputGroup = new Group(top, SWT.NONE);
		outputGroup.setText(Messages.ProfileTransEditor_outputExcel);
		GridLayoutFactory.swtDefaults().numColumns(5).applyTo(outputGroup);
		GridDataFactory.fillDefaults().span(2, 1).grab(true, false).applyTo(outputGroup);

		Label labelOutputFile = new Label(outputGroup, SWT.NONE);
		labelOutputFile.setText(Messages.ProfileTransEditor_outputPath);
		GridDataFactory.swtDefaults().applyTo(labelOutputFile);

		textOutputDir = new Text(outputGroup, SWT.BORDER);
		GridDataFactory.swtDefaults().span(3, 1).applyTo(textOutputDir);
		superviceControl(textOutputDir, 0);

		Button outfolderButton = new Button(outputGroup, SWT.PUSH);
		outfolderButton.addListener(SWT.Selection, new Listener() {
			private String selectedDir;

			public void handleEvent(Event event) {
				DirectoryDialog directoryDialog = new DirectoryDialog(Display.getCurrent().getActiveShell());
				directoryDialog.setFilterPath(selectedDir);
				// directoryDialog.setMessage(Messages.TxtTransView_EXCEL_DIALOG);
				String dir = directoryDialog.open();
				if (dir != null) {
					textOutputDir.setText(dir);
					selectedDir = dir;
				}
			}
		});
		outfolderButton.setText(Messages.ProfileTransEditor_selectPath);
		Label labelOutputFilePath = new Label(outputGroup, SWT.NONE);
		labelOutputFilePath.setText(Messages.ProfileTransEditor_outputFile);
		GridDataFactory.swtDefaults().applyTo(labelOutputFilePath);

		final Text textFileName = new Text(outputGroup, SWT.SINGLE | SWT.BORDER);
		GridDataFactory.swtDefaults().applyTo(textFileName);
		GridDataFactory.fillDefaults().span(4, 1).applyTo(textFileName);
		superviceControl(textFileName, 100);

		Button transBtn = new Button(top, SWT.PUSH);
		transBtn.setText(Messages.ProfileTransEditor_beginOpt);
		GridDataFactory.fillDefaults().applyTo(transBtn);
		transBtn.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				String inputFilePath = inputTextFile.getText();
				String outputFilePath = textOutputDir.getText() + File.separator + textFileName.getText();
				// String ruleName = ruleCombo.getText();
				Set<String> ruleNames = transRuleManager.getInstance().getSelItems();
				String algorithmName = null;

				ProfileServiceParams params = new ProfileServiceParams();
				params.setInputFile(inputFilePath);
				params.setOutputFile(outputFilePath);
				params.setRules(ruleNames);
				params.setExcelVersion("2003"); //$NON-NLS-1$

				int lenWidth = Integer.parseInt(txtOriginLen.getText());
				int headWidth = Integer.parseInt(txtHeadWidth.getText());
				int gapWidth = Integer.parseInt(txtGapWidth.getText());
				params.setLenWidth(lenWidth);
				params.setHeadWidth(headWidth);
				params.setGapWidth(gapWidth);

				IProfileService profileService = new ProfileOptimization();
				profileService.doService(params);
				
//				MessageDialog.openConfirm(null, Messages.ProfileTransEditor_2, Messages.ProfileTransEditor_3);
			}
		});
		Button openBtn = new Button(top, SWT.PUSH);
		openBtn.setText(Messages.ProfileTransEditor_changeToDir);
		// GridDataFactory.fillDefaults().applyTo(transBtn);
		openBtn.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (textOutputDir.getText() != null) {
					try {
						Runtime.getRuntime().exec("explorer " + textOutputDir.getText()); //$NON-NLS-1$
					} catch (IOException e) {
						//
					}
				}
			}

		});
	}

	@Override
	public void setFocus() {

	}

	@Override
	public boolean isDirty() {
		return false;
	}

}

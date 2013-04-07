package com.github.cutstock.ui.editors.dialogs;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

public class RuleSelDialog extends TitleAreaDialog {

	private List availList;
	private List selectedlist;

	public RuleSelDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle(Messages.RuleSelDialog_0);
		setMessage(Messages.RuleSelDialog_1, IMessageProvider.INFORMATION);

	}

	@Override
	public Point getInitialSize() {
		return new Point(300, 400);

	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite logGroup = new Composite(parent, SWT.FLAT);
		logGroup.setLayout(new GridLayout(3, false));
		logGroup.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL | GridData.GRAB_HORIZONTAL));

		Label availSelLabel = new Label(logGroup, SWT.NONE);
		GridData gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		FontData newFontData = availSelLabel.getFont().getFontData()[0];
		newFontData.setStyle(SWT.BOLD);
		Font newFont = new Font(this.getShell().getDisplay(), newFontData);
		availSelLabel.setFont(newFont);
		availSelLabel.setText(Messages.RuleSelDialog_2);
		availSelLabel.setLayoutData(gridData);

		new Label(logGroup, SWT.NONE);

		Label selectedLabel = new Label(logGroup, SWT.NONE);
		selectedLabel.setText(Messages.RuleSelDialog_3);
		selectedLabel.setFont(newFont);
		selectedLabel.setLayoutData(gridData);

		availList = new List(logGroup, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gridData.heightHint = 150;
		gridData.minimumWidth = 80;
		availList.setLayoutData(gridData);
		availList
				.setToolTipText("可选规则. \nHighlight the fields you wish to have as independent variables \nin the logistic regression and move to the right list box\nusing the center buttons."); //$NON-NLS-1$

		availList.setItems(TransRuleManager.getInstance().getAvailItems().toArray(new String[0]));

		// You should not re-use GridData
		gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = GridData.FILL;

		Group middleGroup;
		middleGroup = new Group(logGroup, SWT.FLAT);
		middleGroup.setLayout(new GridLayout(1, false));

		Button toRightBtn;
		toRightBtn = new Button(middleGroup, SWT.PUSH);
		toRightBtn.setText(">"); //$NON-NLS-1$
		toRightBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		toRightBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selections = availList.getSelection();
				for (String sel : selections) {
					selectedlist.add(sel);
					availList.remove(sel);
				}
				saveSelected(selectedlist);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});
		Button toLeftBtn;
		toLeftBtn = new Button(middleGroup, SWT.PUSH);
		toLeftBtn.setText("<"); //$NON-NLS-1$
		toLeftBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		toLeftBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] selections = selectedlist.getSelection();
				for (String sel : selections) {
					availList.add(sel);
					selectedlist.remove(sel);
				}
				saveSelected(selectedlist);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
			}
		});

		Button toFullRightBtn;
		toFullRightBtn = new Button(middleGroup, SWT.PUSH);
		toFullRightBtn.setText(">>"); //$NON-NLS-1$
		toFullRightBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));

		toFullRightBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] allItems = availList.getItems();
				for (String sel : allItems) {
					selectedlist.add(sel);
				}
				availList.removeAll();
				saveSelected(selectedlist);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});

		Button toFullLeftBtn;
		toFullLeftBtn = new Button(middleGroup, SWT.PUSH);
		toFullLeftBtn.setText("<<"); //$NON-NLS-1$
		toFullLeftBtn.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1));
		toFullLeftBtn.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				String[] allItems = selectedlist.getItems();
				for (String sel : allItems) {
					availList.add(sel);
				}
				selectedlist.removeAll();
				saveSelected(selectedlist);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {

			}
		});
		selectedlist = new List(logGroup, SWT.BORDER | SWT.MULTI | SWT.V_SCROLL);
		gridData = new GridData(SWT.CENTER, SWT.CENTER, true, true, 1, 1);
		gridData.heightHint = 150;
		gridData.minimumWidth = 50;
		selectedlist.setLayoutData(gridData);
		selectedlist.setItems(TransRuleManager.getInstance().getSelItems().toArray(new String[0]));
		return parent;
	}

	protected void saveSelected(List selectedlist) {
		String[] selItems = selectedlist.getItems();
		TransRuleManager.getInstance().setSelItems(new HashSet<String>(Arrays.asList(selItems)));

	}

	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.horizontalSpan = 3;
		gridData.grabExcessHorizontalSpace = true;
		gridData.grabExcessVerticalSpace = true;
		gridData.horizontalAlignment = SWT.CENTER;

		parent.setLayoutData(gridData);
		// Create Add button
		// Own method as we need to overview the SelectionAdapter
		createOkButton(parent, OK, Messages.RuleSelDialog_4, true);
		// Add a SelectionListener

		// Create Cancel button
		Button cancelButton = createButton(parent, CANCEL, Messages.RuleSelDialog_5, false);
		// Add a SelectionListener
		cancelButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setReturnCode(CANCEL);
				close();
			}
		});
	}

	protected Button createOkButton(Composite parent, int id, String label, boolean defaultButton) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(label);
		button.setFont(JFaceResources.getDialogFont());
		button.setData(new Integer(id));
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				if (isValidInput()) {
					okPressed();
				}
			}
		});
		if (defaultButton) {
			Shell shell = parent.getShell();
			if (shell != null) {
				shell.setDefaultButton(button);
			}
		}
		setButtonLayoutData(button);
		return button;
	}

	private boolean isValidInput() {
		return true;
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	// We need to have the textFields into Strings because the UI gets disposed
	// and the Text Fields are not accessible any more.
	private void saveInput() {
		Set<String> availItems = new HashSet<String>();
		for (String availItem : availList.getItems()) {
			availItems.add(availItem);
		}
		TransRuleManager.getInstance().setAvailItems(availItems);

		Set<String> selectedItems = new HashSet<String>();
		for (String selectedItem : selectedlist.getItems()) {
			selectedItems.add(selectedItem);
		}
		TransRuleManager.getInstance().setSelItems(selectedItems);
	}

	@Override
	protected void okPressed() {
		saveInput();
		super.okPressed();
	}

}
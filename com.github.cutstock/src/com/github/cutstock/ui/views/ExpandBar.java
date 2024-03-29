package com.github.cutstock.ui.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.github.cutstock.CutStockPlugin;
import com.github.cutstock.utils.ImageProvider;
import com.github.cutstock.utils.images.EImages;
import com.github.cutstock.utils.images.IImage;

public class ExpandBar extends Composite {

	// The SWT widgets of the expand bar
	private final Label image;
	private final Label text;
	private final Label arrow;
	private Composite composite = null;
	private Composite top;
	private ExpandBarManager expandBarManager = null;

	// Private variables representing the state, collapsed or not collapsed
	private boolean collapsed = false;

	/**
	 * Constructor
	 * 
	 * @param expandBarManager
	 *            The expand bar manager
	 * @param parent
	 *            The parent composite
	 * @param style
	 *            The style of the Expand bar
	 * @param description
	 *            The expand bar text
	 * @param icon
	 *            File name of the icon
	 */
	public ExpandBar(ExpandBarManager expandBarManager, Composite parent,
			int style, String description, IImage icon, String toolTip) {
		super(parent, style);

		this.expandBarManager = expandBarManager;

		// Create the top composite of the expand bar
		top = new Composite(this, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(1).applyTo(top);

		// Create the headbar
		Composite headbar = new Composite(top, SWT.BORDER);
		GridLayoutFactory.swtDefaults().numColumns(3).margins(2, 2)
				.applyTo(headbar);
		GridDataFactory.fillDefaults().align(SWT.FILL, SWT.FILL)
				.grab(true, false).applyTo(headbar);
		changeBackground(headbar);

		// The icon of the headbar
		image = new Label(headbar, SWT.NONE);
		image.setText("icon");
		changeBackground(image);
		image.setToolTipText(toolTip);
		GridDataFactory.swtDefaults().align(SWT.BEGINNING, SWT.CENTER)
				.applyTo(image);

		// The text of the headbar
		text = new Label(headbar, SWT.NONE);
		changeBackground(text);
		text.setToolTipText(toolTip);
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER)
				.grab(true, false).applyTo(text);

		// The arrow to minimize or maximize the headbar
		arrow = new Label(headbar, SWT.NONE);
		setArrowImage();
		changeBackground(arrow);
		arrow.setToolTipText(toolTip);
		GridDataFactory.fillDefaults().align(SWT.END, SWT.CENTER)
				.applyTo(arrow);
		arrow.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				toggle();
			}
		});

		// Create the expandable body of the expand bar
		setLayout(new FillLayout());
		composite = new Composite(top, SWT.NONE);
		composite.setToolTipText(toolTip);
		GridLayoutFactory.fillDefaults().margins(0, 0).applyTo(composite);
		GridDataFactory.fillDefaults().indent(5, 0).applyTo(composite);
	 
		// Set the text and icon
		setText(description);
		try {
			setImage(ImageProvider.getImage(icon));
		} catch (Exception e) {
			CutStockPlugin.logError(e);
		}
		GridDataFactory.fillDefaults().grab(true, false).applyTo(this);

//		if (Activator.getDefault().getPreferenceStore()
//				.getBoolean("GENERAL_COLLAPSE_EXPANDBAR"))
//			collapse(true);

		expandBarManager.addExpandBar(this);

	}

	/**
	 * Change the color of a widget to white
	 * 
	 * @param widget
	 *            The widget
	 */
	private void changeBackground(Control widget) {

		// Set the background to white
		Color white = new Color(null, 255, 255, 255);
		widget.setBackground(white);
		white.dispose();

	}

	private void setArrowImage() {
		if (collapsed) {
			try {
				arrow.setImage(ImageProvider.getImage(EImages.DROPDOWN));
			} catch (Exception e) {
				CutStockPlugin.logError(e);
			}
		} else {
			try {
				arrow.setImage(ImageProvider.getImage(EImages.DROPUP));
			} catch (Exception e) {
				CutStockPlugin.logError(e);
			}
		}

	}

	/**
	 * Toggle the state of the expand bar
	 */
	private void toggle() {
		collapse(!collapsed);
	}

	/**
	 * Set the state of the expand bar to collapsed or uncollapsed
	 * 
	 * @param collapseMe
	 */
	public void collapse(boolean collapseMe) {

		// Modify the local variable
		collapsed = collapseMe;

		if (collapseMe) {
			// Set the size to 0,0
			GridDataFactory.fillDefaults().hint(0, 0).grab(true, false)
					.applyTo(composite);

		} else {
			GridDataFactory.fillDefaults().indent(5, 0).applyTo(composite);

			// Collapse expand bar items, or not
//			if (Activator.getDefault().getPreferenceStore()
//					.getBoolean("GENERAL_COLLAPSE_EXPANDBAR"))
				this.expandBarManager.collapseOthers(this);

		}

		// update the arrow
		setArrowImage();

		// Redraw the expand bars
		this.getParent().layout(true);
	}

	/**
	 * Set the image
	 * 
	 * @param image
	 *            of the expand bar
	 */
	public void setImage(Image image) {
		this.image.setImage(image);
	}

	/**
	 * Set the text of the expand bar
	 * 
	 * @param text
	 */
	public void setText(String text) {
		this.text.setText(text);
	}

	/**
	 * Add a new action to the body of the expand bar
	 * 
	 * @param action
	 *            The action to add
	 */
	public void addAction(final Action action) {

		// Create a new composite for the action
		Composite actionComposite = new Composite(composite, SWT.NONE);
		GridLayoutFactory.fillDefaults().numColumns(2).margins(0, 0)
				.applyTo(actionComposite);
		GridDataFactory.fillDefaults().indent(5, 0).applyTo(actionComposite);

		// Create the action's icon
		Label actionImage = new Label(actionComposite, SWT.NONE);
		try {
			actionImage.setImage(action.getImageDescriptor().createImage());
			actionImage.setToolTipText(action.getToolTipText());
		} catch (Exception e) {
			CutStockPlugin.logError(e);
		}
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER)
				.indent(0, 0).applyTo(actionImage);

		// Create the action's text
		Label actionLabel = new Label(actionComposite, SWT.NONE);
		actionLabel.setText(action.getText());
		actionLabel.setToolTipText(action.getToolTipText());
		GridDataFactory.fillDefaults().align(SWT.BEGINNING, SWT.CENTER)
				.indent(0, 0).applyTo(actionImage);

		// Run the action, if the user clicks in the composite
		actionComposite.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				action.run();
			}
		});

		// Run the action, if the user clicks on the icon
		actionImage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				action.run();
			}
		});

		// Run the action, if the user clicks on the text
		actionLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				action.run();
			}
		});

	}

}

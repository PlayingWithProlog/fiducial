/**
 * 
 */
package com.theelginworks.spriter;

import java.awt.Dimension;
import java.awt.LayoutManager;

import javax.swing.JPanel;

/**
 * @author anniepoo
 *
 */
public class DynamicBook extends JPanel {

	/**
	 * 
	 */
	public DynamicBook() {
		initDynamicBook();
	}

	/**
	 * @param arg0
	 */
	public DynamicBook(LayoutManager arg0) {
		super(arg0);
		initDynamicBook();
	}

	/**
	 * @param arg0
	 */
	public DynamicBook(boolean arg0) {
		super(arg0);
		initDynamicBook();
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public DynamicBook(LayoutManager arg0, boolean arg1) {
		super(arg0, arg1);
		initDynamicBook();
	}
	
	private void initDynamicBook() {
		this.setPreferredSize(new Dimension(100, 100));
		this.doLayout();
		
	}

}

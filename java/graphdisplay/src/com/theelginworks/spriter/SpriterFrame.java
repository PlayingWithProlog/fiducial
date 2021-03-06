/**
 * 
 */
package com.theelginworks.spriter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

/**
 * @author anniepoo
 *
 */
public final class SpriterFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8728164615692353965L;

	/**
	 * @throws HeadlessException
	 */
	public SpriterFrame() throws HeadlessException {
		super("GraphDisplay");
		
		this.addWindowListener(new WindowAdapter() {	
			public void windowClosing(WindowEvent e) {
				SpriterFrame.this.setVisible(false);
			    SpriterFrame.this.dispose(); 
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
				System.err.println("exiting");
			}
		});
		
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn = new JButton("howdy");
		DynamicBook thepanel = new DynamicBook();
		
		btn.setPreferredSize(new Dimension(200,100));
		
		getContentPane().add(btn , BorderLayout.CENTER);
		getContentPane().add(thepanel, BorderLayout.SOUTH);
		pack();
		setExtendedState(JFrame.MAXIMIZED_HORIZ);
	//	theFrame.setUndecorated(true);
		setVisible(true);
	}
	
	
}

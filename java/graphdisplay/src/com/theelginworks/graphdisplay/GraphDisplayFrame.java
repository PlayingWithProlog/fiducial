/**
 * 
 */
package com.theelginworks.graphdisplay;

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
public final class GraphDisplayFrame extends JFrame {

	/**
	 * @throws HeadlessException
	 */
	public GraphDisplayFrame() throws HeadlessException {
		super("GraphDisplay");
		
		this.addWindowListener(new WindowAdapter() {	
			public void windowClosing(WindowEvent e) {
				GraphDisplayFrame.this.setVisible(false);
			    GraphDisplayFrame.this.dispose(); 
			}
			
			@Override
			public void windowClosed(WindowEvent arg0) {
				System.exit(0);
				System.err.println("exiting");
			}
		});
		
	//	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JButton btn = new JButton("howdy");
		
		btn.setPreferredSize(new Dimension(200,100));
		
		getContentPane().add(btn , BorderLayout.CENTER);
		pack();
		setExtendedState(JFrame.MAXIMIZED_HORIZ);
	//	theFrame.setUndecorated(true);
		setVisible(true);
	}
	
	
}

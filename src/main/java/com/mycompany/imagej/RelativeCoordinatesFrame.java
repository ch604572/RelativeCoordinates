package com.mycompany.imagej;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;


import ij.IJ;
import ij.ImagePlus;
import ij.Macro;
import ij.measure.Calibration;
import ij.plugin.PlugIn;

import java.awt.Component;
import javax.swing.*;
import static javax.swing.GroupLayout.Alignment.*;

@SuppressWarnings("serial")
public class RelativeCoordinatesFrame extends JFrame implements ActionListener, KeyListener//, PlugIn
{
	public static final String TITLE = "Strain";
	
	JLabel jLabelCoordinate;
	
	JLabel jLabelXCoordinate;
	
	JLabel jLabelYCoordinate;
	
	JLabel jLabelAngle;
	
	JLabel jLabelAngleTitle;
	
	JLabel jLabelCurrentXCoordinate;
	
	JLabel jLabelCurrentYCoordinate;
	
	JLabel jLabelCurrentAngle;
	
	JTextField jTextFieldAngle; 
	
	JTextField jTextFieldXCoordinate;	
	
	JTextField jTextFieldYCoordinate;	

	JFrame jFrame;
	
	private JPanel jPanelMain;
	
	private JPanel jPanelMid;
	
	private JPanel jPanelButtons;	


	private JButton button;
	
	private JLabel label;
	
	private JTextField textField;	
	
	public RelativeCoordinatesFrame( )
	{
		initGUI();
	}
	
//	private void fireAction( final ActionEvent event )
//	{
//		if(event.getSource() == jButtonExport) {
//			
//		}
//	}
//	
	@Override
	public void actionPerformed( ActionEvent e ){}
	@Override
	public void keyTyped( KeyEvent e ) {}
	@Override
	public void keyPressed( KeyEvent e ) {}
	@Override
	public void keyReleased( KeyEvent e ) {}
//	@Override
//	public void run(String arg) {}
	
	private void initGUI()
	{
		IJ.log("GUI initing - strain");
		try
		{
			// Middle Panel 
			jPanelMid = new JPanel();
	        getContentPane().add( jPanelMid );
			GroupLayout layout = new GroupLayout( jPanelMid );
			jPanelMid.setLayout(layout);
	        layout.setAutoCreateGaps(true);
	        layout.setAutoCreateContainerGaps(true);
	        
	        // - Components: Relative Coordinates
			jLabelCoordinate = new JLabel( "Relative Coordinate: " );
			jLabelXCoordinate = new JLabel( "X: " );
			jTextFieldXCoordinate = new JTextField( 8 );
			jLabelCurrentXCoordinate = new JLabel( "Current: " );
			jLabelYCoordinate = new JLabel( "Y: " );
			jTextFieldYCoordinate = new JTextField( 8 );
			jLabelCurrentYCoordinate = new JLabel( "Current: " );
			jLabelAngleTitle = new JLabel( "Rotated Angle (Deg): " );
			jLabelAngle = new JLabel( "Angle: " );
			jTextFieldAngle = new JTextField( 6 ); 
			jLabelCurrentAngle  = new JLabel( "Current: " );


			// - Layout
	    	layout.setHorizontalGroup( layout.createParallelGroup()
	    			.addGroup( layout.createSequentialGroup()
	    					.addGroup( layout.createParallelGroup()
	    							.addComponent( jLabelCoordinate )
	    							.addGroup( layout.createSequentialGroup()
					    					.addComponent( jLabelXCoordinate )
					    					.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
					    							.addComponent( jLabelCurrentXCoordinate )	
					    							.addComponent( jTextFieldXCoordinate )				    							
					    					)					    			
					    					.addComponent( jLabelYCoordinate )
					    					.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
					    							.addComponent( jTextFieldYCoordinate ) 
					    							.addComponent( jLabelCurrentYCoordinate )
					    					)					    					
	    							)		
	    					)
	    					.addGap( 20 )
	    					.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
	    							.addComponent( jLabelAngleTitle )
	    							.addGroup( layout.createSequentialGroup()
	    									.addComponent( jLabelAngle )
	    									.addGroup( layout.createParallelGroup( GroupLayout.Alignment.LEADING )
	    											.addComponent( jTextFieldAngle )
	    											.addComponent( jLabelCurrentAngle )		
	    									)
	    							)					    					
	    					)
		    		)

			);
	 
			layout.setVerticalGroup( layout.createSequentialGroup()
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE )
							.addComponent( jLabelCoordinate )
							.addComponent( jLabelAngleTitle )
					)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE )
							.addComponent( jLabelXCoordinate )
							.addComponent( jTextFieldXCoordinate ) 
							.addComponent( jLabelYCoordinate )
							.addComponent( jTextFieldYCoordinate )
							.addComponent( jLabelAngle )
							.addComponent( jTextFieldAngle )
					)
					.addGroup( layout.createParallelGroup(GroupLayout.Alignment.BASELINE )
							.addComponent( jLabelCurrentXCoordinate )
							.addComponent( jLabelCurrentYCoordinate )
							.addComponent( jLabelCurrentAngle )
					)
			);
	        
			// - Event Handling
			jTextFieldXCoordinate.addKeyListener(new KeyListener()
			{
				@Override
				public void keyPressed(final KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						IJ.log("Enter button pressed X");
						try {
//							strain.setEndDiastoleLength( Double.parseDouble( jTextFieldEndDiastole.getText() ) );
//							jLabelCurrentEndDiastole.setText( "current l0 = " + strain.getEndDiastoleLength() );
							
							pack();
						} catch ( final NumberFormatException e ){
							IJ.error( e.getMessage() + "\nPlease enter a number. " );
						}
					}
				}				
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {}
			});
			
			jTextFieldYCoordinate.addKeyListener(new KeyListener()
			{
				@Override
				public void keyPressed(final KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						IJ.log("Enter button pressed Y");
						try {
//							strain.setEndDiastoleLength( Double.parseDouble( jTextFieldEndDiastole.getText() ) );
//							jLabelCurrentEndDiastole.setText( "current l0 = " + strain.getEndDiastoleLength() );
							pack();
						} catch ( final NumberFormatException e ){
							IJ.error( e.getMessage() + "\nPlease enter a number. " );
						}
					}
				}				
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {}
			});
			
			jTextFieldAngle.addKeyListener(new KeyListener()
			{
				@Override
				public void keyPressed(final KeyEvent event) {
					if (event.getKeyCode() == KeyEvent.VK_ENTER) {
						IJ.log("Enter button pressed A");
						try {
//							strain.setEndDiastoleLength( Double.parseDouble( jTextFieldEndDiastole.getText() ) );
//							jLabelCurrentEndDiastole.setText( "current l0 = " + strain.getEndDiastoleLength() );
							pack();
						} catch ( final NumberFormatException e ){
							IJ.error( e.getMessage() + "\nPlease enter a number. " );
						}
					}
				}				
				@Override
				public void keyTyped(KeyEvent e) {}
				@Override
				public void keyReleased(KeyEvent e) {}
			});			
			
	        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );
	        setTitle(TITLE);
	        pack();
			setVisible(true);
		} catch ( final Exception e ){
			e.printStackTrace();
		} 
	}	
}

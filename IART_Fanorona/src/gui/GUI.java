package gui;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import logic.Game;

public class GUI implements ActionListener{

	Game game;
	private Timer timer;
	final static JFrame frame = new JFrame("Fanorona");
	final static JPanel menu = new JPanel();
	final static String PP_BUTTON = "Player vs Player";
	final static String CP_BUTTON = "Computer vs Player";
	final static String CC_BUTTON = "Computer vs Computer";
	

	public GUI(){
		game = new Game();
	}
	
	public void addComponentToPane(Container pane) {

		menu.setLayout(new GridLayout(3,1));
		JButton b1 = new JButton(PP_BUTTON);
		JButton b2 = new JButton(CP_BUTTON);
		JButton b3 = new JButton(CC_BUTTON);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);	
		menu.add(b1);
		menu.add(b2);
		menu.add(b3);
		
		pane.add(menu, BorderLayout.CENTER);        
	}
	
	public void actionPerformed(ActionEvent e)
    {
		String buttonText = ((JButton)e.getSource()).getText();
		if(buttonText.equals(PP_BUTTON))
			System.out.println("PP");
		else if(buttonText.equals(CP_BUTTON))
			System.out.println("CP");
		else if(buttonText.equals(CC_BUTTON))
			System.out.println("CC");
		
		
		//remover jpanel atual
		//criar novo jpanel com tabuleiro
		frame.remove(menu);
		

		final JPanel boardPanel = new BoardPanel();
		boardPanel.setLayout(new GridLayout(5,9));
		
		timer = new Timer(100, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent ae) {
		        boardPanel.repaint();
		    }
		});
		timer.start();
		
		frame.add(boardPanel);
		frame.pack();
    }
	
	

	/**
	 * Create the GUI and show it.  For thread safety,
	 * this method should be invoked from the
	 * event dispatch thread.
	 */
	private void createAndShowGUI() {
		//Create and set up the window.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Create and set up the content pane.
		GUI demo = new GUI();
		demo.addComponentToPane(frame.getContentPane());

		//Display the window.		
		frame.pack();
		frame.setSize(800,300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		
		/* Use an appropriate Look and Feel */
		try {
			//UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (UnsupportedLookAndFeelException ex) {
			ex.printStackTrace();
		} catch (IllegalAccessException ex) {
			ex.printStackTrace();
		} catch (InstantiationException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		/* Turn off metal's use of bold fonts */
		UIManager.put("swing.boldMetal", Boolean.FALSE);

		//Schedule a job for the event dispatch thread:
		//creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				GUI gui = new GUI();
				gui.createAndShowGUI();
			}
		});
	}

}

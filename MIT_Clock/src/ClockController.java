import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ClockController extends JFrame {
	private JLabel hourLabel, minuteLabel;
	private JButton tickButton, resetButton;
	private JPanel buttonHolder;
	private Container contentPane;
	private ClockView view; // Reference to view
	private ClockModel clock; // Reference to model

	public ClockController() {
		contentPane = getContentPane();
		setSize(300, 300); setTitle( "MVC Clock" );
		buttonHolder = new JPanel(); // Create button holder
		contentPane.add( buttonHolder, BorderLayout.SOUTH );
		tickButton = new JButton("Tick");
		resetButton = new JButton("Reset");
		hourLabel = new JLabel("12:");
		minuteLabel = new JLabel("00"); 
		buttonHolder.add(tickButton);
		buttonHolder.add(resetButton);
		buttonHolder.add(hourLabel);
		buttonHolder.add(minuteLabel);
		clock= new ClockModel(720); // Creates model object
		view= new ClockView(clock); // Creates view object
		contentPane.add( view, BorderLayout.CENTER );

		tickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clock.advance(); 
				view.repaint(); // Use model, view
				setLabels(); 
			}});
		resetButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				clock.setMinutes(720); 
				view.repaint(); // Use model, view
				setLabels();
			}});
	} // End constructor 


	public void setLabels() { // Doesn't handle midnight
		int hours = clock.getMinutes() / 60;
		int min = clock.getMinutes() - hours * 60;
		hourLabel.setText(hours + ":");
		if (min < 10) // Minutes should be two digits
			minuteLabel.setText("0" + min);
		else
			minuteLabel.setText("" + min);
	}

	public static void main(String[] args) {
		ClockController application = new ClockController() ;
		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		application.setVisible(true) ;
	}
} 

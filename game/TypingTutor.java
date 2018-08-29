package game;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener{
	
	JLabel lblTime;
	JLabel lblScore;
	JLabel lblWord;
	
	JTextField txtWord ;
	JButton btnStart;
	JButton btnStop;
	
	Timer timer ;
	int timeremaining;
	int score ;
	boolean running;
	
	String[] words;
	
	public TypingTutor(String[] words) {
		
		 this.words = words;
		GridLayout layout =  new GridLayout(3,2);
		super.setLayout(layout);
		
		Font font = new Font("Comic Sans MS", 1, 150);
		
		lblTime = new JLabel("Time: ");
		lblTime.setFont(font);
		super.add(lblTime);
		
		lblScore = new JLabel("Score: ");
		lblScore.setFont(font);
		super.add(lblScore);
		
		lblWord = new JLabel();
		lblWord.setFont(font);
		super.add(lblWord);
		
		txtWord = new JTextField();
		txtWord.setFont(font);
		super.add(txtWord);
		
		btnStart = new JButton("Start");
		btnStart.setFont(font);
		btnStart.addActionListener(this);
		super.add(btnStart);
		
		btnStop = new JButton("Stop");
		btnStop.setFont(font);
		btnStop.addActionListener(this);
		super.add(btnStop);
		
		
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setTitle("Typing Tutor");
		super.setVisible(true);
		
		setUpgame();
	
	}


	private void setUpgame() {
		timer = new Timer(2000,this);
		timeremaining = 5;
		score = 0 ;
		running = false ;
		
		
		lblTime.setText("Time: " + timeremaining);
		
		lblScore.setText("Score: " + score);
		
		lblWord.setText("");
		
		txtWord.setText("");
		txtWord.setEnabled(false);
		
		btnStart.setText("Start");
		
		btnStop.setText("Stop");
		btnStop.setEnabled(false);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == btnStart) {
			handleStart();
		}else if(e.getSource() == btnStop) {
			handleStop();
		}else {
			handleTimer();
			
		}
		
	}


	private void handleTimer() {
		
		timeremaining--;
		
		String correctword = lblWord.getText();
		String typedword = txtWord.getText();
		
		
		if(correctword.equals(typedword)  && typedword.length() > 0) {
			score++;
		}
		
		
		
		lblScore.setText("Score: " + score); 
		if(timeremaining < 0 ) {
			handleStop();
			return;
		}
		
		lblTime.setText("Time: " + timeremaining);
		
		int idx = (int )(Math.random() * words.length);
		txtWord.setText("");
		lblWord.setText(words[idx]);
		
		
	}


	private void handleStop() {
		
		timer.stop();
		int choice = JOptionPane.showConfirmDialog(this, "Replay");
		
		if(choice == JOptionPane.YES_OPTION ) {
			setUpgame();
		}else if(choice == JOptionPane.NO_OPTION) {
			super.dispose();
		}else {
			if(timeremaining < 0 ) {
				setUpgame();
			}else {
				timer.start();
			}
		}
		
	}


	private void handleStart() {
		
		if(running == false) {
			timer.start();
			btnStart.setText("Pause");
			txtWord.setEnabled(true);
			btnStop.setEnabled(true);
			
			running= true ;
		}else if(running == true) {
			timer.stop();
			btnStart.setText("Resume");
			btnStop.setEnabled(true);
			txtWord.setEnabled(false);
			
			running = false ;
		}
		
	
		
	}

}

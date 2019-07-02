package GUI;

import Common.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.UIManager;

public class VotingGui extends JFrame {

	private Time time;	
	private VotingController controller;

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VotingGui frame = new VotingGui();
					frame.setVisible(true);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	
	}
	
	//GUI objects
	private JPanel contentPane;
	private JTextField txtId;
	private JButton btnAddDay;
	private JButton btnAddMonth;
	private JButton btnLogIn;
	private JButton btnCastVote;
	private JList lstCandidates;
	private JLabel lblDate;
	private JPanel pnlVoting;
	private JLabel lblVoter;
	private JLabel lblVotingBegins;
	private JLabel lblVotingEnds;
	private JLabel lblPollName;
	private TitledBorder brdVoting;
	private JScrollPane scrCandidates;
	private JList lstResults;
	private JButton btnAddWeek;
	
	public VotingGui() {
		setTitle("Voting System");
		time = new Time();	
		controller = new VotingController();
		initializeComponents();
		createEvents();
		searchPolls();
	}

	private void initializeComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 856, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);	
		contentPane.setLayout(null);
		
		/* 
		 * Panels.
		 */
		JPanel pnlLogIn = new JPanel();
		pnlLogIn.setBounds(17, 12, 378, 116);
		pnlLogIn.setBorder(new TitledBorder(null, "Log in", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlLogIn);
		pnlLogIn.setLayout(null);

		JPanel pnlDate = new JPanel();
		pnlDate.setBounds(17, 140, 378, 95);
		pnlDate.setBorder(new TitledBorder(null, "Date", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlDate);
		pnlDate.setLayout(null);

		pnlVoting = new JPanel();
		pnlVoting.setBounds(413, 13, 408, 476);
		brdVoting = new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Voting", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0));
		pnlVoting.setBorder(brdVoting);
		contentPane.add(pnlVoting);
		pnlVoting.setLayout(null);
			
		/* 
		 * Labels. 
		 */
		lblVotingBegins = new JLabel("Voting begins:");
		lblVotingBegins.setBounds(18, 56, 280, 16);
		pnlVoting.add(lblVotingBegins);
		
		lblVotingEnds = new JLabel("Voting ends:");
		lblVotingEnds.setBounds(18, 79, 280, 16);
		pnlVoting.add(lblVotingEnds);
		
		JLabel lblIdNumber = new JLabel("ID:");
		lblIdNumber.setBounds(25, 27, 28, 16);
		pnlLogIn.add(lblIdNumber);
		
		lblDate = new JLabel("Current Date: " + time.toString());
		lblDate.setBounds(18, 26, 231, 16);
		pnlDate.add(lblDate);
		
		lblVoter = new JLabel("     Nobody (input ID to log in)");
		lblVoter.setBorder(new TitledBorder(null, "Currently logged in:", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lblVoter.setBounds(0, 56, 378, 60);
		pnlLogIn.add(lblVoter);
		
		/* 
		 * Buttons.
		 */
		btnCastVote = new JButton("Cast Vote");
		btnCastVote.setBounds(263, 433, 130, 25);
		pnlVoting.add(btnCastVote);
		
		btnAddDay = new JButton("Add 1 day");
		btnAddDay.setBounds(18, 55, 104, 25);
		pnlDate.add(btnAddDay);
				
		btnAddMonth = new JButton("Add 1 month");
		btnAddMonth.setBounds(255, 55, 112, 25);
		pnlDate.add(btnAddMonth);
		
		btnAddWeek = new JButton("Add 1 week");
		btnAddWeek.setBounds(137, 55, 104, 25);
		pnlDate.add(btnAddWeek);
				
		btnLogIn = new JButton("Log in");
		btnLogIn.setBounds(219, 23, 81, 25);
		pnlLogIn.add(btnLogIn);
			
		
		/*
		 * Other components.
		 */		
		lstCandidates = new JList();
		lstCandidates.setBorder(new TitledBorder(null, "Candidate List", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lstCandidates.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		scrCandidates = new JScrollPane(lstCandidates);
		scrCandidates.setBounds(18, 108, 375, 312);
		pnlVoting.add(scrCandidates);
		
		lblPollName = new JLabel("Poll name");
		lblPollName.setBounds(18, 27, 375, 16);
		pnlVoting.add(lblPollName);
		
		txtId = new JTextField();
		txtId.setBounds(48, 24, 146, 22);
		txtId.setColumns(10);
		pnlLogIn.add(txtId);
		
		lstResults = new JList();
		lstResults.setBorder(new TitledBorder(null, "Poll results", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lstResults.setBounds(17, 248, 378, 241);
		lstResults.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(lstResults);
		scrollPane.setBounds(17, 248, 378, 241);
		contentPane.add(scrollPane);	
		
	}

	private void createEvents() {
		
		btnAddDay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time.moveDay();
				updateDateLabel();
				searchPolls();
			}
		});
		
		btnAddMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				time.moveMonth();
				updateDateLabel();
				searchPolls();
			}
		});
		

		btnAddWeek.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				time.moveWeek();
				updateDateLabel();
				searchPolls();
			}
		});
		
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				logIn();
				searchPolls();
			}
		});	
		
		btnCastVote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				castVote();
			}
		});
	}

	
	/**
	 * Uses input ID to check if user exists. If yes, sets them as current user.
	 */
	private void logIn() {
		
		String input = txtId.getText();
		
		try {
		    int id = Integer.parseInt(input);
		    
		    if(controller.checkVoter(id)) {
				lblVoter.setText(controller.getCurrentVoter().toString());
			}
		    else {
		    	showMessage("This voter doesn't exist!", "Error");
		    }
		}
		catch (NumberFormatException ex) {
			showMessage("Wrong input!", "Error");
		}
	}

	/**
	 * Casts user's vote for selected candidate (if selected) in the candidate list.
	 */
	private void castVote() {
		Candidate cand = (Candidate)lstCandidates.getSelectedValue();
		Voter curVoter = controller.getCurrentVoter();
		Poll curPoll = controller.getCurrentPoll(time.getCurrentDate());
		
		 if (cand == null) {
				showMessage("You must select a candidate to vote!", "Error");
			}
		 else if (!curVoter.canVote(curPoll.getId())) {
				showMessage("You already voted in this poll!", "Error");
			}
		 else {
			cand.addVote();
			curVoter.voted(curPoll.getId());
			showMessage("Thank you for voting!", "Thank you");
		}	
	}

	/**
	 * Checks if there is a poll happening at the moment and if a voter is logged in.
	 * 
	 * If yes, sets currentPoll and shows voting panel, if no, hides it.
	 */
	private void searchPolls() {
		if (controller.checkPolls(time.getCurrentDate()) && controller.getCurrentVoter() != null) {
			updateVotingPanel();
		}
		else{
			resetVotingPanel();			
		}
		searchWinners();
	}
	
	/**
	 * Checks if current or any missed poll had their results set.
	 * If not, they are set and added to the winner list.
	 */
	private void searchWinners() {
		Poll poll = controller.getCurrentPoll(time.getCurrentDate());
		
		if(poll != null && poll.hasEnded(time.getCurrentDate()) && !poll.isResultAdded()) {
			controller.addWinner(poll);
			lstResults.setListData(controller.getResults().toArray());	
		}
	}
	
	
	private void showMessage(String message, String title) {
	    JOptionPane.showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
	} 
	
	private void updateVotingPanel() {
		Poll poll = controller.getCurrentPoll(time.getCurrentDate());
		
		if(poll != null) {
			showVotingComponents(true);
			lblPollName.setText(poll.getName());
			lblVotingBegins.setText("Voting begins: " + poll.getStartDateString());
			lblVotingEnds.setText("Voting ends: " + poll.getEndDateString());
			lstCandidates.setListData(poll.getCandidateList().toArray());
			brdVoting.setTitle("Voting");
			repaint();
		}		
	}
	
	
	private void resetVotingPanel() {
		showVotingComponents(false);
		brdVoting.setTitle(controller.getNextPollDate(time.getCurrentDate()));
		repaint();
	}
	
	
	private void updateDateLabel() {
		lblDate.setText("Current Date: " + time.toString());
	}
	
	
	private void showVotingComponents(boolean visible) {
		lblPollName.setVisible(visible);
		lblVotingBegins.setVisible(visible);
		lblVotingEnds.setVisible(visible);
		scrCandidates.setVisible(visible);
		btnCastVote.setVisible(visible);
	}
}

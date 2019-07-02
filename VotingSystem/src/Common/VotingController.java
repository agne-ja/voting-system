package Common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class VotingController {
	private ArrayList<Voter> voters;
	private ArrayList<Poll> polls;
	private ArrayList<Candidate> candidates;
	private ArrayList<String> results;
	private Voter currentVoter;
	private int currentPoll = 0;
	private int nextPoll = 0;
	
	public VotingController() {
		FileRead reader = new FileRead();
		
		voters = reader.getVoters();
		candidates = reader.getCandidates();
		polls = reader.getPolls(candidates);
		Collections.sort(polls);
		
		for(Voter voter : voters)
			voter.setGivenVotes(polls);
		
		results = new ArrayList<String>();
	}
	
	/**
	 * Checks if voter exists, if yes, sets current voter.
	 * 
	 * @param id - potential voter id.
	 * @return "true" if voter exists, "false" if not.
	 */
	public boolean checkVoter(int id) {
		for(Voter voter : voters) {
			if(voter.getId() == id) {
				currentVoter = voter;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the current date
	 * and checks whether any of the polls are happening at the moment. 
	 * If yes, the current poll is set. 
	 * Also sets the poll that will happen after the current one.
	 * 
	 * @param date - current system date
	 * @return "true" if any polls are available at the moment, 
	 * "false" if not.
	 */
	public boolean checkPolls(GregorianCalendar date) {
		findNextPoll(date);
		for(Poll poll : polls) {
			if(poll.hasStarted(date) && !poll.hasEnded(date)) {
				currentPoll = poll.getId();
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Finds the poll that is going to happen next based on the current date.
	 * 
	 * @param date - current system date.
	 */
	private void findNextPoll(GregorianCalendar date) {
		for(Poll poll : polls) {
			if(!poll.hasEnded(date)) {
				nextPoll = poll.getId();
				break;
			}				
		}
	}
	
	/**
	 * Gets current date and checks if there are any more polls left.
	 * 
	 * @param date - current system date.
	 * @return "true" if any more polls left, "false" if none.
	 */
	private boolean isNextPoll(GregorianCalendar date) {
		if(nextPoll + 1 == polls.size() && polls.get(nextPoll).hasEnded(date)) {
			return false;
		}		
		return true;
	}
	
	/**
	 * Adds result to result list.
	 * 
	 * @param poll - poll of which the result is added.
	 */
	public void addWinner(Poll poll) {
		results.add(poll.getResult());
		poll.setResultAdded(true);
	}
	
	/**
	 * Returns the date string of next poll, if one exists.
	 * 
	 * @param date - current system date.
	 * @return string of next available poll.
	 */
	public String getNextPollDate(GregorianCalendar date) {
		if(!isNextPoll(date))
			return "No polls currently available.";
		else {
			Poll poll = polls.get(nextPoll);
			return "Next poll from " + poll.getStartDateString() + " to " + poll.getEndDateString();
		}
	}

	public Voter getCurrentVoter() {
		return currentVoter;
	}
	
	public Poll getCurrentPoll(GregorianCalendar date) {
		if(!isNextPoll(date) || currentPoll == nextPoll)
			return polls.get(nextPoll);
		return polls.get(nextPoll - 1);		
	}
	
	public ArrayList<Candidate> getCandidates() {
		return candidates;
	}	
	
	public ArrayList<String> getResults(){
		return results;
	}
}

package Common;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Poll extends BasicInfo implements Comparable<Poll> {
	private GregorianCalendar startDate;
	private GregorianCalendar endDate;
	private ArrayList<Candidate> candidateList;
	private Candidate winner;
	private boolean resultAdded = false;

	public Poll(int id, String name, String startDate, String endDate) {
		super(id, name);
		this.startDate = makeDate(startDate);
		this.endDate = makeDate(endDate);
	}
	
	private GregorianCalendar makeDate(String date) {
		String[] str = date.split("-");
		GregorianCalendar cal = new GregorianCalendar(Integer.parseInt(str[0]), Integer.parseInt(str[1]) - 1, Integer.parseInt(str[2]));
		return cal;
	}
	
	/**
	 * Checks if a poll has a winner and sets it.
	 * @return "true" if only one voter, "false" if multiple.
	 */	
	private boolean checkWinner() {
		Candidate winner = candidateList.get(0);
		boolean multipleWinners = false;
		
		for(int i = 1; i < candidateList.size(); i++) {
			Candidate cand = candidateList.get(i);
			if(cand.compareTo(winner) > 0) {
				winner = cand;
				multipleWinners = false;
			}
			else if(cand.compareTo(winner) == 0)
				multipleWinners = true;
		}
		if(multipleWinners)
			return true;
		this.winner = winner;
		return false;
	}
	
	/**
	 * Creates and returns poll results.
	 * @return Poll result string
	 */
	public String getResult() {
		if(checkWinner()) 
			return "Inconclusive results of " + super.getName();
		return "Winner of "+ super.getName() + " is " + winner.getName();
	}
	
	/**
	 * Checks if the poll started.
	 * @param date - current date of the system.
	 * @return "true" if the poll has started, "false" if not.
	 */
	public boolean hasStarted(GregorianCalendar date) {
		if(date.compareTo(startDate) > -1)
			return true;
		return false;
	}
	
	/**
	 * Checks if the poll ended.
	 * @param date - current date of the system.
	 * @return "true" if the poll has ended, "false" if not.
	 */
	public boolean hasEnded(GregorianCalendar date) {
		if(date.compareTo(endDate) > 0)
			return true;
		return false;
	}

	@Override
	public int compareTo(Poll poll) {
		return startDate.compareTo(poll.startDate);
	}
	
	public void setResultAdded(boolean resultAdded) {
		this.resultAdded = resultAdded;
	}
	
	public boolean isResultAdded() {
		return resultAdded;
	}

	public GregorianCalendar getStartDate() {
		return startDate;
	}
	
	public GregorianCalendar getEndDate() {
		return endDate;
	}

	public String getStartDateString() {
		String str = startDate.get(Calendar.YEAR) + "–"
				+ (startDate.get(Calendar.MONTH) + 1) + "–"
				+ startDate.get(Calendar.DATE);
		return str;
	}
	
	public String getEndDateString() {
		String str = endDate.get(Calendar.YEAR) + "–"
				+ (endDate.get(Calendar.MONTH) + 1) + "–"
				+ endDate.get(Calendar.DATE);
		return str;
	}
	
	public ArrayList<Candidate> getCandidateList() {
		return candidateList;
	}

	public void setCandidateList(ArrayList<Candidate> candidateList) {
		this.candidateList = candidateList;
	}
}


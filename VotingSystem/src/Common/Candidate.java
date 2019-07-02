package Common;

public class Candidate extends BasicInfo implements Comparable<Candidate> {	
	private int votes = 0;
	
	public Candidate(int id, String name) {
		super(id, name);
	}
	
	@Override
	public String toString() {
		String string = "Candidate nr." + getId()+ ", " + getName();		
		return string;
	}
	
	@Override
	public int compareTo(Candidate cand) {
		return votes - cand.votes;
	}

	public int getVotes() {
		return votes;
	}
	
	public void addVote() {
		votes++;
	}
}

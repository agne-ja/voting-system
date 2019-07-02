package Common;

import java.util.ArrayList;
import java.util.HashMap;

public class Voter extends BasicInfo {

	private HashMap<Integer, Boolean> givenVotes;

    public Voter(int id, String name) {
        super(id, name);
        givenVotes = new HashMap<Integer, Boolean>();
    }

    @Override
    public String toString() {
        String string = "    Voter nr." + getId() + "  " + getName();
        return string;
    }

    /**
     * Initializes the HashMap where data, on which polls the voter voted, is stored.
     */
	public void setGivenVotes(ArrayList<Poll> polls) {
		for(Poll poll : polls) {
			int pollId = poll.getId();
			if(!givenVotes.containsKey(pollId)) {
				givenVotes.put(pollId, true);
			}
		}
	}  
	
	public boolean canVote(int pollId) {
		return givenVotes.get(pollId);
	}
	
	public void voted(int pollId) {
		if(canVote(pollId))
			givenVotes.replace(pollId, false);
	}
}


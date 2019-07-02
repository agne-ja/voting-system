package Common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FileRead {
	private boolean messageShowed = false;
	
	/**
	  * Reads data file and returns voter list.
	 * 
	 * @return ArrayList of voters.
	 */
	public ArrayList<Voter> getVoters() {
		
		ArrayList<Voter> voters = new ArrayList<Voter>();
		
		try {
			FileReader file = new FileReader("./data_files/Voters.txt");
			BufferedReader reader = new BufferedReader(file);
					
			String line = reader.readLine();		
			while(line != null) {
				String[] data = line.split(",");
				
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				
				voters.add(new Voter(id, name));
				line = reader.readLine();
			}
			reader.close();
		}
		catch(Exception ex){
			showError();
			messageShowed = true;
		}
		return voters;
	}
	
	/**
	  * Reads specified data file and gets candidate list.
	 * 
	 * @return ArrayList of candidates
	 */
	public ArrayList<Candidate> getCandidates(){

		ArrayList<Candidate> candidates = new ArrayList<Candidate>();
		
		try {
			FileReader file = new FileReader("./data_files/Candidates.txt");
			BufferedReader reader = new BufferedReader(file);
					
			String line = reader.readLine();		
			while(line != null) {
				String[] data = line.split(",");
				
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				
				candidates.add(new Candidate(id, name));
				line = reader.readLine();
			}
			reader.close();
		}
		catch(Exception ex){
			showError();
			messageShowed = true;
		}
		return candidates;
	}
	
	/**
	 * Reads specified data file, returns poll list with assigned candidates.
	 * 
	 * @param candidates - candidate list from which to assign candidates to different polls.
	 * @return ArrayList of polls.
	 */
	public ArrayList<Poll> getPolls(ArrayList<Candidate> candidates) {
		
		ArrayList<Poll> polls = new ArrayList<Poll>();
		
		try {
			FileReader file = new FileReader("./data_files/Polls.txt");
			BufferedReader reader = new BufferedReader(file);
						
			String line = reader.readLine();		
			while(line != null) {
				String[] data = line.split(",");
				
				int id = Integer.parseInt(data[0]);
				String name = data[1];
				String start = data[2];
				String end = data[3];
				
				polls.add(new Poll(id, name, start, end));
				line = reader.readLine();
			}
			assignCandidates(polls, candidates);
			reader.close();
		}
		catch(Exception ex){
			showError();
			messageShowed = true;
		}
		return polls;
	}
	
	/**
	 * Reads file and assigns candidates to polls based on the data.
	 * 
	 * @param polls - where to assign the candidates.
	 * @param candidates - what to assign.
	 */
	private void assignCandidates(ArrayList<Poll> polls, ArrayList<Candidate> candidates) {
		
		try {
			FileReader file = new FileReader("./data_files/PollCandidates.txt");
			BufferedReader reader = new BufferedReader(file);
						
			String line = reader.readLine();	
			
			while(line != null) {
				
				String[] pollData = line.split(",");
				String[] candIds = pollData[1].split(":");
				
				ArrayList<Candidate> cList = new ArrayList<Candidate>();
				
				for(String str : candIds) {
					
					int newId = Integer.parseInt(str);
					
					for(Candidate cand : candidates) {
						if(newId == cand.getId()) {
							cList.add(new Candidate(cand.getId(), cand.getName()));
							break;
						}
					}
				}
					
				for(Poll poll : polls) {
					
					if(poll.getId() == Integer.parseInt(pollData[0])) {
						poll.setCandidateList(cList);
						break;
					}
				}	
				
				line = reader.readLine();
			}
			
			reader.close();
		}
		catch(Exception ex){
			showError();
			messageShowed = true;
		}
	}	
	
	private void showError() {
		if(!messageShowed)
			JOptionPane.showMessageDialog(null, "Something went wrong while reading data files!", "ERROR", JOptionPane.ERROR_MESSAGE);
	} 
}

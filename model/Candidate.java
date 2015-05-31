package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author romain Fanara
 *  Candidate Class.
 * A candidate is represented by a name, 
 * and an array of integer that store counting votes sorted by preference.
 *
 */
public class Candidate {
	
	private String name;
	private String party;
	private ArrayList<BallotPaper> votes; 
	
	
	
	/**Constructor
	 * Assign the name of candidate and initialize score int array of length nCandidate.
	 * @param name 
	 * @param nCandidate
	 */
	public Candidate(String name, String party){
		this.name = name;
		this.party = party;
		this.votes = new ArrayList<BallotPaper>();
	}
	
	
	/**
	 * Copy constructor
	 * @param c
	 */
	public Candidate(Candidate c){
		this.name = c.getName();
		this.party = c.getParty();
		this.votes = c.getBallotPapers();
	}
	
	
	/**
	 * getVotes
	 * get the ballot paper list of the current candidate
	 * @return this.votes
	 */
	public ArrayList<BallotPaper> getBallotPapers() {
		return this.votes;
	}
	
	/**
	 * AddVote
	 * Delete his preferences to assign later to the second candidate
	 * add a ballotpaper to the candidate
	 * @param bp
	 */
	public void addVote(BallotPaper bp){
		BallotPaper bp2 = new BallotPaper(bp);
		bp2.removePreferenceCandiate(this);
		this.votes.add(bp2);
	}


	/**
	 * Return the candidate's name
	 * @return name
	 */
	public String getName(){
		return this.name;
	}
	
	
	/**
	 * giveBallotPaperTo
	 * give all BallotPapers that the candidate owns to the candidate who are on the
	 * second best position on the BallotPaper.
	 * Check if the candidate who will get the ballotPaper is not an eliminated candidate
	 * @param c
	 * @param candidatesList
	 * @return log of what is done
	 */
	public String giveBallotPaperTo(Candidate c , ArrayList<String> candidatesList){
		String log ="";
		
		for(BallotPaper bp : this.votes){
			//check if Candidate c is the second on the BP
			String bestCandidateName = bp.getNameBestCandidate();
			
			//delete eliminated entries
			while(bestCandidateName==null || !candidatesList.contains(bestCandidateName)){
				bp.removePreferenceCandiate(bestCandidateName);
				bestCandidateName = bp.getNameBestCandidate();
			}
			if(bestCandidateName!=null){
				if(bestCandidateName.equals(c.getName())){
					c.addVote(bp);
					log+="one vote to " +c.getName() + "\n";
				}
			}
		}
		return log;
	}
	
	
	/**
	 * Return the finalScore of candidate
	 * @return
	 */
	public int getFinalScore(){
		return this.votes.size();
	}
	
	
	/**
	 * equals
	 * Test equality of 2 candidate.
	 * Only testing the name and party
	 * @param C
	 * @return true
	 */
	public boolean equals(Candidate C){
		return (this.name.equals(C.getName()) &&
				this.party.equals(C.getParty()));
	}


	/**
	 * GetParty
	 * get the candidate party
	 * @return this.party
	 */
	public String getParty() {
		return this.party;
	}
	
	
}

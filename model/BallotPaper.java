package model;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.TreeMap;

/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Ballot Paper 
 *
 *This class represents a vote from users/electors
 *Implemented by a TreeMap, each entry has for key the preference (int)
 *and for value the candidate name that gets the preferences.
 */
public class BallotPaper {
	
	private TreeMap<Integer,String> preferences;
	
	/*
	 * Constructor
	 * intialize preferences hashmap
	 */
	public BallotPaper(){
		this.preferences = new TreeMap<Integer,String>();
	}
	
	
	public BallotPaper(BallotPaper bp) {
		this.preferences = new TreeMap<Integer,String>(bp.preferences);
	}


	/**
	 * addCandidatePreference
	 * @param n
	 * @param c
	 */
	public void addCandidatePreference(int n, Candidate c){
		this.preferences.put(new Integer(n),c.getName());
	}
	
	/**
	 * @param c
	 */
	public void removePreferenceCandiate(Candidate c){
		this.preferences.remove(this.getPrefrence(c));
	}
	
	/**
	 * @param candidateName
	 */
	public void removePreferenceCandiate(String candidateName){
		this.preferences.remove(this.getPrefrence(candidateName));
	}
	
	
	/**
	 * clearBallotPaper
	 * 
	 */
	public void clearBallotPaper(){
		this.preferences.clear();
	}
	
	/**
	 * get Candidate name
	 * having the given preferences
	 * @param pref
	 * @return candidateName
	 */
	public String getCandidateName(int pref){
		return this.preferences.get(new Integer(pref));
	}
	
	public String getNameBestCandidate(){
		if(this.preferences.size()<=0){
			return null;
			}
		else{
			return this.preferences.firstEntry().getValue();	
		}
	}
	
	
	/**
	 * getPreference
	 * get preference of a given candidate
	 * @param c
	 * @return preference
	 */
	public int getPrefrence(Candidate c){
		int result = 0;
		for (Entry<Integer, String> entry : this.preferences.entrySet()) {
		    if(entry.getValue().equals(c.getName())){
		    	result = entry.getKey();
		    	break;
		    }
		}
		return result;
	}

	
	public int getPrefrence(String candidateName){
		int result = 0;
		for (Entry<Integer, String> entry : this.preferences.entrySet()) {
		    if(entry.getValue().equals(candidateName)){
		    	result = entry.getKey();
		    	break;
		    }
		}
		return result;
	}
	

	public int getSize() {
		return this.preferences.size();
	}

	
}

package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;


/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Candidate List
 *
 *This class represents a candidate list owns by the model : VoteCounter
 *Several specific methods are define to manage correctly the list and candidate who are present in.
 *
 *ArrayList implements the candidate list idea.
 *
 *
 */
public class CandidateList {
	
	private ArrayList<Candidate> candidateList;
	
	
	/**
	 * Constructor
	 */
	public CandidateList(){
		this.candidateList = new ArrayList<Candidate>();
	}
	
	
	/**
	 * AddCandidate
	 * add a candidate to the candidateList
	 * @param c
	 */
	public void addCandidate(Candidate c){
		this.candidateList.add(c);
	}
	
	
	/**
	 *ClearList
	 *Clear all entries from candidateList 
	 */
	public void clearList(){
		this.candidateList.clear();
	}
	
	
	/**
	 * Get size
	 * @return size of candidateList
	 */
	public int getSize(){
		return this.candidateList.size();
	}
	
	
	/**
	 * GetCandidate
	 * get Candidate object of the given candidate name 
	 * @param candidateName
	 * @return candidate
	 */
	public Candidate getCandidate(String candidateName){
		Candidate result = null;	
		Iterator<Candidate> iterator = this.candidateList.iterator();
		while(iterator.hasNext()){
			Candidate c = iterator.next();
			if(c.getName().equals(candidateName)){
				result = c;
				break;
			}
		}
		return result;
	}
	
	
	/**
	 * GetCandidateData
	 * get an object of all candidates
	 * Useful to display as an array
	 * @return object[][]
	 */
	public Object[][] getCandidateData() {
		Object data[][]= new Object[this.getSize()][3]; 
		int i = 0;
		Iterator<Candidate> iterator = this.candidateList.iterator();
		while(iterator.hasNext()){
			Candidate c = iterator.next();
			data[i][0] = c.getName();
			data[i][1] = c.getParty();
			data[i][2] = c.getFinalScore();
			i++;
		}
		return data;
	}
	
	
	/**
	 * addVote
	 * add the given ballotPaper to the candidate where its name is provided.
	 * @param candidateName
	 * @param bp
	 */
	public void addVote(String candidateName, BallotPaper bp) {
		this.getCandidate(candidateName).addVote(bp);
	}
	
	
	/**
	 * getFirstCandidate
	 * Get the candidate with the best final score
	 * @return first candidate
	 */
	public Candidate getFirstCandidate(){
		//Security, list have to be sorted before
		this.count();
		return this.candidateList.get(0);
	}
	
	
	/**getLastCandidate
	 * get the candidate who have the worse final score
	 * @return last candidate
	 */
	public Candidate getLastCandidate(){
		//Security, list have to be sorted before
		this.count();
		return this.candidateList.get(this.getSize()-1);
	}
	
	
	/**
	 * getCurrentCandidateNamesList
	 * get the list of current candidate
	 * @return
	 */
	private ArrayList<String> getCurrentCandidateNamesList(){
		ArrayList<String> list = new ArrayList<String>();
		for(Candidate c: this.candidateList){
			list.add(c.getName());
		}
		return list;
	}
	
	
	/**
	 * distributeBallotPaperFromLastCandidate
	 * This method will take the last candidate "last" from the candidate list,
	 * and checks for each other candidate on the list if last has to give a ballotPaper to the current candidate
	 * Then the last candidate is deleted from the candidate list
	 * @return
	 */
	public String distributeBallotPaperFromLastCandidate(){	
		Candidate last = this.getLastCandidate();
		String log =last.getName()+" gives :\n";
		//iteration exept last candidate
		for(int i = 0; i<this.candidateList.size()-1; i++){
			log += last.giveBallotPaperTo(this.candidateList.get(i),this.getCurrentCandidateNamesList());	
		}
		//Remove last candidate
		log+=last.getName()+" eliminated\n";
		this.candidateList.remove(this.getLastCandidate());
		this.count();
		return log;
	}


	/**
	 * count
	 * call the sort method from Java collections and
	 * redefine compare method for comparing the right values and have a sorted list
	 */
	public void count() {
		  Collections.sort(this.candidateList, new Comparator<Candidate>() {
		        public int compare(Candidate c1, Candidate c2) {
		            return c2.getFinalScore()- c1.getFinalScore();
		        }
		    });
	}
}

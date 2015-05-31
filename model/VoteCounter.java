package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import observer.Observable;
import observer.Observer;


/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *VoteCounter
 *
 *Model of the software 
 *
 *Implements the process of vote counting
 *Has a CandidateList, list of candidate 
 *Has an ArrayList of Observers, to communicate and call their method.
 */
public class VoteCounter implements Observable{
	
	private CandidateList candidateList;
	private ArrayList<Observer> observerList = new ArrayList<Observer>();
	
	
	/**
	 *VoteCounter constructor
	 *initialize the candidate List 
	 */
	public VoteCounter(){
		this.candidateList = new CandidateList();
	}
	
	
	/**
	 * add Candidate
	 * add a candidate into the list, then notify the view by giving 
	 * the new candidate list data.
	 * @param candidateName
	 * @param candidateParty
	 */
	public void addCandidate(String candidateName, String candidateParty) {
		Candidate c = new Candidate(candidateName , candidateParty);
		this.candidateList.addCandidate(c);
		//notify and update view
		this.notifyObserver(this.candidateList.getCandidateData());
	}
	
	
	/**
	 * generateBallotPaper
	 * generate Ballot Paper from a HashMap<Integer, String>
	 * verification if candidate names are available in the candidate List
	 * @return ballot paper
	 */
	public BallotPaper generateBallotPaper(HashMap<Integer,String> vote){
		BallotPaper bp = new BallotPaper();
		
		for (Entry<Integer, String> entry : vote.entrySet()) {
			Candidate c = this.candidateList.getCandidate(entry.getValue());
		    if(c != null){ // that means candidate with the given name is found
		    	bp.addCandidatePreference(entry.getKey(), c);
		    }
		}
		return bp;
	}
	
	
	/**
	 * Add vote
	 * Add a ballot Paper (vote) to the given candidate name
	 * @param candidateName
	 * @param bp
	 */
	public void addVote(BallotPaper bp){
		this.candidateList.addVote(bp.getCandidateName(1), bp);
		//update view
		//this.notifyObserver(this.candidatesList.getCandidateData());
	}
	
	
	/**
	 * countCandidateList
	 * count the vote and notify view
	 */
	public void countCandidateList(){
		this.candidateList.count();
		this.notifyObserver(this.candidateList.getCandidateData());
	}
	
	
	/**
	 *Reset
	 *clear the candidate list
	 *then notify the view 
	 */
	public void reset(){
		this.candidateList.clearList();
		this.notifyObserver(new Object[0][0]);
	}
	
	
	/**
	 * eliminateCandidate
	 * call distributeBallotPaperFromLastCandidate method from CandidateList if more than 2 candidate
	 * then checks and interprets the evaluation of the two last candidates
	 * All changes are save in a log string, given to the view and notify the view with the new candidate list 
	 */
	public void eliminateCandidate() {
		String log = "";
		
		if(this.candidateList.getSize()>2){	
			log += this.candidateList.distributeBallotPaperFromLastCandidate();
		}
		else if(this.candidateList.getSize()==2){
				Candidate first = this.candidateList.getFirstCandidate();
				Candidate last = this.candidateList.getLastCandidate();

				if(first.getFinalScore() == (last.getFinalScore())){
					log = "2 tied candidates elected :";
					log += "\nName : "+ first.getName() + " Party : "+ first.getParty();
					log += "\nName : "+ last.getName() + " Party : "+ last.getParty();
				}
				else{
					log = "Candidate elected :\n";
					log += "Name : "+ first.getName() + " Party : "+ first.getParty();
				}
		}
		else{// listcandidate<=0
			log = "error : no candidates\n";
		}
		this.notifyObserver(this.candidateList.getCandidateData());
		this.notifyObserver(log); //generate info
	}

	
	/**
	 * generateCandidatesOneWinner
	 * Clean and Initialize the candidateList with a list of Candidates within 
	 * there is only one winner
	 * Sort the list when it is done
	 */
	public void generateCandidatesOneWinner() {
		this.candidateList.clearList();
		ArrayList<Candidate> list = Data.generateCandidatesOneWinner();
		for(Candidate c : list){
			 this.candidateList.addCandidate(c);
		}
		this.candidateList.count();
		this.notifyObserver(this.candidateList.getCandidateData());
	}
	
	
	/**
	 * generateCandidatesTwoWinners
	 * Clean and Initialize the candidateList with a list of Candidates within 
	 * there is two winners
	 * Then sort the list  and notify observers of the new candidate list when it is done.       
	 */
	public void generateCandidatesTwoWinners() {
		this.candidateList.clearList();
		ArrayList<Candidate> list = Data.generateCandidatesTwoWinners();
		for(Candidate c : list){
			 this.candidateList.addCandidate(c);
		}
		this.candidateList.count();
		this.notifyObserver(this.candidateList.getCandidateData());
	}
	
	
	/*********************************************************
	 * Implemented methods from Observable interface 
	 * 
	 * Model (VoteCounter) communicates informations by notifying Observer (view) 
	 ********************************************************/

	
	/*
	 * addObserver
	 * Add a provided observer object to the Observer list,
	 * 
	 */
	@Override
	public void addObserver(Observer obs) {
		this.observerList.add(obs);
		this.notifyObserver(this.candidateList.getCandidateData());
	}

	
	/* 
	 *removeObserver
	 *Clear observer List
	 */
	@Override
	public void removeObserver() {
		this.observerList = new ArrayList<Observer>();
	}

	
	/* 
	 *notifyObserver
	 *notify all Observer in observer list, by giving an object
	 *in this case, VoteCounter use this method to give to the view the candidate list parsed as object 
	 */
	@Override
	public void notifyObserver(Object[][] data) {
		for(Observer obs : observerList){
		      obs.update(data);
		  }
	}

	
	/* 
	 *notifyObserver
	 *notify all Observer in observer list, by giving a string
	 *in this case, VoteCounter use this method to give to the view an informations to display.
	 */
	@Override
	public void notifyObserver(String informations) {
		for(Observer obs : observerList){
		      obs.displayInformations(informations);
		 }
	}
}

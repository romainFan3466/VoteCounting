package controller;

import java.util.HashMap;

import model.BallotPaper;
import model.VoteCounter;

/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Controller class
 *
 *The controller receives datas/actions from the view, then calls
 *the rights model (VoteCounter) methods 
 */
public class Controller {
	
	private VoteCounter model;
	
	
	/**Constructor
	 * @param model
	 */
	public Controller (VoteCounter model){
		this.model = model;
	}
	
	
	/**
	 * Add candidate to the VoteCounter,
	 * Check if candidate name and party are not empty
	 * @param candidateName
	 * @param candidateParty
	 */
	public void addCandidate(String candidateName, String candidateParty) {
		if(!(candidateName.length()<=0) && !(candidateParty.length()<=0)){
		this.model.addCandidate(candidateName, candidateParty);
		}
	}
	
	/**
	 * addVote
	 * Generate first a BallotPaper according to the current candidates
	 * then send it by addVote method from model
	 * @param vote
	 */
	public void addVote(HashMap<Integer,String> vote){
		BallotPaper bp = this.model.generateBallotPaper(vote); 
		this.model.addVote(bp); 
	}
	
	/**
	 * count
	 * call count method from the model
	 */
	public void count(){
		this.model.countCandidateList();
	}


	/**
	 * reset
	 * call reset method from the model to clear all datas 
	 */
	public void reset() {
		this.model.reset();
	}


	/**
	 * eliminateCandidate
	 * call eliminateCandidate method from the model
	 */
	public void eliminateCandidate() {
		this.model.eliminateCandidate();
	}


	/**
	 * generateCandidatesOneWinner
	 * call generateCandidatesOneWinner method from the model
	 */
	public void generateCandidatesOneWinner() {
		this.model.generateCandidatesOneWinner();
	}
	
	
	/**
	 * generateCandidatesTwoWinners
	 * call generateCandidatesTwoWinners method from the model
	 */
	public void generateCandidatesTwoWinners() {
		this.model.generateCandidatesTwoWinners();
	}
}

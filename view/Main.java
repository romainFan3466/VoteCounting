package view;

import controller.Controller;
import model.Candidate;

import model.VoteCounter;

 
public class Main {
	
	
	
  public static void main(String[] args){
	  //model
	  VoteCounter model = new VoteCounter();

	  //controller
	  Controller controler = new Controller(model);

	  //view
	  VoteCounting v = new VoteCounting(controler);
	  
	  //add view as observer into the model 
	  model.addObserver(v);
	  
  }       
}
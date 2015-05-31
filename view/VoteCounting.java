package view;

import controller.Controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import model.BallotPaper;
import observer.Observer;


/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *VoteCounting 
 *
 *View of software that communicate by calling controller methods.
 */
public class VoteCounting extends JFrame implements Observer{


//Controller used for this frame
private Controller controller;

//Label
private JLabel candidateNameLabel, candidatePartyLabel;

//Text input
 JTextField candidateNameField, candidatePartyField;
 
 //Combobox
 JComboBox combo[];
 
 //Object representing list of candidate
  Object[][] listCandidate;
 
 //Panel
 JPanel panAddVote;
 
 
 //Column titles
 String[] titles = {"Name","Party","Counter"};
 
 
 //Button
 private JButton resetButtonBtn = new JButton("Reset");
 private JButton countingButtonBtn = new JButton("Counting");
 private JButton eliminateButtonBtn = new JButton("Eliminate Candidates");
 
 //Model and Table 
 private DefaultTableModel tabModel;
 private JTable table = new JTable();

 

public VoteCounting(Controller c){                
  this.setSize(1300, 1000);
  this.setTitle("Vote Counting by Romain FANARA");
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setLocationRelativeTo(null);
  this.setResizable(false);
  this.controller = c;
  this.setLayout(new GridLayout(2, 2, 5, 5));
  
  initComposant();                
                 
  this.setVisible(true);
}

private void initComposant(){



	/*******************Add add Candidate panel*********/
	/****************************************************/
	JPanel panAddCandidate = new JPanel();
	panAddCandidate.setBackground(Color.white); 
	JPanel centerPanel = new JPanel(new GridLayout(3,1,10,20));
	centerPanel.setBackground(Color.white); 
	this.candidateNameLabel = new JLabel("Candidate's name :", JLabel.CENTER);
	this.candidatePartyLabel = new JLabel("Candidate's party :", JLabel.CENTER);
	candidateNameField = new JTextField(15);
	candidatePartyField = new JTextField(15);

	panAddCandidate.add(Box.createVerticalStrut(300));
	centerPanel.add(candidateNameLabel);
	centerPanel.add(candidateNameField);
	centerPanel.add(this.candidatePartyLabel);
	centerPanel.add(this.candidatePartyField); 

	panAddCandidate.add(centerPanel);
	
	panAddCandidate.add(Box.createVerticalStrut(2));
	JButton addCandidateButton =  new JButton("Add candidate");
	addCandidateButton.addActionListener(new AddCandidateListener());
	panAddCandidate.add(addCandidateButton);

	panAddCandidate.add(Box.createVerticalStrut(2));
	JButton generateCandidatesButton1 =  new JButton("<html>Generate Candidates list<br>(1 winner)<html>");
	generateCandidatesButton1.addActionListener(new generateCandidateOneWinnerListener());
	panAddCandidate.add(generateCandidatesButton1);
	
	panAddCandidate.add(Box.createVerticalStrut(2));
	JButton generateCandidatesButton2 =  new JButton("<html>Generate Candidates list <br>(2 winners)<html>");
	generateCandidatesButton2.addActionListener(new generateCandidateTwoWinnersListener());
	panAddCandidate.add(generateCandidatesButton2);
	

	/*******************Add list Candidate panel*********/
	/****************************************************/

	JPanel panListCandidate = new JPanel();
	panListCandidate.setBackground(Color.white);
	JPanel panTable = new JPanel();
	panTable.setSize(new Dimension(100,100));
	this.tabModel = new DefaultTableModel(listCandidate,titles);
	this.table.setModel(this.tabModel);
	JScrollPane js = new JScrollPane(this.table);
	js.setPreferredSize(new Dimension(600,400));
	panTable.add(js);
	panListCandidate.add(panTable);
	panListCandidate.setBorder(BorderFactory.createLineBorder(Color.black));



	/*******************Add add vote Candidate panel*********/
	/****************************************************/
	panAddVote = new JPanel();
	panAddVote.setBackground(Color.white);
	


	/*******************Add button panel*********/
	/****************************************************/
	JPanel infoPanel = new JPanel();
	JPanel countingButton = new JPanel();
	JPanel eliminateButton = new JPanel();
	JPanel resetButton = new JPanel();
	JPanel panCountListCandidate = new JPanel();
	JLabel countInfoLabel = new JLabel("<html>Click on counting button to proccess of 1st round counting,<br>"
			+ "then click on eliminate candidate \n button until <br>the election of final candidate(s)"
			+ "<br><br>Note : If You have gerenated candidates from buttons, <br>the list is already counted<html>", JLabel.CENTER);
	
	infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.LINE_AXIS));
	infoPanel.add(countInfoLabel);
	
	countingButton.setLayout(new BoxLayout(countingButton, BoxLayout.LINE_AXIS));
	countingButtonBtn.addActionListener(new CountListener());
	countingButton.add(countingButtonBtn);
	
	
	eliminateButton.setLayout(new BoxLayout(eliminateButton, BoxLayout.LINE_AXIS));
	eliminateButtonBtn.addActionListener(new EliminateCandidateListener());
	eliminateButton.add(eliminateButtonBtn);
	
	resetButton.setLayout(new BoxLayout(resetButton, BoxLayout.LINE_AXIS));
	resetButtonBtn.addActionListener(new ResetListener());
	resetButton.add(resetButtonBtn);

	panCountListCandidate.add(Box.createVerticalStrut(100));
	panCountListCandidate.setLayout(new BoxLayout(panCountListCandidate, BoxLayout.PAGE_AXIS));
	panCountListCandidate.add(infoPanel);
	panCountListCandidate.add(Box.createVerticalStrut(10));
	panCountListCandidate.add(countingButton);
	panCountListCandidate.add(Box.createVerticalStrut(10));
	panCountListCandidate.add(eliminateButton);
	panCountListCandidate.add(Box.createVerticalStrut(30));
	panCountListCandidate.add(resetButton);


	/*******************Add panels to containers of gridlayout*********/
 	/****************************************************/
	this.getContentPane().add(panAddCandidate);
	this.getContentPane().add(panListCandidate);
	this.getContentPane().add(this.panAddVote);
	this.getContentPane().add(panCountListCandidate);
}   



/*
 * function to add voting combo box button 
 */
private void setVotePanel() {
	this.panAddVote.removeAll();
	JPanel centerPanel = new JPanel(new GridLayout(listCandidate.length,2,10,20));
	combo = new JComboBox[listCandidate.length];
	
	for (int i=0; i<listCandidate.length ; i++){
		combo[i] = new JComboBox();
		combo[i].setPreferredSize(new Dimension(100, 20));
	    for(int y = 0; y<listCandidate.length; y++){
	    	combo[i].addItem((y+1));
	    }
	    centerPanel.add(new JLabel((String)listCandidate[i][0]));
	    centerPanel.add(combo[i]);
	} 
	
	this.panAddVote.add(Box.createVerticalStrut(400));
	this.panAddVote.add(centerPanel);
	JButton addVoteButton = new JButton("Add Vote");
	addVoteButton.addActionListener(new AddVoteListener());
	this.panAddVote.add(addVoteButton);
	this.validate();
	this.repaint();
}

/**
 *internal class, implements actionListener
 *When a click is occured, do a controller call to add candidate
 *with the name and party provided 
 */
class AddCandidateListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
      controller.addCandidate(candidateNameField.getText(), candidatePartyField.getText());
    }                
}

/**
 *internal class, implements actionListener
 *When a click is occured, do a controller call to reset the candidates list
 */
class ResetListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
    	controller.reset();
    }                
}

class CountListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
    	controller.count();
    }                
}

class EliminateCandidateListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
    	controller.eliminateCandidate();
    }                
}

class AddVoteListener implements ActionListener{ 
	
	private boolean isDifferent(){
		boolean different=true;
		int i=0;
		while(i<combo.length && different){
			int j=i+1;
			while(j<combo.length && different){
				if(combo[i].getSelectedItem().equals(combo[j].getSelectedItem())){
					different=false;
				}
				j++;
			}
			i++;
		}
		return different;
	}
	
	
    public void actionPerformed(ActionEvent e) {
    	if(!this.isDifferent()){
    		JOptionPane jop = new JOptionPane();
        	jop.showMessageDialog(null, "Preferences must be different with each other", "Error", JOptionPane.ERROR_MESSAGE);	
    	}
    	else{
    		HashMap<Integer,String> vote = new HashMap<Integer,String>();	
        	for (int i=0; i<listCandidate.length ; i++){
        		Integer value = new Integer((int)combo[i].getSelectedItem());
        		vote.put(value,(String) listCandidate[i][0]);
        	}	
        	controller.addVote(vote);	
    	}
    }                
}


class generateCandidateOneWinnerListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
    	controller.generateCandidatesOneWinner();
    }                
}

class generateCandidateTwoWinnersListener implements ActionListener{ 
    public void actionPerformed(ActionEvent e) {
    	controller.generateCandidatesTwoWinners();
    }                
}


@Override
public void update(Object[][] data) {
	listCandidate = data;
	 this.tabModel = new DefaultTableModel(listCandidate,titles);
	 this.table.setModel(this.tabModel);
	this.tabModel.fireTableDataChanged();// reload Candidate table
	this.candidateNameField.setText("");
	this.candidatePartyField.setText("");
	
	if(listCandidate.length>0){
		this.setVotePanel();	
	}//if reset called
	else{ //clean panAddVote form if no candidate
		this.panAddVote.removeAll();
		this.validate();
		this.repaint();
	}
}

@Override
public void displayInformations(String informations) {
	JOptionPane jop = new JOptionPane();
	jop.showMessageDialog(null, informations, "Information", JOptionPane.INFORMATION_MESSAGE);
}




}
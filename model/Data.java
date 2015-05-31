package model;

import java.util.ArrayList;

/**
 * @author Romain FANARA
 * @ID C00198437
 *
 *Class Data 
 *Static class allows to generate candidates list
 */

public class Data {
	

	/**generateCandidate
	 * one winner
	 * @return list of candidate with their ballot papers
	 */
	public static ArrayList<Candidate> generateCandidatesOneWinner() {

		ArrayList<Candidate> list = new ArrayList<Candidate>();

		// Candidate instances
		Candidate A = new Candidate("A", "droite");
		Candidate B = new Candidate("B", "droite");
		Candidate C = new Candidate("C", "droite");
		Candidate D = new Candidate("D", "droite");
		Candidate E = new Candidate("E", "droite");
		Candidate F = new Candidate("F", "droite");

		// BallotPaper instances
		BallotPaper b1 = new BallotPaper();
		BallotPaper b2 = new BallotPaper();
		BallotPaper b3 = new BallotPaper();
		BallotPaper b4 = new BallotPaper();
		BallotPaper b5 = new BallotPaper();
		BallotPaper b6 = new BallotPaper();
		BallotPaper b7 = new BallotPaper();
		BallotPaper b8 = new BallotPaper();
		BallotPaper b9 = new BallotPaper();
		BallotPaper b10 = new BallotPaper();
		BallotPaper b11 = new BallotPaper();
		BallotPaper b12 = new BallotPaper();

		b1.addCandidatePreference(1, A);
		b1.addCandidatePreference(2, B);
		b1.addCandidatePreference(3, C);
		b1.addCandidatePreference(4, D);
		b1.addCandidatePreference(5, E);
		b1.addCandidatePreference(6, F);

		b2.addCandidatePreference(1, A);
		b2.addCandidatePreference(2, B);
		b2.addCandidatePreference(3, C);
		b2.addCandidatePreference(4, E);
		b2.addCandidatePreference(5, F);
		b2.addCandidatePreference(6, D);

		b3.addCandidatePreference(1, A);
		b3.addCandidatePreference(2, C);
		b3.addCandidatePreference(3, B);
		b3.addCandidatePreference(4, F);
		b3.addCandidatePreference(5, E);
		b3.addCandidatePreference(6, D);

		b4.addCandidatePreference(1, B);
		b4.addCandidatePreference(2, E);
		b4.addCandidatePreference(3, D);
		b4.addCandidatePreference(4, A);
		b4.addCandidatePreference(5, F);
		b4.addCandidatePreference(6, C);

		b5.addCandidatePreference(1, F);
		b5.addCandidatePreference(2, A);
		b5.addCandidatePreference(3, B);
		b5.addCandidatePreference(4, D);
		b5.addCandidatePreference(5, C);
		b5.addCandidatePreference(6, E);

		b8.addCandidatePreference(1, C);
		b8.addCandidatePreference(2, F);
		b8.addCandidatePreference(3, E);
		b8.addCandidatePreference(4, A);
		b8.addCandidatePreference(5, B);
		b8.addCandidatePreference(6, D);

		// /////////////////////////////////////////////////////////
		b6.addCandidatePreference(1, B);
		b6.addCandidatePreference(2, A);
		b6.addCandidatePreference(3, F);
		b6.addCandidatePreference(4, E);
		b6.addCandidatePreference(5, D);
		b6.addCandidatePreference(6, C);

		b7.addCandidatePreference(1, B);
		b7.addCandidatePreference(2, C);
		b7.addCandidatePreference(3, D);
		b7.addCandidatePreference(4, F);
		b7.addCandidatePreference(5, A);
		b7.addCandidatePreference(6, E);

		b9.addCandidatePreference(1, C);
		b9.addCandidatePreference(2, A);
		b9.addCandidatePreference(3, B);
		b9.addCandidatePreference(4, D);
		b9.addCandidatePreference(5, E);
		b9.addCandidatePreference(6, F);

		b10.addCandidatePreference(1, B);
		b10.addCandidatePreference(2, D);
		b10.addCandidatePreference(3, E);
		b10.addCandidatePreference(4, A);
		b10.addCandidatePreference(5, F);
		b10.addCandidatePreference(6, C);
		// ///////////////////////////////////////////
		b11.addCandidatePreference(1, C);
		b11.addCandidatePreference(2, A);
		b11.addCandidatePreference(3, B);
		b11.addCandidatePreference(4, C);
		b11.addCandidatePreference(5, A);
		b11.addCandidatePreference(6, B);

		b12.addCandidatePreference(1, C);
		b12.addCandidatePreference(2, A);
		b12.addCandidatePreference(3, B);
		b12.addCandidatePreference(4, C);
		b12.addCandidatePreference(5, A);
		b12.addCandidatePreference(6, B);

		A.addVote(b1);
		A.addVote(b2);
		A.addVote(b3);
		B.addVote(b4);
		B.addVote(b6);
		B.addVote(b7);
		B.addVote(b10);
		C.addVote(b9);
		C.addVote(b8);
		F.addVote(b5);

		list.add(A);
		list.add(B);
		list.add(C);
		list.add(D);
		list.add(E);
		list.add(F);

		return list;
	}
	
	
	/**generateCandidateTwoEquals
	 * two winners
	 * @return list of candidate with their ballot papers
	 */
	public static ArrayList<Candidate> generateCandidatesTwoWinners() {

		ArrayList<Candidate> list = new ArrayList<Candidate>();
		
		Candidate A = new Candidate("A", "droite");
		Candidate B = new Candidate("B", "droite");
		Candidate C = new Candidate("C", "droite");
		
		BallotPaper b1 = new BallotPaper();
		BallotPaper b2 = new BallotPaper();
		BallotPaper b3 = new BallotPaper();
		BallotPaper b4 = new BallotPaper();
		BallotPaper b5 = new BallotPaper();
		BallotPaper b6 = new BallotPaper();
		 
		b1.addCandidatePreference(1, A);
		b1.addCandidatePreference(2, B);
		b1.addCandidatePreference(3, C);
		
		b2.addCandidatePreference(1, A);
		b2.addCandidatePreference(2, C);
		b2.addCandidatePreference(3, B);
		
		b3.addCandidatePreference(1, A);
		b3.addCandidatePreference(2, B);
		b3.addCandidatePreference(3, C);

		b4.addCandidatePreference(1, B);
		b4.addCandidatePreference(2, A);
		b4.addCandidatePreference(3, C);

		b5.addCandidatePreference(1, B);
		b5.addCandidatePreference(2, C);
		b5.addCandidatePreference(3, A);
		
		b6.addCandidatePreference(1, C);
		b6.addCandidatePreference(2, B);
		b6.addCandidatePreference(3, A);

		A.addVote(b1);
		A.addVote(b2);
		A.addVote(b3);
		B.addVote(b4);
		B.addVote(b5);
		C.addVote(b6);
		
		list.add(A);
		list.add(B);
		list.add(C);
		
		return list;

	}
}

/**
 * Chess.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package chess;

import model.game.Game;

/**
 * Driver class for a Chess game
 * 
 * @version Mar 3, 2019
 * @author gemuelealudino
 */
public class Chess {
	
	/**
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {		
		// Instantiate a "game"
		Game game = new Game();
		
		// Game --> Player --> Board --> Piece.
		// Board and Piece (subclasses) are what is in charge
		// of either allowing or preventing a move to occur.
		// A 'player' can request a move, 
		// but the Board/Pieces have the final word.

		System.out.println("\nNew game\n-------------------------");
		System.out.println(game.boardToString());
		
		System.out.println("--- Move tests begin NOW ---\n");
		
		game.whitePlayMove(1, 1, 1, 3);
		game.whitePlayMove(1, 3, 1, 4);
		game.whitePlayMove(1, 4, 1, 5);
		game.blackPlayMove(2, 6, 2, 5);
		game.whitePlayMove(1, 5, 2, 6);
		game.blackPlayMove(1, 6, 1, 4);
		game.blackPlayMove(1, 4, 1, 3);
		game.blackPlayMove(1, 3, 1, 2);
		game.whitePlayMove(2, 1, 2, 2);
		game.blackPlayMove(1, 2, 2, 1);


			
		/* POSSIBLY INVALIDATED */
		// Diagnostic print of Piece positions is in
		// Player.java, playMove(), line 100.
		
		// Board.java, line 190
		// State of board prints after movePiece() completes
		/* END INVALIDATED */
	}
	
}

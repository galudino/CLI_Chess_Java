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
		
		game.whitePlayMove(3, 1, 3, 2);
		game.whitePlayMove(0, 1, 0, 3);
		game.whitePlayMove(0, 3, 0, 4);
		game.whitePlayMove(4, 1, 4, 2);
		game.whitePlayMove(3, 0, 3, 1);
		game.whitePlayMove(4, 2, 4, 3);
		game.blackPlayMove(5, 6, 5, 4);
		game.blackPlayMove(5, 4, 5, 3);
		game.whitePlayMove(4, 3, 4, 4);
		game.whitePlayMove(4, 4, 4, 5);
		game.whitePlayMove(4, 5, 4, 6);
		//game.whitePlayMove(4, 6, 4, 7);
		//game.whitePlayMove(5, 1, 5, 3);
		
		
		//game.whitePlayMove(4, 7, 7, 4);
		
		
		System.out.println(game.boardToString());
		
		/* POSSIBLY INVALIDATED */
		// Diagnostic print of Piece positions is in
		// Player.java, playMove(), line 100.
		
		// Board.java, line 190
		// State of board prints after movePiece() completes
		/* END INVALIDATED */
	}
	
}

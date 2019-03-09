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
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
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
		
		game.whitePlayMove(3, 1, 3, 2);
		game.whitePlayMove(3, 2, 3, 3);
		
		System.out.println(game.boardToString());
	}
	
}

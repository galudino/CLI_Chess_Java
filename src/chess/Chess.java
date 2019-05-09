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

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.game.Game;
import model.game.Move;

/**
 * Driver class for a Chess game
 * 
 * @version Mar 3, 2019
 * @author gemuelealudino
 * @author patricknogaj
 */
public class Chess {

	/**
	 * Program execution begins here
	 * 
	 * @param args Command line arguments
	 * @throws IOException if game.startFromFile is given a String representing
	 *                     a non-existent plaintext input file
	 */
	public static void main(String[] args) throws IOException {
		Game game = new Game();
		
		/**
		 * By default:
		 * -----------
		 * printBoard == true
		 * debugMoveLog == false
		 * debugPostMoveLog == false
		 * debugPieceSetLog == false
		 * 
		 * toggleAllDebugLogs will toggle all of these booleans,
		 * to allow for the respective console messages.
		 * 
		 * You may also use toggleMoveLog(), togglePostMoveLog(),
		 * and togglePieceSetLog() if only some of these messages
		 * are required.
		 * 
		 * togglePrintBoard() will toggle the console printout of the
		 * chess board.
		 */
		
		/**
		 * Comment this out for release!
		 */
		//game.toggleAllDebugLogs();
		game.toggleMoveLog();
		game.togglePieceSetLog();
		game.togglePostMoveLog();
		
		/**
		 * If for some reason, you would
		 * like to turn off the CLI chess board,
		 * here it is:
		 */
		//game.togglePrintBoard();
		

		//game.startFromFile("dat/resign_test.txt");
		//game.saveGameCLI();
		//game.startFromFile("dat/promotion.txt");
		//game.startFromFile("dat/outofbounds.txt");
		//game.startFromFilePlayByPlay("dat/testgame.txt");
		//game.startFromFile("dat/testgame.txt");
		//List<Move> ml = game.getMoveList();

	}
}

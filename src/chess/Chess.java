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

import java.io.IOException;

import model.game.Game;

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
		
		//game.startFromFile("dat/testgame.txt");
		game.start();
	}
	
}

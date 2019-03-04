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

import java.util.*;

import model.*;
import model.Piece.Color;

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

		Board board = new Board();
		
		board.makeMove(0, 1, 0, 2);
	
		
		System.out.println(board);
	}
	
}

/**
 * Bishop.java
 *
 * Copyright (c) 2019 Gemuele Aludino, Patrick Nogaj. 
 * All rights reserved.
 *
 * Rutgers University: School of Arts and Sciences
 * 01:198:213 Software Methodology, Spring 2019
 * Professor Seshadri Venugopal
 */
package model.chess_set.piecetypes;

import model.PieceType;
import model.chess_set.Piece;
import model.game.Position;

/**
 * @version Mar 3, 2019
 * @author gemuelealudino
 *
 */
public class Bishop extends Piece {

	public Bishop(PieceType pieceType, PieceType.Color color) {
		super(color);
		
		if (pieceType.equals(PieceType.BISHOP_R) 
				|| pieceType.equals(PieceType.BISHOP_L)) {
			this.pieceType = pieceType;
		} else {
			System.err.println("ERROR: Set this piece to either "
					+ "PieceType.BISHOP_R or PieceType.BISHOP_L!");
		}
		
		identifier += "Bishop";
	}


	/* (non-Javadoc)
	 * @see model.Piece#move(Position)
	 */
	@Override
	public boolean move(Position pos) {
		boolean result = false;
		
		// evaluate file and rank based on pos field
		// set to true if file and rank agree with pos.
		result = true;
		
		this.pos = pos;
		
		return result;
	}
	
	@Override
	public String toString() {
		return super.toString() + "B";
	}

}
